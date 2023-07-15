package com.az.server.repository;

import com.az.server.model.Lecture;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LectureRepository {
    // CREATE
    Lecture insert(Lecture lecture);

    // READ
    Optional<Lecture> findById(UUID lectureId);

    List<Lecture> findAll();

    List<Lecture> findBySubject(String subject);

    List<Lecture> findByTutorId(UUID tutorId);

    // UPDATE

    // DELETE

}
