package com.example.amqp;

import com.example.amqp.pojo.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootAmqpApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    //1.单播（点对点）
    @Test
    public void contextLoads() {
        //两种发送方式：
        //Message需要自己构造一个，好处是可以自定义消息体和消息头的内容
        //send(String exchange, String routingKey, Message message)

        //object默认当成消息体，只需要传入要发送的对象，自动序列化发送给rabbitmq
        //convertAndSend(String exchange, String routingKey, Object object)
        Map<String,Object> map = new HashMap<>();
        map.put("msg","这是第一个消息");
        map.put("data", Arrays.asList("helloworld",123,true));

        //content_type:	application/x-java-serialized-object
        // 对象被默认序列化后发送给rabbitmq
        //  如何将数据序列化为json数据发送出去？
        // rabbitTemplate中MessageConverter(消息转换)器默认用的是
        // private volatile MessageConverter messageConverter = new SimpleMessageConverter();
        // 因此我们只需要重写注册一个MessageConverter
        //rabbitTemplate.convertAndSend("exchange.direct","atguigu.news",map);

        //Book book = new Book("西游记","吴承恩");
        //rabbitTemplate.convertAndSend("exchange.direct","atguigu.news",book);

        rabbitTemplate.convertAndSend("exchange.direct","gulixueyuan.news",map);
    }

    //接收消息
    @Test
    public void receive(){
        Object object = rabbitTemplate.receiveAndConvert("atguigu.news");
        System.out.println(object.getClass());
        System.out.println(object);
    }

    /**
     * 广播
     */
    @Test
    public void sendMsg(){
        Book book = new Book("三国演义","罗贯中");
        //广播所有消息队列都会发送，不用管路由键
        rabbitTemplate.convertAndSend("exchange.fanout","",book);
    }

    /**
     * 路由键匹配规则
     */
    @Test
    public void test(){
        Book book = new Book("红楼梦","曹雪芹");
        rabbitTemplate.convertAndSend("exchange.topic","*.news",book);
    }

    //AmqpAdmin组件的使用
    @Autowired
    AmqpAdmin admin;

    @Test
    public void test2(){
        //以declare开头的都是创建

        //创建一个exchange
        //admin.declareExchange(new DirectExchange("amqpadmin.exchange"));

        //创建一个消息队列
        //String queueName = admin.declareQueue(new Queue("amqpadmin.queue"));
        //System.out.println(queueName);

        //创建绑定规则
        admin.declareBinding(new Binding("amqpadmin.queue",Binding.DestinationType.QUEUE, "amqpadmin.exchange","amqpadmin.key",null));
    }


}
