/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */

package org.odpi.openmetadata.accessservices.assetmanager.rest;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.odpi.openmetadata.accessservices.assetmanager.metadataelements.DataFlowElement;
import org.odpi.openmetadata.accessservices.assetmanager.metadataelements.RelationshipElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.PUBLIC_ONLY;

/**
 * RelationshipElementsResponse is a response object for passing back a list of relationships
 * or an exception if the request failed.
 */
@JsonAutoDetect(getterVisibility=PUBLIC_ONLY, setterVisibility=PUBLIC_ONLY, fieldVisibility=NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class RelationshipElementsResponse extends AssetManagerOMASAPIResponse
{
    private static final long    serialVersionUID = 1L;

    private List<RelationshipElement> elementList = null;


    /**
     * Default constructor
     */
    public RelationshipElementsResponse()
    {
    }


    /**
     * Copy/clone constructor
     *
     * @param template object to copy
     */
    public RelationshipElementsResponse(RelationshipElementsResponse template)
    {
        super(template);

        if (template != null)
        {
            elementList = template.getElementList();
        }
    }


    /**
     * Return the list of metadata elements.
     *
     * @return result object
     */
    public List<RelationshipElement> getElementList()
    {
        if (elementList == null)
        {
            return null;
        }
        else if (elementList.isEmpty())
        {
            return null;
        }
        else
        {
            return new ArrayList<>(elementList);
        }
    }


    /**
     * Set up the metadata element to return.
     *
     * @param elementList result object
     */
    public void setElementList(List<RelationshipElement> elementList)
    {
        this.elementList = elementList;
    }


    /**
     * JSON-style toString
     *
     * @return return string containing the property names and values
     */
    @Override
    public String toString()
    {
        return "RelationshipElementsResponse{" +
                       "elementList=" + elementList +
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


    /**
     * Return comparison result based on the content of the properties.
     *
     * @param objectToCompare test object
     * @return result of comparison
     */
    @Override
    public boolean equals(Object objectToCompare)
    {
        if (this == objectToCompare)
        {
            return true;
        }
        if (objectToCompare == null || getClass() != objectToCompare.getClass())
        {
            return false;
        }
        if (!super.equals(objectToCompare))
        {
            return false;
        }
        RelationshipElementsResponse that = (RelationshipElementsResponse) objectToCompare;
        return Objects.equals(elementList, that.elementList);
    }


    /**
     * Return hash code for this object
     *
     * @return int hash code
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), elementList);
    }
}
