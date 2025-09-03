package com.firefly.core.lending.collections.core.services.escalation.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.collections.interfaces.dtos.escalation.v1.CollectionEscalationDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface CollectionEscalationService {

    /**
     * Retrieves a paginated list of collection escalations for a specific collection case,
     * filtered based on the provided filter criteria.
     *
     * @param collectionCaseId the unique identifier of the collection case for which escalations are being retrieved
     * @param filterRequest the filter criteria to apply when retrieving the collection escalations
     * @return a {@link Mono} emitting a {@link PaginationResponse} containing the filtered collection escalations
     */
    Mono<PaginationResponse<CollectionEscalationDTO>> findAll(UUID collectionCaseId,
                                                              FilterRequest<CollectionEscalationDTO> filterRequest);

    /**
     * Creates a new collection escalation for the specified collection case.
     *
     * @param collectionCaseId the unique identifier of the collection case for which the escalation is being created
     * @param dto the {@link CollectionEscalationDTO} object containing the details of the collection escalation to be created
     * @return a {@link Mono} emitting the created {@link CollectionEscalationDTO} object upon successful creation
     */
    Mono<CollectionEscalationDTO> create(UUID collectionCaseId, CollectionEscalationDTO dto);

    /**
     * Retrieves a specific collection escalation for the given collection case and escalation identifiers.
     *
     * @param collectionCaseId the unique identifier of the collection case to which the escalation belongs
     * @param escalationId the unique identifier of the escalation to be retrieved
     * @return a {@link Mono} emitting the retrieved {@link CollectionEscalationDTO} object, or an empty Mono if not found
     */
    Mono<CollectionEscalationDTO> getById(UUID collectionCaseId, UUID escalationId);

    /**
     * Updates an existing collection escalation for the specified collection case and escalation identifiers
     * with the provided details.
     *
     * @param collectionCaseId the unique identifier of the collection case to which the escalation belongs
     * @param escalationId the unique identifier of the escalation to be updated
     * @param dto the {@link CollectionEscalationDTO} object containing the updated details for the escalation
     * @return a {@link Mono} emitting the updated {@link CollectionEscalationDTO} object upon successful update,
     *         or an error signal if the update fails
     */
    Mono<CollectionEscalationDTO> update(UUID collectionCaseId, UUID escalationId, CollectionEscalationDTO dto);

    /**
     * Deletes a specific escalation associated with the given collection case and escalation IDs.
     *
     * @param collectionCaseId the ID of the collection case to which the escalation is associated
     * @param escalationId the ID of the escalation to be deleted
     * @return a {@code Mono<Void>} indicating the completion of the delete operation
     */
    Mono<Void> delete(UUID collectionCaseId, UUID escalationId);
}
