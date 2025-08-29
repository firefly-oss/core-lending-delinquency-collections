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

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("collection_action")
public class CollectionAction {

    @Id
    @Column("collection_action_id")
    private Long collectionActionId;

    @Column("collection_case_id")
    private Long collectionCaseId;

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