/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PeachCoffe.entity;

/**
 *
 * @author nguye
 */
public class CaLamViec {
    public String MaCLV;
    public String TenCLV;
    public String NhanVienTrucCa;
    public String Thu;
    public String GhiChu;

    public CaLamViec() {
    }

    public CaLamViec(String MaCLV, String TenCLV, String NhanVienTrucCa, String Thu, String GhiChu) {
        this.MaCLV = MaCLV;
        this.TenCLV = TenCLV;
        this.NhanVienTrucCa = NhanVienTrucCa;
        this.Thu = Thu;
        this.GhiChu = GhiChu;
    }

    public String getMaCLV() {
        return MaCLV;
    }

    public void setMaCLV(String MaCLV) {
        this.MaCLV = MaCLV;
    }

    public String getTenCLV() {
        return TenCLV;
    }

    public void setTenCLV(String TenCLV) {
        this.TenCLV = TenCLV;
    }

    public String getNhanVienTrucCa() {
        return NhanVienTrucCa;
    }

    public void setNhanVienTrucCa(String NhanVienTrucCa) {
        this.NhanVienTrucCa = NhanVienTrucCa;
    }

    public String getThu() {
        return Thu;
    }

    public void setThu(String Thu) {
        this.Thu = Thu;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }
    
    
}
