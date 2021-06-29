package br.com.rezende.specification.example.controller;

import org.springframework.http.ResponseEntity;

import br.com.rezende.specification.example.service.errors.ErrorResponse;

public class BaseController {

    protected <T> ResponseEntity<T> toResponse(T body) {
        return ResponseEntity
            .ok(body);
    }

    protected <T extends ErrorResponse> ResponseEntity<T> errorToResponse(T error) {
        return ResponseEntity
            .status(error.status)
            .body(error);
    }

}
