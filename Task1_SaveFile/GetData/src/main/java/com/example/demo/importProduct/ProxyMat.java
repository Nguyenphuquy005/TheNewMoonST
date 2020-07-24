package com.example.demo.importProduct;

import org.springframework.http.HttpEntity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.net.InetAddress;


public class ProxyMat {
//    File file=null;
//    static RandomAccessFile read=null;
//    public ProxyMat(){
//        file=new File("proxies.txt");
//        try {
//            read=new RandomAccessFile(file,"rw");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void checkproxies(){
//        try{
//            String line;
//            for(int i=0;i<25;i++){
//                if((line=read.readLine())!=null){
//                    System.out.println(line);
//                    String[] hp=line.split(":");
//                    InetAddress addr=InetAddress.getByName(hp[0]);
//                    if(addr.isReachable(5000)){
//                        System.out.println("reached");
//                        ensocketize(hp[0],Integer.parseInt(hp[1]));
//                    }
//                }
//            }
//        }catch(Exception ex){ex.printStackTrace();}
//    }
//
//    public void ensocketize(String host,int port){
//        try{
//            File pros=new File("working.txt");
//            HttpClient client=new DefaultHttpClient();
//            HttpGet get=new HttpGet("http://blanksite.com/");
//            HttpHost proxy=new HttpHost(host,port);
//            client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
//            client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 15000);
//            HttpResponse response=client.execute(get);
//            HttpEntity enti=response.getEntity();
//            if(response!=null){
//                System.out.println(response.getStatusLine());
//                System.out.println(response.toString());
//                System.out.println(host+":"+port+" @@ working");
//            }
//        }catch(Exception ex){System.out.println("Proxy failed");}
//    }
}
