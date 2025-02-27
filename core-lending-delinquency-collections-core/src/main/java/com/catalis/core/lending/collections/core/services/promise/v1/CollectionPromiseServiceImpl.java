package com.catalis.core.lending.collections.core.services.promise.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.collections.core.mappers.promise.v1.CollectionPromiseMapper;
import com.catalis.core.lending.collections.interfaces.dtos.promise.v1.CollectionPromiseDTO;
import com.catalis.core.lending.collections.models.entities.promise.v1.CollectionPromise;
import com.catalis.core.lending.collections.models.repositories.promise.v1.CollectionPromiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CollectionPromiseServiceImpl implements CollectionPromiseService {

    @Autowired
    private CollectionPromiseRepository repository;

    @Autowired
    private CollectionPromiseMapper mapper;

    @Override
    public Mono<PaginationResponse<CollectionPromiseDTO>> findAll(Long collectionCaseId, FilterRequest<CollectionPromiseDTO> filterRequest) {
        filterRequest.getFilters().setCollectionCaseId(collectionCaseId);
        return FilterUtils.createFilter(
                CollectionPromise.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<CollectionPromiseDTO> create(Long collectionCaseId, CollectionPromiseDTO dto) {
        dto.setCollectionCaseId(collectionCaseId);
        return repository.save(mapper.toEntity(dto))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CollectionPromiseDTO> getById(Long collectionCaseId, Long promiseId) {
        return repository.findById(promiseId)
                .filter(entity -> entity.getCollectionCaseId().equals(collectionCaseId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CollectionPromiseDTO> update(Long collectionCaseId, Long promiseId, CollectionPromiseDTO dto) {
        return repository.findById(promiseId)
                .filter(entity -> entity.getCollectionCaseId().equals(collectionCaseId))
                .flatMap(existing -> {
                    dto.setCollectionPromiseId(existing.getCollectionPromiseId());
                    dto.setCollectionCaseId(existing.getCollectionCaseId());
                    return repository.save(mapper.toEntity(dto));
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(Long collectionCaseId, Long promiseId) {
        return repository.findById(promiseId)
                .filter(entity -> entity.getCollectionCaseId().equals(collectionCaseId))
                .flatMap(repository::delete);
    }
}