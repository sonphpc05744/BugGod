/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import static Dao.ChiTieuDao.SELECT_ALL_SQL;
import static Dao.ChiTieuDao.SELECT_BY_ID_SQL;
import static Dao.ChiTieuDao.rs;
import Helper.JDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.CaLam;
import model.ChiTieu;

/**
 *
 * @author ACER
 */
public class CaLamDao extends PeachCoffeeDao<CaLam, String> {

    public static ResultSet rs = null; // Trả về kết quả truy vấn
    public static String UPDATE_SQL = "Update CaLam set TrangThai = ?, GhiChu= ? where MaCa = ? and MaNV= ?";
    public static String SELECT_ALL_SQL = "Select * from CaLam";
    public static String SELECT_BY_ID_SQL = "SELECT * FROM CaLam WHERE MaCaLam=?";

    @Override
    public void insert(CaLam entity) {
    }

    @Override
    public void update(CaLam entity) {
        JDBC.update(UPDATE_SQL,
                entity.isTrangThai(),
                entity.getGhiChu(),
                entity.getMaCa(),
                entity.getMaNV());

    }

    @Override
    public void delete(String key) {
        //   throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<CaLam> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public CaLam selectById(String key) {
        List<CaLam> list = selectBySql(SELECT_BY_ID_SQL, key);
        return list.size() > 0 ? list.get(0) : new CaLam();
    }

    @Override
    protected List<CaLam> selectBySql(String sql, Object... args) {
        List<CaLam> list = new ArrayList<>();
        try {

            rs = JDBC.query(sql, args);
            while (rs.next()) {
                CaLam entity = new CaLam();
                entity.setMaCL(rs.getString("MaCaLam"));
                entity.setMaNV(rs.getString("MaNV"));
                entity.setTenNV(rs.getString("TenNV"));
                entity.setChucVu(rs.getString("ChucVu"));
                entity.setTrangThai(rs.getBoolean("TrangThai"));
                entity.setMaCa(rs.getString("MaCa"));
                entity.setGhiChu(rs.getString("GhiChu"));
                list.add(entity);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return list;
    }
}
