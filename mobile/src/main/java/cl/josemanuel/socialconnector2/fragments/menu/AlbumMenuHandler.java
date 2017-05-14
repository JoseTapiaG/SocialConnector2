package cl.josemanuel.socialconnector2.fragments.menu;

import android.support.v4.content.ContextCompat;
import android.view.View;

import cl.josemanuel.socialconnector2.R;
import cl.josemanuel.socialconnector2.fragments.album.AlbumFragment;
import cl.josemanuel.socialconnector2.fragments.contacts.AlbumContactsFragment;

public class AlbumMenuHandler extends MenuHandler {

    public AlbumMenuHandler(MenuFragment menuFragment) {
        super(menuFragment);
        backgroundDrawable = R.drawable.menu_album_active;
    }

    public void onClick(View v) {
        super.onClick(v);
        changeActivity(new AlbumContactsFragment());
    }

    @Override
    protected void removeActiveDrawable() {
        currentView.setBackground(ContextCompat.getDrawable(menuFragment.getActivity(), R.drawable.menu_album));
    }
}
