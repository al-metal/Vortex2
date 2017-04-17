package com.vortex.vortex;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

public class ActivityAPKKopytaDesitabProfilakt2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    double vanna = 200;

    EditText etStado;
    EditText etProdolDen;
    EditText etPercent;
    EditText etPrice;
    EditText etVes;

    TextView tvKolichVann;
    TextView tvTrebuemDesitab;
    TextView tvPriceKg;
    TextView tvVsegoPrice;
    TextView tvKorovaDen;
    TextView tvKorovVsego;
    TextView tvKolichObrabotok;

    Button btnRaschet;
    TableLayout tableL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apkkopyta_desitab_profilakt2);

        setTitle("DESITUB «профилактика»");

        btnRaschet = (Button) findViewById(R.id.btnRaschet);
        tableL = (TableLayout) findViewById(R.id.tableL);

        etStado = (EditText) findViewById(R.id.etStado);
        etProdolDen = (EditText) findViewById(R.id.etProdolDen);
        etPercent = (EditText) findViewById(R.id.etPercent);
        etPrice = (EditText) findViewById(R.id.etPrice);
        etVes = (EditText) findViewById(R.id.etVes);

        tvKolichVann = (TextView) findViewById(R.id.tvKolichVann);
        tvTrebuemDesitab = (TextView) findViewById(R.id.tvTrebuemDesitab);
        tvPriceKg = (TextView) findViewById(R.id.tvPriceKg);
        tvVsegoPrice = (TextView) findViewById(R.id.tvVsegoPrice);
        tvKorovaDen = (TextView) findViewById(R.id.tvKorovaDen);
        tvKorovVsego = (TextView) findViewById(R.id.tvKorovVsego);
        tvKolichObrabotok = (TextView) findViewById(R.id.tvKolichObrabotok);

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

    public void onClickRaschet(View view) {
        if (etStado.getText().length() == 0 || etProdolDen.getText().length() == 0
                || etPercent.getText().length() == 0 || etPrice.getText().length() == 0
                || etVes.getText().length() == 0) {
            Toast.makeText(getBaseContext(), "Заполните пожалуйста все данные", Toast.LENGTH_SHORT).show();
            return;
        }

        int gray = Color.parseColor("#7B7979");
        btnRaschet.setBackgroundColor(gray);
        tableL.setVisibility(View.VISIBLE);

        double stado = Double.parseDouble(etStado.getText().toString());
        double prodolDen = Double.parseDouble(etProdolDen.getText().toString());
        double percent = Double.parseDouble(etPercent.getText().toString());
        double price = Double.parseDouble(etPrice.getText().toString());
        double ves = Double.parseDouble(etVes.getText().toString());

        double percentDesitab = (vanna * percent) / 100;
        double kolichObrabotok = (prodolDen / 7) * 2;
        double kolichVann = (kolichObrabotok * stado) / 200;
        double trebuemDesitab = percentDesitab*kolichVann;
        double priceKg = price / ves;
        double vsegoPrice = trebuemDesitab * priceKg;
        double korovVsego = vsegoPrice / stado;
        double korovaDen = korovVsego / prodolDen;

        tvKolichObrabotok.setText(String.valueOf(roundUp(kolichObrabotok, 0)));
        tvKolichVann.setText(String.valueOf(roundUp(kolichVann, 0)));
        tvTrebuemDesitab.setText(String.valueOf(roundUp(trebuemDesitab, 0)));
        tvPriceKg.setText(String.valueOf(roundUp(priceKg, 0)));
        tvVsegoPrice.setText(String.valueOf(roundUp(vsegoPrice, 0)));
        tvKorovaDen.setText(String.valueOf(roundUp(korovaDen, 0)));
        tvKorovVsego.setText(String.valueOf(roundUp(korovVsego, 0)));

    }
    public BigDecimal roundUp(double value, int digits) {
        return new BigDecimal("" + value).setScale(digits, BigDecimal.ROUND_HALF_UP);
    }

    public void onClickVanna(View view) {
        Toast.makeText(getBaseContext(), "Проходимость составляет 500 голов через 200 литров", Toast.LENGTH_SHORT).show();
    }

    public void onClickDay(View view) {
        Toast.makeText(getBaseContext(), "1 раз, 2 раза в неделю", Toast.LENGTH_SHORT).show();
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

        if (id == R.id.nav_camera) {
            Intent intent = new Intent(ActivityAPKKopytaDesitabProfilakt2.this, ApkActivity2.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(ActivityAPKKopytaDesitabProfilakt2.this, ActivityPisheProm2.class);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(ActivityAPKKopytaDesitabProfilakt2.this, ActivityAutoVybor2.class);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {
            Toast.makeText(getBaseContext(), "Данный раздел находится в разработке", Toast.LENGTH_SHORT).show();
        }

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
