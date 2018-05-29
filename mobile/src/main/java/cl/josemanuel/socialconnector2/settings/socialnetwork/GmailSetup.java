package cl.josemanuel.socialconnector2.settings.socialnetwork;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;

import cl.josemanuel.socialconnector2.R;
import cl.josemanuel.socialconnector2.services.CredentialsService;

/**
 * Created by Vincent on 18-03-2018.
 */

public class GmailSetup extends SocialNetworkSetup {

    public GmailSetup(String name){
        super("gmail", name);
        setRequireText(false);
        setListener((View view, String pass) -> {
            try {
                String url = CredentialsService.configure(this.getId(), pass);
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                view.getContext().startActivity(i);
            } catch (Exception e) {
                Resources res = view.getResources();
                String msg = res.getString(R.string.error_connecting_server);
                Toast toast = Toast.makeText(view.getContext(), msg, Toast.LENGTH_LONG);
                toast.show();
            }
            return null;
        });
    }
}
