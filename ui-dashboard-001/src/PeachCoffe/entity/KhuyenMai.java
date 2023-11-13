/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PeachCoffe.entity;


/**
 *
 * @author nguye
 */
public class KhuyenMai {
    public String MaKM;
    public String TenKM;
    public String NgayBD;
    public String NgayKT;
    public float GiaKM;
    public String GhiChu;

    public KhuyenMai() {
    }

    public KhuyenMai(String MaKM, String TenKM, String NgayBD, String NgayKT, float GiaKM, String GhiChu) {
        this.MaKM = MaKM;
        this.TenKM = TenKM;
        this.NgayBD = NgayBD;
        this.NgayKT = NgayKT;
        this.GiaKM = GiaKM;
        this.GhiChu = GhiChu;
    }

    public String getMaKM() {
        return MaKM;
    }

    public void setMaKM(String MaKM) {
        this.MaKM = MaKM;
    }

    public String getTenKM() {
        return TenKM;
    }

    public void setTenKM(String TenKM) {
        this.TenKM = TenKM;
    }

    public String getNgayBD() {
        return NgayBD;
    }

    public void setNgayBD(String NgayBD) {
        this.NgayBD = NgayBD;
    }

    public String getNgayKT() {
        return NgayKT;
    }

    public void setNgayKT(String NgayKT) {
        this.NgayKT = NgayKT;
    }

    public float getGiaKM() {
        return GiaKM;
    }

    public void setGiaKM(float GiaKM) {
        this.GiaKM = GiaKM;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }
    
    
}
