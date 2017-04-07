package com.vortex.vortex.APK;

import android.graphics.Bitmap;
import android.graphics.Color;
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
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.vortex.vortex.R;

import java.math.BigDecimal;

public class ActivityApkMoySredstvaVoda extends AppCompatActivity {

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
    String strVyborJVody;
    TableLayout tableL;
    Button btnSredstva;
    String strJoskost = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apk_moy_sredstva_voda);
        setTitle("Подбор средства под жесткость воды");
        tvVoda = (TextView) findViewById(R.id.tvVoda);
        tvVodaStr = (TextView) findViewById(R.id.tvVodaStr);
        etVoda = (EditText) findViewById(R.id.etVoda);
        rbDjeskost = (RadioButton) findViewById(R.id.rbDjeskost);
        rbMgL = (RadioButton) findViewById(R.id.rbMgL);
        rbDh = (RadioButton) findViewById(R.id.rbDh);

        btnSredstva = (Button) findViewById(R.id.btnSredstva);

        tvSheloch1 = (TextView) findViewById(R.id.tvSheloch1);
        tvKislot1 = (TextView) findViewById(R.id.tvKislot1);
        tvSheloch2 = (TextView) findViewById(R.id.tvSheloch2);
        tvKislot2 = (TextView) findViewById(R.id.tvKislot2);
        tvSheloch3 = (TextView) findViewById(R.id.tvSheloch3);
        tvKislot3 = (TextView) findViewById(R.id.tvKislot3);
        tvSheloch4 = (TextView) findViewById(R.id.tvSheloch4);
        tvKislot4 = (TextView) findViewById(R.id.tvKislot4);

        tableL = (TableLayout) findViewById(R.id.tableL);

        strVyborJVody = "выбранная жесткость воды " + strJoskost + " °Ж";
        tvVoda.setText(strVyborJVody);

        ((EditText) findViewById(R.id.etVoda)).addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                strVyborJVody = null;
                strJoskost = "0";

                if (etVoda.getText().length() != 0) {
                    voda = Double.parseDouble(etVoda.getText().toString());
                    if (rbDjeskost.isChecked() == true) {

                        strJoskost = etVoda.getText().toString();
                        ReturnVoda(strJoskost);
                    } else if (rbDh.isChecked() == true) {
                        voda = voda * 0.36;
                        strJoskost = String.valueOf(roundUp(voda, 2));
                        ReturnVoda(strJoskost);
                    } else if (rbMgL.isChecked() == true) {
                        strJoskost = etVoda.getText().toString();
                        ReturnVoda(strJoskost);
                    }

                    strVyborJVody = "выбранная жесткость воды " + strJoskost + " °Ж";
                    tvVoda.setText(strVyborJVody);

                } else {
                    strVyborJVody = "выбранная жесткость воды " + strJoskost + " °Ж";
                    tvVoda.setText(strVyborJVody);
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

    private void ReturnVoda(String tvVoda) {
        double vod = Double.parseDouble(tvVoda);
        if (0 <= vod && vod < 1.5) {
            tvVodaStr.setText("вода очень мягкая");
        } else if (1.5 <= vod && vod < 3) {
            tvVodaStr.setText("вода мягкая");
        } else if (3 <= vod && vod < 6) {
            tvVodaStr.setText("вода умеренной жесткости");
        } else if (6 <= vod && vod <= 12) {
            tvVodaStr.setText("вода жесткая");
        } else if (vod > 12) {
            tvVodaStr.setText("вода очень жесткая");
        } else {
            tvVodaStr.setText("");
        }

    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        strVyborJVody = null;
        strJoskost = "0";

        if (etVoda.getText().length() != 0)
            voda = Double.parseDouble(etVoda.getText().toString());
        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.rbDjeskost:
                if (checked) {
                    if (etVoda.getText().length() != 0) {
                        strJoskost = etVoda.getText().toString();
                        ReturnVoda(strJoskost);
                    }
                }
                break;
            case R.id.rbDh:
                if (checked) {
                    if (etVoda.getText().length() != 0) {
                        voda = voda * 0.36;
                        strJoskost = String.valueOf(roundUp(voda, 2));
                        ReturnVoda(strJoskost);
                    }
                }
                break;
            case R.id.rbMgL:
                if (checked) {
                    if (etVoda.getText().length() != 0) {
                        strJoskost = etVoda.getText().toString();
                        ReturnVoda(strJoskost);
                    }
                }
                break;
        }
        strVyborJVody = "выбранная жесткость воды " + strJoskost + " °Ж";
        tvVoda.setText(strVyborJVody);
    }

    public BigDecimal roundUp(double value, int digits) {
        return new BigDecimal("" + value).setScale(digits, BigDecimal.ROUND_HALF_UP);
    }

    public void onClickRaschetRekomendSredstv(View view) {
        int gray = Color.parseColor("#7B7979");
        btnSredstva.setBackgroundColor(gray);
        double voda = Double.parseDouble(strJoskost);

        String biotekC = "";
        String ksilanK = "";
        String biotek = "";
        String ksilan = "";
        String biotekM = "";
        String ksilanM = "";
        String biotekSuper = "";
        String ksilanSuper = "";

        tableL.setVisibility(View.VISIBLE);

        if (voda <= 1) {
            biotekM = "BIOTEC M, 0.4%";
            ksilanM = "KSILAN M, 0.4%";
        } else if (voda <= 2) {
            biotekM = "BIOTEC M, 0.5%";
            ksilanM = "KSILAN M, 0.5%";
        } else if (voda <= 3) {
            biotekM = "BIOTEC M, 0.7%";
            ksilanM = "KSILAN M, 0.7%";
        } else if (voda <= 4) {
            biotekM = "BIOTEC M, 1%";
            ksilanM = "KSILAN M, 1%";
        } else {
            biotekM = "BIOTEC M не используется";
            ksilanM = "KSILAN M не используется";
        }

        if (voda <= 5) {
            biotekC = "BIOTEC C, 0.3%";
            ksilanK = "KSILAN K, 0.3%";
        } else if (voda <= 6.5) {
            biotekC = "BIOTEC C, 0.5%";
            ksilanK = "KSILAN K, 0.5%";
        } else if (voda <= 8) {
            biotekC = "BIOTEC C, 1%";
            ksilanK = "KSILAN K, 1%";
        } else {
            biotekC = "BIOTEC C не используется";
            ksilanK = "KSILAN K не используется";
        }

        if (voda <= 5) {
            biotek = "BIOTEC, 0.3%";
            ksilan = "KSILAN, 0.3%";
        } else if (voda <= 6.5) {
            biotek = "BIOTEC, 0.5%";
            ksilan = "KSILAN, 0.5%";
        } else if (voda <= 8) {
            biotek = "BIOTEC, 0.7%";
            ksilan = "KSILAN, 0.7%";
        } else {
            biotek = "BIOTEC не используется";
            ksilan = "KSILAN не используется";
        }

        if (voda <= 10) {
            biotekSuper = "BIOTEC SUPER, 0.3%";
            ksilanSuper = "KSILAN SUPER, 0.3%";
        } else if (voda <= 12) {
            biotekSuper = "BIOTEC SUPER, 0.4%";
            ksilanSuper = "KSILAN SUPER, 0.4%";
        } else {
            biotekSuper = "BIOTEC SUPER не используется";
            ksilanSuper = "KSILAN SUPER не используется";
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
