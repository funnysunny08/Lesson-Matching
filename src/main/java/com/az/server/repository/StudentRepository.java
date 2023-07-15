package com.az.server.repository;

import com.az.server.model.Student;

import java.util.Optional;

public interface StudentRepository {
    // CREATE

    // READ
    Optional<Student> findById(Long studentId);

    // UPDATE

    // DELETE

}
