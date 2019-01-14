package cl.josemanuel.socialconnector2.fragments.menu;

import android.support.v4.content.ContextCompat;
import android.view.View;

import cl.josemanuel.socialconnector2.R;
import cl.josemanuel.socialconnector2.fragments.SendMessageFragment;

public class SendMessageMenuHandler extends MenuHandler {

    public SendMessageMenuHandler(MenuFragment menuFragment) {
        super(menuFragment);
        backgroundDrawable = R.drawable.menu_send_message_active;
    }

    public void onClick(View v) {
        super.onClick(v);
        changeActivity(new SendMessageFragment());
    }

    @Override
    protected void removeActiveDrawable() {
        currentView.setBackground(ContextCompat.getDrawable(menuFragment.getActivity(), R.drawable.menu_send_message));
    }
}
