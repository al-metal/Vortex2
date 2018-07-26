package com.vortex.vortex;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.concurrent.ExecutionException;

public class Logo extends AppCompatActivity {

    public static final String APP_PREFERENCES = "versionDB";
    public static final String APP_PREFERENCES_COUNTER = "counter";
    public static SharedPreferences mSettings;

    private String DB_VERSION;
    private final String LOG_TAG = " Logo TAG";
    private SQLiteDatabase mDb;
    private DatabaseHelper mDBHelper;
    private String pathTempFile;
    private int countTablesDB;
    private static final String DATABASE_PATH = "/data/data/com.vortex.vortex/databases/vortex.db";
    private boolean err;

    private ProgressBar pbLoadDB;
    private TextView tvProgressBarText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        setTitle("");

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        tvProgressBarText = (TextView) findViewById(R.id.tvProgressBarText);
        pbLoadDB = (ProgressBar) findViewById(R.id.pbLoadDB);

        if (mSettings.contains(APP_PREFERENCES_COUNTER)) {
            // Получаем число из настроек
            DB_VERSION = mSettings.getString(APP_PREFERENCES_COUNTER, "");
        }
        err = false;
        tvProgressBarText.setVisibility(View.INVISIBLE);
        pbLoadDB.setVisibility(View.INVISIBLE);

        startService(new Intent(this, GetNewNewsService.class));

