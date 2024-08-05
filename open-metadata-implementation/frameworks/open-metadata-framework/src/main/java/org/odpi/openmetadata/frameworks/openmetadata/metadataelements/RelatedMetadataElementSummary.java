/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */

package org.odpi.openmetadata.frameworks.openmetadata.metadataelements;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;
import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.PUBLIC_ONLY;

/**
 * RelatedElement contains the properties and header for a relationship retrieved from the metadata repository along with the stub
 * of the related element.
 */
@JsonAutoDetect(getterVisibility=PUBLIC_ONLY, setterVisibility=PUBLIC_ONLY, fieldVisibility=NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class RelatedMetadataElementSummary
{
    private ElementHeader          relationshipHeader     = null;
    private Map<String, String>    relationshipProperties = null;
    private MetadataElementSummary relatedElement         = null;

    /**
     * Default constructor
     */
    public RelatedMetadataElementSummary()
    {
        super();
    }


    /**
     * Copy/clone constructor
     *
     * @param template object to copy
     */
    public RelatedMetadataElementSummary(RelatedMetadataElementSummary template)
    {
        if (template != null)
        {
            relationshipHeader = template.getRelationshipHeader();
            relationshipProperties = template.getRelationshipProperties();
            relatedElement = template.getRelatedElement();
        }
    }


    /**
     * Return the element header associated with the relationship.
     *
     * @return element header object
     */
    public ElementHeader getRelationshipHeader()
    {
        return relationshipHeader;
    }


    /**
     * Set up the element header associated with the relationship.
     *
     * @param relationshipHeader element header object
     */
    public void setRelationshipHeader(ElementHeader relationshipHeader)
    {
        this.relationshipHeader = relationshipHeader;
    }


    /**
     * Return details of the relationship
     *
     * @return relationship properties
     */
    public Map<String, String> getRelationshipProperties()
    {
        return relationshipProperties;
    }


    /**
     * Set up relationship properties
     *
     * @param relationshipProperties relationship properties
     */
    public void setRelationshipProperties(Map<String, String> relationshipProperties)
    {
        this.relationshipProperties = relationshipProperties;
    }


    /**
     * Return the element header associated with end 1 of the relationship.
     *
     * @return element stub object
     */
    public MetadataElementSummary getRelatedElement()
    {
        return relatedElement;
    }


    /**
     * Set up the element header associated with end 1 of the relationship.
     *
     * @param relatedElement element stub object
     */
    public void setRelatedElement(MetadataElementSummary relatedElement)
    {
        this.relatedElement = relatedElement;
    }


    /**
     * JSON-style toString
     *
     * @return return string containing the property names and values
     */
    @Override
    public String toString()
    {
        return "RelatedElement{" +
                       "relationshipHeader=" + relationshipHeader +
                       ", relationshipProperties=" + relationshipProperties +
                       ", relatedElement=" + relatedElement +
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
        RelatedMetadataElementSummary that = (RelatedMetadataElementSummary) objectToCompare;
        return Objects.equals(getRelationshipHeader(), that.getRelationshipHeader()) &&
                       Objects.equals(getRelationshipProperties(), that.getRelationshipProperties()) &&
                       Objects.equals(getRelatedElement(), that.getRelatedElement());
    }


    /**
     * Return hash code for this object
     *
     * @return int hash code
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), relationshipHeader, relationshipProperties, relatedElement);
    }
}
