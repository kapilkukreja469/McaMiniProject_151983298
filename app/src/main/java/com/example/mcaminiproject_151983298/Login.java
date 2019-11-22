package com.example.mcaminiproject_151983298;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class Login extends AppCompatActivity {
    EditText password;
    TextView userId;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;
    String verifyCode, aPass, mNo;
    FirebaseDatabase database;
    DatabaseReference reference;
    Button login;
    private FirebaseAuth mAuth;
    ProgressDialog progressDialog;

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (!internetCheck()) {
            new AlertDialog.Builder(this)
                    .setTitle("Interrnet Connection Alert")
                    .setMessage("please Check your Interrnet Connection")
                    .setCancelable(false)
                    .setPositiveButton("close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    })
                    .show();
        } else if (currentUser != null) {
            startActivity(new Intent(Login.this,HomeActivity.class));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        userId = findViewById(R.id.aUserId);
        password = findViewById(R.id.aPassword);
        login = findViewById(R.id.login);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Admin");
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Login process initialize...");
        getSupportActionBar().hide();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mNo = dataSnapshot.child("Mobile").getValue(String.class);
                userId.setText(mNo + "");
                aPass = dataSnapshot.child("password").getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass = password.getText().toString();
                if (TextUtils.isEmpty(pass)) {
                    password.setError("please enter password");
                } else if (pass.equals(aPass)) {
                    progressDialog.show();
                    sendSms();
                } else
                    Toast.makeText(getApplicationContext(), "wrong password", Toast.LENGTH_SHORT).show();
            }
        });
        mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(getApplicationContext(), "verification failed" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verifyCode = s;
                Toast.makeText(getApplicationContext(), "OTP send successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), OtpActivity.class);
                intent.putExtra("OTP", verifyCode);
                startActivity(intent);
                finish();
            }
        };
    }

    public void sendSms() {
        if (mNo.length() != 14) {
            userId.setError("Enter 10 digit valid mobile number");
        } else
            PhoneAuthProvider.getInstance().verifyPhoneNumber(mNo, 60, TimeUnit.SECONDS, Login.this, mCallback);
        progressDialog.dismiss();
    }
    public boolean internetCheck() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
