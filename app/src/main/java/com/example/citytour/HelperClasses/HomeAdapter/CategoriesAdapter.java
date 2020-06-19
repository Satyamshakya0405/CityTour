package com.example.citytour.HelperClasses.HomeAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.citytour.R;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter< CategoriesAdapter.CategoriesViewHolder> {
    ArrayList<CategoriesHelperClass> categoriesHelperClassArrayList;

    public CategoriesAdapter(ArrayList<CategoriesHelperClass> categoriesHelperClassArrayList) {
        this.categoriesHelperClassArrayList = categoriesHelperClassArrayList;
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_cardview,parent,false);
        return new CategoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {
        CategoriesHelperClass categoriesHelperClass=categoriesHelperClassArrayList.get(position);
        holder.imageView.setImageResource(categoriesHelperClass.getImage());
        holder.title.setText(categoriesHelperClass.getTitle());

    }

    @Override
    public int getItemCount() {
        return categoriesHelperClassArrayList.size();
    }

    public static class CategoriesViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView title;
        public CategoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.categories_image);
            title=itemView.findViewById(R.id.categories_title);

        }
    }
}
