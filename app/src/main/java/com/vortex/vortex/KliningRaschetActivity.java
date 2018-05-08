package com.vortex.vortex;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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

public class KliningRaschetActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String product;
    final String LOG_TAG = "myLogs";
    private String pathTempFile;
    String DB_VERSION;
    static final String DB_FULL_PATH = "/data/data/ru.vortex.vortex/databases/vortex.db";
    private DatabaseHelper mDBHelper;
    int countTablesDB;
    private SQLiteDatabase mDb;
    double rashodM2Marvel;
    double rashodMlMarvel;
    double massaMarvel;
    TextView tvRashodMlM2;
    TextView tvRashodMl;
    TextView tvMassa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_klining_raschet);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        tvRashodMlM2 = (TextView) findViewById(R.id.tvRashodMlM2);
        tvRashodMl = (TextView) findViewById(R.id.tvRashodMl);
        tvMassa = (TextView) findViewById(R.id.tvMassa);

        tvRashodMlM2.setText("0");
        tvRashodMl.setText("0");
        tvMassa.setText("0");

        product = getIntent().getStringExtra("nameProduct");
        setTitle(product);
        product = product.toUpperCase();

        new Load_data(this).execute();
    }

    public void onClickRaschet(View view) {
    }

    public class Load_data extends AsyncTask<Void, Void, String> {

        Context mContext;

        public Load_data(Context context) {
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

                mDBHelper = new DatabaseHelper(mContext);

                //mDBHelper.updateDataBase();

                try {
                    mDb = mDBHelper.getWritableDatabase();//.getReadableDatabase();
                } catch (SQLException mSQLException) {
                    throw mSQLException;
                }

                //Проверка на пустую БД
                countTablesDB = 0;

                try {
                    Log.d(LOG_TAG, "--- Получение количество таблиц в БД  в текуущем потоке" + " ----");
                    Cursor c = mDb.query("cleanbox", null, null, null, null, null, null);

                    // ставим позицию курсора на первую строку выборки
                    // если в выборке нет строк, вернется false
                    if (c.moveToFirst()) {

                        // определяем номера столбцов по имени в выборке
                        int idColIndex = c.getColumnIndex("id");
                        int nameColIndex = c.getColumnIndex("name");
                        int masscontainerColIndex = c.getColumnIndex("masscontainer");
                        int consumptionmlColIndex = c.getColumnIndex("consumptionml");
                        int consumptionmlm2ColIndex = c.getColumnIndex("consumptionmlm2");

                        do {
                            String product_db = c.getString(nameColIndex);
                            if (product.equals(product_db)) {

                                massaMarvel = Double.parseDouble(c.getString(masscontainerColIndex));
                                rashodMlMarvel = Double.parseDouble(c.getString(consumptionmlColIndex));
                                rashodM2Marvel = Double.parseDouble(c.getString(consumptionmlm2ColIndex));

                                tvRashodMlM2.setText(String.valueOf(rashodM2Marvel));
                                tvRashodMl.setText(String.valueOf(rashodMlMarvel));
                                tvMassa.setText(String.valueOf(massaMarvel));
                                // получаем значения по номерам столбцов и пишем все в лог
                                Log.d(LOG_TAG,
                                        "ID = " + c.getInt(idColIndex) +
                                                ", name = " + product_db +
                                                ", masscontainer = " + c.getString(masscontainerColIndex) +
                                                ", consumptionml = " + c.getString(consumptionmlColIndex) +
                                                ", consumptionmlm2 = " + c.getString(consumptionmlm2ColIndex));
                            }
                            // переход на следующую строку
                            // а если следующей нет (текущая - последняя), то false - выходим из цикла
                        } while (c.moveToNext());
                    } else
                        Log.d(LOG_TAG, "0 rows");
                    c.close();
                } catch (Exception e) {
                    Log.d(LOG_TAG, "--- Ошибка " + e + " ----");
                }

                return sb.toString();

            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }
        }

        protected void onPostExecute(String result) {

            /*if (countTablesDB < 3) {
                Log.d(LOG_TAG, "--- Таблиц меньше 3 ----");
                DownloadDB("https://pk-vortex.ru/mobail-files/db/db/vortex.db");
            } else {
                Log.d(LOG_TAG, "--- Таблиц больше 3 и равно " + countTablesDB + " ----");
            }*/

            /*Toast toast = Toast.makeText(getApplicationContext(),
                    "Обновление БД завершено", Toast.LENGTH_SHORT);
            toast.show();*/
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
        String filename = DB_FULL_PATH;

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
        Intent intent = ClickLeftMenu.getIntent(KliningRaschetActivity.this, item);
        startActivity(intent);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onClickWebSite(View view) {
        Intent intent = ClickLeftMenu.getIntentWebSite();
        startActivity(intent);
    }


}