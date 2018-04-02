package com.rollingpinbakery.rollingpinbakery;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.rollingpinbakery.rollingpinbakery.Data.AppDatabase;
import com.rollingpinbakery.rollingpinbakery.Data.Product;

import java.util.ArrayList;

/**
 * Created by equno_000 on 3/31/2018.
 */

public class ItemAdapter extends ArrayAdapter<Product> {
    private ArrayList<Product> dataSet;
    Context context;

    public ItemAdapter(Context context, ArrayList<Product> products){
        super(context, R.layout.content_item, products);
        this.dataSet = products;
        this.context = context;
    }


    @NonNull
    @Override
    public View getView(int postition, @Nullable View convertView, @NonNull final ViewGroup parent){
        final Product product = getItem(postition);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.content_item, parent, false);

        final TextView ProductName = convertView.findViewById(R.id.productName);
        final TextView productDesc = convertView.findViewById(R.id.productDesc);
        final TextView productType = convertView.findViewById(R.id.productType);
        final TextView ProductSalePrice = convertView.findViewById(R.id.productSalePrice);
        Button buyBtn = convertView.findViewById(R.id.buyBtn);

        final int id = product.get_prodId();

        ProductName.setText("Name: " + product.getProdName());
        productDesc.setText("Description: " + product.getProdDesc());
        ProductSalePrice.setText("Price: " + product.getProdRetailPrice());


        buyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String txtName = ProductName.getText().toString();
                final String txtType = productType.getText().toString();
                final String txtSalePrice = ProductSalePrice.getText().toString();
                buyItem(view, txtName, txtType, txtSalePrice);
            }
        });

        return convertView;
    }

    public void reset(Product product){
        this.remove(product);
        notifyDataSetChanged();
    }

    public void update(Product product){
        AppDatabase.getAppDatabase(getContext()).productDao().delete(product);
    }

    public void buyItem(View view, String name, String type, String salePrice){
        Intent formResult = new Intent(getContext(), CartActivity.class);

        formResult.putExtra("txt_productName", name);
        formResult.putExtra("txt_productType", type);
        formResult.putExtra("txt_productSalePrice", salePrice);

        view.getContext().startActivity(formResult);
    }
}
