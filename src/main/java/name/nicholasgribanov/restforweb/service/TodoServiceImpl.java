package name.nicholasgribanov.restforweb.service;

import name.nicholasgribanov.restforweb.model.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService {
    private static int counter = 0;

    private static List<Todo> todos = new ArrayList<>();

    static {
        todos.add(new Todo(++counter, "Igor", "Learn Java", new Date(), false));
        todos.add(new Todo(++counter, "Igor", "Learn Angular", new Date(), false));
        todos.add(new Todo(++counter, "Igor", "Learn React", new Date(), false));
        todos.add(new Todo(++counter, "Sam", "Learn React", new Date(), false));

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

    @Override
    public Todo save(Todo todo) {
        if (todo.getId() == -1 || todo.getId() == 0) {
            todo.setId(++counter);
            todos.add(todo);
        } else {
            todos.remove(todo);
            todos.add(todo);
        }
        return todo;
    }
}
