package com.example.citytour.HelperClasses.HomeAdapter;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.citytour.R;

import java.util.ArrayList;

public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.FeaturedViewHolder> {
    ArrayList<FeaturedHelperClass> featuredHelperClassList;

    public FeaturedAdapter(ArrayList<FeaturedHelperClass> featuredHelperClassList) {
        this.featuredHelperClassList = featuredHelperClassList;
    }

    @NonNull
    @Override
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_cardview,parent,false);
        FeaturedViewHolder featuredViewHolder=new FeaturedViewHolder(view);
        return featuredViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {

        FeaturedHelperClass featuredHelperClass=featuredHelperClassList.get(position);
        holder.image.setImageResource(featuredHelperClass.getImage());
        holder.title.setText(featuredHelperClass.getTitle());
        holder.description.setText(featuredHelperClass.getDescription());
    }

    @Override
    public int getItemCount() {
        return featuredHelperClassList.size();
    }

    public static class FeaturedViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView title,description;

        public FeaturedViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.featured_card_image);
            title=itemView.findViewById(R.id.featured_card_title);
            description=itemView.findViewById(R.id.featured_card_desc);
        }
    }
}
