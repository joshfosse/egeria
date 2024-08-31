/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */

package org.odpi.openmetadata.archiveutilities.openconnectors;

import org.odpi.openmetadata.adapters.connectors.governanceactions.stewardship.CreateServerRequestParameter;
import org.odpi.openmetadata.adapters.connectors.surveyaction.controls.FolderRequestParameter;
import org.odpi.openmetadata.adapters.connectors.unitycatalog.controls.UnityCatalogTemplateType;
import org.odpi.openmetadata.frameworks.governanceaction.controls.ActionTarget;
import org.odpi.openmetadata.frameworks.governanceaction.properties.NewActionTarget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Defines the request types for the governance engines that identify which governance service to call
 * for a specific request type.
 */
public enum RequestTypeDefinition
{
    /**
     * watch-for-new-files-in-folder
     */
    WATCH_FOR_NEW_FILES("watch-for-new-files-in-folder",
                        "watch-nested-in-folder",
                        null,
                        null,
                        GovernanceEngineDefinition.FILE_GOVERNANCE_ENGINE,
                        GovernanceServiceDefinition.NEW_FILES_WATCHDOG,
                        "69bead73-b5b7-4791-9293-c660990ec7bf",
                        null),

    /**
     * copy-file
     */
    COPY_FILE("copy-file",
              null,
              null,
              null,
              GovernanceEngineDefinition.FILE_PROVISIONING_ENGINE,
              GovernanceServiceDefinition.FILE_PROVISIONER,
              "4f7c739b-69d3-4310-9bb2-507625dc2899",
              null),

    /**
     * move-file
     */
    MOVE_FILE("move-file",
              null,
              null,
              null,
              GovernanceEngineDefinition.FILE_PROVISIONING_ENGINE,
              GovernanceServiceDefinition.FILE_PROVISIONER,
              "dc3ad63e-6663-4087-bcf3-6e48c68ed5b6",
              null),

    /**
     * delete-file
     */
    DELETE_FILE("delete-file",
                null,
                null,
                null,
                GovernanceEngineDefinition.FILE_PROVISIONING_ENGINE,
                GovernanceServiceDefinition.FILE_PROVISIONER,
                "c658530b-5f99-4212-a321-92bad0cd9b60",
                null),

    /**
     * seek-origin-of-asset
     */
    SEEK_ORIGIN("seek-origin-of-asset",
                null,
                null,
                null,
                GovernanceEngineDefinition.ASSET_ONBOARDING_ENGINE,
                GovernanceServiceDefinition.ORIGIN_SEEKER,
                "98a63f4c-01fc-4c38-9897-d59fb7c888ee",
                null),

    /**
     * "set-zone-membership"
     */
    ZONE_MEMBER("set-zone-membership",
                null,
                null,
                null,
                GovernanceEngineDefinition.ASSET_ONBOARDING_ENGINE,
                GovernanceServiceDefinition.ZONE_PUBLISHER,
                "05df4044-bc0a-40cd-b729-66aef891e7f0",
                null),


    /**
     * set-retention-period
     */
    RETENTION_PERIOD("set-retention-period",
                     null,
                     null,
                     null,
                     GovernanceEngineDefinition.ASSET_ONBOARDING_ENGINE,
                     GovernanceServiceDefinition.RETENTION_CLASSIFIER,
                     "633cca67-7be8-49bf-9c38-f82e4ceea44c",
                     null),

    /**
     * verify-asset
     */
    VERIFY_ASSET("verify-asset",
                 null,
                 null,
                 null,
                 GovernanceEngineDefinition.ASSET_ONBOARDING_ENGINE,
                 GovernanceServiceDefinition.VERIFY_ASSET,
                 "a7983409-8eee-4239-a252-a3c5515def59",
                 null),

    /**
     * evaluate-annotations
     */
    EVALUATE_ANNOTATIONS("evaluate-annotations",
                         null,
                         null,
                         null,
                         GovernanceEngineDefinition.STEWARDSHIP_ENGINE,
                         GovernanceServiceDefinition.EVALUATE_ANNOTATIONS,
                         "be193d1c-1a60-4f03-8204-22817f2d40c4",
                         null),

