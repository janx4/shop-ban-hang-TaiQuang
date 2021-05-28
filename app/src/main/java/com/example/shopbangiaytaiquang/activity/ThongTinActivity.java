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
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;

import com.example.shopbangiaytaiquang.R;

public class ThongTinActivity extends AppCompatActivity {
    Toolbar toolbarVeChungToi;
    Button buttonVeTrangTruoc;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin);
        Anhxa();
        ActionToolbar();
        buttonVeTrangTruoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void Anhxa() {
        toolbarVeChungToi = (Toolbar) findViewById(R.id.toolbarvechungtoi);
        buttonVeTrangTruoc = (Button) findViewById(R.id.buttonquaylaitrangtruoc);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void ActionToolbar() {
        setSupportActionBar(toolbarVeChungToi);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarVeChungToi.setNavigationOnClickListener(v -> finish());
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
}