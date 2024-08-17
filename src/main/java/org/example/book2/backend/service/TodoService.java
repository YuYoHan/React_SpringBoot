package org.example.book2.backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.book2.backend.entity.TodoEntity;
import org.example.book2.backend.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class TodoService {

    private final TodoRepository todoRepository;

    public String testService() {
        TodoEntity entity = TodoEntity.builder().title("My first todo item").build();
        todoRepository.save(entity);
        TodoEntity todoEntity = todoRepository.findById(entity.getId()).get();
        return todoEntity.getTitle();
    }

    public List<TodoEntity> create(final TodoEntity entity) {
        // Validations
        validate(entity);

        todoRepository.save(entity);
        log.info("Entity Id : {} is saved.", entity.getId());

        return todoRepository.findByUserId(entity.getUserId());
    }

    private void validate(final TodoEntity entity) {
        if(entity == null) {
            log.warn("Entity cannot be null");
            throw new RuntimeException("Entity cannot be null");
        }

        if(entity.getUserId() == null) {
            log.warn("Unknown user");
            throw new RuntimeException("Unknown user");
        }
    }
}
