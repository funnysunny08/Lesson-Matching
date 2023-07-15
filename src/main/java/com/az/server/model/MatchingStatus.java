package com.az.server.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MatchingStatus {
    PENDING("대기"),
    ACCEPTED("허가"),
    REFUSED("거절");

    private final String name;
}
