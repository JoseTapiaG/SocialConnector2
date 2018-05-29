package cl.josemanuel.socialconnector2.settings.socialnetwork;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cl.josemanuel.socialconnector2.R;
import cl.josemanuel.socialconnector2.services.SecurityService;

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
        final SocialNetworkArrayAdapter adapter = this;
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

        String savedPass = new SecurityService().getPassword(context, sn.getId());
        pass.setText(savedPass);

        //display trimmed excerpt for description
        name.setText(sn.getName());
        if (!sn.getRequireText()){
            pass.setVisibility(View.GONE);
        }
        if (sn.getConnected()) {
            pass.setVisibility(View.GONE);
            configure.setVisibility(View.GONE);
            status.setVisibility(View.VISIBLE);
        }
        //get the image associated with this property
        int imageID = context.getResources().getIdentifier(sn.getIcon(), "drawable", context.getPackageName());
        image.setImageResource(imageID);
        configure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(pass.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
                sn.listener.apply(v, pass.getText().toString());
                adapter.remove(sn);
                adapter.insert(sn, position);
            }
        });

        return view;
    }
}
