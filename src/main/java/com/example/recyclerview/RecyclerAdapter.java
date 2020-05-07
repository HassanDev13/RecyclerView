package com.example.recyclerview;

import android.graphics.drawable.Icon;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private static final String TAG = "RecyclerAdapter";
    private List<String> moviesList;
    private List<Integer> imageViews;
    private List<Integer> icon;


    private RecyclerViewClickInterface recyclerViewClickInterface;
    private RecyclerViewClickInterface2 recyclerViewClickInterface2;


    RecyclerAdapter(List<String> moviesList, List<Integer> imageViews,List<Integer> icon, RecyclerViewClickInterface recyclerViewClickInterface,RecyclerViewClickInterface2 recyclerViewClickInterface2) {
        this.moviesList = moviesList;
        this.imageViews = imageViews;
        this.icon = icon;
        this.recyclerViewClickInterface = recyclerViewClickInterface;
        this.recyclerViewClickInterface2 = recyclerViewClickInterface2;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(moviesList.get(position));
        holder.imageView.setImageResource(imageViews.get(position));


    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        Button icon;
        TextView textView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            icon = itemView.findViewById(R.id.icon);
            textView = itemView.findViewById(R.id.textView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recyclerViewClickInterface.onItemClick(getAdapterPosition());
                }
            });

            icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recyclerViewClickInterface2.onItemClickIcon(getAdapterPosition());
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    recyclerViewClickInterface.onLongItemClick(getAdapterPosition());
                    return true;
                }
            });

        }

    }

}















