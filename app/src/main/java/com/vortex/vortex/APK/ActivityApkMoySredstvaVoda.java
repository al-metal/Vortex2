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

}
