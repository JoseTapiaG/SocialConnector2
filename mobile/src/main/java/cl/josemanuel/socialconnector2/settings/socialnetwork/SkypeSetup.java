package cl.josemanuel.socialconnector2.settings.socialnetwork;

import android.content.res.Resources;
import android.view.View;
import android.widget.Toast;

import cl.josemanuel.socialconnector2.R;
import cl.josemanuel.socialconnector2.services.CredentialsService;
import cl.josemanuel.socialconnector2.services.SecurityService;

/**
 * Created by farodrig on 19-03-18.
 */

public class SkypeSetup extends SocialNetworkSetup {

    public SkypeSetup(String name){
        super("skype", name);
        setListener((View view, String pass) -> {
            SkypeSetup setup = this;
            Resources res = view.getResources();
            try {
                CredentialsService.configure(this.getId(), pass);
                boolean saved = new SecurityService().savePassword(view.getContext(), this.getId(),pass);
                if (!saved) {
                    String msg = res.getString(R.string.cant_save_password);
                    Toast toast = Toast.makeText(view.getContext(), msg, Toast.LENGTH_SHORT);
                    toast.show();
                }
                setConnected(true);
            } catch (Exception e) {
                e.printStackTrace();
                String msg = res.getString(R.string.error_connecting_server_or_pass);
                Toast toast = Toast.makeText(view.getContext(), msg, Toast.LENGTH_LONG);
                toast.show();
            }
            return null;
        });

    }
}
