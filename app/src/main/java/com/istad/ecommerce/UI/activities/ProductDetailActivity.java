package com.istad.ecommerce.UI.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.istad.ecommerce.R;
import com.istad.ecommerce.data.models.Product;
import com.squareup.picasso.Picasso;

public class ProductDetailActivity extends AppCompatActivity {

    ImageView imageView;
    TextView title, content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        Product product = (Product) getIntent().getSerializableExtra("product");
        initView();

        title.setText(product.getTitle());
        content.setText(product.getDescription());

        // On click to see detail screen
        if (product.getThumbnail().getData() != null) {
            Picasso.with(this)
                    .load("https://cms.istad.co" + product.getThumbnail().getData().getThumbnail().getUrl())
                    .into(imageView);
        }

    }

    private void initView() {
        imageView = findViewById(R.id.imgDetailID);
        title = findViewById(R.id.titleDetailID);
        content = findViewById(R.id.contentDetailID);
    }
}