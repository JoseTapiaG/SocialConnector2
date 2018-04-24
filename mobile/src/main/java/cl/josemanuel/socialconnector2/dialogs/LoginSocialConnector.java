package cl.josemanuel.socialconnector2.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.widget.EditText;

import cl.josemanuel.socialconnector2.R;
import cl.josemanuel.socialconnector2.services.clients.ContactServiceClient;
import cl.josemanuel.socialconnector2.services.LoginSocialConnectorService;
import cl.josemanuel.socialconnector2.services.clients.LoginServiceClient;

/**
 * Created by JoseManuel on 07-01-2018.
 */

public class LoginSocialConnector {

    Activity activity;
    Loading loading;

    public LoginSocialConnector(Activity activity) {
        this.activity = activity;
    }

    public void showLoginDialog(final LoginServiceClient loginServiceClient) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();

        // Add the buttons
        builder.setView(inflater.inflate(R.layout.login_social_connector, null))
                .setPositiveButton(R.string.enviar_login_sc, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String username = ((EditText) ((AlertDialog) dialog).findViewById(R.id.username)).getText().toString();
                        String password = ((EditText) ((AlertDialog) dialog).findViewById(R.id.password)).getText().toString();
                        showLoadingDialog();
                        (new LoginSocialConnectorService(activity, loading, username, password, loginServiceClient)).execute();
                    }
                })
                .setNegativeButton(R.string.cancelar_login_sc, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

        // Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showLoadingDialog(){
        loading = new Loading(activity);
        loading.showLoadingDialog("Enviando credenciales");
    }
}
