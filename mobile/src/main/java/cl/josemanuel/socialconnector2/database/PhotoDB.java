package cl.josemanuel.socialconnector2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import cl.josemanuel.socialconnector2.database.SocialConnectorContract.Photo;
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
}
