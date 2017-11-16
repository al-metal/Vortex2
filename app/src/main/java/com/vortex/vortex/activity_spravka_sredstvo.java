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
            "Щелочное низкопенное моющее средство на основе четвертичных аммониевых соединений (ЧАС)",

            "Средство для обработки вымени до доения на основе йода 0,25% готовый раствор",
            "Средство для обработки вымени до доения на основе йода 0,5%",
            "Средство для обработки вымени после доения на основе йода 0,25%",
            "Средство для обработки вымени после доения на основе йода 0,5%",
            "Щелочное беспенное дезинфицирующее моющее средство для воды повышенной жесткости",
            "Щелочное беспенное дезинфицирующее моющее средство для воды мягкой и средней жесткости",
            "Щелочное беспенное дезинфицирующее моющее средство для воды средней жесткости",
            "Щелочное беспенное дезинфицирующее моющее средство для воды любой жесткости",
            "Добавка к медному и цинковому купоросу для копытных ванн",
            "Моющее и дезинфицирующее средство для копыт",
            "Средство для обработки вымени после доения с экстратом ромашки",
            "Средство для обработки вымени после доения на основе хлоргексидина 0,25%",
            "Средство для обработки вымени до доения с экстрактом алоэ вера",
            "Дезинфицирующее средство для объектов ветеринароного надзора и  профилактики инфекционных болезней животных",
            "Средство для обработки вымени после доения на основе молочной кислоты",
            "Средство для обработки вымени после доения на основе хлоргексидина 0,5%",
            "Кислотное моющее средство для воды повышенной жесткости",
            "Кислотное моющее средство для воды средней жесткости",
            "Кислотное моющее средство для воды мягкой и средней жесткости",
            "Кислотное моющее средство для воды любой жесткости",
            "Средство для обработки вымени после доения на основе молочной кислоты",
            "Средство для обработки вымени до доения на основе хлоргексидина",
            "Экспресс-тест для определения содержания соматических клеток в сыром молоке",
            "Добавка на основе комплекса органических кислот",
            "Средство для обработки вымени до доения на основе молочной кислоты",

            "Высококонцентрированное средство для бесконтактной мойки класс суперпремиум, для воды высокой жесткости",
            "Жидкость незамерзающая для стеклоомывателей",
            "Средство для удаления мошек, тополиных почек, смол деревьев",
            "Размораживатель стекол и замков",
            "Концентрированное слабощелочное средство для профессиональной деликатной мойки автотранспорта",
            "Высокопенное средство для бесконтактной мойки автомобиля на мойках самообслуживания",
            "Высококонцентрированное средство для бесконтактной мойки класс премиум, для воды средней жесткости",
            "Двухкомпонентное средство для бесконтактной мойки класс премиум, для воды высокой жесткости",
            "Средство для ручной мойки автомобиля",
            "Средство для ручной мойки автомобиля с грязезащитным и водоотталкивающим эффектом",
            "Средство для ручной мойки автомобиля с усиленным блеском",
            "Очиститель кузова универсальный",
            "Высококонцентрированное средство для бесконтактной мойки грузового и легкового транспорта класс стандарт, для воды высокой жесткости",
            "Средство для бесконтактной мойки класс стандарт, для воды средней жесткости",
            "Средство для бесконтактной мойки класс стандарт, цветная пена для воды средней жесткости",
            "Очиститель двигателя",
            "Защитное водоотталкивающее нанопокрытие для стекол",
            "Нано-средство для сушки, блеска, консервации автомобиля",
            "Наношампунь для ручной обработки автомобиля",
            "Средство для бесконтактной мойки класс эконом, для воды высокой жесткости",
            "Активное кислотное средство для очистки всех типов колесных дисков",
            "Матовая полироль-очиститель для пластиковых, виниловых и кожаных изделий",
            "Глянцевая полироль-очиститель для пластиковых, виниловых и кожаных изделий",
            "Средство для бесконтактной мойки класс премиум для воды средней жесткости",
            "Очиститель - кондиционер для кожи",
            "Чернитель резины",
            "Чернитель резины на основе силикона",
            "Очищающая паста для рук",
            "Средство для бесконтактной мойки класс премиум, для воды высокой жесткости",
            "Силиконовая смазка",
            "Низкопенное средство для бесконтактной мойки автомобиля на мойках самообслуживания",
            "Универсальное средство для химчистки салона автомобиля",
            "Средство для бесконтактной мойки класс эконом, для воды средней жесткости",
            "Средство для бесконтактной мойки класс эконом, цветная пена для воды средней жесткости",
            "Воск для кузова",
            "Воск для кузова",
            "Средство для бесконтактной мойки класс стандарт, для воды высокой жесткости",
            "Набор автокосметики: химчистка салона - Tantum, полироль - Politura, губка и микрофибра",
            "Средство для бесконтактной мойки класс эконом минус, для воды средней жесткости",
            "Стеклоочиститель универсальный",
            "Автошампунь для бесконтактной мойки автомобилей",

            "Очиститель от следов скотча, наклеек, маркера, жевательной резинки",
            "Моющее средство для посудомоечных машин",
            "Нейтрализатор запаха. Ароматы: апельсин, кожа, кофе",
            "Средство для сантехники",
            "Средство для мытья пола нейтральное",
            "Средство для мытья пола щелочное",
            "Средство для обезжиривания и удаления нагара",
            "Ополаскиватель для посудомоечных машин",
            "Кислотное низкопенное моющее средство для уборки после строительства и ремонта",
            "Средство для канализации",
            "Мыло пенка",
            "Нейтрализатор запаха для сухого тумана. Ароматы: антитабак, корица, апельсин",
            "Индустриальный обезжириватель",
            "Чистящий гель для сантехники с дезинфицирующим и отбеливающим эффектом",
            "Жидкое мыло эконом. Ароматы: апельсин, вишня, яблока, без цвета и запаха",
            "Жидкое мыло с перламутром эконом",
            "Антибактериальное жидкое мыло (кожный антисептик)",
            "Очиститель - кондиционер для кожи",
            "Концентрированное средство для биотуалетов на химической основе",
            "Средство для септика на биологической основе",
            "Очиститель стекол",
            "Средство для мытья посуды",
            "Низкопенный очиститель ковровых покрытий",
            "Пенный очиститель ковровых покрытий",
            "Универсальное пенное моющее средство",
            "Универсальное гелеобразное пенное моющее средство",
            "Моющее дезинфицирующее средство для бани и сауны",
            "Очиститель - полироль для мебели",
            "Универсальный очиститель"
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
            "lbd_1002_2.jpg",

            "algalit.jpg",
            "algalit_50.jpg",
            "algavit_25.jpg",
            "algavit_50.jpg",
            "biotec.jpg",
            "biotec_m.jpg",
            "biotec_c.jpg",
            "biotec_super.jpg",
            "desimix.jpg",
            "desitub.jpg",
            "ecovit.jpg",
            "elovit.jpg",
            "fitolit.jpg",
            "forbicid.jpg",
            "imovit.jpg",
            "kliovit.jpg",
            "ksilan.jpg",
            "ksilan_k.jpg",
            "ksilan_m.jpg",
            "ksilan_super.jpg",
            "lactovit.jpg",
            "priolit.jpg",
            "somatest.jpg",
            "supracid.jpg",
            "violit.jpg",

            "ace.jpg",
            "apex_10.jpg",
            "debug_5.jpg",
            "defroster.jpg",
            "delicate_5.jpg",
            "diy.jpg",
            "dozex.jpg",
            "guru.jpg",
            "hands_5.jpg",
            "hands_hm.jpg",
            "hands_shine_5.jpg",
            "loco.jpg",
            "magnat.jpg",
            "master.jpg",
            "master_tone.jpg",
            "mobile.jpg",
            "nanex.jpg",
            "nano_finish.jpg",
            "nano_next.jpg",
            "novice_5.jpg",
            "orbis_5.jpg",
            "politura_5.jpg",
            "politura_gloss_5.jpg",
            "profy.jpg",
            "propella_5.jpg",
            "rotae_5.jpg",
            "rotaevis_5.jpg",
            "sapo.jpg",
            "senza.jpg",
            "silicone_5.jpg",
            "solo.jpg",
            "tantum.jpg",
            "tiro.jpg",
            "tiro_tone.jpg",
            "tutela_cherry.jpg",
            "tutela_fast_5.jpg",
            "tutor.jpg",
            "twin.jpg",
            "unior.jpg",
            "witrum_5.jpg",
            "k_mini_all.jpg",

            "antistick.jpg",
            "blank_1.jpg",
            "block_apelsin.jpg",
            "breez_05.jpg",
            "comfort_1.jpg",
            "comfort_extra_1.jpg",
            "daze.jpg",
            "deblank_1.jpg",
            "destroy_1.jpg",
            "draft_1.jpg",
            "fay_5.jpg",
            "", //фог
            "fortis_1.jpg",
            "fumigel_1.jpg",
            "joy_orange_1_pet.jpg",
            "joy_platinum_1_pet.jpg",
            "joy_sept_tea_pet.jpg",
            "kraft05.jpg",
            "latrin.jpg",
            "latrin_bio.jpg",
            "magic.jpg",
            "marvel_lemon_1_pet.jpg",
            "novatec_1.jpg",
            "novatec_foam_1.jpg",
            "optima.jpg",
            "optima_gel.jpg",
            "sauna.jpg",
            "twist_05.jpg",
            "well_05.jpg"
    };

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

        String str = "<html><head></head><style>.leftimg {float:left; margin: 7px 7px 7px 0; }</style><body><H1 align=\"center\">" + name + "</H1><P><img height=\"150dp\" src=\"file:///android_res/raw/" + img + "\" class=\"leftimg\"> " + desc + "</P></body></html>";

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

        Intent intent;
        intent = ClickLeftMenu.getIntent(activity_spravka_sredstvo.this, id);
        startActivity(intent);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onClickWebSite(View view) {
        Intent intent = ClickLeftMenu.getIntentWebSite();
        startActivity(intent);
    }
}
