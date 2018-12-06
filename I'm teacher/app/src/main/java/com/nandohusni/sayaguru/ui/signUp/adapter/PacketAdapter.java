package com.nandohusni.sayaguru.ui.signUp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.nandohusni.sayaguru.R;
import com.nandohusni.sayaguru.ui.signUp.model.DataItem;

import java.util.List;

public class PacketAdapter extends BaseAdapter {
    List<DataItem> data;
    public PacketAdapter(List<DataItem> data) {
        this.data = data;
    }






    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemspinner,parent,false);

        TextView textview = view.findViewById(R.id.texviewName);
        textview.setText(data.get(position).getJpJenjang() + "-" + data.get(position).getJpNama());

        return view;
    }



}
