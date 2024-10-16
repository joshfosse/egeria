/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */

package org.odpi.openmetadata.adapters.connectors.unitycatalog.properties;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.PUBLIC_ONLY;

/**
 * Description of a function.  These are the values that are returned from UC.
 */
@JsonAutoDetect(getterVisibility=PUBLIC_ONLY, setterVisibility=PUBLIC_ONLY, fieldVisibility=NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class FunctionInfo extends FunctionProperties implements ElementBase
{
    private long   created_at  = 0L;
    private long   updated_at  = 0L;
    private String function_id = null;
    private String full_name   = null;

    /**
     * Constructor
     */
    public FunctionInfo()
    {
    }


    /**
     * Return the time that the element was created.
     *
     * @return date/time as long
     */
    @Override
    public long getCreated_at()
    {
        return created_at;
    }


    /**
     * Set up the time that the element was created.
     *
     * @param created_at date/time as long
     */
    @Override
    public void setCreated_at(long created_at)
    {
        this.created_at = created_at;
    }


    /**
     * Return the time that the element was last updated.
     *
     * @return date/time as long
     */
    @Override
    public long getUpdated_at()
    {
        return updated_at;
    }


    /**
     * Set up the time that the element was last updated.
     *
     * @param updated_at  date/time as long
     */
    @Override
    public void setUpdated_at(long updated_at)
    {
        this.updated_at = updated_at;
    }


    /**
     * Return the internal identifier of the function.
     *
     * @return string
     */
    public String getFunction_id()
    {
        return function_id;
    }


    /**
     * Set up the internal identifier of the function.
     *
     * @param function_id string
     */
    public void setFunction_id(String function_id)
    {
        this.function_id = function_id;
    }


    /**
     * Return the fully qualified name of the function.
     *
     * @return string
     */
    public String getFull_name()
    {
        return full_name;
    }


    /**
     * Set up the full qualified name of the function.
     *
     * @param full_name string
     */
    public void setFull_name(String full_name)
    {
        this.full_name = full_name;
    }

    /**
     * Standard toString method.
     *
     * @return print out of variables in a JSON-style
     */
    @Override
    public String toString()
    {
        return "FunctionInfo{" +
                "created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", full_name=" + full_name +
                ", function_id='" + function_id + '\'' +
                "} " + super.toString();
    }

    /**
     * Compare the values of the supplied object with those stored in the current object.
     *
     * @param objectToCompare supplied object
     * @return boolean result of comparison
     */
    @Override
    public boolean equals(Object objectToCompare)
    {
        if (this == objectToCompare) return true;
        if (objectToCompare == null || getClass() != objectToCompare.getClass()) return false;
        if (!super.equals(objectToCompare)) return false;
        FunctionInfo that = (FunctionInfo) objectToCompare;
        return created_at == that.created_at && updated_at == that.updated_at && Objects.equals(function_id, that.function_id) && Objects.equals(full_name, that.full_name);
    }

    /**
     * Return hash code based on properties.
     *
     * @return int
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), created_at, updated_at, function_id, full_name);
    }
}
