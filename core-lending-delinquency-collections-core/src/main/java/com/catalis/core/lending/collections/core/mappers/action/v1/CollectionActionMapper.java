package com.catalis.core.lending.collections.core.mappers.action.v1;

import com.catalis.core.lending.collections.interfaces.dtos.action.v1.CollectionActionDTO;
import com.catalis.core.lending.collections.models.entities.action.v1.CollectionAction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CollectionActionMapper {
    CollectionActionDTO toDTO(CollectionAction entity);
    CollectionAction toEntity(CollectionActionDTO dto);
}
