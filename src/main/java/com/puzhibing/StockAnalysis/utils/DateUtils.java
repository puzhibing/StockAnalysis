package com.puzhibing.StockAnalysis.utils;


import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 定义日期工具类
 */
public final class DateUtils {

    private static SimpleDateFormat simpleDateFormat;

    private static String dateFormat;//日期部分的格式

    private static String timeFormat;//时间部分的格式





    /**
     * 获取字符串格式的当前日期（精确到日）
     * @param dateSplitter 日期部分的分隔符
     * @return
     */
    public static String getCurrentDate(DateUtilEnum dateSplitter){
        simpleDateFormat = DateUtils.getSimpleDateFormat(dateSplitter);
        return simpleDateFormat.format(new Date());
    }



    /**
     * 获取字符串格式的当前日期时间（精确到秒）
     * @param dateSplitter 日期部分的分隔符
     * @param timeSplitter 时间部分的分隔符
     * @return
     */
    public static String getCurrentDateTime(DateUtilEnum dateSplitter , DateUtilEnum timeSplitter){
        simpleDateFormat = DateUtils.getSimpleDateTimeFormat(dateSplitter , timeSplitter);
        return simpleDateFormat.format(new Date());
    }


    /**
     * 获取字符串格式的当前日期时间（精确到毫秒）
     * @param dateSplitter 日期部分的分隔符
     * @param timeSplitter 时间部分的分隔符
     * @return
     */
    public static String getCurrentDateTimes(DateUtilEnum dateSplitter , DateUtilEnum timeSplitter){
        simpleDateFormat = DateUtils.getSimpleMillisecondFormat(dateSplitter , timeSplitter);
        return simpleDateFormat.format(new Date());
    }


    /**
     * 将字符串日期格式解析为Date类型
     * @param dateStr
     * @param dateSplitter
     * @return
     */
    public static Date getMonth(String dateStr , DateUtilEnum dateSplitter){
        simpleDateFormat = new SimpleDateFormat(DateUtils.getMonthFormat(dateSplitter));
        return simpleDateFormat.parse(dateStr , new ParsePosition(0));
    }



    /**
     * 获取格式化后的没有分隔符及空格的当前日期字符串
     * @return
     */
    public static String getDateNoSeparator(){
        String DateStr = DateUtils.getCurrentDateTimes(DateUtilEnum.SPACE , DateUtilEnum.SPACE);
        return DateStr.replaceAll(" " , "");
    }


    /**
     * 将日期对象转换成字符串格式（精确到日）
     * @param date
     * @param dateSplitter
     * @return
     */
    public static String getDateToString(Date date , DateUtilEnum dateSplitter){
        simpleDateFormat = DateUtils.getSimpleDateFormat(dateSplitter);
        return simpleDateFormat.format(date);
    }


    /**
     * 获取给定日期向后最近的季度月份
     * @param date
     * @return
     */
    public static Integer getRecentQuarter(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int m = calendar.get(Calendar.MONTH) + 1;

        if(m <= 3){
            return 3;
        }
        if(m <= 6){
            return 6;
        }
        if(m <= 9){
            return 9;
        }
        if(m <= 12){
            return 12;
        }
        return -1;
    }




    /**
     * 获取包含日期的格式化对象（精确到日）
     * @param dateSplitter
     * @return
     */
    private static SimpleDateFormat getSimpleDateFormat(DateUtilEnum dateSplitter){
        return new SimpleDateFormat(DateUtils.getDateFormat(dateSplitter));
    }

    /**
     * 获取包含日期和时间的格式化对象（精确到秒）
     * @param dateSplitter
     * @param timeSplitter
     * @return
     */
    private static SimpleDateFormat getSimpleDateTimeFormat(DateUtilEnum dateSplitter , DateUtilEnum timeSplitter){
        return new SimpleDateFormat(DateUtils.getDateFormat(dateSplitter) + " "
                + DateUtils.getTimeFormat(timeSplitter));
    }


    /**
     * 获取包含日期和时间的格式化对象（精确到毫秒）
     * @param dateSplitter
     * @param timeSplitter
     * @return
     */
    private static SimpleDateFormat getSimpleMillisecondFormat(DateUtilEnum dateSplitter , DateUtilEnum timeSplitter){
        return new SimpleDateFormat(DateUtils.getDateFormat(dateSplitter) + " "
                + DateUtils.getMillisecondFormat(timeSplitter));
    }


    /**
     * 定义日期部分的格式
     * @param dateSplitter
     * @return
     */
    private static String getMonthFormat(DateUtilEnum dateSplitter){
        //定义日期部分的分隔符
        switch (dateSplitter){
            case COLON:
                dateFormat = "yyyy:MM";
                break;
            case SHORTBAR:
                dateFormat = "yyyy-MM";
                break;
            case UNDERLINE:
                dateFormat = "yyyy_MM";
                break;
            case SPACE:
                dateFormat = "yyyy MM";
                break;
            default:
                dateFormat = "yyyy-MM";
                break;
        }
        return DateUtils.dateFormat;
    }


    /**
     * 定义日期部分的格式
     * @param dateSplitter
     * @return
     */
    private static String getDateFormat(DateUtilEnum dateSplitter){
        //定义日期部分的分隔符
        switch (dateSplitter){
            case COLON:
                dateFormat = getMonthFormat(dateSplitter) + ":dd";
                break;
            case SHORTBAR:
                dateFormat = getMonthFormat(dateSplitter) + "-dd";
                break;
            case UNDERLINE:
                dateFormat = getMonthFormat(dateSplitter) + "_dd";
                break;
            case SPACE:
                dateFormat = getMonthFormat(dateSplitter) + " dd";
                break;
            default:
                dateFormat = getMonthFormat(dateSplitter) + "-dd";
                break;
        }
        return DateUtils.dateFormat;
    }





    /**
     * 定义时间部分的字符串格式（精确到秒）
     * @param timeSplitter
     * @return
     */
    private static String getTimeFormat(DateUtilEnum timeSplitter){
        //定义日期部分的分隔符
        switch (timeSplitter){
            case COLON:
                timeFormat = "HH:mm:ss";
                break;
            case SHORTBAR:
                timeFormat = "HH-mm-ss";
                break;
            case UNDERLINE:
                timeFormat = "HH_mm_ss";
                break;
            case SPACE:
                timeFormat = "HH mm ss";
                break;
            default:
                timeFormat = "HH-mm-ss";
                break;
        }
        return DateUtils.timeFormat;
    }



    /**
     * 定义时间部分的格式(精确到毫秒数)
     * @param timeSplitter
     * @return
     */
    private static String getMillisecondFormat(DateUtilEnum timeSplitter){
        //定义日期部分的分隔符
        switch (timeSplitter){
            case COLON:
                timeFormat = DateUtils.getTimeFormat(timeSplitter) + ":SS";
                break;
            case SHORTBAR:
                timeFormat = DateUtils.getTimeFormat(timeSplitter) + "-SS";
                break;
            case UNDERLINE:
                timeFormat = DateUtils.getTimeFormat(timeSplitter) + "_SS";
                break;
            case SPACE:
                timeFormat = DateUtils.getTimeFormat(timeSplitter) + " SS";
                break;
            default:
                timeFormat = DateUtils.getTimeFormat(timeSplitter) + "-SS";
                break;
        }
        return DateUtils.timeFormat;
    }


}
