package com.prado.rabbitconsumer.consumer;

import com.prado.rabbitconsumer.config.MQConfig;
import com.prado.rabbitconsumer.dto.OrderStatus;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    @Value("${spring.rabbitmq.template.default-receive-queue}")
    private String queue;


    @RabbitListener(queues = MQConfig.QUEUE_NAME)
    public void consumeMessage(OrderStatus orderStatus, Message message) {
        String eventType = (String) message.getMessageProperties().getHeaders().get("eventType");
        // Process the orderStatus object and eventType header
        System.out.println("Received Order: " + orderStatus);
        System.out.println("Event Type: " + eventType);
    }


}
