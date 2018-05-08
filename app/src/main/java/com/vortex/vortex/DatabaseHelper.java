package com.vortex.vortex;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import rx.schedulers.Schedulers;

/**
 * Created by KonyshevAM on 16.03.2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String LOG = "log";
    private static String DB_NAME = "vortex.db";
    private static String DB_PATH = "";
    public static int DB_VERSION;
    public static final String DATABASE_PATH = "/data/data/com.vortex.vortex/databases/vortex.db";
    final String CREATE_TABLE = "CREATE TABLE `table` (`id` INTEGER);";
    private SQLiteDatabase mDataBase;
    private final Context mContext;
    private boolean mNeedUpdate = false;
    final String LOG_TAG = "myLogs";
    String pathTempFile = "";
    int DB_VERSION_OLD;
    private static final String TAG = "LOOOOOOOOOOOG";
    rx.Observer<Integer> observer;
    rx.Observable<Integer> observable;

    public DatabaseHelper(Context context, String DBVERSION) throws ExecutionException, InterruptedException {

        super(context, DB_NAME, null, Integer.parseInt(DBVERSION));

        if (Build.VERSION.SDK_INT >= 17)
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        else
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";

        this.mContext = context;
        this.DB_VERSION = Integer.parseInt(DBVERSION);

        this.getReadableDatabase();
    }

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        if (android.os.Build.VERSION.SDK_INT >= 17)
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        else
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        this.mContext = context;

        this.getReadableDatabase();
    }

    public void updateDataBase() {
        if (mNeedUpdate) {

            Log.d(LOG_TAG, "--- Начинаем обновление БД ----");
            File dbFile = new File(DB_PATH + DB_NAME);
            if (dbFile.exists()) {
                dbFile.delete();
                Log.d(LOG_TAG, "--- Старая БД удалена ----");
            }

            NewDB();

            mNeedUpdate = false;
        }
    }

    private void NewDB() {
        // create onSubscribe
        rx.Observable.OnSubscribe<Integer> DownloadDB = subscriber -> {
            Download();
            if (subscriber.isUnsubscribed()) {
                return;
            }
            subscriber.onCompleted();
        };

        // create onSubscribe
        rx.Observable.OnSubscribe<Integer> CopyDB = subscriber -> {
            Download();
            CopyDB();
            if (subscriber.isUnsubscribed()) {
                return;
            }
            subscriber.onCompleted();
        };

        // create observable
        observable = rx.Observable
                .create(DownloadDB)
                .subscribeOn(Schedulers.io());

        // create observer
        observer = new rx.Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onNext(Integer i) {
                Log.d(TAG, "onNext: " + i);
            }
        };

        observable.subscribe(observer);

        Log.d(LOG_TAG, "--- БД скопированна ----");
    }

    @Override
    public synchronized void close() {
        if (mDataBase != null)
            mDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            Log.d(LOG_TAG, "--- Версии БД разные ----");
            mNeedUpdate = true;
        }
    }

    public void Download() {
        URL url;
        HttpURLConnection urlConnection;
        InputStream inputStream;
        int downloadedSize;
        byte[] buffer;
        int bufferLength;

        File file;
        FileOutputStream fos;

        try {
            url = new URL("https://pk-vortex.ru/mobail-files/db/db/vortex.db");
            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);
            urlConnection.connect();

            file = File.createTempFile("yml.", "db");

            fos = new FileOutputStream(file);
            inputStream = urlConnection.getInputStream();
            pathTempFile = file.getPath();
            downloadedSize = 0;

            buffer = new byte[1024];
            bufferLength = 0;

            Log.d(TAG, "--- Начинаем загрузку файла ----");
            // читаем со входа и пишем в выход,
            // с каждой итерацией публикуем прогресс
            while ((bufferLength = inputStream.read(buffer)) > 0) {

                fos.write(buffer, 0, bufferLength);
                downloadedSize += bufferLength;
            }

            fos.close();
            inputStream.close();
            Log.d(TAG, "---  Загрузка завершена ---");
            CopyDB();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void CopyDB() {
        Log.d(TAG, "--- Начинаем Копирование файла ----");
        File file = new File(pathTempFile);
        Log.d(TAG, file.getPath());
        File folder = new File(DATABASE_PATH);
        if (!folder.exists()) {
            folder.mkdir();
        }
        String filename = DATABASE_PATH;

        int size = (int) file.length();
        byte[] bytes = new byte[size];
        try {
            InputStream buf = new BufferedInputStream(new FileInputStream(file));
            buf.read(bytes, 0, bytes.length);

            OutputStream myOutput = new FileOutputStream(filename);
            myOutput.write(bytes);
            myOutput.close();
            buf.close();
            Log.d(TAG, " записан файл");
            file.delete();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Log.d(TAG, "--- Закончили Копирование файла ----");
    }
}
