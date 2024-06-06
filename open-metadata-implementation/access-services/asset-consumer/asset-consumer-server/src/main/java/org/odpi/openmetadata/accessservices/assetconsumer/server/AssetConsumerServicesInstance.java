/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.accessservices.assetconsumer.server;

import org.odpi.openmetadata.accessservices.assetconsumer.connectors.outtopic.AssetConsumerOutTopicClientProvider;
import org.odpi.openmetadata.accessservices.assetconsumer.converters.*;
import org.odpi.openmetadata.accessservices.assetconsumer.elements.*;
import org.odpi.openmetadata.accessservices.assetconsumer.ffdc.AssetConsumerErrorCode;
import org.odpi.openmetadata.accessservices.assetconsumer.handlers.LoggingHandler;
import org.odpi.openmetadata.accessservices.assetconsumer.properties.MetadataElement;
import org.odpi.openmetadata.accessservices.assetconsumer.properties.MetadataRelationship;
import org.odpi.openmetadata.adminservices.configuration.registration.AccessServiceDescription;
import org.odpi.openmetadata.frameworks.connectors.ffdc.PropertyServerException;
import org.odpi.openmetadata.commonservices.generichandlers.*;
import org.odpi.openmetadata.commonservices.multitenant.OMASServiceInstance;
import org.odpi.openmetadata.commonservices.multitenant.ffdc.exceptions.NewInstanceException;
import org.odpi.openmetadata.frameworks.auditlog.AuditLog;
import org.odpi.openmetadata.frameworks.connectors.properties.beans.Asset;
import org.odpi.openmetadata.frameworks.connectors.properties.beans.Connection;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.repositoryconnector.OMRSRepositoryConnector;

import java.util.List;

/**
 * AssetConsumerServicesInstance caches references to objects for a specific server.
 * It is also responsible for registering itself in the instance map.
 */
public class AssetConsumerServicesInstance extends OMASServiceInstance
{
    private final static AccessServiceDescription myDescription = AccessServiceDescription.ASSET_CONSUMER_OMAS;

    private final AssetHandler<Asset>                            assetHandler;
    private final ReferenceableHandler<MetadataElement>          metadataElementHandler;
    private final ReferenceableHandler<MetadataRelationship>     metadataRelationshipHandler;
    private final CommentHandler<OpenMetadataAPIDummyBean>       commentHandler;
    private final ConnectionHandler<OpenMetadataAPIDummyBean>    connectionHandler;
    private final GlossaryTermHandler<MeaningElement>            glossaryTermHandler;
    private final InformalTagHandler<InformalTagElement>         informalTagHandler;
    private final LikeHandler<OpenMetadataAPIDummyBean>          likeHandler;
    private final RatingHandler<OpenMetadataAPIDummyBean>        ratingHandler;
    private final LoggingHandler                                 loggingHandler;

