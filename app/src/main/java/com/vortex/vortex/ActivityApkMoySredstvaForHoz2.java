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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

import static com.vortex.vortex.Calculations.RoundUp.roundUp;

public class ActivityApkMoySredstvaForHoz2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    double plotnost = 1.22;
    private EditText edKolichKorov;
    private EditText etKolichDoek;
    private EditText etKoncentratSheloch;
    private EditText etKoncentratKislot;
    private EditText etKolichShelochMoek;
    private EditText etKolichKislotMoek;
    private EditText etVanna;
    private EditText etDay;
    private EditText etPriceSheloch;
    private EditText edPriceKislot;

    private TextView tvKolichSredstvKg;
    private TextView tvKolichShelochKg;
    private TextView tvKolichKislotKg;
    private TextView tvPriceKislot;
    private TextView tvPriceSheloch;
    private TextView tvPriceObsh;

    Button btnRaschet;
    TableLayout tableL;
    ScrollView scrollview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apk_moy_sredstva_for_hoz2);

        setTitle("Расчет моющих средств для хозяйств");

        btnRaschet = (Button) findViewById(R.id.btnRaschet);
        tableL = (TableLayout) findViewById(R.id.tableL);
        scrollview = (ScrollView) findViewById(R.id.scrollview);

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

    public void onClickResult(View view) {
        edKolichKorov = (EditText) findViewById(R.id.edKolichKorov);
        etKolichDoek = (EditText) findViewById(R.id.etKolichDoek);
        etKoncentratSheloch = (EditText) findViewById(R.id.etKoncentratSheloch);
        etKoncentratKislot = (EditText) findViewById(R.id.etKoncentratKislot);
        etKolichShelochMoek = (EditText) findViewById(R.id.etKolichShelochMoek);
        etKolichKislotMoek = (EditText) findViewById(R.id.etKolichKislotMoek);
        etVanna = (EditText) findViewById(R.id.etVanna);
        etDay = (EditText) findViewById(R.id.etDay);
        etPriceSheloch = (EditText) findViewById(R.id.etPriceSheloch);
        edPriceKislot = (EditText) findViewById(R.id.edPriceKislot);

        tvKolichSredstvKg = (TextView) findViewById(R.id.tvKolichSredstvKg);
        tvKolichShelochKg = (TextView) findViewById(R.id.tvKolichShelochKg);
        tvKolichKislotKg = (TextView) findViewById(R.id.tvKolichKislotKg);
        tvPriceKislot = (TextView) findViewById(R.id.tvPriceKislot);
        tvPriceSheloch = (TextView) findViewById(R.id.tvPriceSheloch);
        tvPriceObsh = (TextView) findViewById(R.id.tvPriceObsh);

        if (edKolichKorov.getText().length() == 0 || etKolichDoek.getText().length() == 0 || etKoncentratSheloch.getText().length() == 0
                || etKoncentratKislot.getText().length() == 0 || etKolichShelochMoek.getText().length() == 0 || etKolichKislotMoek.getText().length() == 0
                || etVanna.getText().length() == 0 || etDay.getText().length() == 0 || etPriceSheloch.getText().length() == 0
                || edPriceKislot.getText().length() == 0) {
            Toast.makeText(getBaseContext(), "Заполните пожалуйста все данные", Toast.LENGTH_SHORT).show();
            return;
        }

        //Скрытие клавиатуры по нажатию кнопки
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(btnRaschet.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        scrollview.fullScroll(View.FOCUS_DOWN);

        tableL.setVisibility(View.VISIBLE);
        int gray = Color.parseColor("#7B7979");
        btnRaschet.setBackgroundColor(gray);

        double kolichKorov = Double.parseDouble(edKolichKorov.getText().toString());
        double kolichDoek = Double.parseDouble(etKolichDoek.getText().toString());
        double koncentratSheloch = Double.parseDouble(etKoncentratSheloch.getText().toString());
        double koncentratKislot = Double.parseDouble(etKoncentratKislot.getText().toString());
        double kolichShelochMoek = Double.parseDouble(etKolichShelochMoek.getText().toString());
        double kolichKislotMoek = Double.parseDouble(etKolichKislotMoek.getText().toString());
        double vanna = Double.parseDouble(etVanna.getText().toString());
        double day = Double.parseDouble(etDay.getText().toString());
        double priceSheloch = Double.parseDouble(etPriceSheloch.getText().toString());
        double priceKislot = Double.parseDouble(edPriceKislot.getText().toString());

        double reskolichSredstvKg;
        double reskolichShelochKg;
        double reskolichKislotKg;
        double respriceKislot;
        double respriceSheloch;
        double respriceObsh;

        double kooficent = 0;

        if (0 < kolichKorov && kolichKorov <= 200)
            kooficent = 1;
        else if (200 < kolichKorov && kolichKorov <= 400)
            kooficent = 2;
        else if (400 < kolichKorov && kolichKorov <= 600)
            kooficent = 3;
        else if (600 < kolichKorov && kolichKorov <= 800)
            kooficent = 4;
        else if (800 < kolichKorov && kolichKorov <= 1000)
            kooficent = 5;
        else if (1000 < kolichKorov && kolichKorov <= 1200)
            kooficent = 6;

        reskolichShelochKg = ((vanna * koncentratSheloch) / 100) * plotnost * ((day * kolichDoek) / (kolichShelochMoek + kolichKislotMoek)) * kolichShelochMoek * kooficent;
        reskolichKislotKg = ((vanna * koncentratKislot) / 100) * plotnost * ((day * kolichDoek) / (kolichShelochMoek + kolichKislotMoek)) * kolichKislotMoek * kooficent;
        reskolichSredstvKg = reskolichShelochKg + reskolichKislotKg;

        respriceSheloch = reskolichShelochKg * priceSheloch;
        respriceKislot = reskolichKislotKg * priceKislot;
        respriceObsh = respriceSheloch + respriceKislot;

        tvKolichSredstvKg.setText(String.valueOf(roundUp(reskolichSredstvKg, 2)));
        tvKolichShelochKg.setText(String.valueOf(roundUp(reskolichShelochKg, 2)));
        tvKolichKislotKg.setText(String.valueOf(roundUp(reskolichKislotKg, 2)));
        tvPriceKislot.setText(String.valueOf(roundUp(respriceKislot, 2)));
        tvPriceSheloch.setText(String.valueOf(roundUp(respriceSheloch, 2)));
        tvPriceObsh.setText(String.valueOf(roundUp(respriceObsh, 2)));
    }

    public void onClickIcon(View view) {

        Intent intent = new Intent(this, Main2Activity.class);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Intent intent = ClickLeftMenu.getIntent(ActivityApkMoySredstvaForHoz2.this, item);
        startActivity(intent);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
