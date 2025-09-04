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


package com.firefly.core.lending.collections.web.controllers.status.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.collections.core.services.status.v1.CollectionStatusHistoryService;
import com.firefly.core.lending.collections.interfaces.dtos.status.v1.CollectionStatusHistoryDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import jakarta.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/collection-cases/{collectionCaseId}/status-history")
@Tag(name = "CollectionStatusHistory", description = "Operations on Collection Status History")
@RequiredArgsConstructor
public class CollectionStatusHistoryController {

    private final CollectionStatusHistoryService service;

    @GetMapping
    @Operation(summary = "List or search status history records for a collection case")
    public Mono<ResponseEntity<PaginationResponse<CollectionStatusHistoryDTO>>> findAll(
            @PathVariable UUID collectionCaseId,
            @ModelAttribute FilterRequest<CollectionStatusHistoryDTO> filterRequest) {

        return service.findAll(collectionCaseId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new status history entry for a case")
    public Mono<ResponseEntity<CollectionStatusHistoryDTO>> create(
            @PathVariable UUID collectionCaseId,
            @Valid @RequestBody CollectionStatusHistoryDTO dto) {

        return service.create(collectionCaseId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{statusHistoryId}")
    @Operation(summary = "Get a status history record by ID")
    public Mono<ResponseEntity<CollectionStatusHistoryDTO>> getById(
            @PathVariable UUID collectionCaseId,
            @PathVariable UUID statusHistoryId) {

        return service.getById(collectionCaseId, statusHistoryId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{statusHistoryId}")
    @Operation(summary = "Update a status history record")
    public Mono<ResponseEntity<CollectionStatusHistoryDTO>> update(
            @PathVariable UUID collectionCaseId,
            @PathVariable UUID statusHistoryId,
            @Valid @RequestBody CollectionStatusHistoryDTO dto) {

        return service.update(collectionCaseId, statusHistoryId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{statusHistoryId}")
    @Operation(summary = "Delete a status history record")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable UUID collectionCaseId,
            @PathVariable UUID statusHistoryId) {

        return service.delete(collectionCaseId, statusHistoryId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
