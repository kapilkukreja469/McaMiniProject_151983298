package com.example.mcaminiproject_151983298;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private long back = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Press back again to Exit", Toast.LENGTH_SHORT).show();
        if (back + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
        back = System.currentTimeMillis();
    }

}
