/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.form;

import Dao.ChiTieuDao;
import com.untils.XDialog;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import model.ChiTieu;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class ChiTieu1 extends javax.swing.JPanel {

     List<Integer> listmhdc = new ArrayList<>();
    
    ChiTieuDao CTD = new ChiTieuDao();
    int row = -1;

    /**
     * Creates new form ChiTieu1
     */
    public ChiTieu1() {
        initComponents();
        init();
    }

    public void init() {
        fillTable();
        fillTable2();
    }

    public void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblChiTieu.getModel();
        model.setRowCount(0);
        try {
            List<ChiTieu> list = CTD.selectAll();
            for (ChiTieu CT : list) {
                
                Object[] row = {
                    CT.getMaHD(),
                    CT.getTenNV(),
                    CT.getTien(),
                    CT.getThoiGian(),
                    CT.getGhiChu()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            XDialog.alert(this, "Lỗi truy vấn dữ liệu Chi Tieu");
            e.printStackTrace();
        }
    }

    private ChiTieu getForm() {
        ChiTieu CT = new ChiTieu();
        CT.setTenNV(txtTenNV.getText());
        CT.setTien(Double.valueOf(txtTien.getText()));
        CT.setThoiGian(txtNgayLay.getDate());
        CT.setGhiChu(txtGhiChu.getText());
        return CT;
    }

    public void setForm(ChiTieu CT) {
        txtTenNV.setText(CT.getTenNV());
        txtTien.setText(String.valueOf(CT.getTien()));
        txtNgayLay.setDate(CT.getThoiGian());
        txtGhiChu.setText(CT.getGhiChu());
    }

    public void edit() {
        int MaHD = (int) tblChiTieu.getValueAt(this.row, 0);
        ChiTieu ct = CTD.selectById(String.valueOf(MaHD));
        this.setForm(ct);
    }

    private void clearForm() {
        ChiTieu ct = new ChiTieu();
        this.setForm(ct);
        this.row = -1;

    }

    private void them() {
        ChiTieu model = getForm();
        try {
            CTD.insert(model);
            this.fillTable();
            XDialog.alert(this, "Thêm hóa đơn thành công!");
        } catch (Exception e) {
            XDialog.alert(this, "Thêm hóa đơn thất bại!");
            e.printStackTrace();
        }
    }

//    public void fillTable2() {
//        DefaultTableModel model = (DefaultTableModel) tblLichSu.getModel();
//        model.setRowCount(0);
//        listmhdc.clear();
//        try {
//            List<ChiTieu> list = CTD.selectAll();
//            
//            for (ChiTieu CT : list) {
//                listmhdc.add(CT.getMaHD());
//                Object[] row = {
//                    CT.getMaHD(),
//                    CT.getTenNV(),
//                    CT.getTien(),
//                    CT.getThoiGian(),
//                    CT.getGhiChu(),
//                    CT.isTrangThai() ? "Xác nhận" : "Không xác nhận"
//                };
//                model.addRow(row);
//            }
//        } catch (Exception e) {
//            XDialog.alert(this, "Lỗi truy vấn dữ liệu lịch sử");
//            e.printStackTrace();
//        }
//    }
//
//     private ChiTieu getForm2() {
//        ChiTieu CT = new ChiTieu();
//        if (row >= 0 && row < listmhdc.size()) {
//            int maCL = listmhdc.get(row);
//            CT.setMaHD(maCL);
//            
//            if (rboKoXacNhan.isSelected()) {
//                CT.setTrangThai(true);
//            } else {
//                CT.setTrangThai(false);
//            }
//           
//        }
//
//        return CT;
//    }
//
//
//    
//    
//    public void setForm2(ChiTieu CT) {
//        
//        lblTenNV.setText(CT.getTenNV());
//        lblTien.setText(String.valueOf(CT.getTien()));
//        lblNgay.setText(String.valueOf(CT.getThoiGian()));
//        txtGhiChu2.setText(CT.getGhiChu());
//        rboXacNhan.setSelected(CT.isTrangThai());
//        rboKoXacNhan.setSelected(!CT.isTrangThai());
//    }
//
//    private void edit2() {
//    if (!listmhdc.isEmpty() && row >= 0 && row < listmhdc.size()) {
//        int mahd = listmhdc.get(row);
//        ChiTieu CT = CTD.selectById(mahd);
//        this.setForm2(CT);
//    } else {
//        // Xử lý trường hợp listmhdc rỗng hoặc row không hợp lệ
//    }
//}
//
//    public void capnhat() {
//    ChiTieu ct = this.getForm2();
//
//    try {
//        CTD.update(ct); // cập nhật chỉ trạng thái, không phải toàn bộ đối tượng
//        this.fillTable2();// đổ lại bảng
//        XDialog.alert(this, "Đã xác nhận thành công!");
//    } catch (Exception e) {
//        XDialog.alert(this, "Lỗi! Không thể cập nhật");
//        e.printStackTrace();
//    }
//}
    
     private void fillTable2() {
        DefaultTableModel model = (DefaultTableModel) tblLichSu.getModel();
        model.setRowCount(0);
        listmhdc.clear();
        
        try {
            List<ChiTieu> list = CTD.selectAll();
            for (ChiTieu CL : list) {
                listmhdc.add(CL.getMaHD());                              
                    Object[] row = {
                        CL.getMaHD(),
                        CL.getTenNV(),
                        CL.getTien(),
                        CL.getGhiChu(),
                        CL.getThoiGian(),
                        CL.isTrangThai() ? "Xác nhận" : "Không xác nhận"
                        
                    };
                    model.addRow(row);
                
            }
        } catch (Exception e) {
            XDialog.alert(this, "Lỗi truy vấn dữ liệu lịch sử chi tiêu!");
            e.printStackTrace();
        }
    }

    private ChiTieu getForm2() {
        ChiTieu CT = new ChiTieu();

        if (row >= 0 && row < listmhdc.size()) {
            Integer maCL = listmhdc.get(row);
            CT.setMaHD(maCL);
            
            if (rboKoXacNhan.isSelected()) {
                CT.setTrangThai(true);
            } else {
                CT.setTrangThai(false);
            }
            CT.setGhiChu(txtGhiChu.getText());
        }

        return CT;
    }

    public void setForm2(ChiTieu Cl) {
        lblTenNV.setText(Cl.getTenNV());
     //   lblTien.setText(String.valueOf(Cl.getTien()));
//        lblNgay.setText(Cl.getThoiGian());
        txtGhiChu2.setText(Cl.getGhiChu());
        rboKoXacNhan.setSelected(!Cl.isTrangThai());
        rboXacNhan.setSelected(Cl.isTrangThai());
    }

    private void edit2() {
        if (row >= 0 && row < listmhdc.size()) {
            Integer maCl = listmhdc.get(row);
            ChiTieu CT = CTD.selectById(maCl);
            this.setForm2(CT);
        } else {
            XDialog.alert(this, "Vui lòng chọn một hàng để chỉnh sửa.");
        }
    }

    public void capnhat() {
        ChiTieu Cl = this.getForm();

        try {
            CTD.update(Cl); // cập nhật
            this.fillTable2();// đổ lại bảng
            XDialog.alert(this, "Đã xác nhận thành công!");
        } catch (Exception e) {
            XDialog.alert(this, "Lỗi! Không thể cập nhật");
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btngTrangThai = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        txtTenNV = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblChiTieu = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtTien = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        btnXacNhan = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        txtNgayLay = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblLichSu = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        rboXacNhan = new javax.swing.JRadioButton();
        rboKoXacNhan = new javax.swing.JRadioButton();
        btnCapNhat = new javax.swing.JButton();
        btnHUy = new javax.swing.JButton();
        lblTenNV = new javax.swing.JLabel();
        lblTien = new javax.swing.JLabel();
        lblNgay = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtGhiChu2 = new javax.swing.JTextArea();

        setBackground(new java.awt.Color(204, 204, 255));

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jTabbedPane1.setBackground(new java.awt.Color(204, 204, 255));
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));

        txtTenNV.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtTenNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenNVActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblChiTieu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tblChiTieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Tên NV", "Số tiền", "Ngày lấy", "Ghi chú"
            }
        ));
        tblChiTieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChiTieuMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblChiTieu);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Tên nhân viên:");

        txtTien.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Số tiền lấy:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Ngày lấy:");

        txtGhiChu.setColumns(20);
        txtGhiChu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtGhiChu.setRows(5);
        txtGhiChu.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ghi chú", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jScrollPane1.setViewportView(txtGhiChu);

        btnXacNhan.setBackground(new java.awt.Color(101, 107, 255));
        btnXacNhan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXacNhan.setForeground(new java.awt.Color(255, 255, 255));
        btnXacNhan.setText("Xác nhận");
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });

        btnHuy.setBackground(new java.awt.Color(255, 102, 102));
        btnHuy.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnHuy.setText("Hủy");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        txtNgayLay.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)
                                .addComponent(jScrollPane1)
                                .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNgayLay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txtTien, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(147, 147, 147)))
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTien, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNgayLay, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(143, Short.MAX_VALUE))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Lấy tiền", jPanel2);

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));

        tblLichSu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tblLichSu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Tên NV", "Số tiền", "Ngày ", "Ghi chú", "Trạng thái"
            }
        ));
        tblLichSu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLichSuMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblLichSu);

        jPanel5.setBackground(new java.awt.Color(204, 204, 255));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Trạng thái");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel5.setText("Xác nhận trạng thái");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Tên Nhân viên:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Số tiền:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Ngày lấy:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Ghi chú:");

        btngTrangThai.add(rboXacNhan);
        rboXacNhan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rboXacNhan.setText("Xác nhận");

        btngTrangThai.add(rboKoXacNhan);
        rboKoXacNhan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rboKoXacNhan.setText("Không xác nhận");

        btnCapNhat.setBackground(new java.awt.Color(101, 112, 255));
        btnCapNhat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCapNhat.setForeground(new java.awt.Color(255, 255, 255));
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnHUy.setBackground(new java.awt.Color(255, 102, 102));
        btnHUy.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnHUy.setText("Hủy");
        btnHUy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHUyActionPerformed(evt);
            }
        });

        lblTenNV.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTenNV.setForeground(new java.awt.Color(0, 0, 204));
        lblTenNV.setText("Nguyễn Văn A");

        lblTien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTien.setForeground(new java.awt.Color(0, 0, 204));
        lblTien.setText("0 VND");

        lblNgay.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNgay.setForeground(new java.awt.Color(0, 0, 204));
        lblNgay.setText("01/01/2023");

        txtGhiChu2.setColumns(20);
        txtGhiChu2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtGhiChu2.setRows(5);
        jScrollPane4.setViewportView(txtGhiChu2);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(350, 350, 350)
                        .addComponent(jLabel5))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(250, 250, 250)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)
                        .addComponent(jLabel4))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(250, 250, 250)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane4))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblTien, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(108, 108, 108))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(81, 81, 81)))
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rboXacNhan)
                                    .addComponent(rboKoXacNhan)))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(370, 370, 370)
                        .addComponent(btnHUy, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(305, 305, 305))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel5)
                .addGap(22, 22, 22)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(lblTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(lblTien, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(rboXacNhan)))
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(lblNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(rboKoXacNhan)))
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnHUy, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Lịch sử", jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnHUyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHUyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHUyActionPerformed

    private void txtTenNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenNVActionPerformed

    private void txtTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienActionPerformed

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        // TODO add your handling code here:
        them();
    }//GEN-LAST:event_btnXacNhanActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        clearForm();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void tblChiTieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTieuMouseClicked
        // TODO add your handling code here:
        this.row = tblChiTieu.getSelectedRow();
        this.edit();
    }//GEN-LAST:event_tblChiTieuMouseClicked

    private void tblLichSuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLichSuMouseClicked
        // TODO add your handling code here:
        this.row = tblLichSu.getSelectedRow();
        this.edit2();
    }//GEN-LAST:event_tblLichSuMouseClicked

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        // TODO add your handling code here:
        capnhat();
    }//GEN-LAST:event_btnCapNhatActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnHUy;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnXacNhan;
    private javax.swing.ButtonGroup btngTrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblNgay;
    private javax.swing.JLabel lblTenNV;
    private javax.swing.JLabel lblTien;
    private javax.swing.JRadioButton rboKoXacNhan;
    private javax.swing.JRadioButton rboXacNhan;
    private javax.swing.JTable tblChiTieu;
    private javax.swing.JTable tblLichSu;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextArea txtGhiChu2;
    private com.toedter.calendar.JDateChooser txtNgayLay;
    private javax.swing.JTextField txtTenNV;
    private javax.swing.JTextField txtTien;
    // End of variables declaration//GEN-END:variables
}