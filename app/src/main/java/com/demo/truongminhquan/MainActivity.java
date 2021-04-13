package com.demo.truongminhquan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.demo.truongminhquan.activity.ListActivity;
import com.demo.truongminhquan.database.AppDatabase;
import com.demo.truongminhquan.entity.ProductEntity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private AppDatabase db;
    private EditText edName, edQuantity;
    private Button btAdd, btView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        db = AppDatabase.getAppDatabase(this);

    }

    private void initView() {
        edName = findViewById(R.id.edName);
        edQuantity = findViewById(R.id.edQuantity);

        btAdd = findViewById(R.id.btAdd);
        btAdd.setOnClickListener(this);
        btView = findViewById(R.id.btView);
        btView.setOnClickListener(this);
    }

    private void addProduct() {
        if (edName.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter name", Toast.LENGTH_LONG).show();
            return;
        }
        if (edQuantity.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter quantity", Toast.LENGTH_LONG).show();
            return;
        }

        ProductEntity product = new ProductEntity();
        product.name = edName.getText().toString();
        product.quantity = Integer.valueOf(edQuantity.getText().toString());
        db.productDao().insertProduct(product);

        intentActivity();
    }

    private void intentActivity() {
        Intent intent = new Intent(MainActivity.this, ListActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btAdd:
                addProduct();
                break;
            case R.id.btView:
                intentActivity();
                break;
            default:
                break;
        }
    }
}