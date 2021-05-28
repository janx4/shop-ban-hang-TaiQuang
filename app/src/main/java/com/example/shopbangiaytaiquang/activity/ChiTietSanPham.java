package com.example.shopbangiaytaiquang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shopbangiaytaiquang.R;
import com.example.shopbangiaytaiquang.model.GioHang;
import com.example.shopbangiaytaiquang.model.SanPham;
import com.squareup.picasso.Picasso;


public class ChiTietSanPham extends AppCompatActivity {
    Toolbar toolbarChiTiet;
    ImageView imageChiTiet;
    TextView txtTen, txtGia, txtMoTa;
    Spinner spinner;
    Button btn;

    int id = 0, giaChiTiet = 0, idSanPham = 0;
    String tenChiTiet = "";
    String anhChiTiet = "";
    String moTaChiTiet = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        Anhxa();
        ActionToolbar();
        GetInformation();
        CatchEventSpinner();
        EventClickButton();
    }
    // Gắn biểu tượng giỏ hàng lên thanh toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menugiohang, menu);
        return true;
    }
    // Bắt sự kiện nhấn vào biểu tượng giỏ hàng
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menugiohang:
                Intent intent = new Intent(getApplicationContext(), com.example.shopbangiaytaiquang.activity.GioHang.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    private void EventClickButton() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.mangGioHang.size() > 0){

                    int sl = Integer.parseInt(spinner.getSelectedItem().toString());
                    boolean exist = false;

                    // Cập nhật lại thông tin giỏ hàng, nếu vượt quá 12 sản phẩm thì set về 12
                    for (int i=0; i<MainActivity.mangGioHang.size(); i++){
                        if (MainActivity.mangGioHang.get(i).getIdSanPham() == id){
                            MainActivity.mangGioHang.get(i).setSoLuongSP(MainActivity.mangGioHang.get(i).getSoLuongSP() + sl);
                            if (MainActivity.mangGioHang.get(i).getIdSanPham() >= 12){
                                MainActivity.mangGioHang.get(i).setSoLuongSP(12);
                            }
                            MainActivity.mangGioHang.get(i).setGiaSanPham(giaChiTiet * MainActivity.mangGioHang.get(i).getSoLuongSP());
                            exist = true;
                        }
                    }
                    if (exist == false){
                        int soLuong = Integer.parseInt(spinner.getSelectedItem().toString());
                        long total = soLuong * giaChiTiet;
                        MainActivity.mangGioHang.add(new GioHang(id, tenChiTiet, total, anhChiTiet, soLuong));
                    }

                } else {
                    int soLuong = Integer.parseInt(spinner.getSelectedItem().toString());
                    long total = soLuong * giaChiTiet;
                    MainActivity.mangGioHang.add(new GioHang(id, tenChiTiet, total, anhChiTiet, soLuong));
                }
                Intent intent = new Intent(getApplicationContext(), com.example.shopbangiaytaiquang.activity.GioHang.class);
                startActivity(intent);
            }
        });
    }

    private void CatchEventSpinner() {
        Integer[] soLuong = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item, soLuong);
        spinner.setAdapter(arrayAdapter);
    }

    private void GetInformation() {

        SanPham sanPham = (SanPham) getIntent().getSerializableExtra("detailproduct");
        id = sanPham.getIdSanPham();
        tenChiTiet = sanPham.getTenSanPham();
        giaChiTiet = sanPham.getGiaSanPham();
        anhChiTiet = sanPham.getAnhSanPham();
        moTaChiTiet = sanPham.getMoTaSanPham();
        idSanPham = sanPham.getIdSanPham();

        txtTen.setText(tenChiTiet);
//        DecimalFormat decimalFormat = new DecimalFormat("###.###.###");
        txtGia.setText("Giá : " + giaChiTiet + " vnđ");
        txtMoTa.setText(moTaChiTiet);
        Picasso.with(getApplicationContext()).load(anhChiTiet)
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(imageChiTiet);
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarChiTiet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarChiTiet.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void Anhxa(){
        toolbarChiTiet = (Toolbar) findViewById(R.id.toolbarchitietsp);
        imageChiTiet = (ImageView) findViewById(R.id.imageviewchitietsp);
        txtTen = (TextView) findViewById(R.id.txttenchitietsp);
        txtGia = (TextView) findViewById(R.id.txtgiachitietsp);
        txtMoTa = (TextView) findViewById(R.id.txtviewmotachitietsp);
        spinner = (Spinner) findViewById(R.id.spinner);
        btn = (Button) findViewById(R.id.buttonthemgiohang);
    }

}
