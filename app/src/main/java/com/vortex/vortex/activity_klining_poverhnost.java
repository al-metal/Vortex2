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
import android.widget.TableRow;
import android.widget.TextView;

public class activity_klining_poverhnost extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, TextView.OnClickListener {

    int id;

    TableLayout tl;
    LinearLayout llmain;
    int length;
    String title;
    String titleProblemNew;
    String[] array;
    String nameProduct;

    String[] kuhnya = {"ПОСУДОМОЕЧНАЯ МАШИНА", "РУЧНАЯ МОЙКА ПАСУДЫ, ЗАМАЧИВАНИЕ, ОТБЕЛИВАНИЕ", "КОПТИЛЬНАЯ КАМЕРА, ПЛИТА, ГРИЛЬ, ДУХОВКА, МИКРОВОЛНОВАЯ ПЕЧЬ", "ХОЛОДИЛЬНИК", "ДЕЗИНФЕКЦИЯ", "СТИРКА ТВОРОЖНЫХ И КРЕМОВЫХ МЕШОЧКОВ И ДРУГИХ ТЕКСТИЛЬНЫХ МАТЕРИАЛОВ", "ЖИРОУЛОВИТЕЛИ",
            "ТРУБЫ", "УСТРАНЕНИЕ ЗАПАХОВ", "МОЙКА И ЧИСТКА ПОМЕЩЕНИЯ", "ГИГИЕНА РУК"};
    String[] sanusel = {"УНИТАЗ, БИДЕ, ПИССУАР, РАКОВИНА", "ВАННА, ДУШЕВАЯ КАБИНА", "ОКНА, СТЕКЛА, ЗЕРКАЛА", "МОЙКА И ЧИСТКА", "ТРУБЫ",
            "УСТРАНЕНИЕ ЗАПАХОВ", "БИОТУАЛЕТ И СЕПТИК", "ГИГИЕНА РУК"};
    String[] office = {"ОРГТЕХНИКА", "МЯГКАЯ МЕБЕЛЬ", "ДЕРЕВЯННАЯ МЕБЕЛЬ", "МЕБЕЛЬ ИЗ МЕТАЛЛА", "ОКНА, СТЕКЛА, ЗЕРКАЛА", "УДАЛЕНИЕ ЖЕВАТЕЛЬНЫХ РЕЗИНОК, СКОТЧА, СЛЕДОВ ОТ МАРКЕРА И ФЛОМАСТЕРА", "МОЙКА И ЧИСТКА ПОМЕЩЕНИЯ", "УСТРАНЕНИЕ ЗАПАХОВ"};
    String[] obshiyKlining = {"ОРГТЕХНИКА", "МЯГКАЯ МЕБЕЛЬ", "ДЕРЕВЯННАЯ МЕБЕЛЬ", "МЕБЕЛЬ ИЗ МЕТАЛЛА", "ОКНА, СТЕКЛА, ЗЕРКАЛА", "УДАЛЕНИЕ ЖЕВАТЕЛЬНЫХ РЕЗИНОК, СКОТЧА, СЛЕДОВ ОТ МАРКЕРА И ФЛОМАСТЕРА", "ЧИСТКА БАССЕЙНОВ", "МОЙКА И ЧИСТКА ПОМЕЩЕНИЯ", "УСТРАНЕНИЕ ЗАПАХОВ", "ГИГИЕНА РУК"};
    String[] remont = {"ГИГИЕНА РУК", "ОКНА, СТЕКЛА, ЗЕРКАЛА", "МОЙКА И ЧИСТКА ПОМЕЩЕНИЯ", "НАПОЛЬНЫЕ ПОКРЫТИЯ"};
    String[][] PromKlining = {{"Следы от нефтепродуктов, жировых и масляных загрязнений на оборудовании, полах и стенах", "Fortis"},
            {"Следы от нефтепродуктов и масел на руках", "Sapo"},
            {"Очистка поверхностей после пожара", "Daze"},
            {"Мойка полов", "Comfort", "Comfort Extra"},
            {"Мойка стен", "Well", "Optima", "Optima Gel"},
            {"Мойка стекол, зеркал", "Magic"},
            {"Плесень, грибок", "Fumigel", "Fungus"},
            {"Устранение запахов", "Block", "Fog"}};
    String[] hotel = {"КОМНАТА", "САНУЗЕЛ"};
    String[] laundry = {"БАЗОВЫЕ СРЕДСТВА", "УСИЛИТЕЛИ", "ОТБЕЛИВАТЕЛИ", "КОНДИЦИОНЕРЫ", "НЕЙТРОЛИЗАТОРЫ", "ПЯТНОВЫВОДИТЕЛИ", "КОМПЛЕКСНАЯ СТИРКА"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_klining_poverhnost);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        id = getIntent().getExtras().getInt("id");
        title = getIntent().getStringExtra("title");
        tl = findViewById(R.id.tlMain);
        llmain = findViewById(R.id.llmain);

