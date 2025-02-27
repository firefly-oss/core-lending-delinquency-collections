package com.catalis.core.lending.collections.core.services.cases.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.collections.interfaces.dtos.cases.v1.CollectionCaseDTO;
import reactor.core.publisher.Mono;

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
    Mono<CollectionCaseDTO> getById(Long collectionCaseId);

    /**
     * Updates an existing collection case with the specified ID using the provided data.
     *
     * @param collectionCaseId the unique identifier of the collection case to update
     * @param dto the details to update the collection case with
     * @return a Mono emitting the updated CollectionCaseDTO, or an error signal if the update fails
     */
    Mono<CollectionCaseDTO> update(Long collectionCaseId, CollectionCaseDTO dto);

    /**
     * Deletes a collection case based on the provided collection case ID.
     *
     * @param collectionCaseId the ID of the collection case to be deleted
     * @return a {@code Mono<Void>} indicating successful completion of the deletion operation
     */
    Mono<Void> delete(Long collectionCaseId);

}
