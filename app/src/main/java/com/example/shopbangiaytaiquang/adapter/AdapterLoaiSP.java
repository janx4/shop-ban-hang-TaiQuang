package com.example.shopbangiaytaiquang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shopbangiaytaiquang.R;
import com.example.shopbangiaytaiquang.model.LoaiSanPham;
import com.squareup.picasso.Picasso;

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
        TextView txtTenLoaiSP;
        ImageView imgLoaiSP;

    }

    public AdapterLoaiSP(ArrayList<LoaiSanPham> arrLoaiSP, Context context) {
        this.arrLoaiSP = arrLoaiSP;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_loaisp, null);
            viewHolder.txtTenLoaiSP = (TextView) convertView.findViewById(R.id.textviewloaisp);
            viewHolder.imgLoaiSP = (ImageView) convertView.findViewById(R.id.imageviewloaisp);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        LoaiSanPham loaiSanPham = (LoaiSanPham) getItem(position);
        viewHolder.txtTenLoaiSP.setText(loaiSanPham.getTenLoaiSP());
        Picasso.with(context).load(loaiSanPham.getHinhLoaiSP())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHolder.imgLoaiSP);
        return convertView;
    }
}
