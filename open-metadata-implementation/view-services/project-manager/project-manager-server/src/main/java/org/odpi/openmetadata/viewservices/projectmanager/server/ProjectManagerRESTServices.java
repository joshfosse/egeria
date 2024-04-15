/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project */
/* Copyright Contributors to the ODPi Egeria category. */
package org.odpi.openmetadata.viewservices.projectmanager.server;

import org.odpi.openmetadata.accessservices.projectmanagement.client.ProjectManagement;
import org.odpi.openmetadata.accessservices.projectmanagement.properties.*;
import org.odpi.openmetadata.commonservices.ffdc.RESTCallLogger;
import org.odpi.openmetadata.commonservices.ffdc.RESTCallToken;
import org.odpi.openmetadata.commonservices.ffdc.RESTExceptionHandler;
import org.odpi.openmetadata.commonservices.ffdc.rest.*;
import org.odpi.openmetadata.frameworks.auditlog.AuditLog;
import org.odpi.openmetadata.frameworks.governanceaction.mapper.OpenMetadataType;
import org.odpi.openmetadata.tokencontroller.TokenController;
import org.odpi.openmetadata.viewservices.projectmanager.rest.*;
import org.slf4j.LoggerFactory;


/**
 * The ProjectManagerRESTServices provides the implementation of the Project Manager Open Metadata View Service (OMVS).
 */

public class ProjectManagerRESTServices extends TokenController
{
    private static final ProjectManagerInstanceHandler instanceHandler = new ProjectManagerInstanceHandler();

    private static final RESTExceptionHandler restExceptionHandler = new RESTExceptionHandler();

    private static final RESTCallLogger restCallLogger = new RESTCallLogger(LoggerFactory.getLogger(ProjectManagerRESTServices.class),
                                                                            instanceHandler.getServiceName());


    /**
     * Default constructor
     */
    public ProjectManagerRESTServices()
    {
    }

    /* =====================================================================================================================
     * Project Management methods
     */

    /**
     * Returns the list of projects that are linked off of the supplied element.
     *
     * @param serverName     name of called server
     * @param parentGUID     unique identifier of referenceable object (typically a personal profile, project or
     *                       community) that the projects hang off of
     * @param startFrom      index of the list to start from (0 for start)
     * @param pageSize       maximum number of elements to return
     * @param requestBody filter response by project status - if null, any value will do
     *
     * @return a list of projects
     *  InvalidParameterException  one of the parameters is null or invalid.
     *  PropertyServerException    there is a problem retrieving information from the property server(s).
     *  UserNotAuthorizedException the requesting user is not authorized to issue this request.
     */
    public ProjectListResponse getLinkedProjects(String            serverName,
                                                 String            parentGUID,
                                                 int               startFrom,
                                                 int               pageSize,
                                                 FilterRequestBody requestBody)
    {
        final String methodName = "getLinkedProjects";

        RESTCallToken token = restCallLogger.logRESTCall(serverName, methodName);

        ProjectListResponse response = new ProjectListResponse();
        AuditLog            auditLog = null;

        try
        {
            String userId = super.getUser(instanceHandler.getServiceName(), methodName);

            restCallLogger.setUserId(token, userId);

            auditLog = instanceHandler.getAuditLog(userId, serverName, methodName);

            ProjectManagement handler = instanceHandler.getProjectManagement(userId, serverName, methodName);

            response.setElements(handler.getLinkedProjects(userId, parentGUID, requestBody.getFilter(), startFrom, pageSize));
        }
        catch (Exception error)
        {
            restExceptionHandler.captureExceptions(response, error, methodName, auditLog);
        }

        restCallLogger.logRESTCallReturn(token, response.toString());
        return response;
    }


