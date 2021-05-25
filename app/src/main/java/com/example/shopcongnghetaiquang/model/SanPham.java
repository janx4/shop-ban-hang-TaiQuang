package com.example.shopcongnghetaiquang.model;

import java.io.Serializable;

public class SanPham implements Serializable {
    private int id;
    private int idSanPham, giaSanPham;
    private String tenSanPham, anhSanPham, moTaSanPham;

    public SanPham(int id, int idSanPham, String tenSanPham, int giaSanPham, String anhSanPham, String moTaSanPham) {
        this.id = id;
        this.idSanPham = idSanPham;
        this.giaSanPham = giaSanPham;
        this.tenSanPham = tenSanPham;
        this.anhSanPham = anhSanPham;
        this.moTaSanPham = moTaSanPham;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
    }

    public int getGiaSanPham() {
        return giaSanPham;
    }

    public void setGiaSanPham(int giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getAnhSanPham() {
        return anhSanPham;
    }

    public void setAnhSanPham(String anhSanPham) {
        this.anhSanPham = anhSanPham;
    }

    public String getMoTaSanPham() {
        return moTaSanPham;
    }

    public void setMoTaSanPham(String moTaSanPham) {
        this.moTaSanPham = moTaSanPham;
    }


}
