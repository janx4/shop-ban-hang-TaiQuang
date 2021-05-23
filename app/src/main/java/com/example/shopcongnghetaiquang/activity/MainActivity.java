package com.example.shopcongnghetaiquang.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;

import android.widget.ViewFlipper;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;


import com.example.shopcongnghetaiquang.R;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewTrangChu;
    NavigationView navigationView;
    ListView listViewTrangChu;
    DrawerLayout drawerLayout;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        ActionBar();
        ActionViewFlipper();
    }

    private void ActionViewFlipper() {
        // Khai báo mảng chứa String URL ảnh quảng cáo
        ArrayList<String> mangQuangCao = new ArrayList<>();
        mangQuangCao.add("https://salt.tikicdn.com/cache/w750/ts/banner/9b/2f/ee/7b08d4b3fdfa8be13d11097c8c8cd4f5.jpg");
        mangQuangCao.add("https://salt.tikicdn.com/ts/banner/28/c4/aa/04b2dd8b5c255c81a33fe302da37f5d1.png");
        mangQuangCao.add("https://salt.tikicdn.com/ts/banner/38/ce/73/fcfdf30520e1c2ad8d02acad076269a0.png");
        for (int i=0; i<mangQuangCao.size(); i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(mangQuangCao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        // Chạy và thay đổi các ảnh với nhau kèm animation tại ../res/anim
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);
    }

    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void Anhxa(){

        // Ánh xạ qua ID tại file ../res/layout/activity_main.xml
        toolbar = (Toolbar) findViewById(R.id.toolbarhomepage);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);
        recyclerViewTrangChu = (RecyclerView) findViewById(R.id.recyclerview);
        navigationView = (NavigationView) findViewById(R.id.navigationview);
        listViewTrangChu = (ListView) findViewById(R.id.listviewhomepage);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
    }
}