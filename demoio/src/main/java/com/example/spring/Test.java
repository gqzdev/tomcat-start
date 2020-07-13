package com.example.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by smlz on 2020/4/4.
 */
public class Test {

    public static void main(String[] args) {
        assert 1==2;
        System.out.println(12|10);
        assert false : "断言失败，此表达式的信息将会在抛出异常的时候输出！";
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application.xml");
        System.out.println("context 启动成功");

        // 从 context 中取出我们的 Bean，而不是用 new MessageServiceImpl() 这种方式
        MessageService messageService = context.getBean(MessageService.class);
        // 这句将输出: hello world
        System.out.println(messageService.getMessage());
    }
}
