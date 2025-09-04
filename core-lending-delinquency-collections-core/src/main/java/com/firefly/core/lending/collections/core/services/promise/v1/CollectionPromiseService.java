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


package com.firefly.core.lending.collections.core.services.promise.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.collections.interfaces.dtos.promise.v1.CollectionPromiseDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface CollectionPromiseService {
    /**
     * Retrieves a paginated list of collection promises for a specific collection case,
     * filtered based on the provided filter criteria.
     *
     * @param collectionCaseId the unique identifier of the collection case for which promises are being retrieved
     * @param filterRequest the filter criteria to apply when retrieving the collection promises
     * @return a {@link Mono} emitting a {@link PaginationResponse} containing the filtered collection promises
     */
    Mono<PaginationResponse<CollectionPromiseDTO>> findAll(UUID collectionCaseId,
                                                           FilterRequest<CollectionPromiseDTO> filterRequest);

    /**
     * Creates a new collection promise for the specified collection case.
     *
     * @param collectionCaseId the unique identifier of the collection case for which the promise is being created
     * @param dto the {@link CollectionPromiseDTO} object containing the details of the collection promise to be created
     * @return a {@link Mono} emitting the created {@link CollectionPromiseDTO} object upon successful creation
     */
    Mono<CollectionPromiseDTO> create(UUID collectionCaseId, CollectionPromiseDTO dto);

    /**
     * Retrieves a specific collection promise for the given collection case and promise identifiers.
     *
     * @param collectionCaseId the unique identifier of the collection case to which the promise belongs
     * @param promiseId the unique identifier of the promise to be retrieved
     * @return a {@link Mono} emitting the retrieved {@link CollectionPromiseDTO} object, or an empty Mono if not found
     */
    Mono<CollectionPromiseDTO> getById(UUID collectionCaseId, UUID promiseId);

    /**
     * Updates an existing collection promise for the specified collection case and promise IDs
     * with the provided updated details.
     *
     * @param collectionCaseId the unique identifier of the collection case to which the promise belongs
     * @param promiseId the unique identifier of the promise to be updated
     * @param dto the {@link CollectionPromiseDTO} object containing the updated details for the promise
     * @return a {@link Mono} emitting the updated {@link CollectionPromiseDTO} object upon successful update,
     *         or an error signal if the update fails
     */
    Mono<CollectionPromiseDTO> update(UUID collectionCaseId, UUID promiseId, CollectionPromiseDTO dto);

    /**
     * Deletes a specific collection promise identified by the given collection case ID and promise ID.
     *
     * @param collectionCaseId the ID of the collection case to which the promise belongs
     * @param promiseId the ID of the promise to be deleted
     * @return a {@code Mono<Void>} indicating the completion of the delete operation
     */
    Mono<Void> delete(UUID collectionCaseId, UUID promiseId);
}