    /**
     * Set up the handlers for this server.  They reflect the range of open metadata elements returned
     * by this OMAS.
     *
     * @param repositoryConnector link to the repository responsible for servicing the REST calls.
     * @param supportedZones list of zones that AssetConsumer is allowed to serve Assets from.
     * @param auditLog destination for audit log events.
     * @param localServerUserId userId used for server initiated actions
     * @param supportedTypesForSearch affects the list of supported asset types seen by the caller
     * @param maxPageSize maximum number of results that can be returned on a single call
     * @param outTopicEventBusConnection inner event bus connection to use to build topic connection to send to client if they which
     *                                   to listen on the out topic.
     * @throws NewInstanceException a problem occurred during initialization
     */
    public AssetConsumerServicesInstance(OMRSRepositoryConnector repositoryConnector,
                                         List<String>            supportedZones,
                                         AuditLog                auditLog,
                                         String                  localServerUserId,
                                         List<String>            supportedTypesForSearch,
                                         int                     maxPageSize,
                                         Connection              outTopicEventBusConnection) throws NewInstanceException
    {
        super(myDescription.getAccessServiceFullName(),
              repositoryConnector,
              supportedZones,
              null,
              null,
              auditLog,
              localServerUserId,
              maxPageSize,
              null,
              null,
              AssetConsumerOutTopicClientProvider.class.getName(),
              outTopicEventBusConnection);

        final String methodName = "new ServiceInstance";

        if (repositoryHandler != null)
        {
            loggingHandler = new LoggingHandler(auditLog);

            OpenMetadataAPIDummyBeanConverter<OpenMetadataAPIDummyBean> dummyConverter =
                    new OpenMetadataAPIDummyBeanConverter<>(repositoryHelper, serviceName, serverName);

            this.assetHandler = new AssetHandler<>(new AssetConverter<>(repositoryHelper, serviceName, serverName),
                                                   Asset.class,
                                                   serviceName,
                                                   serverName,
                                                   invalidParameterHandler,
                                                   repositoryHandler,
                                                   repositoryHelper,
                                                   localServerUserId,
                                                   securityVerifier,
                                                   supportedZones,
                                                   defaultZones,
                                                   publishZones,
                                                   supportedTypesForSearch,
                                                   auditLog);


            this.metadataElementHandler = new ReferenceableHandler<>(new MetadataElementConverter<>(repositoryHelper, serviceName, serverName),
                                                                     MetadataElement.class,
                                                                     serviceName,
                                                                     serverName,
                                                                     invalidParameterHandler,
                                                                     repositoryHandler,
                                                                     repositoryHelper,
                                                                     localServerUserId,
                                                                     securityVerifier,
                                                                     supportedZones,
                                                                     defaultZones,
                                                                     publishZones,
                                                                     auditLog);

            this.metadataRelationshipHandler = new ReferenceableHandler<>(new MetadataRelationshipConverter<>(repositoryHelper, serviceName, serverName),
                                                                          MetadataRelationship.class,
                                                                          serviceName,
                                                                          serverName,
                                                                          invalidParameterHandler,
                                                                          repositoryHandler,
                                                                          repositoryHelper,
                                                                          localServerUserId,
                                                                          securityVerifier,
                                                                          supportedZones,
                                                                          defaultZones,
                                                                          publishZones,
                                                                          auditLog);

            this.commentHandler = new CommentHandler<>(dummyConverter,
                                                       OpenMetadataAPIDummyBean.class,
                                                       serviceName,
                                                       serverName,
                                                       invalidParameterHandler,
                                                       repositoryHandler,
                                                       repositoryHelper,
                                                       localServerUserId,
                                                       securityVerifier,
                                                       supportedZones,
                                                       defaultZones,
                                                       publishZones,
                                                       auditLog);

            this.connectionHandler = new ConnectionHandler<>(dummyConverter,
                                                             OpenMetadataAPIDummyBean.class,
                                                             serviceName,
                                                             serverName,
                                                             invalidParameterHandler,
                                                             repositoryHandler,
                                                             repositoryHelper,
                                                             localServerUserId,
                                                             securityVerifier,
                                                             supportedZones,
                                                             defaultZones,
                                                             publishZones,
                                                             auditLog);

            this.glossaryTermHandler = new GlossaryTermHandler<>(new MeaningConverter<>(repositoryHelper, serviceName, serverName),
                                                                 MeaningElement.class,
                                                                 serviceName,
                                                                 serverName,
                                                                 invalidParameterHandler,
                                                                 repositoryHandler,
                                                                 repositoryHelper,
                                                                 localServerUserId,
                                                                 securityVerifier,
                                                                 supportedZones,
                                                                 defaultZones,
                                                                 publishZones,
                                                                 auditLog);

            this.informalTagHandler = new InformalTagHandler<>(new InformalTagConverter<>(repositoryHelper, serviceName, serverName),
                                                               InformalTagElement.class,
                                                               serviceName,
                                                               serverName,
                                                               invalidParameterHandler,
                                                               repositoryHandler,
                                                               repositoryHelper,
                                                               localServerUserId,
                                                               securityVerifier,
                                                               supportedZones,
                                                               defaultZones,
                                                               publishZones,
                                                               auditLog);

            this.likeHandler = new LikeHandler<>(dummyConverter,
                                                 OpenMetadataAPIDummyBean.class,
                                                 serviceName,
                                                 serverName,
                                                 invalidParameterHandler,
                                                 repositoryHandler,
                                                 repositoryHelper,
                                                 localServerUserId,
                                                 securityVerifier,
                                                 supportedZones,
                                                 defaultZones,
                                                 publishZones,
                                                 auditLog);

            this.ratingHandler = new RatingHandler<>(dummyConverter,
                                                     OpenMetadataAPIDummyBean.class,
                                                     serviceName,
                                                     serverName,
                                                     invalidParameterHandler,
                                                     repositoryHandler,
                                                     repositoryHelper,
                                                     localServerUserId,
                                                     securityVerifier,
                                                     supportedZones,
                                                     defaultZones,
                                                     publishZones,
                                                     auditLog);
        }
        else
        {
            throw new NewInstanceException(AssetConsumerErrorCode.OMRS_NOT_INITIALIZED.getMessageDefinition(methodName),
                                           this.getClass().getName(),
                                           methodName);

        }
    }


