/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.model.HoaDon;
import com.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class HoaDonDAO extends PeachCoffeeDAO<HoaDon, String> {

    final String SELECT_ALL_SQL = "SELECT * FROM HoaDon";

    @Override
    public void insert(HoaDon entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(HoaDon entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updateEmail(HoaDon entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<HoaDon> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public HoaDon selectById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<HoaDon> selectBySql(String sql, Object... args) {
     List<HoaDon> List = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {                
                HoaDon entity = new HoaDon();
                entity.setMaHD(rs.getString("MaHD"));
                entity.setThoiGianTao(rs.getDate("ThoiGianTao"));
                entity.setMaNV(rs.getString("NguoiTao"));
                entity.setTenNV(rs.getString("TenNguoiTao"));
                entity.setMaSP(rs.getString("SanPham"));
                entity.setTongTien(rs.getDouble("TongTien"));
                entity.setMaKM(rs.getString("MaKM"));
                entity.setChiPhiKhac(rs.getDouble("ChiPhiKhac"));
                entity.setHinhThucThanhToan(rs.getBoolean("HinhThucTT"));
                entity.setGhiChu(rs.getString("GhiChu"));
                entity.setTrangThai(rs.getString("TrangThai"));
                List.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace(); 
        }
     return List;
    }

}
