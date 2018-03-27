package cl.josemanuel.socialconnector2.alarms;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import cl.josemanuel.socialconnector2.services.SecurityService;
import cl.josemanuel.socialconnector2.services.SocialNetworkService;

/**
 * Created by farodrig on 26-03-18.
 */

public abstract class SetupReceiver extends BroadcastReceiver {

    String id;

    @Override
    public void onReceive(Context context, Intent intent) {
        String pass = new SecurityService().getPassword(context, id);
        if (pass == null){
            Log.e("SOCIAL_SETUP", "Password no existe en la base de datos");
            return;
        }
        try {
            SocialNetworkService.configure(id, pass);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("SOCIAL_SETUP", "No se pudo reconfigurar " + id +" con social translator");
        }
    }

    public boolean setReceiver(Context context, Class cls){
        Intent alarmIntent = new Intent(context, cls);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);

        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        try {
            manager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                    System.currentTimeMillis(),
                    AlarmManager.INTERVAL_HALF_DAY,
                    pendingIntent);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public abstract boolean setReceiver(Context context);

}
