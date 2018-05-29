package cl.josemanuel.socialconnector2.settings;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import cl.josemanuel.socialconnector2.R;
import cl.josemanuel.socialconnector2.services.CredentialsService;
import cl.josemanuel.socialconnector2.settings.socialnetwork.SocialNetworkArrayAdapter;
import cl.josemanuel.socialconnector2.settings.socialnetwork.SocialNetworkSetup;

public class SettingsFragment extends Fragment {

    ListView listView;
    private ArrayList<SocialNetworkSetup> socialnetworks = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        socialnetworks = CredentialsService.getSocialNetworks();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        listView = (ListView) view.findViewById(R.id.settings_list);

        SocialNetworkArrayAdapter adapter = new SocialNetworkArrayAdapter(this.getActivity(),
                0, socialnetworks);

        listView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
