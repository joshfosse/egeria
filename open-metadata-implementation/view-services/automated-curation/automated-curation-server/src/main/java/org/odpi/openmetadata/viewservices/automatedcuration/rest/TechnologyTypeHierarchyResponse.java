/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.viewservices.automatedcuration.rest;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.odpi.openmetadata.accessservices.assetowner.rest.AssetOwnerOMASAPIResponse;
import org.odpi.openmetadata.viewservices.automatedcuration.properties.TechnologyTypeHierarchy;
import org.odpi.openmetadata.viewservices.automatedcuration.properties.TechnologyTypeReport;

import java.util.Arrays;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.PUBLIC_ONLY;


/**
 * TechnologyTypeHierarchyResponse is the response structure used on the OMVS REST API calls that returns a
 * technology type hierarchy object as a response.
 */
@JsonAutoDetect(getterVisibility=PUBLIC_ONLY, setterVisibility=PUBLIC_ONLY, fieldVisibility=NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class TechnologyTypeHierarchyResponse extends AssetOwnerOMASAPIResponse
{
    private TechnologyTypeHierarchy element = null;


    /**
     * Default constructor
     */
    public TechnologyTypeHierarchyResponse()
    {
        super();
    }



    /**
     * Return the element result.
     *
     * @return requested object
     */
    public TechnologyTypeHierarchy getElement()
    {
        return element;
    }


    /**
     * Set up the element result.
     *
     * @param element requested object
     */
    public void setElement(TechnologyTypeHierarchy element)
    {
        this.element = element;
    }


    /**
     * JSON-style toString
     *
     * @return return string containing the property names and values
     */
    @Override
    public String toString()
    {
        return "TechnologyTypeHierarchyResponse{" +
                "element='" + getElement() + '\'' +
                ", exceptionClassName='" + getExceptionClassName() + '\'' +
                ", exceptionCausedBy='" + getExceptionCausedBy() + '\'' +
                ", actionDescription='" + getActionDescription() + '\'' +
                ", relatedHTTPCode=" + getRelatedHTTPCode() +
                ", exceptionErrorMessage='" + getExceptionErrorMessage() + '\'' +
                ", exceptionErrorMessageId='" + getExceptionErrorMessageId() + '\'' +
                ", exceptionErrorMessageParameters=" + Arrays.toString(getExceptionErrorMessageParameters()) +
                ", exceptionSystemAction='" + getExceptionSystemAction() + '\'' +
                ", exceptionUserAction='" + getExceptionUserAction() + '\'' +
                ", exceptionProperties=" + getExceptionProperties() +
                '}';
    }
}
