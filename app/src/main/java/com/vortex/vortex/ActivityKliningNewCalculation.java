package com.vortex.vortex;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.vortex.vortex.Fragments.KliningCalculation10Fragment;
import com.vortex.vortex.Fragments.KliningCalculation11Fragment;
import com.vortex.vortex.Fragments.KliningCalculation12Fragment;
import com.vortex.vortex.Fragments.KliningCalculation13Fragment;
import com.vortex.vortex.Fragments.KliningCalculation1Fragment;
import com.vortex.vortex.Fragments.KliningCalculation2Fragment;
import com.vortex.vortex.Fragments.KliningCalculation3Fragment;
import com.vortex.vortex.Fragments.KliningCalculation4Fragment;
import com.vortex.vortex.Fragments.KliningCalculation5Fragment;
import com.vortex.vortex.Fragments.KliningCalculation6Fragment;
import com.vortex.vortex.Fragments.KliningCalculation7Fragment;
import com.vortex.vortex.Fragments.KliningCalculation8Fragment;
import com.vortex.vortex.Fragments.KliningCalculation9Fragment;
import com.vortex.vortex.Fragments.KliningCalculationDefaultFragment;
import com.vortex.vortex.Fragments.KliningCalculationProfWashingFragment;
import com.vortex.vortex.Fragments.LaundryBasicToolsFragment;

