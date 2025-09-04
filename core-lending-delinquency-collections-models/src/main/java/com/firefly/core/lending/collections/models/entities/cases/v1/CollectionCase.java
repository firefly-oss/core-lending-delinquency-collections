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


package com.firefly.core.lending.collections.models.entities.cases.v1;

import com.firefly.core.lending.collections.interfaces.enums.status.v1.CollectionStatusEnum;
import com.firefly.core.lending.collections.interfaces.enums.cases.v1.DelinquencyStageEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("collection_case")
public class CollectionCase {

    @Id
    @Column("collection_case_id")
    private UUID collectionCaseId;

    @Column("loan_servicing_case_id")
    private UUID loanServicingCaseId;

    @Column("collection_status")
    private CollectionStatusEnum collectionStatus;

    @Column("days_past_due")
    private Integer daysPastDue;

    @Column("delinquency_stage")
    private DelinquencyStageEnum delinquencyStage;

    @Column("total_due")
    private BigDecimal totalDue;

    @Column("total_recovered")
    private BigDecimal totalRecovered;

    @Column("case_opened_at")
    private LocalDateTime caseOpenedAt;

    @Column("case_closed_at")
    private LocalDateTime caseClosedAt;

    @Column("remarks")
    private String remarks;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}