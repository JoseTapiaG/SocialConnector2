package cl.josemanuel.socialconnector2.fragments.menu;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cl.josemanuel.socialconnector2.R;


public class MenuFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        setMenuListeners(view);
        return view;
    }

    private void setMenuListeners(View view) {
        view.findViewById(R.id.albumButton).setOnClickListener(new AlbumMenuClickListener(this));
        view.findViewById(R.id.button5).setOnClickListener(new GenericMenuClickListener(this));
        view.findViewById(R.id.button6).setOnClickListener(new GenericMenuClickListener(this));
        view.findViewById(R.id.button7).setOnClickListener(new GenericMenuClickListener(this));
    }
}