    /**
     * Returns the list of actors that are linked off of the project.
     *
     * @param serverName     name of called server
     * @param projectGUID     unique identifier of the project
     * @param startFrom      index of the list to start from (0 for start)
     * @param pageSize       maximum number of elements to return
     * @param requestBody    filter response by team role
     *
     * @return a list of projects
     *  InvalidParameterException  one of the parameters is null or invalid.
     *  PropertyServerException    there is a problem retrieving information from the property server(s).
     *  UserNotAuthorizedException the requesting user is not authorized to issue this request.
     */
    public ProjectMemberListResponse getProjectTeam(String            serverName,
                                                    String            projectGUID,
                                                    int               startFrom,
                                                    int               pageSize,
                                                    FilterRequestBody requestBody)
    {
        final String methodName = "getProjectTeam";

        RESTCallToken token = restCallLogger.logRESTCall(serverName, methodName);

        ProjectMemberListResponse response = new ProjectMemberListResponse();
        AuditLog                  auditLog = null;

        try
        {
            String userId = super.getUser(instanceHandler.getServiceName(), methodName);

            restCallLogger.setUserId(token, userId);

            auditLog = instanceHandler.getAuditLog(userId, serverName, methodName);

            ProjectManagement handler = instanceHandler.getProjectManagement(userId, serverName, methodName);

            response.setElements(handler.getProjectMembers(userId, projectGUID, requestBody.getFilter(), startFrom, pageSize));
        }
        catch (Exception error)
        {
            restExceptionHandler.captureExceptions(response, error, methodName, auditLog);
        }

        restCallLogger.logRESTCallReturn(token, response.toString());
        return response;
    }


    /**
     * Returns the list of projects with a particular classification.
     *
     * @param serverName         name of called server
     * @param requestBody        name of the classification - if null, all projects are returned
     * @param startFrom          index of the list to start from (0 for start)
     * @param pageSize           maximum number of elements to return
     *
     * @return a list of projects
     *  InvalidParameterException  one of the parameters is null or invalid.
     *  PropertyServerException    there is a problem retrieving information from the property server(s).
     *  UserNotAuthorizedException the requesting user is not authorized to issue this request.
     */
    public ProjectListResponse getClassifiedProjects(String            serverName,
                                                     int               startFrom,
                                                     int               pageSize,
                                                     FilterRequestBody requestBody)
    {
        final String methodName = "getClassifiedProjects";

        RESTCallToken token = restCallLogger.logRESTCall(serverName, methodName);

        ProjectListResponse response = new ProjectListResponse();
        AuditLog            auditLog = null;

        try
        {
            String userId = super.getUser(instanceHandler.getServiceName(), methodName);

            restCallLogger.setUserId(token, userId);

            auditLog = instanceHandler.getAuditLog(userId, serverName, methodName);

            ProjectManagement handler = instanceHandler.getProjectManagement(userId, serverName, methodName);

            response.setElements(handler.getClassifiedProjects(userId, requestBody.getFilter(), startFrom, pageSize));
        }
        catch (Exception error)
        {
            restExceptionHandler.captureExceptions(response, error, methodName, auditLog);
        }

        restCallLogger.logRESTCallReturn(token, response.toString());
        return response;
    }
    

