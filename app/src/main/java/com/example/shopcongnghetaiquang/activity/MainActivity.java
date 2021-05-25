package com.example.shopcongnghetaiquang.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import android.widget.ViewFlipper;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.shopcongnghetaiquang.R;
import com.example.shopcongnghetaiquang.adapter.AdapterLoaiSP;
import com.example.shopcongnghetaiquang.adapter.AdapterSanPham;
import com.example.shopcongnghetaiquang.model.LoaiSanPham;
import com.example.shopcongnghetaiquang.model.SanPham;
import com.example.shopcongnghetaiquang.ultil.CheckConnection;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewTrangChu;
    NavigationView navigationView;
    ListView listViewTrangChu;
    DrawerLayout drawerLayout;
    AdapterLoaiSP adapterLoaiSP;
    AdapterSanPham adapterSanPham;

    ArrayList<LoaiSanPham> mangLoaiSP;
    ArrayList<SanPham> mangSanPham;

    int id = 0;
    String tenLoaiSP = "";
    String anhLoaiSP = "";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();

        // Nếu có kết nối thì
        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
            CheckConnection.ShowToastShort(getApplicationContext(), "Khởi tạo kết nối internet!");
            ActionBar();
            ActionViewFlipper();
            CatchEventMenu();
        } else {
            CheckConnection.ShowToastShort(getApplicationContext(), "Kết nối đến server thất bại!");
            finish();
        }
    }

    private void CatchEventMenu() {
        listViewTrangChu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            CheckConnection.ShowToastShort(getApplicationContext(), "Không có kết nối mạng!");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, DienThoaiActivity.class);
                            intent.putExtra("dataSanPham", mangSanPham);
                            startActivity(intent);
                        } else {
                            CheckConnection.ShowToastShort(getApplicationContext(), "Không có kết nối mạng!");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, LaptopActivity.class);
                            intent.putExtra("dataSanPham", mangSanPham);
                            startActivity(intent);
                        } else {
                            CheckConnection.ShowToastShort(getApplicationContext(), "Không có kết nối mạng!");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, ThongTinActivity.class);
                            startActivity(intent);
                        } else {
                            CheckConnection.ShowToastShort(getApplicationContext(), "Không có kết nối mạng!");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, LienHeActivity.class);
                            startActivity(intent);
                        } else {
                            CheckConnection.ShowToastShort(getApplicationContext(), "Không có kết nối mạng!");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
            }
        });
    }

    private void GetDataLoaiSP(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> CheckConnection.ShowToastShort(getApplicationContext(), "a"),
                error -> CheckConnection.ShowToastShort(getApplicationContext(), "b")
        );
        requestQueue.add(jsonArrayRequest);

    }

    private void addSanPham(ArrayList<SanPham> mangSanPham){
        // Loại: điện thoại
        mangSanPham.add(0, new SanPham(0, 0, "Điện thoại Samsung Galaxy S21 5G", 18990000, "https://cdn.tgdd.vn/Products/Images/42/220833/samsung-galaxy-s21-tim-600x600.jpg", "Nổi bật với cụm camera sau được thiết kế đầy mới lạ, phần khuôn hình chữ nhật chứa bộ 3 camera ôm trọn cạnh trái của chiếc smartphone, viền khuôn cong uyển chuyển, hạn chế tối đa độ nhô ra so với mặt lưng, mang lại cái nhìn tổng thể hài hòa, độc đáo."));

        mangSanPham.add(1, new SanPham(0, 1, "Điện thoại iPhone 12 64GB", 23490000, "https://cdn.tgdd.vn/Products/Images/42/213031/iphone-12-violet-1-600x600.jpg", "Hiệu năng vượt xa mọi giới hạn. Apple đã trang bị con chip mới nhất của hãng (tính đến 11/2020) cho iPhone 12 đó là A14 Bionic, được sản xuất trên tiến trình 5 nm với hiệu suất ổn định hơn so với chip A13 được trang bị trên phiên bản tiền nhiệm iPhone 11."));

        mangSanPham.add(2, new SanPham(0, 2, "Điện thoại Vivo Y51 (2020)", 6290000, "https://cdn.tgdd.vn/Products/Images/42/228950/vivo-y51-bac-600x600-600x600.jpg", "Màn hình rộng với màu sắc rực rỡ. Y51 2020 sở hữu màn hình 6.58 inch độ phân giải 1080 x 2408 Pixels, có độ tương phản cao đem đến cho người dùng những trải nghiệm hình ảnh chân thực, màu sắc rực rỡ giúp việc sử dụng ứng dụng giải trí trên điện thoại trở nên cuốn hút hơn. "));

        mangSanPham.add(3, new SanPham(0, 3, "Điện thoại Samsung Galaxy S21 Ultra 5G", 33990000, "https://cdn.tgdd.vn/Products/Images/42/234308/samsung-galaxy-s21-ultra-256gb-den-600x600-1-600x600.jpg", "Thiết kế táo bạo, dẫn đầu xu hướng. Galaxy S21 Ultra 5G sở hữu thiết kế mang tính đột phá, táo bạo với dàn máy ảnh mặt sau được bố trí tinh tế trong khung viền hình chữ nhật ôm trọn cạnh trái của thân máy. Ý tưởng thiết kế này giúp S21 Ultra 5G tự tin sở hữu dáng vẻ độc nhất vô nhị giữa hàng ngàn smartphone trên thị trường hiện nay."));

        mangSanPham.add(4, new SanPham(0, 4, "Điện thoại OPPO Reno5 ", 8690000, "https://cdn.tgdd.vn/Products/Images/42/220438/oppo-reno5-trang-600x600-1-600x600.jpg", "Thiết kế đẹp đậm chất OPPO Reno Series. Có thể nói OPPO Reno5 là mẫu điện thoại phô diễn được đỉnh cao thiết kế và công nghệ chế tác của OPPO khi bề mặt lưng được phủ lớp Reno Glow với ngàn tinh thể phát sáng siêu nhỏ tạo nên hiệu ứng chuyển sắc vô cùng hút mắt."));


        // Loại: máy tính
        mangSanPham.add(5, new SanPham(1, 5, "Laptop Acer Nitro 5 AN515", 23490000, "https://cdn.tgdd.vn/Products/Images/44/237636/acer-nitro-5-an515-45-r3sm-r5-nhqbmsv005-600x600.jpg", "Chiếc laptop Acer này được trang bị con chip AMD Ryzen 5 5600H cho hiệu năng mượt mà chạy tốt các ứng dụng đồ họa đến các tựa game cấu hình cao với với 6 lõi 12 luồng, xung nhịp trung bình là 3.30 GHz và đạt tối đa lên đến 4.2 GHz nhờ Turbo Boost."));

        mangSanPham.add(6, new SanPham(1, 6, "Laptop HP 340s G7 i5", 17590000, "https://cdn.tgdd.vn/Products/Images/44/235978/hp-probook-440-g8-i3-2h0r6pa-17-600x600.jpg", "Laptop được thiết kế gọn nhẹ với khung vỏ nhựa phủ màu bạc sang trọng cùng những đường nét phây xước càng bền bỉ bảo vệ máy trước những tác động vật lý. Máy chỉ nhẹ 1.38 kg và mỏng 17.9 mm nên bạn có thể dễ dàng bỏ vừa balo mang đi khắp nơi để học tập, giải trí."));

        mangSanPham.add(7, new SanPham(1, 7, "Laptop Apple MacBook Pro M1", 39990000, "https://cdn.tgdd.vn/Products/Images/44/239561/apple-macbook-pro-m1-2020-16gb-256gb-silver-z11d0-600x600.jpg", "Macbook Pro 2020 được Apple trang bị cấu hình vô cùng mạnh mẽ với chip M1 được thiết kế dành riêng cho máy Mac là một bước đột phá, nhiều công nghệ mạnh mẽ được tích hợp trong một con chip này giúp xử lý mượt mà những tác vụ hàng ngày cho đến những ứng dụng đồ họa."));

        mangSanPham.add(8, new SanPham(1, 8, "Laptop Dell G5 15 5500", 30290000, "https://cdn.tgdd.vn/Products/Images/44/232902/dell-g5-15-5500-i7-70225485-094921-024956-600x600.jpg", "Không cần phải hầm hố, gai góc nhưng vẫn đủ mạnh mẽ với những đường cắt táo bạo và kết hợp với những đường viền màu xanh nổi bật. Lớp vỏ của máy được làm bằng chất liệu nhựa cứng chắc chắn và tản nhiệt tốt hơn."));

        mangSanPham.add(9, new SanPham(1, 9, "Laptop MSI GL65 Leopard", 23990000, "https://cdn.tgdd.vn/Products/Images/44/235499/msi-gl65-leopard-10scxk-i7-093vn-16-600x600.jpg", "Chiếc laptop MSI GL65 Leopard 10SCXK i7(093VN) chắc chắn sẽ là sự lựa chọn thích hợp dành cho những game thủ với hiệu năng mượt mà với CPU Intel Core i7, NVIDIA GeForce GTX 1650 4GB."));

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
        mangLoaiSP = new ArrayList<>();
        mangLoaiSP.add(0, new LoaiSanPham(0, "Trang Chủ", "https://toppng.com/uploads/preview/blue-home-page-icon-png-icon-png-button-home-11563017221o3yywd2nlp.png"));
        mangLoaiSP.add(1, new LoaiSanPham(1, "Điện thoại", "https://toppng.com/uploads/preview/blue-home-page-icon-png-icon-png-button-home-11563017221o3yywd2nlp.png"));
        mangLoaiSP.add(2, new LoaiSanPham(2, "Máy tính", "https://toppng.com/uploads/preview/blue-home-page-icon-png-icon-png-button-home-11563017221o3yywd2nlp.png"));
        mangLoaiSP.add(3, new LoaiSanPham(3, "Thông tin", "https://toppng.com/uploads/preview/blue-home-page-icon-png-icon-png-button-home-11563017221o3yywd2nlp.png"));
        mangLoaiSP.add(4, new LoaiSanPham(4, "Liên hệ", "https://toppng.com/uploads/preview/blue-home-page-icon-png-icon-png-button-home-11563017221o3yywd2nlp.png"));
        adapterLoaiSP = new AdapterLoaiSP(mangLoaiSP, getApplicationContext());
        listViewTrangChu.setAdapter(adapterLoaiSP);

        mangSanPham = new ArrayList<>();

        adapterSanPham = new AdapterSanPham(getApplicationContext(), mangSanPham);
        recyclerViewTrangChu.setHasFixedSize(true);
        recyclerViewTrangChu.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        recyclerViewTrangChu.setAdapter(adapterSanPham);

        addSanPham(mangSanPham);

    }
}