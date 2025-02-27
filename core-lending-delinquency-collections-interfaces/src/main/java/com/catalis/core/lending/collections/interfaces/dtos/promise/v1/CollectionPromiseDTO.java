package com.catalis.core.lending.collections.interfaces.dtos.promise.v1;

import com.catalis.common.core.filters.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CollectionPromiseDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long collectionPromiseId;

    @FilterableId
    private Long collectionCaseId;

    private LocalDateTime promisedDate;
    private BigDecimal promisedAmount;
    private LocalDateTime actualPaidDate;
    private BigDecimal actualPaidAmount;
    private Boolean isKept;
    private String note;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

