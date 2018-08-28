package com.vortex.vortex;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class ActivityAPKKopyta2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apkkopyta2);

        setTitle("Гигиена копыт");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void onClickIcon(View view) {

        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }

    public void onClickOzdorovlenie(View view) {
        Intent intent = new Intent(ActivityAPKKopyta2.this, ActivityAPKKopytaOzdorovlenie2.class);
        startActivity(intent);
    }

    public void onClickProfilaktika(View view) {
        Intent intent = new Intent(ActivityAPKKopyta2.this, ActivityAPKKopytaProfilaktika2.class);
        startActivity(intent);
    }

    public void onClickMedKuporos(View view) {
        Intent intent = new Intent(ActivityAPKKopyta2.this, ActivityAPKKopytaMedKuporos2.class);
        startActivity(intent);
    }

    public void onClickDesitabProfilaktika(View view) {
        Intent intent = new Intent(ActivityAPKKopyta2.this, ActivityAPKKopytaDesitabProfilakt2.class);
        startActivity(intent);
    }

    public void onClickDesitabProfilaktikaUsil(View view) {
        Intent intent = new Intent(ActivityAPKKopyta2.this, ActivityAPKKopytaDesitabProfilaktUsil2.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Intent intent = ClickLeftMenu.getIntent(ActivityAPKKopyta2.this, item);
        startActivity(intent);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
