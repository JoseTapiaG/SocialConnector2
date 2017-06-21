package cl.josemanuel.socialconnector2.fragments.contacts;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import cl.josemanuel.socialconnector2.R;
import cl.josemanuel.socialconnector2.dummy.ContactsDummy;

import static cl.josemanuel.socialconnector2.activities.MainActivity.contactService;

public class AlbumContactsFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_contacts, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.background_album_active));
        ListView listView = (ListView) view.findViewById(R.id.contacts_scrollview);
        listView.setAdapter(new AlbumContactsAdapter(
                getActivity(),
                R.layout.item_contact,
                contactService.getContacts(),
                this));
    }
}
