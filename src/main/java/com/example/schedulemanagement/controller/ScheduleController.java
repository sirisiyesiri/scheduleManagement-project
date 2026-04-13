package com.example.schedulemanagement.controller;

import com.example.schedulemanagement.dto.*;
import com.example.schedulemanagement.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ResponseEntity<CreateScheduleResponse> createSchedule(@RequestBody CreateScheduleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.save(request));
    }

    @GetMapping("/schedules/{scheduleID}")
    public ResponseEntity<GetScheduleResponse> findOneSchedule(@PathVariable Long scheduleID) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findOne(scheduleID));
    }

    @GetMapping("/schedules")
    public ResponseEntity<List<GetScheduleResponse>> findAllScheduleByAuthorName(
            @RequestParam(required = false) String authorName
    ) {
        if(authorName == null || authorName.isBlank()) {
            return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findAll());
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findAllByAuthorName(authorName));
        }
    }

    // 사용자는 title과 authorName 중 수정하길 원하는 필드의 수정 내용만을 하기 때문에(password는 필수) patch사용
    // 만약 put을 사용하면 title과 authorName 둘 다 입력해야 함.(password는 필수)
    @PatchMapping("/schedules/{scheduleID}")
    public ResponseEntity<UpdateScheduleResponse> updateSchedule(
            @PathVariable Long scheduleID,
            @RequestBody UpdateScheduleRequest request
            ) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.update(scheduleID, request));
    }

}
