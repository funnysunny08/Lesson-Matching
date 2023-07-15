package com.az.server.repository;

import com.az.server.exception.Error;
import com.az.server.exception.model.NotFoundException;
import com.az.server.model.Gender;
import com.az.server.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

import static com.az.server.util.JdbcUtils.toLocalDateTime;

@Repository
@RequiredArgsConstructor
public class StudentRepositoryImpl implements StudentRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Optional<Student> findById(Long studentId) {
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject("SELECT * FROM student WHERE student_id = :studentId",
                            Collections.singletonMap("studentId", studentId), studentRowMapper)
            );
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException(Error.NOT_FOUND_USER_EXCEPTION, Error.NOT_FOUND_USER_EXCEPTION.getMessage());
        }
    }

    private static final RowMapper<Student> studentRowMapper = (resultSet, i) -> {
        Long studentId = resultSet.getLong("student_id");
        String name = resultSet.getString("name");
        Gender gender = Gender.valueOf(resultSet.getString("gender"));
        int age = resultSet.getInt("age");
        String residentialArea = resultSet.getString("residential_area");
        LocalDateTime createdAt = toLocalDateTime(resultSet.getTimestamp("created_at"));
        return new Student(studentId, name, gender, age, residentialArea, createdAt);
    };

    private Map<String, Object> toParamMap(Student student) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("studentId", student.getStudentId());
        paramMap.put("name", student.getName());
        paramMap.put("gender", student.getGender().toString());
        paramMap.put("age", student.getAge());
        paramMap.put("residentialArea", student.getResidentialArea());
        paramMap.put("createdAt", student.getCreatedAt());
        return paramMap;
    }
}
