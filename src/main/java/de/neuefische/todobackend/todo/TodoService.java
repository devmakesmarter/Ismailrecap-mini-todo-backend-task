package de.neuefische.todobackend.todo;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import de.neuefische.todobackend.ExceptionHandler.InvalidInputException;
import de.neuefische.todobackend.OpenAiChatGpt.OpenAiService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TodoService {


    private final TodoRepository todoRepository;
    private final IdService idService;
    private final OpenAiService openAiService;

    public TodoService(TodoRepository todoRepository, IdService idService,OpenAiService openAiService) {
        this.todoRepository = todoRepository;
        this.idService = idService;
        this.openAiService = openAiService;
    }

    public List<Todo> findAllTodos() {
        return todoRepository.findAll();
    }

    public Todo addTodo(NewTodo newTodo) {
        String id = idService.randomId();

        String jobdetails ="Bitte prüfe nur den Text nach dem Doppelpunkt und gebe den Text danach ohne Rechtschreibefehler zurück. Nur den Text und wenn es nur ein Wort ist, dann das Wort" + " : " +newTodo.description();
        String descriptionCorrect = openAiService.requestToChatGpt(jobdetails);

        Todo todoToSave = new Todo(id, descriptionCorrect, newTodo.status());

        if(newTodo.description() == null || newTodo.status() == null){
            throw new InvalidInputException("Einer der Werte ist null");
        }

        return todoRepository.save(todoToSave);
    }

    public Todo updateTodo(UpdateTodo todo, String id) {
        Todo todoToUpdate = new Todo(id, todo.description(), todo.status());

        return todoRepository.save(todoToUpdate);

    }

    public Todo findTodoById(String id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Todo with id: " + id + " not found!"));
    }

    public void deleteTodo(String id) {
        todoRepository.deleteById(id);
    }
}
