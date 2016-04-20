package com.example.deeplinking;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

// http://developer.android.com/training/app-indexing/deep-linking.html
// adb shell pm list packages -f | grep com.example.deeplink
// adb shell am start  -n "com.codexpedia.deeplinking/com.codexpedia.deeplinking.MainActivity" -a android.intent.action.MAIN -c android.intent.category.LAUNCHER
//  $ adb shell am start -W -a android.intent.action.VIEW -d "example://mainactivity" com.example.deeplinking
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnOne = (Button) findViewById(R.id.btn_one);

        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = "Activity One";
                Intent intent = new Intent(getApplicationContext(), ActivityOne.class);
                sendNotificationMessage(intent, msg);
            }
        });
    }



    public void sendNotificationMessage(Intent intent, String subTitle) {
        // NotificationCompatBuilder is a very convenient way to build backward-compatible notifications.  Just throw in some data.
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(getApplicationContext())
                        .setColor(getResources().getColor(android.R.color.white))
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Deep Linking")
                        .setContentText(subTitle);

        // The stack builder object will contain an artificial back stack for the started Activity.
        // This ensures that navigating backward from the Activity leads out of your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
        stackBuilder.addNextIntent(intent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(123, mBuilder.build()); // ID 123 allows you to update the notification later on.
    }
}