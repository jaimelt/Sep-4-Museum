package com.example.android_sep4.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_sep4.R;
import com.example.android_sep4.model.Room;
import com.example.android_sep4.view.room.EditRoomsConditionsActivity;
import com.example.android_sep4.view.room.RoomArtworksActivity;
import com.example.android_sep4.view.room.VisualizeDataActivity;

import java.util.ArrayList;

public class RoomsAdapter extends RecyclerView.Adapter<RoomsAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<Room> rooms;
    private Context context;

    public RoomsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RoomsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_room, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RoomsAdapter.ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        Room room = rooms.get(position);

        //Preference from settings for Celsius/Fahrenheit unit
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        boolean prefTemperature = sharedPreferences.getBoolean(context.getString(R.string.pref_temperature_key), context.getResources().getBoolean(R.bool.pref_temperature_default));
        if (prefTemperature) {
            holder.temperatureUnit.setText(context.getString(R.string.temperature_unit_celsius));
        } else {
            holder.temperatureUnit.setText(context.getString(R.string.temperature_unit_fahrenheit));
        }

        holder.locationCode.setText(room.getLocationCode());
        if (room.getLiveRoomMeasurements() != null) {
            holder.co2Value.setText(String.valueOf(room.getLiveRoomMeasurements().getCo2()));
            holder.lightValue.setText(String.valueOf(room.getLiveRoomMeasurements().getLight()));
            holder.temperatureValue.setText(String.valueOf(room.getLiveRoomMeasurements().getTemp()));
            holder.humidityValue.setText(String.valueOf(room.getLiveRoomMeasurements().getHumidity()));
        }
        holder.roomCapacity.setText(String.valueOf(room.getTotalCapacity()));
        holder.currentCapacity.setText(String.valueOf(room.getCurrentCapacity()));
        holder.description.setText(room.getDescription());
        holder.optimalTemperature.setText(String.valueOf(room.getTemperature()));
        holder.optimalLight.setText(String.valueOf(room.getLight()));
        holder.optimalHumidity.setText(String.valueOf(room.getHumidity()));
        holder.optimalCo2.setText(String.valueOf(room.getCo2()));
        holder.viewRoomArtworks.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), RoomArtworksActivity.class);
            Room room1 = rooms.get(position);
            room1.setExpanded(!room1.isExpanded());
            notifyItemChanged(position);
            intent.putExtra("locationCode", room1.getLocationCode());
            System.out.println(room1.getLocationCode());
            v.getContext().startActivity(intent);
        });
        holder.visulizeData.setOnClickListener(v -> {

            Intent intent = new Intent(v.getContext(), VisualizeDataActivity.class);
            intent.putExtra("optimalTemperature", rooms.get(position).getTemperature());
            intent.putExtra("optimalLight", rooms.get(position).getLight());
            intent.putExtra("optimalCo2", rooms.get(position).getCo2());
            intent.putExtra("optimalHumidity", rooms.get(position).getHumidity());
            if (rooms.get(position).getLiveRoomMeasurements() != null) {
                intent.putExtra("liveCo2", rooms.get(position).getLiveRoomMeasurements().getCo2());
                intent.putExtra("liveHumidity", rooms.get(position).getLiveRoomMeasurements().getHumidity());
                intent.putExtra("liveTemp", rooms.get(position).getLiveRoomMeasurements().getTemp());
                intent.putExtra("liveLight", rooms.get(position).getLiveRoomMeasurements().getLight());
            }
            intent.putExtra("locationCode", rooms.get(position).getLocationCode());

            Room room12 = rooms.get(position);
            room12.setExpanded(!room12.isExpanded());
            notifyItemChanged(position);
            v.getContext().startActivity(intent);
        });

        holder.editRoomsConditions.setOnClickListener(v -> {

            Intent intent = new Intent(v.getContext(), EditRoomsConditionsActivity.class);
            intent.putExtra("optimalTemperature", rooms.get(position).getTemperature());
            intent.putExtra("optimalLight", rooms.get(position).getLight());
            intent.putExtra("optimalCo2", rooms.get(position).getCo2());
            intent.putExtra("optimalHumidity", rooms.get(position).getHumidity());
            intent.putExtra("locationCode", rooms.get(position).getLocationCode());
            intent.putExtra("description", rooms.get(position).getDescription());
            intent.putExtra("totalCapacity", rooms.get(position).getTotalCapacity());
            intent.putExtra("currentCapacity", rooms.get(position).getCurrentCapacity());

            Room room12 = rooms.get(position);
            room12.setExpanded(!room12.isExpanded());
            notifyItemChanged(position);
            v.getContext().startActivity(intent);
        });


        holder.parentLayoutRoom.setOnClickListener(view -> Log.d(TAG, "onClick: clicked on:" + rooms.get(position)));
        boolean isExpanded = rooms.get(position).isExpanded();
        holder.expandableLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        if (rooms != null) {
            return rooms.size();
        }
        return 0;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
        notifyDataSetChanged();
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
        Button visulizeData;
        Button editRoomsConditions;
        Button viewRoomArtworks;
        RelativeLayout parentLayoutRoom;
        ConstraintLayout expandableLayout;
        TextView temperatureUnit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.description_content);
            co2Value = itemView.findViewById(R.id.co2_text_view_id);
            locationCode = itemView.findViewById(R.id.title);
            lightValue = itemView.findViewById(R.id.light_text_view_id);
            temperatureValue = itemView.findViewById(R.id.temperature_text_view_id);
            humidityValue = itemView.findViewById(R.id.humidity_text_view_id);
            roomCapacity = itemView.findViewById(R.id.room_capacity_max_value_text_view);
            currentCapacity = itemView.findViewById(R.id.room_capacity_value_text_view);
            editRoomsConditions = itemView.findViewById(R.id.edit_optimal_button);
            parentLayoutRoom = itemView.findViewById(R.id.parent_layout_room);
            expandableLayout = itemView.findViewById(R.id.expandable_layout);
            viewRoomArtworks = itemView.findViewById(R.id.show_room_artworks);
            visulizeData = itemView.findViewById(R.id.show_room_measurements);
            optimalCo2 = itemView.findViewById(R.id.co2_optimal_text_view_id);
            optimalHumidity = itemView.findViewById(R.id.humidity_optimal_text_view_id);
            optimalLight = itemView.findViewById(R.id.light_optimal_text_view_id);
            optimalTemperature = itemView.findViewById(R.id.temperature_optimal_text_view_id);
            temperatureUnit = itemView.findViewById(R.id.measurements_sign_temperature);

            locationCode.setOnClickListener(view -> {
                Room room = rooms.get(getAdapterPosition());
                room.setExpanded(!room.isExpanded());
                notifyItemChanged(getAdapterPosition());
            });

        }
    }
}
