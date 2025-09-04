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


package com.firefly.core.lending.collections.web.controllers.action.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.collections.core.services.action.v1.CollectionActionService;
import com.firefly.core.lending.collections.interfaces.dtos.action.v1.CollectionActionDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import jakarta.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/collection-cases/{collectionCaseId}/actions")
@Tag(name = "CollectionAction", description = "Operations on Collection Actions")
@RequiredArgsConstructor
public class CollectionActionController {

    private final CollectionActionService service;

    @GetMapping
    @Operation(summary = "List or search actions for a collection case")
    public Mono<ResponseEntity<PaginationResponse<CollectionActionDTO>>> findAll(
            @PathVariable UUID collectionCaseId,
            @ModelAttribute FilterRequest<CollectionActionDTO> filterRequest) {

        return service.findAll(collectionCaseId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new action on a collection case")
    public Mono<ResponseEntity<CollectionActionDTO>> create(
            @PathVariable UUID collectionCaseId,
            @Valid @RequestBody CollectionActionDTO dto) {

        return service.create(collectionCaseId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{actionId}")
    @Operation(summary = "Get a collection action by ID")
    public Mono<ResponseEntity<CollectionActionDTO>> getById(
            @PathVariable UUID collectionCaseId,
            @PathVariable UUID actionId) {

        return service.getById(collectionCaseId, actionId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{actionId}")
    @Operation(summary = "Update a collection action")
    public Mono<ResponseEntity<CollectionActionDTO>> update(
            @PathVariable UUID collectionCaseId,
            @PathVariable UUID actionId,
            @Valid @RequestBody CollectionActionDTO dto) {

        return service.update(collectionCaseId, actionId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{actionId}")
    @Operation(summary = "Delete a collection action")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable UUID collectionCaseId,
            @PathVariable UUID actionId) {

        return service.delete(collectionCaseId, actionId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}