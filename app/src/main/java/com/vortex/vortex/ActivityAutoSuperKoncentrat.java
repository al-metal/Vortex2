package com.vortex.vortex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

public class ActivityAutoSuperKoncentrat extends AppCompatActivity {

    double dblVes = 6;
    double dblObjem = 35000;
    double dblRazbavlenie = 900;
    double dblStoimostMoyki = 12;
    double dblKolichestvoZapravok;
    double dblStoimostZapravki;
    double dblStoimostmoykiResult;
    double dblPrice2Kanistr;
    double dblKolichestvoKanistr;

    String strOborudovanie = "PenoGen";
    String strObjem6 = "6 кг = 35 л";
    String strObjem64 = "64 кг = 376 л";
    String strRazbavleniePenoGen = "900";
    String strRazbavleniePenoKomp = "180";
    String strKolichestvoKanistr = "Цена за 2 канистры: ";

    RadioGroup RadioGroupUstroystvo;
    RadioGroup RadioGroupVes;

    TextView tvObjem;
    TextView tvRazbavlenie;
    TextView tvKolichZapravok;
    TextView tvStoimostZapravki;
    TextView tvStoimostMoyki;
    TextView tvKolichestvoKanistr;

    EditText etPrice2Kanistr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_super_koncentrat);
        setTitle("Суперконцентрат");

        RadioGroupUstroystvo = (RadioGroup) findViewById(R.id.RadioGroupUstroystvo);
        RadioGroupVes = (RadioGroup) findViewById(R.id.RadioGroupVes);

        tvObjem = (TextView) findViewById(R.id.tvObjem);
        tvRazbavlenie = (TextView) findViewById(R.id.tvRazbavlenie);
        tvKolichZapravok = (TextView) findViewById(R.id.tvKolichZapravok);
        tvStoimostZapravki = (TextView) findViewById(R.id.tvStoimostZapravki);
        tvStoimostMoyki = (TextView) findViewById(R.id.tvStoimostMoyki);
        tvKolichestvoKanistr = (TextView) findViewById(R.id.tvKolichestvoKanistr);

        etPrice2Kanistr = (EditText) findViewById(R.id.etPrice2Kanistr);

        //region RadioButton
        RadioGroupUstroystvo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case -1:
                        Toast.makeText(getApplicationContext(), "Ничего не выбрано",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rbPenoGen:
                        strOborudovanie = "PenoGen";
                        dblRazbavlenie = 900;
                        dblStoimostMoyki = 12;
                        break;
                    case R.id.rbPenoKomp:
                        strOborudovanie = "PenoKomp";
                        dblRazbavlenie = 180;
                        dblStoimostMoyki = 3;
                        break;
                    default:
                        break;
                }
                ReturnTextViev();
            }
        });

        RadioGroupVes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case -1:
                        Toast.makeText(getApplicationContext(), "Ничего не выбрано",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rbVes6:
                        dblVes = 6;
                        dblObjem = 35000;
                        dblKolichestvoKanistr = 2;
                        strKolichestvoKanistr = "Цена за 2 канистры: ";
                        break;
                    case R.id.rbVes64:
                        dblVes = 64;
                        dblObjem = 376000;
                        dblKolichestvoKanistr = 20;
                        strKolichestvoKanistr = "Цена за 20 канистр: ";
                        break;
                    default:
                        break;
                }
                ReturnTextViev();
            }
        });
        //endregion
    }

    private void ReturnTextViev() {
        if (dblVes == 0)
            return;

        if (strOborudovanie == "PenoGen") {
            tvRazbavlenie.setText(strRazbavleniePenoGen);
            if (dblVes == 6) {
                tvObjem.setText(strObjem6);
                dblVes = 35000;
            } else if (dblVes == 64) {
                tvObjem.setText(strObjem64);
                dblVes = 376000;
            }
        } else if (strOborudovanie == "PenoKomp") {
            tvRazbavlenie.setText(strRazbavleniePenoKomp);
            if (dblVes == 6) {
                tvObjem.setText(strObjem6);
                dblVes = 35000;
            } else if (dblVes == 64) {
                tvObjem.setText(strObjem64);
                dblVes = 376000;
            }
        }
    }

    public void onClickRaschet(View view) {
        if (etPrice2Kanistr.getText().length() == 0) {
            Toast.makeText(getBaseContext(), "Заполните пожалуйста все данные", Toast.LENGTH_SHORT).show();
            return;
        }

        dblPrice2Kanistr = Double.parseDouble(etPrice2Kanistr.getText().toString());

        dblKolichestvoZapravok = dblObjem / dblRazbavlenie;
        dblStoimostZapravki = dblPrice2Kanistr / dblKolichestvoZapravok;
        dblStoimostmoykiResult = dblStoimostZapravki / dblStoimostMoyki;

        tvKolichZapravok.setText(String.valueOf(roundUp(dblKolichestvoZapravok, 2)));
        tvStoimostZapravki.setText(String.valueOf(roundUp(dblStoimostZapravki, 2)));
        tvStoimostMoyki.setText(String.valueOf(roundUp(dblStoimostmoykiResult, 2)));
    }

    public BigDecimal roundUp(double value, int digits) {
        return new BigDecimal("" + value).setScale(digits, BigDecimal.ROUND_HALF_UP);
    }

    public void onClickSravnenie(View view) {
        if (etPrice2Kanistr.getText().length() == 0 || dblKolichestvoZapravok == 0 || dblKolichestvoZapravok == 0 || dblKolichestvoZapravok == 0) {
            Toast.makeText(getBaseContext(), "Данные для сравнения еще не расчитаны", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(ActivityAutoSuperKoncentrat.this, ActivityAutoSuperKoncentratSravnenie.class);
        intent.putExtra("dblPrice", dblPrice2Kanistr);
        intent.putExtra("Objem", tvObjem.getText());
        intent.putExtra("dblRazbavlenie", dblRazbavlenie);
        intent.putExtra("dblKolichestvoZapravok", dblKolichestvoZapravok);
        intent.putExtra("dblStoimostZapravki", dblStoimostZapravki);
        intent.putExtra("dblStoimostMoyki", dblStoimostmoykiResult);
        intent.putExtra("dblKooficent", dblStoimostMoyki);
        intent.putExtra("dblKolichestvoKanistr", dblKolichestvoKanistr);
        startActivity(intent);
    }
}
