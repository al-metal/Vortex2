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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

import static com.vortex.vortex.Calculations.RoundUp.roundUp;

public class ActivityApkMoySredstvaVoda2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView tvVoda;
    private EditText etVoda;
    private TextView tvVodaStr;
    private RadioButton rbDjeskost;
    private RadioButton rbDh;
    private RadioButton rbMgL;

    private TextView tvSheloch1;
    private TextView tvKislot1;
    private TextView tvSheloch2;
    private TextView tvKislot2;
    private TextView tvSheloch3;
    private TextView tvKislot3;
    private TextView tvSheloch4;
    private TextView tvKislot4;

    private double voda;
    String strVyborJVody;
    TableLayout tableL;
    Button btnRaschet;
    String strJoskost = "0";
    boolean cbCheked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apk_moy_sredstva_voda2);

        setTitle("Подбор средства под жесткость воды");
        tvVoda = (TextView) findViewById(R.id.tvVoda);
        tvVodaStr = (TextView) findViewById(R.id.tvVodaStr);
        etVoda = (EditText) findViewById(R.id.etVoda);
        rbDjeskost = (RadioButton) findViewById(R.id.rbDjeskost);
        rbMgL = (RadioButton) findViewById(R.id.rbMgL);
        rbDh = (RadioButton) findViewById(R.id.rbDh);

        btnRaschet = (Button) findViewById(R.id.btnRaschet);

        tvSheloch1 = (TextView) findViewById(R.id.tvSheloch1);
        tvKislot1 = (TextView) findViewById(R.id.tvKislot1);
        tvSheloch2 = (TextView) findViewById(R.id.tvSheloch2);
        tvKislot2 = (TextView) findViewById(R.id.tvKislot2);
        tvSheloch3 = (TextView) findViewById(R.id.tvSheloch3);
        tvKislot3 = (TextView) findViewById(R.id.tvKislot3);
        tvSheloch4 = (TextView) findViewById(R.id.tvSheloch4);
        tvKislot4 = (TextView) findViewById(R.id.tvKislot4);

        tableL = (TableLayout) findViewById(R.id.tableL);

        strVyborJVody = "выбранная жесткость воды " + strJoskost + " °Ж";
        tvVoda.setText(strVyborJVody);

        ((EditText) findViewById(R.id.etVoda)).addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                strVyborJVody = null;
                strJoskost = "0";

                if (etVoda.getText().length() != 0) {
                    voda = Double.parseDouble(etVoda.getText().toString());
                    if (rbDjeskost.isChecked() == true) {
                        cbCheked = true;
                        strJoskost = etVoda.getText().toString();
                        ReturnVoda(strJoskost);
                    } else if (rbDh.isChecked() == true) {
                        cbCheked = true;
                        voda = voda * 0.36;
                        strJoskost = String.valueOf(roundUp(voda, 2));
                        ReturnVoda(strJoskost);
                    } else if (rbMgL.isChecked() == true) {
                        cbCheked = true;
                        strJoskost = etVoda.getText().toString();
                        ReturnVoda(strJoskost);
                    }

                    strVyborJVody = "выбранная жесткость воды " + strJoskost + " °Ж";
                    tvVoda.setText(strVyborJVody);

                } else {
                    strVyborJVody = "выбранная жесткость воды " + strJoskost + " °Ж";
                    tvVoda.setText(strVyborJVody);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // текст будет изменен
            }

            @Override
            public void afterTextChanged(Editable s) {
                // текст уже изменили
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

    private void ReturnVoda(String tvVoda) {
        double vod = Double.parseDouble(tvVoda);
        if (0 <= vod && vod < 1.5) {
            tvVodaStr.setText("вода очень мягкая");
        } else if (1.5 <= vod && vod < 3) {
            tvVodaStr.setText("вода мягкая");
        } else if (3 <= vod && vod < 6) {
            tvVodaStr.setText("вода умеренной жесткости");
        } else if (6 <= vod && vod <= 12) {
            tvVodaStr.setText("вода жесткая");
        } else if (vod > 12) {
            tvVodaStr.setText("вода очень жесткая");
        } else {
            tvVodaStr.setText("");
        }
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        strVyborJVody = null;
        strJoskost = "0";

        if (etVoda.getText().length() != 0)
            voda = Double.parseDouble(etVoda.getText().toString());
        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.rbDjeskost:
                if (checked) {
                    if (etVoda.getText().length() != 0) {
                        strJoskost = etVoda.getText().toString();
                        ReturnVoda(strJoskost);
                    }
                }
                break;
            case R.id.rbDh:
                if (checked) {
                    if (etVoda.getText().length() != 0) {
                        voda = voda * 0.36;
                        strJoskost = String.valueOf(roundUp(voda, 2));
                        ReturnVoda(strJoskost);
                    }
                }
                break;
            case R.id.rbMgL:
                if (checked) {
                    if (etVoda.getText().length() != 0) {
                        strJoskost = etVoda.getText().toString();
                        ReturnVoda(strJoskost);
                    }
                }
                break;
        }
        strVyborJVody = "выбранная жесткость воды " + strJoskost + " °Ж";
        tvVoda.setText(strVyborJVody);
    }

    public void onClickRaschetRekomendSredstv(View view) {
        int gray = Color.parseColor("#7B7979");
        btnRaschet.setBackgroundColor(gray);
        double voda = Double.parseDouble(strJoskost);

        if (!cbCheked || etVoda.getText().toString() == "") {
            Toast.makeText(getBaseContext(), "Заполните пожалуйста все данные", Toast.LENGTH_SHORT).show();
            return;
        }

        //Скрытие клавиатуры по нажатию кнопки
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(btnRaschet.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        String biotekC = "";
        String ksilanK = "";
        String biotek = "";
        String ksilan = "";
        String biotekM = "";
        String ksilanM = "";
        String biotekSuper = "";
        String ksilanSuper = "";

        tableL.setVisibility(View.VISIBLE);

        if (voda <= 1) {
            biotekM = "BIOTEC M, 0.4%";
            ksilanM = "KSILAN M, 0.4%";
        } else if (voda <= 2) {
            biotekM = "BIOTEC M, 0.5%";
            ksilanM = "KSILAN M, 0.5%";
        } else if (voda <= 3) {
            biotekM = "BIOTEC M, 0.7%";
            ksilanM = "KSILAN M, 0.7%";
        } else if (voda <= 4) {
            biotekM = "BIOTEC M, 1%";
            ksilanM = "KSILAN M, 1%";
        } else {
            biotekM = "BIOTEC M не используется";
            ksilanM = "KSILAN M не используется";
        }

        if (voda <= 5) {
            biotekC = "BIOTEC C, 0.3%";
            ksilanK = "KSILAN K, 0.3%";
        } else if (voda <= 6.5) {
            biotekC = "BIOTEC C, 0.5%";
            ksilanK = "KSILAN K, 0.5%";
        } else if (voda <= 8) {
            biotekC = "BIOTEC C, 1%";
            ksilanK = "KSILAN K, 1%";
        } else {
            biotekC = "BIOTEC C не используется";
            ksilanK = "KSILAN K не используется";
        }

        if (voda <= 5) {
            biotek = "BIOTEC, 0.3%";
            ksilan = "KSILAN, 0.3%";
        } else if (voda <= 6.5) {
            biotek = "BIOTEC, 0.5%";
            ksilan = "KSILAN, 0.5%";
        } else if (voda <= 8) {
            biotek = "BIOTEC, 0.7%";
            ksilan = "KSILAN, 0.7%";
        } else {
            biotek = "BIOTEC не используется";
            ksilan = "KSILAN не используется";
        }

        if (voda <= 10) {
            biotekSuper = "BIOTEC SUPER, 0.3%";
            ksilanSuper = "KSILAN SUPER, 0.3%";
        } else if (voda <= 12) {
            biotekSuper = "BIOTEC SUPER, 0.4%";
            ksilanSuper = "KSILAN SUPER, 0.4%";
        } else {
            biotekSuper = "BIOTEC SUPER не используется";
            ksilanSuper = "KSILAN SUPER не используется";
        }

        tvSheloch1.setText(biotekM);
        tvKislot1.setText(ksilanM);
        tvSheloch2.setText(biotekC);
        tvKislot2.setText(ksilanK);
        tvSheloch3.setText(biotek);
        tvKislot3.setText(ksilan);
        tvSheloch4.setText(biotekSuper);
        tvKislot4.setText(ksilanSuper);
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
        Intent intent = ClickLeftMenu.getIntent(ActivityApkMoySredstvaVoda2.this, item);
        startActivity(intent);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
