package com.flexspring.springboot.myfirstwebapp.todo;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoService {

    private static List<Todo> todos = new ArrayList<>();
    private static int todosCount = 0;

    static {
        todos.add(new Todo(++todosCount, "Harsheen", "Learn Aws", LocalDate.now().plusYears(1), false));
        todos.add(new Todo(++todosCount, "Harsheen", "Learn DevOps", LocalDate.now().plusYears(2), false));
        todos.add(new Todo(++todosCount, "Harsheen", "Learn Full Stack Development", LocalDate.now().plusYears(3), false));
    }

    public List<Todo> findByUsername(String username){
        return todos;
    }

    public void addTodo(String userName, String description, LocalDate targetDate, boolean done){
        Todo todo = new Todo(++todosCount, userName, description, targetDate, done);
        todos.add(todo);
    }

    public void deleteById(int id){

        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        todos.removeIf(predicate);
    }

    public Todo findById(int id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        Todo todo = todos.stream().filter(predicate).findFirst().get();
        return todo;
    }

    public void updateTodo(Todo todo) {
        deleteById(todo.getId());
        todos.add(todo);
    }
}