    /**
     * write-to-audit-log
     */
    WRITE_AUDIT_LOG("write-to-audit-log",
                    null,
                    null,
                    null,
                    GovernanceEngineDefinition.STEWARDSHIP_ENGINE,
                    GovernanceServiceDefinition.WRITE_AUDIT_LOG,
                    "faa9ef71-3f49-4ab8-8241-066ef7b517e8",
                    null),

    /**
     * get-day-of-week
     */
    GET_DAY_OF_WEEK("get-day-of-week",
                    null,
                    null,
                    null,
                    GovernanceEngineDefinition.STEWARDSHIP_ENGINE,
                    GovernanceServiceDefinition.DAY_OF_WEEK,
                    "a3c16a82-a754-434f-930d-f412e62643a6",
                    null),

    /**
     * qualified-name-dedup
     */
    QNAME_DEDUP("qualified-name-dedup",
                null,
                null,
                null,
                GovernanceEngineDefinition.STEWARDSHIP_ENGINE,
                GovernanceServiceDefinition.QUALIFIED_NAME_DEDUP,
                "066e9a5f-b725-4047-abd8-ce5353803ba1",
                null),


    /**
     * survey-csv-file
     */
    SURVEY_CSV_FILE("survey-csv-file",
                    null,
                    null,
                    null,
                    GovernanceEngineDefinition.FILE_SURVEY_ENGINE,
                    GovernanceServiceDefinition.CSV_FILE_SURVEY,
                    "fcd7ddce-b61e-49eb-b993-293907dadf72",
                    null),

    /**
     * survey-data-file
     */
    SURVEY_DATA_FILE("survey-data-file",
                     null,
                     null,
                     null,
                     GovernanceEngineDefinition.FILE_SURVEY_ENGINE,
                     GovernanceServiceDefinition.DATA_FILE_SURVEY,
                     "3a15cfe4-e130-4b8c-b4fb-eedd39e1a2ae",
                     null),

    /**
     * survey-folder
     */
    SURVEY_FOLDER("survey-folder",
                  null,
                  getFolderSurveyRequestParameters(),
                  null,
                  GovernanceEngineDefinition.FILE_SURVEY_ENGINE,
                  GovernanceServiceDefinition.FOLDER_SURVEY,
                  "381c60e6-733b-42db-a025-8e6eb29294fc",
                  null),

    /**
     * survey-folder-and-files
     */
    SURVEY_FOLDER_AND_FILES("survey-folder-and-files",
                            null,
                            getFolderAndFilesSurveyRequestParameters(),
                            null,
                            GovernanceEngineDefinition.FILE_SURVEY_ENGINE,
                            GovernanceServiceDefinition.FOLDER_SURVEY,
                            "633e7711-0c65-47b5-894f-c9dba5472412",
                            null),

    /**
     * survey-all-folders
     */
    SURVEY_ALL_FOLDERS("survey-all-folders",
                       null,
                       getAllFoldersSurveyRequestParameters(),
                       null,
                       GovernanceEngineDefinition.FILE_SURVEY_ENGINE,
                       GovernanceServiceDefinition.FOLDER_SURVEY,
                       "a6f2f6e8-d912-4101-982f-79c62190f1ba",
                       null),

    /**
     * survey-all-folders-and-files
     */
    SURVEY_ALL_FOLDERS_AND_FILES("survey-all-folders-and-files",
                                 null,
                                 getAllFolderAndFilesSurveyRequestParameters(),
                                 null,
                                 GovernanceEngineDefinition.FILE_SURVEY_ENGINE,
                                 GovernanceServiceDefinition.FOLDER_SURVEY,
                                 "cc642671-898a-4c83-9d29-b1a1758672d2",
                                 null),

    /**
     * survey-apache-atlas-server
     */
    SURVEY_ATLAS_SERVER("survey-apache-atlas-server",
                        null,
                        null,
                        null,
                        GovernanceEngineDefinition.ASSET_SURVEY_ENGINE,
                        GovernanceServiceDefinition.APACHE_ATLAS_SURVEY,
                        "18d36065-3e39-43bc-be31-4b6c22354480",
                        null),

