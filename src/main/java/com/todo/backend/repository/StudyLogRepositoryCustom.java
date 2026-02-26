package com.todo.backend.repository;

import com.todo.backend.domain.StudyLog;

import java.time.LocalDate;
import java.util.List;

public interface StudyLogRepositoryCustom {
    // 특정 날짜 범위의 기록 찾기 (캘린더 한 달 치 데이터를 한 번에 가져올 때 유용)
    List<StudyLog> findByDateRange(LocalDate start, LocalDate end);
}
