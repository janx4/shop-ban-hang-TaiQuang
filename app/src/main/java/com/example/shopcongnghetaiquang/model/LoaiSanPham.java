package com.example.shopcongnghetaiquang.model;

// Khai báo 1 khung đối tượng loại sản phẩm (constructor, getter, setter)

public class LoaiSanPham {
    public int id;
    public String tenLoaiSP, hinhLoaiSP;

    public LoaiSanPham(int id, String tenLoaiSP, String hinhLoaiSP) {
        this.id = id;
        this.tenLoaiSP = tenLoaiSP;
        this.hinhLoaiSP = hinhLoaiSP;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenLoaiSP() {
        return tenLoaiSP;
    }

    public void setTenLoaiSP(String tenLoaiSP) {
        this.tenLoaiSP = tenLoaiSP;
    }

    public String getHinhLoaiSP() {
        return hinhLoaiSP;
    }

    public void setHinhLoaiSP(String hinhLoaiSP) {
        this.hinhLoaiSP = hinhLoaiSP;
    }
}
