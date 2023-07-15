package com.az.server.repository;

import com.az.server.exception.Error;
import com.az.server.exception.model.NotFoundException;
import com.az.server.model.Gender;
import com.az.server.model.Tutor;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

import static com.az.server.util.JdbcUtils.toLocalDateTime;
import static com.az.server.util.JdbcUtils.toUUID;

@Repository
@RequiredArgsConstructor
public class TutorRepositoryImpl implements TutorRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Optional<Tutor> findById(UUID tutorId) {
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject("SELECT * FROM tutor WHERE tutor_id = UUID_TO_BIN(:tutorId)",
                            Collections.singletonMap("tutorId", tutorId.toString().getBytes()), tutorRowMapper)
            );
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException(Error.NOT_FOUND_USER_EXCEPTION, Error.NOT_FOUND_USER_EXCEPTION.getMessage());
        }
    }

    private static final RowMapper<Tutor> tutorRowMapper = (resultSet, i) -> {
        UUID tutorId = toUUID(resultSet.getBytes("tutor_id"));
        String name = resultSet.getString("name");
        String university = resultSet.getString("university");
        String major = resultSet.getString("major");
        Gender gender = Gender.valueOf(resultSet.getString("gender"));
        int age = resultSet.getInt("age");
        LocalDateTime createdAt = toLocalDateTime(resultSet.getTimestamp("created_at"));
        return Tutor.builder()
                .tutorId(tutorId)
                .name(name)
                .university(university)
                .major(major)
                .gender(gender)
                .age(age)
                .createdAt(createdAt).build();
    };

    private Map<String, Object> toParamMap(Tutor tutor) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("tutorId", tutor.getTutorId().toString().getBytes());
        paramMap.put("name", tutor.getName());
        paramMap.put("university", tutor.getUniversity());
        paramMap.put("major", tutor.getMajor());
        paramMap.put("gender", tutor.getGender().toString());
        paramMap.put("age", tutor.getAge());
        paramMap.put("createdAt", tutor.getCreatedAt());
        return paramMap;
    }
}
