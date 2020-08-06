package com.example.demo.codeLearn;

import com.google.gson.internal.$Gson$Types;

import java.util.ArrayList;
import java.util.List;

public class MaxKey {
    public static void main(String[] args) {
                 String input = "a abc dfdfd df dfdf df 5asd45asd456as456da546d45asd4asd5a dfdfdf dfdfdf5 dfdfdf fdsfsef ";
        ArrayList arrayList = new ArrayList() ;
        int k=0;
               for (int i =0 ; i< input.length() ; ){
                   char  res = input.charAt(i) ;
                   if ((int) res == 32){
                       arrayList.add(input.substring(k+1,i)) ;
                       k=i;
                       i=(k+1);
                   }else {
                       i++;
                   }

               }
        long max = 0 ;
        int index = 0 ;
               for (int i=0;i<arrayList.size();i++){
                   String str = (String) arrayList.get(i);
                   System.out.println(str);
                   long temp = 0 ;

                  for (int j = 0 ; j < str.length(); j++){

                      char kt = str.charAt(j) ;
                      System.out.println(kt);
                      System.out.println((int) kt);
                      temp += (int) kt ;
                      if (j == str.length()-1){
                          System.out.println(temp);
                      }
                      }

                   if (temp> max){
                       max = temp ;
                        index = i;
                   }

               }
        System.out.println(index);
        System.out.println(arrayList.get(5));
        System.out.println(arrayList.get(index) + "____"+ max );
    }
}
