package com.catalis.core.lending.collections.interfaces.dtos.cases.v1;

import com.catalis.core.lending.collections.interfaces.enums.status.v1.CollectionStatusEnum;
import com.catalis.core.lending.collections.interfaces.enums.cases.v1.DelinquencyStageEnum;
import com.catalis.core.utils.annotations.FilterableId;
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
public class CollectionCaseDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long collectionCaseId;

    @FilterableId
    private Long loanServicingCaseId;

    private CollectionStatusEnum collectionStatus;
    private Integer daysPastDue;
    private DelinquencyStageEnum delinquencyStage;
    private BigDecimal totalDue;
    private BigDecimal totalRecovered;
    private LocalDateTime caseOpenedAt;
    private LocalDateTime caseClosedAt;
    private String remarks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}