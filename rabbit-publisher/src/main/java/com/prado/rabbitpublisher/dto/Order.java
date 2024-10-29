package com.prado.rabbitpublisher.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Order {

    private String orderId;
    private String name;
    private int qty;
    private double price;
}
