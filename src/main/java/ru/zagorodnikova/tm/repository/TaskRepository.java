package ru.zagorodnikova.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.zagorodnikova.tm.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, String> {

    @Nullable
    Task save(@NotNull Task task);

    void delete(@NotNull Task task);

    @Modifying
    @Query(value = "DELETE FROM Task task WHERE task.projectId = :projectId")
    void removeAllInProject(@NotNull @Param("projectId") String projectId);

    @Modifying
    @Query(value = "DELETE FROM Task task WHERE task.userId = :userId")
    void removeAll(@NotNull @Param("userId") String userId);

    Optional<Task> findById(@NotNull String id);

    @Nullable
    @Query(value = "SELECT task FROM Task task WHERE task.projectId = :projectId")
    List<Task> findAllInProject(@NotNull @Param("projectId") String projectId);

    @Nullable
    List<Task> findAll();
}
