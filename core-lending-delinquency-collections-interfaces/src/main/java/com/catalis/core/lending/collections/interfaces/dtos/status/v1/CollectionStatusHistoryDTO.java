package com.catalis.core.lending.collections.interfaces.dtos.status.v1;

import com.catalis.core.lending.collections.interfaces.enums.status.v1.CollectionStatusEnum;
import com.catalis.core.lending.collections.interfaces.enums.status.v1.StatusReasonCodeEnum;
import com.catalis.core.utils.annotations.FilterableId;
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
public class CollectionStatusHistoryDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long collectionStatusHistoryId;

    @FilterableId
    private Long collectionCaseId;

    private CollectionStatusEnum oldStatus;
    private CollectionStatusEnum newStatus;
    private StatusReasonCodeEnum reasonCode;
    private LocalDateTime changedAt;
    private String changedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}