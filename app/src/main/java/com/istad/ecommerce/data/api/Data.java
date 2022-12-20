package com.istad.ecommerce.data.api;

import com.google.gson.annotations.SerializedName;
import com.istad.ecommerce.data.models.Product;

public class Data {

    @SerializedName("id")
    private int id;
    @SerializedName("attributes")
    private Product product;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", product=" + product +
                '}';
    }
}
