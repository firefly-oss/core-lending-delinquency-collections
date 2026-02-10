/*
 * Copyright 2025 Firefly Software Solutions Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.firefly.core.lending.collections.web.controllers.escalation.v1;

import org.fireflyframework.core.filters.FilterRequest;
import org.fireflyframework.core.queries.PaginationResponse;
import com.firefly.core.lending.collections.core.services.escalation.v1.CollectionEscalationService;
import com.firefly.core.lending.collections.interfaces.dtos.escalation.v1.CollectionEscalationDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import jakarta.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/collection-cases/{collectionCaseId}/escalations")
@Tag(name = "CollectionEscalation", description = "Operations on Escalations in Collection Cases")
@RequiredArgsConstructor
public class CollectionEscalationController {

    private final CollectionEscalationService service;

    @GetMapping
    @Operation(summary = "List or search escalations for a collection case")
    public Mono<ResponseEntity<PaginationResponse<CollectionEscalationDTO>>> findAll(
            @PathVariable UUID collectionCaseId,
            @ModelAttribute FilterRequest<CollectionEscalationDTO> filterRequest) {

        return service.findAll(collectionCaseId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new escalation")
    public Mono<ResponseEntity<CollectionEscalationDTO>> create(
            @PathVariable UUID collectionCaseId,
            @Valid @RequestBody CollectionEscalationDTO dto) {

        return service.create(collectionCaseId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{escalationId}")
    @Operation(summary = "Get an escalation record by ID")
    public Mono<ResponseEntity<CollectionEscalationDTO>> getById(
            @PathVariable UUID collectionCaseId,
            @PathVariable UUID escalationId) {

        return service.getById(collectionCaseId, escalationId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{escalationId}")
    @Operation(summary = "Update an escalation record")
    public Mono<ResponseEntity<CollectionEscalationDTO>> update(
            @PathVariable UUID collectionCaseId,
            @PathVariable UUID escalationId,
            @Valid @RequestBody CollectionEscalationDTO dto) {

        return service.update(collectionCaseId, escalationId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{escalationId}")
    @Operation(summary = "Delete an escalation record")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable UUID collectionCaseId,
            @PathVariable UUID escalationId) {

        return service.delete(collectionCaseId, escalationId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
