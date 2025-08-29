package com.firefly.core.lending.collections.models.entities.escalation.v1;

import com.firefly.core.lending.collections.interfaces.enums.escalation.v1.EscalationLevelEnum;
import com.firefly.core.lending.collections.interfaces.enums.escalation.v1.EscalationReasonEnum;
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
@Table("collection_escalation")
public class CollectionEscalation {

    @Id
    @Column("collection_escalation_id")
    private Long collectionEscalationId;

    @Column("collection_case_id")
    private Long collectionCaseId;

    @Column("escalation_level")
    private EscalationLevelEnum escalationLevel;

    @Column("escalation_reason")
    private EscalationReasonEnum escalationReason;

    @Column("escalation_date")
    private LocalDateTime escalationDate;

    @Column("notes")
    private String notes;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}