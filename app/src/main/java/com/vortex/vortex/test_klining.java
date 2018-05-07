package com.vortex.vortex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;

public class test_klining extends AppCompatActivity {
    EditText etPrice;
    EditText etPriceMarvel;
    EditText etPriceWell;
    TextView tvStoimostM2;
    TextView tvStoimost;
    TextView tvStoimkg2;
    TextView tvStoimostUborki;
    TextView tvStoimostMarvel;
    TextView tvStoimkgMarvel;
    TextView tvStoimostM2Marvel;
    double massa = 6;
    double rashod = 15;
    double massaWell = 5;
    double rashodWell = 50;
    double rashodM2Marvel = 0.5;
    double rashodMlMarvel = 1;
    double massaMarvel = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_klining);
        setTitle("Расчет средства");

        etPrice = (EditText) findViewById(R.id.etPrice);
        etPriceMarvel = (EditText) findViewById(R.id.etPriceMarvel);
        etPriceWell = (EditText) findViewById(R.id.etPriceWell);
        tvStoimost = (TextView) findViewById(R.id.tvStoimost);
        tvStoimostM2 = (TextView) findViewById(R.id.tvStoimostM2);
        tvStoimkgMarvel = (TextView) findViewById(R.id.tvStoimkgMarvel);
        tvStoimkg2 = (TextView) findViewById(R.id.tvStoimkg2);
        tvStoimostUborki = (TextView) findViewById(R.id.tvStoimostUborki);
        tvStoimostMarvel = (TextView) findViewById(R.id.tvStoimostMarvel);
        tvStoimostM2Marvel = (TextView) findViewById(R.id.tvStoimostM2Marvel);
    }

    public void onClick(View view) {
        double price = Double.parseDouble(etPrice.getText().toString());
        double stoimost = price / massa;

        double stoimostUborki = stoimost / 1000 * rashod;

        tvStoimost.setText(String.valueOf(roundUp(stoimost, 2)));
        tvStoimostUborki.setText((String.valueOf(roundUp(stoimostUborki, 2))));
    }

    public BigDecimal roundUp(double value, int digits) {
        return new BigDecimal("" + value).setScale(digits, BigDecimal.ROUND_HALF_UP);
    }

    public void onClickWell(View view) {

        double price = Double.parseDouble(etPriceWell.getText().toString());
        double stoimost = price / massaWell;

        double stoimostUborki = stoimost / 1000 * rashodWell;

        tvStoimkg2.setText(String.valueOf(roundUp(stoimost, 2)));
        tvStoimostM2.setText((String.valueOf(roundUp(stoimostUborki, 2))));
    }

    public void onClickMarvel(View view) {
        double price = Double.parseDouble(etPriceMarvel.getText().toString());
        double stoimost1Kg = price / massaMarvel;
        double stoimostSredstvam2 = stoimost1Kg / 1000 * rashodM2Marvel;
        double stoimostUborki = stoimost1Kg / 1000 * rashodMlMarvel;

        tvStoimostMarvel.setText(String.valueOf(roundUp(stoimostUborki, 2)));
        tvStoimkgMarvel.setText((String.valueOf(roundUp(stoimost1Kg, 2))));
        tvStoimostM2Marvel.setText((String.valueOf(roundUp(stoimostSredstvam2, 2))));
    }
}
