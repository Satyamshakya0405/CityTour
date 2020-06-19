package com.example.citytour.Common.loginsignup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.citytour.R;
import com.example.citytour.User.UserDashboard;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    TextInputLayout Username, Password;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Username = findViewById(R.id.Login_username);
        Password = findViewById(R.id.Login_Password);
        progressBar = findViewById(R.id.Login_progressBar);
        progressBar.setVisibility(View.INVISIBLE);

    }

    public void CallDashboard(View view) {
        if (!validateUsername() || !validatePassword())
            return;
        InputMethodManager i=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        i.hideSoftInputFromWindow(view.getWindowToken(),0);
        progressBar.setVisibility(View.VISIBLE);
        final String userEnterdusername = Username.getEditText().getText().toString().trim();
        final String userEnterdpassword = Password.getEditText().getText().toString().trim();
        Query checkuser = FirebaseDatabase.getInstance().getReference("Users").orderByChild("username").equalTo(userEnterdusername);
        checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Username.setError(null);
                    Username.setEnabled(true);
                    String systemPassword = dataSnapshot.child(userEnterdusername).child("password").getValue(String.class);
                    if (systemPassword.equals(userEnterdpassword)) {
                        Password.setError(null);
                        Password.setErrorEnabled(true);
                        Intent intent=new Intent(getApplicationContext(), UserDashboard.class);
                        startActivity(intent);
                    } else {
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(Login.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(Login.this, "No Such User Exists", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


                Toast.makeText(Login.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validateUsername() {

        String _username = Username.getEditText().getText().toString();
        if (_username.isEmpty()) {
            Username.setError("Feild Cannot be empty");
            Username.requestFocus();
            return false;
        } else {
            Username.setError(null);
            Username.setErrorEnabled(true);
            return true;
        }


    }

    private boolean validatePassword() {
        String _password = Password.getEditText().getText().toString();
        if (_password.isEmpty()) {
            Password.setError("Feild Cannot be empty");
            Password.requestFocus();
            return false;
        } else {
            Password.setError(null);
            Password.setErrorEnabled(true);
            return true;
        }
    }

}