    /**
     * Return the specialized handler for logging messages to the audit log.
     *
     * @return logging handler
     */
    LoggingHandler getLoggingHandler()
    {
        return loggingHandler;
    }


    /**
     * Return the handler for managing asset objects.
     *
     * @return  handler object
     * @throws PropertyServerException the instance has not been initialized successfully
     */
    public AssetHandler<Asset> getAssetHandler() throws PropertyServerException
    {
        final String methodName = "getAssetHandler";

        validateActiveRepository(methodName);

        return assetHandler;
    }


    /**
     * Return the handler for managing generic metadata element objects.
     *
     * @return  handler object
     * @throws PropertyServerException the instance has not been initialized successfully
     */
    public ReferenceableHandler<MetadataElement> getMetadataElementHandler() throws PropertyServerException
    {
        final String methodName = "getMetadataElementHandler";

        validateActiveRepository(methodName);

        return metadataElementHandler;
    }



    /**
     * Return the handler for managing generic metadata element objects.
     *
     * @return  handler object
     * @throws PropertyServerException the instance has not been initialized successfully
     */
    public ReferenceableHandler<MetadataRelationship> getMetadataRelationshipHandler() throws PropertyServerException
    {
        final String methodName = "getMetadataRelationshipHandler";

        validateActiveRepository(methodName);

        return metadataRelationshipHandler;
    }



    /**
     * Return the handler for managing comment objects.
     *
     * @return  handler object
     * @throws PropertyServerException the instance has not been initialized successfully
     */
    CommentHandler<OpenMetadataAPIDummyBean> getCommentHandler() throws PropertyServerException
    {
        final String methodName = "getCommentHandler";

        validateActiveRepository(methodName);

        return commentHandler;
    }


    /**
     * Return the handler for managing connection objects.
     *
     * @return  handler object
     * @throws PropertyServerException the instance has not been initialized successfully
     */
    ConnectionHandler<OpenMetadataAPIDummyBean> getConnectionHandler() throws PropertyServerException
    {
        final String methodName = "getConnectionHandler";

        validateActiveRepository(methodName);

        return connectionHandler;
    }


    /**
     * Return the handler for managing glossary objects.
     *
     * @return glossary handler
     * @throws PropertyServerException the instance has not been initialized successfully
     */
    GlossaryTermHandler<MeaningElement> getGlossaryTermHandler() throws PropertyServerException
    {
        final String methodName = "getGlossaryTermHandler";

        validateActiveRepository(methodName);

        return glossaryTermHandler;
    }


    /**
     * Return the handler for managing informal tag objects.
     *
     * @return  handler object
     * @throws PropertyServerException the instance has not been initialized successfully
     */
    InformalTagHandler<InformalTagElement> getInformalTagHandler() throws PropertyServerException
    {
        final String methodName = "getInformalTagHandler";

        validateActiveRepository(methodName);

        return informalTagHandler;
    }


    /**
     * Return the handler for managing like objects.
     *
     * @return  handler object
     * @throws PropertyServerException the instance has not been initialized successfully
     */
    LikeHandler<OpenMetadataAPIDummyBean> getLikeHandler() throws PropertyServerException
    {
        final String methodName = "getLikeHandler";

        validateActiveRepository(methodName);

        return likeHandler;
    }


    /**
     * Return the handler for managing rating objects.
     *
     * @return  handler object
     * @throws PropertyServerException the instance has not been initialized successfully
     */
    RatingHandler<OpenMetadataAPIDummyBean> getRatingHandler() throws PropertyServerException
    {
        final String methodName = "getRatingHandler";

        validateActiveRepository(methodName);

        return ratingHandler;
    }
}
