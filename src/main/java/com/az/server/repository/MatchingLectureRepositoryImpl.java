package com.az.server.repository;

import com.az.server.exception.Error;
import com.az.server.exception.model.NotFoundException;
import com.az.server.model.Lecture;
import com.az.server.model.MatchingLecture;
import com.az.server.model.MatchingStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

import static com.az.server.util.JdbcUtils.toLocalDateTime;
import static com.az.server.util.JdbcUtils.toUUID;

@Repository
@RequiredArgsConstructor
public class MatchingLectureRepositoryImpl implements MatchingLectureRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final LectureRepository lectureRepository;

    @Override
    public MatchingLecture insert(MatchingLecture matchingLecture) {
        jdbcTemplate.update("INSERT INTO matching_lecture(matching_lecture_id, lecture_id, student_id, status, created_at) " +
                        "VALUES (UUID_TO_BIN(:matching_lecture_id), UUID_TO_BIN(:lecture_id), UUID_TO_BIN(:student_id), :status, :createdAt)",
                toParamMap(matchingLecture));
        return matchingLecture;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MatchingLecture> findByTutorId(UUID tutorId) {
        List<MatchingLecture> matchingLectures = new ArrayList<>();
        List<Lecture> lectures = lectureRepository.findByTutorId(tutorId);
        lectures.forEach(lecture -> {
            matchingLectures.addAll(jdbcTemplate.query("SELECT * FROM matching_lecture WHERE lecture_id = UUID_TO_BIN(:lectureId)",
                    Collections.singletonMap("lectureId", lecture.getLectureId().toString().getBytes()), matchingLectureRowMapper
            ));
        });
        return matchingLectures;
    }

    @Override
    public List<MatchingLecture> findByStudentId(UUID studentId) {
        return jdbcTemplate.query("SELECT * FROM matching_lecture WHERE student_id = UUID_TO_BIN(:studentId)",
                Collections.singletonMap("studentId", studentId.toString().getBytes()),
                matchingLectureRowMapper
            );
    }

    @Override
    public Optional<MatchingLecture> findById(UUID matchingLectureId) {
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject("SELECT * FROM matching_lecture WHERE matching_lecture_id = UUID_TO_BIN(:matchingLectureId)",
                            Collections.singletonMap("matchingLectureId", matchingLectureId.toString().getBytes()), matchingLectureRowMapper)
            );
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException(Error.NOT_FOUND_MATCHING_LECTURE, Error.NOT_FOUND_MATCHING_LECTURE.getMessage());
        }
    }

    @Override
    public List<MatchingLecture> findAll() {
        return jdbcTemplate.query("SELECT * FROM matching_lecture", matchingLectureRowMapper);
    }

    @Override
    public MatchingLecture updateByStatus(MatchingLecture matchingLecture) {
        jdbcTemplate.update("UPDATE matching_lecture SET status = :status WHERE matching_lecture_id = UUID_TO_BIN(:matchingLectureId)",
                toParamMap(matchingLecture)
        );
        return matchingLecture;
    }

    private static final RowMapper<MatchingLecture> matchingLectureRowMapper = (resultSet, i) -> {
        UUID matchingLectureId = toUUID(resultSet.getBytes("matching_lecture_id"));
        UUID lectureId = toUUID(resultSet.getBytes("lecture_id"));
        UUID studentId = toUUID(resultSet.getBytes("student_id"));
        MatchingStatus matchingStatus = MatchingStatus.valueOf(resultSet.getString("status"));
        LocalDateTime createdAt = toLocalDateTime(resultSet.getTimestamp("created_at"));
        return MatchingLecture.builder()
                .matchingLectureId(matchingLectureId)
                .lectureId(lectureId)
                .studentId(studentId)
                .matchingStatus(matchingStatus)
                .createdAt(createdAt).build();
    };

    private Map<String, Object> toParamMap(MatchingLecture matchingLecture) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("matchingLectureId", matchingLecture.getMatchingLectureId().toString().getBytes());
        paramMap.put("lectureId", matchingLecture.getLectureId().toString().getBytes());
        paramMap.put("studentId", matchingLecture.getStudentId().toString().getBytes());
        paramMap.put("matchingStatus", matchingLecture.getMatchingStatus().toString());
        paramMap.put("createdAt", matchingLecture.getCreatedAt());
        return paramMap;
    }
}
