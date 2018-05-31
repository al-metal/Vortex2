package com.vortex.vortex;

import android.app.Activity;
import android.app.AlertDialog;
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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class activity_spravka extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private final String TAG = getClass().getSimpleName();

    private String name;
    private final String LOG_TAG = "myLogs";
    private String DB_VERSION;
    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;

    private int[] ids;
    private String[] names;
    private String[] descriptions;
    private String[] images;
    private int[] visibles;
    private boolean err;

    private ListView listView;
    private Context context;
    private EditText inputSearch;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spravka);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Справочник");

        inputSearch = (EditText) findViewById(R.id.inputSearch);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        context = this;
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(inputSearch.getWindowToken(), 0);
        err = false;
        new Load_data(this).execute();
    }

    private int ReturnId(String nameProduct) {
        int id = 0;

        for (int i = 0; names.length > i; i++) {
            if (names[i].equals(nameProduct)) {
                id = i;
                break;
            }
        }
        return id;
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
        Intent intent = ClickLeftMenu.getIntent(activity_spravka.this, item);
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
                    visibles = new int[countRow];

                    // ставим позицию курсора на первую строку выборки
                    // если в выборке нет строк, вернется false
                    if (c.moveToFirst()) {

                        // определяем номера столбцов по имени в выборке
                        int idColIndex = c.getColumnIndex("id");
                        int nameColIndex = c.getColumnIndex("nameproduct");
                        int descriptionColIndex = c.getColumnIndex("descriptionproduct");
                        int imageColIndex = c.getColumnIndex("image");
                        int visibleColIndex = c.getColumnIndex("visible");

                        int count = 0;

                        do {
                            int id = Integer.parseInt(c.getString(idColIndex));
                            String name = String.valueOf(c.getString(nameColIndex));
                            String description = String.valueOf(c.getString(descriptionColIndex));
                            String image = String.valueOf(c.getString(imageColIndex));
                            int visible = Integer.parseInt(c.getString(visibleColIndex));

                            ids[count] = id;
                            names[count] = name;
                            descriptions[count] = description;
                            images[count] = image;
                            visibles[count] = visible;

                            // получаем значения по номерам столбцов и пишем все в лог
                            Log.d(LOG_TAG,
                                    "ID = " + id +
                                            ", name = " + name +
                                            ", description = " + description +
                                            ", image = " + image +
                                            ", visible = " + visible);

                            // переход на следующую строку
                            // а если следующей нет (текущая - последняя), то false - выходим из цикла
                            count++;
                        } while (c.moveToNext());
                    } else
                        Log.d(LOG_TAG, "0 rows");
                    c.close();
                } catch (Exception e) {
                    Log.d(LOG_TAG, "--- Ошибка " + e + " ----");
                    err = true;
                }

                //return sb.toString();

            } catch (Exception e) {
                Log.d(LOG_TAG, "--- Ошибка " + e + " ----");
                err = true;
                //return new String("Exception: " + e.getMessage());
            }
            return null;
        }

        protected void onPostExecute(Void result) {

            Log.d(LOG_TAG, "--- postexecut ----");

            if (err) {
                Log.d(LOG_TAG, "--- Ошибка есть ----");
                AlertDialog.Builder builder = new AlertDialog.Builder(activity_spravka.this);
                builder.setTitle("Ошибка")
                        .setMessage(getString(R.string.noneDB))
                        //.setIcon(R.drawable.ic_android_cat)
                        .setCancelable(false)
                        .setNegativeButton("ОК",
                                (dialog, id) -> {
                                    //dialog.cancel();
                                    Intent intent = new Intent(activity_spravka.this, Main2Activity.class);
                                    startActivity(intent);
                                });
                AlertDialog alert = builder.create();
                alert.show();
                Log.d(LOG_TAG, "--- Диалог показан ----");
            } else {
                listView = (ListView) findViewById(R.id.lvMain);
                // создаем адаптер
                adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, names);

                // присваиваем адаптер списку
                listView.setAdapter(adapter);

                //Обрабатываем щелчки на элементах ListView:
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                        String nameProduct = ((TextView) v).getText().toString();
                        position = ReturnId(nameProduct);
                        Intent intent = new Intent();
                        intent.setClass(activity_spravka.this, spravka_sredstvo_new.class);
                        Log.i(TAG, names[position]);
                        intent.putExtra("head", position);
                        intent.putExtra("headName", names[position]);

                        intent.putExtra("position", position);

                        //запускаем вторую активность
                        startActivity(intent);
                    }
                });

                inputSearch.addTextChangedListener(new TextWatcher() {

                    @Override
                    public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                        // When user changed the Text
                        activity_spravka.this.adapter.getFilter().filter(cs);
                    }

                    @Override
                    public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                                  int arg3) {
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void afterTextChanged(Editable arg0) {
                        // TODO Auto-generated method stub
                    }
                });
            }


        }
    }

}
