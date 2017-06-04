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
    String[][] kuhnyaHolodilnik = {{"внутри (при выключенном состоянии)", "Well", "Optima Gel", "Optima"},
            {"снаружи", "Optima", "Optima Gel", "Well"}};
    String[][] kuhnyaVytyajka = {{"обезжиривание", "Daze"},
            {"блеск", "Twist", "Well"}};
    String[][] kuhnyaPlita = {{"", "Daze"}};
    String[][] kuhnyaPech = {{"", "Daze"}};
    String[][] kuhnyaPribory = {{"", "Marvel"}, {"Optima Gel"}};
    String[][] kuhnyaVanna = {{"", "Fumigel"}};
    String[][] kuhnyaDezinfekciya = {{"", "Fumigel"}};
    String[][] kuhnyaRabStol = {{"", "Optima", "Optima Gel"}};
    String[][] kuhnyaMoyka = {{"стены, двери", "Optima Gel", "Optima"},
            {"напольные покрытия", "Comfort", "Comfort Extra"},
            {"окна и зеркала", "Magic"},
            {"отбеливание мопов и полотенец", "Fumigel"},
            {"мусорные баки", "Optima", "Optima Gel"}};
    String[][] kuhnyaTruby = {{"устранение засоров", "Draft"}};
    String[][] kuhnyaZapah = {{"", "Block", "Fog"}};
    String[][] kuhnyaRuki = {{"жидкое мыло", "Joy", "Joy Platinum", "Fay"},
            {"антисептик", "Joy Sept"}};



    String[][] sanuselUnitaz = {{"ржавчина", "Breeze", "Destroy"},
            {"мочевой, водный камень", "Breeze", "Destroy"},
            {"известковый налет", "Breeze", "Destroy"},
            {"плесень, грибок", "Fumigel"},
            {"ржавчина", "Breeze", "Destroy"},
            {"ржавчина", "Breeze", "Destroy"},
            {"ржавчина", "Breeze", "Destroy"},};

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
            ShowSredstva(kuhnyaPosuda);
        }else if (id == 1 && id2 == 1) {
            ShowSredstva(kuhnyaPosudaMashina);
        }else if (id == 1 && id2 == 2) {
            ShowSredstva(kuhnyaHolodilnik);
        }else if (id == 1 && id2 == 3) {
            ShowSredstva(kuhnyaVytyajka);
        }else if (id == 1 && id2 == 4) {
            ShowSredstva(kuhnyaPlita);
        }else if (id == 1 && id2 == 5) {
            ShowSredstva(kuhnyaPech);
        }else if (id == 1 && id2 == 6) {
            ShowSredstva(kuhnyaPribory);
        }else if (id == 1 && id2 == 7) {
            ShowSredstva(kuhnyaVanna);
        }else if (id == 1 && id2 == 8) {
            ShowSredstva(kuhnyaDezinfekciya);
        }else if (id == 1 && id2 == 9) {
            ShowSredstva(kuhnyaRabStol);
        }else if (id == 1 && id2 == 10) {
            ShowSredstva(kuhnyaMoyka);
        }else if (id == 1 && id2 == 11) {
            ShowSredstva(kuhnyaTruby);
        }else if (id == 1 && id2 == 12) {
            ShowSredstva(kuhnyaZapah);
        }else if (id == 1 && id2 == 13) {
            ShowSredstva(kuhnyaRuki);
        }
    }

    private void ShowSredstva(String[][] array) {
        int count = array.length;
        for (int i = 0; count > i; i++) {
            int count2 = array[i].length;
            for (int n = 0; count2 > n; n++) {

                if (n == 0) {

                    TableRow tr = new TableRow(this);
                    TextView tv = new TextView(this);
                    tv.setTypeface(null, Typeface.BOLD);
                    tv.setText(array[i][n]);
                    tr.addView(tv);
                    tbMain.addView(tr);

                } else {

                    TableRow tr = new TableRow(this);
                    TextView tv = new TextView(this);
                    tv.setText("");
                    TextView tv2 = new TextView(this);
                    tv2.setText(array[i][n]);
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
