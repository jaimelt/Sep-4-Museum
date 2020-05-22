package com.example.android_sep4.adapters;

import android.content.Context;
import android.content.Intent;
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
import com.example.android_sep4.view.artwork.EditArtworkActivity;
import com.example.android_sep4.view.artwork.StorageActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerViewAdapterStorage extends RecyclerView.Adapter<RecyclerViewAdapterStorage.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<Artwork> artworks;
    private Context context;
    final private OnListItemClickListener mOnListItemClickListener;


    public RecyclerViewAdapterStorage( Context context, OnListItemClickListener listener) {
        this.context = context;
        mOnListItemClickListener = listener;
    }

    public interface OnListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_artwork, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        Uri uri = Uri.parse(artworks.get(position).getImage());
        holder.imageView.setImageURI(uri);
        Picasso.with(context).load(uri).resize(0, 300).into(holder.imageView);
        holder.artworkName.setText(artworks.get(position).getName());
        holder.artworkType.setText(artworks.get(position).getType());
        holder.artworkDescription.setText(artworks.get(position).getDescription());
        holder.artworkAuthor.setText(artworks.get(position).getAuthor());

        //TODO:Setting image from local storage
    }

    @Override
    public int getItemCount() {
        if (artworks != null) {
            return artworks.size();
        } else {
            return 0;
        }
    }


    public void setArtworks(ArrayList<Artwork> artworks) {
        this.artworks = artworks;
        notifyDataSetChanged();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageView;
        TextView artworkName;
        TextView artworkDescription;
        TextView artworkAuthor;
        TextView artworkType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            artworkName = itemView.findViewById(R.id.artworkName);
            artworkDescription = itemView.findViewById(R.id.artworkDescription);
            artworkAuthor = itemView.findViewById(R.id.artworkAuthor);
            artworkType = itemView.findViewById(R.id.artworkType);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnListItemClickListener.onListItemClick(getAdapterPosition());
        }
    }

}
