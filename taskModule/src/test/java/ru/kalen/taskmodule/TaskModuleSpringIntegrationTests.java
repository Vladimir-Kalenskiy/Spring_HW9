package ru.kalen.taskmodule;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.kalen.taskmodule.model.Task;
import ru.kalen.taskmodule.model.impl.TaskStatus;
import ru.kalen.taskmodule.repository.TaskRepository;
import ru.kalen.taskmodule.service.TaskService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TaskModuleSpringIntegrationTests {

    @MockBean
    private TaskRepository taskRepository;

    @Autowired
    private TaskService taskService;

    @Test
    public void getAllTasksIntegrationTest(){
        List<Task> taskList = List.of(new Task());

        when(taskRepository.findAll()).thenReturn(taskList);

        List<Task> taskListTest = taskService.getAllTask();

        verify(taskRepository).findAll();

        Assertions.assertEquals(taskList.size(),taskListTest.size());
    }

    @Test
    public void addTaskIntegrationTest(){
        Task taskTest = new Task();
        taskTest.setId(1);
        taskTest.setDescription("Проверить метод юнит тестом.");
        taskTest.setTaskStatus(TaskStatus.valueOf("IN_PROGRESS"));
        taskTest.setDateOfCreation(LocalDateTime.now());

        when(taskRepository.findById(taskTest.getId())).thenReturn(Optional.of(taskTest));

        taskService.addTask(taskTest);

        verify(taskRepository).save(taskTest);

        Assertions.assertNotNull(taskRepository.findAll());
    }

    @Test
    public void deleteTaskIntegrationtTest(){
        Task taskTest1 = new Task();
        taskTest1.setId(1);

        List<Task> taskList = new ArrayList<>();
        taskList.add(taskTest1);

        when(taskRepository.findById(taskTest1.getId())).thenReturn(Optional.of(taskTest1));

        taskService.deleteTask(taskTest1.getId());

        verify(taskRepository).deleteById(taskTest1.getId());

        Assertions.assertTrue(taskRepository.findAll().isEmpty());
    }
}
