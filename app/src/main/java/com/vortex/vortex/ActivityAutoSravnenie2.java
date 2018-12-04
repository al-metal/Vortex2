package com.vortex.vortex;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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

import java.util.ArrayList;

import static com.vortex.vortex.Calculations.RoundUp.roundUp;

public class ActivityAutoSravnenie2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //region array
    String[][] peno50ligthName = {{"Продукт", "Разбавление", "Кол-во шампуня, мл"},
            {"ВЫБРАТЬ СРЕДСТВО", "0", "0"},
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

    String[][] peno50defaultName = {{"Продукт", "Разбавление", "Кол-во шампуня, мл"},
            {"ВЫБРАТЬ СРЕДСТВО", "0", "0"},
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

    String[][] peno50hardName = {{"Продукт", "Разбавление", "Кол-во шампуня, мл"},
            {"ВЫБРАТЬ СРЕДСТВО", "0", "0"},
            {"NOVICE", "1:50", "1000"},
            {"TUTOR ", "1:60", "600"},
            {"PROFY", "1:60", "500"},
            {"SENZA", "1:80", "400"},
            {"ACE", "1:80", "400"},
            {"MAGNAT", "1:60", "600"},
            {"diy", "1:60", "600"},
            {"GURU", "1:80", "450"}};

    String[][] penokomplektligthName = {{"Продукт", "Разбавление", "Кол-во шампуня, мл"},
            {"ВЫБРАТЬ СРЕДСТВО", "0", "0"},
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

    String[][] penokomplektdefaultName = {{"Продукт", "Разбавление", "Кол-во шампуня, мл"},
            {"ВЫБРАТЬ СРЕДСТВО", "0", "0"},
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

    String[][] penokomplekthardName = {{"Продукт", "Разбавление", "Кол-во шампуня, мл"},
            {"ВЫБРАТЬ СРЕДСТВО", "0", "0"},
            {"NOVICE", "1:4", "200"},
            {"TUTOR ", "1:6", "145"},
            {"PROFY", "1:6", "150"},
            {"SENZA", "1:10", "125"},
            {"ACE", "1:7", "125"},
            {"MAGNAT", "1:6", "145"},
            {"diy", "1:6", "145"},
            {"GURU", "1:10", "125"}};

    String[][] dozatronligthName = {{"Продукт", "Показатель концентрации %"},
            {"ВЫБРАТЬ СРЕДСТВО", "0", "0"},
            {"UNIOR", "3"},
            {"TIRO, TIRO TONE", "2"},
            {"MASTER, MASTER TONE", "1"},
            {"PROFY", "1"},
            {"DOZEX", "1"},
            {"ACE", "0.5"},
            {"DELICATE", "1.5"},
            {"SOLO", "1.5"},
            {"diy", "1"}};

    String[][] dozatrondefaultName = {{"Продукт", "Показатель концентрации %"},
            {"ВЫБРАТЬ СРЕДСТВО", "0", "0"},
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

    String[][] dozatronhardName = {{"Продукт", "Показатель концентрации %"},
            {"ВЫБРАТЬ СРЕДСТВО", "0", "0"},
            {"NOVICE", "3"},
            {"TUTOR ", "2"},
            {"PROFY", "2"},
            {"SENZA", "1.5"},
            {"ACE", "1.5"},
            {"diy", "2"}};

    //endregion

    //region array 2
    String[][] peno50ligth = {
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

    String[][] peno50default =
            {
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
    String[][] peno50hard =
            {
                    {"NOVICE", "1:50", "1000"},
                    {"TUTOR ", "1:60", "600"},
                    {"PROFY", "1:60", "500"},
                    {"SENZA", "1:80", "400"},
                    {"ACE", "1:80", "400"},
                    {"MAGNAT", "1:60", "600"},
                    {"diy", "1:60", "600"},
                    {"GURU", "1:80", "450"}};

    String[][] penokomplektligth =
            {
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
    String[][] penokomplektdefault =
            {
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
    String[][] ppenokomplekthard =
            {
                    {"NOVICE", "1:4", "200"},
                    {"TUTOR ", "1:6", "145"},
                    {"PROFY", "1:6", "150"},
                    {"SENZA", "1:10", "125"},
                    {"ACE", "1:7", "125"},
                    {"MAGNAT", "1:6", "145"},
                    {"diy", "1:6", "145"},
                    {"GURU", "1:10", "125"}};

    String[][] dozatronligth =
            {
                    {"UNIOR", "3"},
                    {"TIRO, TIRO TONE", "2"},
                    {"MASTER, MASTER TONE", "1"},
                    {"PROFY", "1"},
                    {"DOZEX", "1"},
                    {"ACE", "0.5"},
                    {"DELICATE", "1.5"},
                    {"SOLO", "1.5"},
                    {"diy", "1"}
            };
    String[][] dozatrondefault =
            {
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
    String[][] dozatronhard =
            {
                    {"NOVICE", "3"},
                    {"TUTOR ", "2"},
                    {"PROFY", "2"},
                    {"SENZA", "1.5"},
                    {"ACE", "1.5"},
                    {"diy", "2"}};

    //endregion

    String dh;
    String sredstvo;
    String[] data;
    String[][] array;
    Spinner spinner9;
    ArrayList tovars = new ArrayList();
    String nameProduct;
    String penogenchast;
    String penogenob;
    String penokomchast;
    String penokomob;
    String dozatronpercent;

    TextView tvChastVortex;
    TextView tvPeno50Vortex;
    TextView tvPeno25Vortex;
    TextView tvPeno100Vortex;
    TextView tvPenokomlektChastVortex;
    TextView tvPenokomlektLitrVortex;
    TextView tvDozatronVortex;
    TextView tvRashodPenogeneratorVortex;
    TextView tvRashodPenoKomplektVortex;
    TextView tvStoimostMoykiVortex;
    TextView tvChistAvtoVortex;

    TextView tvPenokomlektLitr;

    TextView tvRashodPenogenerator;
    TextView tvRashodPenoKomplekt;
    TextView tvStoimostMoyki;
    TextView tvChistAvto;
    TextView tvMashinBolshe;
    TextView tvPribyl;
    TextView tvVortex;
    TextView tvVortex2;
    TextView tvVortex3;
    TextView tvVortex4;
    TextView tvVortex5;

    TextView tvStoimostMoykiPenokomplektVortex;
    TextView tvChistAvtoPenokomplektVortex;
    TextView tvRashodPenoKomplektS;
    TextView tvStoimostMoykiPenokomplektS;
    TextView tvChistAvtoPenokomplektS;

    TextView tvStoimostMoykiPenogeneratorVortex;
    TextView tvChistAvtoPenogeneratorVortex;
    TextView tvRashodPenogeneratorS;
    TextView tvStoimostMoykiPenogeneratorS;
    TextView tvChistAvtoPenogeneratorS;

    TextView tvMashinBolshePenokomplekt;
    TextView tvPribylPenokomplekt;

    EditText etPriceVortex;
    EditText etPrice;
    EditText etMoyka;
    EditText etPenokomlektLitr;
    EditText etPeno50;

    TableLayout tbllt3;
    TableLayout tbllt2;
    TableLayout tbllt1;

    double CHistAvtoS;
    double CHistAvto;
    double CHistAvtoPenokomplekt;
    double CHistAvtoCHistAvtoPenokomplektS;

    Button btnSravnenie;
    Button btnRaschet;

    boolean spin;
    int gray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_sravnenie2);

        setTitle("Сравнить с другим средством");

        btnSravnenie = (Button) findViewById(R.id.btnSravnenie);
        btnRaschet = (Button) findViewById(R.id.btnRaschet);

        tbllt1 = (TableLayout) findViewById(R.id.tbllt1);
        tbllt2 = (TableLayout) findViewById(R.id.tbllt2);
        tbllt3 = (TableLayout) findViewById(R.id.tbllt3);

        tvStoimostMoykiPenokomplektVortex = (TextView) findViewById(R.id.tvStoimostMoykiPenokomplektVortex);
        tvChistAvtoPenokomplektVortex = (TextView) findViewById(R.id.tvChistAvtoPenokomplektVortex);
        tvRashodPenoKomplektS = (TextView) findViewById(R.id.tvRashodPenoKomplektS);
        tvStoimostMoykiPenokomplektS = (TextView) findViewById(R.id.tvStoimostMoykiPenokomplektS);
        tvChistAvtoPenokomplektS = (TextView) findViewById(R.id.tvChistAvtoPenokomplektS);

        tvStoimostMoykiPenogeneratorVortex = (TextView) findViewById(R.id.tvStoimostMoykiPenogeneratorVortex);
        tvChistAvtoPenogeneratorVortex = (TextView) findViewById(R.id.tvChistAvtoPenogeneratorVortex);
        tvRashodPenogeneratorS = (TextView) findViewById(R.id.tvRashodPenogeneratorS);
        tvStoimostMoykiPenogeneratorS = (TextView) findViewById(R.id.tvStoimostMoykiPenogeneratorS);
        tvChistAvtoPenogeneratorS = (TextView) findViewById(R.id.tvChistAvtoPenogeneratorS);

        tvChastVortex = (TextView) findViewById(R.id.tvChastVortex);
        tvPeno25Vortex = (TextView) findViewById(R.id.tvPeno25Vortex);
        tvPeno50Vortex = (TextView) findViewById(R.id.tvPeno50Vortex);
        tvPeno100Vortex = (TextView) findViewById(R.id.tvPeno100Vortex);
        tvPenokomlektChastVortex = (TextView) findViewById(R.id.tvPenokomlektChastVortex);
        tvPenokomlektLitrVortex = (TextView) findViewById(R.id.tvPenokomlektLitrVortex);
        tvDozatronVortex = (TextView) findViewById(R.id.tvDozatronVortex);
        tvRashodPenogeneratorVortex = (TextView) findViewById(R.id.tvRashodPenogeneratorVortex);
        tvRashodPenoKomplektVortex = (TextView) findViewById(R.id.tvRashodPenoKomplektVortex);
        tvStoimostMoykiVortex = (TextView) findViewById(R.id.tvStoimostMoykiVortex);
        //tvChistAvtoVortex = (TextView) findViewById(R.id.tvChistAvtoVortex);
        tvVortex = (TextView) findViewById(R.id.tvVortex);
        tvVortex2 = (TextView) findViewById(R.id.tvVortex2);
        tvVortex3 = (TextView) findViewById(R.id.tvVortex3);
        tvVortex4 = (TextView) findViewById(R.id.tvVortex4);
        tvVortex5 = (TextView) findViewById(R.id.tvVortex5);
        gray = Color.parseColor("#7B7979");

        tvMashinBolshePenokomplekt = (TextView) findViewById(R.id.tvMashinBolshePenokomplekt);
        tvPribylPenokomplekt = (TextView) findViewById(R.id.tvPribylPenokomplekt);

        //tvRashodPenogenerator = (TextView) findViewById(R.id.tvRashodPenogenerator);

        tvStoimostMoyki = (TextView) findViewById(R.id.tvStoimostMoyki);
        //tvChistAvto = (TextView) findViewById(R.id.tvChistAvto);

        etPeno50 = (EditText) findViewById(R.id.etPeno50);
        etPenokomlektLitr = (EditText) findViewById(R.id.etPenokomlektLitr);
        tvMashinBolshe = (TextView) findViewById(R.id.tvMashinBolshe);
        tvPribyl = (TextView) findViewById(R.id.tvPribyl);

        etPriceVortex = (EditText) findViewById(R.id.etPriceVortex);
        etPrice = (EditText) findViewById(R.id.etPrice);
        etMoyka = (EditText) findViewById(R.id.etMoyka);

        dh = getIntent().getExtras().getString("dh");
        sredstvo = getIntent().getExtras().getString("sredstvo");

        data = ReturnData(dh, sredstvo);

        spinner9 = (Spinner) findViewById(R.id.spinner9);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner, data);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item_means);

        spinner9.setAdapter(adapter);

        spinner9.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                if (pos == 0) {
                    spin = false;
                    return;
                }

                ViewProductVortex(pos);
                tbllt1.setVisibility(View.VISIBLE);
                spin = true;
            }

            public void ViewProductVortex(int pos) {

                nameProduct = String.valueOf(tovars.get(pos));
                tvVortex.setText(nameProduct);
                tvVortex2.setText(nameProduct);
                tvVortex3.setText(nameProduct);
                tvVortex4.setText(nameProduct);
                tvVortex5.setText(nameProduct);

                if (dh.contains("ligth")) {
                    for (int i = 0; peno50ligth.length > i; i++) {
                        if (peno50ligth[i][0] == nameProduct) {
                            penogenchast = peno50ligth[i][1];
                            penogenob = peno50ligth[i][2];
                            break;
                        }
                    }
                    for (int i = 0; penokomplektligth.length > i; i++) {
                        if (penokomplektligth[i][0] == nameProduct) {
                            penokomchast = penokomplektligth[i][1];
                            penokomob = penokomplektligth[i][2];
                            break;
                        }
                    }
                    for (int i = 0; dozatronligth.length > i; i++) {
                        if (dozatronligth[i][0] == nameProduct) {
                            dozatronpercent = dozatronligth[i][1];
                            break;
                        }
                    }
                } else if (dh.contains("default")) {
                    for (int i = 0; peno50default.length > i; i++) {
                        if (peno50default[i][0] == nameProduct) {
                            penogenchast = peno50default[i][1];
                            penogenob = peno50default[i][2];
                            break;
                        }
                    }
                    for (int i = 0; penokomplektdefault.length > i; i++) {
                        if (penokomplektdefault[i][0] == nameProduct) {
                            penokomchast = penokomplektdefault[i][1];
                            penokomob = penokomplektdefault[i][2];
                            break;
                        }
                    }
                    for (int i = 0; dozatrondefault.length > i; i++) {
                        if (dozatrondefault[i][0] == nameProduct) {
                            dozatronpercent = dozatrondefault[i][1];
                            break;
                        }
                    }
                } else if (dh.contains("hard")) {

                    for (int i = 0; peno50hard.length > i; i++) {
                        if (peno50hard[i][0] == nameProduct) {
                            penogenchast = peno50hard[i][1];
                            penogenob = peno50hard[i][2];
                            break;
                        }
                    }
                    for (int i = 0; ppenokomplekthard.length > i; i++) {
                        if (ppenokomplekthard[i][0] == nameProduct) {
                            penokomchast = ppenokomplekthard[i][1];
                            penokomob = ppenokomplekthard[i][2];
                            break;
                        }
                    }
                    for (int i = 0; dozatronhard.length > i; i++) {
                        if (dozatronhard[i][0] == nameProduct) {
                            dozatronpercent = dozatronhard[i][1];
                            break;
                        }
                    }
                }
                tvChastVortex.setText(penogenchast);
                tvPeno50Vortex.setText(penogenob);
                tvPenokomlektChastVortex.setText(penokomchast);
                tvPenokomlektLitrVortex.setText(penokomob);
                tvDozatronVortex.setText(dozatronpercent);

                ReturnPeno(penogenob);

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

    private void ReturnPeno(String penogenob) {
        double objem = Double.parseDouble(penogenob);

        tvPeno25Vortex.setText(String.valueOf(roundUp(objem / 2, 0)));
        tvPeno100Vortex.setText(String.valueOf(roundUp(objem * 2, 0)));
    }

    private String[] ReturnData(String dh, String sredstvo) {
        String[] dat = null;
        String[][] arr = null;

        if (sredstvo.contains("peno50ligth"))
            arr = peno50ligthName;
        else if (sredstvo.contains("peno50default"))
            arr = peno50defaultName;
        else if (sredstvo.contains("peno50hard"))
            arr = peno50hardName;
        else if (sredstvo.contains("penokomplektligth"))
            arr = penokomplektligthName;
        else if (sredstvo.contains("penokomplektdefault"))
            arr = penokomplektdefaultName;
        else if (sredstvo.contains("penokomplekthard"))
            arr = penokomplekthardName;
        else if (sredstvo.contains("dozatronligth"))
            arr = dozatronligthName;
        else if (sredstvo.contains("dozatrondefault"))
            arr = dozatrondefaultName;
        else if (sredstvo.contains("dozatronhard"))
            arr = dozatronhardName;

        int count = arr.length;
        dat = new String[count - 1];
        for (int i = 1; count > i; i++) {
            dat[i - 1] = arr[i][0];
            tovars.add(arr[i][0]);
        }

        return dat;
    }

    public void onClickRaschet(View view) {

        if (etMoyka.getText().length() == 0) {
            Toast.makeText(getBaseContext(), "Заполните пожалуйста все данные", Toast.LENGTH_SHORT).show();
            return;
        }

        //Скрытие клавиатуры по нажатию кнопки
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(btnRaschet.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        tbllt3.setVisibility(View.VISIBLE);

        btnRaschet.setBackgroundColor(gray);

        if (tvRashodPenoKomplektS.getText() != "0") {
            double avtoPenokomplektClient = CHistAvtoCHistAvtoPenokomplektS;
            double avtoPenokomplektVortex = CHistAvtoPenokomplekt;

            double stoimostMoykiPrice = Double.parseDouble(etMoyka.getText().toString());

            double mashinBolshePenokomlekt = avtoPenokomplektVortex - avtoPenokomplektClient;
            double pribylPenokomlekt = stoimostMoykiPrice * mashinBolshePenokomlekt;

            tvMashinBolshePenokomplekt.setText(String.valueOf(roundUp(mashinBolshePenokomlekt, 0)));
            tvPribylPenokomplekt.setText(String.valueOf(roundUp(pribylPenokomlekt, 2)));
        }
        if (tvRashodPenogeneratorS.getText() != "0") {
            double avtoClient = CHistAvtoS;
            double avtoVortex = CHistAvto;
            double stoimostMoykiPrice = Double.parseDouble(etMoyka.getText().toString());

            double mashinBolshe = avtoVortex - avtoClient;
            double pribyl = stoimostMoykiPrice * mashinBolshe;

            tvMashinBolshe.setText(String.valueOf(roundUp(mashinBolshe, 0)));
            tvPribyl.setText(String.valueOf(roundUp(pribyl, 2)));
        }
        tbllt3.setVisibility(View.VISIBLE);
    }

    public void onClickSravnenie(View view) {
        if (!spin) {
            Toast.makeText(getBaseContext(), "Выберите пожалуйста средство", Toast.LENGTH_SHORT).show();
            return;
        }

        if (etPriceVortex.getText().length() == 0 || etPrice.getText().length() == 0) {
            Toast.makeText(getBaseContext(), "Заполните пожалуйста цена за канистру", Toast.LENGTH_SHORT).show();
            return;
        }
        boolean raschet1 = false;
        boolean raschet2 = false;


        //Скрытие клавиатуры по нажатию кнопки
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(btnRaschet.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        if (etPenokomlektLitr.getText().length() != 0) {
            //расчет Vortex
            double objem1Litr = Double.parseDouble(tvPenokomlektLitrVortex.getText().toString());
            double priceVortex = Double.parseDouble(etPriceVortex.getText().toString());

            double rashodPenokomplekt = objem1Litr / 3;
            double stoimostMoykiPenokomplekt = 1 * priceVortex / 20000 * rashodPenokomplekt;
            CHistAvtoPenokomplekt = 20000 / rashodPenokomplekt;

            //Расчет своего средства
            double objem1LitrS = Double.parseDouble(etPenokomlektLitr.getText().toString());
            double price = Double.parseDouble(etPrice.getText().toString());

            double rashodPenokomplektS = objem1LitrS / 3;
            double stoimostMoykiS = 1 * price / 20000 * rashodPenokomplektS;
            CHistAvtoCHistAvtoPenokomplektS = 20000 / rashodPenokomplektS;

            tvRashodPenoKomplektVortex.setText(String.valueOf(roundUp(rashodPenokomplekt, 2)));
            tvStoimostMoykiPenokomplektVortex.setText(String.valueOf(roundUp(stoimostMoykiPenokomplekt, 2)));
            tvChistAvtoPenokomplektVortex.setText(String.valueOf(roundUp(CHistAvtoPenokomplekt, 2)));

            tvRashodPenoKomplektS.setText(String.valueOf(roundUp(rashodPenokomplektS, 2)));
            tvStoimostMoykiPenokomplektS.setText(String.valueOf(roundUp(stoimostMoykiS, 2)));
            tvChistAvtoPenokomplektS.setText(String.valueOf(roundUp(CHistAvtoCHistAvtoPenokomplektS, 2)));

            raschet1 = true;
        }

        if (etPeno50.getText().length() != 0) {

            //расчет Vortex
            double objemPeno50 = Double.parseDouble(tvPeno50Vortex.getText().toString());
            double priceVortex = Double.parseDouble(etPriceVortex.getText().toString());

            double rashodPenogenerator = objemPeno50 / 14;
            double stoimostMoyki = 1 * priceVortex / 20000 * rashodPenogenerator;
            CHistAvto = 20000 / rashodPenogenerator;

            //Расчет своего средства
            double objemPeno50S = Double.parseDouble(etPeno50.getText().toString());
            double price = Double.parseDouble(etPrice.getText().toString());

            double rashodPenogeneratorS = objemPeno50S / 14;
            double stoimostMoykiS = 1 * price / 20000 * rashodPenogeneratorS;
            CHistAvtoS = 20000 / rashodPenogeneratorS;

            tvRashodPenogeneratorVortex.setText(String.valueOf(roundUp(rashodPenogenerator, 2)));
            tvStoimostMoykiPenogeneratorVortex.setText(String.valueOf(roundUp(stoimostMoyki, 2)));
            tvChistAvtoPenogeneratorVortex.setText(String.valueOf(roundUp(CHistAvto, 2)));

            tvRashodPenogeneratorS.setText(String.valueOf(roundUp(rashodPenogeneratorS, 2)));
            tvStoimostMoykiPenogeneratorS.setText(String.valueOf(roundUp(stoimostMoykiS, 2)));
            tvChistAvtoPenogeneratorS.setText(String.valueOf(roundUp(CHistAvtoS, 2)));
            raschet2 = true;
        }

        if (!raschet1 && !raschet2) {
            Toast.makeText(getBaseContext(), "Заполните пожалуйста данные", Toast.LENGTH_SHORT).show();
            return;
        }
        tbllt2.setVisibility(View.VISIBLE);
        btnSravnenie.setBackgroundColor(gray);
        btnRaschet.setVisibility(View.VISIBLE);
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
        Intent intent = ClickLeftMenu.getIntent(ActivityAutoSravnenie2.this, item);
        startActivity(intent);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
