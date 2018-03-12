package cl.josemanuel.socialconnector2.fragments.menu;

import android.support.v4.content.ContextCompat;
import android.view.View;

import cl.josemanuel.socialconnector2.R;
import cl.josemanuel.socialconnector2.fragments.settings.SettingsFragment;

/**
 * Created by farodrig on 12-03-18.
 */

class SettingsMenuHandler extends MenuHandler {

    public SettingsMenuHandler(MenuFragment menuFragment) {
        super(menuFragment);
        backgroundDrawable = R.drawable.menu_settings;
    }

    public void onClick(View v) {
        super.onClick(v);
        changeActivity(new SettingsFragment());
    }

    @Override
    protected void removeActiveDrawable() {
        currentView.setBackground(ContextCompat.getDrawable(menuFragment.getActivity(), R.drawable.menu_settings));
    }
}
