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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

public class ActivityAPKGigienaVymeniDoDoeniaSravnenie2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String[] data1 = {"10", "15", "20", "30", "40", "100"};

    Spinner spinner3;
    TextView tvStoimostKgVortex;
    TextView tvKolichGigienVortex;
    TextView tvObrabotkiVortex;
    TextView tvRashodGolova;
    TextView tvStoimostKg;
    TextView tvKolichGigien;
    TextView tvObrabotki;
    TextView tvSredstvo;
    TextView tvSredstvoVortex;
    EditText etPrice;
    EditText etVes;
    EditText etGolov;
    EditText etDay;
    EditText etObrabotok;
    EditText etSredstvo;

    double stoimKgVotrex = 0;
    double kolichGigienVotrex = 0;
    double stoimObrabotkiVotrex = 0;
    double dblRashodGolovaVotrex = 0;
    double dblRashodGolova = 0;
    double stoimKg;
    double kolichGigien;
    double stoimObrabotki;
    String strSredstvoVortex;

    TableLayout tableL;
    Button btnRaschet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apkgigiena_vymeni_do_doenia_sravnenie2);

        setTitle("Сравнить с другим средством");

        tableL = (TableLayout) findViewById(R.id.tableL);
        btnRaschet = (Button) findViewById(R.id.btnRaschet);

        spinner3 = (Spinner) findViewById(R.id.spinner3);
        tvStoimostKgVortex = (TextView) findViewById(R.id.tvStoimostKgVortex);
        tvKolichGigienVortex = (TextView) findViewById(R.id.tvKolichGigienVortex);
        tvObrabotkiVortex = (TextView) findViewById(R.id.tvObrabotkiVortex);
        tvRashodGolova = (TextView) findViewById(R.id.tvRashodGolova);
        tvStoimostKg = (TextView) findViewById(R.id.tvStoimostKg);
        tvKolichGigien = (TextView) findViewById(R.id.tvKolichGigien);
        tvObrabotki = (TextView) findViewById(R.id.tvObrabotki);
        tvSredstvo = (TextView) findViewById(R.id.tvSredstvo);
        tvSredstvoVortex = (TextView) findViewById(R.id.tvSredstvoVortex);
        etPrice = (EditText) findViewById(R.id.etPrice);
        etVes = (EditText) findViewById(R.id.etVes);
        etGolov = (EditText) findViewById(R.id.etGolov);
        etDay = (EditText) findViewById(R.id.etDay);
        etObrabotok = (EditText) findViewById(R.id.etObrabotok);
        etSredstvo = (EditText) findViewById(R.id.etSredstvo);

        stoimKgVotrex = getIntent().getExtras().getDouble("stoimKg");
        kolichGigienVotrex = getIntent().getExtras().getDouble("kolichGigien");
        stoimObrabotkiVotrex = getIntent().getExtras().getDouble("stoimObrabotki");
        dblRashodGolovaVotrex = getIntent().getExtras().getDouble("dblRashodGolova");
        strSredstvoVortex = getIntent().getExtras().getString("sredstvo");

        tvStoimostKgVortex.setText(String.valueOf(roundUp(stoimKgVotrex, 2)));
        tvKolichGigienVortex.setText(String.valueOf(roundUp(kolichGigienVotrex, 2)));
        tvObrabotkiVortex.setText(String.valueOf(roundUp(stoimObrabotkiVotrex, 2)));

        tvSredstvoVortex.setText(strSredstvoVortex);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.spinner, data1);
        adapter2.setDropDownViewResource(R.layout.spinner_dropdown_item);

        spinner3.setAdapter(adapter2);

        spinner3.setPrompt("% разведения в пенном стакане");

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                if (spinner3.getSelectedItemId() == 0)
                    dblRashodGolova = 0.00008 * 4;
                else if (spinner3.getSelectedItemId() == 1)
                    dblRashodGolova = 0.00012 * 4;
                else if (spinner3.getSelectedItemId() == 2)
                    dblRashodGolova = 0.00016 * 4;
                else if (spinner3.getSelectedItemId() == 3)
                    dblRashodGolova = 0.00024 * 4;
                else if (spinner3.getSelectedItemId() == 4)
                    dblRashodGolova = 0.00032 * 4;
                else if (spinner3.getSelectedItemId() == 5)
                    dblRashodGolova = 0.0008 * 4;

                String s = String.valueOf(roundUp(dblRashodGolova, 5));
                tvRashodGolova.setText(s);
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

    public void onClickIcon(View view) {

        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }

    public BigDecimal roundUp(double value, int digits) {
        return new BigDecimal("" + value).setScale(digits, BigDecimal.ROUND_HALF_UP);
    }

    public void onClickRaschet(View view) {
        if (etPrice.getText().length() == 0 || etVes.getText().length() == 0 || etGolov.getText().length() == 0
                || etDay.getText().length() == 0 || etObrabotok.getText().length() == 0 || dblRashodGolova == 0
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

        tvSredstvo.setText(etSredstvo.getText());

        double price = Double.parseDouble(etPrice.getText().toString());
        double ves = Double.parseDouble(etVes.getText().toString());
        double kolichGolov = Double.parseDouble(etGolov.getText().toString());
        double period = Double.parseDouble(etDay.getText().toString());
        double kolichObrabotok = Double.parseDouble(etObrabotok.getText().toString());

        stoimKg = price / ves;
        kolichGigien = dblRashodGolova * kolichGolov * period * kolichObrabotok;
        stoimObrabotki = dblRashodGolova * stoimKg;

        tvStoimostKg.setText(String.valueOf(roundUp(stoimKg, 2)));
        tvKolichGigien.setText(String.valueOf(roundUp(kolichGigien, 2)));
        tvObrabotki.setText(String.valueOf(roundUp(stoimObrabotki, 2)));

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
        Intent intent = ClickLeftMenu.getIntent(ActivityAPKGigienaVymeniDoDoeniaSravnenie2.this, item);
        startActivity(intent);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
