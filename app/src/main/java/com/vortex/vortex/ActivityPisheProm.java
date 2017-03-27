package com.vortex.vortex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.vortex.vortex.APK.ActivityAPKDezinfekciyaProfilaktikaForbicid;
import com.vortex.vortex.APK.ActivityAPKDezinfekciyaProfilaktikaForbicidSravnenie;

import java.math.BigDecimal;

public class ActivityPisheProm extends AppCompatActivity {
    String[] data = {"BIOTEC", "BIOTEC C", "BIOTEC Super", "BIOTEC М", "KSILAN", "KSILAN K", "KSILAN Super", "KSILAN М", "Tank CB 46",
            "Tank CA27", "Tank FA18", "Tank FB17", "TANK FBD 0803/1", "TANK FB 36", "TANK FBD 0402/1", "TANK LBD 0107/1", "TANK LBD 1002/2",
            "TANK FBD 0902/2", "TANKCAD 1415/3", "TANK FN"};
    double dblplotnost;
    double PriceLitr;
    double StoimostRastvora;
    double RashodSrdstvaMoyka;
    double RashodSredstvaMes;
    double PriceSredstva;

    EditText etPrice;
    EditText etKoncentratRastvor;
    EditText etObjemRastvor;
    EditText etOperacSutki;
    EditText etDay;

    TextView tvPlotnost;
    TextView tvPriceLitr;
    TextView tvStoimostRastvora;
    TextView tvRashodSrdstvaMoyka;
    TextView tvRashodSredstvaMes;
    TextView tvPriceSredstva;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pishe_prom);
        setTitle("Средства для предприятий пищевой промышленности");

        etPrice = (EditText) findViewById(R.id.etPrice);
        etKoncentratRastvor = (EditText) findViewById(R.id.etKoncentratRastvor);
        etObjemRastvor = (EditText) findViewById(R.id.etObjemRastvor);
        etOperacSutki = (EditText) findViewById(R.id.etOperacSutki);
        etDay = (EditText) findViewById(R.id.etDay);

        tvPlotnost = (TextView) findViewById(R.id.tvPlotnost);
        tvPriceLitr = (TextView) findViewById(R.id.tvPriceLitr);
        tvStoimostRastvora = (TextView) findViewById(R.id.tvStoimostRastvora);
        tvRashodSrdstvaMoyka = (TextView) findViewById(R.id.tvRashodSrdstvaMoyka);
        tvRashodSredstvaMes = (TextView) findViewById(R.id.tvRashodSredstvaMes);
        tvPriceSredstva = (TextView) findViewById(R.id.tvPriceSredstva);

        Spinner spinner = (Spinner) findViewById(R.id.spinner7);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        // заголовок
        spinner.setPrompt("Выберите средство Вортекс");

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                // Set adapter flag that something has been chosen

                if(pos == 0){
                    dblplotnost = 1.252;
                }else if(pos == 1){
                    dblplotnost = 1.226;
                }else if(pos == 2){
                    dblplotnost = 1.230;
                }else if(pos == 3){
                    dblplotnost = 1.198;
                }else if(pos == 4){
                    dblplotnost = 1.22;
                }else if(pos == 5){
                    dblplotnost = 1.2;
                }else if(pos == 6){
                    dblplotnost = 1.28;
                }else if(pos == 7){
                    dblplotnost = 1.167;
                }else if(pos == 8){
                    dblplotnost = 1.457;
                }else if(pos == 9){
                    dblplotnost = 1.221;
                }else if(pos == 10){
                    dblplotnost = 1.157;
                }else if(pos == 11){
                    dblplotnost = 1.194;
                }else if(pos == 12){
                    dblplotnost = 1.170;
                }else if(pos == 13){
                    dblplotnost = 1.391;
                }else if(pos == 14){
                    dblplotnost = 1.173;
                }else if(pos == 15){
                    dblplotnost = 1.145;
                }else if(pos == 16){
                    dblplotnost = 1.122;
                }else if(pos == 17){
                    dblplotnost = 1.104;
                }else if(pos == 18){
                    dblplotnost = 1.16;
                }else if(pos == 19){
                    dblplotnost = 1.025;
                }
                tvPlotnost.setText(String.valueOf(dblplotnost));
            }
        });

    }

    public void onClickRaschet(View view) {
        if (etPrice.getText().length() == 0 || etKoncentratRastvor.getText().length() == 0 || etObjemRastvor.getText().length() == 0
                || etOperacSutki.getText().length() == 0 || etDay.getText().length() == 0 ) {
            Toast.makeText(getBaseContext(), "Заполните пожалуйста все данные", Toast.LENGTH_SHORT).show();
            return;
        }

        double dblPrice = Double.parseDouble(etPrice.getText().toString());
        double dblKoncentratRastvor = Double.parseDouble(etKoncentratRastvor.getText().toString());
        double dblObjemRastvor = Double.parseDouble(etObjemRastvor.getText().toString());
        double dblOperacSutki = Double.parseDouble(etOperacSutki.getText().toString());
        double dblDay = Double.parseDouble(etDay.getText().toString());


        PriceLitr = dblPrice*dblplotnost;
        StoimostRastvora = dblKoncentratRastvor * dblObjemRastvor * PriceLitr / 100;
        RashodSrdstvaMoyka = dblKoncentratRastvor * dblObjemRastvor / 100;
        RashodSredstvaMes = RashodSrdstvaMoyka * dblOperacSutki * dblDay;
        PriceSredstva = PriceLitr*RashodSredstvaMes;

        tvPriceLitr.setText(String.valueOf(roundUp(PriceLitr, 2)));
        tvStoimostRastvora.setText(String.valueOf(roundUp(StoimostRastvora, 2)));
        tvRashodSrdstvaMoyka.setText(String.valueOf(roundUp(RashodSrdstvaMoyka, 2)));
        tvRashodSredstvaMes.setText(String.valueOf(roundUp(RashodSredstvaMes, 2)));
        tvPriceSredstva.setText(String.valueOf(roundUp(PriceSredstva, 2)));
    }

    public BigDecimal roundUp(double value, int digits) {
        return new BigDecimal("" + value).setScale(digits, BigDecimal.ROUND_HALF_UP);
    }

    public void onClickSravnenie(View view) {
        if (PriceLitr == 0 || StoimostRastvora == 0 || RashodSrdstvaMoyka == 0
                || RashodSredstvaMes == 0 || PriceSredstva == 0) {
            Toast.makeText(getBaseContext(), "Данные для сравнения еще не расчитаны", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(ActivityPisheProm.this, ActivityPishePromSravnenie.class);
        intent.putExtra("PriceLitr", PriceLitr);
        intent.putExtra("StoimostRastvora", StoimostRastvora);
        intent.putExtra("RashodSrdstvaMoyka", RashodSrdstvaMoyka);
        intent.putExtra("RashodSredstvaMes", RashodSredstvaMes);
        intent.putExtra("PriceSredstva", PriceSredstva);
        startActivity(intent);
    }
}