    /**
     * Returns the list of projects matching the search string - this is coded as a regular expression.
     *
     * @param serverName name of the service to route the request to
     * @param startsWith does the value start with the supplied string?
     * @param endsWith does the value end with the supplied string?
     * @param ignoreCase should the search ignore case?
     * @param startFrom paging start point
     * @param pageSize maximum results that can be returned
     * @param requestBody string to find in the properties
     *
     * @return a list of projects
     *  InvalidParameterException  one of the parameters is null or invalid.
     *  PropertyServerException    there is a problem retrieving information from the property server(s).
     *  UserNotAuthorizedException the requesting user is not authorized to issue this request.
     */
    public ProjectListResponse findProjects(String            serverName,
                                            boolean           startsWith,
                                            boolean           endsWith,
                                            boolean           ignoreCase,
                                            int               startFrom,
                                            int               pageSize,
                                            FilterRequestBody requestBody)
    {
        final String methodName = "findProjects";

        RESTCallToken token = restCallLogger.logRESTCall(serverName, methodName);

        ProjectListResponse response = new ProjectListResponse();
        AuditLog            auditLog = null;

        try
        {
            String userId = super.getUser(instanceHandler.getServiceName(), methodName);

            restCallLogger.setUserId(token, userId);

            auditLog = instanceHandler.getAuditLog(userId, serverName, methodName);

            ProjectManagement handler = instanceHandler.getProjectManagement(userId, serverName, methodName);

            response.setElements(handler.findProjects(userId,
                                                      instanceHandler.getSearchString(requestBody.getFilter(), startsWith, endsWith, ignoreCase),
                                                      startFrom,
                                                      pageSize));
        }
        catch (Exception error)
        {
            restExceptionHandler.captureExceptions(response, error, methodName, auditLog);
        }

        restCallLogger.logRESTCallReturn(token, response.toString());
        return response;
    }


    /**
     * Returns the list of projects with a particular name.
     *
     * @param serverName    name of called server
     * @param requestBody      name of the projects to return - match is full text match in qualifiedName or name
     * @param startFrom index of the list to start from (0 for start)
     * @param pageSize  maximum number of elements to return
     *
     * @return a list of projects
     *  InvalidParameterException  one of the parameters is null or invalid.
     *  PropertyServerException    there is a problem retrieving information from the property server(s).
     *  UserNotAuthorizedException the requesting user is not authorized to issue this request.
     */
    public ProjectListResponse getProjectsByName(String            serverName,
                                                 int               startFrom,
                                                 int               pageSize,
                                                 FilterRequestBody requestBody)
    {
        final String methodName = "getProjectsByName";

        RESTCallToken token = restCallLogger.logRESTCall(serverName, methodName);

        ProjectListResponse response = new ProjectListResponse();
        AuditLog            auditLog = null;

        try
        {
            String userId = super.getUser(instanceHandler.getServiceName(), methodName);

            restCallLogger.setUserId(token, userId);

            auditLog = instanceHandler.getAuditLog(userId, serverName, methodName);

            ProjectManagement handler = instanceHandler.getProjectManagement(userId, serverName, methodName);

            response.setElements(handler.getProjectsByName(userId,
                                                           requestBody.getFilter(),
                                                           startFrom,
                                                           pageSize));
        }
        catch (Exception error)
        {
            restExceptionHandler.captureExceptions(response, error, methodName, auditLog);
        }

        restCallLogger.logRESTCallReturn(token, response.toString());
        return response;
    }



    /**
     * Return the properties of a specific project.
     *
     * @param serverName         name of called server
     * @param projectGUID unique identifier of the required project
     *
     * @return project properties
     *  InvalidParameterException  one of the parameters is null or invalid.
     *  PropertyServerException    there is a problem retrieving information from the property server(s).
     *  UserNotAuthorizedException the requesting user is not authorized to issue this request.
     */
    public ProjectResponse getProject(String serverName,
                                      String projectGUID)
    {
        final String methodName = "getProject";

        RESTCallToken token = restCallLogger.logRESTCall(serverName, methodName);

        ProjectResponse response = new ProjectResponse();
        AuditLog        auditLog = null;

        try
        {
            String userId = super.getUser(instanceHandler.getServiceName(), methodName);

            restCallLogger.setUserId(token, userId);

            auditLog = instanceHandler.getAuditLog(userId, serverName, methodName);

            ProjectManagement handler = instanceHandler.getProjectManagement(userId, serverName, methodName);

            response.setElement(handler.getProjectByGUID(userId, projectGUID));
        }
        catch (Exception error)
        {
            restExceptionHandler.captureExceptions(response, error, methodName, auditLog);
        }

        restCallLogger.logRESTCallReturn(token, response.toString());
        return response;
    }


