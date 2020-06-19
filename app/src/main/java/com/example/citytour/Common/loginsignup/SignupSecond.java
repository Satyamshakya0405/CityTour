package com.example.citytour.Common.loginsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.citytour.R;

import java.util.Calendar;

public class SignupSecond extends AppCompatActivity {

    Button next;
    RadioGroup radioGroup;
    RadioButton selectedgender;
    DatePicker datePicker;
    String Name,Username,Email,Password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_second);
        next=findViewById(R.id.btn_signupsecond_next);
        radioGroup=findViewById(R.id.radio_group);
        datePicker=findViewById(R.id.age_picker);
         Name=getIntent().getStringExtra("name");
         Username=getIntent().getStringExtra("username");
         Email=getIntent().getStringExtra("email");
         Password=getIntent().getStringExtra("password");

    }
    public void callthirdsignupscreen(View view){

        if(!validateGender()||!validateAge())
            return ;
        selectedgender=findViewById(radioGroup.getCheckedRadioButtonId());
        String Gender=selectedgender.getText().toString();
        int month=datePicker.getMonth();
        int year=datePicker.getYear();
        int day=datePicker.getDayOfMonth();
        String Date=day+"/"+month+"/"+year;
        Intent intent=new Intent(getApplicationContext(),SignupThird.class);
        intent.putExtra("Gender",Gender);
        intent.putExtra("name",Name);
        intent.putExtra("date",Date);
        intent.putExtra("username",Username);
        intent.putExtra("password",Password);
        intent.putExtra("email",Email);
        startActivity(intent);
    }
    private boolean validateGender()
    {
        if(radioGroup.getCheckedRadioButtonId()==-1) {
            Toast.makeText(this, "Please Select Gender", Toast.LENGTH_SHORT).show();
            return false;
        }
        else return true;

    }
    private boolean validateAge()
    {
        int currentyear= Calendar.getInstance().get(Calendar.YEAR);
        int userage=datePicker.getYear();
        int isAgevaild=currentyear-userage;
        if(isAgevaild<18)
        {
            Toast.makeText(this, "You are Under Age", Toast.LENGTH_SHORT).show();
            return false;
        }
        else  return true;
    }
}