    /**
     * survey-kafka-server
     */
    SURVEY_KAFKA_SERVER("survey-kafka-server",
                        null,
                        null,
                        null,
                        GovernanceEngineDefinition.ASSET_SURVEY_ENGINE,
                        GovernanceServiceDefinition.KAFKA_SERVER_SURVEY,
                        "71c73133-6817-42a1-9cc6-b610cee34a8b",
                        null),

    /**
     * survey-unity-catalog-server
     */
    SURVEY_UC_SERVER("survey-unity-catalog-server",
                     null,
                     null,
                     null,
                     GovernanceEngineDefinition.UNITY_CATALOG_SURVEY_ENGINE,
                     GovernanceServiceDefinition.UC_SERVER_SURVEY,
                     "c9fca16e-854d-43bc-b97e-33691afafac3",
                     null),

    /**
     * survey-unity-catalog-catalog
     */
    SURVEY_UC_CATALOG("survey-unity-catalog-catalog",
                      null,
                      null,
                      null,
                      GovernanceEngineDefinition.UNITY_CATALOG_SURVEY_ENGINE,
                      GovernanceServiceDefinition.UC_CATALOG_SURVEY,
                      "d00bc9af-0d2f-4640-a24b-35d77110883e",
                      null),

    /**
     * survey-unity-catalog-schema
     */
    SURVEY_UC_SCHEMA("survey-unity-catalog-schema",
                     null,
                     null,
                     null,
                     GovernanceEngineDefinition.UNITY_CATALOG_SURVEY_ENGINE,
                     GovernanceServiceDefinition.UC_SCHEMA_SURVEY,
                     "a53211fc-89e6-4405-9768-606d519649ee",
                     null),

    /**
     * survey-unity-catalog-volume
     */
    SURVEY_UC_VOLUME("survey-unity-catalog-volume",
                     null,
                     null,
                     null,
                     GovernanceEngineDefinition.UNITY_CATALOG_SURVEY_ENGINE,
                     GovernanceServiceDefinition.FOLDER_SURVEY,
                     "b62df48b-1390-4cb2-afff-2aa136d8467d",
                     null),

    /**
     * survey-postgres-server
     */
    SURVEY_POSTGRES_SERVER("survey-postgres-server",
                           null,
                           null,
                           null,
                           GovernanceEngineDefinition.POSTGRES_SURVEY_ENGINE,
                           GovernanceServiceDefinition.POSTGRES_SERVER_SURVEY,
                           "fcad7603-bd05-4d07-b6e8-a4fb29fd57fc",
                           null),

    /**
     * survey-postgres-database
     */
    SURVEY_POSTGRES_DATABASE("survey-postgres-database",
                             null,
                             null,
                             null,
                             GovernanceEngineDefinition.POSTGRES_SURVEY_ENGINE,
                             GovernanceServiceDefinition.POSTGRES_DATABASE_SURVEY,
                             "8a7e16eb-15e3-4e16-ba7e-1e8d6653677b",
                             null),


    /**
     * create-software-server
     */
    CREATE_SOFTWARE_SERVER("create-software-server",
                           null,
                           null,
                           null,
                           GovernanceEngineDefinition.ASSET_ONBOARDING_ENGINE,
                           GovernanceServiceDefinition.CREATE_SERVER,
                           "2be30523-5c6a-4c5d-a9ca-595ea491a047",
                           null),

    /**
     * catalog-software-server
     */
    CATALOG_SOFTWARE_SERVER("catalog-software-server",
                            null,
                            null,
                            null,
                            GovernanceEngineDefinition.ASSET_ONBOARDING_ENGINE,
                            GovernanceServiceDefinition.CATALOG_SERVER,
                            "134d6840-9f9d-42bb-bd84-a936b6401541",
                            null),


    /**
     * create-unity-catalog-server
     */
    CREATE_UC_SERVER("create-unity-catalog-server",
                     null,
                     getCreateUnityCatalogRequestParameters(),
                     null,
                     GovernanceEngineDefinition.UNITY_CATALOG_GOVERNANCE_ENGINE,
                     GovernanceServiceDefinition.CREATE_SERVER,
                     "78e47705-a159-4e3d-9199-3a2c9400dcee",
                     null),

