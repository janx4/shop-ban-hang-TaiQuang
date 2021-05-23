package com.example.shopcongnghetaiquang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.shopcongnghetaiquang.model.LoaiSanPham;

import java.util.ArrayList;

public class AdapterLoaiSP extends BaseAdapter {
    ArrayList<LoaiSanPham> arrLoaiSP;
    Context context;

    @Override
    public int getCount() {
        return arrLoaiSP.size();
    }
    // Get từng thuộc tính trong mảng
    @Override
    public Object getItem(int position) {
        return arrLoaiSP.get(position);
    }
    // Get ID của Item
    @Override
    public long getItemId(int position) {
        return position;
    }
    // Vẽ lại dòng của ListView
    public class ViewHolder{

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
