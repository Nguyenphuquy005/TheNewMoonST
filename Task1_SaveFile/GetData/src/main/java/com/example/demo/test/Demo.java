package com.example.demo.test;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public  static void  main(String[] args){
        List<String> arrayList = new ArrayList<>();
         arrayList.add("1");
         arrayList.add("2");
         arrayList.add("3");
         arrayList.add("4");
         arrayList.add("5");
        System.out.println(arrayList);
      int i =1;
      while (i <= arrayList.size()){
          arrayList.remove(0);
      }
        System.out.println(arrayList);
    }
}

