/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Dao;

import com.dao.PeachCoffeeDAO;
import com.model.SanPham;
import com.model.SanPham;
import com.utils.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class sanPhamDao extends PeachCoffeeDAO<SanPham, String> {

    public static ResultSet rs = null; // Trả về kết quả truy vấn
    public static String INSERT_SQL = "Insert into SanPham(MaSP,TenSP,HinhAnh,Gia,GhiChu,MaLSP,MaKM,TrangThai)values (?,?,?,?,?,?,?,?)";
    public static String UPDATE_SQL = "Update SanPham set TenSP = ?,HinhAnh = ?,Gia = ?,GhiChu = ?,MaLSP = ?,MaKM = ?, TrangThai = ? where MaSP = ?";
    public static String DELETE_SQL = "Delete from SanPham where MaSP = ?";
    public static String SELECT_ALL_SQL = "Select * from SanPham";
    public static String SELECT_BY_ID_SQL = "SELECT * FROM SanPham WHERE MaSP=?";
    public static String SELECT_TLSP = "Select a.*,b.TenLSP from SanPham a inner join LoaiSanPham b on a.MaLSP = b.MaLSP";

    @Override
    public void insert(SanPham entity) {
        JdbcHelper.update(INSERT_SQL,
                entity.getMaSP(),
                entity.getTenSP(),
                entity.getHinh(),
                entity.getGia(),
                entity.getGhiChu(),
                entity.getLoaiSP(),
                entity.getKhuyenMai(),
                entity.isTrangThai()
        );
    }

    @Override
    public void update(SanPham entity) {
        JdbcHelper.update(UPDATE_SQL,
                entity.getTenSP(),
                entity.getHinh(),
                entity.getGia(),
                entity.getGhiChu(),
                entity.getLoaiSP(),
                entity.getKhuyenMai(),
                entity.isTrangThai(),
                entity.getMaSP());
    }

    @Override
    public void delete(String key) {
        JdbcHelper.update(DELETE_SQL, key);
    }

    @Override
    public List<SanPham> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public SanPham selectById(String key) {
        List<SanPham> list = selectBySql(SELECT_BY_ID_SQL, key);
        return list.size() > 0 ? list.get(0) : null;
    }


    @Override
    public List<SanPham> selectBySql(String sql, Object... args) {
        List<SanPham> list = new ArrayList<>();
        try {
            try {
                rs = JdbcHelper.query(sql, args);
                while (rs.next()) {
                    SanPham entity = new SanPham();
                    entity.setMaSP(rs.getString("MaSP"));
                    entity.setTenSP(rs.getString("TenSP"));
                    entity.setHinh(rs.getString("HinhAnh"));
                    entity.setGia(rs.getDouble("Gia"));
                    entity.setGhiChu(rs.getString("GhiChu"));
                    entity.setLoaiSP(rs.getString("MaLSP"));
                    entity.setKhuyenMai(rs.getString("MaKM"));
                    entity.setTrangThai(rs.getBoolean("TrangThai"));
                    list.add(entity);
                }
            } finally {
                if (rs != null) {
                    rs.close();  // Đóng ResultSet khi đã xử lý xong
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return list;
    }
    private List<Object[]> getListOfArray(String sql, String[] cols, Object... args) {
        try {
            List<Object[]> list = new ArrayList<>();
            rs = JdbcHelper.query(sql, args);
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
    public List<Object[]> getSanPhamFull() {
        String[] cols = {"MaSP", "TenSP","HinhAnh","Gia","GhiChu","MaLSP","TrangThai","TenLSP"};
        return this.getListOfArray(SELECT_TLSP, cols);
    }

    @Override
    public void updateEmail(SanPham entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
