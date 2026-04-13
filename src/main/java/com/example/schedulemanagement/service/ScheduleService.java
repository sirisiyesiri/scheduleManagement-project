package com.example.schedulemanagement.service;

import com.example.schedulemanagement.PasswordNotMatchException;
import com.example.schedulemanagement.dto.*;
import com.example.schedulemanagement.entity.Schedule;
import com.example.schedulemanagement.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    // 일정 생성
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

    // 단일 일정 조회
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

    // 전체 일정 조회
    @Transactional(readOnly = true)
    public List<GetScheduleResponse> findAll() {
        List<Schedule> schedules = scheduleRepository.findAll();

        return schedules.stream()
                .sorted(Comparator.comparing(Schedule::getModifiedDate).reversed())
                // Comparator.comparing : 특정 필드 기준 정렬
                // Schedule::getModifiedDate : Schedule -> Schedule.getModifiedDate
                // reversed() : 내림차순
                .map(schedule -> new GetScheduleResponse(
                        schedule.getId(),
                        schedule.getTitle(),
                        schedule.getContent(),
                        schedule.getAuthorName(),
                        schedule.getCreatedDate(),
                        schedule.getModifiedDate()
                )). toList();
    }

    // 작성자명에 따른 일정 조회
    @Transactional(readOnly = true)
    public List<GetScheduleResponse> findAllByAuthorName(String authorName) {
        List<Schedule> schedules = scheduleRepository.findAllByAuthorName(authorName);

        return schedules.stream()
                .sorted(Comparator.comparing(Schedule::getModifiedDate).reversed())
                // Comparator.comparing : 특정 필드 기준 정렬
                // Schedule::getModifiedDate : Schedule -> Schedule.getModifiedDate
                // reversed() : 내림차순
                .map(schedule -> new GetScheduleResponse(
                        schedule.getId(),
                        schedule.getTitle(),
                        schedule.getContent(),
                        schedule.getAuthorName(),
                        schedule.getCreatedDate(),
                        schedule.getModifiedDate()
                )). toList();
    }

    // 일정 수정
    @Transactional
    public UpdateScheduleResponse update(Long scheduleID, UpdateScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleID).orElseThrow(
                () -> new IllegalStateException("없는 일정입니다.")
        );

        if(request.getPassword().equals(schedule.getPassword())) {
            schedule.updateInfo(
                    request.getTitle(),
                    request.getAuthorName()
            );

            return new UpdateScheduleResponse(
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getContent(),
                    schedule.getAuthorName(),
                    schedule.getCreatedDate(),
                    schedule.getModifiedDate()
            );
        } else {
            throw new PasswordNotMatchException();  // 비밀번호 일치하지 않을 경우, 예외 처리
        }
    }
}
