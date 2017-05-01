package cl.josemanuel.socialconnector2.dummy;

import android.app.Activity;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

import cl.josemanuel.socialconnector2.R;
import cl.josemanuel.socialconnector2.entities.MessageEntity;
import cl.josemanuel.socialconnector2.entities.PhotoEntity;

public class MessagesDummy {

    private Activity activity;

    public MessagesDummy(Activity activity) {
        this.activity = activity;
    }

    public ArrayList<MessageEntity> get3Messages() {
        ArrayList<MessageEntity> messages = new ArrayList<>();

        MessageEntity message1 = new MessageEntity(activity.getResources().getString(R.string.contenido_photo_test1), new Date(), false);
        message1.setPhoto(new PhotoEntity("", createImage("familia.jpg")));
        messages.add(message1);

        MessageEntity message2 = new MessageEntity(activity.getResources().getString(R.string.contenido_photo_test1), new Date(), false);
        message2.setPhoto(new PhotoEntity("", createImage("dog.jpg")));
        messages.add(message2);

        return messages;
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
