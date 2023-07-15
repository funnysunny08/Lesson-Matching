package com.az.server.repository;

import com.az.server.model.Student;

import java.util.Optional;
import java.util.UUID;

public interface StudentRepository {
    // CREATE

    // READ
    Optional<Student> findById(UUID studentId);

    // UPDATE

    // DELETE

}
