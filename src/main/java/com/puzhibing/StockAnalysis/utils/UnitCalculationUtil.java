package com.puzhibing.StockAnalysis.utils;


import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 定义对数据进行计算乘以单位
 */
@Component
public class UnitCalculationUtil {


    /**
     * 计算函数
     * @param unit      需要计算的单位
     * @param object    需要计算的对象
     * @param clazz     对象的类型
     * @return
     */
    public Object calculation(String unit , Object object , Class clazz){
        float u = Float.valueOf(unit).floatValue();
        Field[] fields = clazz.getDeclaredFields();//获取类中定义的所有属性数组
        for (Field field : fields){
            String name = field.getName();
            name = name.substring(0,1).toLowerCase() + name.substring(1);
            Method getMethod = null;
            Method setMethod = null;
            try {
                getMethod = clazz.getDeclaredMethod("get" + name);
                setMethod = clazz.getDeclaredMethod("set" + name , String.class);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            try {
                String str = (String) getMethod.invoke(object);//获取对象属性封装的值
                setMethod.invoke(object , String.valueOf(u * Float.valueOf(str).floatValue()));//重新设置对象属性中的值
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        }

        return object;
    }
}
