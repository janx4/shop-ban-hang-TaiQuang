package com.example.shopbangiaytaiquang.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.shopbangiaytaiquang.R;
import com.example.shopbangiaytaiquang.adapter.AdapterNike;
import com.example.shopbangiaytaiquang.model.SanPham;
import com.example.shopbangiaytaiquang.ultil.CheckConnection;

import java.util.ArrayList;

public class NikeActivity extends AppCompatActivity {
    Toolbar toolbarLaptop;
    ListView listViewLaptop;
    ArrayList<SanPham> mangLaptop = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nike);
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

    private void ShowProducts(ArrayList<SanPham> mangLapTop) {
        // Lọc các sản phẩm là laptop
        for (int i=0; i<mangLapTop.size(); i++){
            if (mangLapTop.get(i).getId() == 0){
                mangLapTop.remove(i);
                i--;
            }
        }
        // Hiển thị sản phẩm
        AdapterNike adapterNike = new AdapterNike(getApplicationContext(), mangLapTop);
        listViewLaptop.setAdapter(adapterNike);

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