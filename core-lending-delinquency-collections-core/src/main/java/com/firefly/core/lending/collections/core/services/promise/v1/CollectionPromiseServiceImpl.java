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


package com.firefly.core.lending.collections.core.services.promise.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.collections.core.mappers.promise.v1.CollectionPromiseMapper;
import com.firefly.core.lending.collections.interfaces.dtos.promise.v1.CollectionPromiseDTO;
import com.firefly.core.lending.collections.models.entities.promise.v1.CollectionPromise;
import com.firefly.core.lending.collections.models.repositories.promise.v1.CollectionPromiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Transactional
public class CollectionPromiseServiceImpl implements CollectionPromiseService {

    @Autowired
    private CollectionPromiseRepository repository;

    @Autowired
    private CollectionPromiseMapper mapper;

    @Override
    public Mono<PaginationResponse<CollectionPromiseDTO>> findAll(UUID collectionCaseId, FilterRequest<CollectionPromiseDTO> filterRequest) {
        filterRequest.getFilters().setCollectionCaseId(collectionCaseId);
        return FilterUtils.createFilter(
                CollectionPromise.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<CollectionPromiseDTO> create(UUID collectionCaseId, CollectionPromiseDTO dto) {
        dto.setCollectionCaseId(collectionCaseId);
        return repository.save(mapper.toEntity(dto))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CollectionPromiseDTO> getById(UUID collectionCaseId, UUID promiseId) {
        return repository.findById(promiseId)
                .filter(entity -> entity.getCollectionCaseId().equals(collectionCaseId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CollectionPromiseDTO> update(UUID collectionCaseId, UUID promiseId, CollectionPromiseDTO dto) {
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
    public Mono<Void> delete(UUID collectionCaseId, UUID promiseId) {
        return repository.findById(promiseId)
                .filter(entity -> entity.getCollectionCaseId().equals(collectionCaseId))
                .flatMap(repository::delete);
    }
}