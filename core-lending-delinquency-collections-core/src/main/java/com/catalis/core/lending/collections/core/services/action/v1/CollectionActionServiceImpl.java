package com.catalis.core.lending.collections.core.services.action.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.collections.core.mappers.action.v1.CollectionActionMapper;
import com.catalis.core.lending.collections.interfaces.dtos.action.v1.CollectionActionDTO;
import com.catalis.core.lending.collections.models.entities.action.v1.CollectionAction;
import com.catalis.core.lending.collections.models.repositories.action.v1.CollectionActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CollectionActionServiceImpl implements CollectionActionService {

    @Autowired
    private CollectionActionRepository repository;

    @Autowired
    private CollectionActionMapper mapper;

    @Override
    public Mono<PaginationResponse<CollectionActionDTO>> findAll(Long collectionCaseId, FilterRequest<CollectionActionDTO> filterRequest) {
        filterRequest.getFilters().setCollectionCaseId(collectionCaseId);
        return FilterUtils.createFilter(
                CollectionAction.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<CollectionActionDTO> create(Long collectionCaseId, CollectionActionDTO dto) {
        dto.setCollectionCaseId(collectionCaseId);
        CollectionAction entity = mapper.toEntity(dto);
        return Mono.just(entity)
                .map(en -> {
                    en.setCreatedAt(java.time.LocalDateTime.now());
                    en.setUpdatedAt(en.getCreatedAt());
                    return en;
                })
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CollectionActionDTO> getById(Long collectionCaseId, Long actionId) {
        return repository.findById(actionId)
                .filter(entity -> entity.getCollectionCaseId().equals(collectionCaseId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CollectionActionDTO> update(Long collectionCaseId, Long actionId, CollectionActionDTO dto) {
        return repository.findById(actionId)
                .filter(entity -> entity.getCollectionCaseId().equals(collectionCaseId))
                .flatMap(entity -> {
                    entity.setActionType(dto.getActionType());
                    entity.setActionDate(dto.getActionDate());
                    entity.setOutcome(dto.getOutcome());
                    entity.setNotes(dto.getNotes());
                    entity.setUpdatedAt(java.time.LocalDateTime.now());
                    return repository.save(entity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(Long collectionCaseId, Long actionId) {
        return repository.findById(actionId)
                .filter(entity -> entity.getCollectionCaseId().equals(collectionCaseId))
                .flatMap(repository::delete);
    }
}