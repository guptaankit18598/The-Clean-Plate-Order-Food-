    package com.CleanPlate.thecleanplate.Adapters;

    import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.CleanPlate.thecleanplate.DbHelper;
import com.CleanPlate.thecleanplate.DetailActivity;
import com.CleanPlate.thecleanplate.Models.OrdersModel;
import com.CleanPlate.thecleanplate.R;

import java.util.ArrayList;

    public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.viewholder> {

    ArrayList<OrdersModel> list;
    Context context;

    public OrdersAdapter(ArrayList<OrdersModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.order_sample , parent , false);

        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        final OrdersModel model = list.get(position);
        holder.orderImage.setImageResource(model.getOrderimage());
        holder.soldItemName.setText(model.getSoldItemName());
        holder.price.setText(model.getPrice());
        holder.orderNumber.setText(model.getOrderNumber());
             holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context , DetailActivity.class);
        intent.putExtra("id" , Integer.parseInt(model.getOrderNumber()));
        intent.putExtra("type" , 2);
        context.startActivity(intent);
    }
     });

    holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            new AlertDialog.Builder(context)
                    .setTitle("Delete Item")
                    .setMessage("Are you sure you want ro delete")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            DbHelper helper = new DbHelper(context);
                            if (helper.deleteOrder(model.getOrderNumber()) > 0) {
                                Toast.makeText(context, "Order Deleted.", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Error.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).show();
            return false;
        }
    });
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        ImageView orderImage;
        TextView soldItemName , price , orderNumber;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            orderImage = itemView.findViewById(R.id.orderImage);
            soldItemName = itemView.findViewById(R.id.orderItemName);
            price = itemView.findViewById(R.id.orderPrice);
            orderNumber = itemView.findViewById(R.id.orderNumber);


        }
    }
}
