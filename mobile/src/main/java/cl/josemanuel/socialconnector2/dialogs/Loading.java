package cl.josemanuel.socialconnector2.dialogs;

import android.app.Activity;
import android.app.ProgressDialog;

/**
 * Created by JoseManuel on 07-01-2018.
 */

public class Loading {

    Activity activity;
    ProgressDialog loading;

    public Loading(Activity activity) {
        this.activity = activity;
    }

    public void showLoadingDialog(String message){
        loading = new ProgressDialog(activity);
        loading.setCancelable(true);
        loading.setMessage(message);
        loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        loading.show();
    }

    public void hideLoadingDialog() {
        loading.dismiss();
    }
}
