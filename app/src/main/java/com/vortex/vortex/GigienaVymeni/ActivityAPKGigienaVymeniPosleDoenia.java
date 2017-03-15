package com.vortex.vortex.GigienaVymeni;

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

public class ActivityAPKGigienaVymeniPosleDoenia extends AppCompatActivity {

    String[] data = {"5.6", "2.8", "2.8", "3.2", "3.2", "5.6"};
    double dblRashodGolova = 0;

    Spinner spinner4;
    EditText etPrice;
    EditText etVes;
    EditText etKolichGolov;
    EditText etPeriodObrabotki;
    EditText etKolichVDen;
    TextView tvRashodGolova;
    TextView tvStoimostKg;
    TextView tvKolichGigien;
    TextView tvStoimObrabotki;

    double stoimKg;
    double kolichGigien;
    double stoimObrabotki;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apkgigiena_vymeni_posle_doenia);
        setTitle("Гигиена вымени после доения");

        spinner4 = (Spinner) findViewById(R.id.spinner4);
        etPrice = (EditText) findViewById(R.id.etPrice);
        etVes = (EditText) findViewById(R.id.etVes);
        etKolichGolov = (EditText) findViewById(R.id.etKolichGolov);
        etPeriodObrabotki = (EditText) findViewById(R.id.etPeriodObrabotki);
        etKolichVDen = (EditText) findViewById(R.id.etKolichVDen);
        tvRashodGolova = (TextView) findViewById(R.id.tvRashodGolova);
        tvStoimostKg = (TextView) findViewById(R.id.tvStoimostKg);
        tvKolichGigien = (TextView) findViewById(R.id.tvKolichGigien);
        tvStoimObrabotki = (TextView) findViewById(R.id.tvStoimObrabotki);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner4.setAdapter(adapter);

        spinner4.setPrompt("Расход на одну голову, гр");

        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                if (spinner4.getSelectedItemId() == 0)
                    dblRashodGolova = 5.6;
                else if (spinner4.getSelectedItemId() == 1)
                    dblRashodGolova = 2.8;
                else if (spinner4.getSelectedItemId() == 2)
                    dblRashodGolova = 2.8;
                else if (spinner4.getSelectedItemId() == 3)
                    dblRashodGolova = 3.2;
                else if (spinner4.getSelectedItemId() == 4)
                    dblRashodGolova = 3.2;
                else if (spinner4.getSelectedItemId() == 5)
                    dblRashodGolova = 5.6;

                String s = String.valueOf(roundUp(dblRashodGolova, 1));
                tvRashodGolova.setText(s);
            }
        });
    }

    public BigDecimal roundUp(double value, int digits) {
        return new BigDecimal("" + value).setScale(digits, BigDecimal.ROUND_HALF_UP);
    }

    public void onClick(View view) {
        if (etPrice.getText().length() == 0 || etVes.getText().length() == 0 || etKolichGolov.getText().length() == 0
                || etPeriodObrabotki.getText().length() == 0 || etKolichVDen.getText().length() == 0 || dblRashodGolova == 0
                ) {
            Toast.makeText(getBaseContext(), "Заполните пожалуйста все данные", Toast.LENGTH_SHORT).show();
            return;
        }

        double price = Double.parseDouble(etPrice.getText().toString());
        double ves = Double.parseDouble(etVes.getText().toString());
        double kolichGolov = Double.parseDouble(etKolichGolov.getText().toString());
        double period = Double.parseDouble(etPeriodObrabotki.getText().toString());
        double kolichObrabotok = Double.parseDouble(etKolichVDen.getText().toString());


        stoimKg = price / ves;
        kolichGigien = (period * kolichObrabotok) * kolichGolov * (dblRashodGolova / 1000);
        stoimObrabotki = stoimKg * (dblRashodGolova / 1000);

        tvStoimostKg.setText(String.valueOf(roundUp(stoimKg, 2)));
        tvKolichGigien.setText(String.valueOf(roundUp(kolichGigien, 2)));
        tvStoimObrabotki.setText(String.valueOf(roundUp(stoimObrabotki, 2)));
    }

    public void onClickSravnenie(View view) {
    }
}