    /**
     * Create a new generic project.
     *
     * @param serverName                 name of called server.
     * @param optionalClassificationName name of project classification
     * @param requestBody             properties for the project.
     *
     * @return unique identifier of the newly created Project
     *  InvalidParameterException  one of the parameters is invalid.
     *  PropertyServerException    there is a problem retrieving information from the property server(s).
     *  UserNotAuthorizedException the requesting user is not authorized to issue this request.
     */
    public GUIDResponse createProject(String                   serverName,
                                      String                   optionalClassificationName,
                                      NewProjectRequestBody requestBody)
    {
        final String methodName = "createProject";

        RESTCallToken token = restCallLogger.logRESTCall(serverName, methodName);

        GUIDResponse response = new GUIDResponse();
        AuditLog     auditLog = null;

        try
        {
            String userId = super.getUser(instanceHandler.getServiceName(), methodName);

            restCallLogger.setUserId(token, userId);

            auditLog = instanceHandler.getAuditLog(userId, serverName, methodName);

            if (requestBody != null)
            {
                ProjectManagement handler = instanceHandler.getProjectManagement(userId, serverName, methodName);

                response.setGUID(handler.createProject(userId,
                                                       requestBody.getAnchorGUID(),
                                                       requestBody.getIsOwnAnchor(),
                                                       optionalClassificationName,
                                                       requestBody.getProjectProperties(),
                                                       requestBody.getParentGUID(),
                                                       requestBody.getParentRelationshipTypeName(),
                                                       requestBody.getParentRelationshipProperties(),
                                                       requestBody.getParentAtEnd1()));
            }
            else
            {
                restExceptionHandler.handleNoRequestBody(userId, methodName, serverName);
            }
        }
        catch (Exception error)
        {
            restExceptionHandler.captureExceptions(response, error, methodName, auditLog);
        }

        restCallLogger.logRESTCallReturn(token, response.toString());
        return response;
    }


    /**
     * Create a new project with the Task classification.  Used to identify the top of a
     * project hierarchy.
     *
     * @param serverName                 name of called server.
     * @param projectGUID             unique identifier of the project
     * @param requestBody             properties for the project.
     *
     * @return unique identifier of the newly created Project
     *  InvalidParameterException  one of the parameters is invalid.
     *  PropertyServerException    there is a problem retrieving information from the property server(s).
     *  UserNotAuthorizedException the requesting user is not authorized to issue this request.
     */
    public GUIDResponse createTaskForProject(String            serverName,
                                             String            projectGUID,
                                             ProjectProperties requestBody)
    {
        final String methodName = "createTaskForProject";

        RESTCallToken token = restCallLogger.logRESTCall(serverName, methodName);

        GUIDResponse response = new GUIDResponse();
        AuditLog     auditLog = null;

        try
        {
            String userId = super.getUser(instanceHandler.getServiceName(), methodName);

            restCallLogger.setUserId(token, userId);

            auditLog = instanceHandler.getAuditLog(userId, serverName, methodName);

            if (requestBody != null)
            {
                ProjectManagement handler = instanceHandler.getProjectManagement(userId, serverName, methodName);

                response.setGUID(handler.createProject(userId,
                                                       projectGUID,
                                                       false,
                                                       OpenMetadataType.TASK_CLASSIFICATION.typeName,
                                                       requestBody,
                                                       projectGUID,
                                                       OpenMetadataType.PROJECT_HIERARCHY_RELATIONSHIP.typeName,
                                                       null,
                                                       true));
            }
            else
            {
                restExceptionHandler.handleNoRequestBody(userId, methodName, serverName);
            }
        }
        catch (Exception error)
        {
            restExceptionHandler.captureExceptions(response, error, methodName, auditLog);
        }

        restCallLogger.logRESTCallReturn(token, response.toString());
        return response;
    }


