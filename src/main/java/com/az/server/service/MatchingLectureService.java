package com.az.server.service;

import com.az.server.controller.request.CreateMatchingRequestDto;
import com.az.server.controller.request.UpdateMatchingStatusRequestDto;
import com.az.server.controller.response.CreateMatchingResponseDto;
import com.az.server.controller.response.GetAllMatchingResponseDto;
import com.az.server.controller.response.LectureResponseDto;
import com.az.server.controller.response.UpdateMatchingStatusResponseDto;
import com.az.server.exception.Error;
import com.az.server.exception.model.BadRequestException;
import com.az.server.exception.model.NotFoundException;
import com.az.server.model.*;
import com.az.server.repository.LectureRepository;
import com.az.server.repository.MatchingLectureRepository;
import com.az.server.repository.StudentRepository;
import com.az.server.repository.TutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchingLectureService {
    private final MatchingLectureRepository matchingLectureRepository;
    private final StudentRepository studentRepository;
    private final TutorRepository tutorRepository;
    private final LectureRepository lectureRepository;

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

        if (!Objects.equals(matchingLecture.getStudentId(), studentId) || matchingLecture.getMatchingStatus() != MatchingStatus.PENDING)
            throw new BadRequestException(Error.REQUEST_VALIDATION_EXCEPTION, Error.REQUEST_VALIDATION_EXCEPTION.getMessage());

        matchingLecture.setMatchingStatus(MatchingStatus.REFUSED);
        matchingLectureRepository.updateByStatus(matchingLecture);
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_USER_EXCEPTION, Error.NOT_FOUND_USER_EXCEPTION.getMessage()));
        return UpdateMatchingStatusResponseDto.of(student.getName(), "학생", matchingLecture.getMatchingStatus().getName());
    }

    @Transactional
    public UpdateMatchingStatusResponseDto updateStatusForTutor(Long tutorId, Long matchingLectureId, MatchingStatus status) {
        MatchingLecture matchingLecture = matchingLectureRepository.findById(matchingLectureId)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_MATCHING_LECTURE, Error.NOT_FOUND_MATCHING_LECTURE.getMessage()));

        Tutor tutor = tutorRepository.findById(tutorId)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_USER_EXCEPTION, Error.NOT_FOUND_USER_EXCEPTION.getMessage()));

        Lecture lecture = lectureRepository.findById(matchingLecture.getLectureId())
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_LECTURE_EXCEPTION, Error.NOT_FOUND_LECTURE_EXCEPTION.getMessage()));

        if (!Objects.equals(lecture.getTutorId(), tutorId) || matchingLecture.getMatchingStatus() != MatchingStatus.PENDING)
            throw new BadRequestException(Error.REQUEST_VALIDATION_EXCEPTION, Error.REQUEST_VALIDATION_EXCEPTION.getMessage());
        matchingLecture.setMatchingStatus(status);
        matchingLectureRepository.updateByStatus(matchingLecture);

        return UpdateMatchingStatusResponseDto.of(tutor.getName(), "튜터", matchingLecture.getMatchingStatus().getName());
    }

    @Transactional
    public List<GetAllMatchingResponseDto> getAllMatching() {
        List<LectureResponseDto> output = new ArrayList<>();

        List<MatchingLecture> matchingLectures = matchingLectureRepository.findAll();

        return matchingLectures.stream()
                .map(matchingLecture -> GetAllMatchingResponseDto.of(matchingLecture.getMatchingLectureId(), matchingLecture.getMatchingStatus().getName()))
                .collect(Collectors.toList());
    }
}
