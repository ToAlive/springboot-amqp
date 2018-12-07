package com.example.amqp;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 自动配置
 * 1.RabbitAutoConfiguration
 * 2.自动了链接工程CachingConnectionFactory
 * 3.RabbitProperties 封装了RabbitMQ的配置
 * 4.RabbitTemplate 给RabbitMQ发送和接收消息的组件
 * 5.AmqpAdmin RabbitMQ的系统管理功能组件，他可以用来创建和删除交换器、队列等等...
 * 6.@EnableRabbit + @RabbitListener 来监听消息队列有没有消息来了
 */
@EnableRabbit
@SpringBootApplication
public class SpringbootAmqpApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAmqpApplication.class, args);
    }
}
