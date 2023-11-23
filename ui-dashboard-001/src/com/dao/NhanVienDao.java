/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.entity.NhanVien;
import com.utils.JdbcHelper;
import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nguye
 */
public class NhanVienDao extends PeachCoffeeDAO<NhanVien, String> {

    String INSERT_SQL = "INSERT INTO NhanVien (MaNV, TenNV, Email, SDT, MatKhau, NgayVaoLam, GhiChu, GioiTinh, TrangThai, ChucVu)\n"
            + "VALUES (?,?,?,?,?,?,?,?,?,?);";
    String UPDATE_SQL = "UPDATE [dbo].[NhanVien]\n"
            + "   SET [TenNV] = ?,[Email] = ?,[SDT] =?,[MatKhau] =?,[NgayVaoLam] = ?,[GhiChu] = ? ,[GioiTinh] = ?,[TrangThai] = ?,[ChucVu] = ?\n" + " WHERE [MaNV] = ? ";
    String DELETE_SQL = "DELETE FROM NhanVien Where MaNV = ?";
    String SELECT_ALL_SQL = "Select * from NhanVien";
    String SELECT_BY_ID_SQL = "Select * from NhanVien WHeRE MaNV = ?";

    @Override
    public void insert(NhanVien entity) {
       // JdbcHelper.update(INSERT_SQL, entity.getMaNV(), entity.getTenNV(), entity.getEmail(), entity.getSDT(), entity.getMatKhau(), entity.getNgayVaoLam(), entity.getGhiChu(), entity.isGioiTinh(), entity.getTrangThai(), entity.getChucVu());
    }

    @Override
    public void update(NhanVien entity) {
       // JdbcHelper.update(UPDATE_SQL, entity.getTenNV(), entity.getEmail(), entity.getSDT(), entity.getMatKhau(), entity.getNgayVaoLam(), entity.getGhiChu(), entity.isGioiTinh(), entity.getTrangThai(), entity.getChucVu(), entity.getMaNV());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(DELETE_SQL, id);
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public NhanVien selectById(String id) {
        List<NhanVien> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
// throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<NhanVien> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected List<NhanVien> selectBySql(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                NhanVien entiny = new NhanVien();
                entiny.setMaNV(rs.getString("MaNV"));
                entiny.setTenNV(rs.getString("TenNV"));
                entiny.setEmail(rs.getString("Email"));
                entiny.setSDT(rs.getString("SDT"));
                entiny.setMatKhau(rs.getString("MatKhau"));
                entiny.setNgayVaoLam(rs.getDate("NgayVaoLam"));
                entiny.setGhiChu(rs.getString("GhiChu"));
                entiny.setGioiTinh(rs.getBoolean("GioiTinh"));
                entiny.setTrangThai(rs.getBoolean("TrangThai"));
                entiny.setChucVu(rs.getBoolean("ChucVu"));
                entiny.setHinh(rs.getString("Hinh"));

                list.add(entiny);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
