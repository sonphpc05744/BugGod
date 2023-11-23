/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.entity;

/**
 *
 * @author nguye
 */
public class CaLam {

    public String MaCaLam;
    public String MaNV;
    public boolean TrangThai;
    public String MaCa;
    public String GhiChu;

    public CaLam() {
    }

    public CaLam(String MaCaLam, String MaNV, boolean TrangThai, String MaCa, String GhiChu) {
        this.MaCaLam = MaCaLam;
        this.MaNV = MaNV;
        this.TrangThai = TrangThai;
        this.MaCa = MaCa;
        this.GhiChu = GhiChu;
    }

    public String getMaCaLam() {
        return MaCaLam;
    }

    public void setMaCaLam(String MaCaLam) {
        this.MaCaLam = MaCaLam;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    public String getMaCa() {
        return MaCa;
    }

    public void setMaCa(String MaCa) {
        this.MaCa = MaCa;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }
    
    
}
