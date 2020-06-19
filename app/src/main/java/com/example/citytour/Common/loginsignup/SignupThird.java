package com.example.citytour.Common.loginsignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.citytour.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class SignupThird extends AppCompatActivity {
    TextView text;
    Button next;
    TextInputLayout phone;
    CountryCodePicker countryCodePicker;
    String Name,Username,Email,Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_third);
        next=findViewById(R.id.btn_signupthird_next);
        phone=findViewById(R.id.edittext_phone);
        countryCodePicker=findViewById(R.id.CodePicker);
        Name=getIntent().getStringExtra("name");
        Username=getIntent().getStringExtra("username");
        Email=getIntent().getStringExtra("email");
        Password=getIntent().getStringExtra("password");
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager i=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                i.hideSoftInputFromWindow(v.getWindowToken(),0);
                String phonenumberbyuser=phone.getEditText().getText().toString();
//                String phoneNo="+"+countryCodePicker.getFullNumber()+phonenumberbyuser;
                Intent intent=new Intent(getApplicationContext(),Codeverfication.class);
                intent.putExtra("name",Name);
                intent.putExtra("username",Username);
                intent.putExtra("password",Password);
                intent.putExtra("email",Email);
                intent.putExtra("phonenumber",phonenumberbyuser);
                startActivity(intent);

            }
        });
    }


}
