package com.vortex.vortex.APK;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.vortex.vortex.R;

import java.math.BigDecimal;

public class ActivityAPKDezinfekciyaVynujdennayaForbicid extends AppCompatActivity {

    EditText etS;
    EditText etPrice;
    EditText etVes;

    TextView tvKoncentraciya;
    TextView tvRashod;
    TextView tvEkspoziciya;
    TextView tvStoimostKg;
    TextView tvKolichForbicid;
    TextView tvStoimostObrabotki;

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
        setContentView(R.layout.activity_apkdezinfekciya_vynujdennaya_forbicid);
        setTitle("Форбицид вынужденная дезинфекция");

        tableL = (TableLayout) findViewById(R.id.tableL);
        btnRaschet = (Button) findViewById(R.id.btnRaschet);

        etS = (EditText) findViewById(R.id.etS);
        etPrice = (EditText) findViewById(R.id.etPrice);
        etVes = (EditText) findViewById(R.id.etVes);

        tvKoncentraciya = (TextView) findViewById(R.id.tvKoncentraciya);
        tvRashod = (TextView) findViewById(R.id.tvRashod);
        tvEkspoziciya = (TextView) findViewById(R.id.tvEkspoziciya);
        tvStoimostKg = (TextView) findViewById(R.id.tvStoimostKg);
        tvKolichForbicid = (TextView) findViewById(R.id.tvKolichForbicid);
        tvStoimostObrabotki = (TextView) findViewById(R.id.tvStoimostObrabotki);

    }

    public void onClickRaschet(View view) {
        if (etPrice.getText().length() == 0 || etVes.getText().length() == 0 || etS.getText().length() == 0) {
            Toast.makeText(getBaseContext(), "Заполните пожалуйста все данные", Toast.LENGTH_SHORT).show();
            return;
        }

        tableL.setVisibility(View.VISIBLE);
        int gray = Color.parseColor("#7B7979");
        btnRaschet.setBackgroundColor(gray);

        double dblPloshad = Double.parseDouble(etS.getText().toString());
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
        Intent intent = new Intent(ActivityAPKDezinfekciyaVynujdennayaForbicid.this, ActivityAPKDezinfekciyaVynujdennayaForbicidSravnenie.class);
        intent.putExtra("Koncentraciya", dblKoncentraciya);
        intent.putExtra("Rashod", dblRashod);
        intent.putExtra("Ekspoziciya", dblEkspoziciya);
        intent.putExtra("StoimostKg", dblStoimostKg);
        intent.putExtra("KolichForbicid", dblKolichForbicid);
        intent.putExtra("StoimostObrabotki", dblStoimostObrabotki);
        startActivity(intent);
    }
}
