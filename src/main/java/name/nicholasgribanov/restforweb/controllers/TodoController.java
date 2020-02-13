package name.nicholasgribanov.restforweb.controllers;

import name.nicholasgribanov.restforweb.model.Todo;
import name.nicholasgribanov.restforweb.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @GetMapping("/users/{username}/todos/{id}")
    public Todo getTodoById(@PathVariable String username, @PathVariable long id) {
        return todoService.findAllByUsername(username).stream().filter(todo -> todo.getId() == id).findFirst().orElse(null);
    }

    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodoById(@PathVariable String username, @PathVariable long id) {
        Todo todo = todoService.deleteById(id);
        if (todo == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable String username, @PathVariable long id, @RequestBody Todo todo) {
        Todo savedTodo = todoService.save(todo);
        return new ResponseEntity<>(savedTodo, HttpStatus.OK);
    }

    @PostMapping("/users/{username}/todos")
    public ResponseEntity<Todo> insertTodo(@PathVariable String username, @RequestBody Todo todo) {
        Todo savedTodo = todoService.save(todo);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(savedTodo.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
