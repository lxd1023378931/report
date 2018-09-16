package com.uzak.config;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeoutException;

/**
 * Created by Administrator on 2018/9/6.
 */
@Component
@Configuration
@ConfigurationProperties(prefix = "rabbitmq")
@Data
public class RabbitMQClient {

    public static final String TEST_QUEUE_NAME = "test_queue_name";

    private Logger logger = LoggerFactory.getLogger(RabbitMQClient.class);
    private static ConcurrentHashMap<String,Connection> localConnections = new ConcurrentHashMap<>();
    private String host;
    private String username;
    private String password;
    private int port;
    private static ConnectionFactory factory;

    private void createFactory(){
        if(factory == null){
            synchronized (this.getClass()){
                if(factory == null){
                    synchronized (RabbitMQClient.class){
                        if(factory == null){
                            factory = new ConnectionFactory();
                            factory.setHost(host);
                            factory.setUsername(username);
                            factory.setPassword(password);
                            factory.setVirtualHost("/");
                            factory.setPort(port);
                        }
                    }
                }
            }
        }
    }

    private Connection getConnection(String queueName){
        Connection connection = null;
        try {
            if (localConnections.contains(queueName)) {
                connection = localConnections.get(queueName);
            } else {
                createFactory();
                connection = factory.newConnection();
                localConnections.put(queueName,connection);
            }
        }catch (TimeoutException | IOException e){
            logger.error("RabbitMQ connection create error!",e);
        }
        return connection;
    }

    public void sendMsgBasicPublish(String queueName,String msg){

        synchronized (this.getClass()){
            Channel channel = null;
            try {
                channel = getConnection(queueName).createChannel();
                //queueDeclare第一个参数表示队列名称、第二个参数为是否持久化（true表示是，队列将在服务器重启时生存）、第三个参数为是否是独占队列（创建者可以使用的私有队列，断开后自动删除）、第四个参数为当所有消费者客户端连接断开时是否自动删除队列、第五个参数为队列的其他参数
                channel.queueDeclare(queueName,true,false,false,null);
                channel.basicPublish("",queueName,null,msg.getBytes("ISO-8859-1"));
            }catch ( IOException e){
                logger.error("RabbitMQ connection create error!",e);
            }finally {
                if(channel != null){
                    try {
                        channel.close();
                    }catch (TimeoutException |IOException e){
                        logger.error("RabbitMQ connection close error!",e);
                    }
                }
            }
        }
    }
}
