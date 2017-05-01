package cl.josemanuel.socialconnector2.fragments.albumFragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cl.josemanuel.socialconnector2.R;

import static cl.josemanuel.socialconnector2.activities.MainActivity.photoService;

public class NextClickListener implements View.OnClickListener {


    private AlbumFragment albumFragment;

    public NextClickListener(AlbumFragment albumFragment) {
        this.albumFragment = albumFragment;
    }

    @Override
    public void onClick(View v) {
        //update currentMessage
        albumFragment.index++;
        albumFragment.currentMessage = albumFragment.messages.get(albumFragment.index);

        //update contact name and text
        ((TextView) albumFragment.getView().findViewById(R.id.contact_name)).setText(albumFragment.currentMessage.getContact().getName());
        ((TextView) albumFragment.getView().findViewById(R.id.message_text)).setText(albumFragment.currentMessage.getText());

        //search and set image of message
        ((ImageView) albumFragment.getView().findViewById(R.id.photo)).setImageBitmap(photoService.getPhoto(albumFragment.currentMessage.getPhoto()));
    }
}
