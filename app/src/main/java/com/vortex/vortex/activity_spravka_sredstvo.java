package com.vortex.vortex;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

public class activity_spravka_sredstvo extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String name;
    String[] descriptions = {
            "Промышленный биодеструктор пищевых жиров",
            "Кислотное беспенное моющее средство на основе ортофосфорной кислоты",
            "Кислотное беспенное моющее средство",
            "Надуксусная кислота Марка НУК15",
            "Высокощелочное беспенное моющее средств",
            "Высокощелочное беспенное моющее средство",
            "Высокощелочное беспенное моющее средство с активным хлором",
            "Кислотное высокопенное моющее средство",
            "Высокощелочное пенное моющее средство",
            "Высокощелочное пенное моющее средство",
            "Высокощелочное пенное моющее средство",
            "Щелочное пенное дезинфицирующее моющее средство для цветных металлов с активным хлором",
            "Щелочное пенное моющее средство с активным хлором",
            "Щелочное высокопенное моющее средство на основе четвертичных аммониевых соединений (ЧАС)",
            "Нейтральное концентрированное пенное средство для ручной мойки твердых поверхностей",
            "Щелочное низкопенное моющее средство с активным хлором",
            "Щелочное низкопенное моющее средство на основе четвертичных аммониевых соединений (ЧАС)"
    };

    String[] images = {
            "tank_bio_60.jpg",
            "ca_23.jpg",
            "ca_27.jpg",
            "cad_1415_3_20.jpg",
            "cb_23.jpg",
            "cb_46.jpg",
            "cbd_2401_1.jpg",
            "fa_18.jpg",
            "fb_17.jpg",
            "fb_36.jpg",
            "fb_48.jpg",
            "fbd_0402_1.jpg",
            "fbd_0803_1.jpg",
            "fbd_0902_2.jpg",
            "fn.jpg",
            "lbd_0107_1.jpg",
            "lbd_1002_2.jpg"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spravka_sredstvo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        WebView webView = (WebView) findViewById(R.id.webView);

        Intent intent = getIntent();
        //получаем строку и формируем имя ресурса
        String resName = "" + intent.getIntExtra("head", 0);
        name = intent.getStringExtra("headName");

        setTitle(name);

        int resId = Integer.parseInt(resName);
        String img = String.valueOf(images[resId]);
        String desc = String.valueOf(descriptions[resId]);

        String str = "<html><head></head><style>.leftimg {float:left; margin: 7px 7px 7px 0; }</style><body><H1 align=\"center\">" + name +"</H1><P><img height=\"150dp\" src=\"file:///android_res/raw/" + img + "\" class=\"leftimg\"> " + desc + "</P></body></html>";

        webView.loadDataWithBaseURL(null, str, "text/html", "en_US", null);



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
            Intent intent = new Intent(activity_spravka_sredstvo.this, ApkActivity2.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(activity_spravka_sredstvo.this, ActivityPisheProm2.class);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(activity_spravka_sredstvo.this, ActivityAutoVybor2.class);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {
            Toast.makeText(getBaseContext(), "Данный раздел находится в разработке", Toast.LENGTH_SHORT).show();
        }else if (id == R.id.nav_spravochnik){
            Intent intent = new Intent(activity_spravka_sredstvo.this, activity_spravka.class);
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
