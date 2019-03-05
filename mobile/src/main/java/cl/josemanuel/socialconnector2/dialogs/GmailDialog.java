package cl.josemanuel.socialconnector2.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;

import cl.josemanuel.socialconnector2.R;
import cl.josemanuel.socialconnector2.services.LoginSocialConnectorService;
import cl.josemanuel.socialconnector2.services.clients.LoginServiceClient;

/**
 * Created by JoseManuel on 07-01-2018.
 */

public class GmailDialog {

    Activity activity;
    Loading loading;

    public GmailDialog(Activity activity) {
        this.activity = activity;
    }

    public void showLoginDialog(String url) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();

        View inflate = inflater.inflate(R.layout.gmail_dialog, null);

        WebView webView = (WebView) inflate.findViewById(R.id.webview);
        webView.loadUrl(url);

        // Add the buttons
        builder.setView(inflate).setNegativeButton("Cerrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
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
