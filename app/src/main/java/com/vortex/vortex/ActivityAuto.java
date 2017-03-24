package com.vortex.vortex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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

    String[] data = {"Пеногенератор 50 литров", "Пенокомплект", "Дозатрон"};

    String[][] arr = {};

    //region Array
    String[][] peno50ligth = {{"Продукт","Разбавление", "Кол-во шампуня, мл" }, {"Unior","1:60", "850" },
            {"Tiro, Tiro Tone","1:80", "625" },
            {"Master, Master Tone","1:100", "500" },
            {"Profy","1:120", "425" },
            {"Dozex","1:100", "500" },
            {"Ace","1:140", "350" },
            {"Magnat","1:110", "450" },
            {"Delicate","1:80", "625" },
            {"Solo","1:70", "725" },
            {"DIY","1:120", "425" }};

    String[][] peno50default = {{"Продукт","Разбавление", "Кол-во шампуня, мл" },
            {"Unior","1:40", "1200" },
            {"Tiro, Tiro Tone","1:60", "800" },
            {"Novice","1:80", "600" },
            {"Master, Master Tone","1:80", "600" },
            {"Tutor ","1:100", "500" },
            {"Profy","1:100", "500" },
            {"Dozex","1:90", "550" },
            {"Senza","1:120", "400" },
            {"Ace","1:120", "400" },
            {"Magnat","1:100", "500" },
            {"Delicate","1:60", "800" },
            {"Solo","1:55", "900" },
            {"DIY","1:100", "500" }};

    String[][] peno50hard = {{"Продукт","Разбавление", "Кол-во шампуня, мл" },
            {"Novice","1:50", "1000" },
            {"Tutor ","1:60", "800" },
            {"Profy","1:60", "800" },
            {"Senza","1:80", "600" },
            {"Ace","1:80", "600" },
            {"Magnat","1:60", "800" },
            {"DIY","1:60", "800" }};

    String[][] penokomplektligth = {{"Продукт","Разбавление", "Кол-во шампуня, мл" },
            {"Unior","1:4", "200" },
            {"Tiro, Tiro Tone","1:6", "150" },
            {"Master, Master Tone","1:7", "110" },
            {"Profy","1:10", "90" },
            {"Dozex","1:9", "100" },
            {"Ace","1:12", "80" },
            {"Magnat","1:9", "100" },
            {"Delicate","1:6", "150" },
            {"Solo","1:5", "170" },
            {"DIY","1:10", "90" }};

    String[][] penokomplektdefault = {{"Продукт","Разбавление", "Кол-во шампуня, мл" },
            {"Unior","1:2", "300" },
            {"Tiro, Tiro Tone","1:4", "200" },
            {"Novice","1:6", "145" },
            {"Master, Master Tone","1:6", "145" },
            {"Tutor ","1:8", "110" },
            {"Profy","1:8", "110" },
            {"Dozex","1:7", "125" },
            {"Senza","1:10", "90" },
            {"Ace","1:10", "90" },
            {"Magnat","1:8", "110" },
            {"Delicate","1:4", "200" },
            {"Solo","1:4", "200" },
            {"DIY","1:8", "110" }};

    String[][] penokomplekthard = {{"Продукт","Разбавление", "Кол-во шампуня, мл" },
            {"Novice","1:4", "200" },
            {"Tutor ","1:6", "145" },
            {"Profy","1:6", "145" },
            {"Senza","1:7", "125" },
            {"Ace","1:7", "125" },
            {"Magnat","1:6", "145" },
            {"DIY","1:3", "250" }};

    String[][] dozatronligth = {{"Продукт","Показатель концентрации %" },
            {"Unior", "2" },
            {"Tiro, Tiro Tone", "1.5" },
            {"Master, Master Tone", "1" },
            {"Profy", "1" },
            {"Dozex", "1" },
            {"Ace", "0.5" },
            {"Delicate", "1.5" },
            {"Solo", "1.5" },
            {"DIY", "1" }};

    String[][] dozatrondefault = {{"Продукт","Показатель концентрации %"},
    {"Unior", "3" },
            {"Tiro, Tiro Tone", "2" },
            {"Novice", "1.5" },
            {"Master, Master Tone", "1.5" },
            {"Tutor ", "1" },
            {"Profy", "1" },
            {"Dozex", "1" },
            {"Senza", "1" },
            {"Ace", "1" },
            {"Delicate", "2" },
            {"Solo", "2" },
            {"DIY", "1" }};

    String[][] dozatronhard = {{"Продукт","Показатель концентрации %"},
            {"Novice", "2" },
            {"Tutor ", "2" },
            {"Profy", "2" },
            {"Senza", "1.5" },
            {"Ace", "1.5" },
            {"DIY", "2" }};
