/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PeachCoffe.entity;

/**
 *
 * @author nguye
 */
public class LoaiSanPham {
   public String MaLSP, TenLSP;

    public LoaiSanPham() {
    }

    public LoaiSanPham(String MaLSP, String TenLSP) {
        this.MaLSP = MaLSP;
        this.TenLSP = TenLSP;
    }

    public String getMaLSP() {
        return MaLSP;
    }

    public void setMaLSP(String MaLSP) {
        this.MaLSP = MaLSP;
    }

    public String getTenLSP() {
        return TenLSP;
    }

    public void setTenLSP(String TenLSP) {
        this.TenLSP = TenLSP;
    }
   
}
