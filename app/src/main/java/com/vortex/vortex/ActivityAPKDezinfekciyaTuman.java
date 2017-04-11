package com.vortex.vortex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.vortex.vortex.APK.ActivityAPKDezinfekciya;
import com.vortex.vortex.APK.ActivityAPKDezinfekciyaTumanFire;
import com.vortex.vortex.APK.ActivityAPKDezinfekciyaTumanIce;
import com.vortex.vortex.APK.ActivityAPKDezinfekciyaTumanIceAnimal;

public class ActivityAPKDezinfekciyaTuman extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apkdezinfekciya_tuman);
        setTitle("Использование тумана");
    }

    public void onClickTumanFire(View view) {
        Intent intent = new Intent(ActivityAPKDezinfekciyaTuman.this, ActivityAPKDezinfekciyaTumanFire.class);
        startActivity(intent);
    }

    public void onClickTumanIce(View view) {
        Intent intent = new Intent(ActivityAPKDezinfekciyaTuman.this, ActivityAPKDezinfekciyaTumanIce.class);
        startActivity(intent);
    }

    public void onClickTumanIceAnimal(View view) {
        Intent intent = new Intent(ActivityAPKDezinfekciyaTuman.this, ActivityAPKDezinfekciyaTumanIceAnimal.class);
        startActivity(intent);
    }
}
