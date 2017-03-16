package com.vortex.vortex.APK.GigienaVymeni;

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

public class ActivityAPKGigienaVymeniDoDoeniaSravnenie extends AppCompatActivity {

    String[] data1 = {"10", "15", "20", "30", "40", "100"};

    Spinner spinner3;
    TextView tvStoimostKgVortex;
    TextView tvKolichGigienVortex;
    TextView tvObrabotkiVortex;
    TextView tvRashodGolova;
    TextView tvStoimostKg;
    TextView tvKolichGigien;
    TextView tvObrabotki;
    EditText etPrice;
    EditText etVes;
    EditText etGolov;
    EditText etDay;
    EditText etObrabotok;

    double stoimKgVotrex = 0;
    double kolichGigienVotrex = 0;
    double stoimObrabotkiVotrex = 0;
    double dblRashodGolovaVotrex = 0;
    double dblRashodGolova = 0;
    double stoimKg;
    double kolichGigien;
    double stoimObrabotki;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apkgigiena_vymeni_do_doenia_sravnenie);
        setTitle("Сравнение средств");

        spinner3 = (Spinner) findViewById(R.id.spinner3);
        tvStoimostKgVortex = (TextView) findViewById(R.id.tvStoimostKgVortex);
        tvKolichGigienVortex = (TextView) findViewById(R.id.tvKolichGigienVortex);
        tvObrabotkiVortex = (TextView) findViewById(R.id.tvObrabotkiVortex);
        tvRashodGolova = (TextView) findViewById(R.id.tvRashodGolova);
        tvStoimostKg = (TextView) findViewById(R.id.tvStoimostKg);
        tvKolichGigien = (TextView) findViewById(R.id.tvKolichGigien);
        tvObrabotki = (TextView) findViewById(R.id.tvObrabotki);
        etPrice = (EditText) findViewById(R.id.etPrice);
        etVes = (EditText) findViewById(R.id.etVes);
        etGolov = (EditText) findViewById(R.id.etGolov);
        etDay = (EditText) findViewById(R.id.etDay);
        etObrabotok = (EditText) findViewById(R.id.etObrabotok);

        stoimKgVotrex = getIntent().getExtras().getDouble("stoimKg");
        kolichGigienVotrex = getIntent().getExtras().getDouble("kolichGigien");
        stoimObrabotkiVotrex = getIntent().getExtras().getDouble("stoimObrabotki");
        dblRashodGolovaVotrex = getIntent().getExtras().getDouble("dblRashodGolova");

        tvStoimostKgVortex.setText(String.valueOf(roundUp(stoimKgVotrex, 2)));
        tvKolichGigienVortex.setText(String.valueOf(roundUp(kolichGigienVotrex, 2)));
        tvObrabotkiVortex.setText(String.valueOf(roundUp(stoimObrabotkiVotrex, 2)));

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data1);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner3.setAdapter(adapter2);

        spinner3.setPrompt("% разведения в пенном стакане");

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                if (spinner3.getSelectedItemId() == 0)
                    dblRashodGolova = 0.00008 * 4;
                else if (spinner3.getSelectedItemId() == 1)
                    dblRashodGolova = 0.00012 * 4;
                else if (spinner3.getSelectedItemId() == 2)
                    dblRashodGolova = 0.00016 * 4;
                else if (spinner3.getSelectedItemId() == 3)
                    dblRashodGolova = 0.00024 * 4;
                else if (spinner3.getSelectedItemId() == 4)
                    dblRashodGolova = 0.00032 * 4;
                else if (spinner3.getSelectedItemId() == 5)
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
        if(etPrice.getText().length() == 0 || etVes.getText().length() == 0 || etGolov.getText().length() == 0
                || etDay.getText().length() == 0 || etObrabotok.getText().length() == 0 || dblRashodGolova == 0
                ){
            Toast.makeText(getBaseContext(), "Заполните пожалуйста все данные", Toast.LENGTH_SHORT).show();
            tvStoimostKg.setText("0");
            tvKolichGigien.setText("0");
            tvObrabotki.setText("0");
            return;
        }

        double price = Double.parseDouble(etPrice.getText().toString());
        double ves = Double.parseDouble(etVes.getText().toString());
        double kolichGolov = Double.parseDouble(etGolov.getText().toString());
        double period = Double.parseDouble(etDay.getText().toString());
        double kolichObrabotok = Double.parseDouble(etObrabotok.getText().toString());

        stoimKg = price / ves;
        kolichGigien = dblRashodGolova*kolichGolov*period*kolichObrabotok;
        stoimObrabotki = dblRashodGolova * stoimKg;

        tvStoimostKg.setText(String.valueOf(roundUp(stoimKg, 2)));
        tvKolichGigien.setText(String.valueOf(roundUp(kolichGigien, 2)));
        tvObrabotki.setText(String.valueOf(roundUp(stoimObrabotki, 2)));
    }
}
