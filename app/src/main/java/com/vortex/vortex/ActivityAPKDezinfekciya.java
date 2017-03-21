package com.vortex.vortex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.vortex.vortex.APK.ActivityApkMoysredstvaMain;
import com.vortex.vortex.APK.ApkActivity;

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
}
