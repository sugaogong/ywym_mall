package com.java.config;

/**
 * 配置文件
 */
public class YwymConfig {

    /**
     * 默认用户头像
     */
    public static final  String DEFAULT_HEAD_IMG = "/upload/sys/img/default_head_img.png";
    /**
     * 常量 字符串 0否 1是
     */
    public static final String SZERO = "0"; //否
    public static final String SONE = "1"; //是

    /**
     * 图片表中类型
     */
    public static final String IMG_GOODS_IMG = "1"; //礼品图片

    /**
     * 礼品上下架 0下架 1上架
     */
    public static final String GOODS_UPPER = "1"; //上架
    public static final String GOODS_LOWER = "0"; //下架

    /**
     * 订单状态 1待发货 2待收货 3已完成
     */
    public static final String ORDER_STATUS_PEN = "1"; //待发货
    public static final String ORDER_STATUS_REC = "2"; //待收货
    public static final String ORDER_STATUS_COM = "3"; //已完成

    /**
     * 收支类型  -1支出 1入账
     */
    public static final String LOG_USER_TYPE_OUT = "-1"; //出账
    public static final String LOG_USER_TYPE_IN = "1"; //入账

    /**
     * 收支备注  1兑换礼品
     */
    public static final String LOG_USER_REMARK_EXCHANGE = "兑换礼品";

}
