package name.nicholasgribanov.restforweb.service;

import name.nicholasgribanov.restforweb.model.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService {

    private static List<Todo> todos = new ArrayList<>();

    static {
        todos.add(new Todo(1, "Igor", "Learn Java", new Date(), false));
        todos.add(new Todo(2, "Igor", "Learn Angular", new Date(), false));
        todos.add(new Todo(3, "Igor", "Learn React", new Date(), false));
        todos.add(new Todo(4, "Sam", "Learn React", new Date(), false));

    }

    @Override
    public List<Todo> findAll() {
        return todos;
    }

    @Override
    public List<Todo> findAllByUsername(String username) {
        return todos.stream().filter(td -> td.getUsername().equals(username)).collect(Collectors.toList());
    }

    @Override
    public Todo deleteById(long id) {
        Todo todoForDelete = todos.stream().filter(todo -> todo.getId() == id).findFirst().orElse(null);
        if (todoForDelete != null)
            todos.remove(todoForDelete);
        return todoForDelete;
    }
}