-- V1 - CREATE ENUMS FOR DELINQUENCY & COLLECTIONS

-- 1) collection_status (used in collection_case, collection_status_history)
CREATE TYPE collection_status AS ENUM (
    'OPEN',
    'CLOSED',
    'LEGAL',
    'RESTRUCTURED',
    'SETTLED'
);

-- 2) delinquency_stage (used in collection_case)
CREATE TYPE delinquency_stage AS ENUM (
    'EARLY',
    'MID',
    'LATE',
    'LEGAL',
    'WRITE_OFF'
);

-- 3) status_reason_code (used in collection_status_history)
CREATE TYPE status_reason_code AS ENUM (
    'PROMISE_BROKEN',
    'LEGAL_REFERRAL',
    'PAYMENT_RECEIVED',
    'NO_CONTACT'
);

-- 4) action_type (used in collection_action)
CREATE TYPE action_type AS ENUM (
    'PHONE_CALL',
    'SMS',
    'EMAIL',
    'VISIT',
    'LEGAL_NOTICE',
    'DEMAND_LETTER'
);

-- 5) outcome_type (used in collection_action)
CREATE TYPE outcome_type AS ENUM (
    'NO_ANSWER',
    'PROMISE_TO_PAY',
    'PAID_PARTIAL',
    'REFUSED',
    'DISPUTED'
);

-- 6) escalation_level (used in collection_escalation)
CREATE TYPE escalation_level AS ENUM (
    'LEVEL_1',
    'LEVEL_2',
    'LEVEL_3',
    'LEGAL',
    'EXTERNAL'
);

-- 7) escalation_reason (used in collection_escalation)
CREATE TYPE escalation_reason AS ENUM (
    'BROKEN_PROMISE',
    'NO_CONTACT',
    'HIGH_AMOUNT',
    'FRAUD_SUSPECT'
);