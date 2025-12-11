package rodrgq.repository;

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
}
