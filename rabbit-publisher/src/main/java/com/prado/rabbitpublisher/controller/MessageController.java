package com.prado.rabbitpublisher.controller;

import com.prado.rabbitpublisher.dto.Order;
import com.prado.rabbitpublisher.dto.OrderStatus;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class MessageController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.template.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.template.routing-key}")
    private String routingKey;


    @PostMapping("/{restaurantName}")
    public String orderFood(@RequestBody Order order,
                            @PathVariable String restaurantName,
                            @RequestParam String eventType) {

        order.setOrderId(UUID.randomUUID().toString());
        OrderStatus orderStatus = OrderStatus.builder()
                            .order(order)
                            .build();

        MessagePostProcessor messagePostProcessor = message -> {
            message.getMessageProperties().setHeader("eventType", eventType);
            return message;
        };

        rabbitTemplate.convertAndSend(exchange, routingKey,orderStatus, messagePostProcessor);
        return "Order sent to " + restaurantName;
    }

}