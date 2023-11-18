/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Dao;

import com.Helper.JDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class hoaDonDao {

    public static ResultSet rs = null; // Trả về kết quả truy vấn
    public static String FULL_HOADON = "Select * from HoaDon";
    public static String HoaDonCT_SQL = "{CALL sp_HoaDonChiTiet(?)}";
    public static String ThongTinChiTiet_SQL = "{CALL sp_ThongTinChiTiet(?)}";
//-----------------------------------------------------------------------------------------------------
    public static String LUONGNGUOIHOC_SQL = "{CALL sp_LuongNguoiHoc}";
    public static String DIEMCHUYENDE_SQL = "{CALL sp_DiemChuyenDe}";
    public static String DOANHTHU_SQL = "{CALL sp_DoanhThu (?)}";
    public static String JChart_SQL = "Select Sum(Kh.HocPhi) Doanhthu,YEAR(NgayKG) Nam from KhoaHoc kh JOIN HocVien hv ON kh.MaKH = hv.MaKH JOIN ChuyenDe cd ON cd.MaCD = kh.MaCD group by YEAR(NgayKG)";
    public static String PieChart_SQL = "SELECT YEAR(NgayDK) Nam,COUNT(*) SoLuong FROM NguoiHoc GROUP BY YEAR(NgayDK)";

    private List<Object[]> getListOfArray(String sql, String[] cols, Object... args) {
        try {
            List<Object[]> list = new ArrayList<>();
            rs = JDBC.query(sql, args);
            while (rs.next()) {
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Object[]> getHoaDonCT(String maHD) {
        String[] cols = {"TenSP", "DonGia", "SoLuong", "ThanhTien", "GhiChu"};
        return this.getListOfArray(HoaDonCT_SQL, cols, maHD);
    }

    public List<Object[]> getThongTinChiTiet(String maHD) {
        String[] cols = {"MaHD", "TenNguoiTao","TenKM","ThoiGianTao","ThoiGianTT","GhiChu","TrangThai","ThanhTien","ChiPhiKhac","TongTien"};
        return this.getListOfArray(ThongTinChiTiet_SQL, cols, maHD);
    }

    public List<Object[]> getHoaDon() {
        String[] cols = {"MaHD", "ThoiGianTao", "NguoiTao", "SanPham", "TongTien", "MaKM", "ChiPhiKhac", "HinhThucTT", "GhiChu", "TrangThai", "TenNguoiTao"};
        return this.getListOfArray(FULL_HOADON, cols);
    }

    public List<Object[]> getDiemChuyenDe() {
        String[] cols = {"ChuyenDe", "SoHV", "ThapNhat", "CaoNhat", "TrungBinh"};
        return this.getListOfArray(DIEMCHUYENDE_SQL, cols);
    }

    public List<Object[]> getDoanhThu(int nam) {
        String[] cols = {"ChuyenDe", "SoKH", "SoHV", "DoanhThu", "ThapNhat", "CaoNhat", "TrungBinh"};
        return this.getListOfArray(DOANHTHU_SQL, cols, nam);
    }

    public List<Object[]> getJChart() {
        String[] cols = {"Doanhthu", "Nam"};
        return this.getListOfArray(JChart_SQL, cols);
    }

    public List<Object[]> getPieChart() {
        String[] cols = {"Nam", "SoLuong"};
        return this.getListOfArray(PieChart_SQL, cols);
    }
}