    /**
     * catalog-unity-catalog-server
     */
    CATALOG_UC_SERVER("catalog-unity-catalog-server",
                      null,
                      null,
                      getCatalogUnityCatalogActionTargets(),
                      GovernanceEngineDefinition.UNITY_CATALOG_GOVERNANCE_ENGINE,
                      GovernanceServiceDefinition.CATALOG_SERVER,
                      "1b2d71c8-b7f9-4b9b-a466-f20e529391ef",
                      null),


    /**
     * create-postgres-server
     */
    CREATE_POSTGRES_SERVER("create-postgres-server",
                           null,
                           getCreatePostgresServerRequestParameters(),
                           null,
                           GovernanceEngineDefinition.POSTGRES_GOVERNANCE_ENGINE,
                           GovernanceServiceDefinition.CREATE_SERVER,
                           "3facbdba-43c6-44b8-a222-ad0ad2c3c3d5",
                           null),

    /**
     * catalog-postgres-server
     */
    CATALOG_POSTGRES_SERVER("catalog-postgres-server",
                            null,
                            null,
                            getCatalogPostgresServerActionTargets(),
                            GovernanceEngineDefinition.POSTGRES_GOVERNANCE_ENGINE,
                            GovernanceServiceDefinition.CATALOG_SERVER,
                            "dab2303b-7bac-4985-b8eb-4a706e77d036",
                            null),


    ;

    private final String                      governanceRequestType;
    private final String                      serviceRequestType;
    private final Map<String, String>         requestParameters;
    private final List<NewActionTarget>       actionTargets;
    private final GovernanceEngineDefinition  governanceEngine;
    private final GovernanceServiceDefinition governanceService;
    private final String                      governanceActionTypeGUID;
    private final String                      supportedElementQualifiedName;

    /**
     * Set up request parameters.
     *
     * @return map
     */
    static Map<String, String> getFolderSurveyRequestParameters()
    {
        Map<String,String> requestParameters = new HashMap<>();

        requestParameters.put(FolderRequestParameter.ANALYSIS_LEVEL.getName(), "TOP_LEVEL_ONLY");

        return requestParameters;
    }

    /**
     * Set up request parameters.
     *
     * @return map
     */
    static Map<String, String> getAllFoldersSurveyRequestParameters()
    {
        Map<String,String> requestParameters = new HashMap<>();

        requestParameters.put(FolderRequestParameter.ANALYSIS_LEVEL.getName(), "ALL_FOLDERS");

        return requestParameters;
    }

    /**
     * Set up request parameters.
     *
     * @return map
     */
    static Map<String, String> getFolderAndFilesSurveyRequestParameters()
    {
        Map<String,String> requestParameters = new HashMap<>();

        requestParameters.put(FolderRequestParameter.ANALYSIS_LEVEL.getName(), "TOP_LEVEL_AND_FILES");

        return requestParameters;
    }

    /**
     * Set up request parameters.
     *
     * @return map
     */
    static Map<String, String> getAllFolderAndFilesSurveyRequestParameters()
    {
        Map<String,String> requestParameters = new HashMap<>();

        requestParameters.put(FolderRequestParameter.ANALYSIS_LEVEL.getName(), "ALL_FOLDERS_AND_FILES");

        return requestParameters;
    }


    /**
     * Set up request parameters.
     *
     * @return map
     */
    static Map<String, String> getCreateUnityCatalogRequestParameters()
    {
        Map<String,String> requestParameters = new HashMap<>();

        requestParameters.put(CreateServerRequestParameter.TEMPLATE_GUID.getName(), UnityCatalogTemplateType.UC_SERVER_TEMPLATE.getDefaultTemplateGUID());

        return requestParameters;
    }


    /**
     * Return the list of action targets that should be attached to the consuming governance action type.
     *
     * @return list of action targets
     */
    static List<NewActionTarget> getCatalogUnityCatalogActionTargets()
    {
        List<NewActionTarget> actionTargets = new ArrayList<>();

        NewActionTarget newActionTarget = new NewActionTarget();

        newActionTarget.setActionTargetName(ActionTarget.INTEGRATION_CONNECTOR.name);
        newActionTarget.setActionTargetGUID(IntegrationConnectorDefinition.UC_SERVER_CATALOGUER.getGUID());

        actionTargets.add(newActionTarget);

        return actionTargets;
    }


