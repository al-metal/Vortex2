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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class activity_spravka extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private final String TAG = getClass().getSimpleName();
    String[] names = {
            "TANK BIO",
            "TANK CA23",
            "TANK CA27",
            "Tank CAD 1415/3",
            "TANK CB23",
            "TANK CB46",
            "TANK CBD 2401/1",
            "TANK FA18",
            "TANK FB17",
            "TANK FB36",
            "TANK FB48",
            "Tank FBD 0402/1",
            "ТANK FBD 0803/1",
            "TANK FBD 0902/2",
            "Tank FN",
            "Tank LBD 0107/1",
            "TANK LBD 1002/2",

            "ALGALIT",
            "ALGALIT 50",
            "ALGAVIT 25",
            "ALGAVIT 50",
            "BIOTEC",
            "BIOTEC M",
            "BIOTEC C",
            "BIOTEC SUPER",
            "DESIMIX",
            "DESITUB",
            "ECOVIT",
            "ELOVIT",
            "FITOLIT",
            "FORBICID",
            "IMOVIT",
            "KLIOVIT",
            "KSILAN",
            "KSILAN K",
            "KSILAN M",
            "KSILAN SUPER",
            "LACTOVIT",
            "PRIOLIT",
            "SOMATEST",
            "SUPRACID",
            "VIOLIT",

            "ACE",
            "APEX",
            "DEBUG",
            "DEFROSTER",
            "DELICATE",
            "diy",
            "DOZEX",
            "GURU",
            "HANDS",
            "HANDS NANOMAX",
            "HANDS SHINE",
            "LOCO",
            "MAGNAT",
            "MASTER",
            "MASTER TONE",
            "MOBILE",
            "NANEX",
            "NANO FINISH",
            "NANO NEXT",
            "NOVICE",
            "ORBIS",
            "POLITURA",
            "POLITURA GLOSS",
            "PROFY",
            "PROPELLA",
            "ROTAE",
            "ROTAE VIS",
            "SAPO",
            "SENZA",
            "SILICONE",
            "SOLO",
            "TANTUM",
            "TIRO",
            "TIRO TONE",
            "TUTELA",
            "TUTELA FAST",
            "TUTOR",
            "TWIN",
            "UNIOR",
            "WITRUM",
            "СУПЕРКОНЦЕНТРАТ",

            "Antistick",
            "Blank",
            "Block",
            "Breeze",
            "Соmfort",
            "Соmfort Extra",
            "Daze",
            "DeBlank",
            "DeStroy",
            "Draft",
            "Fay",
            "Fog",
            "Fortis",
            "FUMIGEL",
            "Joy",
            "Joy Platinum",
            "JoySept",
            "Kraft",
            "Latrin",
            "Latrin Bio",
            "Magic",
            "Marvel",
            "Novatec",
            "Novatec Foam",
            "Optima",
            "Optima Gel",
            "Sauna",
            "Twist",
            "Well"
    };

    EditText inputSearch;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spravka);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Справочник");

        inputSearch = (EditText) findViewById(R.id.inputSearch);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // находим список
        ListView listView = (ListView) findViewById(R.id.lvMain);

        // создаем адаптер
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, names);

        // присваиваем адаптер списку
        listView.setAdapter(adapter);

        //Обрабатываем щелчки на элементах ListView:
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                String nameProduct = ((TextView) v).getText().toString();
                position = ReturnId(nameProduct);
                Intent intent = new Intent();
                intent.setClass(activity_spravka.this, activity_spravka_sredstvo.class);
                Log.i(TAG, names[position]);
                intent.putExtra("head", position);
                intent.putExtra("headName", names[position]);

                //intent.putExtra("position", position);

                //запускаем вторую активность
                startActivity(intent);
            }
        });

        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                activity_spravka.this.adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });
    }

    private int ReturnId(String nameProduct) {
        int id = 0;

        for(int i = 0; names.length > i; i++){
            if(names[i].equals(nameProduct)){
                id = i;
                break;
            }
        }

        return id;
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
            Intent intent = new Intent(activity_spravka.this, ApkActivity2.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(activity_spravka.this, ActivityPisheProm2.class);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(activity_spravka.this, ActivityAutoVybor2.class);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {
            Toast.makeText(getBaseContext(), "Данный раздел находится в разработке", Toast.LENGTH_SHORT).show();
        }else if (id == R.id.nav_spravochnik){
            Intent intent = new Intent(activity_spravka.this, activity_spravka.class);
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
