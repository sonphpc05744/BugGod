/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PeachCoffee.utils;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;

/**
 *
 * @author nguye
 */
public class XImage {

    public static Image getAppIcon() {
        URL url = XImage.class.getResource("");
        return new ImageIcon(url).getImage();
    }

    public static void save(File src) {
        File dst = new File("logos",src.getName());
        if (!dst.getParentFile().exists()) {
            dst.getParentFile().mkdirs();
        }
        try {
            Path from  = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(dst.getAbsolutePath());
            Files.copy(from, to,StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static ImageIcon read (String fileName){
        File path = new File("",fileName);
        return new ImageIcon(path.getAbsolutePath());
    }
}
