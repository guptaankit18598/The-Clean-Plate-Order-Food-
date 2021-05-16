package com.CleanPlate.thecleanplate;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.CleanPlate.thecleanplate.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {
ActivityDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        final DbHelper helper = new DbHelper(this);
        if(getIntent().getIntExtra("type",0) == 1) {

            final int image = getIntent().getIntExtra("image", 0);
            final int price = Integer.parseInt(getIntent().getStringExtra("price"));
            final String name = getIntent().getStringExtra("name");
            final String description = getIntent().getStringExtra("description");

            binding.detailImages.setImageResource(image);
            binding.priceLabel.setText(String.format("%d", price));
            binding.detailDescription.setText(description);
            binding.foodDetailName.setText(name);



            binding.insertbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(binding.nameBox.getText().toString().isEmpty()) {
                        binding.nameBox.setError("Please enter your Full Name");
                        return;
                    }
                    if(binding.phoneBox.getText().toString().isEmpty()) {
                        binding.phoneBox.setError("Please enter your Contact-No.");
                        return;
                    }

                    boolean isInserted = helper.insetOrder(binding.nameBox.getText().toString()
                            , binding.phoneBox.getText().toString(),
                            price, image, name,description,
                            Integer.parseInt(binding.quantity.getText().toString())

                    );
                    if (isInserted) {
                        Toast.makeText(DetailActivity.this, "Order Placed.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DetailActivity.this, "Error in Placing Order.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else {
            int id = getIntent().getIntExtra("id", 0);
            Cursor cursor = helper.getOrderById(id);
            int image = cursor.getInt(4);
            binding.detailImages.setImageResource(image);
            binding.priceLabel.setText(String.format("%d", cursor.getInt(3)));
            binding.detailDescription.setText(cursor.getString(7));
            binding.foodDetailName.setText(cursor.getString(6));
            binding.nameBox.setText(cursor.getString(1));
            binding.phoneBox.setText(cursor.getString(2));
            binding.insertbtn.setText("Update Now");
            binding.insertbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   boolean isUpdated =  helper.UpdateOrder(binding.nameBox.getText().toString(),
                            binding.phoneBox.getText().toString(),
                            Integer.parseInt(binding.priceLabel.getText().toString()),
                            image,
                            binding.detailDescription.getText().toString(),
                            binding.foodDetailName.getText().toString(),
                            1,
                            id
                            );
                    if(isUpdated) {
                        Toast.makeText(DetailActivity.this, "Order Updated.", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(DetailActivity.this, "Failed to Update Order.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
}