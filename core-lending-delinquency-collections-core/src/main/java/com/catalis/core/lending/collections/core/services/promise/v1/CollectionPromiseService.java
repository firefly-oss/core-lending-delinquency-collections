package com.catalis.core.lending.collections.core.services.promise.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.collections.interfaces.dtos.promise.v1.CollectionPromiseDTO;
import reactor.core.publisher.Mono;

public interface CollectionPromiseService {
    /**
     * Retrieves a paginated list of collection promises for a specific collection case,
     * filtered based on the provided filter criteria.
     *
     * @param collectionCaseId the unique identifier of the collection case for which promises are being retrieved
     * @param filterRequest the filter criteria to apply when retrieving the collection promises
     * @return a {@link Mono} emitting a {@link PaginationResponse} containing the filtered collection promises
     */
    Mono<PaginationResponse<CollectionPromiseDTO>> findAll(Long collectionCaseId,
                                                           FilterRequest<CollectionPromiseDTO> filterRequest);

    /**
     * Creates a new collection promise for the specified collection case.
     *
     * @param collectionCaseId the unique identifier of the collection case for which the promise is being created
     * @param dto the {@link CollectionPromiseDTO} object containing the details of the collection promise to be created
     * @return a {@link Mono} emitting the created {@link CollectionPromiseDTO} object upon successful creation
     */
    Mono<CollectionPromiseDTO> create(Long collectionCaseId, CollectionPromiseDTO dto);

    /**
     * Retrieves a specific collection promise for the given collection case and promise identifiers.
     *
     * @param collectionCaseId the unique identifier of the collection case to which the promise belongs
     * @param promiseId the unique identifier of the promise to be retrieved
     * @return a {@link Mono} emitting the retrieved {@link CollectionPromiseDTO} object, or an empty Mono if not found
     */
    Mono<CollectionPromiseDTO> getById(Long collectionCaseId, Long promiseId);

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
    Mono<CollectionPromiseDTO> update(Long collectionCaseId, Long promiseId, CollectionPromiseDTO dto);

    /**
     * Deletes a specific collection promise identified by the given collection case ID and promise ID.
     *
     * @param collectionCaseId the ID of the collection case to which the promise belongs
     * @param promiseId the ID of the promise to be deleted
     * @return a {@code Mono<Void>} indicating the completion of the delete operation
     */
    Mono<Void> delete(Long collectionCaseId, Long promiseId);
}