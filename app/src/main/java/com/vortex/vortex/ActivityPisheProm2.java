package com.vortex.vortex;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

public class ActivityPisheProm2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String[] data = {"ВЫБРАТЬ СРЕДСТВО", "BIOTEC", "BIOTEC C", "BIOTEC Super", "BIOTEC М", "KSILAN", "KSILAN K", "KSILAN Super", "KSILAN М", "Tank CB 46",
            "Tank CA27", "Tank FA18", "Tank FB17", "TANK FBD 0803/1", "TANK FB 36", "TANK FBD 0402/1", "TANK LBD 0107/1", "TANK LBD 1002/2",
            "TANK FBD 0902/2", "TANKCAD 1415/3", "TANK FN"};
    String sredstvo;
    double dblplotnost;
    double PriceLitr;
    double StoimostRastvora;
    double RashodSrdstvaMoyka;
    double RashodSredstvaMes;
    double PriceSredstva;

    EditText etPrice;
    EditText etKoncentratRastvor;
    EditText etObjemRastvor;
    EditText etOperacSutki;
    EditText etDay;

    TextView tvPlotnost;
    TextView tvPriceLitr;
    TextView tvStoimostRastvora;
    TextView tvRashodSrdstvaMoyka;
    TextView tvRashodSredstvaMes;
    TextView tvPriceSredstva;

    boolean selectedSpiner = false;

    TableLayout tableRaschet;
    Button btnSravnenie;
    Button btnRaschet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pishe_prom2);

        setTitle("TM TANK");

        tableRaschet = (TableLayout) findViewById(R.id.tableRaschet);
        btnSravnenie = (Button) findViewById(R.id.btnSravnenie);
        btnRaschet = (Button) findViewById(R.id.btnRaschet);

        etPrice = (EditText) findViewById(R.id.etPrice);
        etKoncentratRastvor = (EditText) findViewById(R.id.etKoncentratRastvor);
        etObjemRastvor = (EditText) findViewById(R.id.etObjemRastvor);
        etOperacSutki = (EditText) findViewById(R.id.etOperacSutki);
        etDay = (EditText) findViewById(R.id.etDay);

        tvPlotnost = (TextView) findViewById(R.id.tvPlotnost);
        tvPriceLitr = (TextView) findViewById(R.id.tvPriceLitr);
        tvStoimostRastvora = (TextView) findViewById(R.id.tvStoimostRastvora);
        tvRashodSrdstvaMoyka = (TextView) findViewById(R.id.tvRashodSrdstvaMoyka);
        tvRashodSredstvaMes = (TextView) findViewById(R.id.tvRashodSredstvaMes);
        tvPriceSredstva = (TextView) findViewById(R.id.tvPriceSredstva);

        Spinner spinner = (Spinner) findViewById(R.id.spinner7);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner, data);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                // Set adapter flag that something has been chosen

                if (pos == 0) {
                    selectedSpiner = false;
                    return;
                } else if (pos == 1) {
                    dblplotnost = 1.252;
                } else if (pos == 2) {
                    dblplotnost = 1.226;
                } else if (pos == 3) {
                    dblplotnost = 1.230;
                } else if (pos == 4) {
                    dblplotnost = 1.198;
                } else if (pos == 5) {
                    dblplotnost = 1.22;
                } else if (pos == 6) {
                    dblplotnost = 1.2;
                } else if (pos == 7) {
                    dblplotnost = 1.28;
                } else if (pos == 8) {
                    dblplotnost = 1.167;
                } else if (pos == 9) {
                    dblplotnost = 1.457;
                } else if (pos == 10) {
                    dblplotnost = 1.221;
                } else if (pos == 11) {
                    dblplotnost = 1.157;
                } else if (pos == 12) {
                    dblplotnost = 1.194;
                } else if (pos == 13) {
                    dblplotnost = 1.170;
                } else if (pos == 14) {
                    dblplotnost = 1.391;
                } else if (pos == 15) {
                    dblplotnost = 1.173;
                } else if (pos == 16) {
                    dblplotnost = 1.145;
                } else if (pos == 17) {
                    dblplotnost = 1.122;
                } else if (pos == 18) {
                    dblplotnost = 1.104;
                } else if (pos == 19) {
                    dblplotnost = 1.16;
                } else if (pos == 20) {
                    dblplotnost = 1.025;
                }
                sredstvo = data[pos];
                selectedSpiner = true;
                tvPlotnost.setText(String.valueOf(dblplotnost));
            }
        });

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
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Intent intent;
        intent = ClickLeftMenu.getIntent(ActivityPisheProm2.this, id);
        startActivity(intent);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onClickRaschet(View view) {
        if (etPrice.getText().length() == 0 || etKoncentratRastvor.getText().length() == 0 || etObjemRastvor.getText().length() == 0
                || etOperacSutki.getText().length() == 0 || etDay.getText().length() == 0 || !selectedSpiner) {
            Toast.makeText(getBaseContext(), "Заполните пожалуйста все данные", Toast.LENGTH_SHORT).show();
            return;
        }

        //Скрытие клавиатуры по нажатию кнопки
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(btnRaschet.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        double dblPrice = Double.parseDouble(etPrice.getText().toString());
        double dblKoncentratRastvor = Double.parseDouble(etKoncentratRastvor.getText().toString());
        double dblObjemRastvor = Double.parseDouble(etObjemRastvor.getText().toString());
        double dblOperacSutki = Double.parseDouble(etOperacSutki.getText().toString());
        double dblDay = Double.parseDouble(etDay.getText().toString());

        PriceLitr = dblPrice * dblplotnost;
        StoimostRastvora = dblKoncentratRastvor * dblObjemRastvor * PriceLitr / 100;
        RashodSrdstvaMoyka = dblKoncentratRastvor * dblObjemRastvor / 100;
        RashodSredstvaMes = RashodSrdstvaMoyka * dblOperacSutki * dblDay;
        PriceSredstva = PriceLitr * RashodSredstvaMes;

        tvPriceLitr.setText(String.valueOf(roundUp(PriceLitr, 2)));
        tvStoimostRastvora.setText(String.valueOf(roundUp(StoimostRastvora, 2)));
        tvRashodSrdstvaMoyka.setText(String.valueOf(roundUp(RashodSrdstvaMoyka, 2)));
        tvRashodSredstvaMes.setText(String.valueOf(roundUp(RashodSredstvaMes, 2)));
        tvPriceSredstva.setText(String.valueOf(roundUp(PriceSredstva, 2)));

        tableRaschet.setVisibility(View.VISIBLE);
        btnSravnenie.setVisibility(View.VISIBLE);
        int gray = Color.parseColor("#7B7979");
        btnRaschet.setBackgroundColor(gray);
    }

    public BigDecimal roundUp(double value, int digits) {
        return new BigDecimal("" + value).setScale(digits, BigDecimal.ROUND_HALF_UP);
    }

    public void onClickSravnenie(View view) {
        if (PriceLitr == 0 || StoimostRastvora == 0 || RashodSrdstvaMoyka == 0
                || RashodSredstvaMes == 0 || PriceSredstva == 0) {
            Toast.makeText(getBaseContext(), "Данные для сравнения еще не расчитаны", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(ActivityPisheProm2.this, ActivityPishePromSravnenie2.class);
        intent.putExtra("PriceLitr", PriceLitr);
        intent.putExtra("StoimostRastvora", StoimostRastvora);
        intent.putExtra("RashodSrdstvaMoyka", RashodSrdstvaMoyka);
        intent.putExtra("RashodSredstvaMes", RashodSredstvaMes);
        intent.putExtra("PriceSredstva", PriceSredstva);
        intent.putExtra("Sredstvo", sredstvo);
        startActivity(intent);
    }

    public void onClickWebSite(View view) {
        Uri uri = Uri.parse("http://www.pk-vortex.ru"); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
