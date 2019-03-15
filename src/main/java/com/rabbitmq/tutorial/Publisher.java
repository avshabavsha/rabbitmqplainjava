package com.rabbitmq.tutorial;

import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

import static com.rabbitmq.tutorial.ExampleConstants.QUEUE_NAME;

public class Publisher {


    public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException, URISyntaxException, IOException, TimeoutException, InterruptedException {
        Channel channel = RabbitHelper.initRabbitMqClient();
        int count = 0;

        System.out.println("Producer starts");
        while(count < 5000){
            String message = "Message number " + count;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            count++;
            System.out.println("Published message: " + message);
            Thread.sleep(5000);
        }
    }
}
