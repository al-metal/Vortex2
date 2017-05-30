package com.vortex.vortex;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class activity_klining extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_klining);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Сфера деятельности");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
            Intent intent = new Intent(activity_klining.this, ApkActivity2.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(activity_klining.this, ActivityPisheProm2.class);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(activity_klining.this, ActivityAutoVybor2.class);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {
            Toast.makeText(getBaseContext(), "Данный раздел находится в разработке", Toast.LENGTH_SHORT).show();
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

    public void onClick(View view) {
            Intent intent = new Intent(activity_klining.this, activity_klining_poverhnost.class);
            startActivity(intent);
    }

    public void onClick1(View view) {
        Toast.makeText(getBaseContext(), "Расчеты не выполнены", Toast.LENGTH_SHORT).show();
    }

    public void onClickKuhnya(View view) {
        id = 1;
        SetIdKlining(id);
    }

    public void onClickSanusel(View view) {
        id = 2;
        SetIdKlining(id);
    }

    public void onClickOffice(View view) {
        id = 3;
        SetIdKlining(id);
    }

    public void onClickObshKlining(View view) {
        id = 4;
        SetIdKlining(id);
    }

    public void onClickRemont(View view) {
        id = 5;
        SetIdKlining(id);
    }

    public void onClickPromKlining(View view) {
        id = 6;
        SetIdKlining(id);
    }

    private void SetIdKlining(int id) {
        Intent intent = new Intent(activity_klining.this, activity_klining_poverhnost.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }
}
