package ru.kalen.taskmodule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kalen.taskmodule.model.Task;
import ru.kalen.taskmodule.model.impl.TaskStatus;

import java.util.List;

/**
 * Репозиторий задач
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    /**
     * Метод получения списка задач по статусу выполнения
     * @param status статус выполнения задачи
     * @return список задач отфильтрованных по статусу
     */
    List<Task> findAllByTaskStatus(TaskStatus status);

}
