package org.example.book2.backend.repository;

import org.example.book2.backend.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

// 생략 가능
@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {

    // ?1은 메서드의 매개변수의 순서 위치이다.
    @Query("select t from TodoEntity t where t.userId = ?1")
    List<TodoEntity> findByUserIdQuery(String userId);

    List<TodoEntity> findByUserId(String userId);
}
