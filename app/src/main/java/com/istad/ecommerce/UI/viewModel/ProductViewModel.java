package com.istad.ecommerce.UI.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.istad.ecommerce.data.api.request.ProductPostRequest;
import com.istad.ecommerce.data.api.request.ProductRequest;
import com.istad.ecommerce.data.api.response.ProductPostResponse;
import com.istad.ecommerce.data.api.response.ProductResponse;
import com.istad.ecommerce.data.models.Thumbnail;
import com.istad.ecommerce.data.remote.repository.ProductRepository;

import java.io.File;
import java.util.List;

/**/
public class ProductViewModel extends ViewModel {

    ProductRepository productRepository;
    LiveData<ProductResponse> productResponseLiveData;
    ProductPostRequest productPostRequest;

    /*Handle on Post Product*/
    public MutableLiveData<ProductPostResponse> postProducts(String imgID, String title, String context){
        ProductRequest productRequest = new ProductRequest();
        ProductPostRequest data = new ProductPostRequest();
        data.setTitle(title);
        data.setCategory("123");
        data.setPrice("124");
        data.setRating("1111111111");
        data.setThumbnail(imgID);
        data.setDescription(context);

        productRequest.setProductDataRequest(data);

        return productRepository.postProduct(productRequest);
    }


    /*Handle on */
    public void init(){
        productRepository = new ProductRepository();
        productResponseLiveData = productRepository.getProductLiveData();

    }

    /*Handle on */
    public void initRepo(){
        productRepository = new ProductRepository("Only Init Service");
    }

    /*Handle on Upload Image*/
    public MutableLiveData<List<Thumbnail>> uploadImage( File file){
        return productRepository.uploadImage(file);
    }

    /*Handle on fetch all data product */
    public void getAllProduct(){
        productRepository.getProduct();
    }

    /*Handle on search data*/
    public void getProductByID(String title){
        productRepository.getProductByTitle(title);
    }

    /*Handle on */
    public LiveData<ProductResponse> getProductResponseLiveData(){
        return productResponseLiveData;
    }

}
