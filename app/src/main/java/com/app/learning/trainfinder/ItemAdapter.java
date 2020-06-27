package com.app.learning.trainfinder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ExampleViewHolder> {
    private ArrayList<Row_item> mRow_items;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder{

        public TextView mTrain_Numer;
        public TextView mTrain_Name;
        public TextView mArrival_Time;
        public TextView mDeparture_Time;
        public TextView mTravelTime;

        public ExampleViewHolder(View itemView)
        {
            super(itemView);
            mTrain_Numer=itemView.findViewById(R.id.trn_num);
            mTrain_Name=itemView.findViewById(R.id.tr_name);
            mArrival_Time=itemView.findViewById(R.id.arr_time);
            mDeparture_Time=itemView.findViewById(R.id.dept_time);
            mTravelTime=itemView.findViewById(R.id.trv_time);
        }

    }

    public ItemAdapter(ArrayList<Row_item> RowList){
        mRow_items=RowList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);
        ExampleViewHolder evh=new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        Row_item currenItem=mRow_items.get(position);
        holder.mTravelTime.setText(currenItem.getTravel_Time());
        holder.mDeparture_Time.setText(currenItem.getDeparture_Time());
        holder.mArrival_Time.setText(currenItem.getArrival_Time());
        holder.mTrain_Name.setText(currenItem.getTrain_Name());
        holder.mTrain_Numer.setText(currenItem.getTrain_Number());
    }

    @Override
    public int getItemCount() {
        return mRow_items.size();
    }
}
