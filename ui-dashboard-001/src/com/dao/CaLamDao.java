/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.entity.CaLam;
import com.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nguye
 */
public class CaLamDao extends PeachCoffeeDAO<CaLam, String>{
String SELECT_ALL_SQL = "Select * from CaLam";
    @Override
    public void insert(CaLam entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(CaLam entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public CaLam selectById(String id) {
        
        
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<CaLam> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected List<CaLam> selectBySql(String sql, Object... args) {
              List<CaLam> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                CaLam entiny = new CaLam();
                entiny.setMaCa(rs.getString(1));
                entiny.setMaNV(rs.getString(2));
                entiny.setTrangThai(rs.getBoolean(3));
                entiny.setMaCaLam(rs.getNString(4));
                entiny.setGhiChu(rs.getString(5));

                list.add(entiny);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
        
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
