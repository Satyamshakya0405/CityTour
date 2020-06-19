package com.example.citytour.Common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.citytour.HelperClasses.SliderAdapter;
import com.example.citytour.R;
import com.example.citytour.User.UserDashboard;

public class OnBoarding extends AppCompatActivity {


    ViewPager viewPager;
    LinearLayout dotslayout;
    SliderAdapter sliderAdapter;
    TextView dots[];
    int currposition;
    Button mletsgetstartedbtn;
    Animation animation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        viewPager=findViewById(R.id.slider);
        dotslayout=findViewById(R.id.dots_layout);
        mletsgetstartedbtn=findViewById(R.id.get_started_btn);
        sliderAdapter=new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);
        adddots(0);
        mletsgetstartedbtn.setVisibility(View.INVISIBLE);
        viewPager.addOnPageChangeListener(changeListener);
mletsgetstartedbtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(getApplicationContext(),UserDashboard.class);
        startActivity(intent);
        finish();
    }
});
    }

    public void skip(View view)
    {
        startActivity(new Intent(getApplicationContext(), UserDashboard.class));
        finish();
    }
    public void next(View view)
    {
     viewPager.setCurrentItem(currposition+1);
    }
    private void adddots(int pos)
    {
        dots=new TextView[4];
        dotslayout.removeAllViews();
        for(int i=0;i<dots.length;i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dotslayout.addView(dots[i]);
        }
        if(dots.length>0)
            dots[pos].setTextColor(getResources().getColor(R.color.colorPrimary));
    }
    ViewPager.OnPageChangeListener changeListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            currposition=position;
            if(position==0)
            {
                mletsgetstartedbtn.setVisibility(View.INVISIBLE);
            }
            else if(position==1)
            {
                mletsgetstartedbtn.setVisibility(View.INVISIBLE);
            }
            else if(position==2)
            {
                mletsgetstartedbtn.setVisibility(View.INVISIBLE);
            }
            else
            {
                animation=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.sideanim);
                mletsgetstartedbtn.setAnimation(animation);
                mletsgetstartedbtn.setVisibility(View.VISIBLE);
            }
            adddots(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
