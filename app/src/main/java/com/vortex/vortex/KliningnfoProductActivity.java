package com.vortex.vortex;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
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

public class KliningnfoProductActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;

    private String LOG_TAG = "myLogs";
    private String DB_VERSION;
    private boolean err;

    private String product;
    private TextView tvAppointment;
    private TextView tvInstruction;

    private String appointment;
    private String instruction;
    private String strNONE;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kliningnfo_product);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        product = getIntent().getStringExtra("nameProduct");
        title = getIntent().getStringExtra("title");
        setTitle(product);
        product = product.toUpperCase();
        title = title.toUpperCase();

        tvAppointment = findViewById(R.id.tvAppointment);
        tvInstruction = findViewById(R.id.tvInstruction);

        appointment = "";
        instruction = "";
        strNONE = "Данные не найдены";

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Intent intent = ClickLeftMenu.getIntent(KliningnfoProductActivity.this, item);
        startActivity(intent);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onclickCalculation(View view) {
        Intent intent = new Intent(KliningnfoProductActivity.this, ActivityKliningNewCalculation.class);
        intent.putExtra("nameProduct", product);
        intent.putExtra("title", title);
        startActivity(intent);
    }

    @SuppressLint("StaticFieldLeak")
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

                try {
                    Log.d(LOG_TAG, "--- Получение данных из БД cleanbox" + " ----");
                    Cursor c = mDb.query("cleanboxInfoProduct", null, null, null, null, null, null);

                    // ставим позицию курсора на первую строку выборки
                    // если в выборке нет строк, вернется false
                    if (c.moveToFirst()) {

                        // определяем номера столбцов по имени в выборке
                        int idColIndex = c.getColumnIndex("id");
                        int nameColIndex = c.getColumnIndex("name");
                        int appointmentColIndex = c.getColumnIndex("appointment");
                        int instructionColIndex = c.getColumnIndex("instruction");

                        do {
                            String product_db = c.getString(nameColIndex);
                            if (product.equals(product_db)) {

                                appointment = c.getString(appointmentColIndex);
                                instruction = c.getString(instructionColIndex);
                            }
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
            }
            return null;
        }

        @SuppressLint("WrongConstant")
        protected void onPostExecute(Void result) {

            if (err) {
                Log.d(LOG_TAG, "--- Ошибка есть ----");
                AlertDialog.Builder builder = new AlertDialog.Builder(KliningnfoProductActivity.this);
                builder.setTitle("Ошибка")
                        .setMessage(getString(R.string.noneDB))
                        //.setIcon(R.drawable.ic_android_cat)
                        .setCancelable(false)
                        .setNegativeButton("ОК",
                                (dialog, id) -> {
                                    //btnRaschetMarvel.setEnabled(false);
                                    Intent intent = new Intent(KliningnfoProductActivity.this, activity_klining.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    dialog.cancel();
                                });
                AlertDialog alert = builder.create();
                alert.show();
                Log.d(LOG_TAG, "--- Диалог показан ----");
            } else {

                if (appointment != "" && instruction != "") {
                    tvAppointment.setText(appointment);
                    tvInstruction.setText(instruction);
                } else {
                    tvAppointment.setText(strNONE);
                    tvInstruction.setText(strNONE);
                }
            }
        }
    }
}
