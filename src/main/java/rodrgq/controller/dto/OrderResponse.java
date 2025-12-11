package rodrgq.controller.dto;

import java.math.BigDecimal;

import rodrgq.entity.OrderEntity;

public record OrderResponse(Long id, Long customerId, BigDecimal totalAmount) {

    public static OrderResponse fromEntity(OrderEntity entity) {
        return new OrderResponse(
                entity.orderId,
                entity.customerId,
                entity.total
        );
    }
}
