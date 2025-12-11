package rodrgq.controller;

import io.quarkus.panache.common.Page;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import rodrgq.controller.dto.ApiResponse;
import rodrgq.controller.dto.OrderResponse;
import rodrgq.services.OrderService;

@Produces("application/json")
@Consumes("application/json")
@Path("/customers")
public class OrderController {
    
    @Inject
    OrderService orderService;

    @GET
    @Path("/{customerId}/orders")
    public ApiResponse<OrderResponse> listOrders(@PathParam("customerId") Long customerId,
                               @QueryParam("page") @DefaultValue("0") int page, 
                               @QueryParam("size") @DefaultValue("10") int size) {
        return orderService.findAllByCostumerId(customerId, Page.of(page, size));
    }

}
