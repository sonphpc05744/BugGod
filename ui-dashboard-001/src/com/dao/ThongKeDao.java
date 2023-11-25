/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author nguye
 */
public class ThongKeDao {

    private List<Object[]> getListOfArray(String sql, String[] cols, Object... args) {
        try {
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Object[]> getThongKeHoaDonTrongKhoangNgay(Date ngayBD, Date ngayKT) {
        
            String sql = "{CALL ThongKeHoaDonTrongKhoangNgay(?,?)}";
            String[] cols = {"TongSoTien", "SoHoaDon"};
            return getListOfArray(sql, cols, ngayBD, ngayKT);
        
       
    }

    public List<Object[]> getThongKeHoaDonNgayHienTai() {
        String sql = "{CALL ThongKeHoaDonNgayHienTai}";
        String[] cols = {"TongDoanhThu", "SoLuongHoaDon"};
        return getListOfArray(sql, cols);
    }

    public List<Object[]> getThongKeTuanNay() {
        String sql = "{CALL ThongKeTuanNay}";
        String[] cols = {"TongSoTien", "SoHoaDon"};
        return getListOfArray(sql, cols);
    }

    public List<Object[]> getThongKeThangNay() {
        String sql = "{CALL ThongKeThangNay}";
        String[] cols = {"TongSoTien", "SoHoaDon"};
        return getListOfArray(sql, cols);
    }
    
      public List<Object[]> get_laySPTuHoaDon() {
        String sql = "{CALL sp_laySPTuHoaDon}";
        String[] cols = {"TenSP", "SpLuong","TongTien"};
        
        return getListOfArray(sql, cols);
    }
}
