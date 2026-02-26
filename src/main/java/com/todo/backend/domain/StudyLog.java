package com.todo.backend.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 외부에서 기본 생성자 호출 방지
public class StudyLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDate studyDate;

    private boolean completed;

    // 빌더 패턴을 사용하되, 필요한 필드만 제한적으로 받음
    @Builder
    private StudyLog(String title, LocalDate studyDate) {
        this.title = title;
        this.studyDate = studyDate;
        this.completed = false; // 생성 시 기본값은 미완료
    }

    // === 비즈니스 로직 (Setter 대신 사용) ===

    /**
     * 완료 상태를 반전시킴 (Check/Uncheck)
     */
    public void toggleCompletion() {
        this.completed = !this.completed;
    }

    /**
     * 내용을 수정해야 할 경우 (의도가 담긴 메서드명)
     */
    public void updateTitle(String newTitle) {
        if (newTitle == null || newTitle.isBlank()) {
            throw new IllegalArgumentException("제목은 비어있을 수 없습니다.");
        }
        this.title = newTitle;
    }
}