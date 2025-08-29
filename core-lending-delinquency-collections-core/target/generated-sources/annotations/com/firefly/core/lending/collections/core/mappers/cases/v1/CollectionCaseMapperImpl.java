package com.firefly.core.lending.collections.core.mappers.cases.v1;

import com.firefly.core.lending.collections.interfaces.dtos.cases.v1.CollectionCaseDTO;
import com.firefly.core.lending.collections.models.entities.cases.v1.CollectionCase;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T15:39:20+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class CollectionCaseMapperImpl implements CollectionCaseMapper {

    @Override
    public CollectionCaseDTO toDTO(CollectionCase entity) {
        if ( entity == null ) {
            return null;
        }

        CollectionCaseDTO.CollectionCaseDTOBuilder collectionCaseDTO = CollectionCaseDTO.builder();

        collectionCaseDTO.collectionCaseId( entity.getCollectionCaseId() );
        collectionCaseDTO.loanServicingCaseId( entity.getLoanServicingCaseId() );
        collectionCaseDTO.collectionStatus( entity.getCollectionStatus() );
        collectionCaseDTO.daysPastDue( entity.getDaysPastDue() );
        collectionCaseDTO.delinquencyStage( entity.getDelinquencyStage() );
        collectionCaseDTO.totalDue( entity.getTotalDue() );
        collectionCaseDTO.totalRecovered( entity.getTotalRecovered() );
        collectionCaseDTO.caseOpenedAt( entity.getCaseOpenedAt() );
        collectionCaseDTO.caseClosedAt( entity.getCaseClosedAt() );
        collectionCaseDTO.remarks( entity.getRemarks() );
        collectionCaseDTO.createdAt( entity.getCreatedAt() );
        collectionCaseDTO.updatedAt( entity.getUpdatedAt() );

        return collectionCaseDTO.build();
    }

    @Override
    public CollectionCase toEntity(CollectionCaseDTO dto) {
        if ( dto == null ) {
            return null;
        }

        CollectionCase.CollectionCaseBuilder collectionCase = CollectionCase.builder();

        collectionCase.collectionCaseId( dto.getCollectionCaseId() );
        collectionCase.loanServicingCaseId( dto.getLoanServicingCaseId() );
        collectionCase.collectionStatus( dto.getCollectionStatus() );
        collectionCase.daysPastDue( dto.getDaysPastDue() );
        collectionCase.delinquencyStage( dto.getDelinquencyStage() );
        collectionCase.totalDue( dto.getTotalDue() );
        collectionCase.totalRecovered( dto.getTotalRecovered() );
        collectionCase.caseOpenedAt( dto.getCaseOpenedAt() );
        collectionCase.caseClosedAt( dto.getCaseClosedAt() );
        collectionCase.remarks( dto.getRemarks() );
        collectionCase.createdAt( dto.getCreatedAt() );
        collectionCase.updatedAt( dto.getUpdatedAt() );

        return collectionCase.build();
    }
}
