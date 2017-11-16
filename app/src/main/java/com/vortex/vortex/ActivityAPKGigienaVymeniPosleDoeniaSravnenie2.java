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
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

public class ActivityAPKGigienaVymeniPosleDoeniaSravnenie2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    double stoimKgVotrex = 0;
    double kolichGigienVotrex = 0;
    double stoimObrabotkiVotrex = 0;
    double stoimObrabotkiVsegoVotrex = 0;
    double dblRashodGolovaVotrex = 0;
    double stoimKg;
    double kolichGigien;
    double stoimObrabotki;
    double stoimObrabotkiVsego;

    TextView tvStoimostKgVortex;
    TextView tvKolichGigienVortex;
    TextView tvObrabotkiVortex;
    TextView tvStoimostKg;
    TextView tvKolichGigien;
    TextView tvObrabotki;
    TextView tvSredstvo;
    TextView tvSredstvoVortex;
    TextView tvObrabotkiVsego;
    TextView tvObrabotkiVsegoVortex;
    EditText etRashodGolova;
    EditText etPrice;
    EditText etVes;
    EditText etKolichGolov;
    EditText etPeriod;
    EditText etObrabotokVDen;
    EditText etSredstvo;

    String strSredstvoVortex;

    TableLayout tableL;
    Button btnRaschet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apkgigiena_vymeni_posle_doenia_sravnenie2);

        setTitle("Сравнение средств");

        tableL = (TableLayout) findViewById(R.id.tableL);
        btnRaschet = (Button) findViewById(R.id.btnRaschet);

        stoimKgVotrex = getIntent().getExtras().getDouble("stoimKg");
        kolichGigienVotrex = getIntent().getExtras().getDouble("kolichGigien");
        stoimObrabotkiVotrex = getIntent().getExtras().getDouble("stoimObrabotki");
        stoimObrabotkiVsegoVotrex = getIntent().getExtras().getDouble("stoimObrabotkiVsego");
        dblRashodGolovaVotrex = getIntent().getExtras().getDouble("dblRashodGolova");
        strSredstvoVortex = getIntent().getExtras().getString("sredstvo");

        tvStoimostKgVortex = (TextView) findViewById(R.id.tvStoimostKgVortex);
        tvKolichGigienVortex = (TextView) findViewById(R.id.tvKolichGigienVortex);
        tvObrabotkiVortex = (TextView) findViewById(R.id.tvObrabotkiVortex);
        tvStoimostKg = (TextView) findViewById(R.id.tvStoimostKg);
        tvKolichGigien = (TextView) findViewById(R.id.tvKolichGigien);
        tvObrabotki = (TextView) findViewById(R.id.tvObrabotki);
        tvSredstvo = (TextView) findViewById(R.id.tvSredstvo);
        tvSredstvoVortex = (TextView) findViewById(R.id.tvSredstvoVortex);
        tvObrabotkiVsegoVortex = (TextView) findViewById(R.id.tvObrabotkiVsegoVortex);
        tvObrabotkiVsego = (TextView) findViewById(R.id.tvObrabotkiVsego);

        tvStoimostKgVortex.setText(String.valueOf(roundUp(stoimKgVotrex, 2)));
        tvKolichGigienVortex.setText(String.valueOf(roundUp(kolichGigienVotrex, 2)));
        tvObrabotkiVortex.setText(String.valueOf(roundUp(stoimObrabotkiVotrex, 2)));
        tvObrabotkiVsegoVortex.setText(String.valueOf(roundUp(stoimObrabotkiVsegoVotrex, 2)));

        etRashodGolova = (EditText) findViewById(R.id.etRashodGolova);
        etPrice = (EditText) findViewById(R.id.etPrice);
        etVes = (EditText) findViewById(R.id.etVes);
        etKolichGolov = (EditText) findViewById(R.id.etKolichGolov);
        etPeriod = (EditText) findViewById(R.id.etPeriod);
        etObrabotokVDen = (EditText) findViewById(R.id.etObrabotokVDen);
        etSredstvo = (EditText) findViewById(R.id.etSredstvo);
        tvSredstvoVortex.setText(strSredstvoVortex);

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

    public BigDecimal roundUp(double value, int digits) {
        return new BigDecimal("" + value).setScale(digits, BigDecimal.ROUND_HALF_UP);
    }

    public void onClickRaschet(View view) {
        if (etPrice.getText().length() == 0 || etVes.getText().length() == 0 || etKolichGolov.getText().length() == 0
                || etPeriod.getText().length() == 0 || etObrabotokVDen.getText().length() == 0 || etRashodGolova.getText().length() == 0
                || etSredstvo.getText().length() == 0) {
            Toast.makeText(getBaseContext(), "Заполните пожалуйста все данные", Toast.LENGTH_SHORT).show();
            tvStoimostKg.setText("0");
            tvKolichGigien.setText("0");
            tvObrabotki.setText("0");
            return;
        }

        //Скрытие клавиатуры по нажатию кнопки
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(btnRaschet.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        tableL.setVisibility(View.VISIBLE);
        int gray = Color.parseColor("#7B7979");
        btnRaschet.setBackgroundColor(gray);

        double price = Double.parseDouble(etPrice.getText().toString());
        double ves = Double.parseDouble(etVes.getText().toString());
        double kolichGolov = Double.parseDouble(etKolichGolov.getText().toString());
        double period = Double.parseDouble(etPeriod.getText().toString());
        double kolichObrabotok = Double.parseDouble(etObrabotokVDen.getText().toString());
        double rashodGolova = Double.parseDouble(etRashodGolova.getText().toString());

        stoimKg = price / ves;
        kolichGigien = (period * kolichObrabotok) * kolichGolov * (rashodGolova / 1000);
        stoimObrabotki = stoimKg * (rashodGolova / 1000);
        stoimObrabotkiVsego = stoimKg * kolichGigien;

        tvStoimostKg.setText(String.valueOf(roundUp(stoimKg, 2)));
        tvKolichGigien.setText(String.valueOf(roundUp(kolichGigien, 2)));
        tvObrabotki.setText(String.valueOf(roundUp(stoimObrabotki, 2)));
        tvObrabotkiVsego.setText(String.valueOf(roundUp(stoimObrabotkiVsego, 2)));
        tvSredstvo.setText(etSredstvo.getText());
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
        intent = ClickLeftMenu.getIntent(ActivityAPKGigienaVymeniPosleDoeniaSravnenie2.this, id);
        startActivity(intent);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onClickWebSite(View view) {
        Uri uri = Uri.parse("http://www.pk-vortex.ru"); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
