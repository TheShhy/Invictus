package com.example.alisiyal.lab9;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Toast;

public class Services extends Service {
    private static final int NOTIFICATION_ID = 1;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    // @Override
    //public void onCreate() {
    //  super.onCreate();
    // Toast.makeText(this,"service",Toast.LENGTH_SHORT).show();
    //}

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "service Started", Toast.LENGTH_LONG).show();
        setNotificationId();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "service stop ", Toast.LENGTH_LONG).show();
        super.onDestroy();
    }

    public void setNotificationId() {
        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
        Resources r = getResources();
        Notification notification = new NotificationCompat.Builder(this)
                .setTicker(r.getString(R.string.app_name))
                .setSmallIcon(android.R.drawable.ic_menu_report_image)
                .setContentTitle(r.getString(R.string.app_name))
                .setContentText(r.getString(R.string.app_name))
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);

    }
}


