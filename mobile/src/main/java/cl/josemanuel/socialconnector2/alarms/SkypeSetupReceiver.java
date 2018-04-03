package cl.josemanuel.socialconnector2.alarms;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

import cl.josemanuel.socialconnector2.services.SecurityService;
import cl.josemanuel.socialconnector2.services.SocialNetworkService;

public class SkypeSetupReceiver extends SetupReceiver {

    String id = "skype";

    public boolean setReceiver(Context context){
        return setReceiver(context, SkypeSetupReceiver.class);
    }

    @Override
    public String getId() {
        return this.id;
    }
}
