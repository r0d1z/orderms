package rodrgq.controller.dto;

import io.quarkus.panache.common.Page;

public record PaginationResponse(Integer index, Integer size, Integer totalElements, Integer totalPages) {

    public PaginationResponse fromPage(Page page, Integer totalElements, Integer totalPages) {
        return new PaginationResponse(
                page.index,
                page.size,
                totalElements,
                totalPages);
    }
}
