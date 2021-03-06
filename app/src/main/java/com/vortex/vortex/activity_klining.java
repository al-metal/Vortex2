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
import android.widget.Toast;

public class activity_klining extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_klining);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Сфера деятельности");

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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
        Intent intent = ClickLeftMenu.getIntent(activity_klining.this, item);
        startActivity(intent);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
        SetIdKlining(id, "Чистая кухня");
    }

    public void onClickSanusel(View view) {
        id = 2;
        SetIdKlining(id, "Чистый санузел");
    }

    public void onClickOffice(View view) {
        id = 3;
        SetIdKlining(id, "Офисные и жилые помещения");
    }

    public void onClickObshKlining(View view) {
        id = 4;
        SetIdKlining(id, "Общий клининг");
    }

    public void onClickRemont(View view) {
        id = 5;
        SetIdKlining(id, "Ремонт и послестрой");
    }

    public void onClickPromKlining(View view) {
        id = 6;
        SetIdKlining(id, "Промышленный клининг");
    }

    private void SetIdKlining(int id, String title) {
        Intent intent = new Intent(activity_klining.this, activity_klining_poverhnost.class);
        intent.putExtra("id", id);
        intent.putExtra("title", title);
        startActivity(intent);
    }

    public void onClickHotel(View view) {
        id = 7;
        SetIdKlining(id, "Номерной фонд");
    }

    public void onClickIcon(View view) {

        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }

    public void onClickLaundry(View view) {
        id = 8;
        SetIdKlining(id, "Профессиональная стирка");
    }
}
