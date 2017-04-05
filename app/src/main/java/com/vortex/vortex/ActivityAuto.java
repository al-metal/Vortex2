package com.vortex.vortex;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.vortex.vortex.APK.ActivityAPKDezinfekciyaProfilaktikaForbicid;
import com.vortex.vortex.APK.ActivityAPKDezinfekciyaProfilaktikaForbicidSravnenie;

import java.math.BigDecimal;

public class ActivityAuto extends AppCompatActivity {

    LinearLayout llmain;

    String[] data = {"ВЫБРАТЬ УСТРОЙСТВО", "ПЕНОГЕНЕРАТОР, 50 Л", "ПЕНОКОМПЛЕКТ", "ДОЗАТРОН"};

    String[][] arr = {};

    //region Array
    String[][] peno50ligth = {{"", "Разбавление", "Количество, мл"},
            {"Unior", "1:60", "850"},
            {"Tiro, Tiro Tone", "1:80", "625"},
            {"Master, Master Tone", "1:100", "500"},
            {"Profy", "1:120", "425"},
            {"Dozex", "1:100", "500"},
            {"Ace", "1:140", "350"},
            {"Magnat", "1:110", "450"},
            {"Delicate", "1:80", "625"},
            {"Solo", "1:70", "725"},
            {"DIY", "1:120", "425"}};

    String[][] peno50default = {{"", "Разбавление", "Количество, мл"},
            {"Unior", "1:40", "1200"},
            {"Tiro, Tiro Tone", "1:60", "800"},
            {"Novice", "1:80", "600"},
            {"Master, Master Tone", "1:80", "600"},
            {"Tutor ", "1:100", "500"},
            {"Profy", "1:100", "500"},
            {"Dozex", "1:90", "550"},
            {"Senza", "1:120", "400"},
            {"Ace", "1:120", "400"},
            {"Magnat", "1:100", "500"},
            {"Delicate", "1:60", "800"},
            {"Solo", "1:55", "900"},
            {"DIY", "1:100", "500"}};

    String[][] peno50hard = {{"", "Разбавление", "Количество, мл"},
            {"Novice", "1:50", "1000"},
            {"Tutor ", "1:60", "800"},
            {"Profy", "1:60", "800"},
            {"Senza", "1:80", "600"},
            {"Ace", "1:80", "600"},
            {"Magnat", "1:60", "800"},
            {"DIY", "1:60", "800"}};

    String[][] penokomplektligth = {{"", "Разбавление", "Количество, мл"},
            {"Unior", "1:4", "200"},
            {"Tiro, Tiro Tone", "1:6", "150"},
            {"Master, Master Tone", "1:7", "110"},
            {"Profy", "1:10", "90"},
            {"Dozex", "1:9", "100"},
            {"Ace", "1:12", "80"},
            {"Magnat", "1:9", "100"},
            {"Delicate", "1:6", "150"},
            {"Solo", "1:5", "170"},
            {"DIY", "1:10", "90"}};

    String[][] penokomplektdefault = {{"", "Разбавление", "Количество, мл"},
            {"Unior", "1:2", "300"},
            {"Tiro, Tiro Tone", "1:4", "200"},
            {"Novice", "1:6", "145"},
            {"Master, Master Tone", "1:6", "145"},
            {"Tutor ", "1:8", "110"},
            {"Profy", "1:8", "110"},
            {"Dozex", "1:7", "125"},
            {"Senza", "1:10", "90"},
            {"Ace", "1:10", "90"},
            {"Magnat", "1:8", "110"},
            {"Delicate", "1:4", "200"},
            {"Solo", "1:4", "200"},
            {"DIY", "1:8", "110"}};

    String[][] penokomplekthard = {{"", "Разбавление", "Количество, мл"},
            {"Novice", "1:4", "200"},
            {"Tutor ", "1:6", "145"},
            {"Profy", "1:6", "145"},
            {"Senza", "1:7", "125"},
            {"Ace", "1:7", "125"},
            {"Magnat", "1:6", "145"},
            {"DIY", "1:3", "250"}};

    String[][] dozatronligth = {{"", "Концентрация, %"},
            {"Unior", "2"},
            {"Tiro, Tiro Tone", "1.5"},
            {"Master, Master Tone", "1"},
            {"Profy", "1"},
            {"Dozex", "1"},
            {"Ace", "0.5"},
            {"Delicate", "1.5"},
            {"Solo", "1.5"},
            {"DIY", "1"}};

    String[][] dozatrondefault = {{"", "Концентрация, %"},
            {"Unior", "3"},
            {"Tiro, Tiro Tone", "2"},
            {"Novice", "1.5"},
            {"Master, Master Tone", "1.5"},
            {"Tutor ", "1"},
            {"Profy", "1"},
            {"Dozex", "1"},
            {"Senza", "1"},
            {"Ace", "1"},
            {"Delicate", "2"},
            {"Solo", "2"},
            {"DIY", "1"}};

