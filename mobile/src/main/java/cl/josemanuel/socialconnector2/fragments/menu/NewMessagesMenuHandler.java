package cl.josemanuel.socialconnector2.fragments.menu;

import android.support.v4.content.ContextCompat;
import android.view.View;

import cl.josemanuel.socialconnector2.R;
import cl.josemanuel.socialconnector2.fragments.NewMessagesFragment;
import cl.josemanuel.socialconnector2.fragments.contacts.ContactsFragment;

public class NewMessagesMenuHandler extends MenuHandler {

    public NewMessagesMenuHandler(MenuFragment menuFragment) {
        super(menuFragment);
        backgroundDrawable = R.drawable.menu_new_messages_active;
    }

    public void onClick(View v) {
        super.onClick(v);
        changeActivity(new ContactsFragment());
    }

    @Override
    protected void removeActiveDrawable() {
        currentView.setBackground(ContextCompat.getDrawable(menuFragment.getActivity(), R.drawable.menu_new_messages));
    }
}
