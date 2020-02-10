package name.nicholasgribanov.restforweb.controllers;

import name.nicholasgribanov.restforweb.model.Todo;
import name.nicholasgribanov.restforweb.service.TodoService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
