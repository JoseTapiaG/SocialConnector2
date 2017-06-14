package cl.josemanuel.socialconnector2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

    public ArrayList<ContactEntity> getMessages() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        Cursor c = db.rawQuery("Select * from " + Contact.TABLE_NAME +
                " join " + Photo.TABLE_NAME +
                " where " + Contact.AVATAR + "=" + Photo.TABLE_NAME + "." + Photo._ID, null);

        c.moveToFirst();
        ArrayList<ContactEntity> contacts = new ArrayList<>();
        while (!c.isAfterLast()) {
            ContactEntity contact = new ContactEntity(
                    c.getString(c.getColumnIndexOrThrow(Contact.NAME)),
                    c.getString(c.getColumnIndexOrThrow(Contact.EMAIL)),
                    c.getString(c.getColumnIndexOrThrow(Contact.SKYPE)));
            contact.setId(c.getInt(c.getColumnIndexOrThrow(Contact._ID)));

            PhotoEntity photo = new PhotoEntity(
                    c.getString(c.getColumnIndexOrThrow(Photo.URL)),
                    c.getString(c.getColumnIndexOrThrow(Photo.PATH)),
                    null);
            photo.setId(c.getInt(c.getColumnIndexOrThrow(Photo._ID)));
            contact.setAvatar(photo);
            contacts.add(contact);
            c.moveToNext();
        }
        db.close();
        return contacts;
    }
}
