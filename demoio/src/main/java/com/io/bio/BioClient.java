package com.io.bio;

import java.io.*;
import java.net.Socket;

/**
* @program: demo
*
* @description: bioclient
*
* @author: wukong
*
* @create: 2020-04-10 20:19
**/
public class BioClient {
    public static byte[] intToBytes(int value)
    {
        byte[] byte_src = new byte[4];
        byte_src[0] = (byte) ((value & 0xFF000000)>>24);
        byte_src[1] = (byte) ((value & 0x00FF0000)>>16);
        byte_src[2] = (byte) ((value & 0x0000FF00)>>8);
        byte_src[3] = (byte) ((value & 0x000000FF));
        return byte_src;
    }
    private static int SERVER_PORT = 8080;
    private static String SERVER_IP = "127.0.0.1";
    public static void main(String[] args){
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try{
            socket = new Socket(SERVER_IP,SERVER_PORT);
            String hello = "hello server";
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(hello.getBytes());
            outputStream.flush();
            InputStream inputStream = socket.getInputStream();
            byte[] rev = new byte[1024];
            inputStream.read(rev);
            System.out.println("client receive: "+new String(rev));
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
