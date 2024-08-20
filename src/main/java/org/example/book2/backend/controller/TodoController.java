package org.example.book2.backend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.book2.backend.dto.TodoDTO;
import org.example.book2.backend.entity.TodoEntity;
import org.example.book2.backend.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("todo")
@RequiredArgsConstructor
@Log4j2
public class TodoController {
    private final TodoService todoService;

    @GetMapping("/test")
    public ResponseEntity<?> testTodo() {
        String str = todoService.testService();
        List<String> list = new ArrayList<>();
        list.add(str);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<?> createTodo(@RequestBody TodoDTO todoDTO) {
        try {
            String temporaryUserId = "temporary-user";
            TodoEntity entity = TodoDTO.toEntity(todoDTO);
            entity.addUserId(temporaryUserId);
            List<TodoEntity> todoEntities = todoService.create(entity);
            List<TodoDTO> response = todoEntities.stream()
                    .map(TodoDTO::new)
                    .collect(Collectors.toList());
            log.info(response);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
