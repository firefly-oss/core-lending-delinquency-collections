/*
 * Copyright 2025 Firefly Software Solutions Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.firefly.core.lending.collections.interfaces.dtos.status.v1;

import com.firefly.core.lending.collections.interfaces.enums.status.v1.CollectionStatusEnum;
import com.firefly.core.lending.collections.interfaces.enums.status.v1.StatusReasonCodeEnum;
import org.fireflyframework.utils.annotations.FilterableId;
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