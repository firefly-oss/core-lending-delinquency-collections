-- V4 - CONVERT LONG IDS TO UUID FOR DELINQUENCY & COLLECTIONS
-- This migration converts all BIGINT/BIGSERIAL primary and foreign keys to UUID format

-- Enable UUID extension if not already enabled
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- ========================================================================
-- STEP 1: Add new UUID columns alongside existing BIGINT columns
-- ========================================================================

-- Add UUID columns to collection_case table
ALTER TABLE collection_case 
    ADD COLUMN collection_case_uuid UUID DEFAULT uuid_generate_v4(),
    ADD COLUMN loan_servicing_case_uuid UUID DEFAULT uuid_generate_v4();

-- Add UUID columns to collection_status_history table
ALTER TABLE collection_status_history 
    ADD COLUMN collection_status_history_uuid UUID DEFAULT uuid_generate_v4(),
    ADD COLUMN collection_case_uuid UUID;

-- Add UUID columns to collection_action table
ALTER TABLE collection_action 
    ADD COLUMN collection_action_uuid UUID DEFAULT uuid_generate_v4(),
    ADD COLUMN collection_case_uuid UUID;

-- Add UUID columns to collection_promise table
ALTER TABLE collection_promise 
    ADD COLUMN collection_promise_uuid UUID DEFAULT uuid_generate_v4(),
    ADD COLUMN collection_case_uuid UUID;

-- Add UUID columns to collection_escalation table
ALTER TABLE collection_escalation 
    ADD COLUMN collection_escalation_uuid UUID DEFAULT uuid_generate_v4(),
    ADD COLUMN collection_case_uuid UUID;

-- ========================================================================
-- STEP 2: Populate UUID foreign key columns with corresponding values
-- ========================================================================

-- Update collection_status_history foreign key references
UPDATE collection_status_history csh 
SET collection_case_uuid = cc.collection_case_uuid
FROM collection_case cc 
WHERE csh.collection_case_id = cc.collection_case_id;

-- Update collection_action foreign key references
UPDATE collection_action ca 
SET collection_case_uuid = cc.collection_case_uuid
FROM collection_case cc 
WHERE ca.collection_case_id = cc.collection_case_id;

-- Update collection_promise foreign key references
UPDATE collection_promise cp 
SET collection_case_uuid = cc.collection_case_uuid
FROM collection_case cc 
WHERE cp.collection_case_id = cc.collection_case_id;

-- Update collection_escalation foreign key references
UPDATE collection_escalation ce 
SET collection_case_uuid = cc.collection_case_uuid
FROM collection_case cc 
WHERE ce.collection_case_id = cc.collection_case_id;

-- ========================================================================
-- STEP 3: Drop existing foreign key constraints
-- ========================================================================

ALTER TABLE collection_status_history DROP CONSTRAINT IF EXISTS fk_status_hist_case;
ALTER TABLE collection_action DROP CONSTRAINT IF EXISTS fk_action_case;
ALTER TABLE collection_promise DROP CONSTRAINT IF EXISTS fk_promise_case;
ALTER TABLE collection_escalation DROP CONSTRAINT IF EXISTS fk_escalation_case;

-- ========================================================================
-- STEP 4: Drop old BIGINT columns and rename UUID columns
-- ========================================================================

-- Drop old columns and rename UUID columns in collection_case
ALTER TABLE collection_case 
    DROP COLUMN collection_case_id,
    DROP COLUMN loan_servicing_case_id;

ALTER TABLE collection_case
    RENAME COLUMN collection_case_uuid TO collection_case_id;

ALTER TABLE collection_case
    RENAME COLUMN loan_servicing_case_uuid TO loan_servicing_case_id;

-- Drop old columns and rename UUID columns in collection_status_history
ALTER TABLE collection_status_history 
    DROP COLUMN collection_status_history_id,
    DROP COLUMN collection_case_id;

