package com.catalis.core.lending.collections.core.services.status.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.collections.interfaces.dtos.status.v1.CollectionStatusHistoryDTO;
import reactor.core.publisher.Mono;

public interface CollectionStatusHistoryService {

    /**
     * Retrieves a paginated list of collection status history records for a specific collection case,
     * filtered based on the provided filter criteria.
     *
     * @param collectionCaseId the identifier of the collection case for which the status history is being retrieved
     * @param filterRequest the filter criteria to apply when retrieving the status history records
     * @return a Mono emitting a {@code PaginationResponse} containing the filtered collection status history records
     */
    Mono<PaginationResponse<CollectionStatusHistoryDTO>> findAll(Long collectionCaseId,
                                                                 FilterRequest<CollectionStatusHistoryDTO> filterRequest);

    /**
     * Creates a new status history entry for a specific collection case.
     *
     * @param collectionCaseId the unique identifier of the collection case for which the status history is being created
     * @param dto the {@link CollectionStatusHistoryDTO} object containing the details of the status history to be created
     * @return a {@link Mono} emitting the created {@link CollectionStatusHistoryDTO} object upon successful creation
     */
    Mono<CollectionStatusHistoryDTO> create(Long collectionCaseId, CollectionStatusHistoryDTO dto);

    /**
     * Retrieves a specific collection status history record by its unique identifiers.
     *
     * @param collectionCaseId the ID of the collection case to which the status history belongs
     * @param statusHistoryId the unique ID of the specific status history record to be retrieved
     * @return a Mono emitting the retrieved CollectionStatusHistoryDTO instance, or an empty Mono if not found
     */
    Mono<CollectionStatusHistoryDTO> getById(Long collectionCaseId, Long statusHistoryId);

    /**
     * Updates an existing collection status history entry for the specified collection case and status history IDs
     * using the provided data.
     *
     * @param collectionCaseId the unique identifier of the collection case associated with the status history
     * @param statusHistoryId the unique identifier of the status history entry to be updated
     *
     * @param dto the {@link CollectionStatusHistoryDTO} object containing the updated details for the status history
     * @return a {@link Mono} emitting the updated {@link CollectionStatusHistoryDTO} object upon successful update,
     *         or an error signal if the update fails
     */
    Mono<CollectionStatusHistoryDTO> update(Long collectionCaseId, Long statusHistoryId, CollectionStatusHistoryDTO dto);

    /**
     * Deletes a specific status history record associated with a given collection case.
     *
     * @param collectionCaseId the ID of the collection case to which the status history record belongs
     * @param statusHistoryId the ID of the status history record to be deleted
     * @return a {@code Mono<Void>} indicating the completion of the delete operation
     */
    Mono<Void> delete(Long collectionCaseId, Long statusHistoryId);
}
