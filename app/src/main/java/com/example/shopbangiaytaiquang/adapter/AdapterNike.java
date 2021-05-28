package com.example.shopbangiaytaiquang.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shopbangiaytaiquang.R;
import com.example.shopbangiaytaiquang.model.SanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AdapterNike extends BaseAdapter {
    Context context;
    ArrayList<SanPham> arrayLaptop;

    public AdapterNike(Context context, ArrayList<SanPham> arrayLaptop) {
        this.context = context;
        this.arrayLaptop = arrayLaptop;
    }

    @Override
    public int getCount() {
        return arrayLaptop.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayLaptop.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        public TextView txtTenLaptop, txtGiaLaptop, txtMoTaLaptop;
        public ImageView imageLaptop;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (viewHolder == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.danhsachlaptop, null);
            viewHolder.txtTenLaptop = convertView.findViewById(R.id.textviewlaptop);
            viewHolder.txtGiaLaptop = convertView.findViewById(R.id.textviewgialaptop);
            viewHolder.txtMoTaLaptop = convertView.findViewById(R.id.textviewmotalaptop);
            viewHolder.imageLaptop = convertView.findViewById(R.id.imageviewlaptop);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        SanPham sanPham = (SanPham) getItem(position);
        viewHolder.txtTenLaptop.setText(sanPham.getTenSanPham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtGiaLaptop.setText("Giá : "+decimalFormat.format(sanPham.getGiaSanPham()) + " vnđ");
        viewHolder.txtMoTaLaptop.setMaxLines(2);
        viewHolder.txtMoTaLaptop.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtMoTaLaptop.setText(sanPham.getMoTaSanPham());
        Picasso.with(context).load(sanPham.getAnhSanPham())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHolder.imageLaptop);
        return convertView;
    }
}
