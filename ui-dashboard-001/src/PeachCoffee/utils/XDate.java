/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PeachCoffee.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author nguye
 */
public class XDate {

    static SimpleDateFormat format = new SimpleDateFormat();

    public static Date toDate(String date, String pattern) {
        try {
            format.applyPattern(pattern);
            return format.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        
        
    }
    
    public static String toString(Date date, String pattern){
        format.applyPattern(pattern);
            return format.format(date);
    }
    
    public static Date addDays(Date date, long days){
        date.setTime(date.getTime()+days*24*60*60*1000);
        return date;
    }
}
