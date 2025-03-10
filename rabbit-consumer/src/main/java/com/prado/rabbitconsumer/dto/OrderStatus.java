package com.prado.rabbitconsumer.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class OrderStatus {

    private Order order;
    private String status;
    private String message;
}
