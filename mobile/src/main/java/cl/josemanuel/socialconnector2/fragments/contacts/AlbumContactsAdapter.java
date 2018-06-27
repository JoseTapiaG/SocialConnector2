package cl.josemanuel.socialconnector2.fragments.contacts;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.util.DisplayMetrics;
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

    private Bitmap drawableToBitmap (Drawable drawable) {
        Bitmap bitmap = null;

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
