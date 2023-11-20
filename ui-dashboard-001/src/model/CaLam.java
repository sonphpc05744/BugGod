/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ACER
 */
public class CaLam {
    private String MaCL;
    private String MaCa;
    private String MaNV;
    private String TenNV;
    private String ChucVu;
    private boolean TrangThai;
    private String GhiChu;

    public CaLam() {
    }

    public CaLam(String MaCL, String MaCa, String MaNV, String TenNV, String ChucVu, boolean TrangThai, String GhiChu) {
        this.MaCL = MaCL;
        this.MaCa = MaCa;
        this.MaNV = MaNV;
        this.TenNV = TenNV;
        this.ChucVu = ChucVu;
        this.TrangThai = TrangThai;
        this.GhiChu = GhiChu;
    }

    public String getMaCL() {
        return MaCL;
    }

    public void setMaCL(String MaCL) {
        this.MaCL = MaCL;
    }

    public String getMaCa() {
        return MaCa;
    }

    public void setMaCa(String MaCa) {
        this.MaCa = MaCa;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String TenNV) {
        this.TenNV = TenNV;
    }

    public String getChucVu() {
        return ChucVu;
    }

    public void setChucVu(String ChucVu) {
        this.ChucVu = ChucVu;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }
    
    
}
