package com.example.sunilm.nycnewsapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sunilm on 1/23/2018.
 */

public class NewsClass implements Parcelable {
    String title;
    String description;
    String link;
    String pubDate;
    String Content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public NewsClass(String title, String content, String description) {
        this.title = title;
        Content = content;
        this.description = description;
    }



    public NewsClass() {
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        // String title;
        //String description;
        //String Content;
        parcel.writeString(this.title);
        parcel.writeString(this.description);
        parcel.writeString(this.link);
        parcel.writeString(this.pubDate);
        parcel.writeString(this.Content);
    }


    public static final Parcelable.Creator<NewsClass> CREATOR
            = new Parcelable.Creator<NewsClass>() {
        public NewsClass createFromParcel(Parcel in) {
            return new NewsClass(in);
        }
        public NewsClass[] newArray(int size) {
            return new NewsClass[size];
        }
    };
    private NewsClass(Parcel in) {
        this.title = in.readString();
        this.description = in.readString();
        this.link = in.readString();
        this.pubDate = in.readString();
        this.Content = in.readString();
    }

    @Override
    public String toString() {
        return "NewsClass{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                ", pubDate='" + pubDate + '\'' +
                ", Content='" + Content + '\'' +
                '}';
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }
}

