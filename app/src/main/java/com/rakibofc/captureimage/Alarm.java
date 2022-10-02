package com.rakibofc.captureimage;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class Alarm extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String extra = intent.getStringExtra("extra");
        Toast.makeText(context, extra, Toast.LENGTH_SHORT).show();

        addNotification(context, "Notification");

        // pushNotification(context, "Prayer Time Alert", "contentTitle", "contentText", 11111);
    }

    private void addNotification(Context context, String channelId) {

        NotificationChannel notificationChannel;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notificationChannel = new NotificationChannel(
                    channelId,
                    channelId,
                    NotificationManager.IMPORTANCE_HIGH // IMPORTANCE_LOW
            );
            notificationChannel.setShowBadge(true);
            context.getSystemService(NotificationManager.class).createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context, channelId)
                        .setSmallIcon(R.mipmap.ic_launcher) //set icon for notification
                        .setContentTitle("Notifications Example") //set title of notification
                        .setContentText("This is a notification message")//this is notification message
                        .setAutoCancel(true) // makes auto cancel of notification
                        .setPriority(NotificationCompat.PRIORITY_HIGH); //set priority of notification


        Intent notificationIntent = new Intent(context, MainActivity.class);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //notification message will get at NotificationView
        notificationIntent.putExtra("message", "This is a notification message");

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        builder.setDefaults(Notification.DEFAULT_SOUND);
        builder.setContentIntent(pendingIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }

    public Notification pushNotification(Context context, String channelId, String title, String message, int requestCode) {

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(
                    channelId,
                    channelId,
                    NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription("This is prayer reminder notification");
            mNotificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        Intent intent = new Intent(context, MainActivity.class);
        @SuppressLint("InlinedApi") PendingIntent pendingIntent = PendingIntent.getActivity(context, requestCode, intent, PendingIntent.FLAG_IMMUTABLE);
        mBuilder.setContentIntent(pendingIntent);
        mNotificationManager.notify(requestCode, mBuilder.build());

        return mBuilder.build();
    }
}