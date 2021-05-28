package com.example.shopbangiaytaiquang.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopbangiaytaiquang.R;
import com.example.shopbangiaytaiquang.activity.ChiTietSanPham;
import com.example.shopbangiaytaiquang.model.SanPham;
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ChiTietSanPham.class);
                    intent.putExtra("detailproduct", arraySanPham.get(getPosition()));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                }
            });
        }
    }

}
