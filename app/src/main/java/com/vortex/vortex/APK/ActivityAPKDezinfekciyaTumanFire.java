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

public class ActivityAPKDezinfekciyaTumanFire extends AppCompatActivity {

    EditText etVysota;
    EditText etVysotaKon;
    EditText etDlinna;
    EditText etShirina;
    EditText etKratnost;

    TextView tvObjem;
    TextView tvEkspoz;
    TextView tvRashodRastvora;
    TextView tvRashodKoncentrata;
    TextView tvForbicida;

    TableLayout tableL;
    Button btnRaschet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apkdezinfekciya_tuman_fire);
        setTitle("Использование метода горячего тумана");

        tableL = (TableLayout) findViewById(R.id.tableL);
        btnRaschet = (Button) findViewById(R.id.btnRaschet);

        etVysota = (EditText) findViewById(R.id.etVysota);
        etVysotaKon = (EditText) findViewById(R.id.etVysotaKon);
        etDlinna = (EditText) findViewById(R.id.etDlinna);
        etShirina = (EditText) findViewById(R.id.etShirina);
        etKratnost = (EditText) findViewById(R.id.etKratnost);

        tvObjem = (TextView) findViewById(R.id.tvObjem);
        tvEkspoz = (TextView) findViewById(R.id.tvEkspoz);
        tvRashodRastvora = (TextView) findViewById(R.id.tvRashodRastvora);
        tvRashodKoncentrata = (TextView) findViewById(R.id.tvRashodKoncentrata);
        tvForbicida = (TextView) findViewById(R.id.tvForbicida);

    }

    public void onClickRaschet(View view) {
        if (etVysota.getText().length() == 0 || etVysotaKon.getText().length() == 0 || etDlinna.getText().length() == 0
                || etShirina.getText().length() == 0 || etKratnost.getText().length() == 0) {
            Toast.makeText(getBaseContext(), "Заполните пожалуйста все данные", Toast.LENGTH_SHORT).show();
            return;
        }

        tableL.setVisibility(View.VISIBLE);
        int gray = Color.parseColor("#7B7979");
        btnRaschet.setBackgroundColor(gray);

        double dblVysota = Double.parseDouble(etVysota.getText().toString());
        double dblVysotaKon = Double.parseDouble(etVysotaKon.getText().toString());
        double dblDlinna = Double.parseDouble(etDlinna.getText().toString());
        double dblShirina = Double.parseDouble(etShirina.getText().toString());
        double dblKratnost = Double.parseDouble(etKratnost.getText().toString());

        double dblEkspoz = Double.parseDouble(tvEkspoz.getText().toString());
        double dblRashodRastvora = Double.parseDouble(tvRashodRastvora.getText().toString());
        double dblRashodKoncentrata = Double.parseDouble(tvRashodKoncentrata.getText().toString());

        double dblObjem = (dblDlinna * dblShirina * dblVysota) + (((dblVysotaKon - dblVysota) * dblShirina) / 2) * dblDlinna;
        double dblForbizida = (dblKratnost * dblRashodKoncentrata * dblObjem) / 1000;

        tvObjem.setText(String.valueOf(roundUp(dblObjem, 2)));
        tvForbicida.setText(String.valueOf(roundUp(dblForbizida, 2)));

    }
    public BigDecimal roundUp(double value, int digits) {
        return new BigDecimal("" + value).setScale(digits, BigDecimal.ROUND_HALF_UP);
    }
}
