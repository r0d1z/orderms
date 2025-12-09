package rodrgq.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import rodrgq.entity.OrderEntity;

@ApplicationScoped
public class OrderRepository implements PanacheMongoRepository<OrderEntity> {

}
