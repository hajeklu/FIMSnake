package cz.uhk.fimsnake.activity.services;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import cz.uhk.fimsnake.R;
import cz.uhk.fimsnake.activity.PreferencesActivity;
import cz.uhk.fimsnake.activity.TapScore;

/**
 * Created by Luboš Hájek in 2019
 */
public class NotifiService {

    public static void createNotificationNewRecord(Context context) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (!sharedPreferences.getBoolean(PreferencesActivity.PRE_NOTIFICATION, PreferencesActivity.NOTIFICATION_DEFAULT)) {
            return;
        }

        Intent intent = new Intent(context, TapScore.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "channel")
                .setSmallIcon(R.drawable.ic_star)
                .setContentTitle("FIM Snake new record!")
                .setContentText("You have new record in FIM snake. Your new record is... ")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        notificationManager.notify(1, builder.build());

    }
}