        setTitle(title);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
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
        } else if (id == 3) {
            CreateViews(office);
        } else if (id == 4) {
            CreateViews(obshiyKlining);
        } else if (id == 5) {
            CreateViews(remont);
        } else if (id == 6) {
            ShowSredstva(PromKlining);
        } else if (id == 7) {
            CreateViews(hotel);
        } else if (id == 8) {
            CreateViews(laundry);
        }
    }

    private void CreateViews(String[] array) {
        this.array = array;
        length = array.length;
        for (int i = 0; length > i; i++) {
            TableRow tr = (TableRow) View.inflate(this, R.layout.item_cleanbox_problem_tools, null);
            TextView tv = tr.findViewById(R.id.col1);
            tv.setTextColor(Color.parseColor("#000000"));
            tv.setText(array[i]);
            tv.setId(i);
            tv.setOnClickListener(oclBtnCancel);

            llmain.addView(tr);
        }
    }

    private void ShowSredstva(String[][] array) {
        int count = array.length;

        for (int i = 0; count > i; i++) {

            int count2 = array[i].length;
            for (int n = 0; count2 > n; n++) {

                if (n == 0) {
                    TableRow tr = (TableRow) View.inflate(this, R.layout.item_cleanbox_problem_tools, null);
                    TextView tv = tr.findViewById(R.id.col1);
                    tv.setText(array[i][n]);
                    n++;
                    tv = tr.findViewById(R.id.col2);
                    tv.setTextColor(Color.parseColor("#000000"));
                    tv.setText(array[i][n]);
                    tv.setClickable(true);
                    tl.addView(tr);
                    tv.setOnClickListener(this);

                } else {
                    TableRow tr = (TableRow) View.inflate(this, R.layout.item_cleanbox_problem_tools, null);
                    TextView tv = tr.findViewById(R.id.col1);
                    tv.setText("");
                    tv = tr.findViewById(R.id.col2);
                    tv.setTextColor(Color.parseColor("#000000"));
                    tv.setText(array[i][n]);
                    tv.setClickable(true);
                    tl.addView(tr);
                    tv.setOnClickListener(this);
                }
            }
        }
    }

    OnClickListener oclBtnCancel = new OnClickListener() {
        @Override
        public void onClick(View v) {
            titleProblemNew = GetTitleProble(array[v.getId()]);
            Intent intent = new Intent(activity_klining_poverhnost.this, activity_klining_problema.class);

            if (id == 8 && titleProblemNew.contains("Комплексная стирка")) {
                intent = new Intent(activity_klining_poverhnost.this, ActivityKliningNewCalculation.class);
                intent.putExtra("nameProduct", "Профессиональная стирка");
            } else {
                intent.putExtra("id", id);
                intent.putExtra("id2", v.getId());
                intent.putExtra("title", titleProblemNew);
            }
            startActivity(intent);
        }
    };

    private String GetTitleProble(String s) {

        s = s.toLowerCase();
        String firstChar = String.valueOf(s.charAt(0));
        firstChar = firstChar.toUpperCase();
        s = firstChar + s.substring(1);

        return s;
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
        Intent intent = ClickLeftMenu.getIntent(activity_klining_poverhnost.this, item);
        startActivity(intent);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onClick1(View view) {
        Intent intent = new Intent(activity_klining_poverhnost.this, activity_klining_problema.class);
        startActivity(intent);
    }

    private void StratRaschet(String nameProduct) {
        Intent intent = new Intent(activity_klining_poverhnost.this, KliningnfoProductActivity.class);
        intent.putExtra("nameProduct", nameProduct);
        intent.putExtra("title", title);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        TextView tv = (TextView) v;
        nameProduct = tv.getText().toString();
        StratRaschet(nameProduct);
    }

    public void onClickIcon(View view) {

        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
}
