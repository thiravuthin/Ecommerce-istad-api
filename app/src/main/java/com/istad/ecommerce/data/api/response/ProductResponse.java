package com.istad.ecommerce.data.api.response;

import com.google.gson.annotations.SerializedName;
import com.istad.ecommerce.data.api.Data;

import java.util.List;

/*  API is the place take model prepare response API
 *
 *
 *
 */
public class ProductResponse {

    @SerializedName("data")
    List<Data> ProductResponseList;

    public List<Data> getProductResponseList() {
        return ProductResponseList;
    }

    public void setProductResponseList(List<Data> productResponseList) {
        ProductResponseList = productResponseList;
    }

    @Override
    public String toString() {
        return "ProductResponse{" +
                "ProductResponseList=" + ProductResponseList +
                '}';
    }
}
