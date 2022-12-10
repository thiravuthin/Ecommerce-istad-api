package com.istad.ecommerce.data.remote.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.istad.ecommerce.data.RetrofitServiceGenerator;
import com.istad.ecommerce.data.api.request.ProductPostRequest;
import com.istad.ecommerce.data.api.request.ProductRequest;
import com.istad.ecommerce.data.api.response.ProductPostResponse;
import com.istad.ecommerce.data.api.response.ProductResponse;
import com.istad.ecommerce.data.models.Thumbnail;
import com.istad.ecommerce.data.remote.service.ProductService;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*ProductRepository handle on: */
public class ProductRepository {

    ProductService productService;
    MutableLiveData <ProductResponse> productResponseMutableLiveData;

    /*Constructor handle on */
    public ProductRepository() {
        productResponseMutableLiveData = new MutableLiveData<>();
        productService = RetrofitServiceGenerator.createService(ProductService.class);
        getProduct();
    }
    /*Constructor : handle on */
    public ProductRepository (String service){
       productService = RetrofitServiceGenerator.createService(ProductService.class);
    }
    /*Handle on Post Product*/
    public MutableLiveData<ProductPostResponse> postProduct (ProductRequest productRequest)  {
        MutableLiveData<ProductPostResponse> liveData = new MutableLiveData<>();
        productService.postProduct(productRequest).enqueue(new Callback<ProductPostResponse>() {
            @Override
            public void onResponse(Call<ProductPostResponse> call, Response<ProductPostResponse> response) {
                liveData.postValue(response.body());
                Log.d("TAG", "onResponseeee: "+ response);
            }

            @Override
            public void onFailure(Call<ProductPostResponse> call, Throwable t) {
                liveData.postValue(null);
                Log.d("TAG", "eeeeeee: "+t.getMessage());
            }
        });
        return liveData;
    }

    /*Handle on fetch all data product */
    public void getProduct(){
        productService.getAllProduct().enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                productResponseMutableLiveData.postValue(response.body());
                Log.d("TAG", "response: "+response);
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                productResponseMutableLiveData.postValue(null);
                Log.d("TAG", "onFailure: "+t.getMessage());
            }
        });
    }

    /*Handle on Search product by title*/
    public void getProductByTitle(String title){
        productService.getProductByTitle(title).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                productResponseMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                productResponseMutableLiveData.postValue(null);
            }
        });
    }
    /*Handle on uploading images*/
    public MutableLiveData<List<Thumbnail>> uploadImage(File file){
        MutableLiveData<List<Thumbnail>> thumbnailLiveData = new MutableLiveData<>();
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"),file);
        Log.d("TAG", "uploadImage: "+file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("files", file.getAbsolutePath(), requestBody);
        productService.uploadImage(body).enqueue(new Callback<List<Thumbnail>>() {
            @Override
            public void onResponse(Call<List<Thumbnail>> call, Response<List<Thumbnail>> response) {
                Log.d("TAG","onResponse: " + response);
                thumbnailLiveData.postValue(response.body());            }

            @Override
            public void onFailure(Call<List<Thumbnail>> call, Throwable t) {
                Log.d("TAG", "onFailure: "+t.getMessage());
                thumbnailLiveData.postValue(null);
            }
        });
        return thumbnailLiveData;
    }

    /*Live data cannot modify value*/ // ViewModel will call
    public LiveData<ProductResponse> getProductLiveData(){
        return productResponseMutableLiveData;
    }

}
