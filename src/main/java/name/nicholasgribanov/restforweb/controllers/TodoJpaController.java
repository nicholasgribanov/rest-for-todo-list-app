package name.nicholasgribanov.restforweb.controllers;

import name.nicholasgribanov.restforweb.model.Todo;
import name.nicholasgribanov.restforweb.repository.TodoJpaRepository;
import name.nicholasgribanov.restforweb.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin("*")
@RestController
public class TodoJpaController {

    private TodoJpaRepository repository;

    public TodoJpaController(TodoJpaRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/jpa/todos")
    public List<Todo> getAllTodos() {
        return repository.findAll();
    }

    @GetMapping("/jpa/users/{username}/todos")
    public List<Todo> getAllTodosForUser(@PathVariable String username) {
        return repository.findAllByUsername(username);
    }

    @GetMapping("/jpa/users/{username}/todos/{id}")
    public Todo getTodoById(@PathVariable String username, @PathVariable long id) {
        return repository.findAllByUsername(username).stream().filter(todo -> todo.getId() == id).findFirst().orElse(null);
    }

    @DeleteMapping("/jpa/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodoById(@PathVariable String username, @PathVariable long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/jpa/users/{username}/todos/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable String username, @PathVariable long id, @RequestBody Todo todo) {
        todo.setUsername(username);
        Todo savedTodo = repository.save(todo);
        return new ResponseEntity<>(savedTodo, HttpStatus.OK);
    }

    @PostMapping("/jpa/users/{username}/todos")
    public ResponseEntity<Todo> insertTodo(@PathVariable String username, @RequestBody Todo todo) {
        todo.setUsername(username);
        Todo savedTodo = repository.save(todo);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(savedTodo.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
