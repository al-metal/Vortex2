package com.vortex.vortex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ActivityAutoSravnenie extends AppCompatActivity {

    //region array
    String[][] peno50ligthName = {{"Продукт","Разбавление", "Кол-во шампуня, мл" }, {"Unior","1:60", "850" },
            {"Tiro, Tiro Tone","1:80", "625" },
            {"Master, Master Tone","1:100", "500" },
            {"Profy","1:120", "425" },
            {"Dozex","1:100", "500" },
            {"Ace","1:140", "350" },
            {"Magnat","1:110", "450" },
            {"Delicate","1:80", "625" },
            {"Solo","1:70", "725" },
            {"DIY","1:120", "425" }};

    String[][] peno50defaultName = {{"Продукт","Разбавление", "Кол-во шампуня, мл" },
            {"Unior","1:40", "1200" },
            {"Tiro, Tiro Tone","1:60", "800" },
            {"Novice","1:80", "600" },
            {"Master, Master Tone","1:80", "600" },
            {"Tutor ","1:100", "500" },
            {"Profy","1:100", "500" },
            {"Dozex","1:90", "550" },
            {"Senza","1:120", "400" },
            {"Ace","1:120", "400" },
            {"Magnat","1:100", "500" },
            {"Delicate","1:60", "800" },
            {"Solo","1:55", "900" },
            {"DIY","1:100", "500" }};

    String[][] peno50hardName = {{"Продукт","Разбавление", "Кол-во шампуня, мл" },
            {"Novice","1:50", "1000" },
            {"Tutor ","1:60", "800" },
            {"Profy","1:60", "800" },
            {"Senza","1:80", "600" },
            {"Ace","1:80", "600" },
            {"Magnat","1:60", "800" },
            {"DIY","1:60", "800" }};

    String[][] penokomplektligthName = {{"Продукт","Разбавление", "Кол-во шампуня, мл" },
            {"Unior","1:4", "200" },
            {"Tiro, Tiro Tone","1:6", "150" },
            {"Master, Master Tone","1:7", "110" },
            {"Profy","1:10", "90" },
            {"Dozex","1:9", "100" },
            {"Ace","1:12", "80" },
            {"Magnat","1:9", "100" },
            {"Delicate","1:6", "150" },
            {"Solo","1:5", "170" },
            {"DIY","1:10", "90" }};

    String[][] penokomplektdefaultName = {{"Продукт","Разбавление", "Кол-во шампуня, мл" },
            {"Unior","1:2", "300" },
            {"Tiro, Tiro Tone","1:4", "200" },
            {"Novice","1:6", "145" },
            {"Master, Master Tone","1:6", "145" },
            {"Tutor ","1:8", "110" },
            {"Profy","1:8", "110" },
            {"Dozex","1:7", "125" },
            {"Senza","1:10", "90" },
            {"Ace","1:10", "90" },
            {"Magnat","1:8", "110" },
            {"Delicate","1:4", "200" },
            {"Solo","1:4", "200" },
            {"DIY","1:8", "110" }};

    String[][] penokomplekthardName = {{"Продукт","Разбавление", "Кол-во шампуня, мл" },
            {"Novice","1:4", "200" },
            {"Tutor ","1:6", "145" },
            {"Profy","1:6", "145" },
            {"Senza","1:7", "125" },
            {"Ace","1:7", "125" },
            {"Magnat","1:6", "145" },
            {"DIY","1:3", "250" }};

    String[][] dozatronligthName= {{"Продукт","Показатель концентрации %" },
            {"Unior", "2" },
            {"Tiro, Tiro Tone", "1.5" },
            {"Master, Master Tone", "1" },
            {"Profy", "1" },
            {"Dozex", "1" },
            {"Ace", "0.5" },
            {"Delicate", "1.5" },
            {"Solo", "1.5" },
            {"DIY", "1" }};

    String[][] dozatrondefaultName = {{"Продукт","Показатель концентрации %"},
            {"Unior", "3" },
            {"Tiro, Tiro Tone", "2" },
            {"Novice", "1.5" },
            {"Master, Master Tone", "1.5" },
            {"Tutor ", "1" },
            {"Profy", "1" },
            {"Dozex", "1" },
            {"Senza", "1" },
            {"Ace", "1" },
            {"Delicate", "2" },
            {"Solo", "2" },
            {"DIY", "1" }};

    String[][] dozatronhardName = {{"Продукт","Показатель концентрации %"},
            {"Novice", "2" },
            {"Tutor ", "2" },
            {"Profy", "2" },
            {"Senza", "1.5" },
            {"Ace", "1.5" },
            {"DIY", "2" }};

    //endregion

    //region array 2
    String[][] peno50ligth = {{"Unior","1:60", "850" },
        {"Tiro, Tiro Tone","1:80", "625" },
        {"Master, Master Tone","1:100", "500" },
        {"Profy","1:120", "425" },
        {"Dozex","1:100", "500" },
        {"Ace","1:140", "350" },
        {"Magnat","1:110", "450" },
        {"Delicate","1:80", "625" },
        {"Solo","1:70", "725" },
        {"DIY","1:120", "425" }};
    String[][] peno50default =
    {
        {"Unior","1:40", "1200" },
        {"Tiro, Tiro Tone","1:60", "800" },
        {"Novice","1:80", "600" },
        {"Master, Master Tone","1:80", "600" },
        {"Tutor ","1:100", "500" },
        {"Profy","1:100", "500" },
        {"Dozex","1:90", "550" },
        {"Senza","1:120", "400" },
        {"Ace","1:120", "400" },
        {"Magnat","1:100", "500" },
        {"Delicate","1:60", "800" },
        {"Solo","1:55", "900" },
        {"DIY","1:100", "500" }
    };
    String[][] peno50hard =
    {
        {"Novice","1:50", "1000" },
        {"Tutor ","1:60", "800" },
        {"Profy","1:60", "800" },
        {"Senza","1:80", "600" },
        {"Ace","1:80", "600" },
        {"Magnat","1:60", "800" },
        {"DIY","1:60", "800" }
    };

    String[][] penokomplektligth =
    {
        {"Unior","1:4", "200" },
        {"Tiro, Tiro Tone","1:6", "150" },
        {"Master, Master Tone","1:7", "110" },
        {"Profy","1:10", "90" },
        {"Dozex","1:9", "100" },
        {"Ace","1:12", "80" },
        {"Magnat","1:9", "100" },
        {"Delicate","1:6", "150" },
        {"Solo","1:5", "170" },
        {"DIY","1:10", "90" }
    };
    String[][] penokomplektdefault =
    {
        {"Unior","1:2", "300" },
        {"Tiro, Tiro Tone","1:4", "200" },
        {"Novice","1:6", "145" },
        {"Master, Master Tone","1:6", "145" },
        {"Tutor ","1:8", "110" },
        {"Profy","1:8", "110" },
        {"Dozex","1:7", "125" },
        {"Senza","1:10", "90" },
        {"Ace","1:10", "90" },
        {"Magnat","1:8", "110" },
        {"Delicate","1:4", "200" },
        {"Solo","1:4", "200" },
        {"DIY","1:8", "110" }
    };
    String[][] ppenokomplekthard =
    {
        {"Novice","1:4", "200" },
        {"Tutor ","1:6", "145" },
        {"Profy","1:6", "145" },
        {"Senza","1:7", "125" },
        {"Ace","1:7", "125" },
        {"Magnat","1:6", "145" },
        {"DIY","1:3", "250" }
    };

    String[][] dozatronligth =
    {
        {"Unior", "2" },
        {"Tiro, Tiro Tone", "1.5" },
        {"Master, Master Tone", "1" },
        {"Profy", "1" },
        {"Dozex", "1" },
        {"Ace", "0.5" },
        {"Delicate", "1.5" },
        {"Solo", "1.5" },
        {"DIY", "1" }
    };
    String[][] dozatrondefault =
    {
        {"Unior", "3" },
        {"Tiro, Tiro Tone", "2" },
        {"Novice", "1.5" },
        {"Master, Master Tone", "1.5" },
        {"Tutor ", "1" },
        {"Profy", "1" },
        {"Dozex", "1" },
        {"Senza", "1" },
        {"Ace", "1" },
        {"Delicate", "2" },
        {"Solo", "2" },
        {"DIY", "1" }
    };
    String[][] dozatronhard =
    {
        {"Novice", "2" },
        {"Tutor ", "2" },
        {"Profy", "2" },
        {"Senza", "1.5" },
        {"Ace", "1.5" },
        {"DIY", "2" }
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

    EditText etPriceVortex;
    EditText etPrice;
    EditText etMoyka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_sravnenie);
        setTitle("Сравнить средства");

        tvChastVortex= (TextView) findViewById(R.id.tvChastVortex);
        tvPeno25Vortex= (TextView) findViewById(R.id.tvPeno25Vortex);
        tvPeno50Vortex= (TextView) findViewById(R.id.tvPeno50Vortex);
        tvPeno100Vortex= (TextView) findViewById(R.id.tvPeno100Vortex);
        tvPenokomlektChastVortex= (TextView) findViewById(R.id.tvPenokomlektChastVortex);
        tvPenokomlektLitrVortex= (TextView) findViewById(R.id.tvPenokomlektLitrVortex);
        tvDozatronVortex= (TextView) findViewById(R.id.tvDozatronVortex);
        tvRashodPenogeneratorVortex= (TextView) findViewById(R.id.tvRashodPenogeneratorVortex);
        tvRashodPenoKomplektVortex= (TextView) findViewById(R.id.tvRashodPenoKomplektVortex);
        tvStoimostMoykiVortex= (TextView) findViewById(R.id.tvStoimostMoykiVortex);
        tvChistAvtoVortex= (TextView) findViewById(R.id.tvChistAvtoVortex);

        tvRashodPenogenerator= (TextView) findViewById(R.id.tvRashodPenogenerator);
        tvRashodPenoKomplekt= (TextView) findViewById(R.id.tvRashodPenoKomplekt);
        tvStoimostMoyki= (TextView) findViewById(R.id.tvStoimostMoyki);
        tvChistAvto= (TextView) findViewById(R.id.tvChistAvto);

        tvPeno50= (EditText) findViewById(R.id.etPeno50);
        tvPenokomlektLitr= (EditText) findViewById(R.id.etPenokomlektLitr);
        tvMashinBolshe= (TextView) findViewById(R.id.tvMashinBolshe);
        tvPribyl= (TextView) findViewById(R.id.tvPribyl);

        etPriceVortex= (EditText) findViewById(R.id.etPriceVortex);
        etPrice= (EditText) findViewById(R.id.etPrice);
        etMoyka= (EditText) findViewById(R.id.etMoyka);

        dh = getIntent().getExtras().getString("dh");
        sredstvo = getIntent().getExtras().getString("sredstvo");

        data = ReturnData(dh, sredstvo);

        spinner9 = (Spinner) findViewById(R.id.spinner9);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner9.setAdapter(adapter);

        spinner9.setPrompt("Выберите средство");

        spinner9.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                /*if(pos == 0){
                    ViewProductVortex(pos);
                }else if (pos == 7)*/
                    ViewProductVortex(pos);

            }

            public void ViewProductVortex(int pos) {
                nameProduct = String.valueOf(tovars.get(pos));


                if (dh.contains("ligth"))
                {
                    for (int i = 0; peno50ligth.length > i; i++)
                    {
                        if (peno50ligth[i][0] == nameProduct)
                        {
                            penogenchast = peno50ligth[i][1];
                            penogenob = peno50ligth[i][2];
                            break;
                        }
                    }
                    for (int i = 0; penokomplektligth.length > i; i++)
                    {
                        if (penokomplektligth[i][0] == nameProduct)
                        {
                            penokomchast = penokomplektligth[i][1];
                            penokomob = penokomplektligth[i][2];
                            break;
                        }
                    }
                    for (int i = 0; dozatronligth.length > i; i++)
                    {
                        if (dozatronligth[i][0] == nameProduct)
                        {
                            dozatronpercent = dozatronligth[i][1];
                            break;
                        }
                    }
                }
                else if (dh.contains("default"))
                {
                    for (int i = 0; peno50default.length > i; i++)
                    {
                        if (peno50default[i][0] == nameProduct)
                        {
                            penogenchast = peno50default[i][1];
                            penogenob = peno50default[i][2];
                            break;
                        }
                    }
                    for (int i = 0; penokomplektdefault.length > i; i++)
                    {
                        if (penokomplektdefault[i][0] == nameProduct)
                        {
                            penokomchast = penokomplektdefault[i][1];
                            penokomob = penokomplektdefault[i][2];
                            break;
                        }
                    }
                    for (int i = 0; dozatrondefault.length > i; i++)
                    {
                        if (dozatrondefault[i][0] == nameProduct)
                        {
                            dozatronpercent = dozatrondefault[i][1];
                            break;
                        }
                    }
                }
                else if (dh.contains("hard"))
                {

                    for (int i = 0; peno50hard.length > i; i++)
                    {
                        if (peno50hard[i][0] == nameProduct)
                        {
                            penogenchast = peno50hard[i][1];
                            penogenob = peno50hard[i][2];
                            break;
                        }
                    }
                    for (int i = 0; ppenokomplekthard.length > i; i++)
                    {
                        if (ppenokomplekthard[i][0] == nameProduct)
                        {
                            penokomchast = ppenokomplekthard[i][1];
                            penokomob = ppenokomplekthard[i][2];
                            break;
                        }
                    }
                    for (int i = 0; dozatronhard.length > i; i++)
                    {
                        if (dozatronhard[i][0] == nameProduct)
                        {
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

        if(sredstvo.contains("peno50ligth"))
            arr = peno50ligthName;
        else if(sredstvo.contains("peno50default"))
            arr = peno50defaultName;
        else if(sredstvo.contains("peno50hard"))
            arr = peno50hardName;
        else if(sredstvo.contains("penokomplektligth"))
            arr = penokomplektligthName;
        else if(sredstvo.contains("penokomplektdefault"))
            arr = penokomplektdefaultName;
        else if(sredstvo.contains("penokomplekthard"))
            arr = penokomplekthardName;
        else if(sredstvo.contains("dozatronligth"))
            arr = dozatronligthName;
        else if(sredstvo.contains("dozatrondefault"))
            arr = dozatrondefaultName;
        else if(sredstvo.contains("dozatronhard"))
            arr = dozatronhardName;

        int count = arr.length;
        dat = new String[count-1];
        for(int i = 1; count > i ; i++){
            dat[i-1] = arr[i][0];
            tovars.add(arr[i][0]);
        }

        return dat;
    }

    public BigDecimal roundUp(double value, int digits) {
        return new BigDecimal("" + value).setScale(digits, BigDecimal.ROUND_HALF_UP);
    }


    public void onClickRaschet(View view) {
        if (etPriceVortex.getText().length() == 0) {
            Toast.makeText(getBaseContext(), "Заполните пожалуйста все данные", Toast.LENGTH_SHORT).show();
            return;
        }
        double objem1Litr = Double.parseDouble(tvPenokomlektLitrVortex.getText().toString());
        double objemPeno50 = Double.parseDouble(tvPeno50Vortex.getText().toString());
        double priceVortex = Double.parseDouble(etPriceVortex.getText().toString());

        double rashodPenokomplekt = objem1Litr/3;
        double rashodPenogenerator = objemPeno50/14;
        double stoimostMoyki = 1*priceVortex/20000*rashodPenogenerator;
        double CHistAvto = 20000/rashodPenogenerator;

        tvRashodPenogeneratorVortex.setText(String.valueOf(roundUp(rashodPenogenerator, 2)));
        tvRashodPenoKomplektVortex.setText(String.valueOf(roundUp(rashodPenokomplekt, 2)));
        tvStoimostMoykiVortex.setText(String.valueOf(roundUp(stoimostMoyki, 2)));
        tvChistAvtoVortex.setText(String.valueOf(roundUp(CHistAvto, 2)));

        if (tvPenokomlektLitr.getText().length() == 0 || tvPeno50.getText().length() == 0 ||
                etPrice.getText().length() == 0) {
            return;
        }

        double objem1LitrS = Double.parseDouble(tvPenokomlektLitr.getText().toString());
        double objemPeno50S = Double.parseDouble(tvPeno50.getText().toString());
        double price = Double.parseDouble(etPrice.getText().toString());

        double rashodPenokomplektS = objem1LitrS/3;
        double rashodPenogeneratorS = objemPeno50S/14;
        double stoimostMoykiS = 1*price/20000*rashodPenogeneratorS;
        double CHistAvtoS = 20000/rashodPenogeneratorS;

        tvRashodPenogenerator.setText(String.valueOf(roundUp(rashodPenogeneratorS, 2)));
        tvRashodPenoKomplekt.setText(String.valueOf(roundUp(rashodPenokomplektS, 2)));
        tvStoimostMoyki.setText(String.valueOf(roundUp(stoimostMoykiS, 2)));
        tvChistAvto.setText(String.valueOf(roundUp(CHistAvtoS, 2)));

        double avtoClient = CHistAvtoS;
        double avtoVortex = CHistAvto;
        double stoimostMoykiPrice = Double.parseDouble(etMoyka.getText().toString());

        double mashinBolshe = avtoVortex-avtoClient;
        double pribyl = stoimostMoykiPrice*mashinBolshe;

        tvMashinBolshe.setText(String.valueOf(roundUp(mashinBolshe, 0)));
        tvPribyl.setText(String.valueOf(roundUp(pribyl, 2)));





    }
}
