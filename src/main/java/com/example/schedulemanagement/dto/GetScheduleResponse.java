package com.example.schedulemanagement.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor    // final로 선언된 변수들을 매개변수로 하는 생성자 자동 생성
public class GetScheduleResponse {

    private final Long id;
    private final String title;
    private final String content;
    private final String authorName;
    private final LocalDate createdDate;
    private final LocalDate modifiedDate;

    // 위의 @RequiredArgsConstructor가 자동적으로 아래와 같은 생성자를 생성해 줌
//    public CreateScheduleResponse(Long id, String title, String content, String authorName, LocalDate createdDate, LocalDate modifiedDate) {
//        this.id = id;
//        this.title = title;
//        this.content = content;
//        this.authorName = authorName;
//        this.createdDate = createdDate;
//        this.modifiedDate = modifiedDate;
//    }
}
