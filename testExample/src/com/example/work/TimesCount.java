package com.example.work;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日志处理
 */
public class TimesCount {

    //异常上限
    private int exceTimes=3;

    //临时日志记录
    private ArrayList<Map<Integer,String>> tempLogs;

    //初始化日志临时空间
    public TimesCount() {
        tempLogs= new ArrayList<Map<Integer,String>>();
    }

    /**
     * 日志收集-内存暂存
     * @return
     */
    public HashMap<String,String> gatherLog(Integer[] ids,String[] dates){
        HashMap<String,String> result = new HashMap<String,String>();
        try{
            if(null != ids && null !=dates && ids.length == dates.length){
                for(int i = 0;i < ids.length; i++){
                    Map<Integer,String> log = new HashMap<Integer,String>();
                    int key = (int)ids[i];
                    String val = (String)dates[i];
                    log.put(key,val);
                    tempLogs.add(log);
                    result.put("code","0");
                    result.put("msg","收集成功");
                }
            }else{
                result.put("code","1");
                result.put("msg","数据非法");
            }
        }catch (Exception e){
            result.put("code","1");
            result.put("msg","数据异常");
        }
        return result;
    }

    /**
     * 日志计算
     * @return
     */
    public Integer[] countLog(){
        ArrayList<Integer> result = new ArrayList<Integer>();
        Map<String,Integer> countlogs =  new HashMap<>();
        SimpleDateFormat sdfOld = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        SimpleDateFormat sdfNew = new SimpleDateFormat("yyyy-MM-dd");
        try {
            //判断日志是否为空
            if (null != tempLogs && tempLogs.size() > 0) {
                //日志归类:每个id每天一条记录，记录的val为统计次数
                for (Map<Integer, String> logMap : tempLogs) {
                    for (Map.Entry<Integer, String> log : logMap.entrySet()) {
                        String keyStr = log.getKey() +"$"+ sdfNew.format(sdfOld.parse(log.getValue()));
                        Integer countNum = countlogs.get(keyStr);
                        if (null == countNum || countNum == 0) {
                            countlogs.put(keyStr,1);
                        }else{
                            countlogs.put(keyStr,countNum+1);
                        }
                    }
                }
                //计算大于异常次数的id
                for(Map.Entry<String,Integer> log:countlogs.entrySet()){
                    if(log.getValue() > exceTimes){
                        String key = log.getKey();
                        result.add(Integer.parseInt(key.substring(0,key.indexOf("$"))));
                    }
                }
            }
        }catch (Exception e){
            System.out.println("数据计算异常！");
            e.printStackTrace();
        }
        Integer [] results  = new Integer[result.size()];
        result.toArray(results);
        return results;
    }
}
