package com.firefly.core.lending.collections.core.services.status.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.collections.core.mappers.status.v1.CollectionStatusHistoryMapper;
import com.firefly.core.lending.collections.interfaces.dtos.status.v1.CollectionStatusHistoryDTO;
import com.firefly.core.lending.collections.models.entities.status.v1.CollectionStatusHistory;
import com.firefly.core.lending.collections.models.repositories.status.v1.CollectionStatusHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Transactional
public class CollectionStatusHistoryServiceImpl implements CollectionStatusHistoryService {

    @Autowired
    private CollectionStatusHistoryRepository repository;

    @Autowired
    private CollectionStatusHistoryMapper mapper;

    @Override
    public Mono<PaginationResponse<CollectionStatusHistoryDTO>> findAll(UUID collectionCaseId, FilterRequest<CollectionStatusHistoryDTO> filterRequest) {
        filterRequest.getFilters().setCollectionCaseId(collectionCaseId);
        return FilterUtils.createFilter(
                CollectionStatusHistory.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<CollectionStatusHistoryDTO> create(UUID collectionCaseId, CollectionStatusHistoryDTO dto) {
        dto.setCollectionCaseId(collectionCaseId);
        return Mono.just(dto)
                .map(mapper::toEntity)
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CollectionStatusHistoryDTO> getById(UUID collectionCaseId, UUID statusHistoryId) {
        return repository.findById(statusHistoryId)
                .filter(history -> history.getCollectionCaseId().equals(collectionCaseId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CollectionStatusHistoryDTO> update(UUID collectionCaseId, UUID statusHistoryId, CollectionStatusHistoryDTO dto) {
        return repository.findById(statusHistoryId)
                .filter(history -> history.getCollectionCaseId().equals(collectionCaseId))
                .flatMap(history -> {
                    dto.setCollectionStatusHistoryId(statusHistoryId);
                    dto.setCollectionCaseId(collectionCaseId);
                    return Mono.just(dto)
                            .map(mapper::toEntity)
                            .flatMap(repository::save)
                            .map(mapper::toDTO);
                });
    }

    @Override
    public Mono<Void> delete(UUID collectionCaseId, UUID statusHistoryId) {
        return repository.findById(statusHistoryId)
                .filter(history -> history.getCollectionCaseId().equals(collectionCaseId))
                .flatMap(repository::delete);
    }
}