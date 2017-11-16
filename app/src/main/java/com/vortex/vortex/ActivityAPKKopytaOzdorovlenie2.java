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

public class ActivityAPKKopytaOzdorovlenie2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView tvTrebuemVann;
    TextView tvTrebuemVann2;
    TextView tvTrebuemDesimix1;
    TextView tvTrebuemDesimix2;
    TextView tvTrebuemKuporos1;
    TextView tvTrebuemKuporos2;
    TextView tvVsegoDesimix;
    TextView tvVsegoKuporos;

    EditText etStado;
    EditText etDesimix1;
    EditText etKuporos1;
    EditText etDesimix2;
    EditText etKuporos2;

    Button btnRaschet;
    TableLayout tableL;

    double vanna = 200;
    double d5 = 36;
    double d13 = 42;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apkkopyta_ozdorovlenie2);

        setTitle("Программа «Оздоровление»");

        btnRaschet = (Button) findViewById(R.id.btnRaschet);
        tableL = (TableLayout) findViewById(R.id.tableL);

        tvTrebuemVann = (TextView) findViewById(R.id.tvTrebuemVann);
        tvTrebuemVann2 = (TextView) findViewById(R.id.tvTrebuemVann2);
        tvTrebuemDesimix1 = (TextView) findViewById(R.id.tvTrebuemDesimix1);
        tvTrebuemDesimix2 = (TextView) findViewById(R.id.tvTrebuemDesimix2);
        tvTrebuemKuporos1 = (TextView) findViewById(R.id.tvTrebuemKuporos1);
        tvTrebuemKuporos2 = (TextView) findViewById(R.id.tvTrebuemKuporos2);
        tvVsegoDesimix = (TextView) findViewById(R.id.tvVsegoDesimix);
        tvVsegoKuporos = (TextView) findViewById(R.id.tvVsegoKuporos);

        etStado = (EditText) findViewById(R.id.etStado);
        etDesimix1 = (EditText) findViewById(R.id.etDesimix1);
        etKuporos1 = (EditText) findViewById(R.id.etKuporos1);
        etDesimix2 = (EditText) findViewById(R.id.etDesimix2);
        etKuporos2 = (EditText) findViewById(R.id.etKuporos2);

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
        if (etStado.getText().length() == 0 || etDesimix1.getText().length() == 0 || etKuporos1.getText().length() == 0
                || etDesimix2.getText().length() == 0 || etKuporos2.getText().length() == 0) {
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

        double stado = Double.parseDouble(etStado.getText().toString());
        double dblDesimix1 = Double.parseDouble(etDesimix1.getText().toString());
        double dblKuporos1 = Double.parseDouble(etKuporos1.getText().toString());
        double dblDesimix2 = Double.parseDouble(etDesimix2.getText().toString());
        double dblKuporos2 = Double.parseDouble(etKuporos2.getText().toString());

        double percentDesimix1 = (vanna * dblDesimix1) / 100;
        double percentKuporos1 = (vanna * dblKuporos1) / 100;
        double kolichestvoTrebuemmVann = (stado * d5) / 500;
        double kolichestvoTrebuemmDesimix1 = percentDesimix1 * kolichestvoTrebuemmVann;
        double kolichestvoTrebuemmKuporos1 = percentKuporos1 * kolichestvoTrebuemmVann;

        double percentDesimix2 = (vanna * dblDesimix2) / 100;
        double percentKuporos2 = (vanna * dblKuporos2) / 100;
        double kolichestvoTrebuemmVann2 = (stado * d13) / 500;
        double kolichestvoTrebuemmDesimix2 = percentDesimix2 * kolichestvoTrebuemmVann2;
        double kolichestvoTrebuemmKuporos2 = percentKuporos2 * kolichestvoTrebuemmVann2;

        double itogDesimix = kolichestvoTrebuemmDesimix1 + kolichestvoTrebuemmDesimix2;
        double itogKuporos = kolichestvoTrebuemmKuporos1 + kolichestvoTrebuemmKuporos2;

        tvTrebuemVann.setText(String.valueOf(roundUp(kolichestvoTrebuemmVann, 2)));
        tvTrebuemVann2.setText(String.valueOf(roundUp(kolichestvoTrebuemmVann2, 2)));
        tvTrebuemDesimix1.setText(String.valueOf(roundUp(kolichestvoTrebuemmDesimix1, 2)));
        tvTrebuemDesimix2.setText(String.valueOf(roundUp(kolichestvoTrebuemmDesimix2, 2)));
        tvTrebuemKuporos1.setText(String.valueOf(roundUp(kolichestvoTrebuemmKuporos1, 2)));
        tvTrebuemKuporos2.setText(String.valueOf(roundUp(kolichestvoTrebuemmKuporos2, 2)));
        tvVsegoDesimix.setText(String.valueOf(roundUp(itogDesimix, 2)));
        tvVsegoKuporos.setText(String.valueOf(roundUp(itogKuporos, 2)));

    }

    public BigDecimal roundUp(double value, int digits) {
        return new BigDecimal("" + value).setScale(digits, BigDecimal.ROUND_HALF_UP);
    }

    public void onClickVanna(View view) {
        Toast.makeText(getBaseContext(), "Проходимость составляет 500 голов через 200 литров", Toast.LENGTH_SHORT).show();
    }

    public void onCLicketap2(View view) {
        Toast.makeText(getBaseContext(), "первые 3 недели - использование 6 дней в неделю, 2 раза в день ", Toast.LENGTH_SHORT).show();
    }

    public void onCLicketap1(View view) {
        Toast.makeText(getBaseContext(), "следующие 7 недель - использование 3 дня в неделю, 2 раза в день ", Toast.LENGTH_SHORT).show();
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
        Intent intent = ClickLeftMenu.getIntent(ActivityAPKKopytaOzdorovlenie2.this, item);
        startActivity(intent);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onClickWebSite(View view) {
        Intent intent = ClickLeftMenu.getIntentWebSite();
        startActivity(intent);
    }
}
