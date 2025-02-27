package com.catalis.core.lending.collections.models.entities.promise.v1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("collection_promise")
public class CollectionPromise {

    @Id
    @Column("collection_promise_id")
    private Long collectionPromiseId;

    @Column("collection_case_id")
    private Long collectionCaseId;

    @Column("promised_date")
    private LocalDateTime promisedDate;

    @Column("promised_amount")
    private BigDecimal promisedAmount;

    @Column("actual_paid_date")
    private LocalDateTime actualPaidDate;

    @Column("actual_paid_amount")
    private BigDecimal actualPaidAmount;

    @Column("is_kept")
    private Boolean isKept;

    @Column("note")
    private String note;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}