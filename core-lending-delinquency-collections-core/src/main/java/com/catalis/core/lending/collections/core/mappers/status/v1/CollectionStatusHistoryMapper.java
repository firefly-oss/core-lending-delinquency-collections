package com.catalis.core.lending.collections.core.mappers.status.v1;

import com.catalis.core.lending.collections.interfaces.dtos.status.v1.CollectionStatusHistoryDTO;
import com.catalis.core.lending.collections.models.entities.status.v1.CollectionStatusHistory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CollectionStatusHistoryMapper {
    CollectionStatusHistoryDTO toDTO(CollectionStatusHistory entity);
    CollectionStatusHistory toEntity(CollectionStatusHistoryDTO dto);
}
