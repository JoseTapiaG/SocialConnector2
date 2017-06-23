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

public class PhotoDB {

    private SocialConnectorDBHelper mDbHelper;

    public PhotoDB(Context context) {
        this.mDbHelper = new SocialConnectorDBHelper(context);
    }

    public long insertPhoto(PhotoEntity photo, long id_contact) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        //Guardar foto
        ContentValues photoValues = new ContentValues();
        photoValues.put(Photo.URL, photo.getUrl());
        photoValues.put(Photo.CONTACT, id_contact);
        photoValues.put(Photo.PATH, photo.getPath());
        photoValues.put(Photo.SEEN, photo.isSeen());
        photoValues.put(Photo.DATE, String.valueOf(photo.getDate()));
        long id_photo = db.insert(Photo.TABLE_NAME, null, photoValues);

        // Guardar mensaje
        ContentValues messageValues = new ContentValues();
        messageValues.put(Message.TEXT, photo.getMessage().getText());
        messageValues.put(Message.DATE, String.valueOf(photo.getMessage().getDate()));
        messageValues.put(Message.CONTACT, id_contact);
        messageValues.put(Message.PHOTO, id_photo);
        long id_message = db.insert(Message.TABLE_NAME, null, messageValues);

        //Actualizar id photo en contacto
        ContentValues updatePhotoValues = new ContentValues();
        updatePhotoValues.put(Photo.MESSAGE, id_message);

        String where = Photo._ID + " = ?";
        String[] whereArgs = {id_photo + ""};

        db.update(Photo.TABLE_NAME, updatePhotoValues, where, whereArgs);

        db.close();
        return id_message;
    }

    public ArrayList<PhotoEntity> getPhotos() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        Cursor c = db.rawQuery("Select * from " + Photo.TABLE_NAME +
                " join " + Contact.TABLE_NAME +
                " on " + Photo.TABLE_NAME + "." + Photo.CONTACT + "=" + Contact.TABLE_NAME + "." + Contact._ID +
                " join " + Message.TABLE_NAME +
                " on " + Photo.TABLE_NAME + "." + Photo.MESSAGE + "=" + Message.TABLE_NAME + "." + Message._ID , null);

        c.moveToFirst();
        ArrayList<PhotoEntity> photos = new ArrayList<>();
        while (!c.isAfterLast()) {
            PhotoEntity photo = new PhotoEntity(
                    c.getString(c.getColumnIndexOrThrow(Photo.URL)),
                    c.getString(c.getColumnIndexOrThrow(Photo.PATH)),
                    null);
            photo.setId(c.getInt(c.getColumnIndexOrThrow(Photo._ID)));

            MessageEntity message = new MessageEntity(
                    c.getString(c.getColumnIndexOrThrow(Message.TEXT)),
                    null,
                    c.getInt(c.getColumnIndexOrThrow(Message.SEEN)) > 0);

            ContactEntity contact = new ContactEntity(
                    c.getString(c.getColumnIndexOrThrow(Contact.NAME)),
                    c.getString(c.getColumnIndexOrThrow(Contact.EMAIL)),
                    c.getString(c.getColumnIndexOrThrow(Contact.SKYPE)));

            photo.setMessage(message);
            photo.setContact(contact);
            photos.add(photo);
            c.moveToNext();
        }
        db.close();
        return photos;
    }

    public ArrayList<PhotoEntity> getPhotos(long idContacto) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        Cursor c = db.rawQuery("Select * from " + Photo.TABLE_NAME +
                " join " + Contact.TABLE_NAME +
                " on " + Photo.TABLE_NAME + "." + Photo.CONTACT + "=" + Contact.TABLE_NAME + "." + Contact._ID +
                " join " + Message.TABLE_NAME +
                " on " + Photo.TABLE_NAME + "." + Photo.MESSAGE + "=" + Message.TABLE_NAME + "." + Message._ID +
                " where " + Photo.TABLE_NAME + "." + Photo.CONTACT + "=" + idContacto, null);

        c.moveToFirst();
        ArrayList<PhotoEntity> photos = new ArrayList<>();
        while (!c.isAfterLast()) {
            PhotoEntity photo = new PhotoEntity(
                    c.getString(c.getColumnIndexOrThrow(Photo.URL)),
                    c.getString(c.getColumnIndexOrThrow(Photo.PATH)),
                    null);
            photo.setId(c.getInt(c.getColumnIndexOrThrow(Photo._ID)));

            MessageEntity message = new MessageEntity(
                    c.getString(c.getColumnIndexOrThrow(Message.TEXT)),
                    null,
                    c.getInt(c.getColumnIndexOrThrow(Message.SEEN)) > 0);

            ContactEntity contact = new ContactEntity(
                    c.getString(c.getColumnIndexOrThrow(Contact.NAME)),
                    c.getString(c.getColumnIndexOrThrow(Contact.EMAIL)),
                    c.getString(c.getColumnIndexOrThrow(Contact.SKYPE)));

            photo.setMessage(message);
            photo.setContact(contact);
            photos.add(photo);
            c.moveToNext();
        }
        db.close();
        return photos;
    }

    public ArrayList<PhotoEntity> getNewPhotos() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        Cursor c = db.rawQuery("Select * from " + Photo.TABLE_NAME +
                " join " + Contact.TABLE_NAME +
                " on " + Photo.TABLE_NAME + "." + Photo.CONTACT + "=" + Contact.TABLE_NAME + "." + Contact._ID +
                " join " + Message.TABLE_NAME +
                " on " + Photo.TABLE_NAME + "." + Photo.MESSAGE + "=" + Message.TABLE_NAME + "." + Message._ID +
                " where " + Photo.TABLE_NAME + "." + Photo.SEEN + "=0", null);

        c.moveToFirst();
        ArrayList<PhotoEntity> photos = new ArrayList<>();
        while (!c.isAfterLast()) {
            PhotoEntity photo = new PhotoEntity(
                    c.getString(c.getColumnIndexOrThrow(Photo.URL)),
                    c.getString(c.getColumnIndexOrThrow(Photo.PATH)),
                    null);
            photo.setId(c.getInt(c.getColumnIndexOrThrow(Photo._ID)));

            MessageEntity message = new MessageEntity(
                    c.getString(c.getColumnIndexOrThrow(Message.TEXT)),
                    null,
                    c.getInt(c.getColumnIndexOrThrow(Message.SEEN)) > 0);

            ContactEntity contact = new ContactEntity(
                    c.getString(c.getColumnIndexOrThrow(Contact.NAME)),
                    c.getString(c.getColumnIndexOrThrow(Contact.EMAIL)),
                    c.getString(c.getColumnIndexOrThrow(Contact.SKYPE)));

            photo.setMessage(message);
            photo.setContact(contact);
            photos.add(photo);
            c.moveToNext();
        }
        db.close();
        return photos;
    }

    public void updatePhoto(long id) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Photo.SEEN, 1);
        String where = Photo._ID + " = ?";
        String[] whereArgs = {id + ""};
        int update = db.update(Photo.TABLE_NAME, values, where, whereArgs);
        db.close();
    }
}
