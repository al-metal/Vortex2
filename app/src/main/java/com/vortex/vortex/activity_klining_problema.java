package com.vortex.vortex;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class activity_klining_problema extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnTouchListener {

    int id;
    int id2;
    TableLayout tbMain;
    String title;
    TextView tv;
    String nameProduct;

    String[][] kitchenDishWasher = {{"Для основной мойки", "Blank"},
            {"Для ополаскивания", "DeBlank"}};
    String[][] kitchenHandWashing = {{"Ручная мойка, замачивание", "Marvel", "Optima Gel"},
            {"Против жира, нагара и копоти", "Daze", "TANK FB 36", "TANK FB 48"},
            {"Для отбеливания и дезинфекции посуды", "TANK LBD 0107/1"}};
    String[][] kitchenSmokeChamber = {{"Против жира, нагара и копоти", "Daze", "TANK FB 36", "TANK FB 48"}};
    String[][] kitchenRefrigerator = {{"Внутри (при выключенном состоянии)", "Fungus", "Optima Gel"},
            {"Снаружи", "Well", "Optima Gel", "Optima"}};
    String[][] kitchenDisinfection = {{"Дезинфекция", "TANK LBD 1002/2"}};
    String[][] kitchenTextile = {{"Стирка и отбеливание", "TANK LBD 0107/1"}};
    String[][] kitchenGreaseTrap = {{"Против жира", "TANK BIO"}};
    String[][] kitchenTubing = {{"Устранение засоров", "Draft"}};
    String[][] kitchenSmell = {{"Устраняет запахи любого происхождения", "Block", "Fog"}};
    String[][] kitchenRoom = {{"Стены, двери, подоконники", "Well", "Optima Gel"},
            {"Напольные покрытия", "Comfort", "Comfort Extra"},
            {"Окна, зеркала и витрины", "Magic"},
            {"Мусорные баки", "Well", "Optima Gel", "Optima"},
            {"Рабочие столы и поверхности", "Well", "Optima Gel", "Optima"}};
    String[][] kitchenHandHygiene = {{"Жидкое мыло", "Joy", "Joy Крем-мыло", "Fay"},
            {"Антисептик", "Joy Sept"}};


    String[][] bathroomToilet = {{"Ржавчина, мочевой, водный камень, известковый налет", "Breeze"},
            {"Плесень, грибок, органика, микробы", "Fumigel", "Fungus"},
            {"Потожировые загрязнения", "Fumigel", "Fungus"},
            {"Дезинфекция", "Fumigel", "Fungus"}};

    String[][] bathroomBath = {{"Потожировые и мыльные загрязнения", "Fumigel", "Breeze"},
            {"Известковый налет, ржавые подтеки", "Breeze"},
            {"Отбеливание и дезинфекция", "Fumigel", "Fungus"}};

    String[][] bathroomWindows = {{"Стеклянные поверхности", "Magic"}};

    String[][] bathroomWashing = {{"Стены, двери", "Well", "Optima Gel"},
            {"Напольные покрытия", "Comfort", "Comfort Extra"},
            {"Мусорные баки", "Well", "Optima Gel", "Optima"},
            {"Плафоны", "Well", "Optima Gel", "Optima"}};

    String[][] bathroomTubing = {{"Устранение засоров", "Draft"}};

    String[][] bathroomSmell = {{"Устраняет запахи любого происхождения", "Block", "Fog"}};

    String[][] bathroomSepticTank = {{"Для биотуалетов", "Latrin"},
            {"Для септика и выгребной ямы", "Latrin Bio(Жидкий)", "Latrin Bio(Сухой)"}};

    String[][] bathroomHands = {{"Жидкое мыло", "Joy", "Joy Крем-мыло", "Fay"},
            {"Антисептик", "Joy Sept"}};


    String[][] officeTechnique = {{"Мониторы, телевизоры", "Magic"},
            {"Телефоны", "Well", "Magic", "Twist"},
            {"Факсы, принтеры, ксероксы", "Well", "Magic", "Twist"}};

    String[][] officeSoftFurniture = {{"Кожаная обивка", "Kraft"},
            {"Текстильная обивка", "Well"}};

    String[][] officeWoodenFurniture = {{"Чистка", "Well"},
            {"Полировка", "Twist"}};

    String[][] officeMetalFurniture = {{"Нержавеющая сталь, хромированные поручни, ограждения, анодированные предметы. Чистка и полировка", "Polex"}};

    String[][] officeWindows = {{"Стеклянные поверхности", "Magic"}};

    String[][] officeScotch = {{"Удаление следов", "Antistick"}};

    String[][] officeWashing = {{"Стены, двери, подоконники", "Well", "Optima Gel", "Optima"},
            {"Жалюзи", "Well", "Optima Gel", "Optima"},
            {"Батареи центрального отопления", "Well", "Optima Gel", "Optima"},
            {"Мусорные баки", "Well", "Optima Gel", "Optima"},
            {"Плафоны", "Well", "Well", "Optima Gel", "Optima"},
            {"Напольные покрытия", "Comfort", "Comfort Extra"}};

    String[][] officeSmell = {{"Устраняет запахи любого происхождения", "Block", "Fog"}};


    String[][] cleaningTechnique = {{"Мониторы, телевизоры", "Magic"},
            {"Телефоны", "Well", "Magic", "Twist"},
            {"Факсы, принтеры, ксероксы", "Well", "Magic", "Twist"}};

    String[][] cleaningSoftFurniture = {{"Кожаная обивка", "Kraft"},
            {"Текстильная обивка", "Well"}};

    String[][] cleaningWoodenFurniture = {{"Чистка", "Well"},
            {"Полировка", "Twist"}};

    String[][] cleaningMetalFurniture = {{"Нержавеющая сталь, хромированные поручни, ограждения, анодированные предметы. Чистка и полировка", "Polex"}};

    String[][] cleaningWindows = {{"Стеклянные поверхности", "Magic"}};

    String[][] cleaningScotch = {{"Удаление следов", "Antistick"}};

    String[][] cleaningBassein = {{"Органические загрязнения, плесень, грибы. Дезинфекция", "Fumigel", "TANK FBD 0803/1"},
            {"Ржавчина, мочевой, водный камень, известковый налет", "Breeze", "TANK FA18"}};

    String[][] cleaningWashing = {{"Стены, двери, подоконники", "Well", "Optima Gel"},
            {"Жалюзи", "Well", "Optima Gel"},
            {"Батареи центрального отопления", "Well", "Optima Gel"},
            {"Мусорные баки", "Well", "Optima Gel"},
            {"Плафоны", "Well", "Optima Gel"},
            {"Напольные покрытия", "Comfort", "Comfort Extra"},
            {"Ковровые покрытия", "Novatec", "Novatec Foam"}};

    String[][] cleaningSmell = {{"Устраняет запахи любого происхождения", "Block", "Fog"}};

    String[][] cleaningHands = {{"Жидкое мыло", "Joy", "Joy Крем-мыло", "Fay"},
            {"Антисептик", "Joy Sept"}};


    String[][] repairHands = {{"Жидкое мыло", "Joy", "Joy Крем-мыло", "Fay"},
            {"Антисептик", "Joy Sept"},
            {"очищающая паста", "Sapo"}};

    String[][] repairWindows = {{"Стеклянные поверхности", "Magic"}};

    String[][] repairWashing = {{"Цемент, затирка, клей, шпаклевка, побелка", "Destroy"},
            {"Высолы", "Destroy"},
            {"Сажа, копоть на фасадах", "Daze", "Fortis"},
            {"Сажа, копоть на брусчатке", "Daze", "Fortis"},
            {"Водный камень", "Breeze", "Destroy"},
            {"Удаление следов скотча, наклеек, маркеров", "Antistick\n"},
            {"Стены, двери", "Well", "Optima Gel"}};

    String[][] repairFloor = {{"Ламинат", "Comfort", "Comfort Extra"},
            {"Паркет", "Comfort", "Comfort Extra"},
            {"Линолеум", "Comfort", "Comfort Extra"},
            {"Ковровые покрытия", "Novatec", "Novatec Foam"},
            {"Керамическая плитка", "Comfort", "Comfort Extra"}};


    String[][] hotelRoom = {{"Влажная уборка: тумбочки, полки, подоконник, оргтехника", "Well"},
            {"Стекла, зеркала, экран телевизора", "Magic"},
            {"Полироль для деревянной и пластиковой мебели", "Twist"},
            {"Нейтрализация запахов, освежитель воздуха", "Block"}};

    String[][] hotelBathRoom = {{"Унитаз", "Breeze"},
            {"Кафель, сантехника, ванная, раковина", "Breeze Spray"},
            {"Зеркало", "Magic"},
            {"Нейтрализация запахов, освежитель воздуха", "Block"}};

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
            ShowSredstva(kitchenDishWasher);
        } else if (id == 1 && id2 == 1) {
            ShowSredstva(kitchenHandWashing);
        } else if (id == 1 && id2 == 2) {
            ShowSredstva(kitchenSmokeChamber);
        } else if (id == 1 && id2 == 3) {
            ShowSredstva(kitchenRefrigerator);
        } else if (id == 1 && id2 == 4) {
            ShowSredstva(kitchenDisinfection);
        } else if (id == 1 && id2 == 5) {
            ShowSredstva(kitchenTextile);
        } else if (id == 1 && id2 == 6) {
            ShowSredstva(kitchenGreaseTrap);
        } else if (id == 1 && id2 == 7) {
            ShowSredstva(kitchenTubing);
        } else if (id == 1 && id2 == 8) {
            ShowSredstva(kitchenSmell);
        } else if (id == 1 && id2 == 9) {
            ShowSredstva(kitchenRoom);
        } else if (id == 1 && id2 == 10) {
            ShowSredstva(kitchenHandHygiene);
        }
