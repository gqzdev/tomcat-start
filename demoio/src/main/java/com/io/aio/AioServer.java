package com.io.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

/**
* @program: demo
*
* @description: 异步io服务端
*
* @author: wukong
*
* @create: 2020-04-10 22:01
**/
public class AioServer implements Runnable{
    public static AtomicLong clientCount = new AtomicLong(0);
    public CountDownLatch latch;
    public AsynchronousServerSocketChannel channel;
    public AioServer(int port) {
        try {
            channel = AsynchronousServerSocketChannel.open();
            channel.bind(new InetSocketAddress(port));
            System.out.println("服务器已启动，端口号：" + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        latch = new CountDownLatch(1);
        channel.accept(this,new AcceptHandler());
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
