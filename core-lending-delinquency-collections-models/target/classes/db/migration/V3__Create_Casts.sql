-- V3 - CASTS USING "WITH INOUT AS IMPLICIT" FOR ALL ENUM TYPES

-------------------------
-- collection_status
-------------------------
CREATE CAST (varchar AS collection_status)
    WITH INOUT
    AS IMPLICIT;

-------------------------
-- delinquency_stage
-------------------------
CREATE CAST (varchar AS delinquency_stage)
    WITH INOUT
    AS IMPLICIT;

-------------------------
-- status_reason_code
-------------------------
CREATE CAST (varchar AS status_reason_code)
    WITH INOUT
    AS IMPLICIT;

-------------------------
-- action_type
-------------------------
CREATE CAST (varchar AS action_type)
    WITH INOUT
    AS IMPLICIT;

-------------------------
-- outcome_type
-------------------------
CREATE CAST (varchar AS outcome_type)
    WITH INOUT
    AS IMPLICIT;

-------------------------
-- escalation_level
-------------------------
CREATE CAST (varchar AS escalation_level)
    WITH INOUT
    AS IMPLICIT;

-------------------------
-- escalation_reason
-------------------------
CREATE CAST (varchar AS escalation_reason)
    WITH INOUT
    AS IMPLICIT;
