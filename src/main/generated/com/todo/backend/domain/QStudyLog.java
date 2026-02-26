package com.todo.backend.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QStudyLog is a Querydsl query type for StudyLog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStudyLog extends EntityPathBase<StudyLog> {

    private static final long serialVersionUID = 330629904L;

    public static final QStudyLog studyLog = new QStudyLog("studyLog");

    public final BooleanPath completed = createBoolean("completed");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DatePath<java.time.LocalDate> studyDate = createDate("studyDate", java.time.LocalDate.class);

    public final StringPath title = createString("title");

    public QStudyLog(String variable) {
        super(StudyLog.class, forVariable(variable));
    }

    public QStudyLog(Path<? extends StudyLog> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStudyLog(PathMetadata metadata) {
        super(StudyLog.class, metadata);
    }

}

