package com.vortex.vortex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

public class ActivityAPKDezinfekciyaTumanIce extends AppCompatActivity {
    String[] data = {"2", "3", "4", "5"};
    double dblSpinerKoncentraciya = 0;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apkdezinfekciya_tuman_ice);
        setTitle("Использование метода холодного тумана");

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

        Spinner spinner = (Spinner) findViewById(R.id.spinner6);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        // заголовок
        spinner.setPrompt("Концентрация использования, %");

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                if (pos == 0)
                    dblSpinerKoncentraciya = 2;
                else if (pos == 1)
                    dblSpinerKoncentraciya = 3;
                else if (pos == 2)
                    dblSpinerKoncentraciya = 4;
                else if (pos == 3)
                    dblSpinerKoncentraciya = 5;
            }
        });
    }

    public void onClickRaschet(View view) {
        if (etVysota.getText().length() == 0 || etVysotaKon.getText().length() == 0 || etDlinna.getText().length() == 0
                || etShirina.getText().length() == 0 || etKratnost.getText().length() == 0) {
            Toast.makeText(getBaseContext(), "Заполните пожалуйста все данные", Toast.LENGTH_SHORT).show();
            return;
        }
        double dblVysota = Double.parseDouble(etVysota.getText().toString());
        double dblVysotaKon = Double.parseDouble(etVysotaKon.getText().toString());
        double dblDlinna = Double.parseDouble(etDlinna.getText().toString());
        double dblShirina = Double.parseDouble(etShirina.getText().toString());
        double dblKratnost = Double.parseDouble(etKratnost.getText().toString());

        double dblEkspoz = Double.parseDouble(tvEkspoz.getText().toString());
        double dblRashodRastvora = Double.parseDouble(tvRashodRastvora.getText().toString());

        double dblObjem = (dblDlinna * dblShirina * dblVysota) + (((dblVysotaKon - dblVysota) * dblShirina) / 2) * dblDlinna;
        double dblKoncentraciya = (30 * dblSpinerKoncentraciya) / 100;
        double dblForbizida = (dblKratnost * dblKoncentraciya * dblObjem) / 1000;

        tvObjem.setText(String.valueOf(roundUp(dblObjem, 2)));
        tvRashodKoncentrata.setText(String.valueOf(roundUp(dblKoncentraciya, 2)));
        tvForbicida.setText(String.valueOf(roundUp(dblForbizida, 2)));

    }

    public BigDecimal roundUp(double value, int digits) {
        return new BigDecimal("" + value).setScale(digits, BigDecimal.ROUND_HALF_UP);
    }
}
