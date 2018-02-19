package com.vortex.vortex.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by KonyshevAM on 14.02.2018.
 */

public class newsModel implements Parcelable {
    private String date;
    private String preview;
    private String news;
    private String header;
    private int id;

    public newsModel(Parcel in) {
        date = in.readString();
        preview = in.readString();
        news = in.readString();
        header = in.readString();
        id = in.readInt();
    }

    public newsModel(int id, String header, String date, String preview, String news) {
        this.id = id;
        this.date = date;
        this.header = header;
        this.preview = preview;
        this.news = news;
    }

    public static final Creator<newsModel> CREATOR = new Creator<newsModel>() {
        @Override
        public newsModel createFromParcel(Parcel in) {
            return new newsModel(in);
        }

        @Override
        public newsModel[] newArray(int size) {
            return new newsModel[size];
        }
    };

    public newsModel() {

    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(preview);
        dest.writeString(news);
        dest.writeString(header);
        dest.writeInt(id);
    }
}
