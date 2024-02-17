package ru.kalen.taskmodule.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kalen.taskmodule.model.Task;
import ru.kalen.taskmodule.model.impl.TaskStatus;
import ru.kalen.taskmodule.service.TaskService;

import java.util.List;

/**
 * Контроллер задач
 */
@RestController
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {
    private final TaskService service;

    /**
     * Метод добавления задачи
     * @param task задача
     */
    @PostMapping
    public void addTask(@RequestBody Task task){
        service.addTask(task);
    }

    /**
     * Метод получения списка всех задач
     * @return список задач
     */
    @GetMapping("/")
    public List<Task> getAllTasks(){
        return service.getAllTask();
    }

    /**
     * Метод получения списка задач по статусу выполнения
     * @param status статус выполнения задачи
     * @return список задач отфильтрованных по статусу
     */
    @GetMapping("/status/{status}")
    public List<Task> getTasksByStatus(@PathVariable TaskStatus status){
        return service.getTasksByStatus(status);
    }

    /**
     * Метод обновления задачи
     * @param id идентификатор задачи
     * @param task задача
     * @return обновленная задача
     */
    @PutMapping("/{id}")
    public Task updateTaskStatus(@PathVariable Long id, @RequestBody Task task){
        return service.updateTaskStatus(id, task);
    }

    /**
     * Метод удаления задачи по id
     * @param id идентификатор задачи
     */
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
        service.deleteTask(id);
    }
}
