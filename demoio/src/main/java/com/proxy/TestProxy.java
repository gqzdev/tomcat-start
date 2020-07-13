package com.proxy;
/**
* @program: demo
*
* @description: ddd
*
* @author: wukong
*
* @create: 2020-04-20 21:31
**/
public class TestProxy {
    public static void main(String[] args) {
        BookFacadeProxy proxy = new BookFacadeProxy();
        BookFacadeImpl bookProxy = (BookFacadeImpl) proxy.bind(new BookFacadeImpl());
        bookProxy.addBook();
    }
}
