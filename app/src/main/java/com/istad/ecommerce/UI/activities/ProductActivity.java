package com.istad.ecommerce.UI.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.istad.ecommerce.R;
import com.istad.ecommerce.UI.Adaptor.EditOnclick;
import com.istad.ecommerce.UI.Adaptor.ProductAdaptor;
import com.istad.ecommerce.UI.Adaptor.ProductAdaptorClickListener;
import com.istad.ecommerce.UI.mutable.MutableActivity;
import com.istad.ecommerce.UI.viewModel.ProductViewModel;
import com.istad.ecommerce.data.api.Data;
import com.istad.ecommerce.data.api.response.ProductResponse;
import com.istad.ecommerce.data.models.Product;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity implements ProductAdaptorClickListener, EditOnclick {

    ProductViewModel productViewModel;
    RecyclerView recyclerView;
    ProductAdaptor adaptor;
    ProgressBar progressBar;
    android.widget.SearchView SearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        initViews();

        ViewModelProvider.Factory factory =
                (ViewModelProvider.Factory) new ViewModelProvider.NewInstanceFactory();
        productViewModel = new ViewModelProvider(this, factory).get(ProductViewModel.class);
        productViewModel.init();

        productViewModel.getProductResponseLiveData().observe(this, new Observer<ProductResponse>() {
            @Override
            public void onChanged(ProductResponse productResponse) {
                adaptor.setProductResponseList(productResponse.getProductResponseList());
                setProgressBarInvisible();

            }
        });
    }

    void setProgressBarVisible() {
        progressBar.setVisibility(View.VISIBLE);
    }

    void setProgressBarInvisible() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void initViews() {
        progressBar = findViewById(R.id.progressbarID);
        recyclerView = findViewById(R.id.recyclerID);
        adaptor = new ProductAdaptor(new ArrayList<>(), this, this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adaptor);
    }

    /*Search*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.actionSearch);
        SearchView searchView = (SearchView) menuItem.getActionView();

        /*Search by Text*/
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.d("TAG", "onQueryTextSubmit: " + s);
                productViewModel.getProductByID(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Log.d("TAG", "onQueryTextChange: " + s);
                if (s.isEmpty()) {
                    productViewModel.getAllProduct();
                }
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onItemProductClick(Product product) {
        Intent intent = new Intent(this, ProductDetailActivity.class);
        intent.putExtra("product", product);
        startActivity(intent);
    }

    // Upload image
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.addProduct) {
            startActivity(new Intent(this, MutableActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void OnItemUpdateProduct(Data data) {

    }
}
