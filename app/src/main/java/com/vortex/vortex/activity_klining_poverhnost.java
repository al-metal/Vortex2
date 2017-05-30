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
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class activity_klining_poverhnost extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    int id;

    TableLayout tl;
    LinearLayout llmain;
    int length;

    String[] kuhnya = {"посуда, противни, сковороды", "посудомоечная машина", "холодильник", "вытяжка", "плита, гриль, духовка", "микроволновая печь", "столовые приборы",
            "моечная ванна", "общая дезинфекция", "рабочие столы и поверхности", "мойка и чистка", "трубы", "устрание запахов", "гигиена рук"};
    String[] sanusel = {"унитаз, биде, писсуар, раковина", "ванна, душевая кабина", "окна, стекла, зеркала", "мойка и чистка", "трубы", "устранение запахов", "гигиена рук"};
    String[] office = {"оргтехника", "мягкая мебель", "деревянная мебель", "окна, стекла, зеркала", "мойка и чистка", "общая дезинфекция"};
    String[] obshiyKlining = {"оргтехника", "мягкая мебель", "деревянная мебель", "окна, стекла, зеркала", "мойка и чистка", "общая дезинфекция", "гигиена рук",
            "устранение запахов"};
    String[] remont = {"гигиена рук", "окна, стекла, зеркала", "мойка и чистка"};
    String[] promKlining = {"Промышленный клининг"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_klining_poverhnost);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Поверхности");

        id = getIntent().getExtras().getInt("id");
        tl = (TableLayout) findViewById(R.id.tlMain);
        llmain = (LinearLayout) findViewById(R.id.llmain);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TableLayout tl = new TableLayout(this);
        llmain.addView(tl);

        AddTextViews(id);
    }

    private void AddTextViews(int id) {
        if (id == 1) {
            CreateViews(kuhnya);
        } else if (id == 2) {
            CreateViews(sanusel);
        }else if (id == 3) {
            CreateViews(office);
        }else if (id == 4) {
            CreateViews(obshiyKlining);
        }else if (id == 5) {
            CreateViews(remont);
        }else if (id == 6) {
            CreateViews(promKlining);
        }
    }

    private void CreateViews(String[] kuhnya) {
        length = kuhnya.length;
        for (int i = 0; length > i; i++) {
            TextView tv = new TextView(this);
            tv.setText(kuhnya[i].toString());
            tv.setId(i);
            tv.setOnClickListener(oclBtnCancel);
            llmain.addView(tv);
        }
    }

    OnClickListener oclBtnCancel = new OnClickListener() {
        @Override
        public void onClick(View v) {
            String ssss = String.valueOf(v.getId());
            Intent intent = new Intent(activity_klining_poverhnost.this, activity_klining_problema.class);
            intent.putExtra("id", id);
            intent.putExtra("id2", v.getId());
            startActivity(intent);
        }
    };

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
            Intent intent = new Intent(activity_klining_poverhnost.this, ApkActivity2.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(activity_klining_poverhnost.this, ActivityPisheProm2.class);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(activity_klining_poverhnost.this, ActivityAutoVybor2.class);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {
            Toast.makeText(getBaseContext(), "Данный раздел находится в разработке", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onClick1(View view) {
        Intent intent = new Intent(activity_klining_poverhnost.this, activity_klining_problema.class);
        startActivity(intent);
    }
}
