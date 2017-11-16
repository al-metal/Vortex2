package com.vortex.vortex;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
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
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

public class ActivityAuto2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    LinearLayout llmain;

    String[] data = {"ВЫБРАТЬ УСТРОЙСТВО", "ПЕНОГЕНЕРАТОР, 50 Л", "ПЕНОКОМПЛЕКТ", "ДОЗАТРОН"};

    String[][] arr = {};

    //region Array
    String[][] peno50ligth = {{"", "Разбавление", "Количество, мл"},
            {"UNIOR", "1:60", "850"},
            {"TIRO, TIRO TONE", "1:80", "625"},
            {"MASTER, MASTER TONE", "1:100", "500"},
            {"PROFY", "1:120", "350"},
            {"DOZEX", "1:100", "450"},
            {"ACE", "1:140", "350"},
            {"MAGNAT", "1:110", "450"},
            {"DELICATE", "1:80", "625"},
            {"SOLO", "1:70", "700"},
            {"diy", "1:120", "350"},
            {"GURU", "1:120", "350"}};

    String[][] peno50default = {{"", "Разбавление", "Количество, мл"},
            {"UNIOR", "1:40", "1000"},
            {"TIRO, TIRO TONE", "1:60", "800"},
            {"NOVICE", "1:80", "800"},
            {"MASTER, MASTER TONE", "1:80", "600"},
            {"TUTOR ", "1:100", "500"},
            {"PROFY", "1:100", "400"},
            {"DOZEX", "1:90", "550"},
            {"SENZA", "1:120", "350"},
            {"ACE", "1:120", "350"},
            {"MAGNAT", "1:100", "500"},
            {"DELICATE", "1:60", "800"},
            {"SOLO", "1:55", "800"},
            {"diy", "1:100", "400"},
            {"GURU", "1:120", "400"}};

    String[][] peno50hard = {{"", "Разбавление", "Количество, мл"},
            {"NOVICE", "1:50", "1000"},
            {"TUTOR ", "1:60", "600"},
            {"PROFY", "1:60", "500"},
            {"SENZA", "1:80", "400"},
            {"ACE", "1:80", "400"},
            {"MAGNAT", "1:60", "600"},
            {"diy", "1:60", "600"},
            {"GURU", "1:80", "450"}};

    String[][] penokomplektligth = {{"", "Разбавление", "Количество, мл"},
            {"UNIOR", "1:4", "200"},
            {"TIRO, TIRO TONE", "1:6", "150"},
            {"MASTER, MASTER TONE", "1:8", "110"},
            {"PROFY", "1:10", "90"},
            {"DOZEX", "1:9", "100"},
            {"ACE", "1:12", "80"},
            {"MAGNAT", "1:9", "100"},
            {"DELICATE", "1:6", "150"},
            {"SOLO", "1:5", "170"},
            {"diy", "1:10", "90"},
            {"GURU", "1:10", "80"}};

    String[][] penokomplektdefault = {{"", "Разбавление", "Количество, мл"},
            {"UNIOR", "1:2", "300"},
            {"TIRO, TIRO TONE", "1:4", "200"},
            {"NOVICE", "1:6", "150"},
            {"MASTER, MASTER TONE", "1:6", "145"},
            {"TUTOR ", "1:8", "110"},
            {"PROFY", "1:8", "110"},
            {"DOZEX", "1:7", "125"},
            {"SENZA", "1:12", "90"},
            {"ACE", "1:15", "80"},
            {"MAGNAT", "1:8", "110"},
            {"DELICATE", "1:4", "200"},
            {"SOLO", "1:4", "200"},
            {"diy", "1:8", "110"},
            {"GURU", "1:12", "100"}};

    String[][] penokomplekthard = {{"", "Разбавление", "Количество, мл"},
            {"NOVICE", "1:4", "200"},
            {"TUTOR ", "1:6", "145"},
            {"PROFY", "1:6", "150"},
            {"SENZA", "1:10", "125"},
            {"ACE", "1:7", "125"},
            {"MAGNAT", "1:6", "145"},
            {"diy", "1:6", "145"},
            {"GURU", "1:10", "125"}};

    String[][] dozatronligth = {{"", "Концентрация, %"},
            {"UNIOR", "3"},
            {"TIRO, TIRO TONE", "2"},
            {"MASTER, MASTER TONE", "1"},
            {"PROFY", "1"},
            {"DOZEX", "1"},
            {"ACE", "0.5"},
            {"DELICATE", "1.5"},
            {"SOLO", "1.5"},
            {"diy", "1"}};

    String[][] dozatrondefault = {{"", "Концентрация, %"},
            {"UNIOR", "3"},
            {"TIRO, TIRO TONE", "2"},
            {"NOVICE", "1.5"},
            {"MASTER, MASTER TONE", "1.5"},
            {"TUTOR ", "1"},
            {"PROFY", "1"},
            {"DOZEX", "1"},
            {"SENZA", "1"},
            {"ACE", "1"},
            {"DELICATE", "2"},
            {"SOLO", "2"},
            {"diy", "1"}};

    String[][] dozatronhard = {{"", "Концентрация, %"},
            {"NOVICE", "3"},
            {"TUTOR ", "2"},
            {"PROFY", "2"},
            {"SENZA", "1.5"},
            {"ACE", "1.5"},
            {"diy", "2"}};
