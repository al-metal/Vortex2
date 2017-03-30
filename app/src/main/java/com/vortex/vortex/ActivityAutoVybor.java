package com.vortex.vortex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ActivityAutoVybor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_vybor);
    }

    public void onClickAuto(View view) {
        Intent intent = new Intent(ActivityAutoVybor.this, ActivityAuto.class);
        startActivity(intent);
    }

    public void onClickSuperK(View view) {
        Intent intent = new Intent(ActivityAutoVybor.this, ActivityAutoSuperKoncentrat.class);
        startActivity(intent);
    }
}
