package com.example.citytour.Common.loginsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.citytour.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Signupfirst extends AppCompatActivity {

    ImageView backbtn;
    TextView title;
    Button next,login;
    TextInputLayout name,username,email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupfirst);
        backbtn=findViewById(R.id.btn_signupfirst_back);
        title=findViewById(R.id.text_signupfirst_title);
        next=findViewById(R.id.btn_signupfirst_next);
        login=findViewById(R.id.btn_signupfirst_Login);
        name=findViewById(R.id.signup_name);
        username=findViewById(R.id.signup_username);
        email=findViewById(R.id.signup_email);
        password=findViewById(R.id.signup_password);
    }
    public void callnextsignupscreen(View view)
    {
        if(!validateName()||!validateUserName()||!validateEmail()||!validatePassword())
        {
            return ;
        }
        String Name=name.getEditText().getText().toString();

        String Username=username.getEditText().getText().toString();
        String Password=password.getEditText().getText().toString();
        String Email=email.getEditText().getText().toString();
        Intent intent=new Intent(getApplicationContext(),SignupSecond.class);
        intent.putExtra("name",Name);
        intent.putExtra("username",Username);
        intent.putExtra("password",Password);
        intent.putExtra("email",Email);
        Pair pair[]=new Pair[4];
        pair[0]=new Pair<View,String>(backbtn,"transition_back_btn");
        pair[1]=new Pair<View,String>(title,"transition_title_text");
        pair[2]=new Pair<View,String>(next,"transition_next_btn");
        pair[3]=new Pair<View,String>(login,"transition_login_btn");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(Signupfirst.this,pair);
            startActivity(intent,options.toBundle());
        }
        else
        {
            startActivity(intent);
        }
    }
    //validation
    private Boolean validateName()
    {
        String val=name.getEditText().getText().toString();
        if(val.isEmpty())
        {
            name.setError("Field cannot be empty");
            return false;
        }
        else
        {
            name.setError(null);
            name.setErrorEnabled(true);
            return true;
        }
    }
    private Boolean validateUserName()
    {
        String val=username.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";
        if(val.isEmpty())
        {
            username.setError("Field cannot be empty");
            return false;
        }
        else if(val.length()<=4)
        {
            username.setError("Too short");
            return false;
        }
        else if(val.length()>=15)
        {
            username.setError("Username Too Long");
            return false;
        }
        else if(!val.matches(noWhiteSpace))
        {
            username.setError("White spaces are not allowed");
            return false;
        }
        else
        {
            username.setError(null);
            username.setErrorEnabled(true);
            return true;
        }
    }
    private Boolean validateEmail() {
        String val=email.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(val.isEmpty())
        {
            email.setError("Field cannot be empty");
            return false;
        }
        else if(!val.matches(emailPattern))
        {
            email.setError("Invalid Email");
            return false;
        }
        else
        {
            email.setError(null);
            email.setErrorEnabled(true);
            return true;
        }
    }
    private Boolean validatePassword() {
        String val = password.getEditText().getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            password.setError("Password is too weak");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }
}
