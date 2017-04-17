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

public class ActivityAPKKopytaMedKuporos2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    double vanna = 200;

    EditText etStado;
    EditText etObrabotka;
    EditText etKuporos;
    EditText etObrabotokDen;
    EditText etPrice;

    TextView tvKuporos;
    TextView tvPrice;
    TextView tvObrabotka;

    Button btnRaschet;
    TableLayout tableL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apkkopyta_med_kuporos2);

        setTitle("Медный купорос");

        btnRaschet = (Button) findViewById(R.id.btnRaschet);
        tableL = (TableLayout) findViewById(R.id.tableL);

        etStado = (EditText) findViewById(R.id.etStado);
        etObrabotka = (EditText) findViewById(R.id.etObrabotka);
        etKuporos = (EditText) findViewById(R.id.etKuporos);
        etObrabotokDen = (EditText) findViewById(R.id.etObrabotokDen);
        etPrice = (EditText) findViewById(R.id.etPrice);

        tvKuporos = (TextView) findViewById(R.id.tvKuporos);
        tvPrice = (TextView) findViewById(R.id.tvPrice);
        tvObrabotka = (TextView) findViewById(R.id.tvObrabotka);

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

    public void onClick(View view) {
        if (etStado.getText().length() == 0 || etObrabotka.getText().length() == 0
                || etKuporos.getText().length() == 0 || etObrabotokDen.getText().length() == 0 || etPrice.getText().length() == 0) {
            Toast.makeText(getBaseContext(), "Заполните пожалуйста все данные", Toast.LENGTH_SHORT).show();
            return;
        }

        int gray = Color.parseColor("#7B7979");
        btnRaschet.setBackgroundColor(gray);
        tableL.setVisibility(View.VISIBLE);

        double stado =  Double.parseDouble(etStado.getText().toString());
        double obrabotka =  Double.parseDouble(etObrabotka.getText().toString());
        double kuporos =  Double.parseDouble(etKuporos.getText().toString());
        double obrabotkaDen =  Double.parseDouble(etObrabotokDen.getText().toString());
        double price =  Double.parseDouble(etPrice.getText().toString());

        double trebuemKuporosKg = (stado / vanna) * ((vanna * kuporos) / 100) * obrabotka * obrabotkaDen;
        double stoimPeriod = trebuemKuporosKg * price;
        double stoimGolova = stoimPeriod / stado;

        tvKuporos.setText(String.valueOf(roundUp(trebuemKuporosKg, 0)));
        tvPrice.setText(String.valueOf(roundUp(stoimPeriod, 0)));
        tvObrabotka.setText(String.valueOf(roundUp(stoimGolova, 0)));
    }
    public BigDecimal roundUp(double value, int digits) {
        return new BigDecimal("" + value).setScale(digits, BigDecimal.ROUND_HALF_UP);
    }

    public void onClickVanna(View view) {
        Toast.makeText(getBaseContext(), "Проходимость составляет 500 голов через 200 литров", Toast.LENGTH_SHORT).show();
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
            Intent intent = new Intent(ActivityAPKKopytaMedKuporos2.this, ApkActivity2.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(ActivityAPKKopytaMedKuporos2.this, ActivityPisheProm2.class);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(ActivityAPKKopytaMedKuporos2.this, ActivityAutoVybor2.class);
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
