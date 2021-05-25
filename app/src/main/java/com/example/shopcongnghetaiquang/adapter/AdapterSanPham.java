package com.example.shopcongnghetaiquang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopcongnghetaiquang.R;
import com.example.shopcongnghetaiquang.model.SanPham;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.ArrayList;

// Khai báo view holder cho recyclerview
public class AdapterSanPham extends RecyclerView.Adapter<AdapterSanPham.ItemHolder> {
    Context context;
    ArrayList<SanPham> arraySanPham;

    public AdapterSanPham(Context context, ArrayList<SanPham> arraySanPham) {
        this.context = context;
        this.arraySanPham = arraySanPham;
    }

    @NonNull
    @NotNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sanphamnoibat, null);
        ItemHolder itemHolder = new ItemHolder(v);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterSanPham.ItemHolder holder, int position) {
        SanPham sanPham = arraySanPham.get(position);
        holder.txtTenSP.setText(sanPham.getTenSanPham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtGiaSP.setText("Giá : "+decimalFormat.format(sanPham.getGiaSanPham()) + " vnđ");
        Picasso.with(context).load(sanPham.getAnhSanPham())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(holder.imgHinhSP);
    }

    @Override
    public int getItemCount() {
        return arraySanPham.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public ImageView imgHinhSP;
        public TextView txtTenSP, txtGiaSP;

        public ItemHolder (View itemView){
            super(itemView);
            imgHinhSP = (ImageView) itemView.findViewById(R.id.imageviewsanpham);
            txtGiaSP = (TextView) itemView.findViewById(R.id.textviewgiasp);
            txtTenSP = (TextView) itemView.findViewById(R.id.textviewtensp);
        }
    }

}
