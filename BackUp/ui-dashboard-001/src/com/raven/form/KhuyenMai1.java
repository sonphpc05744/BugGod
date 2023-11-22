/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.form;

import PeachCoffe.entity.KhuyenMai;
import PeachCoffe.entity.LoaiSanPham;
import PeachCoffe.entity.SanPham;
import PeachCoffee.DAO.KhuyenMaiDao;
import PeachCoffee.DAO.LoaiSanPhamDao;
import PeachCoffee.DAO.SanPhamDao;
import PeachCoffee.utils.MsgBox;
import PeachCoffee.utils.XDate;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import jdk.vm.ci.aarch64.AArch64;

/**
 *
 * @author HP
 */
public class KhuyenMai1 extends javax.swing.JPanel {

    /**
     * Creates new form KhuyenMai1
     */
    public KhuyenMai1() {
        initComponents();
        init();

    }

    void init() {
        fillTableKM();
        fillTableSP();
        clickCheckBox();
        fillComboBoxKM();

    }

    KhuyenMaiDao daokm = new KhuyenMaiDao();
    SanPhamDao daosp = new SanPhamDao();
    KhuyenMai km = new KhuyenMai();
    LoaiSanPhamDao daolsp = new LoaiSanPhamDao();
    int row = 0;
    int row2 = -1;

