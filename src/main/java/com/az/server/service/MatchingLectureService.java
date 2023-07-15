package com.az.server.service;

import com.az.server.controller.request.CreateMatchingRequestDto;
import com.az.server.controller.response.CreateMatchingResponseDto;
import com.az.server.controller.response.UpdateMatchingStatusResponseDto;
import com.az.server.exception.Error;
import com.az.server.exception.model.BadRequestException;
import com.az.server.exception.model.NotFoundException;
import com.az.server.model.MatchingLecture;
import com.az.server.model.MatchingStatus;
import com.az.server.model.Student;
import com.az.server.repository.MatchingLectureRepository;
import com.az.server.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MatchingLectureService {
    private final MatchingLectureRepository matchingLectureRepository;
    private final StudentRepository studentRepository;

    @Transactional
    public CreateMatchingResponseDto createMatching(CreateMatchingRequestDto requestDto) {
        MatchingLecture matchingLecture = new MatchingLecture(MatchingStatus.PENDING, requestDto.lectureId(), requestDto.studentId(), LocalDateTime.now());
        matchingLectureRepository.insert(matchingLecture);
        return CreateMatchingResponseDto.of(matchingLecture.getMatchingStatus().getName(), matchingLecture.getCreatedAt());
    }

    @Transactional
    public UpdateMatchingStatusResponseDto updateStatusForStudent(Long studentId, Long matchingLectureId) {
        MatchingLecture matchingLecture = matchingLectureRepository.findById(matchingLectureId)
                        .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_MATCHING_LECTURE, Error.NOT_FOUND_MATCHING_LECTURE.getMessage()));

        if (!Objects.equals(matchingLecture.getStudentId(), studentId) || matchingLecture.getMatchingStatus() == MatchingStatus.REFUSED)
            throw new BadRequestException(Error.REQUEST_VALIDATION_EXCEPTION, Error.REQUEST_VALIDATION_EXCEPTION.getMessage());

        matchingLecture.setMatchingStatus(MatchingStatus.REFUSED);
        matchingLectureRepository.updateByStatus(matchingLecture);
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_USER_EXCEPTION, Error.NOT_FOUND_USER_EXCEPTION.getMessage()));
        return UpdateMatchingStatusResponseDto.of(student.getName(), "학생", matchingLecture.getMatchingStatus().getName());
    }
}
