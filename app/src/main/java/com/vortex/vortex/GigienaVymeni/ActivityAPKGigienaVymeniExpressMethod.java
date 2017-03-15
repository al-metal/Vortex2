package com.vortex.vortex.GigienaVymeni;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vortex.vortex.R;

import java.math.BigDecimal;

public class ActivityAPKGigienaVymeniExpressMethod extends AppCompatActivity {

    EditText etPrice;
    EditText etVes;
    EditText etKolichGolov;
    TextView tvStoimKg;
    TextView tvKolichestvo;
    TextView tvStoimostObrabotkiVsego;
    TextView tvStoimostObrabotkiGolovy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apkgigiena_vymeni_express_method);
        setTitle("Экспресс-метод диагностики субклинического мастита у КРС");

        etPrice = (EditText) findViewById(R.id.etPrice);
        etVes = (EditText) findViewById(R.id.etVes);
        etKolichGolov = (EditText) findViewById(R.id.etKolichGolov);

        tvStoimKg = (TextView) findViewById(R.id.tvStoimKg);
        tvKolichestvo = (TextView) findViewById(R.id.tvKolichestvo);
        tvStoimostObrabotkiVsego = (TextView) findViewById(R.id.tvStoimostObrabotkiVsego);
        tvStoimostObrabotkiGolovy = (TextView) findViewById(R.id.tvStoimostObrabotkiGolovy);

    }

    public void onClickRaschet(View view) {

        if(etPrice.getText().length() == 0 || etVes.getText().length() == 0 || etKolichGolov.getText().length() == 0){
            Toast.makeText(getBaseContext(), "Заполните пожалуйста все данные", Toast.LENGTH_SHORT).show();
            return;
        }

        double price = Double.parseDouble(etPrice.getText().toString());
        double ves = Double.parseDouble(etVes.getText().toString());
        double kolichGolov = Double.parseDouble(etKolichGolov.getText().toString());

        double dblStoimKg = price/ves;
        double dblKolichestvo = kolichGolov*0.008;
        double dblStoimostVsego = 0.008*dblStoimKg*kolichGolov;
        double dblStoimostGolovy = dblStoimostVsego/kolichGolov;

        tvStoimKg.setText(String.valueOf(roundUp(dblStoimKg, 2)));
        tvKolichestvo.setText(String.valueOf(roundUp(dblKolichestvo, 2)));
        tvStoimostObrabotkiVsego.setText(String.valueOf(roundUp(dblStoimostVsego, 2)));
        tvStoimostObrabotkiGolovy.setText(String.valueOf(roundUp(dblStoimostGolovy, 2)));

    }

    public BigDecimal roundUp(double value, int digits) {
        return new BigDecimal("" + value).setScale(digits, BigDecimal.ROUND_HALF_UP);
    }
}
