package com.vortex.vortex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;

public class ActivityAPKKopytaOzdorovlenie extends AppCompatActivity {

    TextView tvTrebuemVann;
    TextView tvTrebuemVann2;
    TextView tvTrebuemDesimix1;
    TextView tvTrebuemDesimix2;
    TextView tvTrebuemKuporos1;
    TextView tvTrebuemKuporos2;
    TextView tvVsegoDesimix;
    TextView tvVsegoKuporos;

    EditText etStado;
    EditText etDesimix1;
    EditText etKuporos1;
    EditText etDesimix2;
    EditText etKuporos2;

    double vanna = 200;
    double d5 = 36;
    double d13 = 42;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apkkopyta_ozdorovlenie);
        setTitle("Программа \"Оздоровление\"");

        tvTrebuemVann = (TextView) findViewById(R.id.tvTrebuemVann);
        tvTrebuemVann2 = (TextView) findViewById(R.id.tvTrebuemVann2);
        tvTrebuemDesimix1 = (TextView) findViewById(R.id.tvTrebuemDesimix1);
        tvTrebuemDesimix2 = (TextView) findViewById(R.id.tvTrebuemDesimix2);
        tvTrebuemKuporos1 = (TextView) findViewById(R.id.tvTrebuemKuporos1);
        tvTrebuemKuporos2 = (TextView) findViewById(R.id.tvTrebuemKuporos2);
        tvVsegoDesimix = (TextView) findViewById(R.id.tvVsegoDesimix);
        tvVsegoKuporos = (TextView) findViewById(R.id.tvVsegoKuporos);

        etStado = (EditText) findViewById(R.id.etStado);
        etDesimix1 = (EditText) findViewById(R.id.etDesimix1);
        etKuporos1 = (EditText) findViewById(R.id.etKuporos1);
        etDesimix2 = (EditText) findViewById(R.id.etDesimix2);
        etKuporos2 = (EditText) findViewById(R.id.etKuporos2);
    }

    public void onClickRaschet(View view) {
        double stado = Double.parseDouble(etStado.getText().toString());
        double dblDesimix1 = Double.parseDouble(etDesimix1.getText().toString());
        double dblKuporos1 = Double.parseDouble(etKuporos1.getText().toString());
        double dblDesimix2 = Double.parseDouble(etDesimix2.getText().toString());
        double dblKuporos2 = Double.parseDouble(etKuporos2.getText().toString());

        double percentDesimix1 = (vanna * dblDesimix1) / 100;
        double percentKuporos1 = (vanna * dblKuporos1) / 100;
        double kolichestvoTrebuemmVann = (stado * d5) / 500;
        double kolichestvoTrebuemmDesimix1 = percentDesimix1 * kolichestvoTrebuemmVann;
        double kolichestvoTrebuemmKuporos1 = percentKuporos1 * kolichestvoTrebuemmVann;

        double percentDesimix2 = (vanna * dblDesimix2) / 100;
        double percentKuporos2 = (vanna * dblKuporos2) / 100;
        double kolichestvoTrebuemmVann2 = (stado * d13) / 500;
        double kolichestvoTrebuemmDesimix2 = percentDesimix2 * kolichestvoTrebuemmVann2;
        double kolichestvoTrebuemmKuporos2 = percentKuporos2 * kolichestvoTrebuemmVann2;

        double itogDesimix = kolichestvoTrebuemmDesimix1+kolichestvoTrebuemmDesimix2;
        double itogKuporos = kolichestvoTrebuemmKuporos1+kolichestvoTrebuemmKuporos2;

        tvTrebuemVann.setText(String.valueOf(roundUp(kolichestvoTrebuemmVann, 2)));
        tvTrebuemVann2.setText(String.valueOf(roundUp(kolichestvoTrebuemmVann2, 2)));
        tvTrebuemDesimix1.setText(String.valueOf(roundUp(kolichestvoTrebuemmDesimix1, 2)));
        tvTrebuemDesimix2.setText(String.valueOf(roundUp(kolichestvoTrebuemmDesimix2, 2)));
        tvTrebuemKuporos1.setText(String.valueOf(roundUp(kolichestvoTrebuemmKuporos1, 2)));
        tvTrebuemKuporos2.setText(String.valueOf(roundUp(kolichestvoTrebuemmKuporos2, 2)));
        tvVsegoDesimix.setText(String.valueOf(roundUp(itogDesimix, 2)));
        tvVsegoKuporos.setText(String.valueOf(roundUp(itogKuporos, 2)));

    }

    public BigDecimal roundUp(double value, int digits) {
        return new BigDecimal("" + value).setScale(digits, BigDecimal.ROUND_HALF_UP);
    }
}
