package com.istad.ecommerce.UI.mutable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.istad.ecommerce.R;

import com.istad.ecommerce.UI.viewModel.ProductViewModel;
import com.istad.ecommerce.data.api.request.ProductPostRequest;
import com.istad.ecommerce.data.api.response.ProductPostResponse;
import com.istad.ecommerce.data.models.Thumbnail;
import com.istad.ecommerce.utils.FilePath;

import java.io.File;
import java.util.List;

/* Handle on */
public class MutableActivity extends AppCompatActivity {

    Button buttonSave;
    ImageView imageView;
    EditText editTitle, editContent;
    ProgressBar progressBar;
    ProductViewModel productViewModel;
    int thumbnailID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mutable);

        initViews();
        initEvent();

        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        productViewModel = new ViewModelProvider (this, factory).get(ProductViewModel.class);
        productViewModel.initRepo();

        requestPermission();
    }

    private void initEvent() {
        imageView.setOnClickListener(view -> {
         showFileChooser();
        });

        buttonSave.setOnClickListener(view -> {
            productViewModel.postProducts(String.valueOf(thumbnailID),editTitle.getText().toString(),editContent.getText().toString()).observe(this, new Observer<ProductPostResponse>() {
                @Override
                public void onChanged(ProductPostResponse productPostResponse) {

                    Log.d("TAG", "onChangedd: "+productPostResponse);
                }
            });
        });
    }
    private void showFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Choose Image"),1000);
    }

    private void initViews() {
        buttonSave = findViewById(R.id.btnSaveID);
        imageView = findViewById(R.id.imgUploadID);
        editTitle = findViewById(R.id.editTitleID);
        editContent = findViewById(R.id.editContentID);
        progressBar = findViewById(R.id.progressBarUpload);
    }
    void setProgressBarVisible(){
        progressBar.setVisibility(View.VISIBLE);
    }

    void setProgressBarInvisible(){
        progressBar.setVisibility(View.INVISIBLE);
    }

    /**/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode==RESULT_OK && data != null){
            Log.d("TAG", "onActivityResult: "+data);
            setProgressBarVisible();
            Uri imageUri =  data.getData();
            Log.d("TAG", "onActivityResult: "+imageUri);

            try{
                String SelectedPath = FilePath.getPath( this,imageUri);
                File file = new File(SelectedPath);
                uploadFileDataAPI(file);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void uploadFileDataAPI(File file) {

        productViewModel.uploadImage(file).observe(this, new Observer<List<Thumbnail>>() {
            @Override
            public void onChanged(List<Thumbnail> thumbnails) {
                String imageUrl = "https://cms.istad.co"+thumbnails.get(0).getUrl();
                thumbnailID = thumbnails.get(0).getId();
                Glide.with(MutableActivity.this).load(imageUrl).into(imageView);
                setProgressBarInvisible();

            }
        });
    }

    private void requestPermission(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){}
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE
        },100);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode ==100 ){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "You just define Permission", Toast.LENGTH_SHORT).show();
            }
        }
    }
}