package com.example.birthdayrem;


import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {


    public MyIntentService() {
        super("MyIntentService");
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onHandleIntent(Intent intent) {
        String name = intent.getStringExtra("name");
        sendNotification(name);
        Log.e("lili","service on handle");

    }
    @Override
    public void onCreate() {
        Log.e("lili","service on create");
        super.onCreate();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("lili","service on start");
        String name = intent.getStringExtra("name");
        sendNotification(name);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.e("lili","service on destroy");
        super.onDestroy();
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void sendNotification(String name){
        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setTicker("有生日到了");
        builder.setContentTitle("有人生日到了，请注意");
        builder.setContentText(name+"生日到了");
        builder.setWhen(System.currentTimeMillis());
        builder.setDefaults(Notification.DEFAULT_ALL);
        Intent intent = new Intent(this,InfosActivity.class);
        intent.putExtra("name",name);
        builder.setContentIntent(PendingIntent.getActivity(this,0,intent, PendingIntent.FLAG_UPDATE_CURRENT));
        notificationManager.notify(0,builder.build());
    }


}
