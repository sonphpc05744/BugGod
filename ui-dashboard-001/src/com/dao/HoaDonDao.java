/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.entity.HoaDon;
import com.entity.KhuyenMai;
import com.utils.JdbcHelper;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author nguye
 */
public class HoaDonDao extends PeachCoffeeDAO<HoaDon, String> {

    String SELECT_ALL_SQL = "Select * from HoaDon";

    @Override
    public void insert(HoaDon entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(HoaDon entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public HoaDon selectById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<HoaDon> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected List<HoaDon> selectBySql(String sql, Object... args) {
        List<HoaDon> list = new ArrayList<>();
        // Trong đoạn mã lấy giá trị từ ResultSet:

        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                HoaDon entiny = new HoaDon();
                entiny.setMaHD(rs.getNString(1));

                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");// Định dạng ngày tháng
                Date ThoiGianTao = rs.getTimestamp(2);
                String ThoiHianTaoString = dateFormat.format(ThoiGianTao); // Chuyển đổi thành chuỗi
                entiny.setThoiGianTao(ThoiHianTaoString);

                Date ThoiGianTT = rs.getTimestamp(3);
                String ThoiHianTTString = dateFormat.format(ThoiGianTT);
                entiny.setThoiGianTT(ThoiHianTTString);

                entiny.setNguoiTao(rs.getNString(4));
                entiny.setSanPham(rs.getNString(5));
                entiny.setTongTien(rs.getFloat(6));
                entiny.setMaKM(rs.getNString(7));
                entiny.setChiPhiKhac(rs.getFloat(8));
                entiny.setHinhThucTT(rs.getString(9));
                entiny.setGhiChu(rs.getString(10));
                entiny.setTrangThai(rs.getNString(11));
                entiny.setTenNguoiTao(rs.getString(12));

                list.add(entiny);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
