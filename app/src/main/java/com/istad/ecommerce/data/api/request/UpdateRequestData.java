package com.istad.ecommerce.data.api.request;

import com.google.gson.annotations.SerializedName;


public class UpdateRequestData {
    @SerializedName("title")
    private String title;
    @SerializedName("title")
    private String rating;
    @SerializedName("title")
    private String description;
    @SerializedName("title")
    private String quantity;
    @SerializedName("title")
    private String category;
    @SerializedName("title")
    private String thumbnail;
    @SerializedName("title")
    private String price;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "UpdateRequestData{" +
                "title='" + title + '\'' +
                ", rating='" + rating + '\'' +
                ", description='" + description + '\'' +
                ", quantity='" + quantity + '\'' +
                ", category='" + category + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
