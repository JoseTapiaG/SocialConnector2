package cl.josemanuel.socialconnector2.fragments.contacts;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

public class SkypeContactsAdapter extends ArrayAdapter<ContactEntity> {

    private int resource;
    private Context context;
    private List<ContactEntity> contacts;

    public SkypeContactsAdapter(
            @NonNull Context context,
            @LayoutRes int resource,
            @NonNull List<ContactEntity> objects) {
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
        setClickListener(view, contacts.get(position));
        ((TextView) view.findViewById(R.id.contact_name)).setText(contacts.get(position).getName());

        if (contacts.get(position).getAvatar().getBitmap() == null)
            contacts.get(position).getAvatar().setBitmap(photoService.getBitmap(contacts.get(position).getAvatar()));

        ((ImageView) view.findViewById(R.id.contact_avatar)).setImageBitmap(contacts.get(position).getAvatar().getBitmap());
        return view;
    }

    private void setClickListener(View view, final ContactEntity contactEntity) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "skype:" + contactEntity.getSkype() + "?call&video=true";
                Uri skypeUri = Uri.parse(uri);
                Intent myIntent = new Intent(Intent.ACTION_VIEW, skypeUri);

                myIntent.setComponent(new ComponentName("com.skype.raider", "com.skype.raider.Main"));
                context.startActivity(myIntent);
            }
        });
    }
}
