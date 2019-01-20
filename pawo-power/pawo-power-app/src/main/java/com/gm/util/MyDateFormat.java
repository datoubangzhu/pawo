package com.gm.util;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义日期处理格式
 * jackson支持 yyyy-MM-dd HH:mm:ss 格式
 * TODO 未解决验证问题，现在可以传特殊格式了，但是也不校验了！！！！
 */
public class MyDateFormat extends DateFormat {

    private DateFormat dateFormat;

    private SimpleDateFormat format1 = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");

    public MyDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
        return dateFormat.format(date, toAppendTo, fieldPosition);
    }

    @Override
    public Date parse(String source, ParsePosition pos) {
        Date date = null;
        try {
            date = format1.parse(source, pos);
        } catch (Exception e) {
            date = dateFormat.parse(source, pos);
        }
        return date;
    }

    // 主要还是装饰这个方法
    @Override
    public Date parse(String source) throws ParseException {

        Date date = null;

        try {
            // 先按我的规则来
            date = format1.parse(source);
        } catch (Exception e) {
            // 不行，那就按原先的规则吧
            date = dateFormat.parse(source);
        }

        return date;
    }

    // 这里装饰clone方法的原因是因为clone方法在jackson中也有用到
    @Override
    public Object clone() {
        Object format = dateFormat.clone();
        return new MyDateFormat((DateFormat) format);
    }
}
