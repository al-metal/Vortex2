package com.vortex.vortex.APK;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vortex.vortex.R;

import java.math.BigDecimal;

public class ApkMoySredstvaSravnenieActivity extends AppCompatActivity {

    double resStoimKgVotrex = 0;
    double resStoimLVotrex = 0;
    double resStoimPromivVotrex = 0;
    double resStoimSmesiVotrex = 0;
    double resPlotnostVotrex = 0;

    private EditText etStoim;
    private EditText etKoncentrat;
    private EditText etVanna;
    private EditText etVes;
    private EditText etPlotnost;

    private TextView tvresPlotnostVortex;
    private TextView tvresStoimKgtVortex;
    private TextView tvresStoimLVortex;
    private TextView tvresStoimPromivtVortex;
    private TextView tvresStoimSmesitVortex;

    private TextView tvStoimKgSrav;
    private TextView tvStoimLSrav;
    private TextView tvStoimPromivSrav;
    private TextView tvStoimSmesiSrav;
    private TextView tvPlotnostSrav;

    double resStoimKg;
    double resStoimL;
    double resStoimPromiv;
    double resStoimSmesi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apk_moy_sredstva_sravnenie);

        resPlotnostVotrex = getIntent().getExtras().getDouble("resPlotnost");
        resStoimKgVotrex = getIntent().getExtras().getDouble("resStoimKg");
        resStoimLVotrex = getIntent().getExtras().getDouble("resStoimL");
        resStoimPromivVotrex = getIntent().getExtras().getDouble("resStoimPromiv");
        resStoimSmesiVotrex = getIntent().getExtras().getDouble("resStoimSmesi");

        etStoim = (EditText)findViewById(R.id.etStoim);
        etKoncentrat = (EditText)findViewById(R.id.etKoncentrat);
        etVanna = (EditText)findViewById(R.id.etVanna);
        etVes = (EditText)findViewById(R.id.etVes);
        etPlotnost = (EditText)findViewById(R.id.etPlotnost);


        tvresPlotnostVortex = (TextView) findViewById(R.id.tvPlotnostVortex);
        tvresStoimKgtVortex = (TextView) findViewById(R.id.tvStoimKgVortex);
        tvresStoimLVortex = (TextView) findViewById(R.id.tvStoimLVortex);
        tvresStoimPromivtVortex = (TextView) findViewById(R.id.tvStoimPromivVortex);
        tvresStoimSmesitVortex = (TextView) findViewById(R.id.tvLitrSmesiVortex);

        tvresPlotnostVortex.setText(String.valueOf(roundUp(resPlotnostVotrex, 2)));
        tvresStoimKgtVortex.setText(String.valueOf(roundUp(resStoimKgVotrex, 2)));
        tvresStoimLVortex.setText(String.valueOf(roundUp(resStoimLVotrex, 2)));
        tvresStoimPromivtVortex.setText(String.valueOf(roundUp(resStoimPromivVotrex, 2)));
        tvresStoimSmesitVortex.setText(String.valueOf(roundUp(resStoimSmesiVotrex, 2)));

        resStoimKg = 0;
        resStoimL = 0;
        resStoimPromiv = 0;
        resStoimSmesi = 0;


    }

    public BigDecimal roundUp(double value, int digits){
        return new BigDecimal(""+value).setScale(digits, BigDecimal.ROUND_HALF_UP);
    }

    public void onClickApkRes(View view) {

        double stoimost = 0;
        double ves = 0;
        double koncentrat = 0;
        double vanna = 0;
        double plotnost = 0;

        etStoim = (EditText)findViewById(R.id.etStoim);
        etKoncentrat = (EditText)findViewById(R.id.etKoncentrat);
        etVanna = (EditText)findViewById(R.id.etVanna);
        etVes = (EditText)findViewById(R.id.etVes);
        etPlotnost = (EditText)findViewById(R.id.etPlotnost);

        tvStoimKgSrav = (TextView) findViewById(R.id.tvStoimKgSravnenie);
        tvStoimLSrav = (TextView) findViewById(R.id.tvStoimLSravnenie);
        tvStoimPromivSrav = (TextView) findViewById(R.id.tvStoimPromivSravnenie);
        tvStoimSmesiSrav = (TextView) findViewById(R.id.tvLitrSmesiSravnenie);
        tvPlotnostSrav = (TextView) findViewById(R.id.tvPlotnostSravnenie);

        stoimost = Double.parseDouble(etStoim.getText().toString());
        koncentrat = Double.parseDouble(etKoncentrat.getText().toString());
        vanna = Double.parseDouble(etVanna.getText().toString());
        ves = Double.parseDouble(etVes.getText().toString());
        plotnost = Double.parseDouble(etPlotnost.getText().toString());

        resStoimKg = stoimost/ves;
        resStoimL = resStoimKg * plotnost;
        resStoimPromiv = (resStoimL*koncentrat*vanna)/100;
        resStoimSmesi = resStoimPromiv / vanna;

        tvStoimKgSrav.setText(String.valueOf(roundUp(resStoimKg, 2)));
        tvStoimLSrav.setText(String.valueOf(roundUp(resStoimL, 2)));
        tvStoimPromivSrav.setText(String.valueOf(roundUp(resStoimPromiv, 2)));
        tvStoimSmesiSrav.setText(String.valueOf(roundUp(resStoimSmesi, 2)));
        tvPlotnostSrav.setText(String.valueOf(roundUp(plotnost, 2)));
    }
}
