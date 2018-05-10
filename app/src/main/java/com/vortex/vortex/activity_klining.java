package com.vortex.vortex;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

public class activity_klining extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    int id = 0;
    String DB_VERSION;
    final String LOG_TAG = "myLogs";
    private SQLiteDatabase mDb;
    private DatabaseHelper mDBHelper;
    private String pathTempFile;
    int countTablesDB;
    public static final String DATABASE_PATH = "/data/data/com.vortex.vortex/databases/vortex.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_klining);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Сфера деятельности");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        new GetDB_Version(this).execute();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Intent intent = ClickLeftMenu.getIntent(activity_klining.this, item);
        startActivity(intent);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onClick(View view) {
        Intent intent = new Intent(activity_klining.this, activity_klining_poverhnost.class);
        startActivity(intent);
    }

    public void onClick1(View view) {
        Toast.makeText(getBaseContext(), "Расчеты не выполнены", Toast.LENGTH_SHORT).show();
    }

    public void onClickKuhnya(View view) {
        id = 1;
        SetIdKlining(id, "Чистая кухня");
    }

    public void onClickSanusel(View view) {
        id = 2;
        SetIdKlining(id, "Чистый санузел");
    }

    public void onClickOffice(View view) {
        id = 3;
        SetIdKlining(id, "Офисные и жилые помещения");
    }

    public void onClickObshKlining(View view) {
        id = 4;
        SetIdKlining(id, "Общий клининг");
    }

    public void onClickRemont(View view) {
        id = 5;
        SetIdKlining(id, "Ремонт и послестрой");
    }

    public void onClickPromKlining(View view) {
        id = 6;
        SetIdKlining(id, "Промышленный клининг");
    }

    private void SetIdKlining(int id, String title) {
        Intent intent = new Intent(activity_klining.this, activity_klining_poverhnost.class);
        intent.putExtra("id", id);
        intent.putExtra("title", title);
        startActivity(intent);
    }

    public class GetDB_Version extends AsyncTask<Void, Void, String> {

        Context mContext;

        public GetDB_Version(Context context) {
            mContext = context;
        }

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
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
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
                    Log.d(LOG_TAG, "--- Ошибка " + e + " ----");
                }

                return sb.toString();

            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }
        }

        protected void onPostExecute(String result) {

            if (countTablesDB < 3) {
                Log.d(LOG_TAG, "--- Таблиц меньше 3 ----");
                DownloadDB("https://pk-vortex.ru/mobail-files/db/db/vortex.db");
            } else {
                Log.d(LOG_TAG, "--- Таблиц больше 3 и равно " + countTablesDB + " ----");
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void DownloadDB(String url) {
        final ProgressDialog progressDialog = new ProgressDialog(this);

        new AsyncTask<String, Integer, File>() {
            private Exception m_error = null;

            @Override
            protected void onPreExecute() {
                progressDialog.setMessage("Скачивание файлов ...");
                progressDialog.setCancelable(false);
                progressDialog.setMax(100);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.show();
            }

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
                } catch (IOException e) {
                    e.printStackTrace();
                    m_error = e;
                }
                return null;
            }

            // обновляем progressDialog
            protected void onProgressUpdate(Integer... values) {
                progressDialog
                        .setProgress((int) ((values[0] / (float) values[1]) * 100));
            }

            @Override
            protected void onPostExecute(File file) {
                // отображаем сообщение, если возникла ошибка
                if (m_error != null) {
                    m_error.printStackTrace();
                    return;
                }
                // закрываем прогресс и удаляем временный файл
                progressDialog.hide();
                Log.d(LOG_TAG, file.getPath());
                //file.delete();
            }
        }.execute(url);
    }

    private void CopyDB() {
        File file = new File(pathTempFile);
        Log.d(LOG_TAG, file.getPath());
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
            Log.d(LOG_TAG, " записан файл");
            file.delete();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