    /**
     * Set up request parameters.
     *
     * @return map
     */
    static Map<String, String> getCreatePostgresServerRequestParameters()
    {
        Map<String,String> requestParameters = new HashMap<>();

        requestParameters.put(CreateServerRequestParameter.TEMPLATE_GUID.getName(), SoftwareServerTemplateDefinition.POSTGRES_SERVER_TEMPLATE.getTemplateGUID());

        return requestParameters;
    }


    /**
     * Return the list of action targets that should be attached to the consuming governance action type.
     *
     * @return list of action targets
     */
    static List<NewActionTarget> getCatalogPostgresServerActionTargets()
    {
        List<NewActionTarget> actionTargets = new ArrayList<>();

        NewActionTarget newActionTarget = new NewActionTarget();

        newActionTarget.setActionTargetName(ActionTarget.INTEGRATION_CONNECTOR.name);
        newActionTarget.setActionTargetGUID(IntegrationConnectorDefinition.POSTGRES_SERVER_CATALOGUER.getGUID());

        actionTargets.add(newActionTarget);

        return actionTargets;
    }


    /**
     * Return the request type enum value.
     *
     * @param governanceRequestType request type used by the caller
     * @param serviceRequestType option map to a request type known by the service
     * @param requestParameters pre-defined request parameters
     * @param actionTargets predefined action targets (for governance action type)
     * @param governanceEngine governance engine that supports this request type
     * @param governanceService governance service that implements this request type
     * @param governanceActionTypeGUID unique identifier of the associated governance action type
     */
    RequestTypeDefinition(String                      governanceRequestType,
                          String                      serviceRequestType,
                          Map<String, String>         requestParameters,
                          List<NewActionTarget>       actionTargets,
                          GovernanceEngineDefinition  governanceEngine,
                          GovernanceServiceDefinition governanceService,
                          String                      governanceActionTypeGUID,
                          String                      supportedElementQualifiedName)
    {
        this.governanceRequestType         = governanceRequestType;
        this.serviceRequestType            = serviceRequestType;
        this.requestParameters             = requestParameters;
        this.actionTargets                 = actionTargets;
        this.governanceEngine              = governanceEngine;
        this.governanceService             = governanceService;
        this.governanceActionTypeGUID      = governanceActionTypeGUID;
        this.supportedElementQualifiedName = supportedElementQualifiedName;
    }


    /**
     * Return the Request Type.
     *
     * @return string
     */
    public String getGovernanceRequestType()
    {
        return governanceRequestType;
    }


    /**
     * Return the service request type to map to.
     *
     * @return string
     */
    public String getServiceRequestType()
    {
        return serviceRequestType;
    }


    /**
     * Return the request parameters (if needed).
     *
     * @return map or null
     */
    public Map<String, String> getRequestParameters()
    {
        return requestParameters;
    }


    /**
     * Return predefined action targets used by this service.  They are attached to the governance action type.
     *
     * @return list
     */
    public List<NewActionTarget> getActionTargets()
    {
        return actionTargets;
    }


    /**
     * Return the governance engine where this request type belongs to.
     *
     * @return governance engine definition enum
     */
    public GovernanceEngineDefinition getGovernanceEngine()
    {
        return governanceEngine;
    }


    /**
     * Return the governance service that this request type maps to,
     *
     * @return governance service definition enum
     */
    public GovernanceServiceDefinition getGovernanceService()
    {
        return governanceService;
    }


    /**
     * Return the unique identifier of the governance action type.
     *
     * @return string
     */
    public String getGovernanceActionTypeGUID()
    {
        return governanceActionTypeGUID;
    }


    /**
     * Return the element that is supported by this request.
     *
     * @return qualified name string
     */
    public String getSupportedElementQualifiedName()
    {
        return supportedElementQualifiedName;
    }


    /**
     * Output of this enum class and main value.
     *
     * @return string showing enum value
     */
    @Override
    public String toString()
    {
        return "RequestTypeDefinition{" + "name='" + governanceRequestType + '\'' + "}";
    }
}
