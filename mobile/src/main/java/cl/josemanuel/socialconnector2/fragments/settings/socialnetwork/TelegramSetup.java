package cl.josemanuel.socialconnector2.fragments.settings.socialnetwork;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import cl.josemanuel.socialconnector2.R;
import cl.josemanuel.socialconnector2.services.SocialNetworkService;

/**
 * Created by Vincent on 18-03-2018.
 */

public class TelegramSetup extends SocialNetworkSetup {

    public TelegramSetup(String name){
        super("telegram", name);
        setRequireText(false);
        setListener((View view, String pass) -> {
            TelegramSetup setup = this;
            try {
                String result = SocialNetworkService.configure(this.getId(), pass);
                this.setConnected(true);
            } catch (Exception e) {
                LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                final View dialogView = inflater.inflate(R.layout.settings_code_dialog, null);
                        // Inflate and set the layout for the dialog
                // Pass null as the parent view because its going in the dialog layout
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setView(dialogView);

                builder
                        .setTitle("Ingrese código de acceso")
                        .setMessage("Ingrese nuevo código enviado a su aplicación de Telegram.")
                        .setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final EditText code = (EditText) dialogView.findViewById(R.id.settings_code);
                                Toast toast = Toast.makeText(view.getContext(), code.getText(), Toast.LENGTH_SHORT);
                                toast.show();
                                setup.getListener().apply(view, code.getText().toString());
                                setup.setConnected(true);
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
}
