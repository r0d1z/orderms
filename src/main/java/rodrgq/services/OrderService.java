package rodrgq.services;

import java.math.BigDecimal;
import java.util.List;

import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import rodrgq.entity.OrderEntity;
import rodrgq.entity.OrderItem;
import rodrgq.listener.dto.OrderCreatedEvent;
import rodrgq.repository.OrderRepository;
import rodrgq.controller.dto.ApiResponse;
import rodrgq.controller.dto.OrderResponse;

@ApplicationScoped
public class OrderService {

    @Inject
    OrderRepository orderRepository;

    public void save(OrderCreatedEvent event) {
        var entity = new OrderEntity();
        entity.customerId = event.codigoCliente();
        entity.orderId = event.codigoPedido();
        entity.items = getOrderItems(event);
        entity.total = getTotal(entity);

        orderRepository.persist(entity);
    }

    public ApiResponse<OrderResponse> findAllByCostumerId(Long customerId, Page page) {
        return orderRepository.findAllByCustomerId(customerId, page);
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
