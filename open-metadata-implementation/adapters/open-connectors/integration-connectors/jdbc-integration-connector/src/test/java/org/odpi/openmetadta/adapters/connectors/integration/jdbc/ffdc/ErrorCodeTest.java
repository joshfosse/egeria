/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadta.adapters.connectors.integration.jdbc.ffdc;

import org.odpi.openmetadata.adapters.connectors.integration.jdbc.ffdc.JDBCIntegrationConnectorErrorCode;
import org.odpi.openmetadata.test.unittest.utilities.ExceptionMessageSetTest;
import org.testng.annotations.Test;


/**
 * Verify the JDBCIntegrationConnectorErrorCode enum contains unique message ids, non-null names and descriptions and can be
 * serialized to JSON and back again.
 */
public class ErrorCodeTest extends ExceptionMessageSetTest
{
    final static String  messageIdPrefix = "JDBC-INTEGRATION-CONNECTOR";

    /**
     * Validated the values of the enum.
     */
    @Test public void testAllErrorCodeValues()
    {
        for (JDBCIntegrationConnectorErrorCode errorCode : JDBCIntegrationConnectorErrorCode.values())
        {
            super.testSingleErrorCodeValue(errorCode, messageIdPrefix);
        }
    }
}
