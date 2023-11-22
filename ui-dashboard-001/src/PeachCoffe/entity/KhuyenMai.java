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
    public boolean TrangThai;
    public boolean LoaiKM;
    SanPham sp = new SanPham();

    public float TinhKM() {
        
       
        if (LoaiKM == true) { // Nếu trạng thái là true (giảm giá theo phần trăm)
            float phanTramGiamGia = GiaKM; // GiaKM chứa phần trăm giảm giá
            float giamGia = (sp.getGia() * phanTramGiamGia) / 100; // Tính giá trị giảm giá theo phần trăm
            // System.out.println("Giảm giá theo phần trăm: " + giamGia);
            return giamGia;
        } else if (LoaiKM == false) { // Nếu trạng thái là false (giảm giá cố định)
            float giamGia = sp.getGia() - GiaKM; // GiaKM chứa giá trị giảm giá cố định
            //System.out.println("Giảm giá cố định: " + giamGia);
            return giamGia;
        }
        else{
            return 0;
        }
    }

    public KhuyenMai() {
    }

    public KhuyenMai(String MaKM, String TenKM, String NgayBD, String NgayKT, float GiaKM, String GhiChu, boolean TrangThai, boolean LoaiKM) {
        this.MaKM = MaKM;
        this.TenKM = TenKM;
        this.NgayBD = NgayBD;
        this.NgayKT = NgayKT;
        this.GiaKM = GiaKM;
        this.GhiChu = GhiChu;
        this.TrangThai = TrangThai;
        this.LoaiKM = LoaiKM;
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

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    public boolean isLoaiKM() {
        return LoaiKM;
    }

    public void setLoaiKM(boolean LoaiKM) {
        this.LoaiKM = LoaiKM;
    }

}
