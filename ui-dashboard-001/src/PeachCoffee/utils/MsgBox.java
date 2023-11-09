/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PeachCoffee.utils;

import java.awt.Component;
import javax.swing.JOptionPane;


/**
 *
 * @author nguye
 */
public class MsgBox {
    public static void alert(Component parent, String messager){
        JOptionPane.showMessageDialog(parent, messager,"Hệ thống quản lý đào tạo",JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static boolean comfirm(Component parent,  String messager){
        int result = JOptionPane.showConfirmDialog(parent,
                messager,"Hệ thống quản lý đào tạo",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        return result == JOptionPane.YES_OPTION;
    }
    
    public static String prompt(Component parent, String messager){
        return JOptionPane.showInputDialog(parent,messager,
                "Hệ thống quản lý đào tạo",JOptionPane.INFORMATION_MESSAGE);
    }
}
