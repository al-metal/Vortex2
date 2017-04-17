package com.vortex.vortex;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.vortex.vortex.APK.ApkActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Калькулятор Vortex");
    }


    public void onClick(View view) {

        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(intent);
    }

    public void onClickPisheProm(View view) {
        Intent intent = new Intent(MainActivity.this, ActivityPisheProm2.class);
        startActivity(intent);
    }

    public void onClickAuto(View view) {
        Intent intent = new Intent(MainActivity.this, ActivityAutoVybor.class);
        startActivity(intent);
    }

    public void onClickKlining(View view) {
        Toast.makeText(getBaseContext(), "Данный раздел находится в разработке", Toast.LENGTH_SHORT).show();
    }
}
