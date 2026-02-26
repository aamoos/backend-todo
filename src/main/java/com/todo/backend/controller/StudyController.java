package com.todo.backend.controller;

import com.todo.backend.domain.StudyLog;
import com.todo.backend.service.StudyService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/study")
@RequiredArgsConstructor
public class StudyController {

    private final StudyService studyService;

    /**
     * 특정 날짜의 공부 리스트 조회
     * GET /api/study?date=2026-02-25
     */
    @GetMapping
    public ResponseEntity<List<StudyLog>> getLogs(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(studyService.getLogsByDate(date));
    }

    /**
     * 새로운 공부 기록 추가
     * POST /api/study
     */
    @PostMapping
    public ResponseEntity<Long> addLog(@RequestBody StudyRequest request) {
        Long id = studyService.saveLog(request.getTitle(), request.getStudyDate());
        return ResponseEntity.ok(id);
    }

    /**
     * 완료 상태 토글 (체크박스 클릭)
     * PATCH /api/study/1/toggle
     */
    @PatchMapping("/{id}/toggle")
    public ResponseEntity<Void> toggle(@PathVariable Long id) {
        studyService.toggleComplete(id);
        return ResponseEntity.ok().build();
    }

    /**
     * 제목 수정
     * PATCH /api/study/1/title
     */
    @PatchMapping("/{id}/title")
    public ResponseEntity<Void> updateTitle(
            @PathVariable Long id,
            @RequestBody TitleUpdateRequest request) {
        studyService.updateLogTitle(id, request.getNewTitle());
        return ResponseEntity.ok().build();
    }

    /**
     * 기록 삭제
     * DELETE /api/study/1
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studyService.deleteLog(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/month")
    public ResponseEntity<List<StudyLog>> getMonthLogs(
            @RequestParam String yearMonth) { // "2026-02" 형태로 받음
        return ResponseEntity.ok(studyService.getLogsByMonth(yearMonth));
    }

    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllLogs(@RequestParam String date) {
        studyService.deleteAllLogsByDate(date);
        return ResponseEntity.noContent().build();
    }

    // --- 데이터를 주고받기 위한 DTO (Data Transfer Object) ---

    @Getter
    @NoArgsConstructor
    public static class StudyRequest {
        private String title;
        private LocalDate studyDate;
    }

    @Getter
    @NoArgsConstructor
    public static class TitleUpdateRequest {
        private String newTitle;
    }
}