package com.az.server.repository;

import com.az.server.model.Tutor;

import java.util.Optional;

public interface TutorRepository {
    // CREATE

    // READ
    Optional<Tutor> findById(Long tutorId);

    // UPDATE

    // DELETE

}
