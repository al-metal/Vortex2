package com.vortex.vortex;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ActivityAutoSravnenie extends AppCompatActivity {

    //region array
    String[][] peno50ligthName = {{"Продукт", "Разбавление", "Кол-во шампуня, мл"},
            {"ВЫБРАТЬ СРЕДСТВО", "0", "0"},
            {"UNIOR", "1:60", "850"},
            {"TIRO, TIRO TONE", "1:80", "625"},
            {"MASTER, MASTER TONE", "1:100", "500"},
            {"PROFY", "1:120", "425"},
            {"DOZEX", "1:100", "500"},
            {"ACE", "1:140", "350"},
            {"MAGNAT", "1:110", "450"},
            {"DELICATE", "1:80", "625"},
            {"SOLO", "1:70", "725"},
            {"DIY", "1:120", "425"},
            {"GURU", "1:120", "350"}};

    String[][] peno50defaultName = {{"Продукт", "Разбавление", "Кол-во шампуня, мл"},
            {"ВЫБРАТЬ СРЕДСТВО", "0", "0"},
            {"UNIOR", "1:40", "1200"},
            {"TIRO, TIRO TONE", "1:60", "800"},
            {"NOVICE", "1:80", "600"},
            {"MASTER, MASTER TONE", "1:80", "600"},
            {"TUTOR ", "1:100", "500"},
            {"PROFY", "1:100", "500"},
            {"DOZEX", "1:90", "550"},
            {"SENZA", "1:120", "400"},
            {"ACE", "1:120", "400"},
            {"MAGNAT", "1:100", "500"},
            {"DELICATE", "1:60", "800"},
            {"SOLO", "1:55", "900"},
            {"DIY", "1:100", "500"},
            {"GURU", "1:120", "400"}};

    String[][] peno50hardName = {{"Продукт", "Разбавление", "Кол-во шампуня, мл"},
            {"ВЫБРАТЬ СРЕДСТВО", "0", "0"},
            {"NOVICE", "1:50", "1000"},
            {"TUTOR ", "1:60", "800"},
            {"PROFY", "1:60", "800"},
            {"SENZA", "1:80", "600"},
            {"ACE", "1:80", "600"},
            {"MAGNAT", "1:60", "800"},
            {"DIY", "1:60", "800"},
            {"GURU", "1:80", "450"}};

    String[][] penokomplektligthName = {{"Продукт", "Разбавление", "Кол-во шампуня, мл"},
            {"ВЫБРАТЬ СРЕДСТВО", "0", "0"},
            {"UNIOR", "1:4", "200"},
            {"TIRO, TIRO TONE", "1:6", "150"},
            {"MASTER, MASTER TONE", "1:7", "110"},
            {"PROFY", "1:10", "90"},
            {"DOZEX", "1:9", "100"},
            {"ACE", "1:12", "80"},
            {"MAGNAT", "1:9", "100"},
            {"DELICATE", "1:6", "150"},
            {"SOLO", "1:5", "170"},
            {"DIY", "1:10", "90"},
            {"GURU", "1:10", "80"}};

    String[][] penokomplektdefaultName = {{"Продукт", "Разбавление", "Кол-во шампуня, мл"},
            {"ВЫБРАТЬ СРЕДСТВО", "0", "0"},
            {"UNIOR", "1:2", "300"},
            {"TIRO, TIRO TONE", "1:4", "200"},
            {"NOVICE", "1:6", "145"},
            {"MASTER, MASTER TONE", "1:6", "145"},
            {"TUTOR ", "1:8", "110"},
            {"PROFY", "1:8", "110"},
            {"DOZEX", "1:7", "125"},
            {"SENZA", "1:10", "90"},
            {"ACE", "1:10", "90"},
            {"MAGNAT", "1:8", "110"},
            {"DELICATE", "1:4", "200"},
            {"SOLO", "1:4", "200"},
            {"DIY", "1:8", "110"},
            {"GURU", "1:12", "100"}};

    String[][] penokomplekthardName = {{"Продукт", "Разбавление", "Кол-во шампуня, мл"},
            {"ВЫБРАТЬ СРЕДСТВО", "0", "0"},
            {"NOVICE", "1:4", "200"},
            {"TUTOR ", "1:6", "145"},
            {"PROFY", "1:6", "145"},
            {"SENZA", "1:7", "125"},
            {"ACE", "1:7", "125"},
            {"MAGNAT", "1:6", "145"},
            {"DIY", "1:3", "250"},
            {"GURU", "1:10", "125"}};

    String[][] dozatronligthName = {{"Продукт", "Показатель концентрации %"},
            {"ВЫБРАТЬ СРЕДСТВО", "0", "0"},
            {"UNIOR", "2"},
            {"TIRO, TIRO TONE", "1.5"},
            {"MASTER, MASTER TONE", "1"},
            {"PROFY", "1"},
            {"DOZEX", "1"},
            {"ACE", "0.5"},
            {"DELICATE", "1.5"},
            {"SOLO", "1.5"},
            {"DIY", "1"}};

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
            {"DIY", "1"}};

    String[][] dozatronhardName = {{"Продукт", "Показатель концентрации %"},
            {"ВЫБРАТЬ СРЕДСТВО", "0", "0"},
            {"NOVICE", "2"},
            {"TUTOR ", "2"},
            {"PROFY", "2"},
            {"SENZA", "1.5"},
            {"ACE", "1.5"},
            {"DIY", "2"}};

    //endregion

    //region array 2
    String[][] peno50ligth = {
            {"UNIOR", "1:60", "850"},
            {"TIRO, TIRO TONE", "1:80", "625"},
            {"MASTER, MASTER TONE", "1:100", "500"},
            {"PROFY", "1:120", "425"},
            {"DOZEX", "1:100", "500"},
            {"ACE", "1:140", "350"},
            {"MAGNAT", "1:110", "450"},
            {"DELICATE", "1:80", "625"},
            {"SOLO", "1:70", "725"},
            {"DIY", "1:120", "425"},
            {"GURU", "1:120", "350"}};

    String[][] peno50default =
            {
                    {"UNIOR", "1:40", "1200"},
                    {"TIRO, TIRO TONE", "1:60", "800"},
                    {"NOVICE", "1:80", "600"},
                    {"MASTER, MASTER TONE", "1:80", "600"},
                    {"TUTOR ", "1:100", "500"},
                    {"PROFY", "1:100", "500"},
                    {"DOZEX", "1:90", "550"},
                    {"SENZA", "1:120", "400"},
                    {"ACE", "1:120", "400"},
                    {"MAGNAT", "1:100", "500"},
                    {"DELICATE", "1:60", "800"},
                    {"SOLO", "1:55", "900"},
                    {"DIY", "1:100", "500"},
                    {"GURU", "1:120", "400"}};
    String[][] peno50hard =
            {
                    {"NOVICE", "1:50", "1000"},
                    {"TUTOR ", "1:60", "800"},
                    {"PROFY", "1:60", "800"},
                    {"SENZA", "1:80", "600"},
                    {"ACE", "1:80", "600"},
                    {"MAGNAT", "1:60", "800"},
                    {"DIY", "1:60", "800"},
                    {"GURU", "1:80", "450"}};

    String[][] penokomplektligth =
            {
                    {"UNIOR", "1:4", "200"},
                    {"TIRO, TIRO TONE", "1:6", "150"},
                    {"MASTER, MASTER TONE", "1:7", "110"},
                    {"PROFY", "1:10", "90"},
                    {"DOZEX", "1:9", "100"},
                    {"ACE", "1:12", "80"},
                    {"MAGNAT", "1:9", "100"},
                    {"DELICATE", "1:6", "150"},
                    {"SOLO", "1:5", "170"},
                    {"DIY", "1:10", "90"},
                    {"GURU", "1:10", "80"}};
    String[][] penokomplektdefault =
            {
                    {"UNIOR", "1:2", "300"},
                    {"TIRO, TIRO TONE", "1:4", "200"},
                    {"NOVICE", "1:6", "145"},
                    {"MASTER, MASTER TONE", "1:6", "145"},
                    {"TUTOR ", "1:8", "110"},
                    {"PROFY", "1:8", "110"},
                    {"DOZEX", "1:7", "125"},
                    {"SENZA", "1:10", "90"},
                    {"ACE", "1:10", "90"},
                    {"MAGNAT", "1:8", "110"},
                    {"DELICATE", "1:4", "200"},
                    {"SOLO", "1:4", "200"},
                    {"DIY", "1:8", "110"},
                    {"GURU", "1:12", "100"}};
    String[][] ppenokomplekthard =
            {
                    {"NOVICE", "1:4", "200"},
                    {"TUTOR ", "1:6", "145"},
                    {"PROFY", "1:6", "145"},
                    {"SENZA", "1:7", "125"},
                    {"ACE", "1:7", "125"},
                    {"MAGNAT", "1:6", "145"},
                    {"DIY", "1:3", "250"},
                    {"GURU", "1:10", "125"}};

    String[][] dozatronligth =
            {
                    {"UNIOR", "2"},
                    {"TIRO, TIRO TONE", "1.5"},
                    {"MASTER, MASTER TONE", "1"},
                    {"PROFY", "1"},
                    {"DOZEX", "1"},
                    {"ACE", "0.5"},
                    {"DELICATE", "1.5"},
                    {"SOLO", "1.5"},
                    {"DIY", "1"}
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
                    {"DIY", "1"}
            };
    String[][] dozatronhard =
            {
                    {"NOVICE", "2"},
                    {"TUTOR ", "2"},
                    {"PROFY", "2"},
                    {"SENZA", "1.5"},
                    {"ACE", "1.5"},
                    {"DIY", "2"}
            };
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

    TextView tvPeno50;
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

    EditText etPriceVortex;
    EditText etPrice;
    EditText etMoyka;

    TableLayout tbllt3;
    TableLayout tbllt2;
    TableLayout tbllt1;

    double CHistAvtoS;
    double CHistAvto;

    Button btnSravnenie;
    Button btnRaschet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_sravnenie);
        setTitle("Сравнить с другим средством");

        btnSravnenie = (Button) findViewById(R.id.btnSravnenie);
        btnRaschet = (Button) findViewById(R.id.btnRaschet);

        tbllt1 = (TableLayout) findViewById(R.id.tbllt1);
        tbllt2 = (TableLayout) findViewById(R.id.tbllt2);
        tbllt3 = (TableLayout) findViewById(R.id.tbllt3);

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
        tvChistAvtoVortex = (TextView) findViewById(R.id.tvChistAvtoVortex);
        tvVortex = (TextView) findViewById(R.id.tvVortex);
        tvVortex2 = (TextView) findViewById(R.id.tvVortex2);
        tvVortex3 = (TextView) findViewById(R.id.tvVortex3);
        tvVortex4 = (TextView) findViewById(R.id.tvVortex4);

        tvRashodPenogenerator = (TextView) findViewById(R.id.tvRashodPenogenerator);
        tvRashodPenoKomplekt = (TextView) findViewById(R.id.tvRashodPenoKomplekt);
        tvStoimostMoyki = (TextView) findViewById(R.id.tvStoimostMoyki);
        tvChistAvto = (TextView) findViewById(R.id.tvChistAvto);

        tvPeno50 = (EditText) findViewById(R.id.etPeno50);
        tvPenokomlektLitr = (EditText) findViewById(R.id.etPenokomlektLitr);
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
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        spinner9.setAdapter(adapter);

        spinner9.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                if (pos == 0) {
                    return;
                }

                ViewProductVortex(pos);
                tbllt1.setVisibility(View.VISIBLE);
            }

            public void ViewProductVortex(int pos) {

                nameProduct = String.valueOf(tovars.get(pos));
                tvVortex.setText(nameProduct);
                tvVortex2.setText(nameProduct);
                tvVortex3.setText(nameProduct);
                tvVortex4.setText(nameProduct);

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

    public BigDecimal roundUp(double value, int digits) {
        return new BigDecimal("" + value).setScale(digits, BigDecimal.ROUND_HALF_UP);
    }

    public void onClickRaschet(View view) {

        if (etMoyka.getText().length() == 0) {
            Toast.makeText(getBaseContext(), "Заполните пожалуйста все данные", Toast.LENGTH_SHORT).show();
            return;
        }
        tbllt3.setVisibility(View.VISIBLE);
        int gray = Color.parseColor("#7B7979");
        btnRaschet.setBackgroundColor(gray);

        double avtoClient = CHistAvtoS;
        double avtoVortex = CHistAvto;
        double stoimostMoykiPrice = Double.parseDouble(etMoyka.getText().toString());

        double mashinBolshe = avtoVortex - avtoClient;
        double pribyl = stoimostMoykiPrice * mashinBolshe;

        tvMashinBolshe.setText(String.valueOf(roundUp(mashinBolshe, 0)));
        tvPribyl.setText(String.valueOf(roundUp(pribyl, 2)));
    }

    public void onClickSravnenie(View view) {
        if (etPriceVortex.getText().length() == 0 || tvPenokomlektLitr.getText().length() == 0 || tvPeno50.getText().length() == 0 ||
                etPrice.getText().length() == 0) {
            Toast.makeText(getBaseContext(), "Заполните пожалуйста все данные", Toast.LENGTH_SHORT).show();
            return;
        }

        tbllt2.setVisibility(View.VISIBLE);
        int gray = Color.parseColor("#7B7979");
        btnSravnenie.setBackgroundColor(gray);

        double objem1Litr = Double.parseDouble(tvPenokomlektLitrVortex.getText().toString());
        double objemPeno50 = Double.parseDouble(tvPeno50Vortex.getText().toString());
        double priceVortex = Double.parseDouble(etPriceVortex.getText().toString());

        double rashodPenokomplekt = objem1Litr / 3;
        double rashodPenogenerator = objemPeno50 / 14;
        double stoimostMoyki = 1 * priceVortex / 20000 * rashodPenogenerator;
        CHistAvto = 20000 / rashodPenogenerator;

        tvRashodPenogeneratorVortex.setText(String.valueOf(roundUp(rashodPenogenerator, 2)));
        tvRashodPenoKomplektVortex.setText(String.valueOf(roundUp(rashodPenokomplekt, 2)));
        tvStoimostMoykiVortex.setText(String.valueOf(roundUp(stoimostMoyki, 2)));
        tvChistAvtoVortex.setText(String.valueOf(roundUp(CHistAvto, 2)));

        double objem1LitrS = Double.parseDouble(tvPenokomlektLitr.getText().toString());
        double objemPeno50S = Double.parseDouble(tvPeno50.getText().toString());
        double price = Double.parseDouble(etPrice.getText().toString());

        double rashodPenokomplektS = objem1LitrS / 3;
        double rashodPenogeneratorS = objemPeno50S / 14;
        double stoimostMoykiS = 1 * price / 20000 * rashodPenogeneratorS;
        CHistAvtoS = 20000 / rashodPenogeneratorS;

        tvRashodPenogenerator.setText(String.valueOf(roundUp(rashodPenogeneratorS, 2)));
        tvRashodPenoKomplekt.setText(String.valueOf(roundUp(rashodPenokomplektS, 2)));
        tvStoimostMoyki.setText(String.valueOf(roundUp(stoimostMoykiS, 2)));
        tvChistAvto.setText(String.valueOf(roundUp(CHistAvtoS, 2)));
    }
}
