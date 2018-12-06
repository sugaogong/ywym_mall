package com.java.common.utils;

import com.java.sys.common.utils.Tool;
import net.sf.json.JSONObject;

public class ElasticTool {
    public static final String IP = "http://120.78.9.212:9200";


    /**
     * Cluster Health
     * 查看elasticsearch服务状态
     * @return
     */
    public static String health(){
        return Tool.get(IP+"/_cat/health?v");
    }


    /**
     * 获取节点信息
     * @return
     */
    public static String nodes(){
        return Tool.get(IP+"/_cat/nodes?v");
    }

    /**
     * 列出所有索引
     * @return
     */
    public static String indices(){
        return Tool.get(IP+"/_cat/indices?v");
    }

    /**
     * 创建索引
     * @return
     */
    public static String createIndex(String indexName){
        return Tool.put(null,IP+"/"+indexName+"?pretty");
    }


    /**
     * 保存数据
     * @param indexName 索引名称
     * @param type 类型
     * @param id id
     * @param json 数据对象
     * @return
     */
    public static String save(String indexName, String type, String id, JSONObject json){
        return Tool.put(json.toString(),IP+"/"+indexName+"/"+type+"/"+id+"?pretty");
    }

    /**
     * 查询数据
     * @param indexName 索引名称
     * @param type 类型
     * @param id id
     * @return
     */
    public static String get(String indexName,String type,String id){
        return Tool.get(IP+"/"+indexName+"/"+type+"/"+id+"?pretty");
    }


    /**
     * 删除索引
     * @param indexName
     * @return
     */
    public static String deleteIndex(String indexName){
        return Tool.delete(IP+"/"+indexName+"?pretty");
    }




    /*public static void main(String[] args) {
        *//*String result = createIndex("index-4");
        System.out.println(result);
        System.out.println(indices());*//*
        *//*JSONObject json = new JSONObject();
        json.element("name","name-1");
        String result = save("index-1","user","111",json);
        System.out.println(result);*//*
        //System.out.println(get("index-1","user","111"));
        System.out.println(deleteIndex("index-4"));
        System.out.println(indices());
    }*/



}
