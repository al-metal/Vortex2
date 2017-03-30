package com.vortex.vortex;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.vortex.vortex.APK.ApkActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Калькулятор Vortex");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f15b40")));
    }

    public void onClick(View view) {

        Intent intent = new Intent(MainActivity.this, ApkActivity.class);
        startActivity(intent);
    }

    public void onClickPisheProm(View view) {
        Intent intent = new Intent(MainActivity.this, ActivityPisheProm.class);
        startActivity(intent);
    }

    public void onClickAuto(View view) {
        Intent intent = new Intent(MainActivity.this, ActivityAutoVybor.class);
        startActivity(intent);
    }

    public void onClickKlining(View view) {
        Toast.makeText(getBaseContext(), "Данный раздел находиться в разработке", Toast.LENGTH_SHORT).show();
    }
}
