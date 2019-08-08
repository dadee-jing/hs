package com.ruoyi.duge.third.shunde.mq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class PostClient {

    private static final String TOPIC_NAME = "ZHICHAO";

    public static void main(String[] args) throws Exception {
        // 1.创建一个连接工厂
        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://19.201.248.52:61616");
        // 2.使用工厂创建Connection
        Connection connection = factory.createConnection();

        // 3.开启连接
        connection.start();
        //4.创建一个Session
        //第一个参数：是否开启事务(一般不开启)，如果开启事务，第二个参数无意义
        //第二个参数：应答模式（自动/手动）
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5.通过Session创建一个Destination对象，两种形式：queue、topic
        Topic topic = session.createTopic(TOPIC_NAME);
        //6.通过Session创建一个Producer对象
        MessageProducer producer = session.createProducer(topic);
        //7.创建Message对象
//        TextMessage message = new ActiveMQTextMessage();
//        message.setText("");
        String str = "";
        TextMessage textMessage = session.createTextMessage(str);
        //8.发送消息
        producer.send(textMessage);
        //9.关闭资源
        producer.close();
        session.close();
        connection.close();
    }
}
