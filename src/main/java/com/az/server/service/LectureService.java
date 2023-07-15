package com.az.server.service;

import com.az.server.controller.request.CreateLectureRequestDto;
import com.az.server.controller.response.CreateLectureResponseDto;
import com.az.server.model.Lecture;
import com.az.server.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.az.server.util.JdbcUtils.toUUID;

@Service
@RequiredArgsConstructor
public class LectureService {
    private final LectureRepository lectureRepository;

    @Transactional
    public CreateLectureResponseDto createLecture(Long tutorId, CreateLectureRequestDto requestDto) {
        Lecture newLecture = new Lecture(requestDto.subject(),requestDto.region(), requestDto.price(), requestDto.numberOfWeek(), tutorId, LocalDateTime.now());
        lectureRepository.insert(newLecture);
        return  CreateLectureResponseDto.of(newLecture.getCreatedAt());
    }
}
