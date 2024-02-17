create table tasks (
                         id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                         description VARCHAR(255) NOT NULL,
                         task_status VARCHAR(45) NOT NULL,
                         date_of_creation TIMESTAMP
);