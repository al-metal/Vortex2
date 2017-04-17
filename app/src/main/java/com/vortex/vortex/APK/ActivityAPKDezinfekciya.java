package com.vortex.vortex.APK;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.vortex.vortex.ActivityAPKDezinfekciyaTuman;
import com.vortex.vortex.R;

public class ActivityAPKDezinfekciya extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apkdezinfekciya);
        setTitle("Дезинфекция");
    }

    public void onClickProfilakt(View view) {
        Intent intent = new Intent(ActivityAPKDezinfekciya.this, ActivityAPKDezinfekciyaProfilaktikaForbicid.class);
        startActivity(intent);
    }

    public void onCLickVynujden(View view) {
        Intent intent = new Intent(ActivityAPKDezinfekciya.this, ActivityAPKDezinfekciyaVynujdennayaForbicid.class);
        startActivity(intent);
    }

    public void onClickTuman(View view) {
        Intent intent = new Intent(ActivityAPKDezinfekciya.this, ActivityAPKDezinfekciyaTuman.class);
        startActivity(intent);
    }
}