public class ActivityKliningNewCalculation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager manager;
    private FragmentTransaction transaction;
    private KliningCalculation1Fragment kliningCalculation1Fragment;
    private KliningCalculation2Fragment kliningCalculation2Fragment;
    private KliningCalculation3Fragment kliningCalculation3Fragment;
    private KliningCalculation4Fragment kliningCalculation4Fragment;
    private KliningCalculation5Fragment kliningCalculation5Fragment;
    private KliningCalculation6Fragment kliningCalculation6Fragment;
    private KliningCalculation7Fragment kliningCalculation7Fragment;
    private KliningCalculation8Fragment kliningCalculation8Fragment;
    private KliningCalculation9Fragment kliningCalculation9Fragment;
    private KliningCalculation10Fragment kliningCalculation10Fragment;
    private KliningCalculation11Fragment kliningCalculation11Fragment;
    private KliningCalculation12Fragment kliningCalculation12Fragment;
    private KliningCalculation13Fragment kliningCalculation13Fragment;
    private KliningCalculationDefaultFragment kliningCalculationDefaultFragment;
    private LaundryBasicToolsFragment laundryBasicToolsFragment;
    private KliningCalculationProfWashingFragment kliningCalculationProfWashingFragment;
    private String product;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_klining_new_calculation);
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
        title = getIntent().getStringExtra("title");
        setTitle(product);

        manager = getSupportFragmentManager();
        kliningCalculation1Fragment = new KliningCalculation1Fragment();
        kliningCalculation2Fragment = new KliningCalculation2Fragment();
        kliningCalculation3Fragment = new KliningCalculation3Fragment();
        kliningCalculation4Fragment = new KliningCalculation4Fragment();
        kliningCalculation5Fragment = new KliningCalculation5Fragment();
        kliningCalculation6Fragment = new KliningCalculation6Fragment();
        kliningCalculation7Fragment = new KliningCalculation7Fragment();
        kliningCalculation8Fragment = new KliningCalculation8Fragment();
        kliningCalculation9Fragment = new KliningCalculation9Fragment();
        kliningCalculation10Fragment = new KliningCalculation10Fragment();
        kliningCalculation11Fragment = new KliningCalculation11Fragment();
        kliningCalculation12Fragment = new KliningCalculation12Fragment();
        kliningCalculation13Fragment = new KliningCalculation13Fragment();
        kliningCalculationDefaultFragment = new KliningCalculationDefaultFragment();
        laundryBasicToolsFragment = new LaundryBasicToolsFragment();
        kliningCalculationProfWashingFragment = new KliningCalculationProfWashingFragment();

        Bundle bundle = new Bundle();

        transaction = manager.beginTransaction();
        switch (product) {
            case "DAZE":
                transaction.add(R.id.container, kliningCalculation1Fragment);
                bundle.putDouble("expence", 10);
                break;
            case "TANK FB 36":
                transaction.add(R.id.container, kliningCalculation1Fragment);
                bundle.putDouble("expence", 6);
                break;
            case "TANK FB 48":
                transaction.add(R.id.container, kliningCalculation1Fragment);
                bundle.putDouble("expence", 3);
                break;
            case "MAGIC":
                transaction.add(R.id.container, kliningCalculation1Fragment);
                bundle.putDouble("expence", 5);
                break;
            case "TWIST":
                transaction.add(R.id.container, kliningCalculation1Fragment);
                bundle.putDouble("expence", 5);
                break;
            case "POLEX":
                transaction.add(R.id.container, kliningCalculation1Fragment);
                bundle.putDouble("expence", 5);
                break;
            case "DESTROY":
                transaction.add(R.id.container, kliningCalculation1Fragment);
                bundle.putDouble("expence", 20);
                break;
            case "FORTIS":
                transaction.add(R.id.container, kliningCalculation1Fragment);
                bundle.putDouble("expence", 6);
                break;
            case "BREEZE SPRAY":
                transaction.add(R.id.container, kliningCalculation1Fragment);
                bundle.putDouble("expence", 5);
                break;
            case "TANK LBD 1002/2":
                transaction.add(R.id.container, kliningCalculation1Fragment);
                bundle.putDouble("expence", 3);
                break;
            case "OPTIMA":
                transaction.add(R.id.container, kliningCalculation1Fragment);
                bundle.putDouble("expence", 1);
                break;
            case "KRAFT":
                transaction.add(R.id.container, kliningCalculation1Fragment);
                bundle.putDouble("expence", 5);
                break;
            case "ANTISTICK":
                transaction.add(R.id.container, kliningCalculation1Fragment);
                bundle.putDouble("expence", 5);
                break;
            case "NOVATEC FOAM":
                transaction.add(R.id.container, kliningCalculation1Fragment);
                bundle.putDouble("expence", 40);
                break;
            case "FUNGUS":
                transaction.add(R.id.container, kliningCalculation1Fragment);
                bundle.putDouble("expence", 5);
                break;
            case "WELL":
                transaction.add(R.id.container, kliningCalculation1Fragment);
                bundle.putDouble("expence", 30);
                break;
            case "COMFORT":
                transaction.add(R.id.container, kliningCalculation1Fragment);
                bundle.putDouble("expence", 1);
                break;
            case "COMFORT EXTRA":
                transaction.add(R.id.container, kliningCalculation1Fragment);
                bundle.putDouble("expence", 1);
                break;
            case "TANK FBD 0803/1":
                transaction.add(R.id.container, kliningCalculation1Fragment);
                bundle.putDouble("expence", 5);
                break;
            case "TANK FA18":
                transaction.add(R.id.container, kliningCalculation1Fragment);
                bundle.putDouble("expence", 5);
                break;
            case "NOVATEC":
                transaction.add(R.id.container, kliningCalculation1Fragment);
                bundle.putDouble("expence", 60);
                break;
            case "JOY":
                transaction.add(R.id.container, kliningCalculation2Fragment);
                bundle.putDouble("expence", 1.5);
                break;
            case "JOY SEPT":
                transaction.add(R.id.container, kliningCalculation2Fragment);
                bundle.putDouble("expence", 5);
                break;
            case "FAY":
                transaction.add(R.id.container, kliningCalculation2Fragment);
                bundle.putDouble("expence", 0.5);
                break;
            case "SAPO":
                transaction.add(R.id.container, kliningCalculation2Fragment);
                bundle.putDouble("expence", 1.5);
                break;
            case "BREEZE":
                transaction.add(R.id.container, kliningCalculation3Fragment);
                break;
            case "FUMIGEL":
                transaction.add(R.id.container, kliningCalculation3Fragment);
                break;
            case "LATRIN BIO(ЖИДКИЙ)":
                transaction.add(R.id.container, kliningCalculation4Fragment);
                break;
            case "LATRIN":
                transaction.add(R.id.container, kliningCalculation5Fragment);
                break;
            case "DRAFT":
                transaction.add(R.id.container, kliningCalculation6Fragment);
                break;
            case "TANK BIO":
                transaction.add(R.id.container, kliningCalculation7Fragment);
                break;
            case "TANK LBD 0107/1":
                if (title.contains("МЕШОЧКОВ")) {
                    transaction.add(R.id.container, kliningCalculation12Fragment);
                } else {
                    transaction.add(R.id.container, kliningCalculation8Fragment);
                    bundle.putDouble("expenseWashing", 5);
                    bundle.putDouble("expenseSoak", 20);
                }
                break;
            case "MARVEL":
                transaction.add(R.id.container, kliningCalculation8Fragment);
                bundle.putDouble("expenseWashing", 1);
                bundle.putDouble("expenseSoak", 4.5);
                break;
            case "FOG":
                transaction.add(R.id.container, kliningCalculation9Fragment);
                break;
            case "BLANK":
                transaction.add(R.id.container, kliningCalculation10Fragment);
                bundle.putDouble("expence", 1.5);
                break;
            case "DEBLANK":
                transaction.add(R.id.container, kliningCalculation10Fragment);
                bundle.putDouble("expence", 0.6);
                break;
            case "OPTIMA GEL":
                transaction.add(R.id.container, kliningCalculation11Fragment);
                break;
            case "BLOCK":
                transaction.add(R.id.container, kliningCalculation13Fragment);
                break;
            case "MIX ENERGY":
                transaction.add(R.id.container, laundryBasicToolsFragment);
                bundle.putString("expence_name", "Расход на 1кг. белья, мл.(Рекомендовано 8-30)");
                bundle.putDouble("expense", 0);
                break;
            case "MIX BASIC":
                transaction.add(R.id.container, laundryBasicToolsFragment);
                bundle.putString("expence_name", "Расход на 1кг. белья, мл.(Рекомендовано 8-30)");
                bundle.putDouble("expense", 0);
                break;
            case "MIX ACTIV":
                transaction.add(R.id.container, laundryBasicToolsFragment);
                bundle.putString("expence_name", "Расход на 1кг. белья, мл.(Рекомендовано 8-30)");
                bundle.putDouble("expense", 0);
                break;
            case "MIX FERMENT":
                transaction.add(R.id.container, laundryBasicToolsFragment);
                bundle.putString("expence_name", "Расход на 1кг. белья, мл.(Рекомендовано 8-30)");
                bundle.putDouble("expense", 0);
                break;
            case "MIX SENSITIV":
                transaction.add(R.id.container, laundryBasicToolsFragment);
                bundle.putString("expence_name", "Расход на 1кг. белья, мл.(Рекомендовано 8-30)");
                bundle.putDouble("expense", 0);
                break;

            case "MIX FORTE PLUS":
                transaction.add(R.id.container, laundryBasicToolsFragment);
                bundle.putString("expence_name", "Расход на 1кг. белья, мл.(Рекомендовано 3-9)");
                bundle.putDouble("expense", 0);
                break;
            case "MIX POWER PLUS":
                transaction.add(R.id.container, laundryBasicToolsFragment);
                bundle.putString("expence_name", "Расход на 1кг. белья, мл.(Рекомендовано 0,5-20)");
                bundle.putDouble("expense", 0);
                break;
            case "MIX INTENSIV PLUS":
                transaction.add(R.id.container, laundryBasicToolsFragment);
                bundle.putString("expence_name", "Расход на 1кг. белья, мл.(Рекомендовано 0,5-25)");
                bundle.putDouble("expense", 0);
                break;
            case "MIX ENZYM PLUS":
                transaction.add(R.id.container, laundryBasicToolsFragment);
                bundle.putString("expence_name", "Расход на 1кг. белья, мл.(Рекомендовано 5-20)");
                bundle.putDouble("expense", 0);
                break;
            case "MIX COLOR PLUS":
                transaction.add(R.id.container, laundryBasicToolsFragment);
                bundle.putString("expence_name", "Расход на 1кг. белья, мл.(Рекомендовано 0,5-25)");
                bundle.putDouble("expense", 0);
                break;
            case "MIX HYPO":
                transaction.add(R.id.container, laundryBasicToolsFragment);
                bundle.putString("expence_name", "Расход на 1кг. белья, мл.");
                bundle.putDouble("expense", 3.5);
                break;
            case "MIX OXYDEZ":
                transaction.add(R.id.container, laundryBasicToolsFragment);
                bundle.putString("expence_name", "Расход на 1кг. белья, мл.");
                bundle.putDouble("expense", 4);
                break;
            case "MIX OXY":
                transaction.add(R.id.container, laundryBasicToolsFragment);
                bundle.putString("expence_name", "Расход на 1кг. белья, мл.");
                bundle.putDouble("expense", 7);
                break;
            case "MIX SOFT":
                transaction.add(R.id.container, laundryBasicToolsFragment);
                bundle.putString("expence_name", "Расход на 1кг. белья, мл.");
                bundle.putDouble("expense", 6);
                break;
            case "MIX ZERO":
                transaction.add(R.id.container, laundryBasicToolsFragment);
                bundle.putString("expence_name", "Расход на 1кг. белья, мл.");
                bundle.putDouble("expense", 2);
                break;
            case "Профессиональная стирка":
                transaction.add(R.id.container, kliningCalculationProfWashingFragment);
                break;
            default:
                transaction.add(R.id.container, kliningCalculationDefaultFragment);
        }
        kliningCalculation1Fragment.setArguments(bundle);
        kliningCalculation2Fragment.setArguments(bundle);
        kliningCalculation8Fragment.setArguments(bundle);
        kliningCalculation10Fragment.setArguments(bundle);
        laundryBasicToolsFragment.setArguments(bundle);
        transaction.commit();
    }

    public void onClickIcon(View view) {

        Intent intent = new Intent(this, Main2Activity.class);
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
        Intent intent = ClickLeftMenu.getIntent(ActivityKliningNewCalculation.this, item);
        startActivity(intent);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
