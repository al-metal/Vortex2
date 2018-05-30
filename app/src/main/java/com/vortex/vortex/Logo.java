package com.vortex.vortex;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

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

    private TextView tv;
    private String DB_VERSION;
    private final String LOG_TAG = "---------------------- KLINING";
    private SQLiteDatabase mDb;
    private DatabaseHelper mDBHelper;
    private String pathTempFile;
    private int countTablesDB;
    private static final String DATABASE_PATH = "/data/data/com.vortex.vortex/databases/vortex.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        setTitle("");

        tv = (TextView) findViewById(R.id.tv);

        new GetDB_Version(this).execute();
    }

    public void onClick(View view) {
        Intent intent = new Intent(Logo.this, Main2Activity.class);
        startActivity(intent);
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
            }
            Intent intent = new Intent(Logo.this, Main2Activity.class);
            startActivity(intent);
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void DownloadDB(String url) {
        final ProgressDialog progressDialog = new ProgressDialog(this);

        new AsyncTask<String, Integer, File>() {
            private Exception m_error = null;

            @Override
            protected void onPreExecute() {
                tv.setText("Скачивание файлов ...");
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
                    Log.d(LOG_TAG, "ошибка скачнивания БД" + e);
                } catch (IOException e) {
                    e.printStackTrace();
                    m_error = e;
                    Log.d(LOG_TAG, "ошибка скачнивания БД" + e);
                }
                return null;
            }

            // обновляем progressDialog
            protected void onProgressUpdate(Integer... values) {
                tv.setText("Скачивание файлов: " + (int) ((values[0] / (float) values[1]) * 100));
            }

            @SuppressLint("LongLogTag")
            @Override
            protected void onPostExecute(File file) {
                // отображаем сообщение, если возникла ошибка
                if (m_error != null) {
                    m_error.printStackTrace();
                    return;
                }
                // закрываем прогресс и удаляем временный файл
                tv.setText("Скачивание файлов завершено");
                Log.d(LOG_TAG, file.getPath());
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

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            Log.d(LOG_TAG, "ошибка копирования БД" + e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            Log.d(LOG_TAG, "ошибка копирования БД" + e);
        }
    }
}
