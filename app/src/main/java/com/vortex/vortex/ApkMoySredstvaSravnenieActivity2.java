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

public class ApkMoySredstvaSravnenieActivity2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    double resStoimKgVotrex = 0;
    double resStoimLVotrex = 0;
    double resStoimPromivVotrex = 0;
    double resStoimSmesiVotrex = 0;
    double resPlotnostVotrex = 0;

    private EditText etStoim;
    private EditText etKoncentrat;
    private EditText etVanna;
    private EditText etName;
    private EditText etVes;
    private EditText etPlotnost;

    private TextView tvresPlotnostVortex;
    private TextView tvresStoimKgtVortex;
    private TextView tvresStoimLVortex;
    private TextView tvresStoimPromivtVortex;
    private TextView tvresStoimSmesitVortex;

    private TextView tvStoimKgSrav;
    private TextView tvStoimLSrav;
    private TextView tvStoimPromivSrav;
    private TextView tvStoimSmesiSrav;
    private TextView tvPlotnostSrav;
    private TextView tvSravnenie;
    private TextView tvVortex;

    double resStoimKg;
    double resStoimL;
    double resStoimPromiv;
    double resStoimSmesi;
    String nameVortex;

    Button btnRaschet;
    TableLayout tableL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apk_moy_sredstva_sravnenie2);

        setTitle("Сравнить с другим средством");

        btnRaschet = (Button) findViewById(R.id.btnRaschet);
        tableL = (TableLayout) findViewById(R.id.tableL);

        resPlotnostVotrex = getIntent().getExtras().getDouble("resPlotnost");
        resStoimKgVotrex = getIntent().getExtras().getDouble("resStoimKg");
        resStoimLVotrex = getIntent().getExtras().getDouble("resStoimL");
        resStoimPromivVotrex = getIntent().getExtras().getDouble("resStoimPromiv");
        resStoimSmesiVotrex = getIntent().getExtras().getDouble("resStoimSmesi");
        nameVortex = getIntent().getExtras().getString("nameVortex");

        etStoim = (EditText) findViewById(R.id.etStoim);
        etName = (EditText) findViewById(R.id.etName);
        etKoncentrat = (EditText) findViewById(R.id.etKoncentrat);
        etVanna = (EditText) findViewById(R.id.etVanna);
        etVes = (EditText) findViewById(R.id.etVes);
        etPlotnost = (EditText) findViewById(R.id.etPlotnost);


        tvresPlotnostVortex = (TextView) findViewById(R.id.tvPlotnostVortex);
        tvresStoimKgtVortex = (TextView) findViewById(R.id.tvStoimKgVortex);
        tvresStoimLVortex = (TextView) findViewById(R.id.tvStoimLVortex);
        tvresStoimPromivtVortex = (TextView) findViewById(R.id.tvStoimPromivVortex);
        tvresStoimSmesitVortex = (TextView) findViewById(R.id.tvLitrSmesiVortex);
        tvSravnenie = (TextView) findViewById(R.id.tvSravnenie);
        tvVortex = (TextView) findViewById(R.id.tvVortex);

        tvresPlotnostVortex.setText(String.valueOf(roundUp(resPlotnostVotrex, 2)));
        tvresStoimKgtVortex.setText(String.valueOf(roundUp(resStoimKgVotrex, 2)));
        tvresStoimLVortex.setText(String.valueOf(roundUp(resStoimLVotrex, 2)));
        tvresStoimPromivtVortex.setText(String.valueOf(roundUp(resStoimPromivVotrex, 2)));
        tvresStoimSmesitVortex.setText(String.valueOf(roundUp(resStoimSmesiVotrex, 2)));

        resStoimKg = 0;
        resStoimL = 0;
        resStoimPromiv = 0;
        resStoimSmesi = 0;

        tvVortex.setText(nameVortex);

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

    public void onClickApkRes(View view) {

        if (etName.getText().length() == 0 || etStoim.getText().length() == 0 || etKoncentrat.getText().length() == 0 || etVanna.getText().length() == 0 || etVes.getText().length() == 0
                || etPlotnost.getText().length() == 0) {
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

        String name;
        double stoimost = 0;
        double ves = 0;
        double koncentrat = 0;
        double vanna = 0;
        double plotnost = 0;

        etStoim = (EditText) findViewById(R.id.etStoim);
        etKoncentrat = (EditText) findViewById(R.id.etKoncentrat);
        etVanna = (EditText) findViewById(R.id.etVanna);
        etVes = (EditText) findViewById(R.id.etVes);
        etPlotnost = (EditText) findViewById(R.id.etPlotnost);

        tvStoimKgSrav = (TextView) findViewById(R.id.tvStoimKgSravnenie);
        tvStoimLSrav = (TextView) findViewById(R.id.tvStoimLSravnenie);
        tvStoimPromivSrav = (TextView) findViewById(R.id.tvStoimPromivSravnenie);
        tvStoimSmesiSrav = (TextView) findViewById(R.id.tvLitrSmesiSravnenie);
        tvPlotnostSrav = (TextView) findViewById(R.id.tvPlotnostSravnenie);

        name = etName.getText().toString();
        stoimost = Double.parseDouble(etStoim.getText().toString());
        koncentrat = Double.parseDouble(etKoncentrat.getText().toString());
        vanna = Double.parseDouble(etVanna.getText().toString());
        ves = Double.parseDouble(etVes.getText().toString());
        plotnost = Double.parseDouble(etPlotnost.getText().toString());

        resStoimKg = stoimost / ves;
        resStoimL = resStoimKg * plotnost;
        resStoimPromiv = (resStoimL * koncentrat * vanna) / 100;
        resStoimSmesi = resStoimPromiv / vanna;

        tvSravnenie.setText(name);
        tvStoimKgSrav.setText(String.valueOf(roundUp(resStoimKg, 2)));
        tvStoimLSrav.setText(String.valueOf(roundUp(resStoimL, 2)));
        tvStoimPromivSrav.setText(String.valueOf(roundUp(resStoimPromiv, 2)));
        tvStoimSmesiSrav.setText(String.valueOf(roundUp(resStoimSmesi, 2)));
        tvPlotnostSrav.setText(String.valueOf(roundUp(plotnost, 2)));
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

        Intent intent;
        intent = ClickLeftMenu.getIntent(ApkMoySredstvaSravnenieActivity2.this, id);
        startActivity(intent);

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
