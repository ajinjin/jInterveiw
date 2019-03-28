package com.gu.mq;



import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitMQ
 * 1.direct模式 生产者直接发送给blockqueue
 * 2.exchange模式 生产者将消息发送给exchange exchange调度blockqueue 在由消费者消费消息
 *
 */
@Configuration
public class SendRabbitMQ {
    @Autowired
    private AmqpTemplate template;

    //direct发送
    @Bean
    public Queue messeageQueue(){
        return new Queue("mqname");
    }

    public void send(){
        template.convertAndSend("mqname"," 我发了一条消息");
    }



}
