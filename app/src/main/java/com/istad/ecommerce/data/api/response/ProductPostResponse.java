package com.istad.ecommerce.data.api.response;

import com.google.gson.annotations.SerializedName;
import com.istad.ecommerce.data.api.Data;

public class ProductPostResponse {
    @SerializedName("data")
    private Data dataAttribute;

    public Data getDataAttribute() {
        return dataAttribute;
    }

    public void setDataAttribute(Data dataAttribute) {
        this.dataAttribute = dataAttribute;
    }

    @Override
    public String toString() {
        return "ProductPostResponse{" +
                "dataAttribute=" + dataAttribute +
                '}';
    }
}
