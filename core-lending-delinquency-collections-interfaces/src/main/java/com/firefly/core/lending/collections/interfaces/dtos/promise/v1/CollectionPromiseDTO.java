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


package com.firefly.core.lending.collections.interfaces.dtos.promise.v1;

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
public class CollectionPromiseDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID collectionPromiseId;

    @FilterableId
    @NotNull(message = "Collection case ID is required")
    private UUID collectionCaseId;

    @NotNull(message = "Promised date is required")
    @Future(message = "Promised date must be in the future")
    private LocalDateTime promisedDate;

    @NotNull(message = "Promised amount is required")
    @DecimalMin(value = "0.01", message = "Promised amount must be greater than zero")
    @Digits(integer = 15, fraction = 2, message = "Promised amount must have at most 15 integer digits and 2 decimal places")
    private BigDecimal promisedAmount;

    @PastOrPresent(message = "Actual paid date cannot be in the future")
    private LocalDateTime actualPaidDate;

    @DecimalMin(value = "0.0", message = "Actual paid amount must be non-negative")
    @Digits(integer = 15, fraction = 2, message = "Actual paid amount must have at most 15 integer digits and 2 decimal places")
    private BigDecimal actualPaidAmount;

    private Boolean isKept;

    @Size(max = 1000, message = "Note cannot exceed 1000 characters")
    private String note;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

