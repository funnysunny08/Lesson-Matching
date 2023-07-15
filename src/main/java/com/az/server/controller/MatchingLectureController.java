package com.az.server.controller;

import com.az.server.common.dto.ApiResponse;
import com.az.server.controller.request.CreateMatchingRequestDto;
import com.az.server.controller.response.CreateMatchingResponseDto;
import com.az.server.controller.response.UpdateMatchingStatusResponseDto;
import com.az.server.exception.Success;
import com.az.server.service.MatchingLectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/matching-lecture")
public class MatchingLectureController {

    private final MatchingLectureService matchingLectureService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CreateMatchingResponseDto> createMatching(@RequestBody CreateMatchingRequestDto requestDto) {
        return ApiResponse.success(Success.CREATE_MATCHING_LECTURE_SUCCESS, matchingLectureService.createMatching(requestDto));
    }

    @PatchMapping("/student/{matchingLectureId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<UpdateMatchingStatusResponseDto> updateStatusForStudent(@RequestHeader Long studentId, @PathVariable Long matchingLectureId) {
        return ApiResponse.success(Success.UPDATE_MATCHING_STATUS_SUCCESS, matchingLectureService.updateStatusForStudent(studentId, matchingLectureId));
    }
}
