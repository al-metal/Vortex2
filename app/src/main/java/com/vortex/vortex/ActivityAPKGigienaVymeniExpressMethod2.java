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

import static com.vortex.vortex.Calculations.RoundUp.roundUp;

public class ActivityAPKGigienaVymeniExpressMethod2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    EditText etPrice;
    EditText etVes;
    EditText etKolichGolov;
    TextView tvStoimKg;
    TextView tvKolichestvo;
    TextView tvStoimostObrabotkiVsego;
    TextView tvStoimostObrabotkiGolovy;

    double dblStoimKg;
    double dblKolichestvo;
    double dblStoimostVsego;
    double dblStoimostGolovy;

    TableLayout tableL;
    Button btnSravnenie;
    Button btnRaschet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apkgigiena_vymeni_express_method2);

        setTitle("Экспресс-метод диагностики субклинического мастита у КРС");

        tableL = (TableLayout) findViewById(R.id.tableL);
        btnSravnenie = (Button) findViewById(R.id.btnSravnenie);
        btnRaschet = (Button) findViewById(R.id.btnRaschet);

        etPrice = (EditText) findViewById(R.id.etPrice);
        etVes = (EditText) findViewById(R.id.etVes);
        etKolichGolov = (EditText) findViewById(R.id.etKolichGolov);

        tvStoimKg = (TextView) findViewById(R.id.tvStoimKg);
        tvKolichestvo = (TextView) findViewById(R.id.tvKolichestvo);
        tvStoimostObrabotkiVsego = (TextView) findViewById(R.id.tvStoimostObrabotkiVsego);
        tvStoimostObrabotkiGolovy = (TextView) findViewById(R.id.tvStoimostObrabotkiGolovy);

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

        if (etPrice.getText().length() == 0 || etVes.getText().length() == 0 || etKolichGolov.getText().length() == 0) {
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

        double price = Double.parseDouble(etPrice.getText().toString());
        double ves = Double.parseDouble(etVes.getText().toString());
        double kolichGolov = Double.parseDouble(etKolichGolov.getText().toString());

        dblStoimKg = price / ves;
        dblKolichestvo = kolichGolov * 0.008;
        dblStoimostVsego = 0.008 * dblStoimKg * kolichGolov;
        dblStoimostGolovy = dblStoimostVsego / kolichGolov;

        tvStoimKg.setText(String.valueOf(roundUp(dblStoimKg, 2)));
        tvKolichestvo.setText(String.valueOf(roundUp(dblKolichestvo, 2)));
        tvStoimostObrabotkiVsego.setText(String.valueOf(roundUp(dblStoimostVsego, 2)));
        tvStoimostObrabotkiGolovy.setText(String.valueOf(roundUp(dblStoimostGolovy, 2)));
    }

    public void onClickSravnenie(View view) {
        if (dblStoimKg == 0 || dblKolichestvo == 0 || dblStoimostVsego == 0 || dblStoimostGolovy == 0) {
            Toast.makeText(getBaseContext(), "Данные для сравнения еще не расчитаны", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(ActivityAPKGigienaVymeniExpressMethod2.this, ActivityAPKGigienaVymeniExpressMethodSravnenie2.class);
        intent.putExtra("stoimKg", dblStoimKg);
        intent.putExtra("kolichestvo", dblKolichestvo);
        intent.putExtra("stoimostVsego", dblStoimostVsego);
        intent.putExtra("stoimostGolovy", dblStoimostGolovy);
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
        Intent intent = ClickLeftMenu.getIntent(ActivityAPKGigienaVymeniExpressMethod2.this, item);
        startActivity(intent);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
