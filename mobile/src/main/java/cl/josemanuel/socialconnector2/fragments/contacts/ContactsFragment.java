package cl.josemanuel.socialconnector2.fragments.contacts;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import cl.josemanuel.socialconnector2.R;
import cl.josemanuel.socialconnector2.dummy.ContactsDummy;

public class ContactsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contacts, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView listView = (ListView) view.findViewById(R.id.contacts_scrollview);

        ContactsDummy contactsDummy = new ContactsDummy(getActivity());

        listView.setAdapter(new ContactsAdapter(getActivity(), R.layout.item_contact, contactsDummy.getContacts()));

    }
}