    void fillTableKM() {
        DefaultTableModel model = (DefaultTableModel) tblKhuyenMai.getModel();
        model.setRowCount(0);
        try {
            List<KhuyenMai> list = daokm.selectAll(); //đọc all dữ liệu từ cơ sở dữ liệu
            for (KhuyenMai nv : list) {
                Object[] row = {
                    nv.getMaKM(),
                    nv.getTenKM(),
                    nv.isLoaiKM() ? "%" : "VNĐ",
                    nv.getGiaKM(),
                    nv.isTrangThai() ? "Đang áp dụng" : "Hết hạn"
                };
                model.addRow(row);// thêm một hàng vào table
            }

        } catch (Exception e) {
            e.printStackTrace();
            //MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void fillTableSP() {
        DefaultTableModel model = (DefaultTableModel) tblSanPam.getModel();
        model.setRowCount(0);
        try {
            List<SanPham> list1 = daosp.selectAll();
            List<KhuyenMai> list2 = daokm.selectAll(); //đọc all dữ liệu từ cơ sở dữ liệu
            float GiamGia = 0;
            boolean click = false;
            for (SanPham sp : list1) {
                if (sp.MaKM != null) {
                    for (KhuyenMai km : list2) {
                        if (sp.MaKM.equals(km.MaKM)) {
                            click = true;
                            //System.out.println("co ma giong");
                            if (km.LoaiKM == true) {
                                float phanTramGiamGia = km.GiaKM; // GiaKM chứa phần trăm giảm giá
                                GiamGia = (sp.getGia() - (sp.getGia() * km.GiaKM));
                            } else {
                                GiamGia = sp.getGia() - km.GiaKM;
                            }
                        }

                    }

                } else {
                    click = false;
                    GiamGia = 0;
                }
                // System.out.println("" + click);
                Object[] row = {
                    sp.getMaSP(),
                    sp.getTenSP(),
                    sp.getGia(),
                    GiamGia,
                    click

                };

                model.addRow(row);// thêm một hàng vào table

            }
            if (row2 == -1) {
                tblSanPam.setEnabled(false);
                //MsgBox.alert(this, "Chưa chọn KM");
            }
        } catch (Exception e) {
            e.printStackTrace();
            //MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void edit() {
        try {
            String MaKM = (String) tblKhuyenMai.getValueAt(this.row2, 0);
            KhuyenMai model = daokm.selectById(MaKM);
            if (model != null) {//Tìm thấy dữ liệu
                setForm(model);

            } else {
                MsgBox.alert(this, "Lỗi truy vấn dữ liệu");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void setForm(KhuyenMai km) throws ParseException {
        txtMaKM.setText(km.getMaKM());
        txtTenKM.setText(km.getTenKM());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); // Định dạng ngày tháng
        Date date1 = sdf.parse(km.getNgayBD()); // Chuyển đổi ngày tháng thành đối tượng Date
        txtNgayBD.setDate(date1);
        //txtNgayBD.setDate(km.getNgayBD());
        Date date2 = sdf.parse(km.getNgayKT());
        txtNgayKT.setDate(date2);
        //txtNgayKT.setToolTipText(km.getNgayKT());
        rdoDangDienRa.setSelected(km.isTrangThai());
        rdoDaKetThuc.setSelected(!km.isTrangThai());
        rdoPhanTram.setSelected(km.isLoaiKM());
        rdoVND.setSelected(!km.isLoaiKM());
        txtGiaTri.setText(String.valueOf(km.getGiaKM()));
    }

    KhuyenMai getForm() {
        KhuyenMai km = new KhuyenMai();
        km.setMaKM(txtMaKM.getText());
        km.setTenKM(txtTenKM.getText());
//        km.setNgayBD(txtNgayBD.getDateFormatString());
//        km.setNgayKT(txtNgayKT.getDateFormatString());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date ngayBDDate = txtNgayBD.getDate();
        Date ngayKTDate = txtNgayKT.getDate();

        String ngayBDString = dateFormat.format(ngayBDDate);
        String ngayKTString = dateFormat.format(ngayKTDate);

        km.setNgayBD(ngayBDString);
        km.setNgayKT(ngayKTString);

        km.setLoaiKM(rdoPhanTram.isSelected());
        km.setTrangThai(rdoDangDienRa.isSelected());
        km.setGiaKM(Float.parseFloat(txtGiaTri.getText()));
        return km;
    }

    void TimKhuyenMai() {
        DefaultTableModel model = (DefaultTableModel) tblKhuyenMai.getModel();
        model.setRowCount(0);
        try {
            String keyword = txtTenKM1.getText();

            List<KhuyenMai> list = daokm.selectByKeyword(keyword); //đọc all dữ liệu từ cơ sở dữ liệu
            for (KhuyenMai nh : list) {
                Object[] row = {
                    nh.getMaKM(),
                    nh.getTenKM(),
                    nh.isLoaiKM() ? "%" : "VNĐ",
                    nh.getGiaKM(),
                    nh.isTrangThai() ? "Đang diễn ra" : "Đã kết thúc"};
                model.addRow(row);
// thêm một hàng vào table
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void TimKhuyenMai1(String str) {
        DefaultTableModel model = (DefaultTableModel) tblKhuyenMai.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
        tblKhuyenMai.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(str));
    }

    void TimSanPham() {
        DefaultTableModel model = (DefaultTableModel) tblSanPam.getModel();
        model.setRowCount(0);
        try {
            String keyword = txtTenSP.getText();

            List<SanPham> list = daosp.selectByKeyword(keyword); //đọc all dữ liệu từ cơ sở dữ liệu
            for (SanPham nh : list) {
                Object[] row = {
                    nh.getMaSP(),
                    nh.getTenSP(),
                    nh.getGia()
                };
                model.addRow(row);
// thêm một hàng vào table
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void TimSanPham1(String str) {
        DefaultTableModel model = (DefaultTableModel) tblSanPam.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
        tblSanPam.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(str));
        //row2 = -1;
    }

    void insert() {
        KhuyenMai nv = getForm();

        try {
            daokm.insert(nv);
            this.fillTableKM();
            //this.clearForm();

            MsgBox.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            // MsgBox.alert(this, "Thêm mới thất bại!");
        }

        clearForm();
    }

    void update() {
        KhuyenMai nv = getForm();

        try {
            daokm.update(nv);
            this.fillTableKM();
            MsgBox.alert(this, "Cập nhật thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            //MsgBox.alert(this, "Cập nhật thất bại!");
        }

        clearForm();
    }

    void clearForm() {
        txtMaKM.setText("");
        txtTenKM.setText("");
        txtGiaTri.setText("");
        txtNgayBD.setDate(null);
        txtNgayKT.setDate(null);
        btgHTKM.clearSelection();
        btgTrangThai.clearSelection();
    }

    void delete() {

        String maNV = txtMaKM.getText();
        if (MsgBox.comfirm(this, "Bạn thật sự muốn xóa nhân viên này?")) {
            try {
                daokm.delete(maNV);
                this.fillTableKM();
                this.clearForm();
                MsgBox.alert(this, "Xóa thành công");
            } catch (Exception e) {
                MsgBox.alert(this, "Xóa thất bại");
            }

            clearForm();
        }
    }

    void LocKhuyenMai() {
        DefaultTableModel model = (DefaultTableModel) tblKhuyenMai.getModel();
        model.setRowCount(0);
        try {
            int number = cboKhuyenMai.getSelectedIndex();

            List<KhuyenMai> list = daokm.selectLoaiKM(number); //đọc all dữ liệu từ cơ sở dữ liệu
            for (KhuyenMai nh : list) {
                Object[] row = {
                    nh.getMaKM(),
                    nh.getTenKM(),
                    nh.isLoaiKM() ? "%" : "VNĐ",
                    nh.getGiaKM(),
                    nh.isTrangThai() ? "Đang diễn ra" : "Đã kết thúc"};
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void clickCheckBox() {
        for (int row : tblSanPam.getSelectedRows()) {
            Object value = tblSanPam.getValueAt(row, 4);
            SanPham sp = new SanPham();

            if (value instanceof Boolean) {
                //System.out.println("" + value);
                boolean isChecked = (boolean) value;
                if (isChecked) {

                    row2 = tblKhuyenMai.getSelectedRow();

                    row = tblSanPam.getSelectedRow();
                    String MaKM = (String) tblKhuyenMai.getValueAt(row2, 0);

                    String MaSP = (String) tblSanPam.getValueAt(row, 0);
                    daokm.UpdateKM(MaKM, MaSP);
                    // System.out.println("" + MaKM + MaSP);
                    fillTableSP();

                } else {
                    row = tblSanPam.getSelectedRow();
                    String MaSP = (String) tblSanPam.getValueAt(row, 0);
                    daokm.UpdateKM(null, MaSP);
                    fillTableSP();

                }

            }

        }
    }

    private void fillComboBoxKM() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboLoaiSanPham1.getModel();
        model.removeAllElements();
        List<String> list = daosp.selectTenLSP();
        for (String sp : list) {
            model.addElement(sp);
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

        btgHTKM = new javax.swing.ButtonGroup();
        btgTrangThai = new javax.swing.ButtonGroup();
        cboLoaiSanPham = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMaKM = new javax.swing.JTextField();
        txtGiaTri = new javax.swing.JTextField();
        txtTenKM = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        rdoPhanTram = new javax.swing.JRadioButton();
        rdoDangDienRa = new javax.swing.JRadioButton();
        rdoDaKetThuc = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        txtNgayBD = new com.toedter.calendar.JDateChooser();
        txtNgayKT = new com.toedter.calendar.JDateChooser();
        rdoVND = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtTenKM1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cboKhuyenMai = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPam = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKhuyenMai = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        cboLoaiSanPham1 = new javax.swing.JComboBox<>();

        cboLoaiSanPham.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cboLoaiSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Tất cả--", "Đồ ăn vặt", "Nước uống", " " }));
        cboLoaiSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLoaiSanPhamActionPerformed(evt);
            }
        });

        setPreferredSize(new java.awt.Dimension(1042, 715));

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Mã khuyến mãi:");

        txtMaKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaKMActionPerformed(evt);
            }
        });

        txtGiaTri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaTriActionPerformed(evt);
            }
        });

        txtTenKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenKMActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Tên khuyến mãi:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Ngày bắt đầu:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Ngày kết thúc:");

        jLabel5.setText("Giá trị");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Hình thức khuyến mãi:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Trạng thái:");

        btgHTKM.add(rdoPhanTram);
        rdoPhanTram.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdoPhanTram.setText("%");

        btgTrangThai.add(rdoDangDienRa);
        rdoDangDienRa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdoDangDienRa.setText("Đang diễn ra");

        btgTrangThai.add(rdoDaKetThuc);
        rdoDaKetThuc.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdoDaKetThuc.setText("Đã kết thúc");

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnThem.setBackground(new java.awt.Color(204, 255, 217));
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.setPreferredSize(new java.awt.Dimension(72, 40));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel4.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 100, -1));

        btnCapNhat.setBackground(new java.awt.Color(255, 249, 185));
        btnCapNhat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.setPreferredSize(new java.awt.Dimension(78, 40));
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });
        jPanel4.add(btnCapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 100, -1));

        btnXoa.setBackground(new java.awt.Color(255, 204, 214));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setPreferredSize(new java.awt.Dimension(72, 40));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel4.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 100, -1));

        btnMoi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMoi.setText("Mới");
        btnMoi.setPreferredSize(new java.awt.Dimension(72, 40));
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });
        jPanel4.add(btnMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 100, -1));

        txtNgayBD.setDateFormatString("dd/MM/yyyy");

        txtNgayKT.setDateFormatString("dd/MM/yyyy");

        btgHTKM.add(rdoVND);
        rdoVND.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdoVND.setText("VND");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTenKM)
                            .addComponent(txtNgayBD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel5)
                                    .addComponent(txtGiaTri, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtMaKM, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNgayKT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(rdoPhanTram)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdoVND)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel7))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(rdoDangDienRa)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rdoDaKetThuc)))))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMaKM, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTenKM, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoPhanTram)
                            .addComponent(rdoVND)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoDangDienRa)
                            .addComponent(rdoDaKetThuc))))
                .addGap(26, 26, 26)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtGiaTri, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Tên khuyến mãi:");

        txtTenKM1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTenKM1KeyReleased(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Loại khuyến mãi:");

        cboKhuyenMai.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cboKhuyenMai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "VNĐ", "%", "Tất cả" }));
        cboKhuyenMai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboKhuyenMaiActionPerformed(evt);
            }
        });

        tblSanPam.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tblSanPam.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "Mã SP", "Tên SP", "Giá gốc", "Đã giảm", "Chọn SP"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblSanPam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPam);

        tblKhuyenMai.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tblKhuyenMai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã KM", "Tên KM", "Loại KM", "Giá trị", "Trạng thái"
            }
        ));
        tblKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhuyenMaiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblKhuyenMai);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Tên sản phẩm:");

        txtTenSP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTenSPKeyReleased(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Loại sản phẩm");

        cboLoaiSanPham1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cboLoaiSanPham1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Đồ ăn", "Thức uống" }));
        cboLoaiSanPham1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLoaiSanPham1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(cboLoaiSanPham1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(txtTenKM1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(cboKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane2))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(14, 14, 14))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenKM1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboLoaiSanPham1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(41, 41, 41)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaKMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaKMActionPerformed

    private void txtGiaTriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaTriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaTriActionPerformed

    private void txtTenKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenKMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenKMActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        clearForm();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void cboLoaiSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLoaiSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboLoaiSanPhamActionPerformed

    private void tblKhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhuyenMaiMouseClicked
        // TODO add your handling code here:
        this.row2 = tblKhuyenMai.rowAtPoint(evt.getPoint());
        edit();

        // System.out.println("Click row 2 "+row2);
    }//GEN-LAST:event_tblKhuyenMaiMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        insert();
    }//GEN-LAST:event_btnThemActionPerformed

    private void cboKhuyenMaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboKhuyenMaiActionPerformed
        // TODO add your handling code here:
        //selectKM();
        LocKhuyenMai();
    }//GEN-LAST:event_cboKhuyenMaiActionPerformed

    private void tblSanPamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPamMouseClicked
        // TODO add your handling code here:
        if (row2 == -1) {
            tblSanPam.setEnabled(false);
            MsgBox.alert(this, "Chưa chọn KM");
        } else if (row2 != -1) {
            tblSanPam.setEnabled(true);
            clickCheckBox();
            //row = -1;
        }


    }//GEN-LAST:event_tblSanPamMouseClicked

    private void cboLoaiSanPham1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLoaiSanPham1ActionPerformed
        // TODO add your handling code here:
        // LocSanPham();
    }//GEN-LAST:event_cboLoaiSanPham1ActionPerformed

    private void txtTenKM1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenKM1KeyReleased
        // TODO add your handling code here:
        String timKiem = txtTenKM1.getText();
        TimKhuyenMai1(timKiem);
        row2 = -1;
        tblSanPam.setEnabled(false);
    }//GEN-LAST:event_txtTenKM1KeyReleased

    private void txtTenSPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenSPKeyReleased
        // TODO add your handling code here:
        String timKiem = txtTenSP.getText();
        TimSanPham1(timKiem);
    }//GEN-LAST:event_txtTenSPKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgHTKM;
    private javax.swing.ButtonGroup btgTrangThai;
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cboKhuyenMai;
    private javax.swing.JComboBox<String> cboLoaiSanPham;
    private javax.swing.JComboBox<String> cboLoaiSanPham1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rdoDaKetThuc;
    private javax.swing.JRadioButton rdoDangDienRa;
    private javax.swing.JRadioButton rdoPhanTram;
    private javax.swing.JRadioButton rdoVND;
    private javax.swing.JTable tblKhuyenMai;
    private javax.swing.JTable tblSanPam;
    private javax.swing.JTextField txtGiaTri;
    private javax.swing.JTextField txtMaKM;
    private com.toedter.calendar.JDateChooser txtNgayBD;
    private com.toedter.calendar.JDateChooser txtNgayKT;
    private javax.swing.JTextField txtTenKM;
    private javax.swing.JTextField txtTenKM1;
    private javax.swing.JTextField txtTenSP;
    // End of variables declaration//GEN-END:variables
}