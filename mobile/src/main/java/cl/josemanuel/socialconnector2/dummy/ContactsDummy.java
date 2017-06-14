package cl.josemanuel.socialconnector2.dummy;

import android.app.Activity;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

import cl.josemanuel.socialconnector2.R;
import cl.josemanuel.socialconnector2.database.PhotoDB;
import cl.josemanuel.socialconnector2.entities.ContactEntity;
import cl.josemanuel.socialconnector2.entities.MessageEntity;
import cl.josemanuel.socialconnector2.entities.PhotoEntity;

public class ContactsDummy {

    private Activity activity;

    public ContactsDummy(Activity activity) {
        this.activity = activity;
    }

    public ArrayList<ContactEntity> getContacts() {

        PhotoDB photoDB = new PhotoDB(activity);

        ContactEntity contact1 = new ContactEntity(activity.getResources().getString(R.string.contact_name_test1), "test@gmail.com", "test");
        contact1.setAvatar(new PhotoEntity("", createImage("dog.jpg"), new Date()));

        photoDB.insertContact(contact1);

        /*ContactEntity contact2 = new ContactEntity(activity.getResources().getString(R.string.contact_name_test2), "test@gmail.com", "");
        contact2.setAvatar(new PhotoEntity("", createImage("tortuga.jpg"), new Date()));
        contact2.setId(2);*/

        return photoDB.getContacts();
    }

    public String createImage(String photoName) {

        String filepath = Environment.getExternalStorageDirectory().getPath()
                + "/EmailImages";
        File folder = new File(filepath);

        // create folder if does not exist
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File f = new File(activity.getCacheDir() + photoName);
        if (!f.exists()) {
            try {

                InputStream is = activity.getAssets().open(photoName);
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();


                FileOutputStream fos = new FileOutputStream(f);
                fos.write(buffer);
                fos.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return f.getAbsolutePath();
    }

}
