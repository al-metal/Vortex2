package com.vortex.vortex;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import com.google.gson.Gson;
import com.vortex.vortex.Model.Item;
import com.vortex.vortex.Model.RSSObject;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class GetNewNewsService extends Service {
    private MyThread mythread;
    public boolean isRunning = true;
    RSSObject rssObject;

    private final String RSSLink = "https://pk-vortex.ru/news/rss/";
    private final String RSSToJsonApi = "https://api.rss2json.com/v1/api.json?rss_url=";
    private String lastTitleNews;

    private SharedPreferences sharedPreferences;
    private static final String PREFERENCE_NAME = "News_title";
    private static final String PREFERENCE_TITLE = "News";

    public GetNewNewsService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mythread = new MyThread();
        sharedPreferences = getSharedPreferences(PREFERENCE_TITLE, MODE_PRIVATE);
        ClearPreference();
        lastTitleNews = sharedPreferences.getString(PREFERENCE_NAME, "");
    }

    private void SendNotifi(String message) {
        Intent resultIntent = new Intent(this, news_array2.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0, resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_top)
                        .setVibrate(new long[]{0, 500, 50, 1000})
                        .setContentTitle(getString(R.string.newsTitleNotification))
                        .setSound(uri)
                        .setContentText(message)
                        .setContentIntent(resultPendingIntent)
                        .setAutoCancel(true);

        Notification notification = builder.build();

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (isRunning) {
            mythread.start();
        }
        return START_STICKY;
    }

    private class MyThread extends Thread {

        @Override
        public void run() {
            while (isRunning) {
                try {
                    TimeUnit.SECONDS.sleep(30);
                    readWebPage();
                } catch (InterruptedException e) {
                    isRunning = true;
                    e.printStackTrace();
                }
            }
        }
    }

    public void readWebPage() {
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(RSSToJsonApi + RSSLink);
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        String response_str = null;
        try {
            response_str = client.execute(request, responseHandler);
            if (!response_str.equalsIgnoreCase("")) {
                rssObject = new Gson().fromJson(response_str, RSSObject.class);
                List<Item> items = rssObject.items;
                Item item = items.get(0);
                String title = item.title;

                if (!title.equals(lastTitleNews)) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(PREFERENCE_NAME, title);
                    editor.apply();

                    SendNotifi(title);
                } else {
                    NotificationManager notificationManager =
                            (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    notificationManager.cancel(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void ClearPreference() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREFERENCE_NAME, "");
        editor.apply();
    }
}
