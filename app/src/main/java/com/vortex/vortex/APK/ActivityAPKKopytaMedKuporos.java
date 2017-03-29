package com.vortex.vortex.APK;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vortex.vortex.R;

import java.math.BigDecimal;

public class ActivityAPKKopytaMedKuporos extends AppCompatActivity {
    double vanna = 200;

    EditText etStado;
    EditText etObrabotka;
    EditText etKuporos;
    EditText etObrabotokDen;
    EditText etPrice;

    TextView tvKuporos;
    TextView tvPrice;
    TextView tvObrabotka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apkkopyta_med_kuporos);
        setTitle("Медный купорос");

        etStado = (EditText) findViewById(R.id.etStado);
        etObrabotka = (EditText) findViewById(R.id.etObrabotka);
        etKuporos = (EditText) findViewById(R.id.etKuporos);
        etObrabotokDen = (EditText) findViewById(R.id.etObrabotokDen);
        etPrice = (EditText) findViewById(R.id.etPrice);

        tvKuporos = (TextView) findViewById(R.id.tvKuporos);
        tvPrice = (TextView) findViewById(R.id.tvPrice);
        tvObrabotka = (TextView) findViewById(R.id.tvObrabotka);
    }

    public void onClick(View view) {
        if (etStado.getText().length() == 0 || etObrabotka.getText().length() == 0
                || etKuporos.getText().length() == 0 || etObrabotokDen.getText().length() == 0 || etPrice.getText().length() == 0) {
            Toast.makeText(getBaseContext(), "Заполните пожалуйста все данные", Toast.LENGTH_SHORT).show();
            return;
        }

        double stado =  Double.parseDouble(etStado.getText().toString());
        double obrabotka =  Double.parseDouble(etObrabotka.getText().toString());
        double kuporos =  Double.parseDouble(etKuporos.getText().toString());
        double obrabotkaDen =  Double.parseDouble(etObrabotokDen.getText().toString());
        double price =  Double.parseDouble(etPrice.getText().toString());

        double trebuemKuporosKg = (stado / vanna) * ((vanna * kuporos) / 100) * obrabotka * obrabotkaDen;
        double stoimPeriod = trebuemKuporosKg * price;
        double stoimGolova = stoimPeriod / stado;

        tvKuporos.setText(String.valueOf(roundUp(trebuemKuporosKg, 0)));
        tvPrice.setText(String.valueOf(roundUp(stoimPeriod, 0)));
        tvObrabotka.setText(String.valueOf(roundUp(stoimGolova, 0)));
    }
    public BigDecimal roundUp(double value, int digits) {
        return new BigDecimal("" + value).setScale(digits, BigDecimal.ROUND_HALF_UP);
    }
}
