package com.java.sys.common.utils;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * List集合排序工具类
 */
public class ListSortUtil<T> {

    /**
     * @param targetList 要排序的实体类List集合
     * @param sortField  排序字段(实体类属性名)
     * @param sortMode   true正序，false逆序
     */
    @SuppressWarnings("all")
    public static <T> void sort(List<T> targetList, final String sortField,
                                final boolean sortMode) {
        if (targetList == null || targetList.size() < 2 || sortField == null || sortField.length() == 0) {
            return;
        }
        Collections.sort(targetList, new Comparator() {
            @Override
            public int compare(Object obj1, Object obj2) {
                int retVal = 0;
                try {
                    // 获取getXxx()方法名称
                    String methodStr = "get" + sortField.substring(0, 1).toUpperCase() + sortField.substring(1);
                    Method method1 = ((T) obj1).getClass().getMethod(methodStr, null);
                    Method method2 = ((T) obj2).getClass().getMethod(methodStr, null);
                    Date date1 = (Date) method1.invoke((T) obj1, new Object[]{});
                    Date date2 = (Date) method2.invoke((T) obj2, new Object[]{});
                    if (date1.before(date2)) {
                        retVal = 1;
                    } else {
                        retVal = -1;
                    }
                    /*if (sortMode) {
                        retVal = method1.invoke(((T) obj1), null).toString().compareTo(method2.invoke(((T) obj2), null).toString());
                    } else {
                        retVal = method2.invoke(((T) obj2), null).toString().compareTo(method1.invoke(((T) obj1), null).toString());
                    }*/
                } catch (Exception e) {
                    System.out.println("List<" + ((T) obj1).getClass().getName() + ">排序异常！");
                    e.printStackTrace();
                }
                return retVal;
            }
        });
    }
}

