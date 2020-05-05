package com.example.android_sep4.adapters;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_sep4.R;
import com.example.android_sep4.model.Artwork;

import java.util.ArrayList;

public class RecyclerViewAdapterRoomArtworks extends RecyclerView.Adapter<RecyclerViewAdapterRoomArtworks.ViewHolder> {
    private static final String TAG = "RecycleViewAdapterRoom";
    private ArrayList<Artwork> artworks;
    private Context context;

    public RecyclerViewAdapterRoomArtworks(ArrayList<Artwork> artworks, Context context) {
        this.artworks = artworks;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listroom_artworks, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        Uri uri = Uri.parse(artworks.get(position).getImage());
        holder.imageView.setImageURI(uri);
        holder.artworkName.setText(artworks.get(position).getName());
        holder.artworkType.setText(artworks.get(position).getType());
        holder.artworkDescription.setText(artworks.get(position).getDescription());
        holder.artworkAuthor.setText(artworks.get(position).getAuthor());
    }

    @Override
    public int getItemCount() {
        return artworks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        ImageView imageView;
        TextView artworkName;
        TextView artworkDescription;
        TextView artworkAuthor;
        TextView artworkType;

        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            artworkName = itemView.findViewById(R.id.artworkName);
            artworkDescription = itemView.findViewById(R.id.artworkDescription);
            artworkAuthor = itemView.findViewById(R.id.artworkAuthor);
            artworkType = itemView.findViewById(R.id.artworkType);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
