package ru.kalen.taskmodule.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kalen.taskmodule.aspects.TrackUserAction;
import ru.kalen.taskmodule.model.Task;
import ru.kalen.taskmodule.model.impl.TaskStatus;
import ru.kalen.taskmodule.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

/**
 * Класс сервиса задач
 */
@Service
@AllArgsConstructor
public class TaskService {

    /**
     * Объект репозитория
     */
    private final TaskRepository repository;

    /**
     * Метод добавления задачи
     * @param task задача
     */
    @TrackUserAction
    public void addTask(Task task){
        repository.save(task);
    }

    /**
     * Метод получения списка всех задач
     * @return список задач
     */
    @TrackUserAction
    public List<Task> getAllTask(){
        return repository.findAll();
    }

    /**
     * Метод получения списка задач по статусу
     * @param taskStatus статус задач
     * @return список задач отфильтрованных по статусу выполнения
     */
    public List<Task> getTasksByStatus(TaskStatus taskStatus){
        return repository.findAllByTaskStatus(taskStatus);
    }

    /**
     * Метод обновления статуса задачи
     * @param id идентификатор задачи
     * @param taskDetails задача в которой необходимо изменить статус
     * @return задача с обновленным статусом
     */
    @TrackUserAction
    public Task updateTaskStatus(Long id, Task taskDetails){
        Optional<Task> optionalTask = repository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setTaskStatus(taskDetails.getTaskStatus());
            return repository.save(task);
        } else {
            throw new IllegalArgumentException("Task not found with id: " + id);
        }
    }

    /**
     * Метод удаления задачи по id
     * @param id идентификатор задачи
     */
    @TrackUserAction
    public void deleteTask(Long id){
        repository.deleteById(id);
    }
}
