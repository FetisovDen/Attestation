package com.attestation.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MissingOrIncorrectFormat extends RuntimeException {
    public MissingOrIncorrectFormat() {
        super("параметры запроса отсутствуют или имеют некорректный формат");
    }
}
