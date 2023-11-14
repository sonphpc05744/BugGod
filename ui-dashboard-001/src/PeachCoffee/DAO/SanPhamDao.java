/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PeachCoffee.DAO;

import PeachCoffe.entity.NhanVien;
import PeachCoffe.entity.SanPham;
import PeachCoffee.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nguye
 */
public class SanPhamDao extends PeachCoffeeDAO<SanPham, String> {

    String SELECT_ALL_SQL = "Select * from SanPham";
    public  List<SanPham> selectByKeyword(String keyword){
        String sql ="SELECT * FROM SanPham WHERE TenSP LIKE ?";
        return this.selectBySql(sql, "%"+keyword+"%");
    }
    @Override
    public void insert(SanPham entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(SanPham entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public SanPham selectById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<SanPham> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected List<SanPham> selectBySql(String sql, Object... args) {
        List<SanPham> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                SanPham entiny = new SanPham();
                entiny.setMaSP(rs.getString("MaSP"));
                entiny.setTenSP(rs.getString("TenSP"));
                entiny.setSoluong(rs.getInt(3));
                entiny.setHinhAnh(rs.getString("HinhAnh"));
                entiny.setGia(rs.getFloat("Gia"));
                entiny.setGhiChu(rs.getString("GhiChu"));
                
                entiny.setMaLSP(rs.getString("MaLSP"));
                entiny.setMaKM(rs.getString("MaKM"));

                list.add(entiny);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
