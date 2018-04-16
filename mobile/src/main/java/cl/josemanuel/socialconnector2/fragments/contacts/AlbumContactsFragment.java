package cl.josemanuel.socialconnector2.fragments.contacts;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import cl.josemanuel.socialconnector2.R;
import cl.josemanuel.socialconnector2.dialogs.LoginSocialConnector;
import cl.josemanuel.socialconnector2.entities.ContactEntity;
import cl.josemanuel.socialconnector2.fragments.ServiceFragment;
import cl.josemanuel.socialconnector2.services.ContactService;
import cl.josemanuel.socialconnector2.dialogs.Loading;

import static cl.josemanuel.socialconnector2.constants.Constants.LOGIN_TOKEN;
import static cl.josemanuel.socialconnector2.constants.Constants.PREFS_SC;

public class AlbumContactsFragment extends Fragment implements ContactsFragment, ServiceFragment<String> {

    Loading loadingContacts;
    LoginSocialConnector loginSocialConnector;
    ListView listView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingContacts = new Loading(getActivity());
        loginSocialConnector = new LoginSocialConnector(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_contacts, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.background_album_active));
        listView = (ListView) view.findViewById(R.id.contacts_scrollview);

        String token = getToken();

        if ("".equals(getToken()))
            loginSocialConnector.showLoginDialog(this);
        else {
            onTokenObtained(token);
        }
    }

    @Override
    public void onLoad(String response) {
        //Se obtiene token
        setToken(response);
        onTokenObtained(response);
    }

    @Override
    public void onError(String response) {

    }

    public void onTokenObtained(String token) {
        loadingContacts.showLoadingDialog("Cargando contactos");
        (new ContactService(getActivity(), loadingContacts, this, token)).execute();
    }

    @Override
    public void setContacts(final List<ContactEntity> contacts) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                listView.setAdapter(new AlbumContactsAdapter(
                        getActivity(),
                        R.layout.item_contact,
                        contacts,
                        AlbumContactsFragment.this));
                loadingContacts.hideLoadingDialog();
            }
        });

    }

    public void setToken(String token) {
        SharedPreferences settings = getActivity().getSharedPreferences(PREFS_SC, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(LOGIN_TOKEN, token);
        editor.apply();
    }

    public String getToken() {
        SharedPreferences settings = getActivity().getSharedPreferences(PREFS_SC, 0);
        return settings.getString(LOGIN_TOKEN, "");
    }

}
