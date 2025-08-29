package com.firefly.core.lending.collections.core.mappers.cases.v1;

import com.firefly.core.lending.collections.interfaces.dtos.cases.v1.CollectionCaseDTO;
import com.firefly.core.lending.collections.models.entities.cases.v1.CollectionCase;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CollectionCaseMapper {
    CollectionCaseDTO toDTO(CollectionCase entity);
    CollectionCase toEntity(CollectionCaseDTO dto);
}
