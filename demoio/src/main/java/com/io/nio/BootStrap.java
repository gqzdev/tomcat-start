package com.io.nio;

import java.io.IOException;
import java.util.Random;

/**
* @program: demo
* @description: 启动类
* @author: gqzdev
* @create: 2020-04-10 21:18
**/
public class BootStrap {
    public  static void main(String[] args) throws IOException {

        new Thread(new NioServer(8080)).start();
        //run2();
    }

    public static void run(){
        String str = "";
        try {
            while (true){
                int i = new Random().nextInt(333333333);
                str = str +  i ;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(str);
        }
    }


    public static void run2(){
        String str = "";
        try {
            while (true){
                int i = new Random().nextInt(333333333);
                str = str + str+  i ;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(str);
        }
    }

}
