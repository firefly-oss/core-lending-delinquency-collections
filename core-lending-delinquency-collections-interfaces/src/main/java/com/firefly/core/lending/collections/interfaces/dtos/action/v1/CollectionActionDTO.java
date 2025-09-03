package com.firefly.core.lending.collections.interfaces.dtos.action.v1;

import com.firefly.core.lending.collections.interfaces.enums.action.v1.ActionTypeEnum;
import com.firefly.core.lending.collections.interfaces.enums.action.v1.OutcomeTypeEnum;
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
public class CollectionActionDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID collectionActionId;

    @FilterableId
    @NotNull(message = "Collection case ID is required")
    private UUID collectionCaseId;

    @NotNull(message = "Action type is required")
    private ActionTypeEnum actionType;

    @NotNull(message = "Action date is required")
    @PastOrPresent(message = "Action date cannot be in the future")
    private LocalDateTime actionDate;

    @NotNull(message = "Outcome is required")
    private OutcomeTypeEnum outcome;

    @Size(max = 2000, message = "Notes cannot exceed 2000 characters")
    private String notes;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}