package com.vortex.vortex;

import android.content.Intent;
import android.graphics.Color;
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

    String[][] kuhnyaPosuda = {{"Для замачивания", "Marvel", "Optima Gel"},
            {"Против гари и копоти", "Daze"},
            {"Для отбеливания и дезинфекции", "Fumigel"}};
    String[][] kuhnyaPosudaMashina = {{"Для мойки", "Blank"},
            {"Для ополаскивания", "DeBlank"}};
    String[][] kuhnyaHolodilnik = {{"Внутри (при выключенном состоянии)", "Well", "Optima Gel", "Optima"},
            {"Снаружи", "Optima", "Optima Gel", "Well"}};
    String[][] kuhnyaVytyajka = {{"Обезжиривание", "Daze"},
            {"Блеск", "Twist", "Well"}};
    String[][] kuhnyaPlita = {{"Плита, гриль, духовка", "Daze"}};
    String[][] kuhnyaPech = {{"Микроволновая печь", "Daze"}};
    String[][] kuhnyaPribory = {{"Столовые приборы", "Marvel"}, {"", "Optima Gel"}};
    String[][] kuhnyaVanna = {{"Замачивание посуды", "Marvel", "Optima Gel"},
            {"Отбеливание посуды", "Fumigel"}};
    String[][] kuhnyaDezinfekciya = {{"Общая дезинфекция", "Fumigel"}};
    String[][] kuhnyaRabStol = {{"Рабочие столы и поверхности", "Optima", "Optima Gel"}};
    String[][] kuhnyaMoyka = {{"Стены, двери", "Optima Gel", "Optima", "Well"},
            {"Напольные покрытия", "Comfort", "Comfort Extra"},
            {"Окна и зеркала", "Magic"},
            {"Отбеливание мопов и полотенец", "Fumigel"},
            {"Мусорные баки", "Optima", "Optima Gel"}};
    String[][] kuhnyaTruby = {{"Устранение засоров", "Draft"}};
    String[][] kuhnyaZapah = {{"Локально", "Block"},
            {"Методом сухого тумана", "Fog"}};
    String[][] kuhnyaRuki = {{"Жидкое мыло", "Joy", "Joy Platinum", "Fay"},
            {"Антисептик", "Joy Sept"}};
    String[][] kuhnyaJiro = {{"Для жироуловителей и жироотделителей", "Tank Bio"}};



    String[][] sanuselUnitaz = {{"Ржавчина", "Breeze", "Destroy"},
            {"Мочевой, водный камень", "Breeze", "Destroy"},
            {"Известковый налет", "Breeze", "Destroy"},
            {"Плесень, грибок", "Fumigel"},
            {"Потожировые загрязнения", "Breeze", "Fumigel"},
            {"Органика, микробы", "Fumigel"},
            {"Дезинфекция", "Fumigel"}};

    String[][] sanuselVanna = {{"Потожировые и мыльные загрязнения", "Breeze", "Fumigel"},
            {"Известковый налет", "Breeze"},
            {"Ржавые подтеки", "Breeze"},
            {"Отбеливание и дезинфекция", "Fumigel"},
            {"Замачивание посуды", "Marvel", "Optima Gel"},
            {"Отбеливание посуды", "Fumigel"},
            {"Очистка швов кафеля", "Fumigel"}};
    String[][] sanuselOkna = {{"Окна, стекла, зеркала", "Magic"}};
    String[][] sanuselMoyka = {{"Стены, двери", "Optima", "Optima Gel"},
            {"Напольные покрытия", "Comfort", "Comfort Extra"},
            {"Мусорные баки", "Optima", "Optima Gel"},
            {"Плафоны", "Optima", "Optima Gel", "Magic"}};
    String[][] sanuselTruby = {{"Устранение засоров", "Draft"}};
    String[][] sanuselZapah = {{"Устранение запахов", "Block"}};
    String[][] sanuselRuki = {{"Жидкое мыло", "Joy", "Joy Platinum", "Fay"},
            {"Антисептик", "Joy Sept"}};
    String[][] sanuselBanya = {{"Для очистки поверхностей", "Sauna"}};
    String[][] sanuselBIOTualet = {{"Для биотуалета", "Latrin"},
            {"Для септика", "Latrin Bio"}};

    String[][] officeOrgtehnika = {{"Мониторы, телевизоры", "Twist", "Magic"},
            {"Телефоны", "Twist", "Magic", "Optima", "Optima Gel"},
            {"Факсы, принтеры, ксероксы", "Twist", "Magic", "Optima", "Optima Gel"}};
    String[][] officeMebelMygkaya = {{"Кожаная обивка", "Kraft"},
            {"Текстильная обивка", "Well"}};
    String[][] officeMebelTverdaya = {{"Чистка", "Well"},
            {"Полировка", "Twist"}};
    String[][] officeOkna = {{"Окна, стекла, зеркала", "Magic"}};
    String[][] officeMoyka = {{"Стены, двери", "Optima", "Optima Gel"},
            {"Жалюзи", "Well", "Optima", "Optima Gel"},
            {"Батареи центрального отопления", "Well", "Optima", "Optima Gel"},
            {"Мусорные баки", "Optima", "Optima Gel"},
            {"Плафоны", "Optima", "Optima Gel", "Magic"},
            {"Удаление наклеек и скотча", "Antistik"}};
    String[][] officeObshayaDezinfekciya = {{"Общая дезинфекция", "Fumigel"}};
    String[][] officePol = {{"Ламинат", "Comfort"},
            {"Паркет", "Comfort"},
            {"Линолеум", "Comfort"},
            {"Ковролин", "Novatec", "Novatec Foam"},
            {"Керамическая плитка", "Comfort", "Comfort Extra"},
            {"Полимерные покрытия", "Comfort", "Comfort Extra"}};


    String[][] obshiyKliningOrgtehnika = {{"Мониторы, телевизоры", "Twist", "Magic"},
            {"Телефоны", "Twist", "Magic", "Optima", "Optima Gel"},
            {"Факсы, принтеры, ксероксы", "Twist", "Magic", "Optima", "Optima Gel"}};
    String[][] obshiyKliningMyagkayaMebel = {{"Кожаная обивка", "Kraft"},
            {"Текстильная обивка", "Well"}};
    String[][] obshiyKliningDerevoMebel = {{"Чистка", "Well"},
            {"Полировка", "Twist"}};
    String[][] obshiyKliningOkna = {{"Окна, стекла, зеркала", "Magic"}};
    String[][] obshiyKliningMoyka = {{"Стены, двери", "Optima", "Optima Gel"},
            {"Жалюзи", "Well", "Optima", "Optima Gel"},
            {"Батареи центрального отопления", "Well", "Optima", "Optima Gel"},
            {"Мусорные баки", "Optima", "Optima Gel"},
            {"Плафоны", "Magic"},
            {"Скотч, жев. резинка, маркер, наклейки", "Antistik"}};
    String[][] obshiyKliningObshayaDezinfekciya = {{"Общая дезинфекция", "Fumigel"}};
    String[][] obshiyKliningRuki = {{"Жидкое мыло", "Joy", "Joy Platinum", "Fay"},
            {"Антисептик", "Joy Sept"}};
    String[][] obshiyKliningZapah = {{"Локально", "Block"},
            {"Методом сухого тумана", "Fog"}};
    String[][] obshiyKliningPol = {{"Ламинат", "Comfort"},
            {"Паркет", "Comfort"},
            {"Линолеум", "Comfort"},
            {"Ковролин", "Novatec", "Novatec Foam"},
            {"Керамическая плитка", "Comfort", "Comfort Extra"},
            {"Полимерные покрытия", "Comfort", "Comfort Extra"}};

    String[][] remontRuki = {{"Жидкое мыло", "Joy", "Joy Platinum", "Fay"},
            {"Антисептик", "Joy Sept"},
            {"Очищающая паста", "Sapo"}};
    String[][] remontOkna = {{"Окна, стекла, зеркала", "Magic"}};
    String[][] remontMoyka = {{"Цемент, затирка, клей, шпаклевка, побелка", "Destroy"},
            {"Высолы", "Destroy"},
            {"Сажа, копоть на фасадах", "Daze", "Fortis"},
            {"Сажа, копоть на брусчатке", "Daze", "Fortis"},
            {"Водный камень", "Breeze", "Destroy"},
            {"Удаление следов скотча, наклеек, маркеров", "Antistik"},
            {"Стены, двери", "Optima", "Optima Gel"}};
    String[][] remontPol = {{"Ламинат", "Comfort"},
            {"Паркет", "Comfort"},
            {"Линолеум", "Comfort"},
            {"Ковролин", "Novatec", "Novatec Foam"},
            {"Керамическая плитка", "Comfort", "Comfort Extra"},
            {"Полимерные покрытия", "Comfort", "Comfort Extra"}};

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
        }else if (id == 1 && id2 == 14) {
            ShowSredstva(kuhnyaJiro);
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
        }else if (id == 2 && id2 == 7) {
            ShowSredstva(sanuselBanya);
        }else if (id == 2 && id2 == 8) {
            ShowSredstva(sanuselBIOTualet);
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
    }

    private void ShowSredstva(String[][] array) {
        int count = array.length;

        for (int i = 0; count > i; i++) {

            int count2 = array[i].length;
            for (int n = 0; count2 > n; n++) {

                if (n == 0) {
                    TableRow tr = (TableRow) View.inflate(this, R.layout.tablerow, null);
                    TextView tv = (TextView) tr.findViewById(R.id.col1);
                    tv.setText(array[i][n]);
                    n++;
                    tv = (TextView) tr.findViewById(R.id.col2);
                    tv.setTextColor(Color.parseColor("#000000"));
                    tv.setText(array[i][n]);
                    tbMain.addView(tr);

                } else {
                    TableRow tr = (TableRow) View.inflate(this, R.layout.tablerow, null);
                    TextView tv = (TextView) tr.findViewById(R.id.col1);
                    tv.setText("");
                    tv = (TextView) tr.findViewById(R.id.col2);
                    tv.setTextColor(Color.parseColor("#000000"));
                    tv.setText(array[i][n]);
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
