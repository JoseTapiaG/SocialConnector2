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

public class PhotoDummy {

    private Activity activity;

    public PhotoDummy(Activity activity) {
        this.activity = activity;
    }

    public ArrayList<PhotoEntity> loadPhotos() {
        ArrayList<PhotoEntity> photos;

        PhotoDB photoDB = new PhotoDB(activity);

        photos = photoDB.getPhotos();
        if (photos.isEmpty()) {
            PhotoEntity photo1 = new PhotoEntity("", createImage("familia.jpg"), new Date());
            MessageEntity message1 = new MessageEntity(activity.getResources().getString(R.string.contenido_photo_test1), new Date(), false);
            photo1.setMessage(message1);
            photoDB.insertPhoto(photo1, 1);

            PhotoEntity photo2 = new PhotoEntity("", createImage("dog.jpg"), new Date());
            MessageEntity message2 = new MessageEntity(activity.getResources().getString(R.string.contenido_photo_test2), new Date(), false);
            photo2.setMessage(message2);
            photoDB.insertPhoto(photo2, 1);

            PhotoEntity photo3 = new PhotoEntity("", createImage("tortuga.jpg"), new Date());
            MessageEntity message3 = new MessageEntity(activity.getResources().getString(R.string.contenido_photo_test3), new Date(), false);
            photo3.setMessage(message3);
            photoDB.insertPhoto(photo3, 2);
        }
        return photos;
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
