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
    private EditText etVoda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apk_moy_sredstva_voda);
        setTitle("Подбор средства под жесткость воды");
        tvDh = (TextView) findViewById(R.id.tvDh);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rbDjeskost:
                if (checked){
                    tvDh.setText("°Ж");
                }
                    break;
            case R.id.rbDh:
                if (checked){
                    tvDh.setText("°DH");
                }
                    break;
            case R.id.rbMgL:
                if (checked){
                    tvDh.setText("мг - экв/л");
                }
                    break;
        }
    }


}
