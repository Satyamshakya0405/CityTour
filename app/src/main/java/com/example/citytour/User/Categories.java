package com.example.citytour.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.citytour.R;

public class Categories extends AppCompatActivity {

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        imageView=findViewById(R.id.categories_back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Categories.super.onBackPressed();
            }
        });
    }
}
