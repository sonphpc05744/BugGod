/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PeachCoffee.DAO;

import PeachCoffe.entity.KhuyenMai;
import PeachCoffe.entity.SanPham;
import PeachCoffee.utils.JdbcHelper;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class KhuyenMaiDao extends PeachCoffeeDAO<KhuyenMai, String> {

    String SELECT_ALL_SQL = "Select * from KhuyenMai";

    @Override
    public void insert(KhuyenMai entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(KhuyenMai entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public KhuyenMai selectById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<KhuyenMai> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected List<KhuyenMai> selectBySql(String sql, Object... args) {
        List<KhuyenMai> list = new ArrayList<>();
        // Trong đoạn mã lấy giá trị từ ResultSet:

        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                KhuyenMai entiny = new KhuyenMai();
                entiny.setMaKM(rs.getString(1));
                entiny.setTenKM(rs.getString(2));
//                entiny.setNgayBD(rs.getNString(3));
                Date ngayBD = rs.getTimestamp(3);
                Date ngayKT = rs.getTimestamp(4);
// Định dạng ngày tháng
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

// Chuyển đổi thành chuỗi
                String ngayBDString = dateFormat.format(ngayBD);

// Đặt giá trị vào entiny
                entiny.setNgayBD(ngayBDString);
                String ngayKTString = dateFormat.format(ngayKT);
                entiny.setNgayKT(ngayKTString);
                entiny.setGiaKM(rs.getFloat(5));
                entiny.setGhiChu(rs.getString("GhiChu"));

                list.add(entiny);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
