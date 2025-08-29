package com.firefly.core.lending.collections.web.controllers.escalation.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.collections.core.services.escalation.v1.CollectionEscalationService;
import com.firefly.core.lending.collections.interfaces.dtos.escalation.v1.CollectionEscalationDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/collection-cases/{collectionCaseId}/escalations")
@Tag(name = "CollectionEscalation", description = "Operations on Escalations in Collection Cases")
@RequiredArgsConstructor
public class CollectionEscalationController {

    private final CollectionEscalationService service;

    @GetMapping
    @Operation(summary = "List or search escalations for a collection case")
    public Mono<ResponseEntity<PaginationResponse<CollectionEscalationDTO>>> findAll(
            @PathVariable Long collectionCaseId,
            @ModelAttribute FilterRequest<CollectionEscalationDTO> filterRequest) {

        return service.findAll(collectionCaseId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new escalation")
    public Mono<ResponseEntity<CollectionEscalationDTO>> create(
            @PathVariable Long collectionCaseId,
            @RequestBody CollectionEscalationDTO dto) {

        return service.create(collectionCaseId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{escalationId}")
    @Operation(summary = "Get an escalation record by ID")
    public Mono<ResponseEntity<CollectionEscalationDTO>> getById(
            @PathVariable Long collectionCaseId,
            @PathVariable Long escalationId) {

        return service.getById(collectionCaseId, escalationId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{escalationId}")
    @Operation(summary = "Update an escalation record")
    public Mono<ResponseEntity<CollectionEscalationDTO>> update(
            @PathVariable Long collectionCaseId,
            @PathVariable Long escalationId,
            @RequestBody CollectionEscalationDTO dto) {

        return service.update(collectionCaseId, escalationId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{escalationId}")
    @Operation(summary = "Delete an escalation record")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable Long collectionCaseId,
            @PathVariable Long escalationId) {

        return service.delete(collectionCaseId, escalationId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
