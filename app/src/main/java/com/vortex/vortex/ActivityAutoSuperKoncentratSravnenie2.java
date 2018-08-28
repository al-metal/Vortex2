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

public class ActivityAutoSuperKoncentratSravnenie2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    double dblPriceVortex;
    String strObjemVortex;
    double dblRazbavlenieVortex;
    double dblKolichestvoZapravokVortex;
    double dblStoimostZapravkiVortex;
    double dblStoimostMoykiVortex;
    double dblKooficient;
    double dblKolichestvoKanistr;
    double dblObjem;

    TextView tvCena2KanistrVortex;
    TextView tvRazbavlenieVortex;
    TextView tvKolichestvoZapravokVortex;
    TextView tvStoimostZapravokVortex;
    TextView tvStoimostMoykiVortex;

    TextView tvCena2Kanistr;
    TextView tvRazbavlenie;
    TextView tvKolichestvoZapravok;
    TextView tvStoimostZapravok;
    TextView tvStoimostMoyki;
    TextView tvObshStoim;
    TextView tvPoStoim;
    TextView tvObjemVortex;
    TextView tvObjem;

    EditText etPrice;
    EditText etRazbavlenie;
    Button btnRaschet;
    TableLayout tableL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_super_koncentrat_sravnenie2);

        setTitle("Сравнить с другим средством");

        btnRaschet = (Button) findViewById(R.id.btnRaschet);
        tableL = (TableLayout) findViewById(R.id.tableL);

        dblPriceVortex = getIntent().getExtras().getDouble("dblPrice");
        strObjemVortex = getIntent().getExtras().getString("Objem");
        dblRazbavlenieVortex = getIntent().getExtras().getDouble("dblRazbavlenie");
        dblKolichestvoZapravokVortex = getIntent().getExtras().getDouble("dblKolichestvoZapravok");
        dblStoimostZapravkiVortex = getIntent().getExtras().getDouble("dblStoimostZapravki");
        dblStoimostMoykiVortex = getIntent().getExtras().getDouble("dblStoimostMoyki");
        dblKooficient = getIntent().getExtras().getDouble("dblKooficent");
        dblKolichestvoKanistr = getIntent().getExtras().getDouble("dblKolichestvoKanistr");
        dblObjem = getIntent().getExtras().getDouble("dblObjem");
        dblObjem = dblObjem / 1000;

        tvCena2KanistrVortex = (TextView) findViewById(R.id.tvCena2KanistrVortex);
        tvObjemVortex = (TextView) findViewById(R.id.tvObjemVortex);
        tvRazbavlenieVortex = (TextView) findViewById(R.id.tvRazbavlenieVortex);
        tvKolichestvoZapravokVortex = (TextView) findViewById(R.id.tvKolichestvoZapravokVortex);
        tvStoimostZapravokVortex = (TextView) findViewById(R.id.tvStoimostZapravokVortex);
        tvStoimostMoykiVortex = (TextView) findViewById(R.id.tvStoimostMoykiVortex);
        tvObshStoim = (TextView) findViewById(R.id.tvObshStoim);
        tvPoStoim = (TextView) findViewById(R.id.tvPoStoim);
        tvObjem = (TextView) findViewById(R.id.tvObjem);

        tvCena2Kanistr = (TextView) findViewById(R.id.tvCena2Kanistr);
        tvRazbavlenie = (TextView) findViewById(R.id.tvRazbavlenie);
        tvKolichestvoZapravok = (TextView) findViewById(R.id.tvKolichestvoZapravok);
        tvStoimostZapravok = (TextView) findViewById(R.id.tvStoimostZapravok);
        tvStoimostMoyki = (TextView) findViewById(R.id.tvStoimostMoyki);

        tvCena2KanistrVortex.setText(String.valueOf(roundUp(dblPriceVortex, 2)));
        tvObjemVortex.setText(String.valueOf(strObjemVortex));
        tvRazbavlenieVortex.setText(String.valueOf(roundUp(dblRazbavlenieVortex, 2)));
        tvKolichestvoZapravokVortex.setText(String.valueOf(roundUp(dblKolichestvoZapravokVortex, 2)));
        tvStoimostZapravokVortex.setText(String.valueOf(roundUp(dblStoimostZapravkiVortex, 2)));
        tvStoimostMoykiVortex.setText(String.valueOf(roundUp(dblStoimostMoykiVortex, 2)));

        etPrice = (EditText) findViewById(R.id.etPrice);
        etRazbavlenie = (EditText) findViewById(R.id.etRazbavlenie);

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

    public void onClickRaschet(View view) {
        if (etPrice.getText().length() == 0 || etRazbavlenie.getText().length() == 0) {
            Toast.makeText(getBaseContext(), "Заполните пожалуйста все данные", Toast.LENGTH_SHORT).show();
            return;
        }

        //Скрытие клавиатуры по нажатию кнопки
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(btnRaschet.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        tableL.setVisibility(View.VISIBLE);
        int gray = Color.parseColor("#7B7979");
        btnRaschet.setBackgroundColor(gray);

        double dblPriceKanistr = Double.parseDouble(etPrice.getText().toString());
        double dblRazbavlenie = Double.parseDouble(etRazbavlenie.getText().toString());

        double dblPrice2Kanistr = dblPriceKanistr * dblKolichestvoKanistr;
        double dblKolichestvoZapravok = (dblObjem * 1000) / dblRazbavlenie;
        double dblStoimostZapravki = dblPrice2Kanistr / dblKolichestvoZapravok;
        double dblStoimostMoyki = dblStoimostZapravki / dblKooficient;

        if (dblObjem == 35) {
            dblObjem = 40;
        } else {
            dblObjem = 400;
        }

        tvCena2Kanistr.setText(String.valueOf(roundUp(dblPrice2Kanistr, 2)));
        tvObjem.setText(String.valueOf(roundUp(dblObjem, 2)));
        tvRazbavlenie.setText(String.valueOf(roundUp(dblRazbavlenie, 2)));
        tvKolichestvoZapravok.setText(String.valueOf(roundUp(dblKolichestvoZapravok, 2)));
        tvStoimostZapravok.setText(String.valueOf(roundUp(dblStoimostZapravki, 2)));
        tvStoimostMoyki.setText(String.valueOf(roundUp(dblStoimostMoyki, 2)));

        double obshayaStoim = (dblPrice2Kanistr - dblPriceVortex) / dblPrice2Kanistr * 100;
        double poStoimosti = (dblStoimostMoyki - dblStoimostMoykiVortex) / dblStoimostMoyki * 100;

        tvObshStoim.setText(String.valueOf(roundUp(obshayaStoim, 2)));
        tvPoStoim.setText(String.valueOf(roundUp(poStoimosti, 2)));

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Intent intent = ClickLeftMenu.getIntent(ActivityAutoSuperKoncentratSravnenie2.this, item);
        startActivity(intent);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
