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

import com.vortex.vortex.Fragments.KliningCalculation1Fragment;
import com.vortex.vortex.Fragments.KliningCalculationDefaultFragment;

public class ActivityKliningNewCalculation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager manager;
    private FragmentTransaction transaction;
    private KliningCalculation1Fragment kliningCalculation1Fragment;
    private KliningCalculationDefaultFragment kliningCalculationDefaultFragment;
    private String product;

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
        setTitle(product);

        manager = getSupportFragmentManager();
        kliningCalculation1Fragment = new KliningCalculation1Fragment();
        kliningCalculationDefaultFragment = new KliningCalculationDefaultFragment();

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
            default:
                transaction.add(R.id.container, kliningCalculationDefaultFragment);
        }
        kliningCalculation1Fragment.setArguments(bundle);
        transaction.commit();
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
