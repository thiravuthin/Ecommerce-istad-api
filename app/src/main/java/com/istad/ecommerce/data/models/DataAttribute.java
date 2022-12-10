package com.istad.ecommerce.data.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataAttribute implements Serializable {
    @SerializedName("id")
    private int id;
    @SerializedName("attributes")
    private Thumbnail thumbnail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return "DataAttribute{" +
                "id=" + id +
                ", thumbnail=" + thumbnail +
                '}';
    }
}
