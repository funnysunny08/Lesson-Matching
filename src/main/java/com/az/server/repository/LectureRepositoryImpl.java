package com.az.server.repository;

import com.az.server.exception.Error;
import com.az.server.exception.model.NotFoundException;
import com.az.server.model.Lecture;
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
public class LectureRepositoryImpl implements LectureRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Lecture insert(Lecture lecture) {
        jdbcTemplate.update("INSERT INTO lecture(subject, region, price, number_of_week, tutor_id, created_at) " +
                        "VALUES (:subject, :region, :price, :numberOfWeek, :tutorId, :createdAt)",
                toParamMap(lecture));
        return lecture;
    }

    @Override
    public Optional<Lecture> findById(UUID lectureId) {
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject("SELECT * FROM lecture WHERE lecture_id = UUID_TO_BIN(:lectureId)",
                            Collections.singletonMap("lectureId", lectureId.toString().getBytes()), lectureRowMapper)
            );
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException(Error.NOT_FOUND_LECTURE_EXCEPTION, Error.NOT_FOUND_LECTURE_EXCEPTION.getMessage());
        }
    }

    @Override
    public List<Lecture> findAll() {
        return jdbcTemplate.query("SELECT * FROM lecture", lectureRowMapper);
    }

    @Override
    public List<Lecture> findBySubject(String subject) {
        return jdbcTemplate.query(
                "SELECT * FROM lecture WHERE subject = :subject",
                Collections.singletonMap("subject", subject),
                lectureRowMapper
        );
    }

    @Override
    public List<Lecture> findByTutorId(UUID tutorId) {
        return jdbcTemplate.query(
                "SELECT * FROM lecture WHERE tutor_id = UUID_TO_BIN(:tutorId)",
                Collections.singletonMap("tutorId", tutorId.toString().getBytes()),
                lectureRowMapper
        );
    }

    private static final RowMapper<Lecture> lectureRowMapper = (resultSet, i) -> {
        Long lectureId = resultSet.getLong("lecture_id");
        String subject = resultSet.getString("subject");
        String region = resultSet.getString("region");
        int price = resultSet.getInt("price");
        int numberOfWeek = resultSet.getInt("number_of_week");
        Long tutorId = resultSet.getLong("tutor_id");
        LocalDateTime createdAt = toLocalDateTime(resultSet.getTimestamp("created_at"));
        return new Lecture(lectureId, subject, region, price, numberOfWeek,tutorId, createdAt);
    };

    private Map<String, Object> toParamMap(Lecture lecture) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("lectureId", lecture.getLectureId());
        paramMap.put("subject", lecture.getSubject());
        paramMap.put("region", lecture.getRegion());
        paramMap.put("price", lecture.getPrice());
        paramMap.put("numberOfWeek", lecture.getNumberOfWeek());
        paramMap.put("tutorId", lecture.getTutorId());
        paramMap.put("createdAt", lecture.getCreatedAt());
        return paramMap;
    }
}
