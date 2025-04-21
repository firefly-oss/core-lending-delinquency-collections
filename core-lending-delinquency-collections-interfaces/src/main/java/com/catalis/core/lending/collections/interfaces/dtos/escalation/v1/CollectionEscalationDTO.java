package com.catalis.core.lending.collections.interfaces.dtos.escalation.v1;

import com.catalis.core.lending.collections.interfaces.enums.escalation.v1.EscalationLevelEnum;
import com.catalis.core.lending.collections.interfaces.enums.escalation.v1.EscalationReasonEnum;
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
public class CollectionEscalationDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long collectionEscalationId;

    @FilterableId
    private Long collectionCaseId;

    private EscalationLevelEnum escalationLevel;
    private EscalationReasonEnum escalationReason;
    private LocalDateTime escalationDate;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}