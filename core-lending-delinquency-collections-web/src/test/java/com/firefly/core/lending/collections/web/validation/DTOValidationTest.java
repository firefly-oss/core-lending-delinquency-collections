package com.firefly.core.lending.collections.web.validation;

import com.firefly.core.lending.collections.interfaces.dtos.cases.v1.CollectionCaseDTO;
import com.firefly.core.lending.collections.interfaces.dtos.action.v1.CollectionActionDTO;
import com.firefly.core.lending.collections.interfaces.dtos.promise.v1.CollectionPromiseDTO;
import com.firefly.core.lending.collections.interfaces.dtos.escalation.v1.CollectionEscalationDTO;
import com.firefly.core.lending.collections.interfaces.dtos.status.v1.CollectionStatusHistoryDTO;
import com.firefly.core.lending.collections.interfaces.enums.status.v1.CollectionStatusEnum;
import com.firefly.core.lending.collections.interfaces.enums.cases.v1.DelinquencyStageEnum;
import com.firefly.core.lending.collections.interfaces.enums.action.v1.ActionTypeEnum;
import com.firefly.core.lending.collections.interfaces.enums.action.v1.OutcomeTypeEnum;
import com.firefly.core.lending.collections.interfaces.enums.escalation.v1.EscalationLevelEnum;
import com.firefly.core.lending.collections.interfaces.enums.escalation.v1.EscalationReasonEnum;
import com.firefly.core.lending.collections.interfaces.enums.status.v1.StatusReasonCodeEnum;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class DTOValidationTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testCollectionCaseDTOValidation() {
        // Test valid DTO
        CollectionCaseDTO validDto = CollectionCaseDTO.builder()
                .loanServicingCaseId(UUID.randomUUID())
                .collectionStatus(CollectionStatusEnum.OPEN)
                .daysPastDue(30)
                .delinquencyStage(DelinquencyStageEnum.EARLY)
                .totalDue(new BigDecimal("1000.00"))
                .totalRecovered(new BigDecimal("0.00"))
                .caseOpenedAt(LocalDateTime.now().minusDays(1))
                .remarks("Test remarks")
                .build();

        Set<ConstraintViolation<CollectionCaseDTO>> violations = validator.validate(validDto);
        assertTrue(violations.isEmpty(), "Valid DTO should have no violations");

        // Test invalid DTO - null required fields
        CollectionCaseDTO invalidDto = CollectionCaseDTO.builder().build();
        violations = validator.validate(invalidDto);
        assertFalse(violations.isEmpty(), "Invalid DTO should have violations");
        
        // Check specific violations
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Loan servicing case ID is required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Collection status is required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Days past due is required")));
    }

    @Test
    void testCollectionActionDTOValidation() {
        // Test valid DTO
        CollectionActionDTO validDto = CollectionActionDTO.builder()
                .collectionCaseId(UUID.randomUUID())
                .actionType(ActionTypeEnum.PHONE_CALL)
                .actionDate(LocalDateTime.now().minusHours(1))
                .outcome(OutcomeTypeEnum.PROMISE_TO_PAY)
                .notes("Test notes")
                .build();

        Set<ConstraintViolation<CollectionActionDTO>> violations = validator.validate(validDto);
        assertTrue(violations.isEmpty(), "Valid DTO should have no violations");

        // Test invalid DTO - future action date
        CollectionActionDTO invalidDto = CollectionActionDTO.builder()
                .collectionCaseId(UUID.randomUUID())
                .actionType(ActionTypeEnum.PHONE_CALL)
                .actionDate(LocalDateTime.now().plusDays(1)) // Future date
                .outcome(OutcomeTypeEnum.PROMISE_TO_PAY)
                .build();

        violations = validator.validate(invalidDto);
        assertFalse(violations.isEmpty(), "Invalid DTO should have violations");
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Action date cannot be in the future")));
    }

    @Test
    void testCollectionPromiseDTOValidation() {
        // Test valid DTO
        CollectionPromiseDTO validDto = CollectionPromiseDTO.builder()
                .collectionCaseId(UUID.randomUUID())
                .promisedDate(LocalDateTime.now().plusDays(7))
                .promisedAmount(new BigDecimal("500.00"))
                .actualPaidAmount(new BigDecimal("0.00"))
                .note("Test note")
                .build();

        Set<ConstraintViolation<CollectionPromiseDTO>> violations = validator.validate(validDto);
        assertTrue(violations.isEmpty(), "Valid DTO should have no violations");

        // Test invalid DTO - past promised date
        CollectionPromiseDTO invalidDto = CollectionPromiseDTO.builder()
                .collectionCaseId(UUID.randomUUID())
                .promisedDate(LocalDateTime.now().minusDays(1)) // Past date
                .promisedAmount(new BigDecimal("500.00"))
                .build();

        violations = validator.validate(invalidDto);
        assertFalse(violations.isEmpty(), "Invalid DTO should have violations");
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Promised date must be in the future")));
    }

    @Test
    void testCollectionEscalationDTOValidation() {
        // Test valid DTO
        CollectionEscalationDTO validDto = CollectionEscalationDTO.builder()
                .collectionCaseId(UUID.randomUUID())
                .escalationLevel(EscalationLevelEnum.LEVEL_1)
                .escalationReason(EscalationReasonEnum.BROKEN_PROMISE)
                .escalationDate(LocalDateTime.now().minusHours(1))
                .notes("Test escalation notes")
                .build();

        Set<ConstraintViolation<CollectionEscalationDTO>> violations = validator.validate(validDto);
        assertTrue(violations.isEmpty(), "Valid DTO should have no violations");

        // Test invalid DTO - null required fields
        CollectionEscalationDTO invalidDto = CollectionEscalationDTO.builder().build();
        violations = validator.validate(invalidDto);
        assertFalse(violations.isEmpty(), "Invalid DTO should have violations");
        
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Collection case ID is required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Escalation level is required")));
    }

    @Test
    void testCollectionStatusHistoryDTOValidation() {
        // Test valid DTO
        CollectionStatusHistoryDTO validDto = CollectionStatusHistoryDTO.builder()
                .collectionCaseId(UUID.randomUUID())
                .newStatus(CollectionStatusEnum.OPEN)
                .reasonCode(StatusReasonCodeEnum.PAYMENT_RECEIVED)
                .changedAt(LocalDateTime.now().minusHours(1))
                .changedBy("test-user")
                .build();

        Set<ConstraintViolation<CollectionStatusHistoryDTO>> violations = validator.validate(validDto);
        assertTrue(violations.isEmpty(), "Valid DTO should have no violations");

        // Test invalid DTO - blank changedBy
        CollectionStatusHistoryDTO invalidDto = CollectionStatusHistoryDTO.builder()
                .collectionCaseId(UUID.randomUUID())
                .newStatus(CollectionStatusEnum.OPEN)
                .reasonCode(StatusReasonCodeEnum.PAYMENT_RECEIVED)
                .changedAt(LocalDateTime.now().minusHours(1))
                .changedBy("") // Blank value
                .build();

        violations = validator.validate(invalidDto);
        assertFalse(violations.isEmpty(), "Invalid DTO should have violations");
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Changed by is required")));
    }

    @Test
    void testBigDecimalValidation() {
        // Test negative total due
        CollectionCaseDTO invalidDto = CollectionCaseDTO.builder()
                .loanServicingCaseId(UUID.randomUUID())
                .collectionStatus(CollectionStatusEnum.OPEN)
                .daysPastDue(30)
                .delinquencyStage(DelinquencyStageEnum.EARLY)
                .totalDue(new BigDecimal("-100.00")) // Negative value
                .caseOpenedAt(LocalDateTime.now().minusDays(1))
                .build();

        Set<ConstraintViolation<CollectionCaseDTO>> violations = validator.validate(invalidDto);
        assertFalse(violations.isEmpty(), "Invalid DTO should have violations");
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Total due must be greater than zero")));
    }

    @Test
    void testStringLengthValidation() {
        // Test remarks exceeding max length
        String longRemarks = "a".repeat(1001); // Exceeds 1000 character limit
        
        CollectionCaseDTO invalidDto = CollectionCaseDTO.builder()
                .loanServicingCaseId(UUID.randomUUID())
                .collectionStatus(CollectionStatusEnum.OPEN)
                .daysPastDue(30)
                .delinquencyStage(DelinquencyStageEnum.EARLY)
                .totalDue(new BigDecimal("1000.00"))
                .caseOpenedAt(LocalDateTime.now().minusDays(1))
                .remarks(longRemarks)
                .build();

        Set<ConstraintViolation<CollectionCaseDTO>> violations = validator.validate(invalidDto);
        assertFalse(violations.isEmpty(), "Invalid DTO should have violations");
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Remarks cannot exceed 1000 characters")));
    }
}
