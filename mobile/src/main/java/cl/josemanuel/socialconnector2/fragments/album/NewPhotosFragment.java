package cl.josemanuel.socialconnector2.fragments.album;

import android.os.Bundle;
import android.support.annotation.Nullable;

import cl.josemanuel.socialconnector2.R;
import cl.josemanuel.socialconnector2.constants.Constants;
import cl.josemanuel.socialconnector2.dummy.PhotoDummy;
import cl.josemanuel.socialconnector2.entities.ContactEntity;

public class NewPhotosFragment extends PhotoFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutRes = R.layout.fragment_new_photos;
        if (Constants.TEST) {
            setTestEnv();
        }
    }

    private void setTestEnv() {
        PhotoDummy photoDummy = new PhotoDummy(getActivity());
        photos = photoDummy.getNewPhotos();
    }
}
