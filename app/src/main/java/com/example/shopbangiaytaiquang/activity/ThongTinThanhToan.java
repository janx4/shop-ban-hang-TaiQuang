package com.example.shopbangiaytaiquang.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.shopbangiaytaiquang.R;
import com.example.shopbangiaytaiquang.ultil.CheckConnection;

public class ThongTinThanhToan extends AppCompatActivity {

    EditText editTenKhachHang, editSoDienThoai, editDiaChi;
    Button buttonXacNhan, buttonQuayLai;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_thanh_toan);
        Anhxa();
        buttonQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
            EventButtonXacNhan();
        } else {
            CheckConnection.ShowToastShort(getApplicationContext(), "Không có kết nối internet");
        }
    }

    private void EventButtonXacNhan() {
        buttonXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  tenKH = editTenKhachHang.getText().toString().trim(),
                        sdtKH = editSoDienThoai.getText().toString().trim(),
                        diachiKH = editDiaChi.getText().toString().trim();
                if (tenKH.length() > 0 && sdtKH.length() > 0 && diachiKH.length() > 0){
                    CheckConnection.ShowToastShort(getApplicationContext(), "Đặt hàng thành công! Nhân viên của chúng tôi sẽ liên hệ ngay với bạn!");
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } else {
                    CheckConnection.ShowToastShort(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin!");
                }
            }
        });
    }


    private void Anhxa() {
        editTenKhachHang = (EditText) findViewById(R.id.edittenkhachhang);
        editSoDienThoai = (EditText) findViewById(R.id.editsdtkhachhang);
        editDiaChi = (EditText) findViewById(R.id.editdiachikhachhang);
        buttonXacNhan = (Button) findViewById(R.id.buttonxacnhanmuahang);
        buttonQuayLai = (Button) findViewById(R.id.buttonquaylai);
    }


}