package com.istad.ecommerce.data.models;

/*  This class contain object that to be obtain from JSON
*
*
*/

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Product implements Serializable {
    @SerializedName("title")
    private String title;

    @SerializedName("createdAt")
    private String createAt;

    @SerializedName("updatedAt")
    private String updatedAt;

    @SerializedName("publishedAt")
    private String publishedAt;

    @SerializedName("rating")
    private String rating;

    @SerializedName("description")
    private String description;

    @SerializedName("quantity")
    private String quantity;

    @SerializedName("thumbnail")
    private ThumbnailData thumbnail;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
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

    public ThumbnailData getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(ThumbnailData thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return "Product{" +
                "title='" + title + '\'' +
                ", createAt='" + createAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", rating='" + rating + '\'' +
                ", description='" + description + '\'' +
                ", quantity='" + quantity + '\'' +
                ", thumbnail=" + thumbnail +
                '}';
    }
}
