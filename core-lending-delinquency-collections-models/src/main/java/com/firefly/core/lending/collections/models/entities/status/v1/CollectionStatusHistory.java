package com.firefly.core.lending.collections.models.entities.status.v1;

import com.firefly.core.lending.collections.interfaces.enums.status.v1.CollectionStatusEnum;
import com.firefly.core.lending.collections.interfaces.enums.status.v1.StatusReasonCodeEnum;
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
@Table("collection_status_history")
public class CollectionStatusHistory {

    @Id
    @Column("collection_status_history_id")
    private UUID collectionStatusHistoryId;

    @Column("collection_case_id")
    private UUID collectionCaseId;

    @Column("old_status")
    private CollectionStatusEnum oldStatus;

    @Column("new_status")
    private CollectionStatusEnum newStatus;

    @Column("reason_code")
    private StatusReasonCodeEnum reasonCode;

    @Column("changed_at")
    private LocalDateTime changedAt;

    @Column("changed_by")
    private String changedBy;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}