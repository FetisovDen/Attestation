package com.attestation.project.controller.handler;

import com.attestation.project.exception.MissingOrIncorrectFormat;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class ExceptionControllerAdvice {
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MissingOrIncorrectFormat.class)
    public ResponseEntity<ErrorMessage> handleException400() {
        return ResponseEntity.status(BAD_REQUEST).body(
                new ErrorMessage("параметры запроса отсутствуют или имеют некорректный формат"));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException500() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ErrorMessage("произошла ошибка, не зависящая от вызывающей стороны (например, база данных недоступна)"));
    }
}
