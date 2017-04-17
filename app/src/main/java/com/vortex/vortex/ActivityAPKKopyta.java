package com.vortex.vortex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.vortex.vortex.APK.ActivityAPKDezinfekciya;
import com.vortex.vortex.APK.ActivityAPKDezinfekciyaTumanIceAnimal;

public class ActivityAPKKopyta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apkkopyta);
        setTitle("Гигиена копыт");
    }

    public void onClickOzdorovlenie(View view) {
        Intent intent = new Intent(ActivityAPKKopyta.this, ActivityAPKKopytaOzdorovlenie.class);
        startActivity(intent);
    }
}
