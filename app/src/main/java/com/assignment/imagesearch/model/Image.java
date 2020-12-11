package com.assignment.imagesearch.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class Image implements Parcelable {

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        public Image[] newArray(int size) {
            return new Image[size];
        }
    };
    @SerializedName("datetime")
    int datetime;
    @SerializedName("description")
    String description;
    @SerializedName("id")
    String id;
    @SerializedName("link")
    String link;
    @SerializedName("title")
    String title;
    @SerializedName("type")
    String type;

    Image(Parcel in) {
        this.datetime = in.readInt();
        this.description = in.readString();
        this.id = in.readString();
        this.link = in.readString();
        this.title = in.readString();
        this.type = in.readString();
    }


    public Image(int datetime, String description, String id, String link, String title, String type) {
        this.datetime = datetime;
        this.description = description;
        this.id = id;
        this.link = link;
        this.title = title;
        this.type = type;
    }

    public int getDatetime() {
        return datetime;
    }

    public void setDatetime(int datetime) {
        this.datetime = datetime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(this.datetime);
        parcel.writeString(this.description);
        parcel.writeString(this.id);
        parcel.writeString(this.link);
        parcel.writeString(this.title);
        parcel.writeString(this.type);
    }
}
