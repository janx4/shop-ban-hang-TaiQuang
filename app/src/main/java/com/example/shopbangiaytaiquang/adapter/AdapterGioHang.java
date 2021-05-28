package com.example.shopbangiaytaiquang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shopbangiaytaiquang.R;
import com.example.shopbangiaytaiquang.activity.MainActivity;
import com.example.shopbangiaytaiquang.model.GioHang;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AdapterGioHang extends BaseAdapter {
    ArrayList<GioHang> arrGioHang;
    Context context;

    public AdapterGioHang(ArrayList<GioHang> arrGioHang, Context context) {
        this.arrGioHang = arrGioHang;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrGioHang.size();
    }

    @Override
    public Object getItem(int position) {
        return arrGioHang.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        public TextView txtTenGioHang, txtGiaGioHang;
        public ImageView imgGioHang;
        public Button buttonMinus, buttonValues, buttonPlus;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.danhsachgiohang, null);
            viewHolder.txtTenGioHang = (TextView) convertView.findViewById(R.id.textviewtenmonhang);
            viewHolder.txtGiaGioHang = (TextView) convertView.findViewById(R.id.textviewgiamonhang);
            viewHolder.imgGioHang = (ImageView) convertView.findViewById(R.id.imageviewgiohang);
            viewHolder.buttonMinus = (Button) convertView.findViewById(R.id.buttonminus);
            viewHolder.buttonValues = (Button) convertView.findViewById(R.id.buttonvalues);
            viewHolder.buttonPlus = (Button) convertView.findViewById(R.id.buttonplus);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Đổ dữ liệu lên cho thông tin giỏ hàng
        GioHang gioHang = (GioHang) getItem(position);
        viewHolder.txtTenGioHang.setText(gioHang.getTenSanPham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtGiaGioHang.setText(decimalFormat.format(gioHang.getGiaSanPham()) + "đ");
        Picasso.with(context).load(gioHang.getHinhSanPham())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHolder.imgGioHang);
        viewHolder.buttonValues.setText(gioHang.getSoLuongSP()+"");

        // Ẩn hiện button dấu trừ/ dấu cộng khi số lượng hàng vượt quá 12 hoặc nhỏ hơn 1
        int soLuongHang = Integer.parseInt(viewHolder.buttonValues.getText().toString());
        if (soLuongHang >= 12){
            viewHolder.buttonPlus.setVisibility(View.INVISIBLE);
            viewHolder.buttonMinus.setVisibility(View.VISIBLE);
        } else if (soLuongHang <= 1){
            viewHolder.buttonPlus.setVisibility(View.VISIBLE);
            viewHolder.buttonMinus.setVisibility(View.INVISIBLE);
        } else {
            viewHolder.buttonPlus.setVisibility(View.VISIBLE);
            viewHolder.buttonMinus.setVisibility(View.VISIBLE);
        }

        // Cập nhật số lượng khi nhấn - hoặc +
        ViewHolder finalViewHolder = viewHolder;
        viewHolder.buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soLuongHangMoi = Integer.parseInt(finalViewHolder.buttonValues.getText().toString()) + 1;
                int soLuongHienTai = MainActivity.mangGioHang.get(position).getSoLuongSP();
                long giaHienTai = MainActivity.mangGioHang.get(position).getGiaSanPham();
                MainActivity.mangGioHang.get(position).setSoLuongSP(soLuongHangMoi);
                long giaMoiNhat = (giaHienTai * soLuongHangMoi) / soLuongHienTai;
                MainActivity.mangGioHang.get(position).setGiaSanPham(giaMoiNhat);

                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewHolder.txtGiaGioHang.setText(decimalFormat.format(gioHang.getGiaSanPham()) + "đ");

                com.example.shopbangiaytaiquang.activity.GioHang.EventUltil();
                if (soLuongHangMoi > 11){
                    finalViewHolder.buttonPlus.setVisibility(View.INVISIBLE);
                    finalViewHolder.buttonMinus.setVisibility(View.VISIBLE);
                    finalViewHolder.buttonValues.setText(String.valueOf(soLuongHangMoi));
                } else {
                    finalViewHolder.buttonPlus.setVisibility(View.VISIBLE);
                    finalViewHolder.buttonMinus.setVisibility(View.VISIBLE);
                    finalViewHolder.buttonValues.setText(String.valueOf(soLuongHangMoi));
                }
            }
        });

        viewHolder.buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soLuongHangMoi = Integer.parseInt(finalViewHolder.buttonValues.getText().toString()) - 1;
                int soLuongHienTai = MainActivity.mangGioHang.get(position).getSoLuongSP();
                long giaHienTai = MainActivity.mangGioHang.get(position).getGiaSanPham();
                MainActivity.mangGioHang.get(position).setSoLuongSP(soLuongHangMoi);
                long giaMoiNhat = (giaHienTai * soLuongHangMoi) / soLuongHienTai;
                MainActivity.mangGioHang.get(position).setGiaSanPham(giaMoiNhat);

                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewHolder.txtGiaGioHang.setText(decimalFormat.format(gioHang.getGiaSanPham()) + "đ");

                com.example.shopbangiaytaiquang.activity.GioHang.EventUltil();
                if (soLuongHangMoi < 2){
                    finalViewHolder.buttonMinus.setVisibility(View.INVISIBLE);
                    finalViewHolder.buttonPlus.setVisibility(View.VISIBLE);
                    finalViewHolder.buttonValues.setText(String.valueOf(soLuongHangMoi));
                } else {
                    finalViewHolder.buttonPlus.setVisibility(View.VISIBLE);
                    finalViewHolder.buttonMinus.setVisibility(View.VISIBLE);
                    finalViewHolder.buttonValues.setText(String.valueOf(soLuongHangMoi));
                }
            }
        });


        return convertView;
    }
}
