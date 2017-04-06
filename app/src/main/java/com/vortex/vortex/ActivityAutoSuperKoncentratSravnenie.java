package com.vortex.vortex;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

public class ActivityAutoSuperKoncentratSravnenie extends AppCompatActivity {

    double dblPriceVortex;
    String strObjemVortex;
    double dblRazbavlenieVortex;
    double dblKolichestvoZapravokVortex;
    double dblStoimostZapravkiVortex;
    double dblStoimostMoykiVortex;
    double dblKooficient;
    double dblKolichestvoKanistr;

    TextView tvCena2KanistrVortex;
    TextView tvObjemVortex;
    TextView tvRazbavlenieVortex;
    TextView tvKolichestvoZapravokVortex;
    TextView tvStoimostZapravokVortex;
    TextView tvStoimostMoykiVortex;

    TextView tvCena2Kanistr;
    TextView tvObjem;
    TextView tvRazbavlenie;
    TextView tvKolichestvoZapravok;
    TextView tvStoimostZapravok;
    TextView tvStoimostMoyki;
    TextView tvObshStoim;
    TextView tvPoStoim;

    EditText etPrice;
    EditText etObjem;
    EditText etRazbavlenie;
    Button btnRaschet;
    TableLayout tableL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_super_koncentrat_sravnenie);
        setTitle("Сравнить с другим средством");

        btnRaschet = (Button) findViewById(R.id.btnRaschet);
        tableL = (TableLayout) findViewById(R.id.tableL);

        dblPriceVortex = getIntent().getExtras().getDouble("dblPrice");
        strObjemVortex = getIntent().getExtras().getString("Objem");
        dblRazbavlenieVortex = getIntent().getExtras().getDouble("dblRazbavlenie");
        dblKolichestvoZapravokVortex = getIntent().getExtras().getDouble("dblKolichestvoZapravok");
        dblStoimostZapravkiVortex = getIntent().getExtras().getDouble("dblStoimostZapravki");
        dblStoimostMoykiVortex = getIntent().getExtras().getDouble("dblStoimostMoyki");
        dblKooficient = getIntent().getExtras().getDouble("dblKooficent");
        dblKolichestvoKanistr = getIntent().getExtras().getDouble("dblKolichestvoKanistr");

        tvCena2KanistrVortex = (TextView) findViewById(R.id.tvCena2KanistrVortex);
        tvObjemVortex = (TextView) findViewById(R.id.tvObjemVortex);
        tvRazbavlenieVortex = (TextView) findViewById(R.id.tvRazbavlenieVortex);
        tvKolichestvoZapravokVortex = (TextView) findViewById(R.id.tvKolichestvoZapravokVortex);
        tvStoimostZapravokVortex = (TextView) findViewById(R.id.tvStoimostZapravokVortex);
        tvStoimostMoykiVortex = (TextView) findViewById(R.id.tvStoimostMoykiVortex);
        tvObshStoim = (TextView) findViewById(R.id.tvObshStoim);
        tvPoStoim = (TextView) findViewById(R.id.tvPoStoim);

        tvCena2Kanistr = (TextView) findViewById(R.id.tvCena2Kanistr);
        tvObjem = (TextView) findViewById(R.id.tvObjem);
        tvRazbavlenie = (TextView) findViewById(R.id.tvRazbavlenie);
        tvKolichestvoZapravok = (TextView) findViewById(R.id.tvKolichestvoZapravok);
        tvStoimostZapravok = (TextView) findViewById(R.id.tvStoimostZapravok);
        tvStoimostMoyki = (TextView) findViewById(R.id.tvStoimostMoyki);

        tvCena2KanistrVortex.setText(String.valueOf(roundUp(dblPriceVortex, 2)));
        tvObjemVortex.setText(String.valueOf(strObjemVortex));
        tvRazbavlenieVortex.setText(String.valueOf(roundUp(dblRazbavlenieVortex, 2)));
        tvKolichestvoZapravokVortex.setText(String.valueOf(roundUp(dblKolichestvoZapravokVortex, 2)));
        tvStoimostZapravokVortex.setText(String.valueOf(roundUp(dblStoimostZapravkiVortex, 2)));
        tvStoimostMoykiVortex.setText(String.valueOf(roundUp(dblStoimostMoykiVortex, 2)));

        etPrice = (EditText) findViewById(R.id.etPrice);
        etObjem = (EditText) findViewById(R.id.etObjem);
        etRazbavlenie = (EditText) findViewById(R.id.etRazbavlenie);
    }

    public BigDecimal roundUp(double value, int digits) {
        return new BigDecimal("" + value).setScale(digits, BigDecimal.ROUND_HALF_UP);
    }

    public void onClickRaschet(View view) {
        if (etPrice.getText().length() == 0 || etObjem.getText().length() == 0 || etRazbavlenie.getText().length() == 0) {
            Toast.makeText(getBaseContext(), "Заполните пожалуйста все данные", Toast.LENGTH_SHORT).show();
            return;
        }
        tableL.setVisibility(View.VISIBLE);
        int gray = Color.parseColor("#7B7979");
        btnRaschet.setBackgroundColor(gray);

        double dblPriceKanistr = Double.parseDouble(etPrice.getText().toString());
        double dblObjem = Double.parseDouble(etObjem.getText().toString());
        double dblRazbavlenie = Double.parseDouble(etRazbavlenie.getText().toString());

        double dblPrice2Kanistr = dblPriceKanistr * dblKolichestvoKanistr;
        double dblKolichestvoZapravok = (dblObjem * 1000) / dblRazbavlenie;
        double dblStoimostZapravki = dblPrice2Kanistr / dblKolichestvoZapravok;
        double dblStoimostMoyki = dblStoimostZapravki / dblKooficient;

        tvCena2Kanistr.setText(String.valueOf(roundUp(dblPrice2Kanistr, 2)));
        tvObjem.setText(String.valueOf(roundUp(dblObjem, 2)));
        tvRazbavlenie.setText(String.valueOf(roundUp(dblRazbavlenie, 2)));
        tvKolichestvoZapravok.setText(String.valueOf(roundUp(dblKolichestvoZapravok, 2)));
        tvStoimostZapravok.setText(String.valueOf(roundUp(dblStoimostZapravki, 2)));
        tvStoimostMoyki.setText(String.valueOf(roundUp(dblStoimostMoyki, 2)));

        double obshayaStoim = (dblPrice2Kanistr - dblPriceVortex) / dblPrice2Kanistr * 100;
        double poStoimosti = (dblStoimostMoyki - dblStoimostMoykiVortex) / dblStoimostMoyki * 100;

        tvObshStoim.setText(String.valueOf(roundUp(obshayaStoim, 2)));
        tvPoStoim.setText(String.valueOf(roundUp(poStoimosti, 2)));

    }
}
