package org.example.book2.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Getter
@Builder
@Entity
public class TodoEntity {
    @Id @GeneratedValue
    private Long id;      // 이 오브젝트의 아이디
    private String userId;  // 이 오브젝트를 생성한 유저의 아이디
    private String title;   // Todo 타이틀 예) 운동하기
    private boolean done;   // true - todo를 완료한 경우 (checked)


    public void addUserId(String userId) {
        this.userId = userId;
    }
}
