package com.vortex.vortex;

import android.content.Intent;
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

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String APP_PREFERENCE_NAME = "News_title";
    public static final String APP_PREFERENCE = "News";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Калькулятор Vortex");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void onClick(View view) {

        Intent intent = new Intent(Main2Activity.this, ApkActivity2.class);
        startActivity(intent);
    }

    public void onClickPisheProm(View view) {
        Intent intent = new Intent(Main2Activity.this, ActivityPisheProm2.class);
        startActivity(intent);
    }

    public void onClickAuto(View view) {
        Intent intent = new Intent(Main2Activity.this, ActivityAutoVybor2.class);
        startActivity(intent);
    }

    public void onClickKlining(View view) {
        Intent intent = new Intent(Main2Activity.this, activity_klining.class);
        startActivity(intent);
        //Toast.makeText(getBaseContext(), "Данный раздел находится в разработке", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        return;
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
        Intent intent = ClickLeftMenu.getIntent(Main2Activity.this, item);
        startActivity(intent);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
