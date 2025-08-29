package com.firefly.core.lending.collections.core.mappers.escalation.v1;

import com.firefly.core.lending.collections.interfaces.dtos.escalation.v1.CollectionEscalationDTO;
import com.firefly.core.lending.collections.models.entities.escalation.v1.CollectionEscalation;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T16:44:30+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class CollectionEscalationMapperImpl implements CollectionEscalationMapper {

    @Override
    public CollectionEscalationDTO toDTO(CollectionEscalation entity) {
        if ( entity == null ) {
            return null;
        }

        CollectionEscalationDTO.CollectionEscalationDTOBuilder collectionEscalationDTO = CollectionEscalationDTO.builder();

        collectionEscalationDTO.collectionEscalationId( entity.getCollectionEscalationId() );
        collectionEscalationDTO.collectionCaseId( entity.getCollectionCaseId() );
        collectionEscalationDTO.escalationLevel( entity.getEscalationLevel() );
        collectionEscalationDTO.escalationReason( entity.getEscalationReason() );
        collectionEscalationDTO.escalationDate( entity.getEscalationDate() );
        collectionEscalationDTO.notes( entity.getNotes() );
        collectionEscalationDTO.createdAt( entity.getCreatedAt() );
        collectionEscalationDTO.updatedAt( entity.getUpdatedAt() );

        return collectionEscalationDTO.build();
    }

    @Override
    public CollectionEscalation toEntity(CollectionEscalationDTO dto) {
        if ( dto == null ) {
            return null;
        }

        CollectionEscalation.CollectionEscalationBuilder collectionEscalation = CollectionEscalation.builder();

        collectionEscalation.collectionEscalationId( dto.getCollectionEscalationId() );
        collectionEscalation.collectionCaseId( dto.getCollectionCaseId() );
        collectionEscalation.escalationLevel( dto.getEscalationLevel() );
        collectionEscalation.escalationReason( dto.getEscalationReason() );
        collectionEscalation.escalationDate( dto.getEscalationDate() );
        collectionEscalation.notes( dto.getNotes() );
        collectionEscalation.createdAt( dto.getCreatedAt() );
        collectionEscalation.updatedAt( dto.getUpdatedAt() );

        return collectionEscalation.build();
    }
}