        new GetDB_Version(this).execute();
    }

    public class GetDB_Version extends AsyncTask<Void, Void, String> {

        Context mContext;

        public GetDB_Version(Context context) {
            mContext = context;
        }

        @SuppressLint("LongLogTag")
        @Override
        protected String doInBackground(Void... voids) {
            try {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String link = "https://pk-vortex.ru/mobail-files/db/getVersionDB.php";
                String data = URLEncoder.encode("id", "UTF-8");
                URL url = new URL(link);
                URLConnection conn = url.openConnection();
                conn.setDoOutput(true);

                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                wr.write(data);
                wr.flush();

                BufferedReader reader = new BufferedReader(new
                        InputStreamReader(conn.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = null;

                //Read Server Response
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                    break;
                }

                DB_VERSION = sb.toString();

                //записываем версию БД в память приложения
                SharedPreferences.Editor editor = mSettings.edit();
                editor.putString(APP_PREFERENCES_COUNTER, DB_VERSION);
                editor.apply();

                Log.d(LOG_TAG, "--- Версия БД: " + DB_VERSION + " ----");

                try {
                    mDBHelper = new DatabaseHelper(mContext, DB_VERSION);
                } catch (ExecutionException e) {
                    Log.d(LOG_TAG, "ошибка получения версии БД" + e);
                } catch (InterruptedException e) {
                    Log.d(LOG_TAG, "ошибка получения версии БД" + e);
                }

                mDBHelper.updateDataBase();

                try {
                    mDb = mDBHelper.getWritableDatabase();//.getReadableDatabase();
                } catch (SQLException mSQLException) {
                    throw mSQLException;
                }

                //Проверка на пустую БД
                countTablesDB = 0;

                try {
                    Log.d(LOG_TAG, "--- Получение количество таблиц в БД  в текуущем потоке" + " ----");
                    Cursor cursor = mDb.rawQuery("SELECT * FROM sqlite_master", null);
                    cursor.moveToFirst();
                    countTablesDB = cursor.getCount();
                } catch (Exception e) {
                    Log.d(LOG_TAG, "ошибка получения количества таблиц в БД" + e);
                }

                return sb.toString();

            } catch (Exception e) {
                Log.d(LOG_TAG, "ошибка " + e);
                return new String("Exception: " + e.getMessage());
            }
        }

        @SuppressLint("LongLogTag")
        protected void onPostExecute(String result) {

            if (countTablesDB < 3) {
                Log.d(LOG_TAG, "--- Таблиц меньше 3 ----");
                DownloadDB("https://pk-vortex.ru/mobail-files/db/db/vortex.db");

            } else {
                Log.d(LOG_TAG, "--- Таблиц больше 3 и равно " + countTablesDB + " ----");
                Intent intent = new Intent(Logo.this, Main2Activity.class);
                startActivity(intent);
            }
            Log.d(LOG_TAG, "--- Проверка на ошибку ----");
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void DownloadDB(String url) {

        new AsyncTask<String, Integer, File>() {
            private Exception m_error = null;

            @Override
            protected void onPreExecute() {
                tvProgressBarText.setVisibility(View.VISIBLE);
                pbLoadDB.setVisibility(View.VISIBLE);
            }

            @SuppressLint("LongLogTag")
            @Override
            protected File doInBackground(String... params) {
                URL url;
                HttpURLConnection urlConnection;
                InputStream inputStream;
                int totalSize;
                int downloadedSize;
                byte[] buffer;
                int bufferLength;

                File file = null;
                FileOutputStream fos = null;

                try {
                    url = new URL(params[0]);
                    urlConnection = (HttpURLConnection) url.openConnection();

                    urlConnection.setRequestMethod("GET");
                    urlConnection.setDoOutput(true);
                    urlConnection.connect();

                    file = File.createTempFile("yml.", "db");
                    pathTempFile = file.getPath();
                    fos = new FileOutputStream(file);
                    inputStream = urlConnection.getInputStream();

                    totalSize = urlConnection.getContentLength();
                    downloadedSize = 0;

                    buffer = new byte[1024];
                    bufferLength = 0;

                    // читаем со входа и пишем в выход,
                    // с каждой итерацией публикуем прогресс
                    while ((bufferLength = inputStream.read(buffer)) > 0) {
                        Log.d(LOG_TAG, "--- размер = " + bufferLength + " ----");
                        fos.write(buffer, 0, bufferLength);
                        downloadedSize += bufferLength;
                        publishProgress(downloadedSize, totalSize);
                        Log.d(LOG_TAG, "--- размер = " + downloadedSize + " ----");
                    }

                    fos.close();
                    inputStream.close();
                    Log.d(LOG_TAG, "---  Загрузка завершена ---");
                    CopyDB();
                    return file;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    m_error = e;
                    err = true;
                    Log.d(LOG_TAG, "ошибка скачнивания БД" + e);
                } catch (IOException e) {
                    e.printStackTrace();
                    m_error = e;
                    err = true;
                    Log.d(LOG_TAG, "ошибка скачнивания БД" + e);
                }
                return null;
            }

            @SuppressLint({"LongLogTag", "WrongConstant"})
            @Override
            protected void onPostExecute(File file) {
                pbLoadDB.setVisibility(View.INVISIBLE);
                tvProgressBarText.setVisibility(View.INVISIBLE);
                if (err) {
                    Log.d(LOG_TAG, "--- Ошибка есть ----");
                    AlertDialog.Builder builder = new AlertDialog.Builder(Logo.this);
                    builder.setTitle("Ошибка")
                            .setMessage(R.string.noneINTERNET)
                            //.setIcon(R.drawable.ic_android_cat)
                            .setCancelable(false)
                            .setNegativeButton("ОК",
                                    (dialog, id) -> {
                                        //dialog.cancel();
                                        Intent intent = new Intent(Logo.this, Main2Activity.class);
                                        startActivity(intent);
                                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                    Log.d(LOG_TAG, "--- Диалог показан ----");
                } else {
                    // отображаем сообщение, если возникла ошибка
                    if (m_error != null) {
                        m_error.printStackTrace();
                        return;
                    }
                    Log.d(LOG_TAG, file.getPath());
                    Intent intent = new Intent(Logo.this, Main2Activity.class);
                    startActivity(intent);
                }
            }
        }.execute(url);
    }

    @SuppressLint("LongLogTag")
    private void CopyDB() {
        File file = new File(pathTempFile);
        Log.d(LOG_TAG, file.getPath());
        File filename = new File(DATABASE_PATH);

        Log.d(LOG_TAG, "Файл для Чтения " + pathTempFile);
        Log.d(LOG_TAG, "Файл для Записи " + DATABASE_PATH);
        filename.delete();

        int size = (int) file.length();
        byte[] bytes = new byte[size];
        try {
            InputStream buf = new BufferedInputStream(new FileInputStream(file));
            buf.read(bytes, 0, bytes.length);

            OutputStream myOutput = new FileOutputStream(filename);
            myOutput.write(bytes);
            myOutput.close();
            buf.close();
            Log.d(LOG_TAG, " записан файл");
            file.delete();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            Log.d(LOG_TAG, "ошибка копирования БД" + e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            Log.d(LOG_TAG, "ошибка копирования БД" + e);
        }
    }
}
