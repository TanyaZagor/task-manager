package ru.zagorodnikova.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.zagorodnikova.tm.entity.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, String> {

    @Nullable
    @Modifying
    Project save(@NotNull final Project project);

    void delete(@NotNull final Project project);

    @Modifying
    @Query(value = "DELETE FROM Project project WHERE project.userId = :userId")
    void removeAll(@NotNull @Param("userId")  final String userId);

    Optional<Project> findById(@NotNull final String id);

    @Nullable
    @Query(value = "SELECT project FROM Project project WHERE project.userId = :userId")
    List<Project> findAllByUserId(@NotNull @Param("userId")  final String userId);

    @Nullable
    List<Project> findAll();
}
