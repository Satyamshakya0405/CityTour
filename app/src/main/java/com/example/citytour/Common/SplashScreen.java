package com.example.citytour.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.citytour.R;
import com.example.citytour.User.UserDashboard;

public class SplashScreen extends AppCompatActivity {

    ImageView mimage;
    Animation sideanim;
    SharedPreferences mOnboardingsharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mimage=findViewById(R.id.Image);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Animations
        sideanim= AnimationUtils.loadAnimation(this,R.anim.sideanim);
        //set Animation on elements
        mimage.setAnimation(sideanim);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mOnboardingsharedpreferences=getSharedPreferences("Onboarding",MODE_PRIVATE);
                // Fetching the value from sharedprefrences if this key is not present it will return true
                Boolean isFirst=mOnboardingsharedpreferences.getBoolean("first",true);
                if(isFirst)
                {
                    SharedPreferences.Editor editor= mOnboardingsharedpreferences.edit();
                           editor.putBoolean("first",false);
                           editor.commit();

                    Intent intent =new Intent(SplashScreen.this, OnBoarding.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Intent intent =new Intent(SplashScreen.this, UserDashboard.class);
                    startActivity(intent);
                    finish();
                }

            }
        },3000);
    }
}
