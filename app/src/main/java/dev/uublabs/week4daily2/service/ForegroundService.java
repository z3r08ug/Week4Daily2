package dev.uublabs.week4daily2.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import dev.uublabs.week4daily2.Constants;
import dev.uublabs.week4daily2.MainActivity;
import dev.uublabs.week4daily2.R;

public class ForegroundService extends Service
{
    public static final String TAG = ForegroundService.class.getSimpleName() + "_TAG";
    MediaPlayer mediaPlayer;

    public ForegroundService()
    {
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.livinonaprayer);
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        String action = intent.getAction();
        switch (action)
        {
            case Constants.ACTION.STARTFOREGROUND_ACTION:
                Log.d(TAG, "Received Start Foreground Intent ");

                Intent notificationIntent = new Intent(this, MainActivity.class);
                notificationIntent.setAction(Constants.ACTION.MAIN_ACTION);
                notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                        notificationIntent, 0);

                Intent pauseIntent = new Intent(this, ForegroundService.class);
                pauseIntent.setAction(Constants.ACTION.PAUSE_ACTION);
                PendingIntent pendingPauseIntent = PendingIntent.getService(this, 0,
                        pauseIntent, 0);

                Intent playIntent = new Intent(this, ForegroundService.class);
                playIntent.setAction(Constants.ACTION.PLAY_ACTION);
                PendingIntent pendingPlayIntent = PendingIntent.getService(this, 0,
                        playIntent, 0);

                Intent stopIntent = new Intent(this, ForegroundService.class);
                stopIntent.setAction(Constants.ACTION.STOP_ACTION);
                PendingIntent pendingStopIntent = PendingIntent.getService(this, 0,
                        stopIntent, 0);

                Bitmap icon = BitmapFactory.decodeResource(getResources(),
                        R.drawable.ic_launcher_background);

                Notification notification = new NotificationCompat.Builder(this)
                        .setContentTitle("Music Player")
                        .setTicker("Music Player")
                        .setContentText("My Music")
                        .setSmallIcon(R.drawable.ic_launcher_background)
                       // .setLargeIcon(
                          //      Bitmap.createScaledBitmap(icon, 128, 128, false))
                        .setContentIntent(pendingIntent)
                        .setOngoing(true)
                        .addAction(android.R.drawable.ic_media_pause,
                                "Pause", pendingPauseIntent)
                        .addAction(android.R.drawable.ic_media_play, "Play",
                                pendingPlayIntent)
                        .addAction(android.R.drawable.ic_menu_close_clear_cancel, "Close",
                                pendingStopIntent).build();
                startForeground(Constants.NOTIFICATION_ID.FOREGROUND_SERVICE,
                        notification);
                mediaPlayer.start();
                break;
            case Constants.ACTION.PAUSE_ACTION:
                Log.d(TAG, "Clicked Pause");
                mediaPlayer.pause();
                break;
            case Constants.ACTION.PLAY_ACTION:
                Log.d(TAG, "Clicked Play");
                mediaPlayer.start();
                break;
            case Constants.ACTION.STOP_ACTION:
                Log.d(TAG, "Clicked Stop");
                mediaPlayer.stop();
                stopForeground(true);
                stopSelf();
                break;
            case Constants.ACTION.STOPFOREGROUND_ACTION:
                Log.d(TAG, "Received Stop Foreground Intent");
                stopForeground(true);
                stopSelf();
                break;
        }

        return START_STICKY;
    }
}

