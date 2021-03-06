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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

import static com.vortex.vortex.Calculations.RoundUp.roundUp;

public class ActivityPishePromSravnenie2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    double PriceLitr = 0;
    double StoimostRastvora = 0;
    double RashodSrdstvaMoyka = 0;
    double RashodSredstvaMes = 0;
    double PriceSredstvaVortex = 0;
    double PriceSredstva = 0;
    double vygoda = 0;
    String SredstvoVortex;

    TextView tvPriceLitrVortex;
    TextView tvStoimostRastvoraVortex;
    TextView tvRashodSrdstvaMoykaVortex;
    TextView tvRashodSredstvaMesVortex;
    TextView tvPriceSredstvaVortex;
    TextView tvPriceLitr;
    TextView tvStoimostRastvora;
    TextView tvRashodSrdstvaMoyka;
    TextView tvRashodSredstvaMes;
    TextView tvPriceSredstva;
    TextView tvVygoda;
    TextView tvSredstvo;
    TextView tvSredstvoVortex;

    EditText etPlotnost;
    EditText etPrice;
    EditText etKoncentratRastvor;
    EditText etObjemRastvor;
    EditText etOperacSutki;
    EditText etDay;
    EditText etSredstvo;

    TableLayout tableRaschet;
    Button btnRaschet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pishe_prom_sravnenie2);

        setTitle("Сравнить с другим средством");

        tvPriceLitrVortex = (TextView) findViewById(R.id.tvPriceLitrVortex);
        tvStoimostRastvoraVortex = (TextView) findViewById(R.id.tvStoimostRastvoraVortex);
        tvRashodSrdstvaMoykaVortex = (TextView) findViewById(R.id.tvRashodSrdstvaMoykaVortex);
        tvRashodSredstvaMesVortex = (TextView) findViewById(R.id.tvRashodSredstvaMesVortex);
        tvPriceSredstvaVortex = (TextView) findViewById(R.id.tvPriceSredstvaVortex);
        tvVygoda = (TextView) findViewById(R.id.tvVygoda);
        tvSredstvo = (TextView) findViewById(R.id.tvSredstvo);
        tvSredstvoVortex = (TextView) findViewById(R.id.tvSredstvoVortex);
        tableRaschet = (TableLayout) findViewById(R.id.tableRaschet);
        btnRaschet = (Button) findViewById(R.id.btnRaschet);

        PriceLitr = getIntent().getExtras().getDouble("PriceLitr");
        StoimostRastvora = getIntent().getExtras().getDouble("StoimostRastvora");
        RashodSrdstvaMoyka = getIntent().getExtras().getDouble("RashodSrdstvaMoyka");
        RashodSredstvaMes = getIntent().getExtras().getDouble("RashodSredstvaMes");
        PriceSredstvaVortex = getIntent().getExtras().getDouble("PriceSredstva");
        SredstvoVortex = getIntent().getExtras().getString("Sredstvo");

        tvPriceLitrVortex.setText(String.valueOf(roundUp(PriceLitr, 2)));
        tvStoimostRastvoraVortex.setText(String.valueOf(roundUp(StoimostRastvora, 2)));
        tvRashodSrdstvaMoykaVortex.setText(String.valueOf(roundUp(RashodSrdstvaMoyka, 2)));
        tvRashodSredstvaMesVortex.setText(String.valueOf(roundUp(RashodSredstvaMes, 2)));
        tvPriceSredstvaVortex.setText(String.valueOf(roundUp(PriceSredstvaVortex, 2)));

        etPlotnost = (EditText) findViewById(R.id.etPlotnost);
        etPrice = (EditText) findViewById(R.id.etPrice);
        etKoncentratRastvor = (EditText) findViewById(R.id.etKoncentratRastvor);
        etObjemRastvor = (EditText) findViewById(R.id.etObjemRastvor);
        etOperacSutki = (EditText) findViewById(R.id.etOperacSutki);
        etDay = (EditText) findViewById(R.id.etDay);
        etSredstvo = (EditText) findViewById(R.id.etSredstvo);

        tvPriceLitr = (TextView) findViewById(R.id.tvPriceLitr);
        tvStoimostRastvora = (TextView) findViewById(R.id.tvStoimostRastvora);
        tvRashodSrdstvaMoyka = (TextView) findViewById(R.id.tvRashodSrdstvaMoyka);
        tvRashodSredstvaMes = (TextView) findViewById(R.id.tvRashodSredstvaMes);
        tvPriceSredstva = (TextView) findViewById(R.id.tvPriceSredstva);

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

    public void onClick(View view) {
        if (etPlotnost.getText().length() == 0 || etPrice.getText().length() == 0 || etKoncentratRastvor.getText().length() == 0 || etObjemRastvor.getText().length() == 0
                || etOperacSutki.getText().length() == 0 || etDay.getText().length() == 0 || etSredstvo.getText().length() == 0) {
            Toast.makeText(getBaseContext(), "Заполните пожалуйста все данные", Toast.LENGTH_SHORT).show();
            return;
        }

        //Скрытие клавиатуры по нажатию кнопки
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(btnRaschet.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        double dblPrice = Double.parseDouble(etPrice.getText().toString());
        double dblplotnost = Double.parseDouble(etPlotnost.getText().toString());
        double dblKoncentratRastvor = Double.parseDouble(etKoncentratRastvor.getText().toString());
        double dblObjemRastvor = Double.parseDouble(etObjemRastvor.getText().toString());
        double dblOperacSutki = Double.parseDouble(etOperacSutki.getText().toString());
        double dblDay = Double.parseDouble(etDay.getText().toString());

        tvSredstvoVortex.setText(SredstvoVortex);
        tvSredstvo.setText(etSredstvo.getText());

        PriceLitr = dblPrice * dblplotnost;
        StoimostRastvora = dblKoncentratRastvor * dblObjemRastvor * PriceLitr / 100;
        RashodSrdstvaMoyka = dblKoncentratRastvor * dblObjemRastvor / 100;
        RashodSredstvaMes = RashodSrdstvaMoyka * dblOperacSutki * dblDay;
        PriceSredstva = PriceLitr * RashodSredstvaMes;

        tvPriceLitr.setText(String.valueOf(roundUp(PriceLitr, 2)));
        tvStoimostRastvora.setText(String.valueOf(roundUp(StoimostRastvora, 2)));
        tvRashodSrdstvaMoyka.setText(String.valueOf(roundUp(RashodSrdstvaMoyka, 2)));
        tvRashodSredstvaMes.setText(String.valueOf(roundUp(RashodSredstvaMes, 2)));
        tvPriceSredstva.setText(String.valueOf(roundUp(PriceSredstva, 2)));

        vygoda = PriceSredstva - PriceSredstvaVortex;
        tvVygoda.setText(String.valueOf(roundUp(vygoda, 2)));

        tableRaschet.setVisibility(View.VISIBLE);
        int gray = Color.parseColor("#7B7979");
        btnRaschet.setBackgroundColor(gray);
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
        Intent intent = ClickLeftMenu.getIntent(ActivityPishePromSravnenie2.this, item);
        startActivity(intent);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
