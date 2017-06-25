package com.vortex.vortex;

import android.content.Intent;
import android.graphics.Color;
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
    String title;
    String titleProblem;
    String[] array;

    String[] kuhnya = {"ПОСУДА, ПРОТИВНИ, СКОВОРДЫ", "ПОСУДОМОЕЧНАЯ МАШИНА", "ХОЛОДИЛЬНИК", "ВЫТЯЖКА", "ПЛИТА, ГРИЛЬ, ДУХОВКА", "МИКРОВОЛНОВАЯ ПЕЧЬ", "СТОЛОВЫЕ ПРИБОРЫ",
            "МОЕЧНАЯ ВАННАЯ", "ОБЩАЯ ДЕЗИНФЕКЦИЯ", "РАБОЧИЕ СТОЛЫ И ПОВЕРХНОСТИ", "МОЙКА И ЧИСТКА", "ТРУБЫ", "УСТРАНЕНИЕ ЗАПАХОВ", "ГИГИЕНА РУК"};
    String[] sanusel = {"УНИТАЗ, БИДЕ, ПИССУАР, РАКОВИНА", "ВАННА, ДУШЕВАЯ КАБИНА", "ОКНА, СТЕКЛА, ЗЕРКАЛА", "МОЙКА И ЧИСТКА", "ТРУБЫ", "УСТРАНЕНИЕ ЗАПАХОВ", "ГИГИЕНА РУК"};
    String[] office = {"ОРГТЕХНИКА", "МЯГКАЯ МЕБЕЛЬ", "ДЕРЕВЯННАЯ МЕБЕЛЬ", "ОКНА, СТЕКЛА, ЗЕРКАЛА", "МОЙКА И ЧИСТКА", "ОБЩАЯ ДЕЗИНФЕКЦИЯ"};
    String[] obshiyKlining = {"ОРГТЕХНИКА", "МЯГКАЯ МЕБЕЛЬ", "ДЕРЕВЯННАЯ МЕБЕЛЬ", "ОКНА, СТЕКЛА, ЗЕРКАЛА", "МОЙКА И ЧИСТКА", "ОБЩАЯ ДЕЗИНФЕКЦИЯ", "ГИГИЕНА РУК",
            "УСТРАНЕНИЕ ЗАПАХОВ"};
    String[] remont = {"ГИГИЕНА РУК", "ОКНА, СТЕКЛА, ЗЕРКАЛА", "МОЙКА И ЧИСТКА"};
    String[] promKlining = {"ПРОМЫШЛЕННЫЙ КЛИНИНГ"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_klining_poverhnost);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        id = getIntent().getExtras().getInt("id");
        title = getIntent().getStringExtra("title");
        tl = (TableLayout) findViewById(R.id.tlMain);
        llmain = (LinearLayout) findViewById(R.id.llmain);

        setTitle(title);

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
        array = kuhnya;
        length = kuhnya.length;
        for (int i = 0; length > i; i++) {
            TextView tv = (TextView) View.inflate(this, R.layout.textviewrigth, null);
            //tv = (TextView)getResources().getLayout(R.layout.textview_powerhnost);
            tv.setText(kuhnya[i].toString());

            tv.setId(i);

            tv.setTextColor(Color.BLACK);
            tv.setPadding(18,18,0,0);
            tv.setOnClickListener(oclBtnCancel);
            llmain.addView(tv);
        }
    }

    OnClickListener oclBtnCancel = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(activity_klining_poverhnost.this, activity_klining_problema.class);
            intent.putExtra("id", id);
            intent.putExtra("id2", v.getId());
            intent.putExtra("title", titleProblem = array[v.getId()].toString());
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
