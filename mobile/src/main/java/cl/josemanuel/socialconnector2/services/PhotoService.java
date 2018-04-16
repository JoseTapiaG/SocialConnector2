package cl.josemanuel.socialconnector2.services;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;

import cl.josemanuel.socialconnector2.database.PhotoDB;
import cl.josemanuel.socialconnector2.entities.PhotoEntity;

public class PhotoService {

    PhotoDB photoDB;

    public PhotoService(Context context) {

        photoDB = new PhotoDB(context);

        String filepath = Environment.getExternalStorageDirectory().getPath()
                + "/EmailImages";
        File folder = new File(filepath);

        // create folder if does not exist
        if (!folder.exists()) {
            Boolean b = folder.mkdirs();
        }
    }

    public Bitmap getBitmap(PhotoEntity photo) {
        if (photo.getPath() != null) {
            File imgFile = new File(photo.getPath());
            if (imgFile.exists()) {
                return BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            } else {
                searchPhoto(photo.getUrl());
            }
        } else if (photo.getUrl() != null) {
            searchPhoto(photo.getUrl());
        }
        return null;
    }

    private void searchPhoto(String url) {
        savePhoto();
    }

    private void savePhoto() {
    }

    public ArrayList<PhotoEntity> getPhotos(long idContacto) {
        return photoDB.getPhotos(idContacto);
    }

    public ArrayList<PhotoEntity> getNewPhotos() {
        return photoDB.getNewPhotos();
    }

    public void updatePhotoSeenState(long id) {
        photoDB.updatePhoto(id);
    }

    public long countNewPhotos() {
        return photoDB.countNewPhotos();
    }
}
