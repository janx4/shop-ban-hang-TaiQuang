package com.example.shopcongnghetaiquang.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.widget.Toolbar;

import com.example.shopcongnghetaiquang.R;
import com.example.shopcongnghetaiquang.adapter.AdapterDienThoai;
import com.example.shopcongnghetaiquang.model.SanPham;
import com.example.shopcongnghetaiquang.ultil.CheckConnection;

import java.util.ArrayList;

public class DienThoaiActivity extends AppCompatActivity {
    Toolbar toolbarDienThoai;
    ListView listViewDienThoai;
    ArrayList<SanPham> mangDienThoai = new ArrayList<>();


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dien_thoai);
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

    private void ShowProducts(ArrayList<SanPham> mangDienThoai) {
        // Lọc các sản phẩm là điện thoại
        for (int i=0; i<mangDienThoai.size(); i++){
            if (mangDienThoai.get(i).getId() == 1){
                mangDienThoai.remove(i);
                i--;
            }
        }
        // Hiển thị sản phẩm
        AdapterDienThoai adapterDienThoai = new AdapterDienThoai(getApplicationContext(), mangDienThoai);
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