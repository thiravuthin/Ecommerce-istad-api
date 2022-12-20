package com.istad.ecommerce.UI.Adaptor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.istad.ecommerce.R;
import com.istad.ecommerce.UI.activities.UpdateActivity;
import com.istad.ecommerce.data.api.Data;
import com.istad.ecommerce.data.models.Product;

import java.util.List;

/* After we create ProductViewHolder, we create this class
 *  ProductViewHolder below this class */

public class ProductAdaptor extends RecyclerView.Adapter<ProductAdaptor.ProductViewHolder> {

    List<Data> ProductResponseList;
    public ProductAdaptorClickListener productAdaptorClickListener;
    EditOnclick editOnclick;
    Context context;

    public ProductAdaptor(List<Data> productResponseList, ProductAdaptorClickListener listener, EditOnclick editOnclick) {

        this.ProductResponseList = productResponseList;
        this.productAdaptorClickListener = listener;
        this.editOnclick = editOnclick;
    }

    /**/
    @SuppressLint("NotifyDataSetChanged")
    public void setProductResponseList(List<Data> productResponseList) {
        this.ProductResponseList = productResponseList;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_product, parent, false);
        context = parent.getContext();
        return new ProductViewHolder(view);
    }

    /* onBindViewHolder handle on data source*/
    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        /*search*/
        Product product = ProductResponseList.get(position).getProduct();

        /*Detail screen*/
        holder.title.setText(product.getTitle());
        holder.price.setText(product.getPrice());

        /*Get image from API*/
        if (product.getThumbnail().getData() != null) {
            Glide.with(context)
                    .load("https://cms.istad.co" +
                            product.getThumbnail().getData().getThumbnail().getUrl())
                    .into(holder.imageView);
        }
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, UpdateActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return ProductResponseList.size();
    }


    /* This class we should create before class ProductAdaptor*/
    class ProductViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView title, price;
        ImageView update;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageID);
            title = itemView.findViewById(R.id.titleID);
            price = itemView.findViewById(R.id.priceID);
            update = itemView.findViewById(R.id.editButtonID);

            itemView.setOnClickListener(view -> {
                productAdaptorClickListener.onItemProductClick(ProductResponseList.get(getAdapterPosition()).getProduct());
            });
        }
    }
}
