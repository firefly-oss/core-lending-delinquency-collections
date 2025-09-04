-- V2 - CREATE TABLES FOR DELINQUENCY & COLLECTIONS

-- ========================================================================
-- TABLE: collection_case
-- ========================================================================
CREATE TABLE IF NOT EXISTS collection_case (
                                               collection_case_id       BIGSERIAL PRIMARY KEY,
                                               loan_servicing_case_id   BIGINT NOT NULL, -- external reference to Loan Servicing
                                               collection_status        collection_status NOT NULL,
                                               days_past_due            INT DEFAULT 0,
                                               delinquency_stage        delinquency_stage NOT NULL,
                                               total_due                DECIMAL(18,2) DEFAULT 0,
    total_recovered          DECIMAL(18,2) DEFAULT 0,
    case_opened_at           TIMESTAMP NOT NULL DEFAULT NOW(),
    case_closed_at           TIMESTAMP,
    remarks                  TEXT,
    created_at               TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at               TIMESTAMP NOT NULL DEFAULT NOW()
    );

-- ========================================================================
-- TABLE: collection_status_history
-- ========================================================================
CREATE TABLE IF NOT EXISTS collection_status_history (
                                                         collection_status_history_id  BIGSERIAL PRIMARY KEY,
                                                         collection_case_id            BIGINT NOT NULL,
                                                         old_status                    collection_status,
                                                         new_status                    collection_status,
                                                         reason_code                   status_reason_code,
                                                         changed_at                    TIMESTAMP NOT NULL DEFAULT NOW(),
    changed_by                    VARCHAR(100),
    created_at                    TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at                    TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_status_hist_case
    FOREIGN KEY (collection_case_id)
    REFERENCES collection_case (collection_case_id)
    );

-- ========================================================================
-- TABLE: collection_action
-- ========================================================================
CREATE TABLE IF NOT EXISTS collection_action (
                                                 collection_action_id     BIGSERIAL PRIMARY KEY,
                                                 collection_case_id       BIGINT NOT NULL,
                                                 action_type              action_type NOT NULL,
                                                 action_date              TIMESTAMP NOT NULL DEFAULT NOW(),
    outcome                  outcome_type,
    notes                    TEXT,
    created_at               TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at               TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_action_case
    FOREIGN KEY (collection_case_id)
    REFERENCES collection_case (collection_case_id)
    );

-- ========================================================================
-- TABLE: collection_promise
-- ========================================================================
CREATE TABLE IF NOT EXISTS collection_promise (
                                                  collection_promise_id    BIGSERIAL PRIMARY KEY,
                                                  collection_case_id       BIGINT NOT NULL,
                                                  promised_date            TIMESTAMP,
                                                  promised_amount          DECIMAL(18,2),
    actual_paid_date         TIMESTAMP,
    actual_paid_amount       DECIMAL(18,2),
    is_kept                  BOOLEAN DEFAULT FALSE,
    note                     TEXT,
    created_at               TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at               TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_promise_case
    FOREIGN KEY (collection_case_id)
    REFERENCES collection_case (collection_case_id)
    );

-- ========================================================================
-- TABLE: collection_escalation
-- ========================================================================
CREATE TABLE IF NOT EXISTS collection_escalation (
                                                     collection_escalation_id BIGSERIAL PRIMARY KEY,
                                                     collection_case_id       BIGINT NOT NULL,
                                                     escalation_level         escalation_level NOT NULL,
                                                     escalation_reason        escalation_reason,
                                                     escalation_date          TIMESTAMP NOT NULL DEFAULT NOW(),
    notes                    TEXT,
    created_at               TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at               TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_escalation_case
    FOREIGN KEY (collection_case_id)
    REFERENCES collection_case (collection_case_id)
    );