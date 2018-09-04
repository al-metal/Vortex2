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

import static com.vortex.vortex.Calculations.RoundUp.roundUp;

public class ApkMoySredstvaActivity2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String[] data = {"ВЫБРАТЬ СРЕДСТВО", "BIOTEC M", "BIOTEC C", "BIOTEC", "BIOTEC  SUPER", "KSILAN M", "KSILAN K", "KSILAN", "KSILAN SUPER"};
    double plotnost = 0;
    private TextView tvPlotnost;
    private TextView tvStoimKg;
    private TextView tvStoimL;
    private TextView tvStoimPromiv;
    private TextView tvStoimLitrSmes;

    private EditText etStoim;
    private EditText etKoncentrat;
    private EditText etVanna;
    private EditText etVes;

    double resStoimKg;
    double resStoimL;
    double resStoimPromiv;
    double resStoimSmesi;

    TableLayout tableL;
    Button btnRaschet;
    Button btnSavnenie;

    String nameVortex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apk_moy_sredstva2);

        setTitle("Расчет стоимости рабочего раствора");

        tableL = (TableLayout) findViewById(R.id.tableL);
        btnRaschet = (Button) findViewById(R.id.btnRaschet);
        btnSavnenie = (Button) findViewById(R.id.btnSavnenie);

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);

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
                tvPlotnost = (TextView) findViewById(R.id.tvPlotnost);
                if (pos == 0) {
                    return;
                } else if (pos == 1) {
                    plotnost = 1.2;
                } else if (pos == 2) {
                    plotnost = 1.22;
                } else if (pos == 3) {
                    plotnost = 1.25;
                } else if (pos == 4) {
                    plotnost = 1.23;
                } else if (pos == 5) {
                    plotnost = 1.16;
                } else if (pos == 6) {
                    plotnost = 1.2;
                } else if (pos == 7) {
                    plotnost = 1.22;
                } else if (pos == 8) {
                    plotnost = 1.28;
                }

                tvPlotnost.setText(String.valueOf(plotnost));
                nameVortex = spinner.getSelectedItem().toString();
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
        Intent intent = ClickLeftMenu.getIntent(ApkMoySredstvaActivity2.this, item);
        startActivity(intent);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onClickBtn(View view) {
        tvStoimKg = (TextView) findViewById(R.id.tvStoimKg);
        tvStoimL = (TextView) findViewById(R.id.tvStoimL);
        tvStoimPromiv = (TextView) findViewById(R.id.tvStoimPromiv);
        tvStoimLitrSmes = (TextView) findViewById(R.id.tvStoimLitrSmes);

        etStoim = (EditText) findViewById(R.id.etStoim);
        etKoncentrat = (EditText) findViewById(R.id.etKoncentrat);
        etVanna = (EditText) findViewById(R.id.etVanna);
        etVes = (EditText) findViewById(R.id.etVes);

        if (etVes.getText().length() == 0 || etVanna.getText().length() == 0 || etKoncentrat.getText().length() == 0 || etStoim.getText().length() == 0 || plotnost == 0) {
            Toast.makeText(getBaseContext(), "Заполните пожалуйста все данные", Toast.LENGTH_SHORT).show();
            return;
        }

        //Скрытие клавиатуры по нажатию кнопки
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(btnRaschet.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        int gray = Color.parseColor("#7B7979");
        btnRaschet.setBackgroundColor(gray);
        tableL.setVisibility(View.VISIBLE);
        btnSavnenie.setVisibility(View.VISIBLE);


        double stoimost = 0;
        double ves = 0;
        double koncentrat = 0;
        double vanna = 0;

        resStoimKg = 0;
        resStoimL = 0;
        resStoimPromiv = 0;
        resStoimSmesi = 0;

        stoimost = Double.parseDouble(etStoim.getText().toString());
        koncentrat = Double.parseDouble(etKoncentrat.getText().toString());
        vanna = Double.parseDouble(etVanna.getText().toString());
        ves = Double.parseDouble(etVes.getText().toString());

        resStoimKg = stoimost / ves;
        resStoimL = resStoimKg * plotnost;
        resStoimPromiv = (resStoimL * koncentrat * vanna) / 100;
        resStoimSmesi = resStoimPromiv / vanna;

        tvStoimKg.setText(String.valueOf(roundUp(resStoimKg, 2)));
        tvStoimL.setText(String.valueOf(roundUp(resStoimL, 2)));
        tvPlotnost.setText(String.valueOf(roundUp(plotnost, 2)));
        tvStoimLitrSmes.setText(String.valueOf(roundUp(resStoimSmesi, 2)));
        tvStoimPromiv.setText(String.valueOf(roundUp(resStoimPromiv, 2)));
    }

    public void onClickBtnSravnenie(View view) {
        if (resStoimKg == 0 || resStoimL == 0 || resStoimPromiv == 0 || resStoimSmesi == 0) {
            Toast.makeText(getBaseContext(), "Данные для сравнения еще не расчитаны", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(ApkMoySredstvaActivity2.this, ApkMoySredstvaSravnenieActivity2.class);
        intent.putExtra("resStoimKg", resStoimKg);
        intent.putExtra("resStoimL", resStoimL);
        intent.putExtra("resStoimPromiv", resStoimPromiv);
        intent.putExtra("resStoimSmesi", resStoimSmesi);
        intent.putExtra("resPlotnost", plotnost);
        intent.putExtra("nameVortex", nameVortex);
        startActivity(intent);
    }
}
