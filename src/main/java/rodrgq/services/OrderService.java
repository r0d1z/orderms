package rodrgq.services;

import java.math.BigDecimal;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import rodrgq.entity.OrderEntity;
import rodrgq.entity.OrderItem;
import rodrgq.listener.dto.OrderCreatedEvent;

@ApplicationScoped
public class OrderService {

    public void save(OrderCreatedEvent event) {
        var entity = new OrderEntity();
        entity.customerId = event.codigoCliente();
        entity.orderId = event.codigoPedido();
        entity.items = getOrderItems(event);
        entity.total = getTotal(entity);

        entity.persist();
    }

    private BigDecimal getTotal(OrderEntity entity) {
        return entity.items.stream()
            .map(i -> i.price.multiply(BigDecimal.valueOf(i.quantity)))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<OrderItem> getOrderItems(OrderCreatedEvent event) {
        return event.itens().stream().map(i -> new OrderItem(
            i.produto(),
            i.quantidade(),
            i.preco()
        )).toList();
    }
}
