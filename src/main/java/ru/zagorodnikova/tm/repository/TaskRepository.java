package ru.zagorodnikova.tm.repository;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;
import ru.zagorodnikova.tm.api.repository.ITaskRepository;
import ru.zagorodnikova.tm.entity.Task;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
@NoArgsConstructor
public class TaskRepository implements ITaskRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Nullable
    @Override
    public Task persist(@NotNull Task task) {
        entityManager.persist(task);
        return task;
    }

    @Override
    public void remove(@NotNull final Task task) {
        entityManager.remove(task);
    }

    @Override
    public void removeAllInProject(@NotNull String projectId) {
        entityManager.createQuery("DELETE FROM Task task WHERE task.projectId = :projectId")
                .setParameter("projectId", projectId)
                .executeUpdate();
    }

    @Override
    public void removeAll(@NotNull String userId) {
        entityManager.createQuery("DELETE FROM Task task WHERE task.userId = :userId")
                .setParameter("userId", userId)
                .executeUpdate();
    }

    @Nullable
    @Override
    public Task findOne(@NotNull String id) {
        return entityManager.find(Task.class, id);
    }

    @Override
    public void merge(@NotNull Task task) {
        entityManager.merge(task);
    }

    @Override
    @Nullable
    public List<Task> findAllInProject(@NotNull String projectId) {
        return entityManager.createQuery("SELECT task FROM Task task WHERE task.projectId = :projectId", Task.class)
                .setParameter("projectId", projectId)
                .getResultList();
    }

    @Override
    public @Nullable List findAll() {
        return entityManager.createQuery("SELECT task FROM Task task", Task.class)
                .getResultList();
    }

}
