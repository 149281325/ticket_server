/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ticket.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class DateUtil {

    /**
     * convert a Date type variable to String type, applying the specified
     * format.
     *
     * @param d
     * @param format
     * @return
     */
    public static String dateToStr(Date d, String format) {
        SimpleDateFormat df = (SimpleDateFormat) SimpleDateFormat
                .getDateInstance();
        df.applyPattern(format);
        return df.format(d);
    }

    /**
     * convert a Date type variable to String, applying the "yyyyMMdd" format.
     *
     * @param d
     * @return
     */
    public static String dateToStr(Date d) {
        return dateToStr(d, "yyyyMMdd");
    }

    /**
     * convert a String type variable to Date type. this method only support the
     * "yyyy-MM-dd" format, if dosen't match, null will be returned.
     *
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate) {
        //System.out.println("\t" + strDate);
        //System.out.println("\t" + strDate.matches("\\d{12}"));
        if(strDate.matches("\\d{14}")) { //20150801120000
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, Integer.parseInt(strDate.substring(0,4)));
            cal.set(Calendar.MONTH, Integer.parseInt(strDate.substring(4,6))-1);
            cal.set(Calendar.DATE, Integer.parseInt(strDate.substring(6,8)));
            cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(strDate.substring(8,10)));
            cal.set(Calendar.MINUTE, Integer.parseInt(strDate.substring(10,12)));
            cal.set(Calendar.SECOND, Integer.parseInt(strDate.substring(12,14)));
            return cal.getTime();
        }
        return null;
    }
    
    public static boolean atRange(Date d, Date begin, Date end) {
        boolean b1 = false, b2 = false;
        if(begin != null) {
            b1 = d.after(begin);
        } else b1=true;
        
        if(end != null) {
            b2 = d.before(end);
        } else b2 = true;
        
        return b1 && b2;
    }
    
    public static Date plus8Hours(Date ref) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(ref);
        rightNow.add(Calendar.HOUR_OF_DAY, 8);//日期加10天
        return rightNow.getTime();
    }
    
    public static void main(String args[]) {
        Date d = new Date();
        System.out.println(d);
    } 
}
