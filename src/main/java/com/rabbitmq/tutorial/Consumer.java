package com.rabbitmq.tutorial;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

import static com.rabbitmq.tutorial.ExampleConstants.QUEUE_NAME;

public class Consumer {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, KeyManagementException, URISyntaxException, TimeoutException {
        final Channel channel = RabbitHelper.initRabbitMqClient();


        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("Received: " + envelope.getRoutingKey() + " message: " + message);
                channel.basicAck(envelope.getDeliveryTag(), false); //need to send ACK to clear message from queue
            }
        };

        System.out.println("Consumer starts");
        channel.basicConsume(QUEUE_NAME, false, consumer);
    }

}
