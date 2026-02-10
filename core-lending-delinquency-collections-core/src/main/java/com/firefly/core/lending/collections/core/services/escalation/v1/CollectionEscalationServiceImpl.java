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


package com.firefly.core.lending.collections.core.services.escalation.v1;

import org.fireflyframework.core.filters.FilterRequest;
import org.fireflyframework.core.filters.FilterUtils;
import org.fireflyframework.core.queries.PaginationResponse;
import com.firefly.core.lending.collections.core.mappers.escalation.v1.CollectionEscalationMapper;
import com.firefly.core.lending.collections.interfaces.dtos.escalation.v1.CollectionEscalationDTO;
import com.firefly.core.lending.collections.models.entities.escalation.v1.CollectionEscalation;
import com.firefly.core.lending.collections.models.repositories.escalation.v1.CollectionEscalationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Transactional
public class CollectionEscalationServiceImpl implements CollectionEscalationService {

    @Autowired
    private CollectionEscalationRepository repository;

    @Autowired
    private CollectionEscalationMapper mapper;

    @Override
    public Mono<PaginationResponse<CollectionEscalationDTO>> findAll(UUID collectionCaseId, FilterRequest<CollectionEscalationDTO> filterRequest) {
        filterRequest.getFilters().setCollectionCaseId(collectionCaseId);
        return FilterUtils.createFilter(
                CollectionEscalation.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<CollectionEscalationDTO> create(UUID collectionCaseId, CollectionEscalationDTO dto) {
        dto.setCollectionCaseId(collectionCaseId);
        CollectionEscalation entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CollectionEscalationDTO> getById(UUID collectionCaseId, UUID escalationId) {
        return repository.findById(escalationId)
                .filter(entity -> entity.getCollectionCaseId().equals(collectionCaseId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CollectionEscalationDTO> update(UUID collectionCaseId, UUID escalationId, CollectionEscalationDTO dto) {
        return repository.findById(escalationId)
                .filter(entity -> entity.getCollectionCaseId().equals(collectionCaseId))
                .flatMap(existingEntity -> {
                    dto.setCollectionCaseId(collectionCaseId);
                    dto.setCollectionEscalationId(escalationId);
                    CollectionEscalation updatedEntity = mapper.toEntity(dto);
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(UUID collectionCaseId, UUID escalationId) {
        return repository.findById(escalationId)
                .filter(entity -> entity.getCollectionCaseId().equals(collectionCaseId))
                .flatMap(repository::delete);
    }
}