package com.istad.ecommerce.data.remote.service;

/*  Retrofit API we define service
 *   Define Service is define End point
 *   End point we use it at repository
 *   repository we use it for get data
 */
// Retrofit we define service( interface ), define service is --> define End Point

import com.istad.ecommerce.data.api.request.ProductRequest;
import com.istad.ecommerce.data.api.request.UpdateRequest;
import com.istad.ecommerce.data.api.response.ProductPostResponse;
import com.istad.ecommerce.data.api.response.ProductResponse;
import com.istad.ecommerce.data.models.Thumbnail;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ProductService {

    @GET("e-commerce-products?populate=thumbnail")
    Call<ProductResponse> getAllProduct();

    @GET("e-commerce-products?populate=thumbnail")
    Call<ProductResponse> getProductByTitle(@Query("filters[title][$containsi]") String title);

    @Multipart
    @POST("upload")
    Call<List<Thumbnail>> uploadImage(@Part MultipartBody.Part image);

    @POST("e-commerce-products")
    Call<ProductPostResponse> postProduct(@Body ProductRequest productPostRequest);

    @PUT("e-commerce-products")
    Call<ProductPostResponse> updateProduct(@Body UpdateRequest updateRequest);

    @PUT("e-commerce-products/{id}")
    Call<ProductPostResponse> updateProductID(@Part("id") int id, @Body UpdateRequest updateRequestID);
}

