package com.az.server.service;

import com.az.server.controller.request.CreateMatchingRequestDto;
import com.az.server.controller.response.CreateMatchingResponseDto;
import com.az.server.model.MatchingLecture;
import com.az.server.model.MatchingStatus;
import com.az.server.repository.MatchingLectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MatchingLectureService {
    private final MatchingLectureRepository matchingLectureRepository;

    public CreateMatchingResponseDto createMatching(CreateMatchingRequestDto requestDto) {
        MatchingLecture matchingLecture = new MatchingLecture(MatchingStatus.PENDING, requestDto.lectureId(), requestDto.studentId(), LocalDateTime.now());
        matchingLectureRepository.insert(matchingLecture);
        System.out.println("aasdlkasja");
        return CreateMatchingResponseDto.of(matchingLecture.getCreatedAt());
    }
}
