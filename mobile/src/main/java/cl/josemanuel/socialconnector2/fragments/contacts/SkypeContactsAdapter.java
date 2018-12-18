package cl.josemanuel.socialconnector2.fragments.contacts;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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

        if (contacts.get(position).getAvatar().getBitmap() == null) {
            if (photoService.getBitmap(contacts.get(position).getAvatar()) != null)
                contacts.get(position).getAvatar().setBitmap(photoService.getBitmap(contacts.get(position).getAvatar()));
            else {
                contacts.get(position).getAvatar().setBitmap(
                        drawableToBitmap(context.getDrawable(R.drawable.ic_account_box)));
            }
        }

        ((ImageView) view.findViewById(R.id.contact_avatar)).setImageBitmap(contacts.get(position).getAvatar().getBitmap());
        return view;
    }

    private void setClickListener(View view, final ContactEntity contactEntity) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "skype:" + contactEntity.getSkype() + "?call&amp;video=true";
                Uri skypeUri = Uri.parse(uri);
                Intent myIntent = new Intent(Intent.ACTION_VIEW, skypeUri);

                myIntent.setComponent(new ComponentName("com.skype.raider", "com.skype.raider.Main"));
                context.startActivity(myIntent);
            }
        });
    }

    private Bitmap drawableToBitmap (Drawable drawable) {
        Bitmap bitmap;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }
}
