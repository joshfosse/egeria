/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.viewservices.templatemanager.server;

import org.odpi.openmetadata.accessservices.digitalarchitecture.client.OpenMetadataStoreClient;
import org.odpi.openmetadata.commonservices.multitenant.OMVSServiceInstance;
import org.odpi.openmetadata.adminservices.configuration.registration.ViewServiceDescription;
import org.odpi.openmetadata.frameworks.auditlog.AuditLog;
import org.odpi.openmetadata.frameworks.connectors.ffdc.InvalidParameterException;

/**
 * TemplateManagerInstance caches references to the objects it needs for a specific server.
 * It is also responsible for registering itself in the instance map.
 */

public class TemplateManagerInstance extends OMVSServiceInstance
{
    private static final ViewServiceDescription myDescription = ViewServiceDescription.TEMPLATE_MANAGER;

    private final OpenMetadataStoreClient openMetadataStoreClient;


    /**
     * Set up theTemplate Manager OMVS instance
     *
     * @param serverName name of this server
     * @param auditLog logging destination
     * @param localServerUserId userId used for server initiated actions
     * @param maxPageSize maximum page size
     * @param remoteServerName  remote server name
     * @param remoteServerURL remote server URL
     * @throws InvalidParameterException problem with server name or platform URL
     */
    public TemplateManagerInstance(String       serverName,
                                   AuditLog     auditLog,
                                   String       localServerUserId,
                                   int          maxPageSize,
                                   String       remoteServerName,
                                   String       remoteServerURL) throws InvalidParameterException
    {
        super(serverName,
              myDescription.getViewServiceName(),
              auditLog,
              localServerUserId,
              maxPageSize,
              remoteServerName,
              remoteServerURL);

        openMetadataStoreClient = new OpenMetadataStoreClient(remoteServerName, remoteServerURL, maxPageSize);
    }


    /**
     * Return the organization management client.  This client is from Community Profile OMAS and is for maintaining information .
     *
     * @return client
     */
    public OpenMetadataStoreClient getOpenMetadataStoreClient()
    {
        return openMetadataStoreClient;
    }
}
