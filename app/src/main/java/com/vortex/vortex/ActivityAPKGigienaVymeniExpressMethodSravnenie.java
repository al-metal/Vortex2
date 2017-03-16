package com.vortex.vortex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

public class ActivityAPKGigienaVymeniExpressMethodSravnenie extends AppCompatActivity {

    double stoimKgVotrex = 0;
    double kolichGigienVotrex = 0;
    double stoimObrabotkiVotrex = 0;
    double dblRashodGolovaVotrex = 0;
    TextView tvStoimKgVortex;
    TextView tvKolichSredstvaVortex;
    TextView tvStoimostVsegoVortex;
    TextView tvStoimostGolovyVortex;
    TextView tvStoimKg;
    TextView tvKolichSredstva;
    TextView tvStoimostVsego;
    TextView tvStoimostGolovy;
    EditText etPrice;
    EditText etVes;
    EditText etKolichGolov;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apkgigiena_vymeni_express_method_sravnenie);
        setTitle("Сравнение средств");

        tvStoimKgVortex = (TextView) findViewById(R.id.tvStoimKgVortex);
        tvKolichSredstvaVortex = (TextView) findViewById(R.id.tvKolichSredstvaVortex);
        tvStoimostVsegoVortex = (TextView) findViewById(R.id.tvStoimostVsegoVortex);
        tvStoimostGolovyVortex = (TextView) findViewById(R.id.tvStoimostGolovyVortex);
        tvStoimKg = (TextView) findViewById(R.id.tvStoimKg);
        tvKolichSredstva = (TextView) findViewById(R.id.tvKolichSredstva);
        tvStoimostVsego = (TextView) findViewById(R.id.tvStoimostVsego);
        tvStoimostGolovy = (TextView) findViewById(R.id.tvStoimostGolovy);

        etPrice = (EditText) findViewById(R.id.etPrice);
        etVes = (EditText) findViewById(R.id.etVes);
        etKolichGolov = (EditText) findViewById(R.id.etKolichGolov);


        stoimKgVotrex = getIntent().getExtras().getDouble("stoimKg");
        kolichGigienVotrex = getIntent().getExtras().getDouble("kolichestvo");
        stoimObrabotkiVotrex = getIntent().getExtras().getDouble("stoimostVsego");
        dblRashodGolovaVotrex = getIntent().getExtras().getDouble("stoimostGolovy");

        tvStoimKgVortex.setText(String.valueOf(roundUp(stoimKgVotrex, 2)));
        tvKolichSredstvaVortex.setText(String.valueOf(roundUp(kolichGigienVotrex, 2)));
        tvStoimostVsegoVortex.setText(String.valueOf(roundUp(stoimObrabotkiVotrex, 2)));
        tvStoimostGolovyVortex.setText(String.valueOf(roundUp(dblRashodGolovaVotrex, 2)));
    }

    public BigDecimal roundUp(double value, int digits) {
        return new BigDecimal("" + value).setScale(digits, BigDecimal.ROUND_HALF_UP);
    }

    public void onClick(View view) {
        if(etPrice.getText().length() == 0 || etVes.getText().length() == 0 || etKolichGolov.getText().length() == 0){
            Toast.makeText(getBaseContext(), "Заполните пожалуйста все данные", Toast.LENGTH_SHORT).show();
            return;
        }
        double stoim = Double.parseDouble(etPrice.getText().toString());
        double ves = Double.parseDouble(etVes.getText().toString());
        double kolichGolov = Double.parseDouble(etKolichGolov.getText().toString());

        double dblStoimKg = stoim / ves;
        double dblKolichSredstva = kolichGolov * 0.008;
        double dblStoimostVsego = 0.008 * dblStoimKg * kolichGolov;
        double dblStoimostGolovy = dblStoimostVsego / kolichGolov;

        tvStoimKg.setText(String.valueOf(roundUp(dblStoimKg, 2)));
        tvKolichSredstva.setText(String.valueOf(roundUp(dblKolichSredstva, 2)));
        tvStoimostVsego.setText(String.valueOf(roundUp(dblStoimostVsego, 2)));
        tvStoimostGolovy.setText(String.valueOf(roundUp(dblStoimostGolovy, 2)));
    }
}
