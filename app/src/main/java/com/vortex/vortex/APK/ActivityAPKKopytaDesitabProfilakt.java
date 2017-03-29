package com.vortex.vortex.APK;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vortex.vortex.R;

import java.math.BigDecimal;

public class ActivityAPKKopytaDesitabProfilakt extends AppCompatActivity {

    double vanna = 200;

    EditText etStado;
    EditText etProdolDen;
    EditText etPercent;
    EditText etPrice;
    EditText etVes;

    TextView tvKolichVann;
    TextView tvTrebuemDesitab;
    TextView tvPriceKg;
    TextView tvVsegoPrice;
    TextView tvKorovaDen;
    TextView tvKorovVsego;
    TextView tvKolichObrabotok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apkkopyta_desitab_profilakt);
        setTitle("DESITUB Профилактика");

        etStado = (EditText) findViewById(R.id.etStado);
        etProdolDen = (EditText) findViewById(R.id.etProdolDen);
        etPercent = (EditText) findViewById(R.id.etPercent);
        etPrice = (EditText) findViewById(R.id.etPrice);
        etVes = (EditText) findViewById(R.id.etVes);

        tvKolichVann = (TextView) findViewById(R.id.tvKolichVann);
        tvTrebuemDesitab = (TextView) findViewById(R.id.tvTrebuemDesitab);
        tvPriceKg = (TextView) findViewById(R.id.tvPriceKg);
        tvVsegoPrice = (TextView) findViewById(R.id.tvVsegoPrice);
        tvKorovaDen = (TextView) findViewById(R.id.tvKorovaDen);
        tvKorovVsego = (TextView) findViewById(R.id.tvKorovVsego);
        tvKolichObrabotok = (TextView) findViewById(R.id.tvKolichObrabotok);
    }

    public void onClickRaschet(View view) {
        if (etStado.getText().length() == 0 || etProdolDen.getText().length() == 0
                || etPercent.getText().length() == 0 || etPrice.getText().length() == 0
                || etVes.getText().length() == 0) {
            Toast.makeText(getBaseContext(), "Заполните пожалуйста все данные", Toast.LENGTH_SHORT).show();
            return;
        }

        double stado = Double.parseDouble(etStado.getText().toString());
        double prodolDen = Double.parseDouble(etProdolDen.getText().toString());
        double percent = Double.parseDouble(etPercent.getText().toString());
        double price = Double.parseDouble(etPrice.getText().toString());
        double ves = Double.parseDouble(etVes.getText().toString());

        double percentDesitab = (vanna * percent) / 100;
        double kolichObrabotok = (prodolDen / 7) * 2;
        double kolichVann = (kolichObrabotok * stado) / 200;
        double trebuemDesitab = percentDesitab*kolichVann;
        double priceKg = price / ves;
        double vsegoPrice = trebuemDesitab * priceKg;
        double korovVsego = vsegoPrice / stado;
        double korovaDen = korovVsego / prodolDen;

        tvKolichObrabotok.setText(String.valueOf(roundUp(kolichObrabotok, 0)));
        tvKolichVann.setText(String.valueOf(roundUp(kolichVann, 0)));
        tvTrebuemDesitab.setText(String.valueOf(roundUp(trebuemDesitab, 0)));
        tvPriceKg.setText(String.valueOf(roundUp(priceKg, 0)));
        tvVsegoPrice.setText(String.valueOf(roundUp(vsegoPrice, 0)));
        tvKorovaDen.setText(String.valueOf(roundUp(korovaDen, 0)));
        tvKorovVsego.setText(String.valueOf(roundUp(korovVsego, 0)));

    }
    public BigDecimal roundUp(double value, int digits) {
        return new BigDecimal("" + value).setScale(digits, BigDecimal.ROUND_HALF_UP);
    }
}
