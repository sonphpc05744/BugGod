/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.main;

import com.raven.event.EventItem;
import com.raven.event.EventMenuSelected;
import com.raven.form.ChiTieu1;
import com.raven.form.Form_1;
import com.raven.form.Form_2;
import com.raven.form.Form_3;
import com.raven.form.Form_Home;
import com.raven.form.HoaDon2;
import com.raven.form.KhuyenMai1;
import com.raven.form.Menu1;
import com.raven.form.QuanLyNhanVien1;
import com.raven.form.QuanLySanPham1;
import com.raven.form.ThongKe1;
import com.raven.form.TrangChu;
import com.raven.model.ModelItem;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

/**
 *
 * @author RAVEN
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    private TrangChu home;
    private Menu1 form1;
    private HoaDon2 form2;
    private QuanLySanPham1 form3;
    private KhuyenMai1 form4;
    private ThongKe1 form5;
    private QuanLyNhanVien1 form6;
    private ChiTieu1 form7;
    private ModelItem itemSelected;

    public Main() {
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        home = new TrangChu();
        form1 = new Menu1();
        form2 = new HoaDon2();
        form3 = new QuanLySanPham1();
        form4 = new KhuyenMai1();
        form5 = new ThongKe1();
        form6 = new QuanLyNhanVien1();
        form7 = new ChiTieu1();
        menu.initMoving(Main.this);
        menu.addEventMenuSelected(new EventMenuSelected() {
            @Override
            public void selected(int index) {
                if (index == 0) {
                    setForm(home);
                } else if (index == 2) {
                    init();
                    setForm(form1);
                } else if (index == 4) {
                    setForm(form2);
                } else if (index == 6) {
                    setForm(form3);
                } else if (index == 8) {
                    setForm(form4);
                } else if (index == 10) {
                    setForm(form5);
                } else if (index == 12) {
                    setForm(form6);
                } else if (index == 14) {
                    setForm(form7);
                }
            }
        });
        //  set when system open start with home form
        setForm(new TrangChu());
    }

    private void setForm(JComponent com) {
        mainPanel.removeAll();
        mainPanel.add(com);
        mainPanel.repaint();
        mainPanel.revalidate();
    }

    private void init() {
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(form1);
        testData();
    }

    private void testData() {
        form1.setEvent(new EventItem() {
            @Override
            public void itemClick(Component com, ModelItem item) {
                if (itemSelected != null) {
                    //        mainPanel.setImageOld(itemSelected.getImage());
                }
                if (itemSelected != item) {
                    //         if (!animator.isRunning()) {
                    itemSelected = item;
                    //         animatePoint = getLocationOf(com);
//                    mainPanel.setImage(item.getImage());
//                    //        mainPanel.setImageLocation(animatePoint);
//                    mainPanel.setImageSize(new Dimension(180, 120));
//                    mainPanel.repaint();
                    form1.setSelected(com);
                    
                    //home.showItem(item);
                    //     animator.start();
                    //    }
                }
            }
        });
        int ID = 1;
        for (int i = 0; i <= 5; i++) {
            form1.addItem(new ModelItem(ID++, "4DFWD PULSE", "This product is excluded from all promotional discounts and offers.", 160, "Adidas", new ImageIcon(getClass().getResource("/com/raven/image/img1.png"))));
            form1.addItem(new ModelItem(ID++, "FORUM MID", "This product is excluded from all promotional discounts and offers.", 100, "Adidas", new ImageIcon(getClass().getResource("/com/raven/image/img2.png"))));
            form1.addItem(new ModelItem(ID++, "SUPERNOVA", "NMD City Stock 2", 150, "Adidas", new ImageIcon(getClass().getResource("/com/raven/image/img3.png"))));
            form1.addItem(new ModelItem(ID++, "Adidas", "NMD City Stock 2", 160, "Adidas", new ImageIcon(getClass().getResource("/com/raven/image/img4.png"))));
            form1.addItem(new ModelItem(ID++, "Adidas", "NMD City Stock 2", 120, "Adidas", new ImageIcon(getClass().getResource("/com/raven/image/img5.png"))));
            form1.addItem(new ModelItem(ID++, "4DFWD PULSE", "This product is excluded from all promotional discounts and offers.", 160, "Adidas", new ImageIcon(getClass().getResource("/com/raven/image/img6.png"))));
        }
    }

    private Point getLocationOf(Component com) {
        Point p = form1.getPanelItemLocation();
        int x = p.x;
        int y = p.y;
        int itemX = com.getX();
        int itemY = com.getY();
        int left = 10;
        int top = 35;
        return new Point(x + itemX + left, y + itemY + top);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new com.raven.swing.PanelBorder();
        menu = new com.raven.component.Menu();
        header2 = new com.raven.component.Header();
        mainPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panelBorder1.setBackground(new java.awt.Color(255, 153, 255));

        header2.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N

        mainPanel.setOpaque(false);
        mainPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(header2, javax.swing.GroupLayout.DEFAULT_SIZE, 1293, Short.MAX_VALUE)
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(header2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.component.Header header2;
    private javax.swing.JPanel mainPanel;
    private com.raven.component.Menu menu;
    private com.raven.swing.PanelBorder panelBorder1;
    // End of variables declaration//GEN-END:variables
}
