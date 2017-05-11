package cl.josemanuel.socialconnector2.services;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.File;

import cl.josemanuel.socialconnector2.entities.PhotoEntity;

public class PhotoService {

    public PhotoService(Context context) {
        String filepath = Environment.getExternalStorageDirectory().getPath()
                + "/EmailImages";
        File folder = new File(filepath);

        // create folder if does not exist
        if (!folder.exists()){
            Boolean b = folder.mkdirs();
        }
    }

    public Bitmap getPhoto(PhotoEntity photo){
        File imgFile = new File(photo.getPath());
        if(imgFile.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            return bitmap;
        }
        else {
            searchPhoto(photo.getUrl());
            //TODO search image if image in path doesnt exist
        }
        return null;
    }

    private void searchPhoto(String url) {
        savePhoto();
    }

    private void savePhoto() {
    }
}
