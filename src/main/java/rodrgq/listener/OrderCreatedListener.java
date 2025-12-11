package rodrgq.listener;

import java.io.IOException;
import java.util.concurrent.CompletionStage;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import rodrgq.listener.dto.OrderCreatedEvent;
import rodrgq.services.OrderService;

@ApplicationScoped
public class OrderCreatedListener {

    private static final Logger logger = LoggerFactory.getLogger(OrderCreatedListener.class); // Use static logger

    @Inject
    ObjectMapper objectMapper;

    @Inject
    OrderService orderService;

    @Incoming("orders")
    public CompletionStage<Void> onOrderCreated(Message<byte[]> message) {
        
        try {
            OrderCreatedEvent eventPayload = objectMapper.readValue(message.getPayload(), OrderCreatedEvent.class);

            logger.info("Received Order Created Event: Order ID - {}, Customer ID - {}, Items Count - {}, First Item Product - {}",
                    eventPayload.codigoPedido(),
                    eventPayload.codigoCliente(),
                    eventPayload.itens().size(),
                    eventPayload.itens().get(0).produto());
            
            orderService.save(eventPayload);
        
        } catch (IOException e) {
            logger.error("Failed to deserialize message", e);
            return message.nack(e);
        }

        return message.ack();
    }
}
