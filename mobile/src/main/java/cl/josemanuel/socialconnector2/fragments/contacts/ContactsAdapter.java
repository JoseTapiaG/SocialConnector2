package cl.josemanuel.socialconnector2.fragments.contacts;

import android.content.Context;
import android.graphics.Bitmap;
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

import static cl.josemanuel.socialconnector2.activities.MainActivity.photoService;

class ContactsAdapter extends ArrayAdapter<ContactEntity> {

    private int resource;
    private Context context;
    private List<ContactEntity> contacts;

    public ContactsAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<ContactEntity> objects) {
        super(context, resource, objects);
        this.resource = resource;
        this.context = context;
        this.contacts = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(resource, null);
        ((TextView) view.findViewById(R.id.contact_name)).setText(contacts.get(position).getName());

        if (contacts.get(position).getAvatar().getBitmap() == null)
            contacts.get(position).getAvatar().setBitmap(photoService.getPhoto(contacts.get(position).getAvatar()));

        ((ImageView) view.findViewById(R.id.contact_avatar)).setImageBitmap(contacts.get(position).getAvatar().getBitmap());
        return view;
    }
}
