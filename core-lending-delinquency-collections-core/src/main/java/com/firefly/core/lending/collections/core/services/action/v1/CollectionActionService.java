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


package com.firefly.core.lending.collections.core.services.action.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.collections.interfaces.dtos.action.v1.CollectionActionDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface CollectionActionService {
    /**
     * Retrieves a paginated list of collection actions for a given collection case,
     * filtered based on the provided filter criteria.
     *
     * @param collectionCaseId the ID of the collection case for which actions are being retrieved
     * @param filterRequest the filter criteria to apply when retrieving the collection actions
     * @return a Mono emitting a {@code PaginationResponse} containing the filtered collection actions
     */
    Mono<PaginationResponse<CollectionActionDTO>> findAll(UUID collectionCaseId,
                                                          FilterRequest<CollectionActionDTO> filterRequest);

    /**
     * Creates a new collection action for the specified collection case.
     *
     * @param collectionCaseId the unique identifier of the collection case for which the action is being created
     * @param dto the {@link CollectionActionDTO} object containing the details of the collection action to be created
     * @return a {@link Mono} emitting the created {@link CollectionActionDTO} object upon successful creation
     */
    Mono<CollectionActionDTO> create(UUID collectionCaseId, CollectionActionDTO dto);

    /**
     * Retrieves a specific collection action for a given collection case and action ID.
     *
     * @param collectionCaseId the unique identifier of the collection case to which the action belongs
     * @param actionId the unique identifier of the action to be retrieved
     * @return a Mono emitting the retrieved CollectionActionDTO instance, or an empty Mono if not found
     */
    Mono<CollectionActionDTO> getById(UUID collectionCaseId, UUID actionId);

    /**
     * Updates an existing collection action for the specified collection case ID and action ID
     * with the provided action details.
     *
     * @param collectionCaseId the unique identifier of the collection case to which the action is associated
     * @param actionId the unique identifier of the action to be updated
     * @param dto the {@link CollectionActionDTO} object containing the updated details for the action
     * @return a {@link Mono} emitting the updated {@link CollectionActionDTO} object upon successful update,
     *         or an error signal if the update fails
     */
    Mono<CollectionActionDTO> update(UUID collectionCaseId, UUID actionId, CollectionActionDTO dto);

    /**
     * Deletes a specific collection action associated with the given collection case and action IDs.
     *
     * @param collectionCaseId the ID of the collection case to which the action is associated
     * @param actionId the ID of the action to be deleted
     * @return a {@code Mono<Void>} indicating the completion of the delete operation
     */
    Mono<Void> delete(UUID collectionCaseId, UUID actionId);
}