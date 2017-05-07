package cl.josemanuel.socialconnector2.fragments.menu;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cl.josemanuel.socialconnector2.R;


public class MenuFragment extends Fragment {

    public MenuHandler currentMenuHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        setMenuListeners(view);
        return view;
    }

    private void setMenuListeners(View view) {
        view.findViewById(R.id.albumButton).setOnClickListener(new AlbumMenuHandler(this));
        view.findViewById(R.id.sendMessageButton).setOnClickListener(new SendMessageMenuHandler(this));
        view.findViewById(R.id.newPhotosButton).setOnClickListener(new NewPhotosMenuHandler(this));
        view.findViewById(R.id.newMessagesButton).setOnClickListener(new NewMessagesMenuHandler(this));
    }
}
