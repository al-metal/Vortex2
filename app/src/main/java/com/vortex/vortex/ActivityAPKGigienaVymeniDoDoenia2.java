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

public class ActivityAPKGigienaVymeniDoDoenia2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String[] data = {"ВЫБРАТЬ СРЕДСТВО", "PRIOLIT", "VIOLIT", "FITOLIT", "AGALIT 50", "AGALIT"};
    String[] data2 = {"0", "10", "15", "20", "30", "40", "100"};

    Spinner spinner;
    Spinner spinner2;
    TextView tvRashodGolova;
    double dblRashodGolova = 0;
    EditText etPrice;
    EditText etVes;
    EditText etKolichGolov;
    EditText etDay;
    EditText etKolichObrabotok;

    TextView tvSoimostKg;
    TextView tvKolichGigien;
    TextView tvStoimPromyvki;

    double stoimKg;
    double kolichGigien;
    double stoimObrabotki;
    boolean bollSpinner1;
    boolean bollSpinner2;

    TableLayout tableL;
    Button btnSravnenie;
    Button btnRaschet;

    String strSredstvo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apkgigiena_vymeni_do_doenia2);

        setTitle("Гигиена вымени до доения");

        tableL = (TableLayout) findViewById(R.id.tableL);
        btnSravnenie = (Button) findViewById(R.id.btnSravnenie);
        btnRaschet = (Button) findViewById(R.id.btnRaschet);

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        tvRashodGolova = (TextView) findViewById(R.id.tvRashodGolova);
        etPrice = (EditText) findViewById(R.id.etPrice);
        etVes = (EditText) findViewById(R.id.etVes);
        etKolichGolov = (EditText) findViewById(R.id.etKolichGolov);
        etDay = (EditText) findViewById(R.id.etDay);
        etKolichObrabotok = (EditText) findViewById(R.id.etKolichObrabotok);

        tvSoimostKg = (TextView) findViewById(R.id.tvSoimostKg);
        tvKolichGigien = (TextView) findViewById(R.id.tvKolichGigien);
        tvStoimPromyvki = (TextView) findViewById(R.id.tvStoimPromyvki);

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
                if(pos == 0){
                    bollSpinner1 = false;
                    return;
                }
                if (pos == 5) {
                    spinner2.setSelection(6);
                    spinner2.setClickable(false);
                } else {
                    spinner2.setSelection(1);
                    spinner2.setClickable(true);
                }
                bollSpinner1 = true;
                strSredstvo = data[pos];
            }
        });

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.spinner, data2);
        adapter2.setDropDownViewResource(R.layout.spinner_dropdown_item);

        spinner2.setAdapter(adapter2);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                if (spinner2.getSelectedItemId() == 6) {
                    if(spinner.getSelectedItemId() != 0){
                        if (spinner.getSelectedItemId() == 5)
                            spinner2.setSelection(6);
                        else
                            spinner2.setSelection(5);
                    }
                }
                if (spinner2.getSelectedItemId() == 0){
                    bollSpinner2 = false;
                    return;
                }
                else if (spinner2.getSelectedItemId() == 1)
                    dblRashodGolova = 0.00008 * 4;
                else if (spinner2.getSelectedItemId() == 2)
                    dblRashodGolova = 0.00012 * 4;
                else if (spinner2.getSelectedItemId() == 3)
                    dblRashodGolova = 0.00016 * 4;
                else if (spinner2.getSelectedItemId() == 4)
                    dblRashodGolova = 0.00024 * 4;
                else if (spinner2.getSelectedItemId() == 5)
                    dblRashodGolova = 0.00032 * 4;
                else if (spinner2.getSelectedItemId() == 6)
                    dblRashodGolova = 0.0008 * 4;

                String s = String.valueOf(roundUp(dblRashodGolova, 5));
                tvRashodGolova.setText(s);
                bollSpinner2 = true;
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
        Intent intent = ClickLeftMenu.getIntent(ActivityAPKGigienaVymeniDoDoenia2.this, item);
        startActivity(intent);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onClickWebSite(View view) {
        Intent intent = ClickLeftMenu.getIntentWebSite();
        startActivity(intent);
    }

    public BigDecimal roundUp(double value, int digits) {
        return new BigDecimal("" + value).setScale(digits, BigDecimal.ROUND_HALF_UP);
    }

    public void onClickRaschet(View view) {
        if(etPrice.getText().length() == 0 || etVes.getText().length() == 0 || etKolichGolov.getText().length() == 0
                || etDay.getText().length() == 0 || etKolichObrabotok.getText().length() == 0 || dblRashodGolova == 0
                || !bollSpinner1 || !bollSpinner2){
            Toast.makeText(getBaseContext(), "Заполните пожалуйста все данные", Toast.LENGTH_SHORT).show();
            return;
        }

        //Скрытие клавиатуры по нажатию кнопки
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(btnRaschet.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        double price = Double.parseDouble(etPrice.getText().toString());
        double ves = Double.parseDouble(etVes.getText().toString());
        double kolichGolov = Double.parseDouble(etKolichGolov.getText().toString());
        double period = Double.parseDouble(etDay.getText().toString());
        double kolichObrabotok = Double.parseDouble(etKolichObrabotok.getText().toString());

        stoimKg = price / ves;
        kolichGigien = dblRashodGolova*kolichGolov*period*kolichObrabotok;
        stoimObrabotki = dblRashodGolova * stoimKg;

        tvSoimostKg.setText(String.valueOf(roundUp(stoimKg, 2)));
        tvKolichGigien.setText(String.valueOf(roundUp(kolichGigien, 2)));
        tvStoimPromyvki.setText(String.valueOf(roundUp(stoimObrabotki, 2)));

        tableL.setVisibility(View.VISIBLE);
        btnSravnenie.setVisibility(View.VISIBLE);
        int gray = Color.parseColor("#7B7979");
        btnRaschet.setBackgroundColor(gray);

    }

    public void onClickSravnenie(View view) {
        if(stoimKg ==0 || kolichGigien ==0 || stoimObrabotki ==0){
            Toast.makeText(getBaseContext(), "Данные для сравнения еще не расчитаны", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(ActivityAPKGigienaVymeniDoDoenia2.this, ActivityAPKGigienaVymeniDoDoeniaSravnenie2.class);
        intent.putExtra("stoimKg", stoimKg);
        intent.putExtra("kolichGigien", kolichGigien);
        intent.putExtra("stoimObrabotki", stoimObrabotki);
        intent.putExtra("dblRashodGolova", dblRashodGolova);
        intent.putExtra("sredstvo", strSredstvo);
        startActivity(intent);
    }
}
