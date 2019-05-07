package ru.zagorodnikova.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.Project;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface IProjectEndpoint {

    @WebMethod
    @Nullable
    Project persistProject(
            @WebParam(name = "userId") @NotNull final String userId,
            @WebParam(name = "name") @Nullable final String name,
            @WebParam(name = "description") @Nullable final String description,
            @WebParam(name = "dateStart") @Nullable final String dateStart,
            @WebParam(name = "dateFinish") @Nullable final String dateFinish
    ) throws Exception;

    @WebMethod
    void removeProject(
            @WebParam(name = "projectId") @NotNull final String projectId
    );

    @WebMethod
    @Nullable
    List<Project> findAllProjects(@WebParam(name = "userId") @NotNull final String userId);

    @WebMethod
    @Nullable
    Project findOneProject(@WebParam(name = "projectId") @NotNull final String projectId);

    @WebMethod
    Project mergeProject(
            @WebParam(name = "projectId") @NotNull final String projectId,
            @WebParam(name = "name") @NotNull final String name,
            @WebParam(name = "description") @NotNull final String description,
            @WebParam(name = "dateStart") @NotNull final String dateStart,
            @WebParam(name = "dateFinish") @NotNull final String dateFinish,
            @WebParam(name = "status") @NotNull final String status) throws Exception;
}
