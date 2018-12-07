package com.example.amqp.service;

import com.example.amqp.pojo.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

/**
 * 监听消息队列，有消息进来的时候调用
 */
@Service
public class BookService {

    //queues是个数组，可以指定多个消息队列
    @RabbitListener(queues = "atguigu")
    public void receive(Book object){
        System.out.println("收到消息:"+object);
    }

    //获取消息体和消息头的信息
    @RabbitListener(queues = "gulixueyuan.news")
    public void receive(Message message) throws UnsupportedEncodingException {
        System.out.println("消息体信息——字节数组："+message.getBody());
        System.out.println("消息头信息："+message.getMessageProperties());
        System.out.println("消息体信息——数据："+ new String(message.getBody(),"utf-8"));
    }
}
