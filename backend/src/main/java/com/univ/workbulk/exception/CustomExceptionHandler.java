package com.univ.workbulk.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.MessageFormat;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    private ResponseEntity<Object> handleEntityNotFound(final EntityNotFoundException e) {
        return ResponseEntity.status(404).body(
                MessageFormat.format("Entity not found: {0}", e.getMessage())
        );
    }

    @ExceptionHandler(AccessException.class)
    private ResponseEntity<Object> handleAccessRightsException(final AccessException e) {
        return ResponseEntity.status(403).body(
                MessageFormat.format("Operation cannot be performed: {0}", e.getMessage())
        );
    }

    @ExceptionHandler(DuplicateEntryException.class)
    private ResponseEntity<Object> handleDuplicateEntryException(final DuplicateEntryException e) {
        return ResponseEntity.status(409).body(
                MessageFormat.format("Duplicate entry: {0}", e.getMessage())
        );
    }

}
