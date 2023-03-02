package com.sparta.hanghaepost.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResultResponseDto {
    private boolean result;

    public ResultResponseDto(boolean result) {
        this.result = result;
    }
}
