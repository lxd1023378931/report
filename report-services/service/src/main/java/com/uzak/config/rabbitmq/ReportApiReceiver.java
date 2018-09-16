package com.uzak.config.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/9/7.
 */
@Component
public class ReportApiReceiver {

    @RabbitListener(queues = "test_queue_name")
    public void process(byte[] msg){
        System.out.println(new String(msg));
    }

}
