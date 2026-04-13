package com.example.schedulemanagement.controller;

import com.example.schedulemanagement.dto.CreateScheduleRequest;
import com.example.schedulemanagement.dto.CreateScheduleResponse;
import com.example.schedulemanagement.dto.GetScheduleResponse;
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
}
