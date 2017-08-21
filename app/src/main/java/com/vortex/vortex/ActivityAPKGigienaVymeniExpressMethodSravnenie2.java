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

public class ActivityAPKGigienaVymeniExpressMethodSravnenie2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    double stoimKgVotrex = 0;
    double kolichGigienVotrex = 0;
    double stoimObrabotkiVotrex = 0;
    double dblRashodGolovaVotrex = 0;
    TextView tvStoimKgVortex;
    TextView tvKolichSredstvaVortex;
    TextView tvStoimostVsegoVortex;
    TextView tvStoimostGolovyVortex;
    TextView tvStoimKg;
    TextView tvKolichSredstva;
    TextView tvStoimostVsego;
    TextView tvStoimostGolovy;
    TextView tvSravnenie;
    EditText etPrice;
    EditText etVes;
    EditText etKolichGolov;
    EditText etSredstvo;

    TableLayout tableL;
    Button btnRaschet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apkgigiena_vymeni_express_method_sravnenie2);

        setTitle("Сравнить с другим средством");

        tableL = (TableLayout) findViewById(R.id.tableL);
        btnRaschet = (Button) findViewById(R.id.btnRaschet);

        tvStoimKgVortex = (TextView) findViewById(R.id.tvStoimKgVortex);
        tvKolichSredstvaVortex = (TextView) findViewById(R.id.tvKolichSredstvaVortex);
        tvStoimostVsegoVortex = (TextView) findViewById(R.id.tvStoimostVsegoVortex);
        tvStoimostGolovyVortex = (TextView) findViewById(R.id.tvStoimostGolovyVortex);
        tvStoimKg = (TextView) findViewById(R.id.tvStoimKg);
        tvKolichSredstva = (TextView) findViewById(R.id.tvKolichSredstva);
        tvStoimostVsego = (TextView) findViewById(R.id.tvStoimostVsego);
        tvStoimostGolovy = (TextView) findViewById(R.id.tvStoimostGolovy);
        tvSravnenie = (TextView) findViewById(R.id.tvSravnenie);

        etPrice = (EditText) findViewById(R.id.etPrice);
        etVes = (EditText) findViewById(R.id.etVes);
        etKolichGolov = (EditText) findViewById(R.id.etKolichGolov);
        etSredstvo = (EditText) findViewById(R.id.etSredstvo);


        stoimKgVotrex = getIntent().getExtras().getDouble("stoimKg");
        kolichGigienVotrex = getIntent().getExtras().getDouble("kolichestvo");
        stoimObrabotkiVotrex = getIntent().getExtras().getDouble("stoimostVsego");
        dblRashodGolovaVotrex = getIntent().getExtras().getDouble("stoimostGolovy");

        tvStoimKgVortex.setText(String.valueOf(roundUp(stoimKgVotrex, 2)));
        tvKolichSredstvaVortex.setText(String.valueOf(roundUp(kolichGigienVotrex, 2)));
        tvStoimostVsegoVortex.setText(String.valueOf(roundUp(stoimObrabotkiVotrex, 2)));
        tvStoimostGolovyVortex.setText(String.valueOf(roundUp(dblRashodGolovaVotrex, 2)));

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

    public void onClick(View view) {
        if(etPrice.getText().length() == 0 || etVes.getText().length() == 0 || etKolichGolov.getText().length() == 0 || etSredstvo.getText().length() == 0){
            Toast.makeText(getBaseContext(), "Заполните пожалуйста все данные", Toast.LENGTH_SHORT).show();
            return;
        }

        //Скрытие клавиатуры по нажатию кнопки
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(btnRaschet.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        double stoim = Double.parseDouble(etPrice.getText().toString());
        double ves = Double.parseDouble(etVes.getText().toString());
        double kolichGolov = Double.parseDouble(etKolichGolov.getText().toString());

        double dblStoimKg = stoim / ves;
        double dblKolichSredstva = kolichGolov * 0.008;
        double dblStoimostVsego = 0.008 * dblStoimKg * kolichGolov;
        double dblStoimostGolovy = dblStoimostVsego / kolichGolov;

        tvStoimKg.setText(String.valueOf(roundUp(dblStoimKg, 2)));
        tvKolichSredstva.setText(String.valueOf(roundUp(dblKolichSredstva, 2)));
        tvStoimostVsego.setText(String.valueOf(roundUp(dblStoimostVsego, 2)));
        tvStoimostGolovy.setText(String.valueOf(roundUp(dblStoimostGolovy, 2)));
        tvSravnenie.setText(etSredstvo.getText());

        tableL.setVisibility(View.VISIBLE);
        int gray = Color.parseColor("#7B7979");
        btnRaschet.setBackgroundColor(gray);
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
            Intent intent = new Intent(ActivityAPKGigienaVymeniExpressMethodSravnenie2.this, ApkActivity2.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(ActivityAPKGigienaVymeniExpressMethodSravnenie2.this, ActivityPisheProm2.class);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(ActivityAPKGigienaVymeniExpressMethodSravnenie2.this, ActivityAutoVybor2.class);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent(ActivityAPKGigienaVymeniExpressMethodSravnenie2.this, activity_klining.class);
            startActivity(intent);
        }else if (id == R.id.nav_spravochnik){
            Intent intent = new Intent(ActivityAPKGigienaVymeniExpressMethodSravnenie2.this, activity_spravka.class);
            startActivity(intent);
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
