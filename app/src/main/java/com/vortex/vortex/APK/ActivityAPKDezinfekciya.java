package com.vortex.vortex.APK;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.vortex.vortex.R;

public class ActivityAPKDezinfekciya extends AppCompatActivity {
Button btnTumanFire;
Button btnTumanIce;
Button btnTumanIceAnimal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apkdezinfekciya);
        setTitle("Дезинфекция");

        btnTumanFire = (Button) findViewById(R.id.btnTumanFire);
        btnTumanIce = (Button) findViewById(R.id.btnTumanIce);
        btnTumanIceAnimal = (Button) findViewById(R.id.btnTumanIceAnimal);

        btnTumanFire.setVisibility(View.INVISIBLE);
        btnTumanIce.setVisibility(View.INVISIBLE);
        btnTumanIceAnimal.setVisibility(View.INVISIBLE);
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
        btnTumanFire.setVisibility(View.VISIBLE);
        btnTumanIce.setVisibility(View.VISIBLE);
        btnTumanIceAnimal.setVisibility(View.VISIBLE);

    }

    public void onClickTumanFire(View view) {
        Intent intent = new Intent(ActivityAPKDezinfekciya.this, ActivityAPKDezinfekciyaTumanFire.class);
        startActivity(intent);
    }

    public void onClickTumanIce(View view) {
        Intent intent = new Intent(ActivityAPKDezinfekciya.this, ActivityAPKDezinfekciyaTumanIce.class);
        startActivity(intent);
    }

    public void onClickTumanIceAnimal(View view) {
        Intent intent = new Intent(ActivityAPKDezinfekciya.this, ActivityAPKDezinfekciyaTumanIceAnimal.class);
        startActivity(intent);
    }
}
