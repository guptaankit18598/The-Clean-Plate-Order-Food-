package com.CleanPlate.thecleanplate;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.CleanPlate.thecleanplate.Models.OrdersModel;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {
    final  static String dbName = "mydatabase.db";
    final static int dbversion = 103;

    public DbHelper(@Nullable Context context) {


        super(context, dbName, null, dbversion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "create table orders" + "(id integer primary key autoincrement,"+"name text,"+" " +
                        "phone text,"+"price int , "+"image int,"+"quantity int,"+"foodname text,"+"description text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
              sqLiteDatabase.execSQL("DROP table if exists orders");
              onCreate(sqLiteDatabase);
    }

    public boolean insetOrder (String name , String phone , int price , int image , String description , String foodName , int quantity){

        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name" , name);
        values.put("phone",phone);
        values.put("price",price);
        values.put("image",image);
        values.put("description",description);
        values.put("foodName",foodName);
        values.put("quantity",quantity);

        long id = database.insert("orders",null,values);
        if(id <= 0) {
            return false;
        }else {
            return true;
        }
    }

    public ArrayList<OrdersModel> getOrders() {
        ArrayList<OrdersModel> orders = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select id,description,image,price from orders" , null);
        if (cursor.moveToFirst()) {
            do {
                OrdersModel model = new OrdersModel();
                model.setOrderNumber(cursor.getInt(0)+"");
                model.setSoldItemName(cursor.getString(1));
                model.setOrderimage(cursor.getInt(2));
                model.setPrice(cursor.getInt(3)+"");
                orders.add(model);
            }while(cursor.moveToNext());

        }
        cursor.close();
        database.close();
        return orders;

    }
    public Cursor getOrderById(int id) {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from orders Where id = "+id , null);
        if(cursor != null)
            cursor.moveToFirst();

        return cursor;
    }
    public boolean UpdateOrder (String name , String phone , int price , int image , String description , String foodName , int quantity,int id){

        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name" , name);
        values.put("phone",phone);
        values.put("price",price);
        values.put("image",image);
        values.put("description",description);
        values.put("foodName",foodName);
        values.put("quantity",quantity);


        long row = database.update("orders" ,values,"id="+id,null);
        if(row <= 0) {
            return false;
        }else {
            return true;
        }
    }

    public int deleteOrder(String id) {
        SQLiteDatabase database = this.getWritableDatabase();
        return database.delete("orders","id="+id,null);
    }
}
