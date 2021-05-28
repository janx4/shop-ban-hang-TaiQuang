package com.example.shopbangiaytaiquang.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.widget.Toolbar;

import com.example.shopbangiaytaiquang.R;
import com.example.shopbangiaytaiquang.adapter.AdapterConverse;
import com.example.shopbangiaytaiquang.model.SanPham;
import com.example.shopbangiaytaiquang.ultil.CheckConnection;

import java.util.ArrayList;

public class ConverseActivity extends AppCompatActivity {
    Toolbar toolbarDienThoai;
    ListView listViewDienThoai;
    ArrayList<SanPham> mangDienThoai = new ArrayList<>();


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converse);
        Anhxa();
        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
            FetchDataDienThoai();
            ActionToolbar();
            ShowProducts(mangDienThoai);
        } else {
            CheckConnection.ShowToastShort(getApplicationContext(), "Không có kết nối internet!");
            finish();
        }
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

    private void ShowProducts(ArrayList<SanPham> mangDienThoai) {
        // Lọc các sản phẩm là điện thoại
        for (int i=0; i<mangDienThoai.size(); i++){
            if (mangDienThoai.get(i).getId() == 1){
                mangDienThoai.remove(i);
                i--;
            }
        }


        // Hiển thị sản phẩm
        AdapterConverse adapterDienThoai = new AdapterConverse(getApplicationContext(), mangDienThoai);
        listViewDienThoai.setAdapter(adapterDienThoai);

        listViewDienThoai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ChiTietSanPham.class);
                intent.putExtra("detailproduct", mangDienThoai.get(position));

                startActivity(intent);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void ActionToolbar() {
        setSupportActionBar(toolbarDienThoai);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarDienThoai.setNavigationOnClickListener(v -> finish());
    }

    private void FetchDataDienThoai() {
        mangDienThoai = (ArrayList<SanPham>) getIntent().getSerializableExtra("dataSanPham");

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void Anhxa() {
        toolbarDienThoai = (Toolbar) findViewById(R.id.toolbardienthoai);
        listViewDienThoai = (ListView) findViewById(R.id.listviewdientthoai);


    }
}