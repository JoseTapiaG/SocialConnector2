package cl.josemanuel.socialconnector2.settings.socialnetwork;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;

import cl.josemanuel.socialconnector2.R;
import cl.josemanuel.socialconnector2.dialogs.GmailDialog;
import cl.josemanuel.socialconnector2.dialogs.Loading;
import cl.josemanuel.socialconnector2.services.CredentialsService;
import cl.josemanuel.socialconnector2.services.GmailConfigureService;
import cl.josemanuel.socialconnector2.services.clients.GmailConfigureClient;
import cl.josemanuel.socialconnector2.settings.socialnetwork.SocialNetworkSetup;

/**
 * Created by Vincent on 18-03-2018.
 */

public class GmailSetup extends SocialNetworkSetup implements GmailConfigureClient{

    Loading loading;
    Activity activity;

    public GmailSetup(String name, Activity activity){
        super("gmail", name);

        this.activity = activity;

        loading = new Loading(activity);


        setRequireText(false);
        setListener((View view, String pass) -> {
            try {
                loading.showLoadingDialog("Verificando cuenta");
                (new GmailConfigureService(activity, this)).execute();
                /*String url = CredentialsService.configure(this.getId(), pass);
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                view.getContext().startActivity(i);*/
            } catch (Exception e) {
                Resources res = view.getResources();
                String msg = res.getString(R.string.error_connecting_server);
                Toast toast = Toast.makeText(view.getContext(), msg, Toast.LENGTH_LONG);
                toast.show();
            }
            return null;
        });
    }


    @Override
    public void onLoadCheckPage(String redirect) {
        loading.hideLoadingDialog();
        GmailDialog gmailDialog = new GmailDialog(activity);
        gmailDialog.showLoginDialog(redirect);


    }

    @Override
    public void onError(String response) {
        loading.hideLoadingDialog();
    }
}
