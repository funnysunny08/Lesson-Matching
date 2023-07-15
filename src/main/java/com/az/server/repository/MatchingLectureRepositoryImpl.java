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

import java.time.LocalDateTime;
import java.util.*;

import static com.az.server.util.JdbcUtils.toLocalDateTime;

@Repository
@RequiredArgsConstructor
public class MatchingLectureRepositoryImpl implements MatchingLectureRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final LectureRepository lectureRepository;

    @Override
    public MatchingLecture insert(MatchingLecture matchingLecture) {
        jdbcTemplate.update("INSERT INTO matching_lecture(lecture_id, student_id, status, created_at) " +
                        "VALUES (:lectureId, :studentId, :status, :createdAt)",
                toParamMap(matchingLecture));
        return matchingLecture;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MatchingLecture> findByTutorId(Long tutorId) {
        List<MatchingLecture> matchingLectures = new ArrayList<>();
        List<Lecture> lectures = lectureRepository.findByTutorId(tutorId);
        lectures.forEach(lecture -> {
            matchingLectures.addAll(jdbcTemplate.query("SELECT * FROM matching_lecture WHERE lecture_id = :lectureId",
                    Collections.singletonMap("lectureId", lecture.getLectureId()), matchingLectureRowMapper
            ));
        });
        return matchingLectures;
    }

    @Override
    public List<MatchingLecture> findByStudentId(Long studentId) {
        return jdbcTemplate.query("SELECT * FROM matching_lecture WHERE student_id = :studentId",
                Collections.singletonMap("studentId", studentId),
                matchingLectureRowMapper
        );
    }

    @Override
    public Optional<MatchingLecture> findById(Long matchingLectureId) {
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject("SELECT * FROM matching_lecture WHERE matching_lecture_id = :matchingLectureId",
                            Collections.singletonMap("matchingLectureId", matchingLectureId), matchingLectureRowMapper)
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
        jdbcTemplate.update("UPDATE matching_lecture SET status = :status WHERE matching_lecture_id = :matchingLectureId",
                toParamMap(matchingLecture)
        );
        return matchingLecture;
    }

    private static final RowMapper<MatchingLecture> matchingLectureRowMapper = (resultSet, i) -> {
        Long matchingLectureId = resultSet.getLong("matching_lecture_id");
        Long lectureId = resultSet.getLong("lecture_id");
        Long studentId = resultSet.getLong("student_id");
        MatchingStatus matchingStatus = MatchingStatus.valueOf(resultSet.getString("status"));
        LocalDateTime createdAt = toLocalDateTime(resultSet.getTimestamp("created_at"));
        return new MatchingLecture(matchingLectureId, matchingStatus, lectureId, studentId, createdAt);
    };

    private Map<String, Object> toParamMap(MatchingLecture matchingLecture) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("matchingLectureId", matchingLecture.getMatchingLectureId());
        paramMap.put("lectureId", matchingLecture.getLectureId());
        paramMap.put("studentId", matchingLecture.getStudentId());
        paramMap.put("status", matchingLecture.getMatchingStatus().toString());
        paramMap.put("createdAt", matchingLecture.getCreatedAt());
        return paramMap;
    }
}
