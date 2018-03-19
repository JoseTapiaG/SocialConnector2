package cl.josemanuel.socialconnector2.fragments.settings;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cl.josemanuel.socialconnector2.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by farodrig on 12-03-18.
 */

public class SocialNetworkArrayAdapter extends ArrayAdapter<SocialNetworkSetup> {

    private Context context;
    private List<SocialNetworkSetup> socialNetworks;

    public SocialNetworkArrayAdapter(Context context, int resource, ArrayList<SocialNetworkSetup> ssnn) {
        super(context, resource, ssnn);
        this.context = context;
        this.socialNetworks = ssnn;
    }

    //called when rendering the list
    public View getView(int position, View convertView, ViewGroup parent) {

        //get the property we are displaying
        final SocialNetworkSetup sn = socialNetworks.get(position);

        //get the inflater and inflate the XML layout for each item
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.settings_social_network, null);

        TextView name = (TextView) view.findViewById(R.id.sn_name);
        final EditText pass = (EditText) view.findViewById(R.id.sn_pass);
        Button configure = (Button) view.findViewById(R.id.sn_configure);
        ImageView image = (ImageView) view.findViewById(R.id.sn_icon);
        ImageView status = (ImageView) view.findViewById(R.id.sn_status);

        //display trimmed excerpt for description
        name.setText(sn.getName());
        if (sn.getConnected()) {
            pass.setVisibility(View.GONE);
            configure.setVisibility(View.GONE);
            status.setVisibility(View.VISIBLE);
        }
        //get the image associated with this property
        int imageID = context.getResources().getIdentifier(sn.getIcon(), "drawable", context.getPackageName());
        image.setImageResource(imageID);
        configure.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sn.onClick.apply(v, pass.getText().toString());
                }
            }
        );
        return view;
    }
}
