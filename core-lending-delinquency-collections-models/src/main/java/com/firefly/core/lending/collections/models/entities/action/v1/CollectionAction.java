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


package com.firefly.core.lending.collections.models.entities.action.v1;

import com.firefly.core.lending.collections.interfaces.enums.action.v1.ActionTypeEnum;
import com.firefly.core.lending.collections.interfaces.enums.action.v1.OutcomeTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("collection_action")
public class CollectionAction {

    @Id
    @Column("collection_action_id")
    private UUID collectionActionId;

    @Column("collection_case_id")
    private UUID collectionCaseId;

    @Column("action_type")
    private ActionTypeEnum actionType;

    @Column("action_date")
    private LocalDateTime actionDate;

    @Column("outcome")
    private OutcomeTypeEnum outcome;

    @Column("notes")
    private String notes;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}