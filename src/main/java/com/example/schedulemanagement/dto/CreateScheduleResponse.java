package com.example.schedulemanagement.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class CreateScheduleResponse {

    private final Long id;
    private final String title;
    private final String content;
    private final String authorName;
    private final LocalDate createdDate;
    private final LocalDate modifiedDate;
}
