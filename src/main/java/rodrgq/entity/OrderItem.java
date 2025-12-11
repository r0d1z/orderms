package rodrgq.entity;

import java.math.BigDecimal;


public class OrderItem {

    public OrderItem() {
    }

    public OrderItem(String product, Integer quantity, BigDecimal price) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public String product;
    public Integer quantity;
    public BigDecimal price;

}
