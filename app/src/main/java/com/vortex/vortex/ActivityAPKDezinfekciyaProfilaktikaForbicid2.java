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

public class ActivityAPKDezinfekciyaProfilaktikaForbicid2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String[] data = {"Гладкая", "Шероховатая"};

    Spinner spinner5;
    TextView tvKoncentraciya;
    TextView tvRashod;
    TextView tvEkspoziciya;
    TextView tvStoimostKg;
    TextView tvKolichForbicid;
    TextView tvStoimostObrabotki;

    EditText etPloshad;
    EditText etPrice;
    EditText etVes;

    double dblStoimostKg;
    double dblKolichForbicid;
    double dblStoimostObrabotki;
    double dblKoncentraciya;
    double dblRashod;
    double dblEkspoziciya;

    TableLayout tableL;
    Button btnRaschet;
    Button btnSravnenie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apkdezinfekciya_profilaktika_forbicid2);

        setTitle("Форбицид профилактическая дезинфекция");

        tableL = (TableLayout) findViewById(R.id.tableL);
        btnRaschet = (Button) findViewById(R.id.btnRaschet);
        btnSravnenie = (Button) findViewById(R.id.btnSravnenie);

        spinner5 = (Spinner) findViewById(R.id.spinner5);
        tvKoncentraciya = (TextView) findViewById(R.id.tvKoncentraciya);
        tvRashod = (TextView) findViewById(R.id.tvRashod);
        tvEkspoziciya = (TextView) findViewById(R.id.tvEkspoziciya);

        tvStoimostKg = (TextView) findViewById(R.id.tvStoimostKg);
        tvKolichForbicid = (TextView) findViewById(R.id.tvKolichForbicid);
        tvStoimostObrabotki = (TextView) findViewById(R.id.tvStoimostObrabotki);

        etPloshad = (EditText) findViewById(R.id.etPloshad);
        etPrice = (EditText) findViewById(R.id.etPrice);
        etVes = (EditText) findViewById(R.id.etVes);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner, data);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        spinner5.setAdapter(adapter);

        spinner5.setPrompt("Выберите вид поверхности");

        spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                if (pos == 0) {
                    tvKoncentraciya.setText("0.25");
                    tvRashod.setText("0.25");
                    tvEkspoziciya.setText("20");
                } else {
                    tvKoncentraciya.setText("0.25");
                    tvRashod.setText("0.35");
                    tvEkspoziciya.setText("30");
                }
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

    public void onClickRaschet(View view) {
        if (etPloshad.getText().length() == 0 || etPrice.getText().length() == 0 || etVes.getText().length() == 0
                || tvKoncentraciya.getText().length() == 0 || tvRashod.getText().length() == 0 || tvEkspoziciya.getText().length() == 0) {
            Toast.makeText(getBaseContext(), "Заполните пожалуйста все данные", Toast.LENGTH_SHORT).show();
            return;
        }

        //Скрытие клавиатуры по нажатию кнопки
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(btnRaschet.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        tableL.setVisibility(View.VISIBLE);
        btnSravnenie.setVisibility(View.VISIBLE);
        int gray = Color.parseColor("#7B7979");
        btnRaschet.setBackgroundColor(gray);

        double dblPloshad = Double.parseDouble(etPloshad.getText().toString());
        double dblPrice = Double.parseDouble(etPrice.getText().toString());
        double dblVes = Double.parseDouble(etVes.getText().toString());
        dblKoncentraciya = Double.parseDouble(tvKoncentraciya.getText().toString());
        dblRashod = Double.parseDouble(tvRashod.getText().toString());
        dblEkspoziciya = Double.parseDouble(tvEkspoziciya.getText().toString());

        dblStoimostKg = dblPrice / dblVes;
        dblKolichForbicid = ((dblPloshad * dblRashod) * dblKoncentraciya) / 100;
        dblStoimostObrabotki = dblStoimostKg * dblKolichForbicid;

        tvStoimostKg.setText(String.valueOf(roundUp(dblStoimostKg, 2)));
        tvKolichForbicid.setText(String.valueOf(roundUp(dblKolichForbicid, 2)));
        tvStoimostObrabotki.setText(String.valueOf(roundUp(dblStoimostObrabotki, 2)));


    }

    public void onClickSravnenie(View view) {
        if (dblKoncentraciya == 0 || dblRashod == 0 || dblEkspoziciya == 0
                || dblStoimostKg == 0 || dblKolichForbicid == 0 || dblStoimostObrabotki == 0) {
            Toast.makeText(getBaseContext(), "Данные для сравнения еще не расчитаны", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(ActivityAPKDezinfekciyaProfilaktikaForbicid2.this, ActivityAPKDezinfekciyaProfilaktikaForbicidSravnenie2.class);
        intent.putExtra("Koncentraciya", dblKoncentraciya);
        intent.putExtra("Rashod", dblRashod);
        intent.putExtra("Ekspoziciya", dblEkspoziciya);
        intent.putExtra("StoimostKg", dblStoimostKg);
        intent.putExtra("KolichForbicid", dblKolichForbicid);
        intent.putExtra("StoimostObrabotki", dblStoimostObrabotki);
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
        Intent intent = ClickLeftMenu.getIntent(ActivityAPKDezinfekciyaProfilaktikaForbicid2.this, item);
        startActivity(intent);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
