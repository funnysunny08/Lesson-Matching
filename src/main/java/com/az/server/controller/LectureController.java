package com.az.server.controller;

import com.az.server.common.dto.ApiResponse;
import com.az.server.controller.request.CreateLectureRequestDto;
import com.az.server.controller.response.CreateLectureResponseDto;
import com.az.server.controller.response.LectureResponseDto;
import com.az.server.exception.Success;
import com.az.server.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lecture")
public class LectureController {

    private final LectureService lectureService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CreateLectureResponseDto> createLecture(
            @RequestHeader Long tutorId,
            @RequestBody CreateLectureRequestDto requestDto
    ) {
        return ApiResponse.success(Success.CREATE_LECTURE_SUCCESS, lectureService.createLecture(tutorId, requestDto));
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<LectureResponseDto>> getAllLecture() {
        return ApiResponse.success(Success.GET_LECTURE_SUCCESS, lectureService.getAllLecture());
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<LectureResponseDto>> getLectureBySubject(@RequestParam String subject) {
        return ApiResponse.success(Success.GET_LECTURE_SUCCESS, lectureService.getLectureBySubject(subject));
    }


}
