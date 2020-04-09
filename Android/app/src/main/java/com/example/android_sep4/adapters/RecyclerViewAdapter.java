package com.example.android_sep4.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_sep4.R;
import com.example.android_sep4.model.Artwork;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<Artwork> artworksNames;


    public RecyclerViewAdapter(ArrayList<Artwork> artworksNames) {
        this.artworksNames = artworksNames;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.artworkName.setText(artworksNames.get(position).getName());
        holder.artworkType.setText(artworksNames.get(position).getType());
        holder.artworkDescription.setText(artworksNames.get(position).getDescription());
        holder.artworkAuthor.setText(artworksNames.get(position).getAuthor());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on:" + artworksNames.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return artworksNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView artworkName;
        TextView artworkDescription;
        TextView artworkAuthor;
        TextView artworkType;

        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            artworkName = itemView.findViewById(R.id.artworkName);
            artworkDescription = itemView.findViewById(R.id.artworkDescription);
            artworkAuthor = itemView.findViewById(R.id.artworkAuthor);
            artworkType = itemView.findViewById(R.id.artworkType);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }

    }
}
