package com.firefly.core.lending.collections.core.mappers.status.v1;

import com.firefly.core.lending.collections.interfaces.dtos.status.v1.CollectionStatusHistoryDTO;
import com.firefly.core.lending.collections.models.entities.status.v1.CollectionStatusHistory;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T20:21:16+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class CollectionStatusHistoryMapperImpl implements CollectionStatusHistoryMapper {

    @Override
    public CollectionStatusHistoryDTO toDTO(CollectionStatusHistory entity) {
        if ( entity == null ) {
            return null;
        }

        CollectionStatusHistoryDTO.CollectionStatusHistoryDTOBuilder collectionStatusHistoryDTO = CollectionStatusHistoryDTO.builder();

        collectionStatusHistoryDTO.collectionStatusHistoryId( entity.getCollectionStatusHistoryId() );
        collectionStatusHistoryDTO.collectionCaseId( entity.getCollectionCaseId() );
        collectionStatusHistoryDTO.oldStatus( entity.getOldStatus() );
        collectionStatusHistoryDTO.newStatus( entity.getNewStatus() );
        collectionStatusHistoryDTO.reasonCode( entity.getReasonCode() );
        collectionStatusHistoryDTO.changedAt( entity.getChangedAt() );
        collectionStatusHistoryDTO.changedBy( entity.getChangedBy() );
        collectionStatusHistoryDTO.createdAt( entity.getCreatedAt() );
        collectionStatusHistoryDTO.updatedAt( entity.getUpdatedAt() );

        return collectionStatusHistoryDTO.build();
    }

    @Override
    public CollectionStatusHistory toEntity(CollectionStatusHistoryDTO dto) {
        if ( dto == null ) {
            return null;
        }

        CollectionStatusHistory.CollectionStatusHistoryBuilder collectionStatusHistory = CollectionStatusHistory.builder();

        collectionStatusHistory.collectionStatusHistoryId( dto.getCollectionStatusHistoryId() );
        collectionStatusHistory.collectionCaseId( dto.getCollectionCaseId() );
        collectionStatusHistory.oldStatus( dto.getOldStatus() );
        collectionStatusHistory.newStatus( dto.getNewStatus() );
        collectionStatusHistory.reasonCode( dto.getReasonCode() );
        collectionStatusHistory.changedAt( dto.getChangedAt() );
        collectionStatusHistory.changedBy( dto.getChangedBy() );
        collectionStatusHistory.createdAt( dto.getCreatedAt() );
        collectionStatusHistory.updatedAt( dto.getUpdatedAt() );

        return collectionStatusHistory.build();
    }
}
