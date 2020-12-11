package com.assignment.imagesearch.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
@Entity(tableName = "Comments")
public class ImageCommentEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "comment")
    @SerializedName("comment")
    public String comment;

    @ColumnInfo(name = "dataId")
    @SerializedName("dataId")
    public String dataId;

    @ColumnInfo(name = "timestamp")
    @SerializedName("timestamp")
    public long timestamp;

    public ImageCommentEntity(int id, String comment, String dataId, long timestamp) {
        this.id = id;
        this.comment = comment;
        this.dataId = dataId;
        this.timestamp = timestamp;
    }
}