ALTER TABLE collection_status_history
    RENAME COLUMN collection_status_history_uuid TO collection_status_history_id;

ALTER TABLE collection_status_history
    RENAME COLUMN collection_case_uuid TO collection_case_id;

-- Drop old columns and rename UUID columns in collection_action
ALTER TABLE collection_action 
    DROP COLUMN collection_action_id,
    DROP COLUMN collection_case_id;

ALTER TABLE collection_action
    RENAME COLUMN collection_action_uuid TO collection_action_id;

ALTER TABLE collection_action
    RENAME COLUMN collection_case_uuid TO collection_case_id;

-- Drop old columns and rename UUID columns in collection_promise
ALTER TABLE collection_promise 
    DROP COLUMN collection_promise_id,
    DROP COLUMN collection_case_id;

ALTER TABLE collection_promise
    RENAME COLUMN collection_promise_uuid TO collection_promise_id;

ALTER TABLE collection_promise
    RENAME COLUMN collection_case_uuid TO collection_case_id;

-- Drop old columns and rename UUID columns in collection_escalation
ALTER TABLE collection_escalation 
    DROP COLUMN collection_escalation_id,
    DROP COLUMN collection_case_id;

ALTER TABLE collection_escalation
    RENAME COLUMN collection_escalation_uuid TO collection_escalation_id;

ALTER TABLE collection_escalation
    RENAME COLUMN collection_case_uuid TO collection_case_id;

-- ========================================================================
-- STEP 5: Add primary key and foreign key constraints with UUID columns
-- ========================================================================

-- Add primary key constraints
ALTER TABLE collection_case ADD CONSTRAINT pk_collection_case PRIMARY KEY (collection_case_id);
ALTER TABLE collection_status_history ADD CONSTRAINT pk_collection_status_history PRIMARY KEY (collection_status_history_id);
ALTER TABLE collection_action ADD CONSTRAINT pk_collection_action PRIMARY KEY (collection_action_id);
ALTER TABLE collection_promise ADD CONSTRAINT pk_collection_promise PRIMARY KEY (collection_promise_id);
ALTER TABLE collection_escalation ADD CONSTRAINT pk_collection_escalation PRIMARY KEY (collection_escalation_id);

-- Add foreign key constraints
ALTER TABLE collection_status_history 
    ADD CONSTRAINT fk_status_hist_case 
    FOREIGN KEY (collection_case_id) REFERENCES collection_case (collection_case_id);

ALTER TABLE collection_action 
    ADD CONSTRAINT fk_action_case 
    FOREIGN KEY (collection_case_id) REFERENCES collection_case (collection_case_id);

ALTER TABLE collection_promise 
    ADD CONSTRAINT fk_promise_case 
    FOREIGN KEY (collection_case_id) REFERENCES collection_case (collection_case_id);

ALTER TABLE collection_escalation 
    ADD CONSTRAINT fk_escalation_case 
    FOREIGN KEY (collection_case_id) REFERENCES collection_case (collection_case_id);

-- ========================================================================
-- STEP 6: Add NOT NULL constraints where appropriate
-- ========================================================================

ALTER TABLE collection_case 
    ALTER COLUMN collection_case_id SET NOT NULL,
    ALTER COLUMN loan_servicing_case_id SET NOT NULL;

ALTER TABLE collection_status_history 
    ALTER COLUMN collection_status_history_id SET NOT NULL,
    ALTER COLUMN collection_case_id SET NOT NULL;

ALTER TABLE collection_action 
    ALTER COLUMN collection_action_id SET NOT NULL,
    ALTER COLUMN collection_case_id SET NOT NULL;

ALTER TABLE collection_promise 
    ALTER COLUMN collection_promise_id SET NOT NULL,
    ALTER COLUMN collection_case_id SET NOT NULL;

ALTER TABLE collection_escalation 
    ALTER COLUMN collection_escalation_id SET NOT NULL,
    ALTER COLUMN collection_case_id SET NOT NULL;