package com.istad.ecommerce.data.api.request;

// Post product

import com.google.gson.annotations.SerializedName;

public class ProductRequest {
    @SerializedName("data")
    private ProductPostRequest productDataRequest;

    public ProductPostRequest getProductDataRequest() {
        return productDataRequest;
    }

    public void setProductDataRequest(ProductPostRequest productDataRequest) {
        this.productDataRequest = productDataRequest;
    }

    @Override
    public String toString() {
        return "ProductRequest{" +
                "productDataRequest=" + productDataRequest +
                '}';
    }
}
