package com.vortex.vortex.APK;

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

public class ActivityAPKKopytaProfilaktika extends AppCompatActivity {

    double vanna = 200;

    EditText etStado;
    EditText etPeriod;
    EditText etDesimix;

    TextView tvTrebuemObrabot;
    TextView tvTrebuemVann;
    TextView tvVsegoDesimix;

    Button btnRaschet;
    TableLayout tableL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apkkopyta_profilaktika);
        setTitle("Программа «Профилактика»");

        btnRaschet = (Button) findViewById(R.id.btnRaschet);
        tableL = (TableLayout) findViewById(R.id.tableL);

        etStado = (EditText) findViewById(R.id.etStado);
        etPeriod = (EditText) findViewById(R.id.etPeriod);
        etDesimix = (EditText) findViewById(R.id.etDesimix);

        tvTrebuemObrabot = (TextView) findViewById(R.id.tvTrebuemObrabot);
        tvTrebuemVann = (TextView) findViewById(R.id.tvTrebuemVann);
        tvVsegoDesimix = (TextView) findViewById(R.id.tvVsegoDesimix);

    }

    public void onClickRaschet(View view) {
        if (etStado.getText().length() == 0 || etPeriod.getText().length() == 0
                || etDesimix.getText().length() == 0) {
            Toast.makeText(getBaseContext(), "Заполните пожалуйста все данные", Toast.LENGTH_SHORT).show();
            return;
        }

        int gray = Color.parseColor("#7B7979");
        btnRaschet.setBackgroundColor(gray);
        tableL.setVisibility(View.VISIBLE);

        double stado = Double.parseDouble(etStado.getText().toString());
        double period = Double.parseDouble(etPeriod.getText().toString());
        double desimix = Double.parseDouble(etDesimix.getText().toString());

        double percentDesimix = (vanna * desimix) / 100;

        double trebuemobrabotok = (period / 7) * 6;
        double trebuemVann = (stado * trebuemobrabotok) / 500;
        double vsegoDesimix = percentDesimix * trebuemVann;

        tvTrebuemObrabot.setText(String.valueOf(roundUp(trebuemobrabotok, 0)));
        tvTrebuemVann.setText(String.valueOf(roundUp(trebuemVann, 0)));
        tvVsegoDesimix.setText(String.valueOf(roundUp(vsegoDesimix, 0)));
    }

    public BigDecimal roundUp(double value, int digits) {
        return new BigDecimal("" + value).setScale(digits, BigDecimal.ROUND_HALF_UP);
    }

    public void onClickObrabotka(View view) {
        Toast.makeText(getBaseContext(), "3 дня в неделю, 2 раза в день", Toast.LENGTH_SHORT).show();
    }

    public void onClickVanna(View view) {
        Toast.makeText(getBaseContext(), "Проходимость составляет 500 голов через 200 литров", Toast.LENGTH_SHORT).show();
    }
}
