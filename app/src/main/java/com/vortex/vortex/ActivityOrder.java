package com.vortex.vortex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class ActivityOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        setTitle("Оформление заказа");

        String[] data = {"ВЫБРАТЬ СРЕДСТВО", "BIOTEC", "BIOTEC C", "BIOTEC Super", "BIOTEC М", "KSILAN", "KSILAN K", "KSILAN Super", "KSILAN М", "Tank CB 46",
                "Tank CA27", "Tank FA18", "Tank FB17", "TANK FBD 0803/1", "TANK FB 36", "TANK FBD 0402/1", "TANK LBD 0107/1", "TANK LBD 1002/2",
                "TANK FBD 0902/2", "TANKCAD 1415/3", "TANK FN"};
        String[] data2 = {"ВЫБРАТЬ СРЕДСТВО", "BIOTEC", "BIOTEC C", "BIOTEC Super", "BIOTEC М", "KSILAN", "KSILAN K", "KSILAN Super", "KSILAN М", "Tank CB 46",
                "Tank CA27", "Tank FA18", "Tank FB17", "TANK FBD 0803/1", "TANK FB 36", "TANK FBD 0402/1", "TANK LBD 0107/1", "TANK LBD 1002/2",
                "TANK FBD 0902/2", "TANKCAD 1415/3", "TANK FN"};

        Spinner spinner = (Spinner) findViewById(R.id.spin1);
        final Spinner spinner2 = (Spinner) findViewById(R.id.spin2);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner, data);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item_means);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.spinner, data2);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item_means);

        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter2);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                spinner2.setVisibility(view.VISIBLE);

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

            }
        });
    }

    public void onClickIcon(View view) {

        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }

    public void onClickOrderBuy(View view) {
        Toast.makeText(getBaseContext(), "Ваша заявка отправлена,  в ближайшее время с вами свяжется наш менеджер", Toast.LENGTH_SHORT).show();
    }
}
