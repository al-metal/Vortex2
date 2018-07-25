package com.vortex.vortex.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class RSSObject  implements Parcelable
{
    public String status;
    public Feed feed;
    public List<Item> items;

    protected RSSObject(Parcel in) {
        status = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RSSObject> CREATOR = new Creator<RSSObject>() {
        @Override
        public RSSObject createFromParcel(Parcel in) {
            return new RSSObject(in);
        }

        @Override
        public RSSObject[] newArray(int size) {
            return new RSSObject[size];
        }
    };

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Feed getFeed() {
        return feed;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public RSSObject(String status, Feed feed, List<Item> items) {

        this.status = status;
        this.feed = feed;
        this.items = items;
    }
}
