package com.lindseyweberc196.Activity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import com.lindseyweberc196.R;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;
import static android.content.Context.NOTIFICATION_SERVICE;

public class Receiver extends BroadcastReceiver {

    static int notificationID;
    String channel_id="test";
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,intent.getStringExtra("key"),Toast.LENGTH_LONG).show();
        createNotificationChannel(context,channel_id);

        Notification n= new NotificationCompat.Builder(context, channel_id)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentText(intent.getStringExtra("content"))
                .setContentTitle(intent.getStringExtra("title")).build();

        NotificationManager notificationManager=(NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(notificationID++,n);

    }

    private void createNotificationChannel(Context context, String CHANNEL_ID) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = context.getResources().getString(R.string.channel_name);
            String description = context.getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    //Convert date to milliseconds
    public static long dateToMillis(String date) {

        System.out.println(TimeZone.getDefault());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
        LocalDateTime localDate = LocalDateTime.parse(date + " 12:00", formatter);
        long timeInMilliseconds = localDate.atOffset(ZoneOffset.UTC).toInstant().toEpochMilli();

        return timeInMilliseconds;
    }

}
