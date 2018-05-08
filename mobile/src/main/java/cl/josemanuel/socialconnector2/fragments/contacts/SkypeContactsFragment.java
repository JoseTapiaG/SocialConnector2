package cl.josemanuel.socialconnector2.fragments.contacts;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import cl.josemanuel.socialconnector2.R;
import cl.josemanuel.socialconnector2.entities.ContactEntity;
import cl.josemanuel.socialconnector2.services.ContactService;
import cl.josemanuel.socialconnector2.dialogs.Loading;
import cl.josemanuel.socialconnector2.services.clients.ContactServiceClient;

public class SkypeContactsFragment extends Fragment implements ContactsFragment, ContactServiceClient{

    Loading loadingContacts;
    ListView listView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingContacts = new Loading(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_contacts, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.background_phone_call_active));
        listView = (ListView) view.findViewById(R.id.contacts_scrollview);

        loadingContacts.showLoadingDialog("Cargando contactos");
        (new ContactService(getActivity(), loadingContacts,this, "")).execute();
    }

    @Override
    public void setContacts(final List<ContactEntity> contacts) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                listView.setAdapter(new SkypeContactsAdapter(
                        getActivity(),
                        R.layout.item_contact,
                        contacts));
                loadingContacts.hideLoadingDialog();
            }
        });
    }

    @Override
    public void onLoadContacts(List<ContactEntity> contacts) {
        setContacts(contacts);
    }

    @Override
    public void onErrorLoadContacts() {

    }
}
