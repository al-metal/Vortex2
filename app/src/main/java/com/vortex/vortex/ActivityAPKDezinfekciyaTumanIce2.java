package com.vortex.vortex;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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

import static com.vortex.vortex.Calculations.RoundUp.roundUp;

public class ActivityAPKDezinfekciyaTumanIce2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String[] data = {"2", "3", "4", "5"};
    double dblSpinerKoncentraciya = 0;

    EditText etVysota;
    EditText etVysotaKon;
    EditText etDlinna;
    EditText etShirina;
    EditText etKratnost;

    TextView tvObjem;
    TextView tvEkspoz;
    TextView tvRashodRastvora;
    TextView tvRashodKoncentrata;
    TextView tvForbicida;

    TableLayout tableL;
    Button btnRaschet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apkdezinfekciya_tuman_ice2);

        setTitle("Использование метода холодного тумана");

        tableL = (TableLayout) findViewById(R.id.tableL);
        btnRaschet = (Button) findViewById(R.id.btnRaschet);

        etVysota = (EditText) findViewById(R.id.etVysota);
        etVysotaKon = (EditText) findViewById(R.id.etVysotaKon);
        etDlinna = (EditText) findViewById(R.id.etDlinna);
        etShirina = (EditText) findViewById(R.id.etShirina);
        etKratnost = (EditText) findViewById(R.id.etKratnost);

        tvObjem = (TextView) findViewById(R.id.tvObjem);
        tvEkspoz = (TextView) findViewById(R.id.tvEkspoz);
        tvRashodRastvora = (TextView) findViewById(R.id.tvRashodRastvora);
        tvRashodKoncentrata = (TextView) findViewById(R.id.tvRashodKoncentrata);
        tvForbicida = (TextView) findViewById(R.id.tvForbicida);

        Spinner spinner = (Spinner) findViewById(R.id.spinner6);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner, data);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item_means);

        spinner.setAdapter(adapter);

        // заголовок
        spinner.setPrompt("Концентрация использования, %");

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                if (pos == 0)
                    dblSpinerKoncentraciya = 2;
                else if (pos == 1)
                    dblSpinerKoncentraciya = 3;
                else if (pos == 2)
                    dblSpinerKoncentraciya = 4;
                else if (pos == 3)
                    dblSpinerKoncentraciya = 5;
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
        if (etVysota.getText().length() == 0 || etVysotaKon.getText().length() == 0 || etDlinna.getText().length() == 0
                || etShirina.getText().length() == 0 || etKratnost.getText().length() == 0) {
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

        double dblVysota = Double.parseDouble(etVysota.getText().toString());
        double dblVysotaKon = Double.parseDouble(etVysotaKon.getText().toString());
        double dblDlinna = Double.parseDouble(etDlinna.getText().toString());
        double dblShirina = Double.parseDouble(etShirina.getText().toString());
        double dblKratnost = Double.parseDouble(etKratnost.getText().toString());

        double dblEkspoz = Double.parseDouble(tvEkspoz.getText().toString());
        double dblRashodRastvora = Double.parseDouble(tvRashodRastvora.getText().toString());

        double dblObjem = (dblDlinna * dblShirina * dblVysota) + (((dblVysotaKon - dblVysota) * dblShirina) / 2) * dblDlinna;
        double dblKoncentraciya = (30 * dblSpinerKoncentraciya) / 100;
        double dblForbizida = (dblKratnost * dblKoncentraciya * dblObjem) / 1000;

        tvObjem.setText(String.valueOf(roundUp(dblObjem, 2)));
        tvRashodKoncentrata.setText(String.valueOf(roundUp(dblKoncentraciya, 2)));
        tvForbicida.setText(String.valueOf(roundUp(dblForbizida, 2)));

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
        Intent intent = ClickLeftMenu.getIntent(ActivityAPKDezinfekciyaTumanIce2.this, item);
        startActivity(intent);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
