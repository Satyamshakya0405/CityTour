package com.example.citytour.HelperClasses.HomeAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.citytour.R;

import java.util.ArrayList;

public class MostViewedAdapter extends RecyclerView.Adapter<MostViewedAdapter.MostViewedViewholder> {

    ArrayList<MostViewedHelperClass> mostViewedHelperClassArrayList;

    public MostViewedAdapter(ArrayList<MostViewedHelperClass> mostViewedHelperClassArrayList) {
        this.mostViewedHelperClassArrayList = mostViewedHelperClassArrayList;
    }

    @NonNull
    @Override
    public MostViewedViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.mostviewed_cardview,parent,false);

        return new MostViewedViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MostViewedViewholder holder, int position) {
        MostViewedHelperClass mostViewedHelperClass=mostViewedHelperClassArrayList.get(position);
        holder.imageView.setImageResource(mostViewedHelperClass.getImage());
        holder.title.setText(mostViewedHelperClass.getTitle());


    }

    @Override
    public int getItemCount() {
        return mostViewedHelperClassArrayList.size();
    }


    public static class MostViewedViewholder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView title;
        public MostViewedViewholder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.mostviewed_image);
            title=itemView.findViewById(R.id.mostviewed_title);
        }
    }
}
