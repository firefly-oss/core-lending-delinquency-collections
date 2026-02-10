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


package com.firefly.core.lending.collections.core.services.cases.v1;

import org.fireflyframework.core.filters.FilterRequest;
import org.fireflyframework.core.queries.PaginationResponse;
import com.firefly.core.lending.collections.interfaces.dtos.cases.v1.CollectionCaseDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface CollectionCaseService {

    /**
     * Retrieves all collection cases based on the specified filter criteria.
     *
     * @param filterRequest the filter request containing criteria to filter the collection cases
     * @return a Mono containing the paginated response with the filtered collection cases
     */
    Mono<PaginationResponse<CollectionCaseDTO>> findAll(FilterRequest<CollectionCaseDTO> filterRequest);

    /**
     * Creates a new collection case based on the provided data.
     *
     * @param dto the {@link CollectionCaseDTO} object containing the details of the collection case to be created
     * @return a {@link Mono} emitting the created {@link CollectionCaseDTO} object upon successful creation
     */
    Mono<CollectionCaseDTO> create(CollectionCaseDTO dto);

    /**
     * Retrieves a collection case by its unique identifier.
     *
     * @param collectionCaseId the ID of the collection case to be retrieved
     * @return a Mono emitting the retrieved CollectionCaseDTO instance or an empty Mono if not found
     */
    Mono<CollectionCaseDTO> getById(UUID collectionCaseId);

    /**
     * Updates an existing collection case with the specified ID using the provided data.
     *
     * @param collectionCaseId the unique identifier of the collection case to update
     * @param dto the details to update the collection case with
     * @return a Mono emitting the updated CollectionCaseDTO, or an error signal if the update fails
     */
    Mono<CollectionCaseDTO> update(UUID collectionCaseId, CollectionCaseDTO dto);

    /**
     * Deletes a collection case based on the provided collection case ID.
     *
     * @param collectionCaseId the ID of the collection case to be deleted
     * @return a {@code Mono<Void>} indicating successful completion of the deletion operation
     */
    Mono<Void> delete(UUID collectionCaseId);

}
