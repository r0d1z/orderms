package rodrgq.entity;

import java.math.BigDecimal;
import java.util.List;

import io.quarkus.mongodb.panache.PanacheMongoEntityBase;
import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity(collection="tb_orders")
public class OrderEntity extends PanacheMongoEntityBase{
    
    public long orderId;

    public long customerId;
    
    public BigDecimal total;

    public List<OrderItem> items;

}
