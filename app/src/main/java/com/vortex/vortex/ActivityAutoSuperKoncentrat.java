package com.vortex.vortex;

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

    String strOborudovanie = "PenoGen";
    String strObjem6 = "6 кг = 35 л";
    String strObjem64 = "64 кг = 376 л";
    String strRazbavleniePenoGen = "900";
    String strRazbavleniePenoKomp = "180";

    RadioGroup RadioGroupUstroystvo;
    RadioGroup RadioGroupVes;

    TextView tvObjem;
    TextView tvRazbavlenie;
    TextView tvKolichZapravok;
    TextView tvStoimostZapravki;
    TextView tvStoimostMoyki;

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
                        break;
                    case R.id.rbVes64:
                        dblVes = 64;
                        dblObjem = 376000;
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
        if(dblVes == 0)
            return;

        if(strOborudovanie == "PenoGen"){
            tvRazbavlenie.setText(strRazbavleniePenoGen);
            if(dblVes == 6){
                tvObjem.setText(strObjem6);
                dblVes = 35000;
            }else if(dblVes == 64){
                tvObjem.setText(strObjem64);
                dblVes = 376000;
            }
        }else if(strOborudovanie == "PenoKomp"){
            tvRazbavlenie.setText(strRazbavleniePenoKomp);
            if(dblVes == 6){
                tvObjem.setText(strObjem6);
                dblVes = 35000;
            }else if(dblVes == 64){
                tvObjem.setText(strObjem64);
                dblVes = 376000;
            }
        }
    }

    public void onClickRaschet(View view) {

        double dblPrice2Kanistr = Double.parseDouble(etPrice2Kanistr.getText().toString());

        double dblKolichestvoZapravok = dblObjem / dblRazbavlenie;
        double dblStoimostZapravki = dblPrice2Kanistr / dblKolichestvoZapravok;
        double dblStoimostmoykiResult = dblStoimostZapravki / dblStoimostMoyki;

        tvKolichZapravok.setText(String.valueOf(roundUp(dblKolichestvoZapravok, 2)));
        tvStoimostZapravki.setText(String.valueOf(roundUp(dblStoimostZapravki, 2)));
        tvStoimostMoyki.setText(String.valueOf(roundUp(dblStoimostmoykiResult, 2)));
    }

    public BigDecimal roundUp(double value, int digits){
        return new BigDecimal(""+value).setScale(digits, BigDecimal.ROUND_HALF_UP);
    }
}
