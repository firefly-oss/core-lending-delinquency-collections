package com.firefly.core.lending.collections.core.mappers.escalation.v1;

import com.firefly.core.lending.collections.interfaces.dtos.escalation.v1.CollectionEscalationDTO;
import com.firefly.core.lending.collections.models.entities.escalation.v1.CollectionEscalation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CollectionEscalationMapper {
    CollectionEscalationDTO toDTO(CollectionEscalation entity);
    CollectionEscalation toEntity(CollectionEscalationDTO dto);
}
