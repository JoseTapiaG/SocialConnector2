package cl.josemanuel.socialconnector2.fragments.menu;

import android.view.View;
import cl.josemanuel.socialconnector2.fragments.AlbumFragment;

public class AlbumMenuClickListener extends GenericMenuClickListener {

    public AlbumMenuClickListener(MenuFragment menuFragment) {
        super(menuFragment);
    }

    @Override
    public void onClick(View v) {
        changeActivity(new AlbumFragment());
    }
}
