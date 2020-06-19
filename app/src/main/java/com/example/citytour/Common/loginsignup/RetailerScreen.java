package com.example.citytour.Common.loginsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;

import com.example.citytour.R;

public class RetailerScreen extends AppCompatActivity {

    Button login,signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_screen);
        login=findViewById(R.id.login);
        signup=findViewById(R.id.btn_retailer_signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Signupfirst.class);
                startActivity(intent);
            }
        });
    }
    public void callLogin(View view)
    {
        Intent intent=new Intent(getApplicationContext(),Login.class);
        Pair[] pairs=new Pair[1];
        pairs[0]=new Pair<View,String>(login,"transtion_login");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(RetailerScreen.this,pairs);
            startActivity(intent,options.toBundle());
        }
        else startActivity(intent);
    }
}
