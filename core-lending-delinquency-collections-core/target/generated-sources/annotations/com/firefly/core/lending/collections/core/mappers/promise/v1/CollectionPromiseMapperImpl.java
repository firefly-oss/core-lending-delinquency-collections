package com.firefly.core.lending.collections.core.mappers.promise.v1;

import com.firefly.core.lending.collections.interfaces.dtos.promise.v1.CollectionPromiseDTO;
import com.firefly.core.lending.collections.models.entities.promise.v1.CollectionPromise;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T15:39:20+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class CollectionPromiseMapperImpl implements CollectionPromiseMapper {

    @Override
    public CollectionPromiseDTO toDTO(CollectionPromise entity) {
        if ( entity == null ) {
            return null;
        }

        CollectionPromiseDTO.CollectionPromiseDTOBuilder collectionPromiseDTO = CollectionPromiseDTO.builder();

        collectionPromiseDTO.collectionPromiseId( entity.getCollectionPromiseId() );
        collectionPromiseDTO.collectionCaseId( entity.getCollectionCaseId() );
        collectionPromiseDTO.promisedDate( entity.getPromisedDate() );
        collectionPromiseDTO.promisedAmount( entity.getPromisedAmount() );
        collectionPromiseDTO.actualPaidDate( entity.getActualPaidDate() );
        collectionPromiseDTO.actualPaidAmount( entity.getActualPaidAmount() );
        collectionPromiseDTO.isKept( entity.getIsKept() );
        collectionPromiseDTO.note( entity.getNote() );
        collectionPromiseDTO.createdAt( entity.getCreatedAt() );
        collectionPromiseDTO.updatedAt( entity.getUpdatedAt() );

        return collectionPromiseDTO.build();
    }

    @Override
    public CollectionPromise toEntity(CollectionPromiseDTO dto) {
        if ( dto == null ) {
            return null;
        }

        CollectionPromise.CollectionPromiseBuilder collectionPromise = CollectionPromise.builder();

        collectionPromise.collectionPromiseId( dto.getCollectionPromiseId() );
        collectionPromise.collectionCaseId( dto.getCollectionCaseId() );
        collectionPromise.promisedDate( dto.getPromisedDate() );
        collectionPromise.promisedAmount( dto.getPromisedAmount() );
        collectionPromise.actualPaidDate( dto.getActualPaidDate() );
        collectionPromise.actualPaidAmount( dto.getActualPaidAmount() );
        collectionPromise.isKept( dto.getIsKept() );
        collectionPromise.note( dto.getNote() );
        collectionPromise.createdAt( dto.getCreatedAt() );
        collectionPromise.updatedAt( dto.getUpdatedAt() );

        return collectionPromise.build();
    }
}
