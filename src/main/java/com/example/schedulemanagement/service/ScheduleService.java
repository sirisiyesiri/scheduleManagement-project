package com.example.schedulemanagement.service;

import com.example.schedulemanagement.dto.CreateScheduleRequest;
import com.example.schedulemanagement.dto.CreateScheduleResponse;
import com.example.schedulemanagement.dto.GetScheduleResponse;
import com.example.schedulemanagement.entity.Schedule;
import com.example.schedulemanagement.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public CreateScheduleResponse save(CreateScheduleRequest request) {
        Schedule schedule = new Schedule(
                request.getTitle(),
                request.getContent(),
                request.getAuthorName(),
                request.getPassword()
        );

        Schedule savedSchedule = scheduleRepository.save(schedule);    // DB에 갔다오면서 영속성 생김(= 고유 ID생김)
        return new CreateScheduleResponse(
                savedSchedule.getId(),
                savedSchedule.getTitle(),
                savedSchedule.getContent(),
                savedSchedule.getAuthorName(),
                savedSchedule.getCreatedDate(),
                savedSchedule.getModifiedDate()
        );
    }

    @Transactional(readOnly = true)
    public GetScheduleResponse findOne(Long scheduleID) {
        Schedule schedule = scheduleRepository.findById(scheduleID).orElseThrow(
                () -> new IllegalStateException("없는 일정입니다.")
        );

        return new GetScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getAuthorName(),
                schedule.getCreatedDate(),
                schedule.getModifiedDate()
        );
    }
}
