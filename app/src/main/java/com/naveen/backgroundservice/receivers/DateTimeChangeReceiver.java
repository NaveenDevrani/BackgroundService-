package com.naveen.backgroundservice.receivers;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.naveen.backgroundservice.R;
import com.naveen.backgroundservice.services.InfinityBackgroundService;
import com.naveen.backgroundservice.services.OneTimeServiceAfterDateChanged;

public class DateTimeChangeReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        Toast.makeText(context, "Date changed", Toast.LENGTH_SHORT).show();
        showSmallNotification(context,"checking Data change....","Data Change");
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            context.startForegroundService(new Intent(context, OneTimeServiceAfterDateChanged.class));
//        } else {
//            context.startService(new Intent(context, OneTimeServiceAfterDateChanged.class));
//        }
    }

    private void showSmallNotification(Context context,String title, String message) {

        NotificationCompat.Builder build;
        Notification build1;
        int notificationID = 234231;

        NotificationManager mNotifyManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        build = new NotificationCompat.Builder(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
            inboxStyle.addLine(message);

            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel mChannel = new NotificationChannel("ksjadf87", "service...", importance);
            build1 = new Notification.Builder(context)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setChannelId("ksjadf87")
                    .build();

            assert mNotifyManager != null;
            mNotifyManager.createNotificationChannel(mChannel);

// Issue the notification.
            mNotifyManager.notify(notificationID, build1);

        } else {
            NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
            inboxStyle.addLine(message);
            build.setContentTitle(title)
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setStyle(inboxStyle)
                    .setSmallIcon(R.mipmap.ic_launcher);

            assert mNotifyManager != null;
            mNotifyManager.notify(notificationID, build.build());
//        }


        }
    }


}