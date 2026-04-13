package com.example.schedulemanagement.repository;

import com.example.schedulemanagement.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByAuthorName(String authorName);   // 다 건 조회

    // 만약 단 건 조회의 경우
    // Optional<Schedule> findByAuthorName();
    // null일 가능성도 있으니까


}
