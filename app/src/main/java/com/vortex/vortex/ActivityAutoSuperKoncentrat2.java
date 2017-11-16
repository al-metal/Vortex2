package com.vortex.vortex;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

public class ActivityAutoSuperKoncentrat2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    double dblVes = 6;
    double dblObjem = 35000;
    double dblRazbavlenie = 900;
    double dblStoimostMoyki = 12;
    double dblKolichestvoZapravok;
    double dblStoimostZapravki;
    double dblStoimostmoykiResult;
    double dblPrice2Kanistr;
    double dblKolichestvoKanistr = 2;

    String strOborudovanie = "PenoGen";
    String strObjem6 = "6 кг = 35 л";
    String strObjem64 = "64 кг = 376 л";
    String strRazbavleniePenoGen = "900";
    String strRazbavleniePenoKomp = "180";
    String strKolichestvoKanistr = "Цена за 2 канистры: ";

    RadioGroup RadioGroupUstroystvo;
    RadioGroup RadioGroupVes;

    TextView tvObjem;
    TextView tvRazbavlenie;
    TextView tvKolichZapravok;
    TextView tvStoimostZapravki;
    TextView tvStoimostMoyki;
    TextView tvKolichestvoKanistr;
    TextView tvPrice2kanistr;

    TableLayout tableL;

    EditText etPrice2Kanistr;
    Button btnRaschet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_super_koncentrat2);

        setTitle("Суперконцентрат");

        tableL = (TableLayout) findViewById(R.id.tableL);
        btnRaschet = (Button) findViewById(R.id.btnRaschet);

        RadioGroupUstroystvo = (RadioGroup) findViewById(R.id.RadioGroupUstroystvo);
        RadioGroupVes = (RadioGroup) findViewById(R.id.RadioGroupVes);

        tvObjem = (TextView) findViewById(R.id.tvObjem);
        tvRazbavlenie = (TextView) findViewById(R.id.tvRazbavlenie);
        tvKolichZapravok = (TextView) findViewById(R.id.tvKolichZapravok);
        tvStoimostZapravki = (TextView) findViewById(R.id.tvStoimostZapravki);
        tvStoimostMoyki = (TextView) findViewById(R.id.tvStoimostMoyki);
        tvKolichestvoKanistr = (TextView) findViewById(R.id.tvKolichestvoKanistr);
        tvPrice2kanistr = (TextView) findViewById(R.id.tvPrice2kanistr);

        etPrice2Kanistr = (EditText) findViewById(R.id.etPrice2Kanistr);

        //region RadioButton
        RadioGroupUstroystvo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case -1:
                        Toast.makeText(getApplicationContext(), "Ничего не выбрано",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rbPenoGen:
                        strOborudovanie = "PenoGen";
                        dblRazbavlenie = 900;
                        dblStoimostMoyki = 12;
                        break;
                    case R.id.rbPenoKomp:
                        strOborudovanie = "PenoKomp";
                        dblRazbavlenie = 180;
                        dblStoimostMoyki = 3;
                        break;
                    default:
                        break;
                }
                ReturnTextViev();
            }
        });

        RadioGroupVes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case -1:
                        Toast.makeText(getApplicationContext(), "Ничего не выбрано",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rbVes6:
                        dblVes = 6;
                        dblObjem = 35000;
                        dblKolichestvoKanistr = 2;
                        strKolichestvoKanistr = "Цена за 2 канистры: ";
                        break;
                    case R.id.rbVes64:
                        dblVes = 64;
                        dblObjem = 376000;
                        dblKolichestvoKanistr = 20;
                        strKolichestvoKanistr = "Цена за 20 канистр: ";
                        break;
                    default:
                        break;
                }
                ReturnTextViev();
            }
        });
        //endregion

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void ReturnTextViev() {
        if (dblVes == 0)
            return;

        if (strOborudovanie == "PenoGen") {
            tvRazbavlenie.setText(strRazbavleniePenoGen);
            if (dblVes == 6) {
                tvObjem.setText(strObjem6);
                dblVes = 35000;
            } else if (dblVes == 64) {
                tvObjem.setText(strObjem64);
                dblVes = 376000;
            }
        } else if (strOborudovanie == "PenoKomp") {
            tvRazbavlenie.setText(strRazbavleniePenoKomp);
            if (dblVes == 6) {
                tvObjem.setText(strObjem6);
                dblVes = 35000;
            } else if (dblVes == 64) {
                tvObjem.setText(strObjem64);
                dblVes = 376000;
            }
        }
    }

    public void onClickRaschet(View view) {
        if (etPrice2Kanistr.getText().length() == 0) {
            Toast.makeText(getBaseContext(), "Заполните пожалуйста все данные", Toast.LENGTH_SHORT).show();
            return;
        }

        //Скрытие клавиатуры по нажатию кнопки
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(btnRaschet.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        tableL.setVisibility(View.VISIBLE);
        int gray = Color.parseColor("#7B7979");
        btnRaschet.setBackgroundColor(gray);

        dblPrice2Kanistr = Double.parseDouble(etPrice2Kanistr.getText().toString());
        dblPrice2Kanistr = dblPrice2Kanistr / dblKolichestvoKanistr;

        dblKolichestvoZapravok = dblObjem / dblRazbavlenie;
        dblStoimostZapravki = dblPrice2Kanistr / dblKolichestvoZapravok;
        dblStoimostmoykiResult = dblStoimostZapravki / dblStoimostMoyki;

        tvKolichZapravok.setText(String.valueOf(roundUp(dblKolichestvoZapravok, 2)));
        tvStoimostZapravki.setText(String.valueOf(roundUp(dblStoimostZapravki, 2)));
        tvStoimostMoyki.setText(String.valueOf(roundUp(dblStoimostmoykiResult, 2)));

        tvKolichestvoKanistr.setText(strKolichestvoKanistr);
        tvPrice2kanistr.setText(String.valueOf(roundUp(dblPrice2Kanistr, 2)));
    }

    public BigDecimal roundUp(double value, int digits) {
        return new BigDecimal("" + value).setScale(digits, BigDecimal.ROUND_HALF_UP);
    }

    public void onClickSravnenie(View view) {
        if (etPrice2Kanistr.getText().length() == 0 || dblKolichestvoZapravok == 0 || dblKolichestvoZapravok == 0 || dblKolichestvoZapravok == 0) {
            Toast.makeText(getBaseContext(), "Данные для сравнения еще не расчитаны", Toast.LENGTH_SHORT).show();
            return;
        }
        dblPrice2Kanistr = dblPrice2Kanistr * dblKolichestvoKanistr;
        Intent intent = new Intent(ActivityAutoSuperKoncentrat2.this, ActivityAutoSuperKoncentratSravnenie2.class);
        intent.putExtra("dblPrice", dblPrice2Kanistr);
        intent.putExtra("Objem", tvObjem.getText());
        intent.putExtra("dblRazbavlenie", dblRazbavlenie);
        intent.putExtra("dblKolichestvoZapravok", dblKolichestvoZapravok);
        intent.putExtra("dblStoimostZapravki", dblStoimostZapravki);
        intent.putExtra("dblStoimostMoyki", dblStoimostmoykiResult);
        intent.putExtra("dblKooficent", dblStoimostMoyki);
        intent.putExtra("dblKolichestvoKanistr", dblKolichestvoKanistr);
        intent.putExtra("dblObjem", dblObjem);
        startActivity(intent);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Intent intent;
        intent = ClickLeftMenu.getIntent(ActivityAutoSuperKoncentrat2.this, id);
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
