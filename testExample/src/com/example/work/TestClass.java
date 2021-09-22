package com.example.work;

public class TestClass {
    public static void main(String[] args) {
        TimesCount tc = new TimesCount();

        Integer[] obj = {1,1,2,2,3,4};
        String[] obj1 = {"2021-09-22 12:19","2021-09-22 12:19","2021-09-22 12:19","2021-09-22 12:19","2021-09-22 12:19","2021-09-22 12:19"};
        tc.gatherLog(obj,obj1);
        Integer [] result = tc.countLog();
        for(Integer re :result){
            System.out.println(re);
        }

    }
}
