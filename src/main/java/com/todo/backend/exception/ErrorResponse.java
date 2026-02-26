package com.todo.backend.exception;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@Builder // 이 어노테이션이 있어야 .builder()를 쓸 수 있습니다.
public class ErrorResponse {
    private final LocalDateTime timestamp;
    private final int status;
    private final String error;
    private final String code;    // 에러 코드 (예: S001)
    private final String message; // 에러 메시지
    private final String path;    // 요청 경로
}
