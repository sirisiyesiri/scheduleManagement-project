package com.example.schedulemanagement.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class UpdateScheduleResponse {

    private final Long id;
    private final String title;
    private final String content;
    private final String authorName;
    private final LocalDate createdDate;
    private final LocalDate modifiedDate;
}
