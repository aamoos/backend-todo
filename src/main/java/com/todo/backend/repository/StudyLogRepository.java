package com.todo.backend.repository;

import com.todo.backend.domain.StudyLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface StudyLogRepository extends JpaRepository<StudyLog, Long>, StudyLogRepositoryCustom {
    // 기본 제공 메서드 외에 날짜별 조회 추가
    List<StudyLog> findAllByStudyDate(LocalDate date);

    // 쿼리 예시: WHERE study_date >= '2026-02-01' AND study_date <= '2026-02-28'
    List<StudyLog> findByStudyDateBetween(LocalDate start, LocalDate end);

    void deleteByStudyDate(LocalDate studyDate);
}
