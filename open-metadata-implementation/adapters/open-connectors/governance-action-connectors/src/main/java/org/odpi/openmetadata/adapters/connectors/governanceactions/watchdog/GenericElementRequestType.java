/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */

package org.odpi.openmetadata.adapters.connectors.governanceactions.watchdog;


import org.odpi.openmetadata.frameworks.governanceaction.controls.RequestTypeType;

import java.util.ArrayList;
import java.util.List;

/**
 * MoveCopyFileRequestType provides a template for defining the request type for a governance service.
 */
public enum GenericElementRequestType
{
    PROCESS_SINGLE_EVENT("process-single-event", "Only a single event is processed."),
    PROCESS_MULTIPLE_EVENTS("process-multiple-events", "The monitoring should go on indefinitely, processing multiple events as they occur."),


    ;

    public final String requestType;
    public final String description;



    /**
     * Create a specific Enum constant.
     *
     * @param requestType name of the request type
     * @param description description of the request type
     */
    GenericElementRequestType(String requestType,
                              String description)
    {
        this.requestType = requestType;
        this.description = description;
    }


    /**
     * Return the name of the requestType.
     *
     * @return string name
     */
    public String getRequestType()
    {
        return requestType;
    }


    /**
     * Return the description of the requestType.
     *
     * @return text
     */
    public String getDescription()
    {
        return description;
    }


    /**
     * Return details of all the defined request types for a governance service.
     *
     * @return list of request type types
     */
    public static List<RequestTypeType> getRequestTypeTypes()
    {
        List<RequestTypeType> requestTypeTypes = new ArrayList<>();

        for (GenericElementRequestType requestTypeValue : GenericElementRequestType.values())
        {
            RequestTypeType requestTypeType = new RequestTypeType();

            requestTypeType.setRequestType(requestTypeValue.requestType);
            requestTypeType.setDescription(requestTypeValue.description);

            requestTypeTypes.add(requestTypeType);
        }

        return requestTypeTypes;
    }



    /**
     * Return the request type packaged for use by the Service Provider.
     *
     * @return requestTypeType
     */
    public RequestTypeType getRequestTypeType()
    {
        RequestTypeType requestTypeType = new RequestTypeType();

        requestTypeType.setRequestType(requestType);
        requestTypeType.setDescription(description);

        return requestTypeType;
    }


    /**
     * Output of this enum class and main value.
     *
     * @return string showing enum value
     */
    @Override
    public String toString()
    {
        return "MoveCopyFileRequestType{" + requestType + "}";
    }
}
