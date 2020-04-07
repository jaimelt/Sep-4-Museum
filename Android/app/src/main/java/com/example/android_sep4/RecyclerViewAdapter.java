package com.example.myalgosvisualizer;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<String> myAlgosNames;
    private Context context;


    public RecyclerViewAdapter(ArrayList<String> myAlgosNames, Context context) {
        this.myAlgosNames = myAlgosNames;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.algoName.setText(myAlgosNames.get(position));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on:" + myAlgosNames.get(position));

                Toast.makeText(context, myAlgosNames.get(position), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, SortActivity.class);
                intent.putExtra("algo_name", myAlgosNames.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myAlgosNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView algoName;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            algoName = itemView.findViewById(R.id.algoName);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }

    }
}
