package com.vortex.vortex;

import android.annotation.SuppressLint;
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
import android.webkit.WebView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class spravka_sredstvo_new extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String name;
    private final String LOG_TAG = "myLogs";
    private String DB_VERSION;
    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;

    private int[] ids;
    private String[] names;
    private String[] descriptions;
    private String[] images;
    private String[] urls;
    private int[] visibles;
    private boolean err;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spravka_sredstvo_new);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        err = false;

        new Load_data(this).execute();
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

    public void onClickIcon(View view) {

        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
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
        Intent intent = ClickLeftMenu.getIntent(spravka_sredstvo_new.this, item);
        startActivity(intent);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public class Load_data extends AsyncTask<Void, Void, Void> {

        Context mContext;

        public Load_data(Context context) {
            mContext = context;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Logo.mSettings = getSharedPreferences(Logo.APP_PREFERENCES, Context.MODE_PRIVATE);
                if (Logo.mSettings.contains(Logo.APP_PREFERENCES_COUNTER)) {
                    // Получаем число из настроек
                    DB_VERSION = Logo.mSettings.getString(Logo.APP_PREFERENCES_COUNTER, "");
                }

                Log.d(LOG_TAG, "--- Версия БД: " + DB_VERSION + " ----");

                mDBHelper = new DatabaseHelper(mContext, DB_VERSION);

                try {
                    mDb = mDBHelper.getWritableDatabase();//.getReadableDatabase();
                } catch (SQLException mSQLException) {
                    throw mSQLException;
                }
                Log.d(LOG_TAG, "--- GOOOOOOO" + " ----");
                try {
                    Log.d(LOG_TAG, "--- Получение данных из БД cleanbox" + " ----");
                    Cursor c = mDb.query("manualproduct", null, null, null, null, null, null);

                    int countRow = c.getCount();

                    ids = new int[countRow];
                    names = new String[countRow];
                    descriptions = new String[countRow];
                    images = new String[countRow];
                    urls = new String[countRow];
                    visibles = new int[countRow];

                    // ставим позицию курсора на первую строку выборки
                    // если в выборке нет строк, вернется false
                    if (c.moveToFirst()) {

                        // определяем номера столбцов по имени в выборке
                        int idColIndex = c.getColumnIndex("id");
                        int nameColIndex = c.getColumnIndex("nameproduct");
                        int descriptionColIndex = c.getColumnIndex("descriptionproduct");
                        int imageColIndex = c.getColumnIndex("image");
                        int urlColIndex = c.getColumnIndex("url");
                        int visibleColIndex = c.getColumnIndex("visible");

                        int count = 0;

                        do {
                            int id = Integer.parseInt(c.getString(idColIndex));
                            String name = String.valueOf(c.getString(nameColIndex));
                            String description = String.valueOf(c.getString(descriptionColIndex));
                            String image = String.valueOf(c.getString(imageColIndex));
                            String url = String.valueOf(c.getString(urlColIndex));
                            int visible = Integer.parseInt(c.getString(visibleColIndex));

                            ids[count] = id;
                            names[count] = name;
                            descriptions[count] = description;
                            images[count] = image;
                            urls[count] = url;
                            visibles[count] = visible;

                            // получаем значения по номерам столбцов и пишем все в лог
                            Log.d(LOG_TAG,
                                    "ID = " + id +
                                            ", name = " + name +
                                            ", description = " + description +
                                            ", image = " + image +
                                            ", url = " + url +
                                            ", visible = " + visible);

                            // переход на следующую строку
                            // а если следующей нет (текущая - последняя), то false - выходим из цикла
                            count++;
                        } while (c.moveToNext());
                    } else
                        Log.d(LOG_TAG, "0 rows");
                    c.close();
                } catch (Exception e) {
                    err = true;
                    Log.d(LOG_TAG, "--- Ошибка " + e + " ----");
                }

            } catch (Exception e) {
                err = true;
                Log.d(LOG_TAG, "--- Ошибка " + e + " ----");
                //return new String("Exception: " + e.getMessage());
            }
            return null;
        }

        @SuppressLint("WrongConstant")
        protected void onPostExecute(Void result) {

            if (err) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Возникли проблемы с данными. Включите интернет и " +
                                "откройте программу заново", Toast.LENGTH_SHORT);
                toast.setDuration(50000);
                toast.show();
            } else {

                WebView webView = (WebView) findViewById(R.id.webView);

                Intent intent = getIntent();
                //получаем строку и формируем имя ресурса
                String resName = "" + intent.getIntExtra("head", 0);
                name = intent.getStringExtra("headName");

                setTitle(name);

                int resId = Integer.parseInt(resName);
                String img = String.valueOf(images[resId]);
                String url = String.valueOf(urls[resId]);
                String desc = String.valueOf(descriptions[resId]);

                String urlHTML = "";
                if(url.equals(null) || url.equals("")){
                    urlHTML = "<p><a href=\"" + url + "\">Подробная информация</a></p>";
                }

                String str = "<html><head></head><style>.center-pic {text-align:center; margin: 7px 7px 7px 0; }</style><body>" +
                        "<P class=\"center-pic\">" +
                        "<img height=\"150dp\" src=\"" + img + "\">" +
                        "</P>" +
                        "<P> " + desc + "</P>" +
                        urlHTML +
                        "</body></html>";

                webView.loadDataWithBaseURL(null, str, "text/html", "utf-8", null);
            }
        }
    }
}
