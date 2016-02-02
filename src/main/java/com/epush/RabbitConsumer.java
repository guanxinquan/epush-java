package com.epush;

import com.epush.broker.event.LoginEvent;
import com.ericsson.otp.erlang.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


/**
 * Created by guanxinquan on 16/1/29.
 */
public class RabbitConsumer {

    ConnectionFactory factory = new ConnectionFactory();
    ObjectMapper mapper = new ObjectMapper();


    public void doCounsumer()throws IOException, TimeoutException{
        factory.setHost("localhost");
        factory.setVirtualHost("epush");
        factory.setUsername("epush");
        factory.setPassword("epush");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    OtpInputStream InputStream = new  OtpInputStream(body);
                    OtpErlangTuple login = (OtpErlangTuple) OtpErlangTuple.decode(InputStream);

                    LoginEvent loginEvent = new LoginEvent(login);

                    System.out.println(loginEvent);

                    //mapper.writeValueAsString(loginEvent);


                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };

        channel.basicConsume("epush-login-queue",consumer);
    }
}
