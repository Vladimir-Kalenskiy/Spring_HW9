package ru.kalen.taskmodule.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import ru.kalen.taskmodule.model.impl.TaskStatus;

import java.time.LocalDateTime;

/**
 * Сущность "задача"
 */
@Entity
@Data
@Table(name = "tasks")
public class Task {
    /**
     * Идентификатор задачи
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    /**
     * Описание задачи
     */
    @Column(nullable = false)
    private String description;
    /**
     * Статус выполнения задачи
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "task_status")
    private TaskStatus taskStatus;
    /**
     * Дата и время создания задачи
     */
    @CreationTimestamp
    @Column(name = "date_of_creation")
    private LocalDateTime dateOfCreation;

}
