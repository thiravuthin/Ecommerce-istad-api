package com.istad.ecommerce.data.api.response;

import com.google.gson.annotations.SerializedName;
import com.istad.ecommerce.data.api.Data;

public class UpdateResponse {

    @SerializedName("id")
    private int id;
    @SerializedName("data")
    private Data data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "UpdateResponse{" +
                "id=" + id +
                ", data=" + data +
                '}';
    }
}
