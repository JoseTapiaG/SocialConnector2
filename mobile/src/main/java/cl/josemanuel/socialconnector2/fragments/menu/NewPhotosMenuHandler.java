package cl.josemanuel.socialconnector2.fragments.menu;

import android.support.v4.content.ContextCompat;
import android.view.View;

import cl.josemanuel.socialconnector2.R;
import cl.josemanuel.socialconnector2.fragments.NewPhotosFragment;
import cl.josemanuel.socialconnector2.fragments.album.AlbumFragment2;

public class NewPhotosMenuHandler extends MenuHandler {

    public NewPhotosMenuHandler(MenuFragment menuFragment) {
        super(menuFragment);
        backgroundDrawable = R.drawable.menu_new_photos_active;
    }

    public void onClick(View v) {
        super.onClick(v);
        changeActivity(new AlbumFragment2());
    }

    @Override
    protected void removeActiveDrawable() {
        currentView.setBackground(ContextCompat.getDrawable(menuFragment.getActivity(), R.drawable.menu_new_photos));
    }
}
