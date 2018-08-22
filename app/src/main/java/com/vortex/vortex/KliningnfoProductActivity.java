package com.vortex.vortex;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class KliningnfoProductActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String product;
    private TextView tvAppointment;
    private TextView tvInstruction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kliningnfo_product);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        product = getIntent().getStringExtra("nameProduct");
        setTitle(product);
        product = product.toUpperCase();

        tvAppointment = findViewById(R.id.tvAppointment);
        tvInstruction = findViewById(R.id.tvInstruction);

        switch (product) {
            case "DAZE":
                tvAppointment.setText("Жидкое моющее средство для обработки внешних и внутренних поверхностей технологического оборудования (коптильных камер, жаровен, грилей, духовок, плит, пароконвектоматов и т.д.) от белково-жировых пригаров и смол на предприятиях пищевой, пивной и мясоперерабатывающей промышленностей. Хорошо растворимо в воде. Средство обладает высоким смачивающим, очищающим, эмульгирующим действием. Идеально подходит для обработки с помощью пенообразующего оборудования (пеногенераторы, стационарные и мобильные пенные станции)");
                tvInstruction.setText("Средство в триггерной упаковке (0,6 кг.) готово к применению, не требует разбавления. Равномерно нанести на обрабатываемую поверхность. Выдержать 1-10 минут. В случае сильных загрязнений потереть губкой или щеткой. Смыть средство водой\n" +
                        "При использовании продукта в фасовке 1, 3 и 6 кг. концентрация, экспозиция средства подбирается индивидуально, в зависимости от загрязнения.");
                break;
            default:
                tvAppointment.setText("Средство еще не обработано");
                tvInstruction.setText("Средство еще не обработано");

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
        Intent intent = ClickLeftMenu.getIntent(KliningnfoProductActivity.this, item);
        startActivity(intent);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onclickCalculation(View view) {
        Intent intent = new Intent(KliningnfoProductActivity.this, ActivityKliningNewCalculation.class);
        intent.putExtra("nameProduct", product);
        startActivity(intent);
    }
}
