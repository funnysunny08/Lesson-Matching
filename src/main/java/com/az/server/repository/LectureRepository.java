package com.az.server.repository;

import com.az.server.model.Lecture;

import java.util.List;
import java.util.Optional;

public interface LectureRepository {
    // CREATE
    Lecture insert(Lecture lecture);

    // READ
    Optional<Lecture> findById(Long lectureId);

    List<Lecture> findAll();

    List<Lecture> findBySubject(String subject);

    List<Lecture> findByTutorId(Long tutorId);

    // UPDATE

    // DELETE

}
