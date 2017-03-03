package com.vortex.vortex.APK;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.vortex.vortex.R;

public class ActivityApkMoySredstvaVoda extends AppCompatActivity {

    private TextView tvDh;
    private TextView tvDh2;
    private TextView tvVoda;
    private EditText etVoda;

    private double voda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apk_moy_sredstva_voda);
        setTitle("Подбор средства под жесткость воды");
        tvDh = (TextView) findViewById(R.id.tvDh);
        tvDh2 = (TextView) findViewById(R.id.tvDh2);
        tvVoda = (TextView) findViewById(R.id.tvVoda);
        etVoda = (EditText) findViewById(R.id.etVoda);
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
                    tvDh2.setText("°Ж");
                    if(etVoda.getText().length() != 0){
                        tvVoda.setText(etVoda.getText().toString());
                    }
                }
                    break;
            case R.id.rbDh:
                if (checked){
                    tvDh.setText("°DH");
                    tvDh2.setText("°DH");
                    if(etVoda.getText().length() != 0){
                        voda =  voda*0.36;
                        tvVoda.setText(String.valueOf(voda));
                    }
                }
                    break;
            case R.id.rbMgL:
                if (checked){
                    tvDh.setText("мг - экв/л");
                    tvDh2.setText("мг - экв/л");
                    if(etVoda.getText().length() != 0){
                        tvVoda.setText(etVoda.getText().toString());
                    }
                }
                    break;
        }
    }


}
