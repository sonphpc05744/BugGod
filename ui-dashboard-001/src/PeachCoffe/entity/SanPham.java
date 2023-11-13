/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PeachCoffe.entity;

/**
 *
 * @author nguye
 */
public class SanPham {
    public String MaSP;
    public String MaLSP;
    public String MaKM;
    public String TenSP;
    public int Soluong;
    public String HinhAnh;
    public float Gia;
    public String GhiChu;

    public SanPham() {
    }

    public SanPham(String MaSP, String MaLSP, String MaKM, String TenSP, int Soluong, String HinhAnh, float Gia, String GhiChu) {
        this.MaSP = MaSP;
        this.MaLSP = MaLSP;
        this.MaKM = MaKM;
        this.TenSP = TenSP;
        this.Soluong = Soluong;
        this.HinhAnh = HinhAnh;
        this.Gia = Gia;
        this.GhiChu = GhiChu;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public String getMaLSP() {
        return MaLSP;
    }

    public void setMaLSP(String MaLSP) {
        this.MaLSP = MaLSP;
    }

    public String getMaKM() {
        return MaKM;
    }

    public void setMaKM(String MaKM) {
        this.MaKM = MaKM;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public int getSoluong() {
        return Soluong;
    }

    public void setSoluong(int Soluong) {
        this.Soluong = Soluong;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String HinhAnh) {
        this.HinhAnh = HinhAnh;
    }

    public float getGia() {
        return Gia;
    }

    public void setGia(float Gia) {
        this.Gia = Gia;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }
    
    
    
}
