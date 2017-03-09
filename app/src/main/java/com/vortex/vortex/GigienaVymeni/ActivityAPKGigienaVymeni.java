package com.vortex.vortex.GigienaVymeni;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.vortex.vortex.R;

public class ActivityAPKGigienaVymeni extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apkgigiena_vymeni);
        setTitle("Гигиена вымени");
    }

    public void onClickAPKGigienaDoDoenia(View view) {
        Intent intent = new Intent(ActivityAPKGigienaVymeni.this, ActivityAPKGigienaVymeniDoDoenia.class);
        startActivity(intent);
    }

    public void onClickAPKGigienaPosleDoenia(View view) {
        Intent intent = new Intent(ActivityAPKGigienaVymeni.this, ActivityAPKGigienaVymeniPosleDoenia.class);
        startActivity(intent);
    }

    public void onClickAPKGigienaExpressMethod(View view) {
        Intent intent = new Intent(ActivityAPKGigienaVymeni.this, ActivityAPKGigienaVymeniExpressMethod.class);
        startActivity(intent);
    }
}
