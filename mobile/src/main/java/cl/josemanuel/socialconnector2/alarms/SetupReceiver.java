package cl.josemanuel.socialconnector2.alarms;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;

import java.util.Date;

import cl.josemanuel.socialconnector2.R;
import cl.josemanuel.socialconnector2.services.SecurityService;
import cl.josemanuel.socialconnector2.services.SocialNetworkService;

/**
 * Created by farodrig on 26-03-18.
 */

public abstract class SetupReceiver extends BroadcastReceiver {

    long interval = AlarmManager.INTERVAL_HALF_DAY;
    long shortInterval = AlarmManager.INTERVAL_HOUR;
    int shortAlarmID = 652975;

    @Override
    public void onReceive(Context context, Intent intent) {
        Resources res = context.getResources();
        String tag = res.getString(R.string.setup_alarm_tag);
        String id = getId();
        String pass = new SecurityService().getPassword(context, id);
        if (pass == null){
            Log.e(tag, res.getString(R.string.not_password_in_db));
            return;
        }
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, shortAlarmID, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        try {
            SocialNetworkService.configure(id, pass);
            Log.i(tag, res.getString(R.string.delete_short_alarm));
            manager.cancel(alarmIntent);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(tag, String.format(res.getString(R.string.reconfigure_error), id));
            Log.e(tag, res.getString(R.string.create_short_alarm));
            manager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                    System.currentTimeMillis(),
                    shortInterval,
                    alarmIntent);
        }
    }

    public boolean setReceiver(Context context, Class cls){
        Intent alarmIntent = new Intent(context, cls);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        try {
            manager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                    System.currentTimeMillis(),
                    interval,
                    pendingIntent);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public abstract boolean setReceiver(Context context);
    public abstract String getId();

}
