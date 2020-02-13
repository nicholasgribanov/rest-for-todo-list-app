package name.nicholasgribanov.restforweb.service;

import name.nicholasgribanov.restforweb.model.Todo;

import java.util.List;

public interface TodoService {

    List<Todo> findAll();
    List<Todo> findAllByUsername(String username);
    Todo deleteById(long id);
    Todo save(Todo todo);
}
