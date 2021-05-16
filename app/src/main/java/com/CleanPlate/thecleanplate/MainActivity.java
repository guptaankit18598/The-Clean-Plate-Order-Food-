package com.CleanPlate.thecleanplate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.CleanPlate.thecleanplate.Adapters.MainAdapter;
import com.CleanPlate.thecleanplate.Models.MainModel;
import com.CleanPlate.thecleanplate.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<MainModel> list = new ArrayList<>();
        list.add(new MainModel(R.drawable.burger , "Burger" , "5" , " They actually get their name from Hamburg."));
        list.add(new MainModel(R.drawable.lollipop , "Chicken Lollipop" , "10" , "You may savour chicken lollipop."));
        list.add(new MainModel(R.drawable.macpuff , "MacPuff" , "12" , "½ capsicum finely chopped."));
        list.add(new MainModel(R.drawable.pizza , "Pizza" , "14" , "A dish of Italian origin."));
        list.add(new MainModel(R.drawable.sandwich , "SandWich" , "8" , "An item of food consisting of two pieces of bread."));
        list.add(new MainModel(R.drawable.chowmein , "Chowmein" , "7" , "It is a stir-fried dish consisting of noodles."));

        MainAdapter adapter = new MainAdapter(list , this);
        binding.recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu , menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.orders:
                startActivity(new Intent(MainActivity.this, OrderActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}