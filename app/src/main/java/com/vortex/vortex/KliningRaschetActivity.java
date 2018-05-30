package com.vortex.vortex;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

public class KliningRaschetActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;

    private String product;
    private String LOG_TAG = "myLogs";
    private String DB_VERSION;
    private double rashodM2Marvel;
    private double rashodMlMarvel;
    private double massaMarvel;
    private boolean err;

    private TextView tvRashodMlM2;
    private TextView tvRashodMl;
    private TextView tvMassa;
    private TextView tvStoimostkg;
    private TextView tvStoimost;
    private TextView tvStoimostM2;
    private EditText etPrice;

    private TextView tvStoimostM2Text;
    private TextView tvStoimostText;
    private TextView tvRashodMlM2Text;
    private TextView tvRashodMlText;

    private TableRow rwRashodMl;
    private TableRow rwRashodMlM2;
    private TableRow rwStoimost;
    private TableRow rwStoimostM2;

    private Button btnRaschetMarvel;

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

        tvStoimostkg = (TextView) findViewById(R.id.tvStoimostkg);
        tvStoimost = (TextView) findViewById(R.id.tvStoimost);
        tvStoimostM2 = (TextView) findViewById(R.id.tvStoimostM2);
        tvRashodMlM2 = (TextView) findViewById(R.id.tvRashodMlM2);
        tvRashodMl = (TextView) findViewById(R.id.tvRashodMl);
        tvMassa = (TextView) findViewById(R.id.tvMassa);
        etPrice = (EditText) findViewById(R.id.etPrice);

        tvStoimostM2Text = (TextView) findViewById(R.id.tvStoimostM2Text);
        tvStoimostText = (TextView) findViewById(R.id.tvStoimostText);
        tvRashodMlM2Text = (TextView) findViewById(R.id.tvRashodMlM2Text);
        tvRashodMlText = (TextView) findViewById(R.id.tvRashodMlText);

        rwRashodMl = (TableRow) findViewById(R.id.rwRashodMl);
        rwRashodMlM2 = (TableRow) findViewById(R.id.rwRashodMlM2);
        rwStoimost = (TableRow) findViewById(R.id.rwStoimost);
        rwStoimostM2 = (TableRow) findViewById(R.id.rwStoimostM2);

        btnRaschetMarvel = (Button) findViewById(R.id.btnRaschetMarvel);

        tvRashodMlM2.setText("0");
        tvRashodMl.setText("0");
        tvMassa.setText("0");

        product = getIntent().getStringExtra("nameProduct");
        setTitle(product);
        product = product.toUpperCase();
        err = false;
        new Load_data(this).execute();
    }

    public void onClickRaschet(View view) {

        double price = Double.parseDouble(etPrice.getText().toString());
        double stoimost1Kg = price / massaMarvel;
        double stoimostSredstvam2 = stoimost1Kg / 1000 * rashodM2Marvel;
        double stoimostUborki = stoimost1Kg / 1000 * rashodMlMarvel;

        tvStoimost.setText(String.valueOf(roundUp(stoimostUborki, 2)));
        tvStoimostkg.setText((String.valueOf(roundUp(stoimost1Kg, 2))));
        tvStoimostM2.setText((String.valueOf(roundUp(stoimostSredstvam2, 2))));
    }

    public BigDecimal roundUp(double value, int digits) {
        return new BigDecimal("" + value).setScale(digits, BigDecimal.ROUND_HALF_UP);
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

                rashodM2Marvel = 0;
                rashodMlMarvel = 0;
                massaMarvel = 0;

                try {
                    Log.d(LOG_TAG, "--- Получение данных из БД cleanbox" + " ----");
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
                    err = true;
                    Log.d(LOG_TAG, "--- Ошибка " + e + " ----");
                }
                //return "";//sb.toString();

            } catch (Exception e) {
                err = true;
                //return new String("Exception: " + e.getMessage());
            }
            return null;
        }

        @SuppressLint("WrongConstant")
        protected void onPostExecute(Void result) {

            if (err) {
                btnRaschetMarvel.setEnabled(false);
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Возникли проблемы с данными. Включите интернет и " +
                                "откройте программу заново", Toast.LENGTH_SHORT);
                toast.setDuration(50000);
                toast.show();
            } else {

                tvRashodMlM2.setText(String.valueOf(rashodM2Marvel));
                tvRashodMl.setText(String.valueOf(rashodMlMarvel));
                tvMassa.setText(String.valueOf(massaMarvel));

                if (rashodMlMarvel == 0) {
                    rwRashodMl.setVisibility(View.GONE);
                    rwStoimost.setVisibility(View.GONE);
                } else if (rashodM2Marvel == 0) {
                    rwRashodMlM2.setVisibility(View.GONE);
                    rwStoimostM2.setVisibility(View.GONE);
                }
            }
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

}