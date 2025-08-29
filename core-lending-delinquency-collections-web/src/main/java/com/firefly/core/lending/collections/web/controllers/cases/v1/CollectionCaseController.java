package com.firefly.core.lending.collections.web.controllers.cases.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.collections.core.services.cases.v1.CollectionCaseService;
import com.firefly.core.lending.collections.interfaces.dtos.cases.v1.CollectionCaseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/collection-cases")
@Tag(name = "CollectionCase", description = "Operations on Collection Cases")
@RequiredArgsConstructor
public class CollectionCaseController {

    private final CollectionCaseService service;

    @GetMapping
    @Operation(summary = "List or search collection cases")
    public Mono<ResponseEntity<PaginationResponse<CollectionCaseDTO>>> findAll(
            @ModelAttribute FilterRequest<CollectionCaseDTO> filterRequest) {

        return service.findAll(filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new collection case")
    public Mono<ResponseEntity<CollectionCaseDTO>> create(@RequestBody CollectionCaseDTO dto) {
        return service.create(dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{collectionCaseId}")
    @Operation(summary = "Get a collection case by ID")
    public Mono<ResponseEntity<CollectionCaseDTO>> getById(@PathVariable Long collectionCaseId) {
        return service.getById(collectionCaseId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{collectionCaseId}")
    @Operation(summary = "Update a collection case")
    public Mono<ResponseEntity<CollectionCaseDTO>> update(
            @PathVariable Long collectionCaseId,
            @RequestBody CollectionCaseDTO dto) {

        return service.update(collectionCaseId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{collectionCaseId}")
    @Operation(summary = "Delete a collection case")
    public Mono<ResponseEntity<Void>> delete(@PathVariable Long collectionCaseId) {
        return service.delete(collectionCaseId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
