package com.CleanPlate.thecleanplate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.CleanPlate.thecleanplate.Adapters.OrdersAdapter;
import com.CleanPlate.thecleanplate.Models.OrdersModel;
import com.CleanPlate.thecleanplate.databinding.ActivityOrderBinding;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {
ActivityOrderBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        DbHelper helper = new DbHelper(this);
        ArrayList<OrdersModel> list = helper.getOrders();
        Toast.makeText(this, "Long press on order to delete it.", Toast.LENGTH_SHORT).show();


        OrdersAdapter adapter = new OrdersAdapter(list , this);
        binding.orderRecyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.orderRecyclerView.setLayoutManager(layoutManager);

        

    }
}