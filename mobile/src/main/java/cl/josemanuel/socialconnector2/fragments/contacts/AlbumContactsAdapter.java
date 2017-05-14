package cl.josemanuel.socialconnector2.fragments.contacts;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cl.josemanuel.socialconnector2.R;
import cl.josemanuel.socialconnector2.entities.ContactEntity;
import cl.josemanuel.socialconnector2.fragments.album.AlbumFragment;

import static cl.josemanuel.socialconnector2.activities.MainActivity.photoService;

public class AlbumContactsAdapter extends ArrayAdapter<ContactEntity> {

    private int resource;
    private Context context;
    private List<ContactEntity> contacts;
    private Fragment fragment;

    public AlbumContactsAdapter(
            @NonNull Context context,
            @LayoutRes int resource,
            @NonNull List<ContactEntity> objects,
            Fragment fragment) {
        super(context, resource, objects);
        this.resource = resource;
        this.context = context;
        this.contacts = objects;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(resource, null);
        setClickListener(view, contacts.get(position));
        ((TextView) view.findViewById(R.id.contact_name)).setText(contacts.get(position).getName());

        if (contacts.get(position).getAvatar().getBitmap() == null)
            contacts.get(position).getAvatar().setBitmap(photoService.getPhoto(contacts.get(position).getAvatar()));

        ((ImageView) view.findViewById(R.id.contact_avatar)).setImageBitmap(contacts.get(position).getAvatar().getBitmap());
        return view;
    }

    private void setClickListener(View view, final ContactEntity contactEntity) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = fragment.getFragmentManager().beginTransaction();
                AlbumFragment albumFragment = new AlbumFragment();

                Bundle args = new Bundle();
                args.putSerializable("contact", contactEntity);
                albumFragment.setArguments(args);

                transaction.replace(R.id.content_fragment, albumFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }
}
