package com.tad.dto.exception;

import org.springframework.http.HttpStatus;

public class TadAPIException extends RuntimeException{
    private HttpStatus status;
    private String message;

    // Constructor 1: Đơn giản
    public TadAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    // Constructor 2: Đầy đủ hơn với tên tham số rõ ràng
    public TadAPIException(String stackTraceMessage, HttpStatus status, String userMessage) {
        super(stackTraceMessage);        // Lưu vào RuntimeException (cho stack trace)
        this.status = status;
        this.message = userMessage;      // Lưu vào field của class này (cho user)
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;  // Trả về userMessage
    }
}
