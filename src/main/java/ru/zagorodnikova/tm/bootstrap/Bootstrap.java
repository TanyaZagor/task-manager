package ru.zagorodnikova.tm.bootstrap;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.api.service.IProjectService;
import ru.zagorodnikova.tm.api.service.ITaskService;
import ru.zagorodnikova.tm.api.service.IUserService;

@Component
@NoArgsConstructor
public class Bootstrap implements ServiceLocator {

    @Autowired
    private IProjectService projectService;

    @Autowired
    private ITaskService taskService;

    @Autowired
    private IUserService userService;

    public void init() throws Exception {
//        User user1 = userService.signUp("test", "test", "first", "last", "email");
//        User user2 = userService.signUp("admin", "admin", "first", "last", "email");
//
//        Project project1 = projectService.persist(user1.getId(), "name1", "des", "12.02.2012", "12.02.2013");
//        Project project2 = projectService.persist(user1.getId(), "name2", "des", "12.02.2012", "12.02.2013");
//        Project project3 = projectService.persist(user2.getId(), "name3", "des", "12.02.2012", "12.02.2013");
//
//        Task task1 = taskService.persist(project1.getId(), "task1", "des1", "12.02.2012", "12.02.2013");
//        Task task2 = taskService.persist(project1.getId(), "task2", "des2", "12.02.2012", "12.02.2013");
//        Task task3 = taskService.persist(project2.getId(), "task1", "des1", "12.02.2012", "12.02.2013");
//        Task task4 = taskService.persist(project2.getId(), "task2", "des2", "12.02.2012", "12.02.2013");
//        Task task5 = taskService.persist(project3.getId(), "task1", "des1", "12.02.2012", "12.02.2013");
//        Task task6 = taskService.persist(project3.getId(), "task2", "des2", "12.02.2012", "12.02.2013");
    }
}
