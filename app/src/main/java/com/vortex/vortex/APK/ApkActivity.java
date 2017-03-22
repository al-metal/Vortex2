package com.vortex.vortex.APK;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.vortex.vortex.APK.GigienaVymeni.ActivityAPKGigienaVymeni;
import com.vortex.vortex.R;

public class ApkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apk);
        setTitle("Направление АПК");
    }

    public void onClickMoySredstva(View view) {
        Intent intent = new Intent(ApkActivity.this, ActivityApkMoysredstvaMain.class);
        startActivity(intent);
    }

    public void onClickGigienaVymeni(View view) {
        Intent intent = new Intent(ApkActivity.this, ActivityAPKGigienaVymeni.class);
        startActivity(intent);
    }

    public void onClickDezinfekciya(View view) {
        Intent intent = new Intent(ApkActivity.this, ActivityAPKDezinfekciya.class);
        startActivity(intent);
    }
}
