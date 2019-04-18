package ru.zagorodnikova.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.enumeration.Status;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;


@Getter
@Setter
@Entity
@Cacheable
@NoArgsConstructor
@Table(name = "app_task")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Task {

    @Id
    @NotNull
    private String id = UUID.randomUUID().toString();

    @Nullable
    private String userId;

    @Nullable
    private String projectId;

    @Nullable
    private String name;

    @Nullable
    private String description;

    @Nullable
    private Date dateStart;

    @Nullable
    private Date dateFinish;

    @NotNull
    private Date dateCreate = new Date();

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status = Status.SCHEDULED;

    public Task(@NotNull String userId, @NotNull String projectId, @NotNull String name,
                @NotNull String  description, @NotNull Date dateStart, @NotNull Date dateFinish) {
        this.userId = userId;
        this.name = name;
        this.projectId = projectId;
        this.description = description;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Task task = (Task) o;
        return projectId.equals(task.projectId) &&
                name.equals(task.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, name);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", projectId='" + projectId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dateStart=" + dateStart +
                ", dateFinish=" + dateFinish +
                ", dateCreate=" + dateCreate +
                ", status=" + status +
                '}';
    }
}
