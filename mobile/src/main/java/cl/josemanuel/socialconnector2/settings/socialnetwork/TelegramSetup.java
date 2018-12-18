package cl.josemanuel.socialconnector2.settings.socialnetwork;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import cl.josemanuel.socialconnector2.R;
import cl.josemanuel.socialconnector2.dialogs.Loading;
import cl.josemanuel.socialconnector2.services.CredentialsService;
import cl.josemanuel.socialconnector2.services.TelegramService;
import cl.josemanuel.socialconnector2.services.clients.TelegramCodeClient;

/**
 * Created by Vincent on 18-03-2018.
 */

public class TelegramSetup extends SocialNetworkSetup implements TelegramCodeClient {

    Loading loading;
    Activity activity;
    String code;

    public TelegramSetup(String name, Activity activity){
        super("telegram", name);

        this.activity = activity;
        loading = new Loading(activity);

        setRequireText(false);
        setListener((View view, String pass) -> {
            TelegramSetup setup = this;
            try {
                String result = CredentialsService.configure(this.getId(), pass);
                this.setConnected(true);
            } catch (Exception e) {
                LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                final View dialogView = inflater.inflate(R.layout.settings_code_dialog, null);
                        // Inflate and set the layout for the dialog
                // Pass null as the parent view because its going in the dialog layout
                Resources res = view.getResources();
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setView(dialogView);

                builder
                        .setTitle(R.string.telegram_access_code)
                        .setMessage(R.string.telegram_new_code)
                        .setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final EditText code = (EditText) dialogView.findViewById(R.id.settings_code);
                                TelegramSetup.this.code = code.getText().toString();

                                loading.showLoadingDialog("Cargando codigo");
                                (new TelegramService(activity, code.getText().toString(), TelegramSetup.this, 1)).execute();
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
            return null;
        });
    }

    @Override
    public void onTelegramCodeSended() {
        this.loading.hideLoadingDialog();
        this.setConnected(true);
    }

    @Override
    public void onErrorSendTelegramCode() {
        this.loading.hideLoadingDialog();
    }

    @Override
    public void onTelegramCheckSended() {
        (new TelegramService(activity, code, TelegramSetup.this, 2)).execute();
    }
}
