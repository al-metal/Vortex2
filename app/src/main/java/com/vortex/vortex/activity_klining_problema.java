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
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class activity_klining_problema extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    int id;
    int id2;
    TableLayout tbMain;
    String title;

    String[][] kuhnyaPosuda = {{"для замачивания", "Marvel", "Optima Gel"},
            {"против гари и копоти", "Daze"},
            {"для отбеливания и дезинфекции", "Fumigel"}};
    String[][] kuhnyaPosudaMashina = {{"для мойки", "Blank"},
            {"для ополаскивания", "DeBlank"}};
    String[][] kuhnyaHolodilnik = {{"внутри (при выключенном состоянии)", "Well", "Optima Gel", "Optima"},
            {"снаружи", "Optima", "Optima Gel", "Well"}};
    String[][] kuhnyaVytyajka = {{"обезжиривание", "Daze"},
            {"блеск", "Twist", "Well"}};
    String[][] kuhnyaPlita = {{"плита, гриль, духовка", "Daze"}};
    String[][] kuhnyaPech = {{"микроволновая печь", "Daze"}};
    String[][] kuhnyaPribory = {{"столовые приборы", "Marvel"}, {"", "Optima Gel"}};
    String[][] kuhnyaVanna = {{"замачивание посуды", "Marvel", "Optima Gel"},
            {"отбеливание посуды", "Fumigel"}};
    String[][] kuhnyaDezinfekciya = {{"общая дезинфекция", "Fumigel"}};
    String[][] kuhnyaRabStol = {{"рабочие столы и поверхности", "Optima", "Optima Gel"}};
    String[][] kuhnyaMoyka = {{"стены, двери", "Optima Gel", "Optima", "Well"},
            {"напольные покрытия", "Comfort", "Comfort Extra"},
            {"окна и зеркала", "Magic"},
            {"отбеливание мопов и полотенец", "Fumigel"},
            {"мусорные баки", "Optima", "Optima Gel"}};
    String[][] kuhnyaTruby = {{"устранение засоров", "Draft"}};
    String[][] kuhnyaZapah = {{"локально", "Block"},
            {"методом сухого тумана", "Fog"}};
    String[][] kuhnyaRuki = {{"жидкое мыло", "Joy", "Joy Platinum", "Fay"},
            {"антисептик", "Joy Sept"}};



    String[][] sanuselUnitaz = {{"ржавчина", "Breeze", "Destroy"},
            {"мочевой, водный камень", "Breeze", "Destroy"},
            {"известковый налет", "Breeze", "Destroy"},
            {"плесень, грибок", "Fumigel"},
            {"потожировые загрязнения", "Breeze", "Fumigel"},
            {"органика, микробы", "Fumigel"},
            {"дезинфекция", "Fumigel"}};

    String[][] sanuselVanna = {{"потожировые и мыльные загрязнения", "Breeze", "Fumigel"},
            {"известковый налет", "Breeze"},
            {"ржавые подтеки", "Breeze"},
            {"отбеливание и дезинфекция", "Fumigel"},
            {"замачивание посуды", "Marvel", "Optima Gel"},
            {"отбеливание посуды", "Fumigel"},
            {"очистка швов кафеля", "Fumigel"}};
    String[][] sanuselOkna = {{"окна, стекла, зеркала", "Magic"}};
    String[][] sanuselMoyka = {{"стены, двери", "Optima", "Optima Gel"},
            {"напольные покрытия", "Comfort", "Comfort Extra"},
            {"мусорные баки", "Optima", "Optima Gel"},
            {"плафоны", "Optima", "Optima Gel", "Magic"}};
    String[][] sanuselTruby = {{"устранение засоров", "Draft"}};
    String[][] sanuselZapah = {{"устранение запахов", "Block"}};
    String[][] sanuselRuki = {{"жидкое мыло", "Joy", "Joy Platinum", "Fay"},
            {"антисептик", "Joy Sept"}};

    String[][] officeOrgtehnika = {{"мониторы, телевизоры", "Twist", "Magic"},
            {"телефоны", "Twist", "Magic", "Optima", "Optima Gel"},
            {"факсы, принтеры, ксероксы", "Twist", "Magic", "Optima", "Optima Gel"}};
    String[][] officeMebelMygkaya = {{"кожаная обивка", "Kraft"},
            {"текстильная обивка", "Well"}};
    String[][] officeMebelTverdaya = {{"чистка", "Well"},
            {"полировка", "Twist"}};
    String[][] officeOkna = {{"окна, стекла, зеркала", "Magic"}};
    String[][] officeMoyka = {{"стены, двери", "Optima", "Optima Gel"},
            {"жалюзи", "Well", "Optima", "Optima Gel"},
            {"батареи центрального отопления", "Well", "Optima", "Optima Gel"},
            {"мусорные баки", "Optima", "Optima Gel"},
            {"плафоны", "Optima", "Optima Gel"},
            {"удаление наклеек и скотча", "Antistik"},
            {"плафоны", "Magic"}};
    String[][] officeObshayaDezinfekciya = {{"общая дезинфекция", "Fumigel"}};
    String[][] officePol = {{"ламинат", "Comfort"},
            {"паркет", "Comfort"},
            {"линолеум", "Comfort"},
            {"ковролин", "Novatec", "Novatec Foam"},
            {"керамическая плитка", "Comfort", "Comfort Extra"},
            {"полимерные покрытия", "Comfort", "Comfort Extra"}};


    String[][] obshiyKliningOrgtehnika = {{"мониторы, телевизоры", "Twist", "Magic"},
            {"телефоны", "Twist", "Magic", "Optima", "Optima Gel"},
            {"факсы, принтеры, ксероксы", "Twist", "Magic", "Optima", "Optima Gel"}};
    String[][] obshiyKliningMyagkayaMebel = {{"кожаная обивка", "Kraft"},
            {"текстильная обивка", "Well"}};
    String[][] obshiyKliningDerevoMebel = {{"чистка", "Well"},
            {"полировка", "Twist"}};
    String[][] obshiyKliningOkna = {{"окна, стекла, зеркала", "Magic"}};
    String[][] obshiyKliningMoyka = {{"стены, двери", "Optima", "Optima Gel"},
            {"жалюзи", "Well", "Optima", "Optima Gel"},
            {"батареи центрального отопления", "Well", "Optima", "Optima Gel"},
            {"мусорные баки", "Optima", "Optima Gel"},
            {"плафоны", "Magic"},
            {"скотч, жев. резинка, маркер, наклейки", "Antistik"}};
    String[][] obshiyKliningObshayaDezinfekciya = {{"общая дезинфекция", "Fumigel"}};
    String[][] obshiyKliningRuki = {{"жидкое мыло", "Joy", "Joy Platinum", "Fay"},
            {"антисептик", "Joy Sept"}};
    String[][] obshiyKliningZapah = {{"локально", "Block"},
            {"методом сухого тумана", "Fog"}};
    String[][] obshiyKliningPol = {{"ламинат", "Comfort"},
            {"паркет", "Comfort"},
            {"линолеум", "Comfort"},
            {"ковролин", "Novatec", "Novatec Foam"},
            {"керамическая плитка", "Comfort", "Comfort Extra"},
            {"полимерные покрытия", "Comfort", "Comfort Extra"}};

    String[][] remontRuki = {{"жидкое мыло", "Joy", "Joy Platinum", "Fay"},
            {"антисептик", "Joy Sept"},
            {"очищающая паста", "Sapo"}};
    String[][] remontOkna = {{"окна, стекла, зеркала", "Magic"}};
    String[][] remontMoyka = {{"цемент, затирка, клей, шпаклевка, побелка", "Destroy"},
            {"высолы", "Destroy"},
            {"сажа, копоть на фасадах", "Daze", "Fortis"},
            {"сажа, копоть на брусчатке", "Daze", "Fortis"},
            {"водный камень", "Breeze", "Destroy"},
            {"удаление следов скотча, наклеек, маркеров", "Antistik"},
            {"стены, двери", "Optima", "Optima Gel"}};
    String[][] remontPol = {{"ламинат", "Comfort", "Comfort Extra"},
            {"паркет", "Comfort", "Comfort Extra"},
            {"линолеум", "Comfort", "Comfort Extra"},
            {"ковровые покрытия", "Novatec", "Novatec Foam"},
            {"керамическая плитка", "Comfort", "Comfort Extra"}};

    String[][] PromKlining = {{"следы от нефтепродуктов, жировых и масляных загрязнений на оборудовании, полах и стенах", "Fortis"},
            {"следы от нефтепродуктов и масел на руках", "Sapo"},
            {"очистка поверхностей после пожара", "Daze"},
            {"мойка полов", "Comfort", "Comfort Extra"},
            {"мойка стен", "Optima", "Optima Gel"},
            {"мойка стекол, зеркал", "Magic"},
            {"плесень, грибок", "Fumigel"},
            {"устранение запахов", "локально - Block", "методом сухого тумана - Fog"}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_klining_problema);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        id = getIntent().getExtras().getInt("id");
        id2 = getIntent().getExtras().getInt("id2");
        tbMain = (TableLayout) findViewById(R.id.tbMain);
        title = getIntent().getStringExtra("title");
        setTitle(title);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //region Kuhnya
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
//endregion

        //region Sanusel
        else if (id == 2 && id2 == 0) {
            ShowSredstva(sanuselUnitaz);
        }else if (id == 2 && id2 == 1) {
            ShowSredstva(sanuselVanna);
        }else if (id == 2 && id2 == 2) {
            ShowSredstva(sanuselOkna);
        }else if (id == 2 && id2 == 3) {
            ShowSredstva(sanuselMoyka);
        }else if (id == 2 && id2 == 4) {
            ShowSredstva(sanuselTruby);
        }else if (id == 2 && id2 == 5) {
            ShowSredstva(sanuselZapah);
        }else if (id == 2 && id2 == 6) {
            ShowSredstva(sanuselRuki);
        }
        //endregion

        //region office
        else if (id == 3 && id2 == 0) {
            ShowSredstva(officeOrgtehnika);
        }else if (id == 3 && id2 == 1) {
            ShowSredstva(officeMebelMygkaya);
        }else if (id == 3 && id2 == 2) {
            ShowSredstva(officeMebelTverdaya);
        }else if (id == 3 && id2 == 3) {
            ShowSredstva(officeOkna);
        }else if (id == 3 && id2 == 4) {
            ShowSredstva(officeMoyka);
        }else if (id == 3 && id2 == 5) {
            ShowSredstva(officeObshayaDezinfekciya);
        }else if (id == 3 && id2 == 6) {
            ShowSredstva(officePol);
        }
        //endregion

        //region obshiyKlinig
        else if (id == 4 && id2 == 0) {
            ShowSredstva(obshiyKliningOrgtehnika);
        }else if (id == 4 && id2 == 1) {
            ShowSredstva(obshiyKliningMyagkayaMebel);
        }else if (id == 4 && id2 == 2) {
            ShowSredstva(obshiyKliningDerevoMebel);
        }else if (id == 4 && id2 == 3) {
            ShowSredstva(obshiyKliningOkna);
        }else if (id == 4 && id2 == 4) {
            ShowSredstva(obshiyKliningMoyka);
        }else if (id == 4 && id2 == 5) {
            ShowSredstva(obshiyKliningObshayaDezinfekciya);
        }else if (id == 4 && id2 == 6) {
            ShowSredstva(obshiyKliningRuki);
        }else if (id == 4 && id2 == 7) {
            ShowSredstva(obshiyKliningZapah);
        }else if (id == 4 && id2 == 8) {
            ShowSredstva(obshiyKliningPol);
        }
        //endregion

        //region Remont
        else if (id == 5 && id2 == 0) {
            ShowSredstva(remontRuki);
        }else if (id == 5 && id2 == 1) {
            ShowSredstva(remontOkna);
        }else if (id == 5 && id2 == 2) {
            ShowSredstva(remontMoyka);
        }else if (id == 5 && id2 == 3) {
            ShowSredstva(remontPol);
        }
        //endregion


        else if (id == 6 && id2 == 0) {
            ShowSredstva(PromKlining);
        }
    }

    private void ShowSredstva(String[][] array) {
        int count = array.length;

        for (int i = 0; count > i; i++) {

            int count2 = array[i].length;
            for (int n = 0; count2 > n; n++) {
                TableRow tr = (TableRow) View.inflate(this, R.layout.tablerow, null);
                if (n == 0) {
                    TextView tv = (TextView) View.inflate(this, R.layout.textviewleft, null);
                    TextView tv2 = (TextView) View.inflate(this, R.layout.textviewrigth, null);
                    tv.setWidth(400);
                    tv.setText(array[i][n]);
                    n++;
                    tv2.setText(array[i][n]);

                    tr.addView(tv);
                    tr.addView(tv2);
                    tbMain.addView(tr);

                } else {


                    TextView tv = (TextView) View.inflate(this, R.layout.textviewleft, null);
                    TextView tv2 = (TextView) View.inflate(this, R.layout.textviewrigth, null);

                    tv.setText("");

                    tv2.setText(array[i][n]);
                    tv2.setPadding(17,0,0,0);

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
