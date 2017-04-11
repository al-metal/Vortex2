package com.vortex.vortex.APK;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.vortex.vortex.R;

import java.math.BigDecimal;

public class ActivityAPKDezinfekciyaProfilaktikaForbicid extends AppCompatActivity {

    String[] data = {"Гладкая", "Шероховатая"};

    Spinner spinner5;
    TextView tvKoncentraciya;
    TextView tvRashod;
    TextView tvEkspoziciya;
    TextView tvStoimostKg;
    TextView tvKolichForbicid;
    TextView tvStoimostObrabotki;

    EditText etPloshad;
    EditText etPrice;
    EditText etVes;

    double dblStoimostKg;
    double dblKolichForbicid;
    double dblStoimostObrabotki;
    double dblKoncentraciya;
    double dblRashod;
    double dblEkspoziciya;

    TableLayout tableL;
    Button btnRaschet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apkdezinfekciya_profilaktika_forbicid);
        setTitle("Форбицид профилактическая дезинфекция");

        tableL = (TableLayout) findViewById(R.id.tableL);
        btnRaschet = (Button) findViewById(R.id.btnRaschet);

        spinner5 = (Spinner) findViewById(R.id.spinner5);
        tvKoncentraciya = (TextView) findViewById(R.id.tvKoncentraciya);
        tvRashod = (TextView) findViewById(R.id.tvRashod);
        tvEkspoziciya = (TextView) findViewById(R.id.tvEkspoziciya);

        tvStoimostKg = (TextView) findViewById(R.id.tvStoimostKg);
        tvKolichForbicid = (TextView) findViewById(R.id.tvKolichForbicid);
        tvStoimostObrabotki = (TextView) findViewById(R.id.tvStoimostObrabotki);

        etPloshad = (EditText) findViewById(R.id.etPloshad);
        etPrice = (EditText) findViewById(R.id.etPrice);
        etVes = (EditText) findViewById(R.id.etVes);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner, data);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        spinner5.setAdapter(adapter);

        spinner5.setPrompt("Выберите вид поверхности");

        spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                if (pos == 0) {
                    tvKoncentraciya.setText("0.25");
                    tvRashod.setText("0.25");
                    tvEkspoziciya.setText("20");
                } else {
                    tvKoncentraciya.setText("0.25");
                    tvRashod.setText("0.35");
                    tvEkspoziciya.setText("30");
                }
            }
        });
    }

    public void onClickRaschet(View view) {
        if (etPloshad.getText().length() == 0 || etPrice.getText().length() == 0 || etVes.getText().length() == 0
                || tvKoncentraciya.getText().length() == 0 || tvRashod.getText().length() == 0 || tvEkspoziciya.getText().length() == 0) {
            Toast.makeText(getBaseContext(), "Заполните пожалуйста все данные", Toast.LENGTH_SHORT).show();
            return;
        }

        tableL.setVisibility(View.VISIBLE);
        int gray = Color.parseColor("#7B7979");
        btnRaschet.setBackgroundColor(gray);

        double dblPloshad = Double.parseDouble(etPloshad.getText().toString());
        double dblPrice = Double.parseDouble(etPrice.getText().toString());
        double dblVes = Double.parseDouble(etVes.getText().toString());
        dblKoncentraciya = Double.parseDouble(tvKoncentraciya.getText().toString());
        dblRashod = Double.parseDouble(tvRashod.getText().toString());
        dblEkspoziciya = Double.parseDouble(tvEkspoziciya.getText().toString());

        dblStoimostKg = dblPrice / dblVes;
        dblKolichForbicid = ((dblPloshad * dblRashod) * dblKoncentraciya) / 100;
        dblStoimostObrabotki = dblStoimostKg * dblKolichForbicid;

        tvStoimostKg.setText(String.valueOf(roundUp(dblStoimostKg, 2)));
        tvKolichForbicid.setText(String.valueOf(roundUp(dblKolichForbicid, 2)));
        tvStoimostObrabotki.setText(String.valueOf(roundUp(dblStoimostObrabotki, 2)));


    }

    public BigDecimal roundUp(double value, int digits) {
        return new BigDecimal("" + value).setScale(digits, BigDecimal.ROUND_HALF_UP);
    }

    public void onClickSravnenie(View view) {
        if (dblKoncentraciya == 0 || dblRashod == 0 || dblEkspoziciya == 0
                || dblStoimostKg == 0 || dblKolichForbicid == 0 || dblStoimostObrabotki == 0) {
            Toast.makeText(getBaseContext(), "Данные для сравнения еще не расчитаны", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(ActivityAPKDezinfekciyaProfilaktikaForbicid.this, ActivityAPKDezinfekciyaProfilaktikaForbicidSravnenie.class);
        intent.putExtra("Koncentraciya", dblKoncentraciya);
        intent.putExtra("Rashod", dblRashod);
        intent.putExtra("Ekspoziciya", dblEkspoziciya);
        intent.putExtra("StoimostKg", dblStoimostKg);
        intent.putExtra("KolichForbicid", dblKolichForbicid);
        intent.putExtra("StoimostObrabotki", dblStoimostObrabotki);
        startActivity(intent);
    }
}