    /**
     * Create a new metadata element to represent a project using an existing metadata element as a template.
     * The template defines additional classifications and relationships that should be added to the new project.
     *
     * @param serverName             calling user
     * @param requestBody properties that override the template
     *
     * @return unique identifier of the new metadata element
     *  InvalidParameterException  one of the parameters is invalid
     *  UserNotAuthorizedException the user is not authorized to issue this request
     *  PropertyServerException    there is a problem reported in the open metadata server(s)
     */
    public GUIDResponse createProjectFromTemplate(String              serverName,
                                                  TemplateRequestBody requestBody)
    {
        final String methodName = "createProjectFromTemplate";

        RESTCallToken token = restCallLogger.logRESTCall(serverName, methodName);

        GUIDResponse response = new GUIDResponse();
        AuditLog     auditLog = null;

        try
        {
            String userId = super.getUser(instanceHandler.getServiceName(), methodName);

            restCallLogger.setUserId(token, userId);

            auditLog = instanceHandler.getAuditLog(userId, serverName, methodName);

            if (requestBody != null)
            {
                ProjectManagement handler = instanceHandler.getProjectManagement(userId, serverName, methodName);

                response.setGUID(handler.createProjectFromTemplate(userId,
                                                                      requestBody.getAnchorGUID(),
                                                                      requestBody.getIsOwnAnchor(),
                                                                      null,
                                                                      null,
                                                                      requestBody.getTemplateGUID(),
                                                                      requestBody.getReplacementProperties(),
                                                                      requestBody.getPlaceholderPropertyValues(),
                                                                      requestBody.getParentGUID(),
                                                                      requestBody.getParentRelationshipTypeName(),
                                                                      requestBody.getParentRelationshipProperties(),
                                                                      requestBody.getParentAtEnd1()));
            }
            else
            {
                restExceptionHandler.handleNoRequestBody(userId, methodName, serverName);
            }
        }
        catch (Exception error)
        {
            restExceptionHandler.captureExceptions(response, error, methodName, auditLog);
        }

        restCallLogger.logRESTCallReturn(token, response.toString());
        return response;
    }


    /**
     * Update the properties of a project.
     *
     * @param serverName         name of called server.
     * @param projectGUID unique identifier of the project (returned from create)
     * @param replaceAllProperties flag to indicate whether to completely replace the existing properties with the new properties, or just update
     *                          the individual properties specified on the request.
     * @param requestBody     properties for the project.
     *
     * @return void or
     *  InvalidParameterException  one of the parameters is invalid.
     *  PropertyServerException    there is a problem retrieving information from the property server(s).
     *  UserNotAuthorizedException the requesting user is not authorized to issue this request.
     */
    public VoidResponse   updateProject(String            serverName,
                                        String            projectGUID,
                                        boolean           replaceAllProperties,
                                        ProjectProperties requestBody)
    {
        final String methodName = "updateProject";

        RESTCallToken token = restCallLogger.logRESTCall(serverName, methodName);

        VoidResponse response = new VoidResponse();
        AuditLog     auditLog = null;

        try
        {
            String userId = super.getUser(instanceHandler.getServiceName(), methodName);

            restCallLogger.setUserId(token, userId);

            auditLog = instanceHandler.getAuditLog(userId, serverName, methodName);

            if (requestBody != null)
            {
                ProjectManagement handler = instanceHandler.getProjectManagement(userId, serverName, methodName);

                handler.updateProject(userId,
                                      null,
                                      null,
                                      projectGUID,
                                      ! replaceAllProperties,
                                      requestBody);
            }
            else
            {
                restExceptionHandler.handleNoRequestBody(userId, methodName, serverName);
            }
        }
        catch (Exception error)
        {
            restExceptionHandler.captureExceptions(response, error, methodName, auditLog);
        }

        restCallLogger.logRESTCallReturn(token, response.toString());
        return response;
    }


