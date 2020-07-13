package com.example.demo;

import java.util.ArrayList;
import java.util.List;

/**
* @program: demo
*
* @description: box
*
* @author: wukong
*
* @create: 2020-04-22 21:42
**/
public class Box<T> {
    private List<T> list = new ArrayList<>();
    public T getT(){
        return list.get(list.size()-1);
    }
    public void addT(T t){
         list.add(t);
    }
}
