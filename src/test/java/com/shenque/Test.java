package com.shenque;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {


    public static void main(String[] args) throws ParseException {

        SimpleDateFormat df = new SimpleDateFormat("YYYY");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间


    }

}
