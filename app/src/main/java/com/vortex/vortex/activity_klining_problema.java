package com.vortex.vortex;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class activity_klining_problema extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    int id;
    int id2;
    TableLayout tbMain;

    String[][] kuhnyaPosuda = {{"для замачивания", "Marvel", "Optima Gel"},
            {"против гари и копоти", "Daze"},
            {"для отбеливания и дезинфекии", "Fumigel"}};
    String[][] kuhnyaPosudaMashina = {{"для мойки", "Blank"},
            {"для ополаскивания", "DeBlank"}};
    String[][] kuhnyaHolodilnik = {{"внутри (при выкылюченном состоянии)", "Well", "Optima Gel", "Optima"},
            {"снаружи", "Optima", "Optima Gel", "Well"}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_klining_problema);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Рекомендованые средства");

        id = getIntent().getExtras().getInt("id");
        id2 = getIntent().getExtras().getInt("id2");
        tbMain = (TableLayout) findViewById(R.id.tbMain);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        if (id == 1 && id2 == 0) {
            Toast.makeText(getBaseContext(), "Расчеты не выполнены" + id + "\n id2 = " + id2, Toast.LENGTH_SHORT).show();
            ShowSredstva(kuhnyaPosuda);
        }
    }

    private void ShowSredstva(String[][] kuhnyaPosuda) {
        int count = kuhnyaPosuda.length;
        for (int i = 0; count > i; i++) {
            int count2 = kuhnyaPosuda[i].length;
            for (int n = 0; count2 > n; n++) {

                if (n == 0) {

                    TableRow tr = new TableRow(this);
                    TextView tv = new TextView(this);
                    tv.setTypeface(null, Typeface.BOLD);
                    tv.setText(kuhnyaPosuda[i][n]);
                    tr.addView(tv);
                    tbMain.addView(tr);

                } else {

                    TableRow tr = new TableRow(this);
                    TextView tv = new TextView(this);
                    tv.setText("");
                    TextView tv2 = new TextView(this);
                    tv2.setText(kuhnyaPosuda[i][n]);
                    tr.addView(tv);
                    tr.addView(tv2);
                    tbMain.addView(tr);

                }

            }
        }
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
            Intent intent = new Intent(activity_klining_problema.this, ApkActivity2.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(activity_klining_problema.this, ActivityPisheProm2.class);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(activity_klining_problema.this, ActivityAutoVybor2.class);
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
}
