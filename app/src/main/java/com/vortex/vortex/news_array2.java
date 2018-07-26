package com.vortex.vortex;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.vortex.vortex.Adapter.FeedAdapter;
import com.vortex.vortex.Common.HTTPDataHandler;
import com.vortex.vortex.Model.RSSObject;
import com.vortex.vortex.models.newsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class news_array2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ProgressBar pgLoadNews;
    TextView tvProgressBerText;
    android.widget.Toolbar toolbar;
    RecyclerView recyclerView;
    RSSObject rssObject;

    private final String RSSLink = "https://pk-vortex.ru/news/rss/";
    private final String RSSToJsonApi = "https://api.rss2json.com/v1/api.json?rss_url=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_array2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Новости");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        tvProgressBerText = (TextView) findViewById(R.id.tvProgressBerText);
        pgLoadNews = (ProgressBar) findViewById(R.id.pgLoadNews);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        LoadRSS();
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
        Intent intent = ClickLeftMenu.getIntent(news_array2.this, item);
        startActivity(intent);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void LoadRSS() {
        @SuppressLint("StaticFieldLeak") AsyncTask<String, String, String> loadRSSAsync = new AsyncTask<String, String, String>() {

            @Override
            protected void onPostExecute(String s) {
                pgLoadNews.setVisibility(View.GONE);
                tvProgressBerText.setVisibility(View.GONE);
                if (s == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(news_array2.this);
                    builder.setTitle("Ошибка")
                            .setMessage(R.string.noneINTERNET)
                            //.setIcon(R.drawable.ic_android_cat)
                            .setCancelable(false)
                            .setNegativeButton("ОК",
                                    (dialog, id) -> {
                                        //dialog.cancel();
                                        Intent intent = new Intent(news_array2.this, Main2Activity.class);
                                        startActivity(intent);
                                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                } else {
                    rssObject = new Gson().fromJson(s, RSSObject.class);
                    FeedAdapter adapter = new FeedAdapter(rssObject, getBaseContext());
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            protected String doInBackground(String... strings) {
                String result;
                HTTPDataHandler httpDataHandler = new HTTPDataHandler();
                result = httpDataHandler.getHTTPData(strings[0]);
                return result;
            }
        };
        StringBuilder url_get_data = new StringBuilder(RSSToJsonApi);
        url_get_data.append(RSSLink);
        loadRSSAsync.execute(url_get_data.toString());
    }
}
