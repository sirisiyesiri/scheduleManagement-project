package com.example.schedulemanagement.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.repository.cdi.Eager;

import java.time.LocalDate;

@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)

// JPA Auditing 기능을 활성화하여 생성일/수정일을 자동으로 관리하도록 설정
// -> 해당 엔티티에서 Auditing을 사용한다는 선언
@EntityListeners(AuditingEntityListener.class)
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    // 고유 식별 ID
    private String title;   // 일정 제목
    private String content; // 일정 내용
    private String authorName;  // 작성자명
    private String password;    // 비밀번호

    // 엔티티가 처음 저장될 때 현재 날짜를 자동으로 저장
    @CreatedDate
    private LocalDate createdDate;  // 작성일

    // 엔티티가 수정될 때마다 현재 날짜로 자동 갱신
    @LastModifiedDate
    private LocalDate modifiedDate; // 수정일

    public Schedule(String title, String content, String authorName, String password) {
        this.title = title;
        this.content = content;
        this.authorName = authorName;
        this.password = password;
//        this.createdDate = createdDate;   // save()시점에 createdDate랑 modifiedDate 값은 자동으로 세팅되므로 생성자에 넣을 필요X
//        this.modifiedDate = modifiedDate;
    }

    // title과 authorName 세터를 각각 만들기 보다 한 번에 만들어서 그 안에서 조건 구분을 통해 값 세팅
    public void updateInfo(String title, String authorName) {
        if(title != null) {
            this.title = title;
        }
        if(authorName != null) {
            this.authorName = authorName;
        }
    }
}
