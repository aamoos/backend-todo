package com.todo.backend.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.todo.backend.domain.StudyLog;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import static com.todo.backend.domain.QStudyLog.studyLog;

@RequiredArgsConstructor
public class StudyLogRepositoryImpl implements StudyLogRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<StudyLog> findByDateRange(LocalDate start, LocalDate end) {
        return queryFactory
                .selectFrom(studyLog)
                .where(studyLog.studyDate.between(start, end))
                .orderBy(
                        studyLog.studyDate.asc(), // 1순위: 날짜 순서대로
                        studyLog.id.asc()         // 2순위: 같은 날짜 안에서는 ID 순서대로 (이게 핵심!)
                )
                .fetch();
    }

    public List<StudyLog> findAllByStudyDate(LocalDate date) {
        return queryFactory
                .selectFrom(studyLog)
                .where(studyLog.studyDate.eq(date))
                .orderBy(
                        studyLog.completed.asc(), // 1순위: false(0)인 미완료 건부터
                        studyLog.id.asc()         // 2순위: 그 안에서 등록 순서대로
                )
                .fetch();
    }
}
