package com.todo.backend.service;

import com.todo.backend.domain.StudyLog;
import com.todo.backend.exception.BusinessException;
import com.todo.backend.exception.ErrorCode;
import com.todo.backend.repository.StudyLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudyService {

    private final StudyLogRepository studyLogRepository;

    /**
     * 새로운 공부 기록 저장
     */
    @Transactional
    public Long saveLog(String title, LocalDate date) {
        StudyLog log = StudyLog.builder()
                .title(title)
                .studyDate(date)
                .build();

        return studyLogRepository.save(log).getId();
    }

    /**
     * 특정 날짜의 공부 리스트 조회
     */
    public List<StudyLog> getLogsByDate(LocalDate date) {
        return studyLogRepository.findAllByStudyDate(date);
    }

    /**
     * 완료 상태 반전 (Check/Uncheck)
     * NoSuchElementException -> BusinessException 변경
     */
    @Transactional
    public void toggleComplete(Long id) {
        StudyLog log = studyLogRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.STUDY_LOG_NOT_FOUND));

        log.toggleCompletion();
    }

    /**
     * 기록 내용 수정
     * NoSuchElementException -> BusinessException 변경
     */
    @Transactional
    public void updateLogTitle(Long id, String newTitle) {
        StudyLog log = studyLogRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.STUDY_LOG_NOT_FOUND));

        log.updateTitle(newTitle);
    }

    /**
     * 기록 삭제
     * NoSuchElementException -> BusinessException 변경
     */
    @Transactional
    public void deleteLog(Long id) {
        StudyLog log = studyLogRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.STUDY_LOG_NOT_FOUND));

        studyLogRepository.delete(log);
    }

    public List<StudyLog> getLogsByMonth(String yearMonthStr) {
        // yearMonthStr 형식: "2026-02"
        YearMonth yearMonth = YearMonth.parse(yearMonthStr);
        LocalDate start = yearMonth.atDay(1);            // 2026-02-01
        LocalDate end = yearMonth.atEndOfMonth();        // 2026-02-28

        return studyLogRepository.findByStudyDateBetween(start, end);
    }

    @Transactional
    public void deleteAllLogsByDate(String dateStr) {
        LocalDate date = LocalDate.parse(dateStr);
        studyLogRepository.deleteByStudyDate(date);
    }
}