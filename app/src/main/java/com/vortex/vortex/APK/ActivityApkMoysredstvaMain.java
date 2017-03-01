package com.vortex.vortex.APK;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.vortex.vortex.R;

public class ActivityApkMoysredstvaMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apk_moysredstva_main);
        setTitle("Выберите направление");
    }
    public void onClickRaschetStoimostiRabRastvor(View view){
        Intent intent = new Intent(ActivityApkMoysredstvaMain.this, ApkMoySredstvaActivity.class);
        startActivity(intent);
    }

    public void onClickRaschetMoySredstvForHoz(View view) {
        Intent intent = new Intent(ActivityApkMoysredstvaMain.this, ActivityApkMoySredstvaForHoz.class);
        startActivity(intent);
    }
}
