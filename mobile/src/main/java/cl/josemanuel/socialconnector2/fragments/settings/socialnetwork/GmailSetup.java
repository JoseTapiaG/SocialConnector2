package cl.josemanuel.socialconnector2.fragments.settings.socialnetwork;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import cl.josemanuel.socialconnector2.R;
import cl.josemanuel.socialconnector2.services.SocialNetworkService;

/**
 * Created by Vincent on 18-03-2018.
 */

public class GmailSetup extends SocialNetworkSetup {

    public GmailSetup(String name){
        super("gmail", name);
        setRequireText(false);
        setListener((View view, String pass) -> {
            try {
                String url = SocialNetworkService.configure(this.getId(), pass);
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                view.getContext().startActivity(i);
            } catch (Exception e) {
                String msg = "Error conectando al servidor. Intente de nuevo más tarde.";
                Toast toast = Toast.makeText(view.getContext(), msg, Toast.LENGTH_LONG);
                toast.show();
            }
            return null;
        });
    }
}
