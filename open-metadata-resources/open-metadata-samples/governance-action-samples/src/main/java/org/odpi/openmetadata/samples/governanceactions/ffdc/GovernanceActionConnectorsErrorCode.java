/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.governanceactions.ffdc;

import org.odpi.openmetadata.frameworks.auditlog.messagesets.ExceptionMessageDefinition;
import org.odpi.openmetadata.frameworks.auditlog.messagesets.ExceptionMessageSet;

/**
 * The GovernanceActionConnectorsErrorCode is used to define first failure data capture (FFDC) for errors that occur when working with
 * the Basic File Connector.  It is used in conjunction with both Checked and Runtime (unchecked) exceptions.
 *
 * The 5 fields in the enum are:
 * <ul>
 *     <li>HTTP Error Code - for translating between REST and JAVA - Typically the numbers used are:</li>
 *     <li><ul>
 *         <li>500 - internal error</li>
 *         <li>400 - invalid parameters</li>
 *         <li>404 - not found</li>
 *         <li>409 - data conflict errors - eg item already defined</li>
 *     </ul></li>
 *     <li>Error Message Id - to uniquely identify the message</li>
 *     <li>Error Message Text - includes placeholder to allow additional values to be captured</li>
 *     <li>SystemAction - describes the result of the error</li>
 *     <li>UserAction - describes how a consumer should correct the error</li>
 * </ul>
 */
public enum GovernanceActionConnectorsErrorCode implements ExceptionMessageSet
{
    NO_SOURCE_FILE_NAME(400, "GOVERNANCE-ACTION-CONNECTORS-400-006",
                        "The {0} governance action service has been called without a source file name to work with",
                        "The provisioning governance action service connector is designed to manage files on request.  " +
                                "It is unable to operate without the name of the source file and so it terminates with a FAILED completion status.",
                        "The source file is passed to the governance action service through the request parameters or via the TargetForAction " +
                                "relationship.  Correct the information passed to the governance service and rerun the request"),

    FOLDER_ELEMENT_NOT_FOUND(404, "GOVERNANCE-ACTION-CONNECTORS-404-002",
                             "A FileFolder element with a path name of {0} is not found in the open metadata ecosystem",
                             "The governance action service is not able to proceed until the element has been created.",
                             "The path name of the folder is passed either in the folderName configuration property; folderName request parameters or folderTarget action target."),

    UNABLE_TO_REGISTER_LISTENER(500, "GOVERNANCE-ACTION-CONNECTORS-500-003",
                                "The {0} governance action service received a {1} exception when it registered a listener with the governance context.  The exception's message is: {2}",
                                "The governance action throws a GovernanceServiceException in the hope that the .",
                                "This is likely to be a configuration error.  Review the description of the exception's message to understand what is not set up correctly and " +
                                        "and follow its instructions."),

    UNEXPECTED_EXCEPTION(500, "GOVERNANCE-ACTION-CONNECTORS-500-004",
                                  "The {0} governance action service received an unexpected exception {1} during its processing; the error message was: {2}",
                                  "The governance action returns an exception to the Governance Action Engine.",
                                  "Use details from the error message to determine the cause of the error and retry the service call once it is resolved."),
    ;


    private final int    httpErrorCode;
    private final String errorMessageId;
    private final String errorMessage;
    private final String systemAction;
    private final String userAction;


    /**
     * The constructor expects to be passed one of the enumeration rows defined above.
     *
     * @param httpErrorCode   error code to use over REST calls
     * @param errorMessageId   unique id for the message
     * @param errorMessage   text for the message
     * @param systemAction   description of the action taken by the system when the error condition happened
     * @param userAction   instructions for resolving the error
     */
    GovernanceActionConnectorsErrorCode(int httpErrorCode, String errorMessageId, String errorMessage, String systemAction, String userAction)
    {
        this.httpErrorCode = httpErrorCode;
        this.errorMessageId = errorMessageId;
        this.errorMessage = errorMessage;
        this.systemAction = systemAction;
        this.userAction = userAction;
    }


    /**
     * Retrieve a message definition object for an exception.  This method is used when there are no message inserts.
     *
     * @return message definition object.
     */
    @Override
    public ExceptionMessageDefinition getMessageDefinition()
    {
        return new ExceptionMessageDefinition(httpErrorCode,
                                              errorMessageId,
                                              errorMessage,
                                              systemAction,
                                              userAction);
    }


    /**
     * Retrieve a message definition object for an exception.  This method is used when there are values to be inserted into the message.
     *
     * @param params array of parameters (all strings).  They are inserted into the message according to the numbering in the message text.
     * @return message definition object.
     */
    @Override
    public ExceptionMessageDefinition getMessageDefinition(String... params)
    {
        ExceptionMessageDefinition messageDefinition = new ExceptionMessageDefinition(httpErrorCode,
                                                                                      errorMessageId,
                                                                                      errorMessage,
                                                                                      systemAction,
                                                                                      userAction);

        messageDefinition.setMessageParameters(params);

        return messageDefinition;
    }


    /**
     * JSON-style toString
     *
     * @return string of property names and values for this enum
     */
    @Override
    public String toString()
    {
        return "ErrorCode{" +
                       "httpErrorCode=" + httpErrorCode +
                       ", errorMessageId='" + errorMessageId + '\'' +
                       ", errorMessage='" + errorMessage + '\'' +
                       ", systemAction='" + systemAction + '\'' +
                       ", userAction='" + userAction + '\'' +
                       '}';
    }
}
