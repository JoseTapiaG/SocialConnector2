package cl.josemanuel.socialconnector2.fragments.album;

import android.os.Bundle;
import android.support.annotation.Nullable;

import cl.josemanuel.socialconnector2.R;
import cl.josemanuel.socialconnector2.constants.Constants;
import cl.josemanuel.socialconnector2.dummy.PhotoDummy;
import cl.josemanuel.socialconnector2.entities.ContactEntity;

import static cl.josemanuel.socialconnector2.activities.MainActivity.photoService;

public class AlbumFragment extends PhotoFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutRes = R.layout.fragment_album;

        ContactEntity contact = (ContactEntity) getArguments().getSerializable("contact");
        photos = photoService.getPhotos(contact.getId());
    }
}
