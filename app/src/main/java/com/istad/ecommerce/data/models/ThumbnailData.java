package com.istad.ecommerce.data.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ThumbnailData implements Serializable {

    @SerializedName("data")
    private DataAttribute data;

    public DataAttribute getData() {
        return data;
    }

    public void setData(DataAttribute data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ThumbnailData{" +
                "data=" + data +
                '}';
    }
}
