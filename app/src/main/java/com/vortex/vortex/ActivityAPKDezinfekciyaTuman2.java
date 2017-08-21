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

public class ActivityAPKDezinfekciyaTuman2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apkdezinfekciya_tuman2);

        setTitle("Использование тумана");

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

    public void onClickTumanFire(View view) {
        Intent intent = new Intent(ActivityAPKDezinfekciyaTuman2.this, ActivityAPKDezinfekciyaTumanFire2.class);
        startActivity(intent);
    }

    public void onClickTumanIce(View view) {
        Intent intent = new Intent(ActivityAPKDezinfekciyaTuman2.this, ActivityAPKDezinfekciyaTumanIce2.class);
        startActivity(intent);
    }

    public void onClickTumanIceAnimal(View view) {
        Intent intent = new Intent(ActivityAPKDezinfekciyaTuman2.this, ActivityAPKDezinfekciyaTumanIceAnimal2.class);
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
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent intent = new Intent(ActivityAPKDezinfekciyaTuman2.this, ApkActivity2.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(ActivityAPKDezinfekciyaTuman2.this, ActivityPisheProm2.class);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(ActivityAPKDezinfekciyaTuman2.this, ActivityAutoVybor2.class);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent(ActivityAPKDezinfekciyaTuman2.this, activity_klining.class);
            startActivity(intent);
        }else if (id == R.id.nav_spravochnik){
            Intent intent = new Intent(ActivityAPKDezinfekciyaTuman2.this, activity_spravka.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onClickWebSite(View view) {
        Uri uri = Uri.parse("http://www.pk-vortex.ru"); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
