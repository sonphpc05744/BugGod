/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Helper.JDBC;
import com.Dao.PeachCoffeeDao;
import java.sql.ResultSet;
import java.util.List;
import model.ChiTieu;

/**
 *
 * @author ACER
 */
public class ChiTieuDao extends PeachCoffeeDao<ChiTieu, String>{
    public static ResultSet rs = null; // Trả về kết quả truy vấn
    public static String INSERT_SQL = "Insert into HoaDonChi (MaNV,TienLay,ThoiGianTao,GhiChu)values (?,?,?,?)";
    public static String UPDATE_SQL = "Update HoaDonChi set HoaDonChi = ? where MaHD = ?";  
    public static String SELECT_ALL_SQL = "Select * from HoaDonChi";
    public static String SELECT_BY_ID_SQL = "SELECT * FROM HoaDonChi WHERE MaHD=?";

    @Override
    public void insert(ChiTieu entity) {
              JDBC.update
                
                
    }

    @Override
    public void update(ChiTieu entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String key) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ChiTieu> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ChiTieu selectById(String key) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected List<ChiTieu> selectBySql(String sql, Object... args) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
