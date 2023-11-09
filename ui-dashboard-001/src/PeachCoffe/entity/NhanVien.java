/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PeachCoffe.entity;

import java.util.Date;

public class NhanVien {
    public String MaNV;
    public String TenNV;
    public  String Email;
    public  String SDT;
    public  String MatKhau;
    public  Date NgayVaoLam;
    public  String GhiChu;
    public  boolean GioiTinh;
    public  String TrangThai;
    public  int ChucVu;

    public NhanVien() {
    }

    public NhanVien(String MaNV, String TenNV, String Email, String SDT, String MatKhau, Date NgayVaoLam, String GhiChu, boolean GioiTinh, String TrangThai, int ChucVu) {
        this.MaNV = MaNV;
        this.TenNV = TenNV;
        this.Email = Email;
        this.SDT = SDT;
        this.MatKhau = MatKhau;
        this.NgayVaoLam = NgayVaoLam;
        this.GhiChu = GhiChu;
        this.GioiTinh = GioiTinh;
        this.TrangThai = TrangThai;
        this.ChucVu = ChucVu;
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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public Date getNgayVaoLam() {
        return NgayVaoLam;
    }

    public void setNgayVaoLam(Date NgayVaoLam) {
        this.NgayVaoLam = NgayVaoLam;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    public boolean isGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(boolean GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }

    public int getChucVu() {
        return ChucVu;
    }

    public void setChucVu(int ChucVu) {
        this.ChucVu = ChucVu;
    }
    
    
}
