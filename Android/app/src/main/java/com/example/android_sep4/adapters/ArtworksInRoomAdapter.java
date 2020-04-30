package com.example.android_sep4.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android_sep4.R;
import com.example.android_sep4.model.Room;

import java.util.ArrayList;

public class ArtworksInRoomAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<Room> artworksInRoom;
    private LayoutInflater layoutInflater = null;
    private ViewHolder viewHolder = null;

    public ArtworksInRoomAdapter(Activity activity, ArrayList<Room> artworksInRoom, LayoutInflater layoutInflater) {
        this.activity = activity;
        this.artworksInRoom = artworksInRoom;
        this.layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return artworksInRoom.size();
    }

    @Override
    public Object getItem(int i) {
        return artworksInRoom.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View vi = view;
        final int pos = position;
        if (vi == null) {

            // create  viewholder object for list_rowcell View.
            // inflate list_rowcell for each row
            vi = layoutInflater.inflate(R.layout.activity_room_a1, null);
            viewHolder.place_holder_1 = vi.findViewById(R.id.artwork_place_1);
            viewHolder.place_holder_2 = vi.findViewById(R.id.artwork_place_2);
            viewHolder.place_holder_3 = vi.findViewById(R.id.artwork_place_3);
            viewHolder.place_holder_4 = vi.findViewById(R.id.artwork_place_4);
            viewHolder.place_holder_5 = vi.findViewById(R.id.artwork_place_5);
            viewHolder.place_holder_6 = vi.findViewById(R.id.artwork_place_6);
            viewHolder.place_holder_7 = vi.findViewById(R.id.artwork_place_7);
            viewHolder.place_holder_8 = vi.findViewById(R.id.artwork_place_8);
            viewHolder.place_holder_9 = vi.findViewById(R.id.artwork_place_9);

            /*We can use setTag() and getTag() to set and get custom objects as per our requirement.
            The setTag() method takes an argument of type Object, and getTag() returns an Object.*/
            vi.setTag(viewHolder);
        } else {

            /* We recycle a View that already exists */
            viewHolder = (ViewHolder) vi.getTag();
        }

        viewHolder.place_holder_1.setText(artworksInRoom.get(pos).getArtworkList().get(pos).getName());
        viewHolder.place_holder_2.setText(artworksInRoom.get(pos).getArtworkList().get(pos).getName());
        viewHolder.place_holder_3.setText(artworksInRoom.get(pos).getArtworkList().get(pos).getName());
        viewHolder.place_holder_4.setText(artworksInRoom.get(pos).getArtworkList().get(pos).getName());
        viewHolder.place_holder_5.setText(artworksInRoom.get(pos).getArtworkList().get(pos).getName());
        viewHolder.place_holder_6.setText(artworksInRoom.get(pos).getArtworkList().get(pos).getName());
        viewHolder.place_holder_7.setText(artworksInRoom.get(pos).getArtworkList().get(pos).getName());
        viewHolder.place_holder_8.setText(artworksInRoom.get(pos).getArtworkList().get(pos).getName());
        viewHolder.place_holder_9.setText(artworksInRoom.get(pos).getArtworkList().get(pos).getName());

        return vi;
    }

    private static class ViewHolder {
        TextView place_holder_1, place_holder_2, place_holder_3,
                place_holder_4, place_holder_5, place_holder_6,
                place_holder_7, place_holder_8, place_holder_9;

    }
}
