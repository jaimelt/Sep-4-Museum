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
import com.example.android_sep4.model.Room;

import java.util.ArrayList;

public class RecyclerViewAdapterRooms extends RecyclerView.Adapter<RecyclerViewAdapterRooms.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<Room> roomsName;


    public RecyclerViewAdapterRooms(ArrayList<Room> roomsName) {
        this.roomsName = roomsName;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterRooms.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listroom, parent, false);
        RecyclerViewAdapterRooms.ViewHolder viewHolder = new RecyclerViewAdapterRooms.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterRooms.ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.roomName.setText(roomsName.get(position).getLocationCode()+" - "+roomsName.get(position).getRoomType());

        holder.parentLayoutRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on:" + roomsName.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return roomsName.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView roomName;
        RelativeLayout parentLayoutRoom;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            roomName = itemView.findViewById(R.id.RoomName);
            parentLayoutRoom = itemView.findViewById(R.id.parent_layoutRoom);
        }

    }
}
