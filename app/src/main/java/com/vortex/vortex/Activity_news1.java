package com.vortex.vortex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import com.vortex.vortex.models.newsModel;

public class Activity_news1 extends AppCompatActivity {

    newsModel model = new newsModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news1);

        //model = getIntent().getParcelableExtra("model");
        newsModel model = (newsModel) getIntent().getParcelableExtra("newsModel");
        String headerNews = model.getHeader();
        WebView webView = (WebView) findViewById(R.id.wvNews);
        setTitle(headerNews);

        TextView tvDate = null;
        TextView tvNews;
        TextView tvHeader;

        tvDate = (TextView) findViewById(R.id.tvDate);
        tvNews = (TextView) findViewById(R.id.tvNews);
        tvHeader = (TextView) findViewById(R.id.tvHeader);

        tvDate.setText(model.getDate());
        tvHeader.setText(headerNews);
        //tvNews.setText(model.getNews());
        webView.loadDataWithBaseURL(null, model.getNews(), "text/html", "utf-8", null);

    }
}
