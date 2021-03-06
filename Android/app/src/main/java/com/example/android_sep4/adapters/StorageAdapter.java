package com.example.android_sep4.adapters;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_sep4.R;
import com.example.android_sep4.model.Artwork;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StorageAdapter extends RecyclerView.Adapter<StorageAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    final private OnListItemClickListener mOnListItemClickListener;
    private ArrayList<Artwork> artworks;
    private Context context;

    public StorageAdapter(Context context, OnListItemClickListener listener) {
        this.context = context;
        mOnListItemClickListener = listener;
    }

    public void deleteArtwork(int position) {
        artworks.remove(position);
        notifyDataSetChanged();
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


    public interface OnListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        TextView artworkName;
        TextView artworkDescription;
        TextView artworkAuthor;
        TextView artworkType;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.artwork_image);
            artworkName = itemView.findViewById(R.id.artwork_name);
            artworkDescription = itemView.findViewById(R.id.artwork_description);
            artworkAuthor = itemView.findViewById(R.id.artwork_author);
            artworkType = itemView.findViewById(R.id.artwork_type);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnListItemClickListener.onListItemClick(getAdapterPosition());
        }
    }

}
