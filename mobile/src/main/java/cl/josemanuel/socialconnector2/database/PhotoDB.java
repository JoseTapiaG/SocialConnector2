package cl.josemanuel.socialconnector2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import cl.josemanuel.socialconnector2.database.SocialConnectorContract.Photo;
import cl.josemanuel.socialconnector2.database.SocialConnectorContract.Contact;
import cl.josemanuel.socialconnector2.entities.ContactEntity;
import cl.josemanuel.socialconnector2.entities.PhotoEntity;

public class PhotoDB {

    private SocialConnectorDBHelper mDbHelper;

    public PhotoDB(Context context) {
        this.mDbHelper = new SocialConnectorDBHelper(context);
    }

    public long insertPhoto(PhotoEntity photo) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Photo.URL, photo.getUrl());
        values.put(Photo.PATH, photo.getPath());
        values.put(Photo.SEEN, photo.isSeen());
        values.put(Photo.DATE, String.valueOf(photo.getDate()));
        long row = db.insert(Photo.TABLE_NAME, null, values);
        db.close();
        return row;
    }

    public long insertContact(ContactEntity contact) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        long avatar_id = insertPhoto(contact.getAvatar());

        values.put(Contact.NAME, contact.getName());
        values.put(Contact.EMAIL, contact.getEmail());
        values.put(Contact.SKYPE, contact.getSkype());
        values.put(Contact.AVATAR, avatar_id);
        long row = db.insert(Contact.TABLE_NAME, null, values);

        db.close();
        return row;
    }

    public ArrayList<ContactEntity> getContacts() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        Cursor c = db.rawQuery("Select * from " + Contact.TABLE_NAME +
                "join " + Photo.TABLE_NAME +
                "where " + Contact.AVATAR + "=" + Photo._ID, null);

        c.moveToFirst();
        ArrayList<ContactEntity> contacts = new ArrayList<>();
        while (!c.isAfterLast()){
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
