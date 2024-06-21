/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */

package org.odpi.openmetadata.adapters.connectors.unitycatalog.resource.rest;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.odpi.openmetadata.adapters.connectors.unitycatalog.properties.TableInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.PUBLIC_ONLY;

/**
 * Return a list of tables.
 */
@JsonAutoDetect(getterVisibility=PUBLIC_ONLY, setterVisibility=PUBLIC_ONLY, fieldVisibility=NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class ListTablesResponse
{
    private List<TableInfo> tables       = new ArrayList<>();
    private String           nextPageToken = null;


    /**
     * Constructor
     */
    public ListTablesResponse()
    {
    }


    /**
     * Return the list of tables.
     * 
     * @return list
     */
    public List<TableInfo> getTables()
    {
        return tables;
    }


    /**
     * Return the list of tables.
     * 
     * @param table list
     */
    public void setTables(List<TableInfo> table)
    {
        this.tables = table;
    }


    /**
     * Return the opaque token to retrieve the next page of results. Absent if there are no more pages. page_token should be set to this value for the next request (for the next page of results).
     * 
     * @return token
     */
    public String getNextPageToken()
    {
        return nextPageToken;
    }


    /**
     * Set up the opaque token to retrieve the next page of results. Absent if there are no more pages. page_token should be set to this value for the next request (for the next page of results).
     * 
     * @param nextPageToken token
     */
    public void setNextPageToken(String nextPageToken)
    {
        this.nextPageToken = nextPageToken;
    }


    /**
     * Standard toString method.
     *
     * @return print out of variables in a JSON-style
     */
    @Override
    public String toString()
    {
        return "ListTablesResponse{" +
                "tables=" + tables +
                ", nextPageToken='" + nextPageToken + '\'' +
                '}';
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
        ListTablesResponse that = (ListTablesResponse) objectToCompare;
        return Objects.equals(tables, that.tables) && Objects.equals(nextPageToken, that.nextPageToken);
    }


    /**
     * Return hash code based on properties.
     *
     * @return int
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(tables, nextPageToken);
    }
}
