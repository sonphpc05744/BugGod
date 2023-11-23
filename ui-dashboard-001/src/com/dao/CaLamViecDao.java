/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.entity.CaLamViec;
import com.entity.NhanVien;
import com.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nguye
 */
public class CaLamViecDao extends PeachCoffeeDAO<CaLamViec, String> {

    String SELECT_ALL_SQL = "Select * from CaLamViec";

    @Override
    public void insert(CaLamViec entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(CaLamViec entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public CaLamViec selectById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<CaLamViec> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);

        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected List<CaLamViec> selectBySql(String sql, Object... args) {
        List<CaLamViec> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                CaLamViec entiny = new CaLamViec();
                //NhanVien en = new NhanVien();
                entiny.setMaCLV(rs.getString(1));
                entiny.setTenCLV(rs.getString(2));
                entiny.setNhanVienTrucCa(rs.getString(3));
                entiny.setThu(rs.getString(4));
                entiny.setGhiChu(rs.getString(5));
                entiny.setTrangThai(rs.getBoolean(6));
                list.add(entiny);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
