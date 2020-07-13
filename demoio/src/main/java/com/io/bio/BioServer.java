package com.io.bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
* @program: demo
*
* @description: bio
*
* @author: wukong
*
* @create: 2020-04-10 20:03
**/
public class BioServer {

    private static int PORT = 8080;

    private static ServerSocket server;

    static ExecutorService executorServices = Executors.newCachedThreadPool();

    public  static void main(String[] args) throws IOException{
        if(server != null) return;
        try{
            server = new ServerSocket(PORT);
            System.out.println("服务器启动成功，准备接受请求");
            while(true){
                Socket socket = server.accept();
                executorServices.submit(new ServerHandler(socket));
            }
        }finally{
            if(server != null){
                System.out.println("服务器已关闭。");
                server.close();
                server = null;
            }
        }
    }


}

class ServerHandler implements Runnable{
    private Socket socket;
    public ServerHandler(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        InputStream in = null;
        OutputStream out = null;
        try{
            byte[] recv = new byte[1024];
            in = socket.getInputStream();
            in.read(recv);
            System.out.println("服务器收到消息：" + new String(recv));
            out = socket.getOutputStream();
            out.write("hello client ".getBytes());
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(in != null){
                try {
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                in = null;
            }
            if(in != null){
                try {
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                out = null;
            }
            if(socket != null){
                try {
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                socket = null;
            }
        }
    }
}