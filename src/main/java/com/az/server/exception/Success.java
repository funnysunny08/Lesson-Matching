package com.az.server.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Success {

    /**
     * 200 OK
     */
    GET_USER_SUCCESS(HttpStatus.OK, "유저 조회 성공"),
    GET_LECTURE_SUCCESS(HttpStatus.OK, "과외 조회 성공"),

    /**
     * 201 CREATED
     */
    LOGIN_SUCCESS(HttpStatus.OK, "로그인에 성공했습니다."),
    SIGNUP_SUCCESS(HttpStatus.CREATED, "회원가입이 완료됐습니다."),
    CREATE_LECTURE_SUCCESS(HttpStatus.CREATED, "과외 등록에 성공했습니다."),

    /**
     * 204 NO CONTENT
     */
    DELETE_FEED_SUCCESS(HttpStatus.NO_CONTENT, "피드가 정상적으로 삭제되었습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
