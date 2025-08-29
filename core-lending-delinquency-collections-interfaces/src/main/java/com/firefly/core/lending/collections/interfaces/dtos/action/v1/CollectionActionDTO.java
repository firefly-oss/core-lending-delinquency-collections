package com.firefly.core.lending.collections.interfaces.dtos.action.v1;

import com.firefly.core.lending.collections.interfaces.enums.action.v1.ActionTypeEnum;
import com.firefly.core.lending.collections.interfaces.enums.action.v1.OutcomeTypeEnum;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CollectionActionDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long collectionActionId;

    @FilterableId
    private Long collectionCaseId;

    private ActionTypeEnum actionType;
    private LocalDateTime actionDate;
    private OutcomeTypeEnum outcome;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}