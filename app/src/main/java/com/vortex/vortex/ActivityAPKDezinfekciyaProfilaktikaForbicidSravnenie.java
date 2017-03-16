package com.vortex.vortex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

public class ActivityAPKDezinfekciyaProfilaktikaForbicidSravnenie extends AppCompatActivity {

    double dblKoncentraciya = 0;
    double dblRashod = 0;
    double dblEkspoziciya = 0;
    double dblStoimostKg = 0;
    double dblKolichForbicid = 0;
    double dblStoimostObrabotki = 0;

    TextView tvStoimostKgVortex;
    TextView tvKolichestvoVortex;
    TextView tvStoimostObrabotkiVortex;
    TextView tvStoimostKg;
    TextView tvKolichestvo;
    TextView tvStoimostObrabotki;

    EditText etPloshad;
    EditText etKoncentraciya;
    EditText etRashod;
    EditText etPrice;
    EditText etVes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apkdezinfekciya_profilaktika_forbicid_sravnenie);
        setTitle("Сравнение средств");

        dblKoncentraciya = getIntent().getExtras().getDouble("Koncentraciya");
        dblRashod = getIntent().getExtras().getDouble("Rashod");
        dblEkspoziciya = getIntent().getExtras().getDouble("Ekspoziciya");
        dblStoimostKg = getIntent().getExtras().getDouble("StoimostKg");
        dblKolichForbicid = getIntent().getExtras().getDouble("KolichForbicid");
        dblStoimostObrabotki = getIntent().getExtras().getDouble("StoimostObrabotki");

        tvStoimostKgVortex = (TextView) findViewById(R.id.tvStoimostKgVortex);
        tvKolichestvoVortex = (TextView) findViewById(R.id.tvKolichestvoVortex);
        tvStoimostObrabotkiVortex = (TextView) findViewById(R.id.tvStoimostObrabotkiVortex);
        tvStoimostKg = (TextView) findViewById(R.id.tvStoimostKg);
        tvKolichestvo = (TextView) findViewById(R.id.tvKolichestvo);
        tvStoimostObrabotki = (TextView) findViewById(R.id.tvStoimostObrabotki);

        etPloshad = (EditText) findViewById(R.id.etPloshad);
        etKoncentraciya = (EditText) findViewById(R.id.etKoncentraciya);
        etRashod = (EditText) findViewById(R.id.etRashod);
        etPrice = (EditText) findViewById(R.id.etPrice);
        etVes = (EditText) findViewById(R.id.etVes);

        tvStoimostKgVortex.setText(String.valueOf(roundUp(dblStoimostKg, 2)));
        tvKolichestvoVortex.setText(String.valueOf(roundUp(dblKolichForbicid, 2)));
        tvStoimostObrabotkiVortex.setText(String.valueOf(roundUp(dblStoimostObrabotki, 2)));
    }

    public BigDecimal roundUp(double value, int digits) {
        return new BigDecimal("" + value).setScale(digits, BigDecimal.ROUND_HALF_UP);
    }

    public void onClick(View view) {

        if (etPloshad.getText().length() == 0 || etPrice.getText().length() == 0 || etVes.getText().length() == 0
                || etKoncentraciya.getText().length() == 0 || etRashod.getText().length() == 0) {
            Toast.makeText(getBaseContext(), "Заполните пожалуйста все данные", Toast.LENGTH_SHORT).show();
            return;
        }

        double dblPloshad = Double.parseDouble(etPloshad.getText().toString());
        double dblPrice = Double.parseDouble(etPrice.getText().toString());
        double dblVes = Double.parseDouble(etVes.getText().toString());
        double dblKoncentraciyaSravnenie = Double.parseDouble(etKoncentraciya.getText().toString());
        double dblRashodSravnenie = Double.parseDouble(etRashod.getText().toString());

        double dblStoimostKgSravnenie = dblPrice / dblVes;
        double dblKolichForbicidSravnenie = ((dblPloshad * dblRashodSravnenie) * dblKoncentraciyaSravnenie) / 100;
        double dblStoimostObrabotkiSravnenie = dblStoimostKgSravnenie * dblKolichForbicidSravnenie;

        tvStoimostKg.setText(String.valueOf(roundUp(dblStoimostKgSravnenie, 2)));
        tvKolichestvo.setText(String.valueOf(roundUp(dblKolichForbicidSravnenie, 2)));
        tvStoimostObrabotki.setText(String.valueOf(roundUp(dblStoimostObrabotkiSravnenie, 2)));
    }
}
