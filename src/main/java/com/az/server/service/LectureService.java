package com.az.server.service;

import com.az.server.controller.request.CreateLectureRequestDto;
import com.az.server.controller.response.CreateLectureResponseDto;
import com.az.server.controller.response.LectureResponseDto;
import com.az.server.exception.Error;
import com.az.server.exception.model.NotFoundException;
import com.az.server.model.Lecture;
import com.az.server.model.Tutor;
import com.az.server.repository.LectureRepository;
import com.az.server.repository.TutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureService {
    private final LectureRepository lectureRepository;
    private final TutorRepository tutorRepository;

    @Transactional
    public CreateLectureResponseDto createLecture(Long tutorId, CreateLectureRequestDto requestDto) {
        Lecture newLecture = new Lecture(requestDto.subject(), requestDto.region(), requestDto.price(), requestDto.numberOfWeek(), tutorId, LocalDateTime.now());
        lectureRepository.insert(newLecture);
        return CreateLectureResponseDto.of(newLecture.getCreatedAt());
    }

    public List<LectureResponseDto> getAllLecture() {
        List<LectureResponseDto> output = new ArrayList<>();

        List<Lecture> lectures = lectureRepository.findAll();
        lectures.forEach(lecture -> {
            Tutor tutor = tutorRepository.findById(lecture.getTutorId())
                    .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_LECTURE_EXCEPTION, Error.NOT_FOUND_LECTURE_EXCEPTION.getMessage()));
            output.add(
                    LectureResponseDto.of(lecture.getLectureId(), lecture.getSubject(), lecture.getRegion(), lecture.getPrice(), lecture.getNumberOfWeek(), tutor.getName(), tutor.getUniversity(), tutor.getMajor(), tutor.getGender().getGender(), tutor.getAge())
            );
        });
        return output;
    }
}
