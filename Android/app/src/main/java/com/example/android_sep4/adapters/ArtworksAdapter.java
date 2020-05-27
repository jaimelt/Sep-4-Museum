package com.example.android_sep4.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_sep4.view.artwork.EditArtworkActivity;
import com.squareup.picasso.Picasso;
import com.example.android_sep4.R;
import com.example.android_sep4.model.Artwork;

import java.util.ArrayList;

public class ArtworksAdapter extends RecyclerView.Adapter<ArtworksAdapter.ViewHolder> implements Filterable {

    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<Artwork> artworks;
    private ArrayList<Artwork> copyOfArtworks;
    private OnListItemClickListener mOnListItemClickListener;
    private Context context;
    private Filter filter = new Filter() {
        @Override
        //constraint = input from the search function
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Artwork> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(copyOfArtworks);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Artwork artwork : copyOfArtworks) {
                    if (artwork.getName().toLowerCase().contains(filterPattern) || artwork.getAuthor().toLowerCase().contains(filterPattern)) {
                        filteredList.add(artwork);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        @SuppressWarnings("unchecked")
        protected void publishResults(CharSequence constraint, FilterResults results) {
            //Clean the original list of artworks and we want to replace it with filtered list
            artworks.clear();
            artworks.addAll((ArrayList<Artwork>) results.values);
            notifyDataSetChanged();
        }
    };

    public ArtworksAdapter(ArrayList<Artwork> artworks, Context context, OnListItemClickListener listener) {
        //Creating a duplicate of original list of artworks to not mess up with the original one
//        copyOfArtworks = new ArrayList<>(artworks);
        this.artworks = artworks;
        mOnListItemClickListener = listener;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_artwork, parent, false);
        return new ViewHolder(view, mOnListItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        Uri uri = Uri.parse(artworks.get(position).getImage());
//        holder.imageView.setImageURI(uri);
        Picasso.with(context).load(uri).resize(0, 300).into(holder.imageView);
        holder.artworkName.setText(artworks.get(position).getName());
        holder.artworkType.setText(artworks.get(position).getType());
        holder.artworkDescription.setText(artworks.get(position).getDescription());
        holder.artworkAuthor.setText(artworks.get(position).getAuthor());

        setColors(artworks.get(position).getType(), holder);
        //TODO:Setting image from local storage

        holder.parentLayout.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), EditArtworkActivity.class);
            intent.putExtra("id", artworks.get(position).getId());
            intent.putExtra("position", artworks.get(position).getArtworkPosition());
            v.getContext().startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        if (artworks != null) {
            return artworks.size();
        } else {
            return 0;
        }
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    public void setArtworks(ArrayList<Artwork> artworks) {
        this.artworks = artworks;
        notifyDataSetChanged();
    }

    private void setColors(String type, ViewHolder holder) {
        switch (type) {
            case "Painting":
                holder.artworkType.setTextColor(Color.parseColor("#4ACFAC"));
                break;
            case "Drawing":
                holder.artworkType.setTextColor(Color.parseColor("#FFA48E"));
                break;
            case "Ceramics":
                holder.artworkType.setTextColor(Color.parseColor("#F45C51"));
                break;
            case "Photo":
                holder.artworkType.setTextColor(Color.parseColor("#7E8CE0"));
                break;
        }
    }

    public interface OnListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        TextView artworkName;
        TextView artworkDescription;
        TextView artworkAuthor;
        TextView artworkType;

        RelativeLayout parentLayout;
        OnListItemClickListener onListItemClickListener;

        public ViewHolder(@NonNull View itemView, OnListItemClickListener listener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            artworkName = itemView.findViewById(R.id.artworkName);
            artworkDescription = itemView.findViewById(R.id.artworkDescription);
            artworkAuthor = itemView.findViewById(R.id.artworkAuthor);
            artworkType = itemView.findViewById(R.id.artworkType);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            onListItemClickListener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onListItemClickListener.onListItemClick(getAdapterPosition());
        }
    }

}
