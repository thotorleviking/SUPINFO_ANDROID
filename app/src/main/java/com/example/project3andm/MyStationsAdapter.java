package com.example.project3andm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class MyStationsAdapter extends RecyclerView.Adapter<MyStationsAdapter.MyViewHolder> {
    List<Station> mesStations;

    MyStationsAdapter(List<Station> mesStations){
        this.mesStations = mesStations;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.station_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){
        holder.display(mesStations.get(position));
    }

    @Override
    public int getItemCount(){
        return mesStations.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mStationName;

        MyViewHolder(View itemView){
            super(itemView);
            mStationName = (TextView)itemView.findViewById(R.id.name);
        }

        void display(Station station){
            mStationName.setText(station.getName());
        }
    }
}

