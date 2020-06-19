package com.example.citytour.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.citytour.Common.loginsignup.RetailerScreen;
import com.example.citytour.HelperClasses.HomeAdapter.CategoriesAdapter;
import com.example.citytour.HelperClasses.HomeAdapter.CategoriesHelperClass;
import com.example.citytour.HelperClasses.HomeAdapter.FeaturedAdapter;
import com.example.citytour.HelperClasses.HomeAdapter.FeaturedHelperClass;
import com.example.citytour.HelperClasses.HomeAdapter.MostViewedAdapter;
import com.example.citytour.HelperClasses.HomeAdapter.MostViewedHelperClass;
import com.example.citytour.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class UserDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    RecyclerView featuredrecyclerView, mostviewedrecyclerview, categoriesrecyclerview;
    RecyclerView.Adapter featuredadapter, mostviewedadapter, categoriesadapter;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView drawericon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);
        featuredrecyclerView = findViewById(R.id.featured_recycler);
        featuredRecycler();
        mostviewedrecyclerview = findViewById(R.id.mostviewed_recycler);
        mostviewedrecyclerview();
        categoriesrecyclerview = findViewById(R.id.categories_recycler);
        categoriesrecyclerview();
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationview);

        drawericon=findViewById(R.id.image_drawer);
        navigationdraweer();

    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerVisible(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        super.onBackPressed();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.nav_all_categories)
        {
            startActivity(new Intent(getApplicationContext(),Categories.class));
        }
        return true;
    }
    private void navigationdraweer() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
        drawericon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawerLayout.isDrawerVisible(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else
                {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
    }

    private void categoriesrecyclerview() {
        categoriesrecyclerview.setHasFixedSize(true);
        categoriesrecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<CategoriesHelperClass> categoriesHelperClassArrayList = new ArrayList<>();
        categoriesHelperClassArrayList.add(new CategoriesHelperClass(R.drawable.icons_beach, "Marina Beach"));
        categoriesHelperClassArrayList.add(new CategoriesHelperClass(R.drawable.icons_macd, "MacDonald"));
        categoriesHelperClassArrayList.add(new CategoriesHelperClass(R.drawable.icons_park, "Amusement Park"));
        categoriesHelperClassArrayList.add(new CategoriesHelperClass(R.drawable.icons_restaurant_table, "Restaurants"));
        categoriesadapter = new CategoriesAdapter(categoriesHelperClassArrayList);
        categoriesrecyclerview.setAdapter(categoriesadapter);
    }

    private void mostviewedrecyclerview() {
        mostviewedrecyclerview.setHasFixedSize(true);
        mostviewedrecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<MostViewedHelperClass> mostViewedHelperClassArrayList = new ArrayList<>();
        mostViewedHelperClassArrayList.add(new MostViewedHelperClass(R.drawable.icons_restaurant_table, "Restaurants"));
        mostViewedHelperClassArrayList.add(new MostViewedHelperClass(R.drawable.icons_park, "Amusement Park"));
        mostViewedHelperClassArrayList.add(new MostViewedHelperClass(R.drawable.icons_macd, "MacDonald"));
        mostViewedHelperClassArrayList.add(new MostViewedHelperClass(R.drawable.icons_beach, "Marina Beach"));
        mostviewedadapter = new MostViewedAdapter(mostViewedHelperClassArrayList);
        mostviewedrecyclerview.setAdapter(mostviewedadapter);
    }

    private void featuredRecycler() {
        featuredrecyclerView.setHasFixedSize(true);
        featuredrecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<FeaturedHelperClass> featuredHelperClassArrayList = new ArrayList<>();
        featuredHelperClassArrayList.add(new FeaturedHelperClass(R.drawable.icons_beach, "Marina Beach", "Open In the morning from 5am to 9am ans in Evening 4pm to 7pm"));
        featuredHelperClassArrayList.add(new FeaturedHelperClass(R.drawable.icons_macd, "MacDonald's ", "Open 24 Hours"));
        featuredHelperClassArrayList.add(new FeaturedHelperClass(R.drawable.icons_park, "Amusement Park", "Open from 10 am to 6pm"));
        featuredHelperClassArrayList.add(new FeaturedHelperClass(R.drawable.icons_restaurant_table, "Restaurant", "Open from 11am to 11pm"));
        featuredadapter = new FeaturedAdapter(featuredHelperClassArrayList);
        featuredrecyclerView.setAdapter(featuredadapter);

    }
    public  void RetailScreen(View view)
    {
        startActivity(new Intent(getApplicationContext(), RetailerScreen.class));
    }

}
