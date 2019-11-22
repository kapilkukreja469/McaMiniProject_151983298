package com.example.mcaminiproject_151983298.fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mcaminiproject_151983298.R;
import com.example.mcaminiproject_151983298.database_Helper.MyHelper;

import java.util.ArrayList;
import java.util.List;

public class SalesHistoryFragment extends Fragment {
    TextView textView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sales_history, null);

        textView = view.findViewById(R.id.textView);
        MyHelper helper = new MyHelper(getActivity());
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT date,debitorName,b1,q1,p1,b2,q2,p2,b3,q3,p3,b4,q4,p4,b5,q5,p5,gTotal FROM SALES", new String[]{});
        if (cursor != null) {
            cursor.moveToFirst();
        }
        List<String> data = new ArrayList<>();
        do {
            String date = cursor.getString(0);
            String debitorName = cursor.getString(1);
            String b1 = cursor.getString(2);
            int q1 = cursor.getInt(3);
            int p1 = cursor.getInt(4);
            String b2 = cursor.getString(5);
            int q2 = cursor.getInt(6);
            int p2 = cursor.getInt(7);
            String b3 = cursor.getString(8);
            int q3 = cursor.getInt(9);
            int p3 = cursor.getInt(10);
            String b4 = cursor.getString(11);
            int q4 = cursor.getInt(12);
            int p4 = cursor.getInt(13);
            String b5 = cursor.getString(14);
            int q5 = cursor.getInt(15);
            int p5 = cursor.getInt(16);
            int gTotal = cursor.getInt(17);
            data.add("\t" + "Date= " + "\t" + date + "\t" + "debitor= " + "\t" + debitorName + "\n" + "\t" + "Book name" + "\t" + "\t" + "\t" + "\t" + "\t" + "Quantity" + "\t" + "\t" + "Price" + "\n" + "\t" + b1 + "\t" + q1 + "\t" + "\t" + p1 + "\n" + "\t" + b2 + "\t" + q2 + "\t" + "\t" + p2 + "\n" + "\t" + b3 + "\t" + q3 + "\t" + "\t" + p3 + "\n" + "\t" + b4 + "\t" + q4 + "\t" + "\t" + p4 + "\n" + "\t" + b5 + "\t" + q5 + "\t" + "\t" + p5 + "\n" + "\t" + "Grand Total" + gTotal + "\t" + "\n" + "______________________________________________________" + "\t");
            textView.setText(data.toString());
        } while (cursor.moveToNext());
        return view;
    }
}
