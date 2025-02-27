package com.catalis.core.lending.collections.core.mappers.promise.v1;

import com.catalis.core.lending.collections.interfaces.dtos.promise.v1.CollectionPromiseDTO;
import com.catalis.core.lending.collections.models.entities.promise.v1.CollectionPromise;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CollectionPromiseMapper {
    CollectionPromiseDTO toDTO(CollectionPromise entity);
    CollectionPromise toEntity(CollectionPromiseDTO dto);
}
