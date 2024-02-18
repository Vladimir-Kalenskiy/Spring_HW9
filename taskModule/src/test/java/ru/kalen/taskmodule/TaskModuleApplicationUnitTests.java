package ru.kalen.taskmodule;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.kalen.taskmodule.model.Task;
import ru.kalen.taskmodule.model.impl.TaskStatus;
import ru.kalen.taskmodule.repository.TaskRepository;
import ru.kalen.taskmodule.service.TaskService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class TaskModuleApplicationUnitTests {

	@Test
	@DisplayName("Unit test for get all tasks.")
	public void getAllTasksUnitTest() {
		TaskRepository taskRepository = mock(TaskRepository.class);
		TaskService taskService = new TaskService(taskRepository);

		List<Task> taskList = List.of(new Task());

		given(taskRepository.findAll()).willReturn(taskList);

		List<Task> taskListTest = taskService.getAllTask();

		verify(taskRepository).findAll();

		Assertions.assertEquals(taskList.size(),taskListTest.size());
	}

	@Test
	@DisplayName("Unit test for add task.")
	public void addTaskUnitTest(){
		TaskRepository taskRepository = mock(TaskRepository.class);
		TaskService taskService = new TaskService(taskRepository);

		Task taskTest = new Task();
		taskTest.setId(1);
		taskTest.setDescription("Проверить метод юнит тестом.");
		taskTest.setTaskStatus(TaskStatus.valueOf("IN_PROGRESS"));
		taskTest.setDateOfCreation(LocalDateTime.now());

		given(taskRepository.findById(taskTest.getId())).willReturn(Optional.of(taskTest));

		taskService.addTask(taskTest);

		verify(taskRepository).save(taskTest);

		Assertions.assertNotNull(taskRepository.findAll());
	}

	@Test
	@DisplayName("Unit test for delete task")
	public void deleteTaskUnitTest(){
		TaskRepository taskRepository = mock(TaskRepository.class);
		TaskService taskService = new TaskService(taskRepository);

		Task taskTest1 = new Task();
		taskTest1.setId(1);

		List<Task> taskList = new ArrayList<>();
		taskList.add(taskTest1);

		given(taskRepository.findById(taskTest1.getId())).willReturn(Optional.of(taskTest1));

		taskService.deleteTask(taskTest1.getId());

		verify(taskRepository).deleteById(taskTest1.getId());

		Assertions.assertTrue(taskRepository.findAll().isEmpty());
	}
}
