package com.assignment.imagesearch.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImageSearchResponse {
    @SerializedName("data")
    public List<Data> imageData;
    @SerializedName("status")
    public int status;
    @SerializedName("success")
    public boolean success;
}
