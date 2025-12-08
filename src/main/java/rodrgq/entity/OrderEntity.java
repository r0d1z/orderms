package rodrgq.entity;

import java.math.BigDecimal;
import java.util.List;

import org.bson.types.ObjectId;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity(collection="tb_orders")
public class OrderEntity extends PanacheMongoEntity {
    
    public ObjectId customerId;
    
    public BigDecimal total;

    public List<OrderItem> items;

}
