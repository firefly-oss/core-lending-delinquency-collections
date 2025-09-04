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


package com.firefly.core.lending.collections.interfaces.dtos.cases.v1;

import com.firefly.core.lending.collections.interfaces.enums.status.v1.CollectionStatusEnum;
import com.firefly.core.lending.collections.interfaces.enums.cases.v1.DelinquencyStageEnum;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CollectionCaseDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID collectionCaseId;

    @FilterableId
    @NotNull(message = "Loan servicing case ID is required")
    private UUID loanServicingCaseId;

    @NotNull(message = "Collection status is required")
    private CollectionStatusEnum collectionStatus;

    @NotNull(message = "Days past due is required")
    @Min(value = 0, message = "Days past due must be non-negative")
    private Integer daysPastDue;

    @NotNull(message = "Delinquency stage is required")
    private DelinquencyStageEnum delinquencyStage;

    @NotNull(message = "Total due amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Total due must be greater than zero")
    @Digits(integer = 15, fraction = 2, message = "Total due must have at most 15 integer digits and 2 decimal places")
    private BigDecimal totalDue;

    @DecimalMin(value = "0.0", message = "Total recovered must be non-negative")
    @Digits(integer = 15, fraction = 2, message = "Total recovered must have at most 15 integer digits and 2 decimal places")
    private BigDecimal totalRecovered;

    @NotNull(message = "Case opened date is required")
    @PastOrPresent(message = "Case opened date cannot be in the future")
    private LocalDateTime caseOpenedAt;

    @PastOrPresent(message = "Case closed date cannot be in the future")
    private LocalDateTime caseClosedAt;

    @Size(max = 1000, message = "Remarks cannot exceed 1000 characters")
    private String remarks;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}