    /**
     * Delete a project.  It is detected from all parent elements.  If members are anchored to the project
     * then they are also deleted.
     *
     * @param serverName         name of called server.
     * @param projectGUID unique identifier of the project.
     * @param requestBody null request body
     *
     * @return void or
     *  InvalidParameterException  one of the parameters is null or invalid.
     *  PropertyServerException    there is a problem retrieving information from the property server(s).
     *  UserNotAuthorizedException the requesting user is not authorized to issue this request.
     */
    @SuppressWarnings(value = "unused")
    public VoidResponse deleteProject(String          serverName,
                                      String          projectGUID,
                                      NullRequestBody requestBody)
    {
        final String methodName = "deleteProject";

        RESTCallToken token = restCallLogger.logRESTCall(serverName, methodName);

        VoidResponse response = new VoidResponse();
        AuditLog     auditLog = null;

        try
        {
            String userId = super.getUser(instanceHandler.getServiceName(), methodName);

            restCallLogger.setUserId(token, userId);

            auditLog = instanceHandler.getAuditLog(userId, serverName, methodName);

            ProjectManagement handler = instanceHandler.getProjectManagement(userId, serverName, methodName);

            handler.removeProject(userId, null, null, projectGUID);
        }
        catch (Exception error)
        {
            restExceptionHandler.captureExceptions(response, error, methodName, auditLog);
        }

        restCallLogger.logRESTCallReturn(token, response.toString());
        return response;
    }


    /**
     * Add an actor to a project.
     *
     * @param serverName               name of called server.
     * @param projectGUID       unique identifier of the project.
     * @param requestBody properties describing the membership characteristics.
     * @param actorGUID          unique identifier of the actor.
     *
     * @return void or
     *  InvalidParameterException  one of the parameters is invalid.
     *  PropertyServerException    there is a problem updating information in the property server(s).
     *  UserNotAuthorizedException the requesting user is not authorized to issue this request.
     */
    public VoidResponse addToProjectTeam(String                serverName,
                                         String                projectGUID,
                                         String                actorGUID,
                                         ProjectTeamProperties requestBody)
    {
        final String methodName = "addToProjectTeam";

        RESTCallToken token = restCallLogger.logRESTCall(serverName, methodName);

        VoidResponse response = new VoidResponse();
        AuditLog     auditLog = null;

        try
        {
            String userId = super.getUser(instanceHandler.getServiceName(), methodName);

            restCallLogger.setUserId(token, userId);

            auditLog = instanceHandler.getAuditLog(userId, serverName, methodName);

            if (requestBody != null)
            {
                ProjectManagement handler = instanceHandler.getProjectManagement(userId, serverName, methodName);

                handler.setupProjectTeam(userId,
                                         null,
                                         null,
                                         projectGUID,
                                         requestBody,
                                         actorGUID);
            }
            else
            {
                restExceptionHandler.handleNoRequestBody(userId, methodName, serverName);
            }
        }
        catch (Exception error)
        {
            restExceptionHandler.captureExceptions(response, error, methodName, auditLog);
        }

        restCallLogger.logRESTCallReturn(token, response.toString());
        return response;
    }


    /**
     * Remove a ProjectTeam relationship between a project and an actor.
     *
     * @param serverName         name of called server.
     * @param projectGUID unique identifier of the project.
     * @param actorGUID    unique identifier of the element.
     * @param requestBody  null request body
     *
     * @return void or
     *  InvalidParameterException  one of the parameters is invalid.
     *  PropertyServerException    there is a problem updating information in the property server(s).
     *  UserNotAuthorizedException the requesting user is not authorized to issue this request.
     */
    @SuppressWarnings(value = "unused")
    public VoidResponse removeFromProjectTeam(String          serverName,
                                              String          projectGUID,
                                              String          actorGUID,
                                              NullRequestBody requestBody)
    {
        final String methodName = "removeFromProjectTeam";

        RESTCallToken token = restCallLogger.logRESTCall(serverName, methodName);

        VoidResponse response = new VoidResponse();
        AuditLog     auditLog = null;

        try
        {
            String userId = super.getUser(instanceHandler.getServiceName(), methodName);

            restCallLogger.setUserId(token, userId);

            auditLog = instanceHandler.getAuditLog(userId, serverName, methodName);

            ProjectManagement handler = instanceHandler.getProjectManagement(userId, serverName, methodName);

            handler.clearProjectTeam(userId,
                                     null,
                                     null,
                                     projectGUID,
                                     actorGUID);
        }
        catch (Exception error)
        {
            restExceptionHandler.captureExceptions(response, error, methodName, auditLog);
        }

        restCallLogger.logRESTCallReturn(token, response.toString());
        return response;
    }


