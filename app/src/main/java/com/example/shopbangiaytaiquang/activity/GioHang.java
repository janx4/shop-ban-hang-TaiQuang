package com.example.shopbangiaytaiquang.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.example.shopbangiaytaiquang.R;
import com.example.shopbangiaytaiquang.adapter.AdapterGioHang;
import com.example.shopbangiaytaiquang.ultil.CheckConnection;

import java.text.DecimalFormat;

public class GioHang extends AppCompatActivity {

    ListView listViewGioHang;
    TextView txtThongBao;
    static TextView txtTongTien;
    Button buttonThanhToan, buttonTiepTucMua;
    Toolbar toolbarGioHang;
    AdapterGioHang adapterGioHang;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        Anhxa();
        ActionToolBar();
        CheckData();
        EventUltil();
        CatchOnItemListView();
        EventButton();
    }

    private void EventButton() {
        buttonTiepTucMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        buttonThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.mangGioHang.size() > 0){
                    Intent intent = new Intent(getApplicationContext(), ThongTinThanhToan.class);
                    startActivity(intent);
                } else {
                    CheckConnection.ShowToastShort(getApplicationContext(), "Giỏ hàng của bạn đang trống!");
                }
            }
        });
    }

    private void CatchOnItemListView() {
        listViewGioHang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(GioHang.this);
                builder.setTitle("Xác nhận xoá sản phẩm");
                builder.setMessage("Bạn chắc chắn xoá sản phẩm này khỏi giỏ hàng chứ?");

                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (MainActivity.mangGioHang.size() <= 0){
                            txtThongBao.setText(View.VISIBLE);
                        } else {
                            MainActivity.mangGioHang.remove(position);
                            adapterGioHang.notifyDataSetChanged();
                            EventUltil();
                            if(MainActivity.mangGioHang.size() <= 0){
                                txtThongBao.setVisibility(View.VISIBLE);
                            } else {
                                txtThongBao.setVisibility(View.INVISIBLE);
                                adapterGioHang.notifyDataSetChanged();
                                EventUltil();
                            }
                        }
                    }
                });

                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapterGioHang.notifyDataSetChanged();
                        EventUltil();
                    }
                });

                builder.show();
                return true;
            }
        });
    }


    public static void EventUltil() {
        long tongTien = 0;
        for (int i=0; i< MainActivity.mangGioHang.size(); i++){
            tongTien += MainActivity.mangGioHang.get(i).getGiaSanPham();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtTongTien.setText(decimalFormat.format(tongTien) + "đ");

    }

    private void CheckData() {
        if (MainActivity.mangGioHang.size() <= 0){
            adapterGioHang.notifyDataSetChanged();
            txtThongBao.setVisibility(View.VISIBLE);
            listViewGioHang.setVisibility(View.INVISIBLE);
        } else {
            adapterGioHang.notifyDataSetChanged();
            txtThongBao.setVisibility(View.INVISIBLE);
            listViewGioHang.setVisibility(View.VISIBLE);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void ActionToolBar() {
        setSupportActionBar(toolbarGioHang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarGioHang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void Anhxa() {
        listViewGioHang =(ListView) findViewById(R.id.listviewgiohang);
        txtThongBao = (TextView) findViewById(R.id.textviewthongbao);
        txtTongTien = (TextView) findViewById(R.id.textviewtongtien);
        buttonThanhToan = (Button) findViewById(R.id.buttonthanhtoangiohang);
        buttonTiepTucMua = (Button) findViewById(R.id.buttontieptucmuahang);
        toolbarGioHang = (Toolbar) findViewById(R.id.toolbargiohang);
        adapterGioHang = new AdapterGioHang(MainActivity.mangGioHang, GioHang.this);
        listViewGioHang.setAdapter(adapterGioHang);

    }
}