package com.example.mcaminiproject_151983298.fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mcaminiproject_151983298.R;
import com.example.mcaminiproject_151983298.database_Helper.MyHelper;

import java.util.ArrayList;
import java.util.List;

public class StockFragment extends Fragment {
    TextView textView1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stock, null);
        textView1 = view.findViewById(R.id.textView1);

        MyHelper helper = new MyHelper(getActivity());
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT bookName,authorName,quantity,sellingPrice FROM STOCK", new String[]{});
        if (cursor != null) {
            cursor.moveToFirst();
        }
        List<String> data = new ArrayList<>();
        do {
            String bookName=cursor.getString(0);
            String authorName=cursor.getString(1);
            int quantity=cursor.getInt(2);
            int sellingPrice=cursor.getInt(3);
            data.add(bookName+" by "+authorName+"\t"+"\t"+"\t"+"\t"+"\t"+"\t"+"\t"+quantity+"\t"+"\t"+"\t"+"\t"+"\t"+"\t"+"\t"+sellingPrice+"\n" + "______________________________________________________" + "\t");
            textView1.setText(data.toString());
        } while (cursor.moveToNext());


        return view;
    }

}
