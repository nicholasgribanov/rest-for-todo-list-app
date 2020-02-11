package name.nicholasgribanov.restforweb.controllers;

import name.nicholasgribanov.restforweb.model.Todo;
import name.nicholasgribanov.restforweb.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
public class TodoController {

    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }


    @GetMapping("/todos")
    public List<Todo> getAllTodos() {
        return todoService.findAll();
    }

    @GetMapping("/users/{username}/todos")
    public List<Todo> getAllTodosForUser(@PathVariable String username) {
        return todoService.findAllByUsername(username);
    }

    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodoById(@PathVariable String username, @PathVariable long id) {
        Todo todo = todoService.deleteById(id);
        if (todo == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }
}
