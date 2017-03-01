package com.vortex.vortex.APK;

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

import com.vortex.vortex.R;

import java.math.BigDecimal;

public class ApkMoySredstvaActivity extends AppCompatActivity {

    String[] data = {"Biotec M", "Biotec C", "Biotec", "Biotec  Super", "Ksilan M", "Ksilan K", "Ksilan", "Ksilan Super"};
    double plotnost = 0;
    private TextView tvPlotnost;
    private TextView tvStoimKg;
    private TextView tvStoimL;
    private TextView tvStoimPromiv;
    private TextView tvStoimLitrSmes;

    private EditText etStoim;
    private EditText etKoncentrat;
    private EditText etVanna;
    private EditText etVes;

    double resStoimKg;
    double resStoimL;
    double resStoimPromiv;
    double resStoimSmesi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apk_moy_sredstva);
        setTitle("Расчет стоимости рабочего раствора");

        Spinner spinner = (Spinner) findViewById(R.id.spinner);

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
                tvPlotnost = (TextView) findViewById(R.id.tvPlotnost);
                if(pos == 0){
                    plotnost = 1.2;
                }else if(pos == 1){
                    plotnost = 1.22;
                }else if(pos == 2){
                    plotnost = 1.25;
                }else if(pos == 3){
                    plotnost = 1.23;
                }else if(pos == 4){
                    plotnost = 1.16;
                }else if(pos == 5){
                    plotnost = 1.2;
                }else if(pos == 6){
                    plotnost = 1.22;
                }else if(pos == 7){
                    plotnost = 1.28;
                }

                tvPlotnost.setText(String.valueOf(plotnost));
            }
        });
    }

    public void onClickBtn(View view) {
        tvStoimKg = (TextView) findViewById(R.id.tvStoimKg);
        tvStoimL = (TextView) findViewById(R.id.tvStoimL);
        tvStoimPromiv = (TextView) findViewById(R.id.tvStoimPromiv);
        tvStoimLitrSmes = (TextView) findViewById(R.id.tvStoimLitrSmes);

        etStoim = (EditText)findViewById(R.id.etStoim);
        etKoncentrat = (EditText)findViewById(R.id.etKoncentrat);
        etVanna = (EditText)findViewById(R.id.etVanna);
        etVes = (EditText)findViewById(R.id.etVes);

        if(etVes.getText().length() ==0 || etVanna.getText().length() ==0 || etKoncentrat.getText().length() ==0 || etStoim.getText().length() ==0){
            Toast.makeText(getBaseContext(), "Заполните пожалуйста все данные", Toast.LENGTH_SHORT).show();
            return;
        }

        double stoimost = 0;
        double ves = 0;
        double koncentrat = 0;
        double vanna = 0;

        resStoimKg = 0;
        resStoimL = 0;
        resStoimPromiv = 0;
        resStoimSmesi = 0;

        stoimost = Double.parseDouble(etStoim.getText().toString());
        koncentrat = Double.parseDouble(etKoncentrat.getText().toString());
        vanna = Double.parseDouble(etVanna.getText().toString());
        ves = Double.parseDouble(etVes.getText().toString());

        resStoimKg = stoimost/ves;
        resStoimL = resStoimKg * plotnost;
        resStoimPromiv = (resStoimL*koncentrat*vanna)/100;
        resStoimSmesi = resStoimPromiv / vanna;

        tvStoimKg.setText(String.valueOf(roundUp(resStoimKg, 2)));
        tvStoimL.setText(String.valueOf(roundUp(resStoimL, 2)));
        tvPlotnost.setText(String.valueOf(roundUp(plotnost, 2)));
        tvStoimLitrSmes.setText(String.valueOf(roundUp(resStoimSmesi, 2)));
        tvStoimPromiv.setText(String.valueOf(roundUp(resStoimPromiv, 2)));
    }

    public BigDecimal roundUp(double value, int digits){
        return new BigDecimal(""+value).setScale(digits, BigDecimal.ROUND_HALF_UP);
    }

    public void onClickBtnSravnenie(View view) {
        if(resStoimKg ==0 || resStoimL ==0 || resStoimPromiv ==0 || resStoimSmesi ==0){
            Toast.makeText(getBaseContext(), "Данные для сравнения еще не расчитаны", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(ApkMoySredstvaActivity.this, ApkMoySredstvaSravnenieActivity.class);
        intent.putExtra("resStoimKg", resStoimKg);
        intent.putExtra("resStoimL", resStoimL);
        intent.putExtra("resStoimPromiv", resStoimPromiv);
        intent.putExtra("resStoimSmesi", resStoimSmesi);
        intent.putExtra("resPlotnost", plotnost);
        startActivity(intent);
    }
}
