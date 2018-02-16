package com.vortex.vortex.models;

/**
 * Created by KonyshevAM on 14.02.2018.
 */

public class newsModel {
    private String date;
    private String preview;
    private String news;
    private String header;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
