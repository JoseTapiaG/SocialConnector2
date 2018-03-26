package cl.josemanuel.socialconnector2.fragments.settings.socialnetwork;

import android.view.View;
import android.widget.Toast;

import java.security.KeyPair;
import java.security.KeyStoreException;

import cl.josemanuel.socialconnector2.services.SecurityService;
import cl.josemanuel.socialconnector2.services.SocialNetworkService;

/**
 * Created by farodrig on 19-03-18.
 */

public class SkypeSetup extends SocialNetworkSetup {

    public SkypeSetup(String name){
        super("skype", name);
        setListener((View view, String pass) -> {
            SkypeSetup setup = this;
            try {
                SocialNetworkService.configure(this.getId(), pass);
                boolean saved = new SecurityService().savePassword(view.getContext(), this.getId(),pass);
                if (!saved) {
                    String msg = "No se pudo guardar la clave localmente";
                    Toast toast = Toast.makeText(view.getContext(), msg, Toast.LENGTH_SHORT);
                    toast.show();
                }
                setConnected(true);
            } catch (Exception e) {
                String msg = "Error conectando al servidor o clave incorrecta. Intente de nuevo más tarde.";
                Toast toast = Toast.makeText(view.getContext(), msg, Toast.LENGTH_LONG);
                toast.show();
            }
            return null;
        });

    }
}
