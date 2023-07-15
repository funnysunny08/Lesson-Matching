package com.az.server.controller;

import com.az.server.common.dto.ApiResponse;
import com.az.server.controller.request.CreateMatchingRequestDto;
import com.az.server.controller.request.UpdateMatchingStatusRequestDto;
import com.az.server.controller.response.CreateMatchingResponseDto;
import com.az.server.controller.response.GetAllMatchingResponseDto;
import com.az.server.controller.response.UpdateMatchingStatusResponseDto;
import com.az.server.exception.Error;
import com.az.server.exception.Success;
import com.az.server.exception.model.BadRequestException;
import com.az.server.model.MatchingStatus;
import com.az.server.service.MatchingLectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/matching-lecture")
public class MatchingLectureController {

    private final MatchingLectureService matchingLectureService;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<GetAllMatchingResponseDto>> getAllMatching() {
        return ApiResponse.success(Success.GET_MATCHING_LECTURE_SUCCESS, matchingLectureService.getAllMatching());
    }

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

    @PatchMapping("/tutor/{matchingLectureId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<UpdateMatchingStatusResponseDto> updateStatusForTutor(
            @RequestHeader Long tutorId,
            @PathVariable Long matchingLectureId,
            @RequestBody UpdateMatchingStatusRequestDto requestDto)
    {
        MatchingStatus status = null;
        try {
            status = MatchingStatus.valueOf(requestDto.status());
        } catch (RuntimeException e) {
            throw new BadRequestException(Error.REQUEST_VALIDATION_EXCEPTION, Error.REQUEST_VALIDATION_EXCEPTION.getMessage());
        }
        return ApiResponse.success(Success.UPDATE_MATCHING_STATUS_SUCCESS, matchingLectureService.updateStatusForTutor(tutorId, matchingLectureId, status));
    }
}
