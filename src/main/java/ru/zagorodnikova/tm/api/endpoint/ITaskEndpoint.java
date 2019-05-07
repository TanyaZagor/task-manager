package ru.zagorodnikova.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.Task;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface ITaskEndpoint {

    @WebMethod
    @Nullable
    Task persistTask(@WebParam(name = "projectId") @NotNull final String projectId,
                     @WebParam(name = "name") @NotNull final String taskName,
                     @WebParam(name = "description") @NotNull final String description,
                     @WebParam(name = "dateStart") @NotNull final String dateStart,
                     @WebParam(name = "dateFinish") @NotNull final String dateFinish) throws Exception;

    @WebMethod
    void removeTask(@WebParam(name = "taskId") @NotNull final String taskId);

    @WebMethod
    void removeAllTasksInProject(@WebParam(name = "projectId") @NotNull final String projectId);

    @WebMethod
    Task mergeTask(@WebParam(name = "taskId") @NotNull final String taskId,
                   @WebParam(name = "name") @NotNull final String name,
                   @WebParam(name = "description") @NotNull final String description,
                   @WebParam(name = "dateStart") @NotNull final String dateStart,
                   @WebParam(name = "dateFinish") @NotNull final String dateFinish,
                   @WebParam(name = "status") @NotNull final String status) throws Exception;

    @WebMethod
    @Nullable
    List<Task> findAllTasksInProject(@WebParam(name = "projectId") @NotNull final String projectId);

    @WebMethod
    @Nullable
    Task findOneTask(@WebParam(name = "taskId") @NotNull final String taskId);
}