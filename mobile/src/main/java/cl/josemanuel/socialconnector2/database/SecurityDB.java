package cl.josemanuel.socialconnector2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import cl.josemanuel.socialconnector2.database.SocialConnectorContract.SecurityPass;

/**
 * Created by Vincent on 25-03-2018.
 */

public class SecurityDB {

    private SocialConnectorDBHelper mDbHelper;

    public SecurityDB(Context context) {
        this.mDbHelper = new SocialConnectorDBHelper(context);
    }

    public String getPassword(String social){
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String where = SecurityPass.SOCIAL + " = ?";
        String[] whereArgs = {social};
        Cursor c = db.query(SecurityPass.TABLE_NAME, null, where, whereArgs, null, null, null, "1");

        if( c == null || !c.moveToFirst() ) {
            db.close();
            return null;
        }
        String pass = c.getString(c.getColumnIndexOrThrow(SecurityPass.PASS));
        db.close();
        return pass;
    }

    public long insertPassword(String social, String pass){
        int updated = this.updatePassword(social, pass);
        if (updated != 0)
            return -1;

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues securityValues = new ContentValues();
        securityValues.put(SecurityPass.SOCIAL, social);
        securityValues.put(SecurityPass.PASS, pass);
        long res = db.insert(SecurityPass.TABLE_NAME, null, securityValues);
        db.close();
        return res;
    }

    private int updatePassword(String social, String pass) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues updateMessageValues = new ContentValues();
        updateMessageValues.put(SecurityPass.PASS, pass);

        String where = SecurityPass.SOCIAL + " = ?";
        String[] whereArgs = {social};
        int updated = db.update(SecurityPass.TABLE_NAME, updateMessageValues, where, whereArgs);
        db.close();

        if (updated <= 1) return updated;

        int deleted = this.deleteSocial(social);
        if (updated == deleted) return 0;
        return -1;
    }

    private int deleteSocial(String social) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        String where = SecurityPass.SOCIAL + " = ?";
        String[] whereArgs = {social};

        int deleted = db.delete(SecurityPass.TABLE_NAME, where, whereArgs);
        db.close();
        return deleted;
    }
}
