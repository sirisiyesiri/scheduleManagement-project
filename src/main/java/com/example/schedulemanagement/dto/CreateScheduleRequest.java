package com.example.schedulemanagement.dto;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
public class CreateScheduleRequest {
    // Request이기 때문에 final 안 붙음

    private String title;
    private String content;
    private String authorName;
    private String password;
}
