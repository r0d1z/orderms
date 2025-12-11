package rodrgq.repository;

import java.math.BigDecimal;
import java.util.Arrays;

import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Accumulators.*;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import rodrgq.controller.dto.ApiResponse;
import rodrgq.controller.dto.OrderResponse;
import rodrgq.controller.dto.PaginationResponse;
import rodrgq.entity.OrderEntity;

@ApplicationScoped
public class OrderRepository implements PanacheMongoRepository<OrderEntity> {
    
    public ApiResponse<OrderResponse> findAllByCustomerId(Long customerId, Page page) {
        var query = find("customerId", customerId);
        query = query.page(page);

        var listEntities = query.stream().map(OrderResponse::fromEntity).toList();

        return new ApiResponse<>(listEntities, new PaginationResponse(
            page.index,
            page.size,
            (int) query.count(),
            query.pageCount()
        ));
    }

    public BigDecimal findTotalAmountByCustomerId(Long customerId) {
        
        OrderEntity result = mongoCollection().aggregate(Arrays.asList(
            match(eq("customerId", customerId)), 
            group(null, sum("total", "$total"))
        )).first();

        if (result == null) {
            return BigDecimal.ZERO;
        }

        // O Mongo retorna BigDecimal como Decimal128
        return result.total != null ? result.total : BigDecimal.ZERO;
    }
}
