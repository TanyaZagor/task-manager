package ru.zagorodnikova.tm.bootstrap;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.api.repository.IProjectRepository;
import ru.zagorodnikova.tm.api.repository.ITaskRepository;
import ru.zagorodnikova.tm.api.repository.IUserRepository;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.entity.Task;
import ru.zagorodnikova.tm.entity.User;
import ru.zagorodnikova.tm.repository.ProjectRepository;
import ru.zagorodnikova.tm.repository.TaskRepository;
import ru.zagorodnikova.tm.repository.UserRepository;

import java.util.Date;

@Component
@NoArgsConstructor
public class Bootstrap implements ServiceLocator {

    @Autowired
    private IProjectRepository projectRepository;

    @Autowired
    private ITaskRepository taskRepository;

    @Autowired
    private IUserRepository userRepository;

    public void init() throws Exception {
        User user1 = new User("test", "test", "first", "last", "email");
        User user2 = new User("admin", "admin", "first", "last", "email");
        userRepository.signUp(user1);
        userRepository.signUp(user2);

        Project project1 = new Project(user1.getId(), "name1", "des", new Date(), new Date());
        Project project2 = new Project(user1.getId(), "name2", "des", new Date(), new Date());
        Project project3 = new Project(user2.getId(), "name3", "des", new Date(), new Date());
        projectRepository.persist(project1);
        projectRepository.persist(project2);
        projectRepository.persist(project3);

        Task task1 = new Task(user1.getId(), project1.getId(), "task1", "des1", new Date(), new Date());
        Task task2 = new Task(user1.getId(), project1.getId(), "task2", "des2", new Date(), new Date());
        Task task3 = new Task(user1.getId(), project2.getId(), "task1", "des1", new Date(), new Date());
        Task task4 = new Task(user1.getId(), project2.getId(), "task2", "des2", new Date(), new Date());
        Task task5 = new Task(user2.getId(), project3.getId(), "task1", "des1", new Date(), new Date());
        Task task6 = new Task(user2.getId(), project3.getId(), "task2", "des2", new Date(), new Date());

        taskRepository.persist(task1);
        taskRepository.persist(task2);
        taskRepository.persist(task3);
        taskRepository.persist(task4);
        taskRepository.persist(task5);
        taskRepository.persist(task6);
    }
}
