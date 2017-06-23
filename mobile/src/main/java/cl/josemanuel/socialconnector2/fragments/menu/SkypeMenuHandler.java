package cl.josemanuel.socialconnector2.fragments.menu;

import android.support.v4.content.ContextCompat;
import android.view.View;

import cl.josemanuel.socialconnector2.R;
import cl.josemanuel.socialconnector2.fragments.contacts.SkypeContactsFragment;

public class SkypeMenuHandler extends MenuHandler {

    public SkypeMenuHandler(MenuFragment menuFragment) {
        super(menuFragment);
        backgroundDrawable = R.drawable.menu_skype_active;
    }

    public void onClick(View v) {
        super.onClick(v);
        changeActivity(new SkypeContactsFragment());
    }

    @Override
    protected void removeActiveDrawable() {
        currentView.setBackground(ContextCompat.getDrawable(menuFragment.getActivity(), R.drawable.menu_skype));
    }
}