    /**
     * Create a ProjectManagement relationship between a project and a person role to show that anyone appointed to the role is a member of the project.
     *
     * @param serverName name of the service to route the request to.
     * @param projectGUID unique identifier of the project
     * @param projectRoleGUID unique identifier of the person role
     * @param requestBody external identifiers
     *
     * @return void or
     * InvalidParameterException  one of the parameters is invalid or
     * UserNotAuthorizedException the user is not authorized to issue this request or
     * PropertyServerException    there is a problem reported in the open metadata server(s)
     */
    @SuppressWarnings(value = "unused")
    public VoidResponse setupProjectManagementRole(String          serverName,
                                                   String          projectGUID,
                                                   String          projectRoleGUID,
                                                   NullRequestBody requestBody)
    {
        final String methodName = "setupProjectManagementRole";

        RESTCallToken token = restCallLogger.logRESTCall(serverName, methodName);

        VoidResponse response = new VoidResponse();
        AuditLog     auditLog = null;

        try
        {
            String userId = super.getUser(instanceHandler.getServiceName(), methodName);

            restCallLogger.setUserId(token, userId);

            auditLog = instanceHandler.getAuditLog(userId, serverName, methodName);

            ProjectManagement handler = instanceHandler.getProjectManagement(userId, serverName, methodName);

            handler.setupProjectManagementRole(userId,
                                               null,
                                               null,
                                               projectGUID,
                                               projectRoleGUID);
        }
        catch (Exception error)
        {
            restExceptionHandler.captureExceptions(response, error, methodName, auditLog);
        }

        restCallLogger.logRESTCallReturn(token, response.toString());
        return response;
    }


    /**
     * Remove a ProjectManagement relationship between a project and a person role.
     *
     * @param serverName name of the service to route the request to.
     * @param projectGUID unique identifier of the project
     * @param projectRoleGUID unique identifier of the person role
     * @param requestBody external identifiers
     *
     * @return void or
     * InvalidParameterException  one of the parameters is invalid or
     * UserNotAuthorizedException the user is not authorized to issue this request or
     * PropertyServerException    there is a problem reported in the open metadata server(s)
     */
    @SuppressWarnings(value = "unused")
    public VoidResponse clearProjectManagementRole(String          serverName,
                                                   String          projectGUID,
                                                   String          projectRoleGUID,
                                                   NullRequestBody requestBody)
    {
        final String methodName = "clearProjectManagementRole";

        RESTCallToken token = restCallLogger.logRESTCall(serverName, methodName);

        VoidResponse response = new VoidResponse();
        AuditLog     auditLog = null;

        try
        {
            String userId = super.getUser(instanceHandler.getServiceName(), methodName);

            restCallLogger.setUserId(token, userId);

            auditLog = instanceHandler.getAuditLog(userId, serverName, methodName);

            ProjectManagement handler = instanceHandler.getProjectManagement(userId, serverName, methodName);

            handler.clearProjectManagementRole(userId,
                                               null,
                                               null,
                                               projectGUID,
                                               projectRoleGUID);
        }
        catch (Exception error)
        {
            restExceptionHandler.captureExceptions(response, error, methodName, auditLog);
        }

        restCallLogger.logRESTCallReturn(token, response.toString());
        return response;
    }
}