//endregion

        //region Sanusel
        else if (id == 2 && id2 == 0) {
            ShowSredstva(bathroomToilet);
        } else if (id == 2 && id2 == 1) {
            ShowSredstva(bathroomBath);
        } else if (id == 2 && id2 == 2) {
            ShowSredstva(bathroomWindows);
        } else if (id == 2 && id2 == 3) {
            ShowSredstva(bathroomWashing);
        } else if (id == 2 && id2 == 4) {
            ShowSredstva(bathroomTubing);
        } else if (id == 2 && id2 == 5) {
            ShowSredstva(bathroomSmell);
        } else if (id == 2 && id2 == 6) {
            ShowSredstva(bathroomSepticTank);
        } else if (id == 2 && id2 == 7) {
            ShowSredstva(bathroomHands);
        }
        //endregion

        //region office
        else if (id == 3 && id2 == 0) {
            ShowSredstva(officeTechnique);
        } else if (id == 3 && id2 == 1) {
            ShowSredstva(officeSoftFurniture);
        } else if (id == 3 && id2 == 2) {
            ShowSredstva(officeWoodenFurniture);
        } else if (id == 3 && id2 == 3) {
            ShowSredstva(officeMetalFurniture);
        } else if (id == 3 && id2 == 4) {
            ShowSredstva(officeWindows);
        } else if (id == 3 && id2 == 5) {
            ShowSredstva(officeScotch);
        } else if (id == 3 && id2 == 6) {
            ShowSredstva(officeWashing);
        } else if (id == 3 && id2 == 7) {
            ShowSredstva(officeSmell);
        }
        //endregion

        //region obshiyKlinig
        else if (id == 4 && id2 == 0) {
            ShowSredstva(cleaningTechnique);
        } else if (id == 4 && id2 == 1) {
            ShowSredstva(cleaningSoftFurniture);
        } else if (id == 4 && id2 == 2) {
            ShowSredstva(cleaningWoodenFurniture);
        } else if (id == 4 && id2 == 3) {
            ShowSredstva(cleaningMetalFurniture);
        } else if (id == 4 && id2 == 4) {
            ShowSredstva(cleaningWindows);
        } else if (id == 4 && id2 == 5) {
            ShowSredstva(cleaningScotch);
        } else if (id == 4 && id2 == 6) {
            ShowSredstva(cleaningBassein);
        } else if (id == 4 && id2 == 7) {
            ShowSredstva(cleaningWashing);
        } else if (id == 4 && id2 == 8) {
            ShowSredstva(cleaningSmell);
        } else if (id == 4 && id2 == 9) {
            ShowSredstva(cleaningHands);
        }
        //endregion

        //region Remont
        else if (id == 5 && id2 == 0) {
            ShowSredstva(repairHands);
        } else if (id == 5 && id2 == 1) {
            ShowSredstva(repairWindows);
        } else if (id == 5 && id2 == 2) {
            ShowSredstva(repairWashing);
        } else if (id == 5 && id2 == 3) {
            ShowSredstva(repairFloor);
        }
        //endregion

        //hotel

        else if (id == 7 && id2 == 0) {
            ShowSredstva(hotelRoom);
        } else if (id == 7 && id2 == 1) {
            ShowSredstva(hotelBathRoom);
        }

        //hotel
    }

    private void ShowSredstva(String[][] array) {
        int count = array.length;

        for (int i = 0; count > i; i++) {

            int count2 = array[i].length;
            for (int n = 0; count2 > n; n++) {

                if (n == 0) {
                    TableRow tr = (TableRow) View.inflate(this, R.layout.tablerow, null);
                    tv = (TextView) tr.findViewById(R.id.col1);
                    tv.setText(array[i][n]);
                    n++;
                    tv = (TextView) tr.findViewById(R.id.col2);
                    tv.setClickable(true);
                    tv.setTextColor(Color.parseColor("#000000"));
                    tv.setText(array[i][n]);
                    tbMain.addView(tr);
                    tv.setOnTouchListener(this);

                    if (nameProduct != null) {
                        StratRaschet(nameProduct);
                    }

                } else {
                    TableRow tr = (TableRow) View.inflate(this, R.layout.tablerow, null);
                    tv = (TextView) tr.findViewById(R.id.col1);
                    tv.setText("");
                    tv = (TextView) tr.findViewById(R.id.col2);
                    tv.setClickable(true);
                    tv.setTextColor(Color.parseColor("#000000"));
                    tv.setText(array[i][n]);
                    tv.setClickable(true);
                    tbMain.addView(tr);
                    tv.setOnTouchListener(this);
                    if (nameProduct != null) {
                        StratRaschet(nameProduct);
                    }
                }
            }
        }
    }

    private void StratRaschet(String nameProduct) {
        Intent intent = new Intent(activity_klining_problema.this, KliningnfoProductActivity.class);
        intent.putExtra("nameProduct", nameProduct);
        startActivity(intent);
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
        Intent intent = ClickLeftMenu.getIntent(activity_klining_problema.this, item);
        startActivity(intent);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        TextView tv = (TextView) v;
        nameProduct = tv.getText().toString();

        switch (event.getAction()) {
            case MotionEvent.ACTION_UP: // отпускание
                if (nameProduct.equals("Tank Bio")) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "К сожалению расчет по средству Tank Bio не производится.", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    StratRaschet(nameProduct);
                }
                break;
        }

        return true;
    }
}