//endregion

    private EditText etVoda;
    private TextView tvVoda;
    private TextView tvVodaStr;

    private RadioButton rbDjeskost;
    private RadioButton rbDh;
    private RadioButton rbMgL;
    private Button btnRaschet;

    private double voda;
    String spin;
    String sredstvo;
    String strVoda;
    String strVyborJVody;
    String strJoskost = "0";

    boolean selectedSpiner = false;
    boolean bollSpinner = false;

    TableLayout table;
    TableRow tableR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto2);

        setTitle("Автохимия");

        strVoda = null;

        table = (TableLayout) findViewById(R.id.table);
        tableR = (TableRow) findViewById(R.id.tableR);

        llmain = (LinearLayout) findViewById(R.id.llmain);

        etVoda = (EditText) findViewById(R.id.etVoda);
        tvVoda = (TextView) findViewById(R.id.tvVoda);
        tvVodaStr = (TextView) findViewById(R.id.tvVodaStr);

        rbDjeskost = (RadioButton) findViewById(R.id.rbDjeskost);
        rbMgL = (RadioButton) findViewById(R.id.rbMgL);
        rbDh = (RadioButton) findViewById(R.id.rbDh);
        btnRaschet = (Button) findViewById(R.id.btnRaschet);

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
                        strJoskost = etVoda.getText().toString();
                        //tvVoda.setText(etVoda.getText().toString());
                        ReturnVoda(strJoskost);
                    } else if (rbDh.isChecked() == true) {
                        voda = voda * 0.36;
                        strJoskost = String.valueOf(roundUp(voda, 2));
                        //tvVoda.setText(String.valueOf(roundUp(voda, 2)));
                        ReturnVoda(strJoskost);
                    } else if (rbMgL.isChecked() == true) {
                        strJoskost = etVoda.getText().toString();
                        //tvVoda.setText(etVoda.getText().toString());
                        ReturnVoda(strJoskost);
                    }
                    strVyborJVody = "выбранная жесткость воды " + strJoskost + " °Ж";
                    tvVoda.setText(strVyborJVody);
                    selectedSpiner = true;
                } else {
                    tvVoda.setText("выбранная жесткость воды °Ж");
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

        final Spinner spinner = (Spinner) findViewById(R.id.spinner8);

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
                if (strJoskost == null)
                    return;
                double vod = Double.parseDouble(strJoskost);
                if (pos == 0) {
                    selectedSpiner = false;
                    return;
                } else if (pos == 1) {
                    spin = "peno50";
                    if (0 <= vod && vod < 3.5) {
                        arr = peno50ligth;
                        sredstvo = "peno50ligth";
                    } else if (3.5 <= vod && vod < 7) {
                        arr = peno50default;
                        sredstvo = "peno50default";
                    } else if (7 <= vod) {
                        arr = peno50hard;
                        sredstvo = "peno50hard";
                    }
                } else if (pos == 2) {
                    spin = "penokomplekt";
                    if (0 <= vod && vod < 3.5) {
                        arr = penokomplektligth;
                        sredstvo = "penokomplektligth";
                    } else if (3.5 <= vod && vod < 7) {
                        arr = penokomplektdefault;
                        sredstvo = "penokomplektdefault";
                    } else if (7 <= vod) {
                        arr = penokomplekthard;
                        sredstvo = "penokomplekthard";
                    }
                } else if (pos == 3) {
                    spin = "dozatron";
                    if (0 <= vod && vod < 3.5) {
                        arr = dozatronligth;
                        sredstvo = "dozatronligth";
                    } else if (3.5 <= vod && vod < 7) {
                        arr = dozatrondefault;
                        sredstvo = "dozatrondefault";
                    } else if (7 <= vod) {
                        arr = dozatronhard;
                        sredstvo = "dozatronhard";
                    }
                }
                bollSpinner = true;
                selectedSpiner = true;
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
                    //tvDh.setText("°Ж");
                    if (etVoda.getText().length() != 0) {
                        strJoskost = etVoda.getText().toString();
                        //tvVoda.setText(etVoda.getText().toString());
                        ReturnVoda(strJoskost);
                    }
                }
                break;
            case R.id.rbDh:
                if (checked) {
                    //tvDh.setText("°DH");
                    if (etVoda.getText().length() != 0) {
                        voda = voda * 0.36;
                        strJoskost = String.valueOf(roundUp(voda, 2));
                        //tvVoda.setText(String.valueOf(roundUp(voda, 2)));
                        ReturnVoda(strJoskost);
                    }
                }
                break;
            case R.id.rbMgL:
                if (checked) {
                    //tvDh.setText("мг - экв/л");
                    if (etVoda.getText().length() != 0) {
                        strJoskost = etVoda.getText().toString();
                        //tvVoda.setText(etVoda.getText().toString());
                        ReturnVoda(strJoskost);
                    }
                }
                break;
        }
        strVyborJVody = "выбранная жесткость воды " + strJoskost + " °Ж";
        tvVoda.setText(strVyborJVody);
        selectedSpiner = true;
    }

    private void ReturnVoda(String tvVoda) {
        double vod = Double.parseDouble(tvVoda);
        if (0 <= vod && vod < 3.5) {
            tvVodaStr.setText("вода мягкая");
            strVoda = "ligth";
            if (spin == "peno50") {
                arr = peno50ligth;
            } else if (spin == "penokomplekt") {
                arr = penokomplektligth;
            } else if (spin == "dozatron") {
                arr = dozatronligth;
            }
        } else if (3.5 <= vod && vod < 7) {
            tvVodaStr.setText("вода умеренной жесткости");
            strVoda = "default";
            if (spin == "peno50") {
                arr = peno50default;
            } else if (spin == "penokomplekt") {
                arr = penokomplektdefault;
            } else if (spin == "dozatron") {
                arr = dozatrondefault;
            }
        } else if (7 <= vod) {
            tvVodaStr.setText("вода жесткая");
            strVoda = "hard";
            if (spin == "peno50") {
                arr = peno50hard;
            } else if (spin == "penokomplekt") {
                arr = penokomplekthard;
            } else if (spin == "dozatron") {
                arr = dozatronhard;
            }
        } else {
            tvVodaStr.setText("");
        }
    }

    public BigDecimal roundUp(double value, int digits) {
        return new BigDecimal("" + value).setScale(digits, BigDecimal.ROUND_HALF_UP);
    }

    public void onClick(View view) {

        if (!selectedSpiner || etVoda.getText().length() == 0 || !bollSpinner) {
            Toast.makeText(getBaseContext(), "Заполните пожалуйста все данные", Toast.LENGTH_SHORT).show();
            return;
        }

        //Скрытие клавиатуры по нажатию кнопки
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(btnRaschet.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        table.setVisibility(View.VISIBLE);
        tableR.setVisibility(View.VISIBLE);

        TableLayout tableLayout = (TableLayout) findViewById(R.id.table);
        tableLayout.removeAllViewsInLayout();

        //ReturnArr();

        int count = arr.length;
        for (int i = 0; count > i; i++) {
            if (spin == "dozatron")
                addRow2(arr[i][0], arr[i][1]);
            else
                addRow(arr[i][0], arr[i][1], arr[i][2]);
        }
        //tvVoda.setText(String.valueOf(roundUp(voda, 2)));
        ReturnVoda(strJoskost);
        int gray = Color.parseColor("#7B7979");
        btnRaschet.setBackgroundColor(gray);
    }

    private void ReturnArr() {

        if (sredstvo == "peno50ligth") {
            arr = peno50ligth;
        } else if (sredstvo == "peno50default") {
            arr = peno50default;
        } else if (sredstvo == "peno50hard") {
            arr = peno50hard;
        } else if (sredstvo == "penokomplektligth") {
            arr = penokomplektligth;
        } else if (sredstvo == "penokomplektdefault") {
            arr = penokomplektdefault;
        } else if (sredstvo == "penokomplekthard") {
            arr = penokomplekthard;
        } else if (sredstvo == "dozatronligth") {
            arr = dozatronligth;
        } else if (sredstvo == "dozatrondefault") {
            arr = dozatrondefault;
        } else if (sredstvo == "dozatronhard") {
            arr = dozatronhard;
        }
    }

    private void addRow(String a, String b, String c) {

        TableLayout tableLayout = (TableLayout) findViewById(R.id.table);

        LayoutInflater inflater = LayoutInflater.from(this);
        TableRow tr = (TableRow) inflater.inflate(R.layout.table_row2222, null);
        TextView tv = (TextView) tr.findViewById(R.id.col1);
        tv.setText(a);
        tv = (TextView) tr.findViewById(R.id.col2);
        tv.setText(b);
        tv = (TextView) tr.findViewById(R.id.col3);
        tv.setText(c);

        tableLayout.addView(tr);
    }

    private void addRow2(String a, String b) {

        TableLayout tableLayout = (TableLayout) findViewById(R.id.table);
        LayoutInflater inflater = LayoutInflater.from(this);
        TableRow tr = (TableRow) inflater.inflate(R.layout.table_row22, null);
        TextView tv = (TextView) tr.findViewById(R.id.col1);
        tv.setText(a);
        tv = (TextView) tr.findViewById(R.id.col2);
        tv.setText(b);

        tableLayout.addView(tr);
    }

    public void onClickSravnenie(View view) {
        if (strVoda == null) {
            Toast.makeText(getBaseContext(), "Расчеты не выполнены", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(ActivityAuto2.this, ActivityAutoSravnenie2.class);
        intent.putExtra("dh", strVoda);
        intent.putExtra("sredstvo", sredstvo);
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
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Intent intent;
        intent = ClickLeftMenu.getIntent(ActivityAuto2.this, id);
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
