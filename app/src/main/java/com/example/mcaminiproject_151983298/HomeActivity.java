package com.example.mcaminiproject_151983298;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.mcaminiproject_151983298.database_Helper.MyHelper;

import android.text.TextUtils;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.example.mcaminiproject_151983298.fragments.PurchaseFragment;
import com.example.mcaminiproject_151983298.fragments.PurchaseHistoryFragment;
import com.example.mcaminiproject_151983298.fragments.SalesFragment;
import com.example.mcaminiproject_151983298.fragments.SalesHistoryFragment;
import com.example.mcaminiproject_151983298.fragments.StockFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    SQLiteDatabase database;
    private long back = 0;
    EditText bookName, authorName, publisherName, sellingPrice, bookDescription;
    private FirebaseAuth mAuth;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        bookName = findViewById(R.id.BookName);
        authorName = findViewById(R.id.AutherName);
        publisherName = findViewById(R.id.PublisherName);
        sellingPrice = findViewById(R.id.SellingPrice);
        bookDescription = findViewById(R.id.BookDescription);
        setSupportActionBar(toolbar);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }

    public void getData(View view) {
        String bName, aName, pName, desc;
        int sPrice, quantity = 0;
        if (TextUtils.isEmpty(bookName.getText()) || TextUtils.isEmpty(authorName.getText()) || TextUtils.isEmpty(publisherName.getText()) || TextUtils.isEmpty(sellingPrice.getText()) || TextUtils.isEmpty(bookDescription.getText())) {
            Toast.makeText(this, "please fill all details", Toast.LENGTH_SHORT).show();
        } else {
            bName = bookName.getText().toString();
            aName = authorName.getText().toString();
            pName = publisherName.getText().toString();
            sPrice = Integer.parseInt(sellingPrice.getText().toString());
            desc = bookDescription.getText().toString();
            MyHelper helper = new MyHelper(HomeActivity.this);
            database = helper.getWritableDatabase();
            helper.insertData(bName, aName, pName, sPrice, desc);
            helper.addStock(bName, aName, quantity, sPrice);
            Toast.makeText(HomeActivity.this, "Data Added Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
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

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        if (id == R.id.nav_add_books) {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        } else if (id == R.id.nav_purchaseVoucher) {
            fragment = new PurchaseFragment();
        } else if (id == R.id.nav_saleVoucher) {
            fragment = new SalesFragment();

        } else if (id == R.id.purchaseHistory) {
            fragment = new PurchaseHistoryFragment();

        } else if (id == R.id.saleHistory) {
            fragment = new SalesHistoryFragment();

        } else if (id == R.id.bookStock) {
            fragment = new StockFragment();

        } else if (id == R.id.Logout) {
            mAuth.signOut();
            startActivity(new Intent(getApplicationContext(), Login.class));
        }
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.frame, fragment);
            ft.commit();
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