    String[][] dozatronhard = {{"", "Концентрация, %"},
            {"Novice", "2"},
            {"Tutor ", "2"},
            {"Profy", "2"},
            {"Senza", "1.5"},
            {"Ace", "1.5"},
            {"DIY", "2"}};
//endregion

    private EditText etVoda;
    private TextView tvDh;
    private TextView tvVoda;
    private TextView tvVodaStr;

    private RadioButton rbDjeskost;
    private RadioButton rbDh;
    private RadioButton rbMgL;
    private Button btnRaschet;

    private double voda;
    String spin;
    String sredstvo;
    String strVoda;
    String strVyborJVody;
    String strJoskost = "0";

    boolean selectedSpiner = false;
    boolean bollSpinner = false;

    TableLayout table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto);
        setTitle("Автохимия");

        strVoda = null;

        table = (TableLayout) findViewById(R.id.table);

        llmain = (LinearLayout) findViewById(R.id.llmain);

        etVoda = (EditText) findViewById(R.id.etVoda);
        tvDh = (TextView) findViewById(R.id.tvDh);
        tvVoda = (TextView) findViewById(R.id.tvVoda);
        tvVodaStr = (TextView) findViewById(R.id.tvVodaStr);

        rbDjeskost = (RadioButton) findViewById(R.id.rbDjeskost);
        rbMgL = (RadioButton) findViewById(R.id.rbMgL);
        rbDh = (RadioButton) findViewById(R.id.rbDh);
        btnRaschet = (Button) findViewById(R.id.btnRaschet);

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
                        //tvVoda.setText(etVoda.getText().toString());
                        ReturnVoda(strJoskost);
                    } else if (rbDh.isChecked() == true) {
                        voda = voda * 0.36;
                        strJoskost = String.valueOf(roundUp(voda, 2));
                        //tvVoda.setText(String.valueOf(roundUp(voda, 2)));
                        ReturnVoda(strJoskost);
                    } else if (rbMgL.isChecked() == true) {
                        strJoskost = etVoda.getText().toString();
                        //tvVoda.setText(etVoda.getText().toString());
                        ReturnVoda(strJoskost);
                    }
                    strVyborJVody = "выбранная жесткость воды " + strJoskost + " °Ж";
                    tvVoda.setText(strVyborJVody);
                    selectedSpiner = true;
                } else {
                    tvVoda.setText("выбранная жесткость воды °Ж");
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

        final Spinner spinner = (Spinner) findViewById(R.id.spinner8);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner, data);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                if (strJoskost == null)
                    return;
                double vod = Double.parseDouble(strJoskost);
                if (pos == 0) {
                    selectedSpiner = false;
                    return;
                } else if (pos == 1) {
                    spin = "peno50";
                    if (0 <= vod && vod < 3.5) {
                        arr = peno50ligth;
                        sredstvo = "peno50ligth";
                    } else if (3.5 <= vod && vod < 7) {
                        arr = peno50default;
                        sredstvo = "peno50default";
                    } else if (7 <= vod) {
                        arr = peno50hard;
                        sredstvo = "peno50hard";
                    }
                } else if (pos == 2) {
                    spin = "penokomplekt";
                    if (0 <= vod && vod < 3.5) {
                        arr = penokomplektligth;
                        sredstvo = "penokomplektligth";
                    } else if (3.5 <= vod && vod < 7) {
                        arr = penokomplektdefault;
                        sredstvo = "penokomplektdefault";
                    } else if (7 <= vod) {
                        arr = penokomplekthard;
                        sredstvo = "penokomplekthard";
                    }
                } else if (pos == 3) {
                    spin = "dozatron";
                    if (0 <= vod && vod < 3.5) {
                        arr = dozatronligth;
                        sredstvo = "dozatronligth";
                    } else if (3.5 <= vod && vod < 7) {
                        arr = dozatrondefault;
                        sredstvo = "dozatrondefault";
                    } else if (7 <= vod) {
                        arr = dozatronhard;
                        sredstvo = "dozatronhard";
                    }
                }
                bollSpinner = true;
                selectedSpiner = true;
            }
        });
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
                    //tvDh.setText("°Ж");
                    if (etVoda.getText().length() != 0) {
                        strJoskost = etVoda.getText().toString();
                        //tvVoda.setText(etVoda.getText().toString());
                        ReturnVoda(strJoskost);
                    }
                }
                break;
            case R.id.rbDh:
                if (checked) {
                    //tvDh.setText("°DH");
                    if (etVoda.getText().length() != 0) {
                        voda = voda * 0.36;
                        strJoskost = String.valueOf(roundUp(voda, 2));
                        //tvVoda.setText(String.valueOf(roundUp(voda, 2)));
                        ReturnVoda(strJoskost);
                    }
                }
                break;
            case R.id.rbMgL:
                if (checked) {
                    //tvDh.setText("мг - экв/л");
                    if (etVoda.getText().length() != 0) {
                        strJoskost = etVoda.getText().toString();
                        //tvVoda.setText(etVoda.getText().toString());
                        ReturnVoda(strJoskost);
                    }
                }
                break;
        }
        strVyborJVody = "выбранная жесткость воды " + strJoskost + " °Ж";
        tvVoda.setText(strVyborJVody);
        selectedSpiner = true;
    }

    private void ReturnVoda(String tvVoda) {
        double vod = Double.parseDouble(tvVoda);
        if (0 <= vod && vod < 3.5) {
            tvVodaStr.setText("вода мягкая");
            strVoda = "ligth";
            if (spin == "peno50") {
                arr = peno50ligth;
            } else if (spin == "penokomplekt") {
                arr = penokomplektligth;
            } else if (spin == "dozatron") {
                arr = dozatronligth;
            }
        } else if (3.5 <= vod && vod < 7) {
            tvVodaStr.setText("вода умеренной жесткости");
            strVoda = "default";
            if (spin == "peno50") {
                arr = peno50default;
            } else if (spin == "penokomplekt") {
                arr = penokomplektdefault;
            } else if (spin == "dozatron") {
                arr = dozatrondefault;
            }
        } else if (7 <= vod) {
            tvVodaStr.setText("вода жесткая");
            strVoda = "hard";
            if (spin == "peno50") {
                arr = peno50hard;
            } else if (spin == "penokomplekt") {
                arr = penokomplekthard;
            } else if (spin == "dozatron") {
                arr = dozatronhard;
            }
        } else {
            tvVodaStr.setText("");
        }
    }

    public BigDecimal roundUp(double value, int digits) {
        return new BigDecimal("" + value).setScale(digits, BigDecimal.ROUND_HALF_UP);
    }

    public void onClick(View view) {

        if (!selectedSpiner || etVoda.getText().length() == 0 || !bollSpinner) {
            Toast.makeText(getBaseContext(), "Заполните пожалуйста все данные", Toast.LENGTH_SHORT).show();
            return;
        }

        table.setVisibility(View.VISIBLE);

        TableLayout tableLayout = (TableLayout) findViewById(R.id.table);
        tableLayout.removeAllViewsInLayout();

        //ReturnArr();

        int count = arr.length;
        for (int i = 0; count > i; i++) {
            if (spin == "dozatron")
                addRow2(arr[i][0], arr[i][1]);
            else
                addRow(arr[i][0], arr[i][1], arr[i][2]);
        }
        //tvVoda.setText(String.valueOf(roundUp(voda, 2)));
        ReturnVoda(strJoskost);
        int gray = Color.parseColor("#7B7979");
        btnRaschet.setBackgroundColor(gray);
    }

    private void ReturnArr() {

        if (sredstvo == "peno50ligth") {
            arr = peno50ligth;
        } else if (sredstvo == "peno50default") {
            arr = peno50default;
        } else if (sredstvo == "peno50hard") {
            arr = peno50hard;
        } else if (sredstvo == "penokomplektligth") {
            arr = penokomplektligth;
        } else if (sredstvo == "penokomplektdefault") {
            arr = penokomplektdefault;
        } else if (sredstvo == "penokomplekthard") {
            arr = penokomplekthard;
        } else if (sredstvo == "dozatronligth") {
            arr = dozatronligth;
        } else if (sredstvo == "dozatrondefault") {
            arr = dozatrondefault;
        } else if (sredstvo == "dozatronhard") {
            arr = dozatronhard;
        }
    }

    private void addRow(String a, String b, String c) {

        TableLayout tableLayout = (TableLayout) findViewById(R.id.table);

        LayoutInflater inflater = LayoutInflater.from(this);
        TableRow tr = (TableRow) inflater.inflate(R.layout.table_row, null);
        TextView tv = (TextView) tr.findViewById(R.id.col1);
        tv.setText(a);
        tv = (TextView) tr.findViewById(R.id.col2);
        tv.setText(b);
        tv = (TextView) tr.findViewById(R.id.col3);
        tv.setText(c);

        tableLayout.addView(tr);
    }

    private void addRow2(String a, String b) {

        TableLayout tableLayout = (TableLayout) findViewById(R.id.table);
        LayoutInflater inflater = LayoutInflater.from(this);
        TableRow tr = (TableRow) inflater.inflate(R.layout.table_row2, null);
        TextView tv = (TextView) tr.findViewById(R.id.col1);
        tv.setText(a);
        tv = (TextView) tr.findViewById(R.id.col2);
        tv.setText(b);

        tableLayout.addView(tr);
    }

    public void onClickSravnenie(View view) {
        if (strVoda == null) {
            Toast.makeText(getBaseContext(), "Расчеты не выполнены", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(ActivityAuto.this, ActivityAutoSravnenie.class);
        intent.putExtra("dh", strVoda);
        intent.putExtra("sredstvo", sredstvo);
        startActivity(intent);
    }
}
