package com.todo.backend.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    // 공통 에러
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "C001", " 올바르지 않은 입력값입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C002", " 서버 내부 오류가 발생했습니다."),

    // StudyLog 관련 에러
    STUDY_LOG_NOT_FOUND(HttpStatus.NOT_FOUND, "S001", " 해당 공부 기록을 찾을 수 없습니다."),
    TITLE_REQUIRED(HttpStatus.BAD_REQUEST, "S002", " 공부 제목은 필수 입력 항목입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    ErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
