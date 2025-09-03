package com.firefly.core.lending.collections.core.services.cases.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.collections.core.mappers.cases.v1.CollectionCaseMapper;
import com.firefly.core.lending.collections.interfaces.dtos.cases.v1.CollectionCaseDTO;
import com.firefly.core.lending.collections.models.entities.cases.v1.CollectionCase;
import com.firefly.core.lending.collections.models.repositories.cases.v1.CollectionCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Transactional
public class CollectionCaseServiceImpl implements CollectionCaseService {

    @Autowired
    private CollectionCaseRepository repository;

    @Autowired
    private CollectionCaseMapper mapper;

    @Override
    public Mono<PaginationResponse<CollectionCaseDTO>> findAll(FilterRequest<CollectionCaseDTO> filterRequest) {
        return FilterUtils.createFilter(
                CollectionCase.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<CollectionCaseDTO> create(CollectionCaseDTO dto) {
        return Mono.just(dto)
                .map(mapper::toEntity)
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CollectionCaseDTO> getById(UUID collectionCaseId) {
        return repository.findById(collectionCaseId)
                .map(mapper::toDTO)
                .switchIfEmpty(Mono.error(new RuntimeException("Collection case not found")));
    }

    @Override
    public Mono<CollectionCaseDTO> update(UUID collectionCaseId, CollectionCaseDTO dto) {
        return repository.findById(collectionCaseId)
                .switchIfEmpty(Mono.error(new RuntimeException("Collection case not found")))
                .flatMap(existing -> {
                    CollectionCase updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setCollectionCaseId(collectionCaseId);
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(UUID collectionCaseId) {
        return repository.findById(collectionCaseId)
                .switchIfEmpty(Mono.error(new RuntimeException("Collection case not found")))
                .flatMap(repository::delete);
    }
}