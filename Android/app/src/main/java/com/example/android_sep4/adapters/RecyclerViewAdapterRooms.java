package com.example.android_sep4.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_sep4.R;
import com.example.android_sep4.model.Room;
import com.example.android_sep4.view.MainActivity;
import com.example.android_sep4.view.artwork.NewArtworkActivity;
import com.example.android_sep4.view.room.EditRoomActivity;

import java.util.ArrayList;

public class RecyclerViewAdapterRooms extends RecyclerView.Adapter<RecyclerViewAdapterRooms.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<Room> rooms;

    public RecyclerViewAdapterRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterRooms.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_room, parent, false);
        RecyclerViewAdapterRooms.ViewHolder viewHolder = new RecyclerViewAdapterRooms.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewAdapterRooms.ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        Room room = rooms.get(position);

        holder.locationCode.setText(room.getLocationCode());
        holder.co2Value.setText(Integer.toString(room.getMeasurementConditions().getCo2()));
        holder.lightValue.setText(Integer.toString(room.getMeasurementConditions().getLight()));
        holder.temperatureValue.setText(Integer.toString(room.getMeasurementConditions().getTemp()));
        holder.humidityValue.setText(Integer.toString(room.getMeasurementConditions().getHumidity()));
        holder.roomCapacity.setText(Integer.toString(room.getTotalCapacity()));
        holder.currentCapacity.setText(Integer.toString(room.getCurrentCapacity()));
        holder.description.setText(room.getDescription());
        //Optimal conditions
        holder.optimalTemperature.setText(Integer.toString(room.getOptimalMeasurementConditions().getTemp()));
        holder.optimalLight.setText(Integer.toString(room.getOptimalMeasurementConditions().getLight()));
        holder.optimalHumidity.setText(Integer.toString(room.getOptimalMeasurementConditions().getHumidity()));
        holder.optimalCo2.setText(Integer.toString(room.getOptimalMeasurementConditions().getCo2()));
        holder.editRoomsConditions.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    Intent intent = new Intent(v.getContext(), EditRoomActivity.class);
                    intent.putExtra("temperature", rooms.get(position).getOptimalMeasurementConditions().getTemp());
                    intent.putExtra("light", rooms.get(position).getOptimalMeasurementConditions().getLight());
                    intent.putExtra("co2", rooms.get(position).getOptimalMeasurementConditions().getCo2());
                    intent.putExtra("humidity", rooms.get(position).getOptimalMeasurementConditions().getHumidity());
                    Room room = rooms.get(position);
                    room.setExpanded(!room.isExpanded());
                    notifyItemChanged(position);
                    v.getContext().startActivity(intent);
                }
            });


        holder.parentLayoutRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on:" + rooms.get(position));
            }
        });
        boolean isExpanded = rooms.get(position).isExpanded();
        holder.expandableLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE );
    }

    @Override
    public int getItemCount() {
        return rooms.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView lightValue;
        TextView temperatureValue;
        TextView humidityValue;
        TextView co2Value;
        TextView roomCapacity;
        TextView currentCapacity;
        TextView locationCode;
        TextView description;
        TextView optimalCo2;
        TextView optimalLight;
        TextView optimalTemperature;
        TextView optimalHumidity;
        Button editRoomsConditions;
        RelativeLayout parentLayoutRoom;
        ConstraintLayout expandableLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.descriptionContent);
            co2Value = itemView.findViewById(R.id.co2TextViewId);
            locationCode = itemView.findViewById(R.id.titleTextView);
            lightValue = itemView.findViewById(R.id.lightTextViewId);
            temperatureValue = itemView.findViewById(R.id.temperatureTextViewId);
            humidityValue = itemView.findViewById(R.id.humidityTextViewID);
            roomCapacity = itemView.findViewById(R.id.roomCapacityMaxValueTextView);
            currentCapacity= itemView.findViewById(R.id.roomCapacityValueTextView);
            editRoomsConditions = itemView.findViewById(R.id.editOptimalButton);
            parentLayoutRoom = itemView.findViewById(R.id.parent_layoutRoom);
            expandableLayout = itemView.findViewById(R.id.expandableLayout);
            //Optimal conditions
            optimalCo2 = itemView.findViewById(R.id.co2OptimalTextViewId);
            optimalHumidity = itemView.findViewById(R.id.humidityOptimalTextViewID);
            optimalLight = itemView.findViewById(R.id.lightOptimalTextViewId);
            optimalTemperature = itemView.findViewById(R.id.temperatureOptimalTextViewId);
            locationCode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Room room = rooms.get(getAdapterPosition());
                    room.setExpanded(!room.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });

        }
    }
}
