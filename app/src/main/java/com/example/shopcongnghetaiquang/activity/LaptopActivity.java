package com.example.shopcongnghetaiquang.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.shopcongnghetaiquang.R;
import com.example.shopcongnghetaiquang.adapter.AdapterLaptop;
import com.example.shopcongnghetaiquang.model.SanPham;
import com.example.shopcongnghetaiquang.ultil.CheckConnection;

import java.util.ArrayList;

public class LaptopActivity extends AppCompatActivity {
    Toolbar toolbarLaptop;
    ListView listViewLaptop;
    ArrayList<SanPham> mangLaptop = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laptop);
        Anhxa();
        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
            FetchDataLapTop();
            ActionToolbar();
            ShowProducts(mangLaptop);
        } else {
            CheckConnection.ShowToastShort(getApplicationContext(), "Không có kết nối internet!");
            finish();
        }


    }



    private void ShowProducts(ArrayList<SanPham> mangLapTop) {
        // Lọc các sản phẩm là laptop
        for (int i=0; i<mangLapTop.size(); i++){
            if (mangLapTop.get(i).getId() == 0){
                mangLapTop.remove(i);
                i--;
            }
        }
        // Hiển thị sản phẩm
        AdapterLaptop adapterLaptop = new AdapterLaptop(getApplicationContext(), mangLapTop);
        listViewLaptop.setAdapter(adapterLaptop);

        // Lắng nghe sự kiện click vào sản phẩm -> Thông tin chi tiết sản phẩm
        listViewLaptop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ChiTietSanPham.class);
                intent.putExtra("detailproduct", mangLapTop.get(position));
                startActivity(intent);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void ActionToolbar() {
        setSupportActionBar(toolbarLaptop);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarLaptop.setNavigationOnClickListener(v -> finish());
    }

    private void FetchDataLapTop() {
        mangLaptop = (ArrayList<SanPham>) getIntent().getSerializableExtra("dataSanPham");

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void Anhxa() {
        toolbarLaptop = (Toolbar) findViewById(R.id.toolbarlaptop);
        listViewLaptop = (ListView) findViewById(R.id.listviewlaptop);


    }
}