package cl.josemanuel.socialconnector2.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import cl.josemanuel.socialconnector2.database.SocialConnectorContract.*;

public class SocialConnectorDBHelper extends SQLiteOpenHelper {

    private static final String TEXT_TYPE = " TEXT";
    private static final String DATE_TYPE = " DATE";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String BOOLEAN_TYPE = " BOOLEAN";
    private static final String DATABASE_NAME = " SocialConnector.db";
    private static final int DATABASE_VERSION = 4;
    private static final String COMMA_SEP = ", ";

    private static final String SQL_CREATE_PHOTO =
            "CREATE TABLE " + Photo.TABLE_NAME + " (" +
                    Photo._ID + " INTEGER PRIMARY KEY, " +
                    Photo.URL + TEXT_TYPE + COMMA_SEP +
                    Photo.PATH + TEXT_TYPE + COMMA_SEP +
                    Photo.SEEN + BOOLEAN_TYPE + COMMA_SEP +
                    Photo.DATE + DATE_TYPE + COMMA_SEP +
                    Photo.CONTACT + INTEGER_TYPE + COMMA_SEP +
                    Photo.MESSAGE + INTEGER_TYPE + " )";

    private static final String SQL_CREATE_CONTACT =
            "CREATE TABLE " + Contact.TABLE_NAME + " (" +
                    Contact._ID + " INTEGER PRIMARY KEY, " +
                    Contact.NAME + TEXT_TYPE + COMMA_SEP +
                    Contact.EMAIL + TEXT_TYPE + COMMA_SEP +
                    Contact.SKYPE + BOOLEAN_TYPE + COMMA_SEP +
                    Contact.AVATAR + INTEGER_TYPE + " )";

    private static final String SQL_CREATE_MESSAGE =
            "CREATE TABLE " + Message.TABLE_NAME + " (" +
                    Message._ID + " INTEGER PRIMARY KEY, " +
                    Message.TEXT + TEXT_TYPE + COMMA_SEP +
                    Message.DATE + DATE_TYPE + COMMA_SEP +
                    Message.SEEN + BOOLEAN_TYPE + COMMA_SEP +
                    Message.CONTACT + INTEGER_TYPE + COMMA_SEP +
                    Message.PHOTO + INTEGER_TYPE + " )";

    private static final String SQL_DELETE_CONTACTS =
            "DROP TABLE IF EXISTS " + Contact.TABLE_NAME;

    private static final String SQL_DELETE_MESSAGES =
            "DROP TABLE IF EXISTS " + Message.TABLE_NAME;

    private static final String SQL_DELETE_PHOTOS =
            "DROP TABLE IF EXISTS " + Photo.TABLE_NAME;

    public SocialConnectorDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_CONTACT);
        db.execSQL(SQL_CREATE_MESSAGE);
        db.execSQL(SQL_CREATE_PHOTO);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_CONTACTS);
        db.execSQL(SQL_DELETE_MESSAGES);
        db.execSQL(SQL_DELETE_PHOTOS);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
