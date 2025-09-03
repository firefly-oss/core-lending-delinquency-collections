package com.firefly.core.lending.collections.interfaces.dtos.escalation.v1;

import com.firefly.core.lending.collections.interfaces.enums.escalation.v1.EscalationLevelEnum;
import com.firefly.core.lending.collections.interfaces.enums.escalation.v1.EscalationReasonEnum;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CollectionEscalationDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID collectionEscalationId;

    @FilterableId
    @NotNull(message = "Collection case ID is required")
    private UUID collectionCaseId;

    @NotNull(message = "Escalation level is required")
    private EscalationLevelEnum escalationLevel;

    @NotNull(message = "Escalation reason is required")
    private EscalationReasonEnum escalationReason;

    @NotNull(message = "Escalation date is required")
    @PastOrPresent(message = "Escalation date cannot be in the future")
    private LocalDateTime escalationDate;

    @Size(max = 2000, message = "Notes cannot exceed 2000 characters")
    private String notes;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}