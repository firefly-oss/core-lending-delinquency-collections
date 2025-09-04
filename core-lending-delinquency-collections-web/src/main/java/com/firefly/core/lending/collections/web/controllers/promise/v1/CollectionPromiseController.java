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


package com.firefly.core.lending.collections.web.controllers.promise.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.collections.core.services.promise.v1.CollectionPromiseService;
import com.firefly.core.lending.collections.interfaces.dtos.promise.v1.CollectionPromiseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import jakarta.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/collection-cases/{collectionCaseId}/promises")
@Tag(name = "CollectionPromise", description = "Operations on Payment Promises in Collection Cases")
@RequiredArgsConstructor
public class CollectionPromiseController {

    private final CollectionPromiseService service;

    @GetMapping
    @Operation(summary = "List or search promises for a collection case")
    public Mono<ResponseEntity<PaginationResponse<CollectionPromiseDTO>>> findAll(
            @PathVariable UUID collectionCaseId,
            @ModelAttribute FilterRequest<CollectionPromiseDTO> filterRequest) {

        return service.findAll(collectionCaseId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new payment promise")
    public Mono<ResponseEntity<CollectionPromiseDTO>> create(
            @PathVariable UUID collectionCaseId,
            @Valid @RequestBody CollectionPromiseDTO dto) {

        return service.create(collectionCaseId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{promiseId}")
    @Operation(summary = "Get a payment promise by ID")
    public Mono<ResponseEntity<CollectionPromiseDTO>> getById(
            @PathVariable UUID collectionCaseId,
            @PathVariable UUID promiseId) {

        return service.getById(collectionCaseId, promiseId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{promiseId}")
    @Operation(summary = "Update a payment promise")
    public Mono<ResponseEntity<CollectionPromiseDTO>> update(
            @PathVariable UUID collectionCaseId,
            @PathVariable UUID promiseId,
            @Valid @RequestBody CollectionPromiseDTO dto) {

        return service.update(collectionCaseId, promiseId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{promiseId}")
    @Operation(summary = "Delete a payment promise record")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable UUID collectionCaseId,
            @PathVariable UUID promiseId) {

        return service.delete(collectionCaseId, promiseId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
