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
                .orderBy(studyLog.studyDate.asc())
                .fetch();
    }
}
