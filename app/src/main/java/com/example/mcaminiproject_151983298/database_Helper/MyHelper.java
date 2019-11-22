package com.example.mcaminiproject_151983298.database_Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelper extends SQLiteOpenHelper {

    public MyHelper(Context context) {
        super(context, "BookStock", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createQuery = "CREATE TABLE DESCRIPTION(bookName TEXT,authorName TEXT,publisherName TEXT,sellingPrice INTEGER,bookDescription TEXT,PRIMARY KEY(bookName,authorName))";
        String purchaseQuery = "CREATE TABLE PURCHASE(date TEXT,creditorName TEXT,b1 TEXT,q1 INTEGER,p1 INTEGER,b2 TEXT,q2 INTEGER,p2 INTEGER,b3 TEXT,q3 INTEGER,p3 INTEGER,b4 TEXT,q4 INTEGER,p4 INTEGER,b5 TEXT,q5 INTEGER,p5 INTEGER,gTotal INTEGER)";
        String salesQuery = "CREATE TABLE SALES(date TEXT,debitorName TEXT,b1 TEXT,q1 INTEGER,p1 INTEGER,b2 TEXT,q2 INTEGER,p2 INTEGER,b3 TEXT,q3 INTEGER,p3 INTEGER,b4 TEXT,q4 INTEGER,p4 INTEGER,b5 TEXT,q5 INTEGER,p5 INTEGER,gTotal INTEGER)";
        String stockQuery = "CREATE TABLE STOCK(bookName TEXT,authorName TEXT,quantity INTEGER,sellingPrice INTEGER)";
        sqLiteDatabase.execSQL(createQuery);
        sqLiteDatabase.execSQL(purchaseQuery);
        sqLiteDatabase.execSQL(salesQuery);
        sqLiteDatabase.execSQL(stockQuery);
    }

    public void addStock(String bookName, String authorName, int quantity, int sellingPrice) {
        SQLiteDatabase database4 = this.getWritableDatabase();
        ContentValues values4 = new ContentValues();
        values4.put("bookName", bookName);
        values4.put("authorName", authorName);
        values4.put("quantity", quantity);
        values4.put("sellingPrice", sellingPrice);
        database4.insert("STOCK", null, values4);
    }


    public void insertSales(String date, String debitorName, String b1, int q1, int p1, String b2, int q2, int p2, String b3, int q3, int p3, String b4, int q4, int p4, String b5, int q5, int p5, int gTotal) {
        SQLiteDatabase database3 = this.getWritableDatabase();
        ContentValues values3 = new ContentValues();
        values3.put("date", date);
        values3.put("debitorName", debitorName);
        values3.put("b1", b1);
        values3.put("q1", q1);
        values3.put("p1", p1);
        values3.put("b2", b2);
        values3.put("q2", q2);
        values3.put("p2", p2);
        values3.put("b3", b3);
        values3.put("q3", q3);
        values3.put("p3", p3);
        values3.put("b4", b4);
        values3.put("q4", q4);
        values3.put("p4", p4);
        values3.put("b5", b5);
        values3.put("q5", q5);
        values3.put("p5", p5);
        values3.put("gTotal", gTotal);
        database3.insert("SALES", null, values3);
    }

    public void insertPurchase(String date, String creditorName, String b1, int q1, int p1, String b2, int q2, int p2, String b3, int q3, int p3, String b4, int q4, int p4, String b5, int q5, int p5, int gTotal) {
        SQLiteDatabase database2 = this.getWritableDatabase();
        ContentValues values2 = new ContentValues();
        values2.put("date", date);
        values2.put("creditorName", creditorName);
        values2.put("b1", b1);
        values2.put("q1", q1);
        values2.put("p1", p1);
        values2.put("b2", b2);
        values2.put("q2", q2);
        values2.put("p2", p2);
        values2.put("b3", b3);
        values2.put("q3", q3);
        values2.put("p3", p3);
        values2.put("b4", b4);
        values2.put("q4", q4);
        values2.put("p4", p4);
        values2.put("b5", b5);
        values2.put("q5", q5);
        values2.put("p5", p5);
        values2.put("gTotal", gTotal);
        database2.insert("PURCHASE", null, values2);
    }

    public void insertData(String bookName, String authorName, String publisherName, int sellingPrice, String description) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("bookName", bookName);
        values.put("authorName", authorName);
        values.put("publisherName", publisherName);
        values.put("sellingPrice", sellingPrice);
        values.put("bookDescription", description);
        database.insert("DESCRIPTION", null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
