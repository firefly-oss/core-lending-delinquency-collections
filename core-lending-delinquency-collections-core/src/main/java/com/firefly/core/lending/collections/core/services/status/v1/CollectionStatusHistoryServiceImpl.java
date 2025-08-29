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

@Service
@Transactional
public class CollectionStatusHistoryServiceImpl implements CollectionStatusHistoryService {

    @Autowired
    private CollectionStatusHistoryRepository repository;

    @Autowired
    private CollectionStatusHistoryMapper mapper;

    @Override
    public Mono<PaginationResponse<CollectionStatusHistoryDTO>> findAll(Long collectionCaseId, FilterRequest<CollectionStatusHistoryDTO> filterRequest) {
        filterRequest.getFilters().setCollectionCaseId(collectionCaseId);
        return FilterUtils.createFilter(
                CollectionStatusHistory.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<CollectionStatusHistoryDTO> create(Long collectionCaseId, CollectionStatusHistoryDTO dto) {
        dto.setCollectionCaseId(collectionCaseId);
        return Mono.just(dto)
                .map(mapper::toEntity)
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CollectionStatusHistoryDTO> getById(Long collectionCaseId, Long statusHistoryId) {
        return repository.findById(statusHistoryId)
                .filter(history -> history.getCollectionCaseId().equals(collectionCaseId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CollectionStatusHistoryDTO> update(Long collectionCaseId, Long statusHistoryId, CollectionStatusHistoryDTO dto) {
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
    public Mono<Void> delete(Long collectionCaseId, Long statusHistoryId) {
        return repository.findById(statusHistoryId)
                .filter(history -> history.getCollectionCaseId().equals(collectionCaseId))
                .flatMap(repository::delete);
    }
}