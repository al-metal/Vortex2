package com.vortex.vortex.APK;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.vortex.vortex.R;

import java.math.BigDecimal;

public class ActivityApkMoySredstvaVoda extends AppCompatActivity {

    private TextView tvDh;
    private TextView tvVoda;
    private EditText etVoda;
    private TextView tvVodaStr;
    private RadioButton rbDjeskost;
    private RadioButton rbDh;
    private RadioButton rbMgL;

    private TextView tvSheloch1;
    private TextView tvKislot1;
    private TextView tvSheloch2;
    private TextView tvKislot2;
    private TextView tvSheloch3;
    private TextView tvKislot3;
    private TextView tvSheloch4;
    private TextView tvKislot4;

    private double voda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apk_moy_sredstva_voda);
        setTitle("Подбор средства под жесткость воды");
        tvDh = (TextView) findViewById(R.id.tvDh);
        tvVoda = (TextView) findViewById(R.id.tvVoda);
        tvVodaStr = (TextView) findViewById(R.id.tvVodaStr);
        etVoda = (EditText) findViewById(R.id.etVoda);
        rbDjeskost = (RadioButton) findViewById(R.id.rbDjeskost);
        rbMgL = (RadioButton) findViewById(R.id.rbMgL);
        rbDh = (RadioButton) findViewById(R.id.rbDh);

        tvSheloch1 = (TextView) findViewById(R.id.tvSheloch1);
        tvKislot1 = (TextView) findViewById(R.id.tvKislot1);
        tvSheloch2 = (TextView) findViewById(R.id.tvSheloch2);
        tvKislot2 = (TextView) findViewById(R.id.tvKislot2);
        tvSheloch3 = (TextView) findViewById(R.id.tvSheloch3);
        tvKislot3 = (TextView) findViewById(R.id.tvKislot3);
        tvSheloch4 = (TextView) findViewById(R.id.tvSheloch4);
        tvKislot4 = (TextView) findViewById(R.id.tvKislot4);

        ((EditText) findViewById(R.id.etVoda)).addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(etVoda.getText().length() != 0){
                    voda = Double.parseDouble(etVoda.getText().toString());
                    if(rbDjeskost.isChecked() == true){
                        tvVoda.setText(etVoda.getText().toString());
                        ReturnVoda(tvVoda);
                    }
                    else if(rbDh.isChecked() == true){
                        voda =  voda*0.36;
                        tvVoda.setText(String.valueOf(roundUp(voda, 2)));
                        ReturnVoda(tvVoda);
                    }
                    else if(rbMgL.isChecked() == true){
                        tvVoda.setText(etVoda.getText().toString());
                        ReturnVoda(tvVoda);
                    }
                }
                else {
                    tvVoda.setText("0");
                }
            }



            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // текст будет изменен
            }

            @Override
            public void afterTextChanged(Editable s) {
                // текст уже изменили
            }
        });

    }

    private void ReturnVoda(TextView tvVoda) {
        double vod = Double.parseDouble(tvVoda.getText().toString());
        if (0 <= vod && vod < 1.5)
        {
            tvVodaStr.setText("очень мягкая");
        }
        else if (1.5 <= vod && vod < 3)
        {
            tvVodaStr.setText("мягкая");
        }
        else if (3 <= vod && vod < 6)
        {
            tvVodaStr.setText("умеренной жесткости");
        }
        else if (6 <= vod && vod <= 12)
        {
            tvVodaStr.setText("жесткая");
        }
        else if (vod > 12)
        {
            tvVodaStr.setText("очень жесткая");
        }
        else
        {
            tvVodaStr.setText("");
        }

    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        if(etVoda.getText().length() != 0)
        voda = Double.parseDouble(etVoda.getText().toString());
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rbDjeskost:
                if (checked){
                    tvDh.setText("°Ж");
                    if(etVoda.getText().length() != 0){
                        tvVoda.setText(etVoda.getText().toString());
                        ReturnVoda(tvVoda);
                    }
                }
                    break;
            case R.id.rbDh:
                if (checked){
                    tvDh.setText("°DH");
                    if(etVoda.getText().length() != 0){
                        voda =  voda*0.36;
                        tvVoda.setText(String.valueOf(roundUp(voda, 2)));
                        ReturnVoda(tvVoda);
                    }
                }
                    break;
            case R.id.rbMgL:
                if (checked){
                    tvDh.setText("мг - экв/л");
                    if(etVoda.getText().length() != 0){
                        tvVoda.setText(etVoda.getText().toString());
                        ReturnVoda(tvVoda);
                    }
                }
                    break;
        }
    }


    public BigDecimal roundUp(double value, int digits){
        return new BigDecimal(""+value).setScale(digits, BigDecimal.ROUND_HALF_UP);
    }

    public void onClickRaschetRekomendSredstv(View view) {
        double voda = Double.parseDouble(tvVoda.getText().toString());
        String biotekC = "";
        String ksilanK = "";
        String biotek = "";
        String ksilan = "";
        String biotekM = "";
        String ksilanM = "";
        String biotekSuper = "";
        String ksilanSuper = "";

        if (voda <= 1)
        {
            biotekM = "Biotec M, 0,4%";
            ksilanM = "Ksilan M, 0,4%";
        }
        else if (voda <= 2)
        {
            biotekM = "Biotec M, 0,5%";
            ksilanM = "Ksilan M, 0,5%";
        }
        else if (voda <= 3)
        {
            biotekM = "Biotec M, 0,7%";
            ksilanM = "Ksilan M, 0,7%";
        }
        else if (voda <= 4)
        {
            biotekM = "Biotec M, 1%";
            ksilanM = "Ksilan M, 1%";
        }
        else
        {
            biotekM = "Biotec M не используется";
            ksilanM = "Ksilan M не используется";
        }




        if (voda <= 5)
        {
            biotekC = "Biotec C, 0,3%";
            ksilanK = "Ksilan K, 0,3%";
        }
        else if (voda <= 6.5)
        {
            biotekC = "Biotec C, 0,5%";
            ksilanK = "Ksilan K, 0,5%";
        }
        else if (voda <= 8)
        {
            biotekC = "Biotec C, 1%";
            ksilanK = "Ksilan K, 1%";
        }
        else
        {
            biotekC = "Biotec C не используется";
            ksilanK = "Ksilan K не используется";
        }



        if (voda <= 5)
        {
            biotek = "Biotec, 0,3%";
            ksilan = "Ksilan, 0,3%";
        }
        else if (voda <= 6.5)
        {
            biotek = "Biotec, 0,5%";
            ksilan = "Ksilan, 0,5%";
        }
        else if (voda <= 8)
        {
            biotek = "Biotec, 0,7%";
            ksilan = "Ksilan, 0,7%";
        }
        else
        {
            biotek = "Biotec не используется";
            ksilan = "Ksilan не используется";
        }



        if (voda <= 10)
        {
            biotekSuper = "Biotec Super, 0,3%";
            ksilanSuper = "Ksilan Super, 0,3%";
        }
        else if (voda <= 12)
        {
            biotekSuper = "Biotec Super, 0,4%";
            ksilanSuper = "Ksilan Super, 0,4%";
        }
        else
        {
            biotekSuper = "Biotec Super не используется";
            ksilanSuper = "Ksilan Super не используется";
        }

        tvSheloch1.setText(biotekM);
        tvKislot1.setText(ksilanM);
        tvSheloch2.setText(biotekC);
        tvKislot2.setText(ksilanK);
        tvSheloch3.setText(biotek);
        tvKislot3.setText(ksilan);
        tvSheloch4.setText(biotekSuper);
        tvKislot4.setText(ksilanSuper);
    }
}
