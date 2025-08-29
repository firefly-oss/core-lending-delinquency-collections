package com.firefly.core.lending.collections.core.mappers.action.v1;

import com.firefly.core.lending.collections.interfaces.dtos.action.v1.CollectionActionDTO;
import com.firefly.core.lending.collections.models.entities.action.v1.CollectionAction;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T16:44:30+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class CollectionActionMapperImpl implements CollectionActionMapper {

    @Override
    public CollectionActionDTO toDTO(CollectionAction entity) {
        if ( entity == null ) {
            return null;
        }

        CollectionActionDTO.CollectionActionDTOBuilder collectionActionDTO = CollectionActionDTO.builder();

        collectionActionDTO.collectionActionId( entity.getCollectionActionId() );
        collectionActionDTO.collectionCaseId( entity.getCollectionCaseId() );
        collectionActionDTO.actionType( entity.getActionType() );
        collectionActionDTO.actionDate( entity.getActionDate() );
        collectionActionDTO.outcome( entity.getOutcome() );
        collectionActionDTO.notes( entity.getNotes() );
        collectionActionDTO.createdAt( entity.getCreatedAt() );
        collectionActionDTO.updatedAt( entity.getUpdatedAt() );

        return collectionActionDTO.build();
    }

    @Override
    public CollectionAction toEntity(CollectionActionDTO dto) {
        if ( dto == null ) {
            return null;
        }

        CollectionAction.CollectionActionBuilder collectionAction = CollectionAction.builder();

        collectionAction.collectionActionId( dto.getCollectionActionId() );
        collectionAction.collectionCaseId( dto.getCollectionCaseId() );
        collectionAction.actionType( dto.getActionType() );
        collectionAction.actionDate( dto.getActionDate() );
        collectionAction.outcome( dto.getOutcome() );
        collectionAction.notes( dto.getNotes() );
        collectionAction.createdAt( dto.getCreatedAt() );
        collectionAction.updatedAt( dto.getUpdatedAt() );

        return collectionAction.build();
    }
}