//endregion

    private EditText etVoda;
    private TextView tvDh;
    private TextView tvVoda;
    private TextView tvVodaStr;

    private RadioButton rbDjeskost;
    private RadioButton rbDh;
    private RadioButton rbMgL;

    private double voda;
    String spin;
    String sredstvo;
    String strVoda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto);

        strVoda = null;

        etVoda = (EditText) findViewById(R.id.etVoda);
        tvDh = (TextView) findViewById(R.id.tvDh);
        tvVoda = (TextView) findViewById(R.id.tvVoda);
        tvVodaStr = (TextView) findViewById(R.id.tvVodaStr);

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

        final Spinner spinner = (Spinner) findViewById(R.id.spinner8);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        // заголовок
        spinner.setPrompt("Выберите устройство");

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                double vod = Double.parseDouble(tvVoda.getText().toString());
                if (pos == 0)
                {
                    spin = "peno50";
                    if (0 <= vod && vod < 3.5)
                    {
                        arr = peno50ligth;
                        sredstvo = "peno50ligth";
                    }
                    else if (3.5 <= vod && vod < 7)
                    {
                        arr = peno50default;
                        sredstvo = "peno50default";
                    }
                    else if (7 <= vod)
                    {
                        arr = peno50hard;
                        sredstvo = "peno50hard";
                    }
                }
                else if (pos == 1)
                {
                    spin = "penokomplekt";
                    if (0 <= vod && vod < 3.5)
                    {
                        arr = penokomplektligth;
                        sredstvo = "penokomplektligth";
                    }
                    else if (3.5 <= vod && vod < 7)
                    {
                        arr = penokomplektdefault;
                        sredstvo = "penokomplektdefault";
                    }
                    else if (7 <= vod)
                    {
                        arr = penokomplekthard;
                        sredstvo = "penokomplekthard";
                    }
                }
                else if (pos == 2)
                {
                    spin = "dozatron";
                    if (0 <= vod && vod < 3.5)
                    {
                        arr = dozatronligth;
                        sredstvo = "dozatronligth";
                    }
                    else if (3.5 <= vod && vod < 7)
                    {
                        arr = dozatrondefault;
                        sredstvo = "dozatrondefault";
                    }
                    else if (7 <= vod)
                    {
                        arr = dozatronhard;
                        sredstvo = "dozatronhard";
                    }
                }

            }
        });

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

    private void ReturnVoda(TextView tvVoda) {
        double vod = Double.parseDouble(tvVoda.getText().toString());
        if (0 <= vod && vod < 3.5)
        {
            tvVodaStr.setText("мягкая");
            strVoda= "ligth";
        }
        else if (3.5 <= vod && vod < 7)
        {
            tvVodaStr.setText("умеренной жесткости");
            strVoda= "default";
        }
        else if (7 <= vod)
        {
            tvVodaStr.setText("жесткая");
            strVoda= "hard";
        }
        else
        {
            tvVodaStr.setText("");
        }
    }

    public BigDecimal roundUp(double value, int digits){
        return new BigDecimal(""+value).setScale(digits, BigDecimal.ROUND_HALF_UP);
    }

    public void onClick(View view) {
        TableLayout tableLayout = (TableLayout) findViewById(R.id.table);
        tableLayout.removeAllViews();
        int count = arr.length;
        for(int i = 0; count > i; i++){
            if(spin == "dozatron")
            addRow2(arr[i][0],arr[i][1]);
            else
            addRow(arr[i][0],arr[i][1],arr[i][2]);
        }
        tvVoda.setText(String.valueOf(roundUp(voda, 2)));
        ReturnVoda(tvVoda);
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
        if(strVoda == null){
            Toast.makeText(getBaseContext(), "Расчеты не выполнены", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(ActivityAuto.this, ActivityAutoSravnenie.class);
        intent.putExtra("dh", strVoda);
        intent.putExtra("sredstvo", sredstvo);
        startActivity(intent);
    }
}
