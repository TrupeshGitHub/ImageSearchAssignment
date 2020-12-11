package com.assignment.imagesearch.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {
    @SerializedName("datetime")
    public int dateTime;
    @SerializedName("description")
    public String description;
    @SerializedName("id")
    public String id;
    @SerializedName("images")
    public List<Image> images;
    @SerializedName("title")
    public String title;
}
