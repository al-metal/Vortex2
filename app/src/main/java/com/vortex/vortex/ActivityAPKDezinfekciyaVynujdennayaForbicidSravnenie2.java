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

public class ActivityAPKDezinfekciyaVynujdennayaForbicidSravnenie2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    double dblKoncentraciya = 0;
    double dblRashod = 0;
    double dblEkspoziciya = 0;
    double dblStoimostKg = 0;
    double dblKolichForbicid = 0;
    double dblStoimostObrabotki = 0;

    TextView tvStoimostKgVortex;
    TextView tvKolichestvoVortex;
    TextView tvStoimostObrabotkiVortex;
    TextView tvStoimostKg;
    TextView tvKolichestvo;
    TextView tvStoimostObrabotki;
    TextView tvNameSredstvo;

    EditText etPloshad;
    EditText etKoncentraciya;
    EditText etRashod;
    EditText etPrice;
    EditText etVes;
    EditText etName;

    TableLayout tableL;
    Button btnRaschet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apkdezinfekciya_vynujdennaya_forbicid_sravnenie2);

        setTitle("Сравнить с другим средством");

        tableL = (TableLayout) findViewById(R.id.tableL);
        btnRaschet = (Button) findViewById(R.id.btnRaschet);

        dblKoncentraciya = getIntent().getExtras().getDouble("Koncentraciya");
        dblRashod = getIntent().getExtras().getDouble("Rashod");
        dblEkspoziciya = getIntent().getExtras().getDouble("Ekspoziciya");
        dblStoimostKg = getIntent().getExtras().getDouble("StoimostKg");
        dblKolichForbicid = getIntent().getExtras().getDouble("KolichForbicid");
        dblStoimostObrabotki = getIntent().getExtras().getDouble("StoimostObrabotki");

        tvStoimostKgVortex = (TextView) findViewById(R.id.tvStoimostKgVortex);
        tvKolichestvoVortex = (TextView) findViewById(R.id.tvKolichestvoVortex);
        tvStoimostObrabotkiVortex = (TextView) findViewById(R.id.tvStoimostObrabotkiVortex);
        tvStoimostKg = (TextView) findViewById(R.id.tvStoimostKg);
        tvKolichestvo = (TextView) findViewById(R.id.tvKolichestvo);
        tvStoimostObrabotki = (TextView) findViewById(R.id.tvStoimostObrabotki);
        tvNameSredstvo = (TextView) findViewById(R.id.tvNameSredstvo);

        etPloshad = (EditText) findViewById(R.id.etPloshad);
        etKoncentraciya = (EditText) findViewById(R.id.etKoncentraciya);
        etRashod = (EditText) findViewById(R.id.etRashod);
        etPrice = (EditText) findViewById(R.id.etPrice);
        etVes = (EditText) findViewById(R.id.etVes);
        etName = (EditText) findViewById(R.id.etName);

        tvStoimostKgVortex.setText(String.valueOf(roundUp(dblStoimostKg, 2)));
        tvKolichestvoVortex.setText(String.valueOf(roundUp(dblKolichForbicid, 2)));
        tvStoimostObrabotkiVortex.setText(String.valueOf(roundUp(dblStoimostObrabotki, 2)));

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

    public void onClick(View view) {
        if (etPloshad.getText().length() == 0 || etPrice.getText().length() == 0 || etVes.getText().length() == 0
                || etKoncentraciya.getText().length() == 0 || etRashod.getText().length() == 0 || etName.getText().length() == 0) {
            Toast.makeText(getBaseContext(), "Заполните пожалуйста все данные", Toast.LENGTH_SHORT).show();
            return;
        }

        //Скрытие клавиатуры по нажатию кнопки
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(btnRaschet.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        double dblPloshad = Double.parseDouble(etPloshad.getText().toString());
        double dblPrice = Double.parseDouble(etPrice.getText().toString());
        double dblVes = Double.parseDouble(etVes.getText().toString());
        double dblKoncentraciyaSravnenie = Double.parseDouble(etKoncentraciya.getText().toString());
        double dblRashodSravnenie = Double.parseDouble(etRashod.getText().toString());

        double dblStoimostKgSravnenie = dblPrice / dblVes;
        double dblKolichForbicidSravnenie = ((dblPloshad * dblRashodSravnenie) * dblKoncentraciyaSravnenie) / 100;
        double dblStoimostObrabotkiSravnenie = dblStoimostKgSravnenie * dblKolichForbicidSravnenie;

        tvStoimostKg.setText(String.valueOf(roundUp(dblStoimostKgSravnenie, 2)));
        tvKolichestvo.setText(String.valueOf(roundUp(dblKolichForbicidSravnenie, 2)));
        tvStoimostObrabotki.setText(String.valueOf(roundUp(dblStoimostObrabotkiSravnenie, 2)));
        tvNameSredstvo.setText(etName.getText());

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
        Intent intent = ClickLeftMenu.getIntent(ActivityAPKDezinfekciyaVynujdennayaForbicidSravnenie2.this, item);
        startActivity(intent);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
