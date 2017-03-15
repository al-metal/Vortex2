package com.vortex.vortex.GigienaVymeni;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vortex.vortex.R;

import java.math.BigDecimal;

public class ActivityAPKGigienaVymeniPosleDoeniaSravnenie extends AppCompatActivity {

    double stoimKgVotrex = 0;
    double kolichGigienVotrex = 0;
    double stoimObrabotkiVotrex = 0;
    double dblRashodGolovaVotrex = 0;
    double stoimKg;
    double kolichGigien;
    double stoimObrabotki;

    TextView tvStoimostKgVortex;
    TextView tvKolichGigienVortex;
    TextView tvObrabotkiVortex;
    TextView tvStoimostKg;
    TextView tvKolichGigien;
    TextView tvObrabotki;
    EditText etRashodGolova;
    EditText etPrice;
    EditText etVes;
    EditText etKolichGolov;
    EditText etPeriod;
    EditText etObrabotokVDen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apkgigiena_vymeni_posle_doenia_sravnenie);
        setTitle("Сравнение средств");

        stoimKgVotrex = getIntent().getExtras().getDouble("stoimKg");
        kolichGigienVotrex = getIntent().getExtras().getDouble("kolichGigien");
        stoimObrabotkiVotrex = getIntent().getExtras().getDouble("stoimObrabotki");
        dblRashodGolovaVotrex = getIntent().getExtras().getDouble("dblRashodGolova");

        tvStoimostKgVortex = (TextView) findViewById(R.id.tvStoimostKgVortex);
        tvKolichGigienVortex = (TextView) findViewById(R.id.tvKolichGigienVortex);
        tvObrabotkiVortex = (TextView) findViewById(R.id.tvObrabotkiVortex);
        tvStoimostKg = (TextView) findViewById(R.id.tvStoimostKg);
        tvKolichGigien = (TextView) findViewById(R.id.tvKolichGigien);
        tvObrabotki = (TextView) findViewById(R.id.tvObrabotki);

        tvStoimostKgVortex.setText(String.valueOf(roundUp(stoimKgVotrex, 2)));
        tvKolichGigienVortex.setText(String.valueOf(roundUp(kolichGigienVotrex, 2)));
        tvObrabotkiVortex.setText(String.valueOf(roundUp(stoimObrabotkiVotrex, 2)));

        etRashodGolova = (EditText) findViewById(R.id.etRashodGolova);
        etPrice = (EditText) findViewById(R.id.etPrice);
        etVes = (EditText) findViewById(R.id.etVes);
        etKolichGolov = (EditText) findViewById(R.id.etKolichGolov);
        etPeriod = (EditText) findViewById(R.id.etPeriod);
        etObrabotokVDen = (EditText) findViewById(R.id.etObrabotokVDen);
    }

    public BigDecimal roundUp(double value, int digits) {
        return new BigDecimal("" + value).setScale(digits, BigDecimal.ROUND_HALF_UP);
    }

    public void onClickRaschet(View view) {
        if(etPrice.getText().length() == 0 || etVes.getText().length() == 0 || etKolichGolov.getText().length() == 0
                || etPeriod.getText().length() == 0 || etObrabotokVDen.getText().length() == 0 || etRashodGolova.getText().length() == 0
                ){
            Toast.makeText(getBaseContext(), "Заполните пожалуйста все данные", Toast.LENGTH_SHORT).show();
            tvStoimostKg.setText("0");
            tvKolichGigien.setText("0");
            tvObrabotki.setText("0");
            return;
        }

        double price = Double.parseDouble(etPrice.getText().toString());
        double ves = Double.parseDouble(etVes.getText().toString());
        double kolichGolov = Double.parseDouble(etKolichGolov.getText().toString());
        double period = Double.parseDouble(etPeriod.getText().toString());
        double kolichObrabotok = Double.parseDouble(etObrabotokVDen.getText().toString());
        double rashodGolova = Double.parseDouble(etRashodGolova.getText().toString());

        stoimKg = price / ves;
        kolichGigien = (period * kolichObrabotok) * kolichGolov * (rashodGolova / 1000);
        stoimObrabotki = stoimKg * (rashodGolova / 1000);

        tvStoimostKg.setText(String.valueOf(roundUp(stoimKg, 2)));
        tvKolichGigien.setText(String.valueOf(roundUp(kolichGigien, 2)));
        tvObrabotki.setText(String.valueOf(roundUp(stoimObrabotki, 2)));
    }
}
