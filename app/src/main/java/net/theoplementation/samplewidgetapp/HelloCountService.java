package net.theoplementation.samplewidgetapp;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.RemoteViews;

/**
 * Created by thaim on 17/01/14.
 */

public class HelloCountService extends Service {
    protected static final String TAG = "HelloService";
    protected static final String BUTTON_CLICK_ACTION = "BUTTON_CLICK_ACTION";
    int countClick = 0;
    int countAutoIncrement = 0;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand start with id " + startId + ": " + intent + " (" + flags + ")");

        Intent btnIntent = new Intent();
        btnIntent.setAction(BUTTON_CLICK_ACTION);
        Log.v(TAG, "create button intent: " + btnIntent);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, btnIntent, 0);
        Log.d(TAG, "prepared pendingIntent: " + pendingIntent);

        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.count_btn);
        remoteViews.setOnClickPendingIntent(R.id.btn_click, pendingIntent);
        Log.d(TAG, "set onClickPendingIntent: " + remoteViews);

        Log.v(TAG, "current intent action: " + intent.getAction());
        if (BUTTON_CLICK_ACTION.equals(intent.getAction())) {
            countClick++;

            CharSequence widgetText =
                    R.string.tag_click + countClick
                            + " " + R.string.tag_inc + countAutoIncrement;

            remoteViews.setTextViewText(R.id.txt_countview, widgetText);
        }

        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind start");

        return null;
    }
}
