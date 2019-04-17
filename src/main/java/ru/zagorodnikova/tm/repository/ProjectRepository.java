package ru.zagorodnikova.tm.repository;


import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;
import ru.zagorodnikova.tm.api.repository.IProjectRepository;
import ru.zagorodnikova.tm.entity.Project;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@NoArgsConstructor
public class ProjectRepository implements IProjectRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Nullable
    @Override
    public Project persist(@NotNull final Project project) {
        entityManager.persist(project);
        return project;
    }

    @Override
    public void remove(@NotNull final Project project) {
        entityManager.remove(project);
    }

    @Override
    public void removeAll(@NotNull final String userId) {
        entityManager.createQuery("DELETE FROM Project project WHERE project.userId = :userId")
                .setParameter("userId", userId)
                .executeUpdate();
    }

    @Nullable
    @Override
    public Project findOne(@NotNull final String id) {
        return entityManager.find(Project.class, id);
    }

    @Override
    public void merge(@NotNull final Project project) {
        entityManager.merge(project);
    }

    @Override
    public @Nullable List<Project> findAll(@NotNull final String userId) {
        return entityManager.createQuery("SELECT project FROM Project project WHERE project.userId = :userId", Project.class)
                .setParameter("userId",userId)
                .getResultList();
    }

    @Override
    public @Nullable List<Project> getProjects() {
        return entityManager.createQuery("SELECT project FROM Project project", Project.class)
                .getResultList();
    }
}
