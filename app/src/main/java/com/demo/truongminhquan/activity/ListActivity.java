package com.demo.truongminhquan.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.demo.truongminhquan.R;
import com.demo.truongminhquan.adapter.ProductAdapter;
import com.demo.truongminhquan.database.AppDatabase;
import com.demo.truongminhquan.entity.ProductEntity;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private AppDatabase db;
    private List<ProductEntity> listProduct = new ArrayList<>();
    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        db = AppDatabase.getAppDatabase(this);
        listProduct = db.productDao().getAllProduct();

        adapter = new ProductAdapter(this, listProduct);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);

        RecyclerView rvProduct = findViewById(R.id.rvProduct);
        rvProduct.setLayoutManager(layoutManager);
        rvProduct.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        listProduct = db.productDao().getAllProduct();
        adapter.ReloadData(listProduct);
    }
}