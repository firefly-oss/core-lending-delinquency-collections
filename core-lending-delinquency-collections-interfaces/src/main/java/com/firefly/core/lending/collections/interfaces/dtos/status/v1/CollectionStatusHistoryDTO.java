package com.firefly.core.lending.collections.interfaces.dtos.status.v1;

import com.firefly.core.lending.collections.interfaces.enums.status.v1.CollectionStatusEnum;
import com.firefly.core.lending.collections.interfaces.enums.status.v1.StatusReasonCodeEnum;
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
public class CollectionStatusHistoryDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID collectionStatusHistoryId;

    @FilterableId
    @NotNull(message = "Collection case ID is required")
    private UUID collectionCaseId;

    private CollectionStatusEnum oldStatus;

    @NotNull(message = "New status is required")
    private CollectionStatusEnum newStatus;

    @NotNull(message = "Reason code is required")
    private StatusReasonCodeEnum reasonCode;

    @NotNull(message = "Changed date is required")
    @PastOrPresent(message = "Changed date cannot be in the future")
    private LocalDateTime changedAt;

    @NotBlank(message = "Changed by is required")
    @Size(max = 255, message = "Changed by cannot exceed 255 characters")
    private String changedBy;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}