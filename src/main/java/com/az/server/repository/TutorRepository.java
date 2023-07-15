package com.az.server.repository;

import com.az.server.model.Tutor;

import java.util.Optional;
import java.util.UUID;

public interface TutorRepository {
    // CREATE

    // READ
    Optional<Tutor> findById(UUID tutorId);

    // UPDATE

    // DELETE

}
