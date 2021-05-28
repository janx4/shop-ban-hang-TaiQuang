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

public class AdapterConverse extends BaseAdapter {
    Context context;
    ArrayList<SanPham> arrayDienThoai;

    public AdapterConverse(Context context, ArrayList<SanPham> arrayDienThoai) {
        this.context = context;
        this.arrayDienThoai = arrayDienThoai;
    }

    @Override
    public int getCount() {
        return arrayDienThoai.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayDienThoai.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        public TextView txtTenDienThoai, txtGiaDienThoai, txtMoTaDienThoai;
        public ImageView imageDienThoai;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (viewHolder == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.danhsachdienthoai, null);
            viewHolder.txtTenDienThoai = convertView.findViewById(R.id.textviewdienthoai);
            viewHolder.txtGiaDienThoai = convertView.findViewById(R.id.textviewgiadienthoai);
            viewHolder.txtMoTaDienThoai = convertView.findViewById(R.id.textviewmotadienthoai);
            viewHolder.imageDienThoai = convertView.findViewById(R.id.imageviewdienthoai);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        SanPham sanPham = (SanPham) getItem(position);
        viewHolder.txtTenDienThoai.setText(sanPham.getTenSanPham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtGiaDienThoai.setText("Giá : "+decimalFormat.format(sanPham.getGiaSanPham()) + " vnđ");
        viewHolder.txtMoTaDienThoai.setMaxLines(2);
        viewHolder.txtMoTaDienThoai.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtMoTaDienThoai.setText(sanPham.getMoTaSanPham());
        Picasso.with(context).load(sanPham.getAnhSanPham())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHolder.imageDienThoai);
        return convertView;
    }
}
