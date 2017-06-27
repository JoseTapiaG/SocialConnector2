package cl.josemanuel.socialconnector2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import cl.josemanuel.socialconnector2.database.SocialConnectorContract.Contact;
import cl.josemanuel.socialconnector2.database.SocialConnectorContract.Message;
import cl.josemanuel.socialconnector2.database.SocialConnectorContract.Photo;
import cl.josemanuel.socialconnector2.entities.ContactEntity;
import cl.josemanuel.socialconnector2.entities.MessageEntity;
import cl.josemanuel.socialconnector2.entities.PhotoEntity;

public class MessageDB {

    private SocialConnectorDBHelper mDbHelper;

    public MessageDB(Context context) {
        this.mDbHelper = new SocialConnectorDBHelper(context);
    }

    public long insertMessage(MessageEntity message, long id_contact) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Guardar mensaje
        ContentValues messageValues = new ContentValues();
        messageValues.put(Message.TEXT, message.getText());
        messageValues.put(Message.DATE, String.valueOf(message.getDate()));
        messageValues.put(Message.CONTACT, id_contact);
        long id_message = db.insert(Message.TABLE_NAME, null, messageValues);

        //Guardar foto
        ContentValues photoValues = new ContentValues();
        photoValues.put(Photo.URL, message.getPhoto().getUrl());
        photoValues.put(Photo.CONTACT, id_contact);
        photoValues.put(Photo.MESSAGE, id_message);
        photoValues.put(Photo.PATH, message.getPhoto().getPath());
        photoValues.put(Photo.SEEN, message.getPhoto().isSeen());
        photoValues.put(Photo.DATE, String.valueOf(message.getPhoto().getDate()));
        long id_photo = db.insert(Photo.TABLE_NAME, null, photoValues);

        //Actualizar id photo en contacto
        ContentValues updateMessageValues = new ContentValues();
        updateMessageValues.put(Message.PHOTO, id_photo);

        String where = Message._ID + " = ?";
        String[] whereArgs = {id_message + ""};

        db.update(Message.TABLE_NAME, updateMessageValues, where, whereArgs);

        db.close();
        return id_message;
    }

    public ArrayList<MessageEntity> getMessages() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        Cursor c = db.rawQuery("Select " + Message.SELECT_ALL + ", " +
                Contact.SELECT_ALL + ", " +
                Photo.SELECT_ALL +
                " from " + Message.TABLE_NAME +
                " join " + Contact.TABLE_NAME +
                " on " + Message.TABLE_NAME + "." + Message.CONTACT + "=" + Contact.TABLE_NAME + "." + Contact._ID +
                " left join " + Photo.TABLE_NAME +
                " on " + Message.TABLE_NAME + "." + Message.PHOTO + "=" + Photo.TABLE_NAME + "." + Photo._ID, null);

        c.moveToFirst();
        ArrayList<MessageEntity> messages = new ArrayList<>();
        while (!c.isAfterLast()) {

            MessageEntity message = new MessageEntity(
                    c.getString(c.getColumnIndexOrThrow(Message.TABLE_NAME + "_" + Message.TEXT)),
                    null,
                    c.getInt(c.getColumnIndexOrThrow(Message.TABLE_NAME + "_" + Message.SEEN)) > 0);
            message.setId(c.getInt(c.getColumnIndexOrThrow(Message.TABLE_NAME + "_" + Message._ID)));

            ContactEntity contact = new ContactEntity(
                    c.getString(c.getColumnIndexOrThrow(Contact.TABLE_NAME + "_" + Contact.NAME)),
                    c.getString(c.getColumnIndexOrThrow(Contact.TABLE_NAME + "_" + Contact.EMAIL)),
                    c.getString(c.getColumnIndexOrThrow(Contact.TABLE_NAME + "_" + Contact.SKYPE)));
            contact.setId(c.getInt(c.getColumnIndexOrThrow(Contact.TABLE_NAME + "_" + Contact._ID)));

            message.setContact(contact);

            if (c.getInt(c.getColumnIndexOrThrow(Message.TABLE_NAME + "_" + Message.PHOTO)) != 0) {
                PhotoEntity photo = new PhotoEntity(
                        c.getString(c.getColumnIndexOrThrow(Photo.TABLE_NAME + "_" + Photo.URL)),
                        c.getString(c.getColumnIndexOrThrow(Photo.TABLE_NAME + "_" + Photo.PATH)),
                        null);
                photo.setId(c.getInt(c.getColumnIndexOrThrow(Photo.TABLE_NAME + "_" + Photo._ID)));
                message.setPhoto(photo);
            }

            messages.add(message);
            c.moveToNext();
        }
        db.close();
        return messages;
    }

    public long countNewMessages() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        return DatabaseUtils.queryNumEntries(db, Message.TABLE_NAME,
                "seen=?", new String[] {"0"});
    }
}
