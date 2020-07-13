package com.io.aio;

import java.io.IOException;

/**
* @program: demo
*
* @description: 启动类
*
* @author: wukong
*
* @create: 2020-04-10 22:01
**/
public class BootStrap {
    public  static void main(String[] args) throws IOException {
        new Thread(new AioServer(8080)).start();
    }
}
