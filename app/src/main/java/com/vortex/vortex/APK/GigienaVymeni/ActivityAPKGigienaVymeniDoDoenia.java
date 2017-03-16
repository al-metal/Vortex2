package com.vortex.vortex.APK.GigienaVymeni;

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

public class ActivityAPKGigienaVymeniDoDoenia extends AppCompatActivity {

    String[] data = {"Приолит", "Виолит", "Фитолит", "Алгалит 50", "Алгалит"};
    String[] data2 = {"10", "15", "20", "30", "40", "100"};

    Spinner spinner;
    Spinner spinner2;
    TextView tvRashodGolova;
    double dblRashodGolova = 0;
    EditText etPrice;
    EditText etVes;
    EditText etKolichGolov;
    EditText etDay;
    EditText etKolichObrabotok;

    TextView tvSoimostKg;
    TextView tvKolichGigien;
    TextView tvStoimPromyvki;

    double stoimKg;
    double kolichGigien;
    double stoimObrabotki;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apkgigiena_vymeni_do_doenia);
        setTitle("Гигиена вымени до доения");

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        tvRashodGolova = (TextView) findViewById(R.id.tvRashodGolova);
        etPrice = (EditText) findViewById(R.id.etPrice);
        etVes = (EditText) findViewById(R.id.etVes);
        etKolichGolov = (EditText) findViewById(R.id.etKolichGolov);
        etDay = (EditText) findViewById(R.id.etDay);
        etKolichObrabotok = (EditText) findViewById(R.id.etKolichObrabotok);

        tvSoimostKg = (TextView) findViewById(R.id.tvSoimostKg);
        tvKolichGigien = (TextView) findViewById(R.id.tvKolichGigien);
        tvStoimPromyvki = (TextView) findViewById(R.id.tvStoimPromyvki);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setPrompt("Выберите средство Вортекс");

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                if (pos == 4) {
                    spinner2.setSelection(5);
                    spinner2.setClickable(false);
                } else {
                    spinner2.setSelection(0);
                    spinner2.setClickable(true);
                }
            }
        });

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner2.setAdapter(adapter2);

        spinner2.setPrompt("% разведения в пенном стакане");

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                if (spinner2.getSelectedItemId() == 5) {
                    if (spinner.getSelectedItemId() == 4)
                        spinner2.setSelection(5);
                    else
                        spinner2.setSelection(4);
                }
                if (spinner2.getSelectedItemId() == 0)
                    dblRashodGolova = 0.00008 * 4;
                else if (spinner2.getSelectedItemId() == 1)
                    dblRashodGolova = 0.00012 * 4;
                else if (spinner2.getSelectedItemId() == 2)
                    dblRashodGolova = 0.00016 * 4;
                else if (spinner2.getSelectedItemId() == 3)
                    dblRashodGolova = 0.00024 * 4;
                else if (spinner2.getSelectedItemId() == 4)
                    dblRashodGolova = 0.00032 * 4;
                else if (spinner2.getSelectedItemId() == 5)
                    dblRashodGolova = 0.0008 * 4;

                String s = String.valueOf(roundUp(dblRashodGolova, 5));
                tvRashodGolova.setText(s);
            }
        });
    }

    public BigDecimal roundUp(double value, int digits) {
        return new BigDecimal("" + value).setScale(digits, BigDecimal.ROUND_HALF_UP);
    }

    public void onClickRaschet(View view) {
if(etPrice.getText().length() == 0 || etVes.getText().length() == 0 || etKolichGolov.getText().length() == 0
        || etDay.getText().length() == 0 || etKolichObrabotok.getText().length() == 0 || dblRashodGolova == 0
        ){
    Toast.makeText(getBaseContext(), "Заполните пожалуйста все данные", Toast.LENGTH_SHORT).show();
    return;
        }
        double price = Double.parseDouble(etPrice.getText().toString());
        double ves = Double.parseDouble(etVes.getText().toString());
        double kolichGolov = Double.parseDouble(etKolichGolov.getText().toString());
        double period = Double.parseDouble(etDay.getText().toString());
        double kolichObrabotok = Double.parseDouble(etKolichObrabotok.getText().toString());

        stoimKg = price / ves;
        kolichGigien = dblRashodGolova*kolichGolov*period*kolichObrabotok;
        stoimObrabotki = dblRashodGolova * stoimKg;

        tvSoimostKg.setText(String.valueOf(roundUp(stoimKg, 2)));
        tvKolichGigien.setText(String.valueOf(roundUp(kolichGigien, 2)));
        tvStoimPromyvki.setText(String.valueOf(roundUp(stoimObrabotki, 2)));


    }

    public void onClickSravnenie(View view) {
        if(stoimKg ==0 || kolichGigien ==0 || stoimObrabotki ==0){
            Toast.makeText(getBaseContext(), "Данные для сравнения еще не расчитаны", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(ActivityAPKGigienaVymeniDoDoenia.this, ActivityAPKGigienaVymeniDoDoeniaSravnenie.class);
        intent.putExtra("stoimKg", stoimKg);
        intent.putExtra("kolichGigien", kolichGigien);
        intent.putExtra("stoimObrabotki", stoimObrabotki);
        intent.putExtra("dblRashodGolova", dblRashodGolova);
        startActivity(intent);
    }
}
