package com.example.mcaminiproject_151983298.fragments;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mcaminiproject_151983298.R;
import com.example.mcaminiproject_151983298.database_Helper.MyHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SalesFragment extends Fragment {
    EditText q1, q2, q3, q4, q5, p1, p2, p3, p4, p5, dr;
    TextView total;
    Spinner s1, s2, s3, s4, s5;
    String st1, st2, st3, st4, st5, dtr, date;
    int qt1 = 0, qt2 = 0, qt3 = 0, qt4 = 0, qt5 = 0, pt1 = 0, pt2 = 0, pt3 = 0, pt4 = 0, pt5 = 0, gTotal = 0;
    List<String> courseList = new ArrayList<>();
    Button submit, dateBtn;
    SQLiteDatabase database2;
    DatePickerDialog.OnDateSetListener dateSetListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sales, null);
        q1 = view.findViewById(R.id.q1);
        q2 = view.findViewById(R.id.q2);
        q3 = view.findViewById(R.id.q3);
        q4 = view.findViewById(R.id.q4);
        q5 = view.findViewById(R.id.q5);
        p1 = view.findViewById(R.id.p1);
        p2 = view.findViewById(R.id.p2);
        p3 = view.findViewById(R.id.p3);
        p4 = view.findViewById(R.id.p4);
        p5 = view.findViewById(R.id.p5);
        dr = view.findViewById(R.id.debitorName);
        s1 = view.findViewById(R.id.i1);
        s2 = view.findViewById(R.id.i2);
        s3 = view.findViewById(R.id.i3);
        s4 = view.findViewById(R.id.i4);
        s5 = view.findViewById(R.id.i5);
        total = view.findViewById(R.id.total);
        submit = view.findViewById(R.id.submitBill);
        dateBtn = view.findViewById(R.id.date);
        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(getActivity(), android.R.style.Theme_Holo_Dialog_MinWidth, dateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month += 1;
                date = day + "/" + month + "/" + year;
                dateBtn.setText(date);
            }
        };
        MyHelper helper = new MyHelper(getActivity());
        SQLiteDatabase database = helper.getReadableDatabase();
        courseList.add("--select Book--");
        Cursor cursor = database.rawQuery("SELECT bookName , authorName FROM DESCRIPTION", new String[]{});
        if (cursor != null) {
            cursor.moveToFirst();
        }
        do {
            String bname = cursor.getString(0);
            String aname = cursor.getString(1);
            courseList.add(bname + " By " + aname);
        } while (cursor.moveToNext());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, courseList);
        s1.setAdapter(adapter);
        s2.setAdapter(adapter);
        s3.setAdapter(adapter);
        s4.setAdapter(adapter);
        s5.setAdapter(adapter);

        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                if (courseList.get(position).equals("--select Book--")) {
                    st1 = "null";
                } else
                    st1 = courseList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                st1 = "null";
            }
        });
        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                if (courseList.get(position).equals("--select Book--")) {
                    st2 = "null";
                } else
                    st2 = courseList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                st2 = "null";
            }
        });
        s3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                if (courseList.get(position).equals("--select Book--")) {
                    st3 = "null";
                } else
                    st3 = courseList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                st3 = "null";
            }
        });
        s4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                if (courseList.get(position).equals("--select Book--")) {
                    st4 = "null";
                } else
                    st4 = courseList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                st4 = "null";
            }
        });
        s5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                if (courseList.get(position).equals("--select Book--")) {
                    st5 = "null";
                } else
                    st5 = courseList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                st5 = "null";
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(dr.getText()) || TextUtils.isEmpty(dateBtn.getText())) {
                    dr.setError("Debitor name and Date can't be Empty");
                    Toast.makeText(getActivity(), "Debitor name and Date can't be Empty", Toast.LENGTH_SHORT).show();
                } else {
                    dtr = dr.getText().toString();
                    if (!TextUtils.isEmpty(q1.getText()) && !TextUtils.isEmpty(p1.getText())) {
                        qt1 = Integer.parseInt(q1.getText().toString());
                        pt1 = Integer.parseInt(p1.getText().toString());
                    }
                    if (!TextUtils.isEmpty(q2.getText()) && !TextUtils.isEmpty(p2.getText())) {
                        qt2 = Integer.parseInt(q2.getText().toString());
                        pt2 = Integer.parseInt(p2.getText().toString());
                    }
                    if (!TextUtils.isEmpty(q3.getText()) && !TextUtils.isEmpty(p3.getText())) {
                        qt3 = Integer.parseInt(q3.getText().toString());
                        pt3 = Integer.parseInt(p3.getText().toString());
                    }
                    if (!TextUtils.isEmpty(q4.getText()) && !TextUtils.isEmpty(p4.getText())) {
                        qt4 = Integer.parseInt(q4.getText().toString());
                        pt4 = Integer.parseInt(p4.getText().toString());
                    }
                    if (!TextUtils.isEmpty(q5.getText()) && !TextUtils.isEmpty(p5.getText())) {
                        qt5 = Integer.parseInt(q5.getText().toString());
                        pt5 = Integer.parseInt(p5.getText().toString());
                    }
                    gTotal = (qt1 * pt1) + (qt2 * pt2) + (qt3 * pt3) + (qt4 * pt4) + (qt5 * pt5);
                    total.setText(gTotal + "");

                    MyHelper helper = new MyHelper(getActivity());
                    database2 = helper.getWritableDatabase();
                    helper.insertSales(date, dtr, st1, qt1, pt1, st2, qt2, pt2, st3, qt3, pt3, st4, qt4, pt4, st5, qt5, pt5, gTotal);
                    Toast.makeText(getActivity(), "Sales done Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}
