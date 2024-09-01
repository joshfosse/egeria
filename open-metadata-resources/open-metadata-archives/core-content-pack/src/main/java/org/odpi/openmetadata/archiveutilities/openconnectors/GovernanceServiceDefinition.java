/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */

package org.odpi.openmetadata.archiveutilities.openconnectors;


import org.odpi.openmetadata.adapters.connectors.apacheatlas.survey.SurveyApacheAtlasProvider;
import org.odpi.openmetadata.adapters.connectors.apachekafka.survey.SurveyApacheKafkaServerProvider;
import org.odpi.openmetadata.adapters.connectors.governanceactions.provisioning.MoveCopyFileGovernanceActionProvider;
import org.odpi.openmetadata.adapters.connectors.governanceactions.remediation.OriginSeekerGovernanceActionProvider;
import org.odpi.openmetadata.adapters.connectors.governanceactions.remediation.QualifiedNamePeerDuplicateGovernanceActionProvider;
import org.odpi.openmetadata.adapters.connectors.governanceactions.remediation.RetentionClassifierGovernanceActionProvider;
import org.odpi.openmetadata.adapters.connectors.governanceactions.remediation.ZonePublisherGovernanceActionProvider;
import org.odpi.openmetadata.adapters.connectors.governanceactions.stewardship.*;
import org.odpi.openmetadata.adapters.connectors.governanceactions.verification.VerifyAssetGovernanceActionProvider;
import org.odpi.openmetadata.adapters.connectors.governanceactions.watchdog.GenericFolderWatchdogGovernanceActionProvider;
import org.odpi.openmetadata.adapters.connectors.postgres.survey.PostgresDatabaseSurveyActionProvider;
import org.odpi.openmetadata.adapters.connectors.postgres.survey.PostgresServerSurveyActionProvider;
import org.odpi.openmetadata.adapters.connectors.surveyaction.surveycsv.CSVSurveyServiceProvider;
import org.odpi.openmetadata.adapters.connectors.surveyaction.surveyfile.FileSurveyServiceProvider;
import org.odpi.openmetadata.adapters.connectors.surveyaction.surveyfolder.FolderSurveyServiceProvider;
import org.odpi.openmetadata.adapters.connectors.unitycatalog.survey.OSSUnityCatalogInsideCatalogSurveyProvider;
import org.odpi.openmetadata.adapters.connectors.unitycatalog.survey.OSSUnityCatalogInsideSchemaSurveyProvider;
import org.odpi.openmetadata.adapters.connectors.unitycatalog.survey.OSSUnityCatalogServerSurveyProvider;
import org.odpi.openmetadata.frameworks.governanceaction.GovernanceServiceProviderBase;
import org.odpi.openmetadata.frameworks.openmetadata.refdata.DeployedImplementationType;
import org.odpi.openmetadata.frameworks.openmetadata.refdata.ResourceUse;
import org.odpi.openmetadata.frameworks.surveyaction.SurveyActionServiceProvider;
import org.odpi.openmetadata.samples.archiveutilities.GovernanceActionDescription;

/**
 * Define the Governance Action Services configuration shipped with Egeria.
 */
public enum GovernanceServiceDefinition
{
    /**
     * File {move, copy, delete} Governance Action Service
     */
    FILE_PROVISIONER("bc302284-d423-47fa-86f3-0c6fd9892535",
                     "file-provisioning-governance-action-service",
                     "File {move, copy, delete} Governance Action Service",
                     new MoveCopyFileGovernanceActionProvider(),
                     ResourceUse.PROVISION_RESOURCE,
                     DeployedImplementationType.GOVERNANCE_ACTION_SERVICE_CONNECTOR),

    /**
     * New Files Watchdog Governance Action Service
     */
    NEW_FILES_WATCHDOG("16ba9235-ae9f-4754-8430-5ffbce7da6a0",
                       "new-files-watchdog-governance-action-service",
                       "New Files Watchdog Governance Action Service",
                       new GenericFolderWatchdogGovernanceActionProvider(),
                       ResourceUse.WATCH_DOG,
                       DeployedImplementationType.GOVERNANCE_ACTION_SERVICE_CONNECTOR),

    /**
     * Locate and Set Origin Governance Action Service -
     * Navigates back through the lineage relationships to locate the origin classification(s) from the source(s) and sets it on the requested asset if the origin is unique.
     */
    ORIGIN_SEEKER("e1044fa8-18c7-44bf-ac19-65945f3d2333",
                  "origin-seeker-governance-action-service",
                  "Locate and Set Origin Governance Action Service",
                  new OriginSeekerGovernanceActionProvider(),
                  ResourceUse.IMPROVE_METADATA,
                  DeployedImplementationType.GOVERNANCE_ACTION_SERVICE_CONNECTOR),

    /**
     * Add a Retention classification to an Asset Governance Action Service
     */
    RETENTION_CLASSIFIER("613dd0b5-5ced-4ad1-9269-51f5138aaf7c",
                         "retention-classifier-governance-action-service",
                         "Add a Retention classification to an Asset Governance Action Service",
                         new RetentionClassifierGovernanceActionProvider(),
                         ResourceUse.IMPROVE_METADATA,
                         DeployedImplementationType.GOVERNANCE_ACTION_SERVICE_CONNECTOR),

    /**
     * Update Asset's Zone Membership Governance Action Service
     */
    ZONE_PUBLISHER("4718325b-ae49-4378-97d7-8b283309c3ce",
                   "zone-publisher-governance-action-service",
                   "Update Asset's Zone Membership Governance Action Service",
                   new ZonePublisherGovernanceActionProvider(),
                   ResourceUse.IMPROVE_METADATA,
                   DeployedImplementationType.GOVERNANCE_ACTION_SERVICE_CONNECTOR),

    /**
     * Detect elements with the same qualified names.
     */
    QUALIFIED_NAME_DEDUP("3023fa37-89eb-42d2-a637-d600b408dcf3",
                         "qualified-name-deduplication-governance-action-service",
                         "Qualified Name De-duplicator Governance Action Service",
                         new QualifiedNamePeerDuplicateGovernanceActionProvider(),
                         ResourceUse.IMPROVE_METADATA,
                         DeployedImplementationType.GOVERNANCE_ACTION_SERVICE_CONNECTOR),

    /**
     * Verify annotations in a Survey Report.
     */
    EVALUATE_ANNOTATIONS("16026ffb-2147-42c5-9dbd-362ff7aa58af",
                         "evaluate-annotations-governance-action-service",
                         "Verify annotations in a Survey Report",
                         new EvaluateAnnotationsGovernanceActionProvider(),
                         ResourceUse.IMPROVE_METADATA,
                         DeployedImplementationType.GOVERNANCE_ACTION_SERVICE_CONNECTOR),

    /**
     * Write an Audit Log Message
     */
    WRITE_AUDIT_LOG("6898b138-9ab0-4465-9ee8-ed9607dcc4f8",
                    "write-audit-log-governance-action-service",
                    "Write an Audit Log Message",
                    new WriteAuditLogMessageGovernanceActionProvider(),
                    ResourceUse.INFORM_STEWARD,
                    DeployedImplementationType.GOVERNANCE_ACTION_SERVICE_CONNECTOR),

    /**
     * Detect the day of the week
     */
    DAY_OF_WEEK("a4837c75-aa3f-49c6-a903-ef0da0017546",
                "get-day-of-week-governance-action-service",
                "Detect the day of the week",
                new DaysOfWeekGovernanceActionProvider(),
                ResourceUse.CHOOSE_PATH,
                DeployedImplementationType.GOVERNANCE_ACTION_SERVICE_CONNECTOR),


    /**
     * Verify an Asset Governance Action Service
     */
    VERIFY_ASSET("9c5b74a0-148b-4623-9c50-8a2f6072bb0a",
                 "verify-asset-governance-action-service",
                 "Verify an Asset Governance Action Service",
                 new VerifyAssetGovernanceActionProvider(),
                 ResourceUse.IMPROVE_METADATA,
                 DeployedImplementationType.GOVERNANCE_ACTION_SERVICE_CONNECTOR),

    /**
     * Discovers columns within a CSV File.
     */
    CSV_FILE_SURVEY("e8edf32a-ec39-437a-8deb-1e32355fea38",
                    "csv-file-survey-service",
                    "CSV File Survey Service",
                    new CSVSurveyServiceProvider(),
                    ResourceUse.SURVEY_RESOURCE,
                    DeployedImplementationType.SURVEY_ACTION_SERVICE_CONNECTOR),

    /**
     * Discovers the name, extension, file type and other characteristics of a file.
     */
    DATA_FILE_SURVEY("8e27e53b-a827-487e-b444-719545c93be5",
                     "data-file-survey-service",
                     "Data File Survey Service",
                     new FileSurveyServiceProvider(),
                     ResourceUse.SURVEY_RESOURCE,
                     DeployedImplementationType.SURVEY_ACTION_SERVICE_CONNECTOR),

    /**
     * Discovers the types of files located within a file system directory (and its sub-directories).
     */
    FOLDER_SURVEY("0f89211c-15bf-4c69-9d29-0dac3be1fa8b",
                  "folder-survey-service",
                  "Folder (directory) Survey Service",
                  new FolderSurveyServiceProvider(),
                  ResourceUse.SURVEY_RESOURCE,
                  DeployedImplementationType.SURVEY_ACTION_SERVICE_CONNECTOR),

    /**
     * Apache Atlas Survey Service
     */
    APACHE_ATLAS_SURVEY("ff973b80-d7aa-4c07-a318-be848d4d0b91",
                        "apache-atlas-survey-service",
                        "Apache Atlas Survey Service",
                        new SurveyApacheAtlasProvider(),
                        ResourceUse.SURVEY_RESOURCE,
                        DeployedImplementationType.SURVEY_ACTION_SERVICE_CONNECTOR),

    /**
     * OSS Unity Catalog Server Survey Service
     */
    UC_SERVER_SURVEY("8285b149-5419-4cee-94d2-12eae983c605",
                     "oss-unity-catalog-server-survey-service",
                     "OSS Unity Catalog Server Survey Service",
                     new OSSUnityCatalogServerSurveyProvider(),
                     ResourceUse.SURVEY_RESOURCE,
                     DeployedImplementationType.SURVEY_ACTION_SERVICE_CONNECTOR),

    /**
     * OSS Unity Catalog Inside Catalog Survey Service
     */
    UC_CATALOG_SURVEY("6f15ffd6-1cbb-4764-a868-7186a92f4b3e",
                      "oss-unity-catalog-inside-catalog-survey-service",
                      "OSS Unity Catalog Inside Catalog Survey Service",
                      new OSSUnityCatalogInsideCatalogSurveyProvider(),
                      ResourceUse.SURVEY_RESOURCE,
                      DeployedImplementationType.SURVEY_ACTION_SERVICE_CONNECTOR),

    /**
     * OSS Unity Catalog Inside Schema Survey Service
     */
    UC_SCHEMA_SURVEY("4227dd15-272e-4be9-8918-33df80d5c271",
                     "oss-unity-catalog-inside-schema-survey-service",
                     "OSS Unity Catalog Inside Schema Survey Service",
                     new OSSUnityCatalogInsideSchemaSurveyProvider(),
                     ResourceUse.SURVEY_RESOURCE,
                     DeployedImplementationType.SURVEY_ACTION_SERVICE_CONNECTOR),

    /**
     * PostgreSQL Server Survey Service
     */
    POSTGRES_SERVER_SURVEY("7ca400d7-ff64-42b0-981e-2a5422818cfc",
                           "postgres-server-survey-service",
                           "PostgreSQL Server Survey Service",
                           new PostgresServerSurveyActionProvider(),
                           ResourceUse.SURVEY_RESOURCE,
                           DeployedImplementationType.SURVEY_ACTION_SERVICE_CONNECTOR),

    /**
     * PostgreSQL Database Survey Service
     */
    POSTGRES_DATABASE_SURVEY("08542267-97ec-4bdb-a15a-f5045af758e0",
                             "postgres-database-survey-service",
                             "PostgreSQL Database Survey Service",
                             new PostgresDatabaseSurveyActionProvider(),
                            ResourceUse.SURVEY_RESOURCE,
                             DeployedImplementationType.SURVEY_ACTION_SERVICE_CONNECTOR),


    /**
     * Apache Kafka Server Survey Service
     */
    KAFKA_SERVER_SURVEY("540c7db1-20ce-4c4c-a98c-de3e41390c1e",
                        "kafka-server-survey-service",
                        "Apache Kafka Server Survey Service",
                        new SurveyApacheKafkaServerProvider(),
                        ResourceUse.SURVEY_RESOURCE,
                        DeployedImplementationType.SURVEY_ACTION_SERVICE_CONNECTOR),


    /**
     * Create Server Governance Service
     */
    CREATE_SERVER("e971749f-4b0e-4c46-b4dd-ca0cf8df3900",
                        "create-server-governance-service",
                        "Create Server Governance Service",
                        new CreateServerGovernanceActionProvider(),
                        ResourceUse.CATALOG_RESOURCE,
                        DeployedImplementationType.GOVERNANCE_ACTION_SERVICE_CONNECTOR),

    /**
     * Catalog Server Governance Service
     */
    CATALOG_SERVER("96a01919-a361-404d-bcb7-3855fbbc8cd5",
                   "catalog-server-governance-service",
                   "Catalog Server Governance Service",
                   new CatalogServerGovernanceActionProvider(),
                   ResourceUse.CATALOG_RESOURCE,
                   DeployedImplementationType.GOVERNANCE_ACTION_SERVICE_CONNECTOR),
    ;
    
    private final String            guid;
    private final String            name;
    private final String            displayName;
    private final GovernanceServiceProviderBase connectorProvider;
    private final ResourceUse resourceUse;
    private final DeployedImplementationType deployedImplementationType;

    GovernanceServiceDefinition(String                        guid,
                                String                        name,
                                String                        displayName,
                                GovernanceServiceProviderBase connectorProvider,
                                ResourceUse                   resourceUse,
                                DeployedImplementationType    deployedImplementationType)
    {
        this.guid              = guid;
        this.name              = name;
        this.displayName       = displayName;
        this.connectorProvider = connectorProvider;
        this.resourceUse       = resourceUse;
        this.deployedImplementationType = deployedImplementationType;
    }

    /**
     * Return the unique identifier of the governance service.
     *
     * @return string
     */
    public String getGUID()
    {
        return guid;
    }


    /**
     * Return the unique name of the governance service.
     *
     * @return string
     */
    public String getName()
    {
        return name;
    }


    /**
     * Return the display name of the governance service.
     *
     * @return string
     */
    public String getDisplayName()
    {
        return displayName;
    }


    /**
     * Return the description of the governance service.
     *
     * @return string
     */
    public String getDescription()
    {
        return connectorProvider.getConnectorType().getDescription();
    }


    /**
     * Return the name of the governance service provider implementation class.
     *
     * @return string
     */
    public String getConnectorProviderClassName()
    {
        return connectorProvider.getClass().getName();
    }


    /**
     * Return the deployed implementation type for the service.
     *
     * @return DeployedImplementationType enum
     */
    public DeployedImplementationType getDeployedImplementationType()
    {
        return deployedImplementationType;
    }

    /**
     * Create a governance action description from the governance service's provider.
     *
     * @return governance action description
     */
    public GovernanceActionDescription getGovernanceActionDescription()
    {
        GovernanceActionDescription governanceActionDescription = new GovernanceActionDescription();

        governanceActionDescription.governanceServiceGUID        = guid;
        governanceActionDescription.resourceUse                  = resourceUse;
        governanceActionDescription.supportedTechnologies        = connectorProvider.getSupportedTechnologyTypes();
        governanceActionDescription.supportedRequestTypes        = connectorProvider.getSupportedRequestTypes();
        governanceActionDescription.supportedRequestParameters   = connectorProvider.getSupportedRequestParameters();
        governanceActionDescription.supportedActionTargets       = connectorProvider.getSupportedActionTargetTypes();
        governanceActionDescription.producedRequestParameters    = connectorProvider.getProducedRequestParameters();
        governanceActionDescription.producedActionTargets        = connectorProvider.getProducedActionTargetTypes();
        governanceActionDescription.producedGuards               = connectorProvider.getProducedGuards();

        if (connectorProvider instanceof SurveyActionServiceProvider surveyActionServiceProvider)
        {
            governanceActionDescription.supportedAnalysisSteps = surveyActionServiceProvider.getSupportedAnalysisSteps();
            governanceActionDescription.supportedAnnotationTypes = surveyActionServiceProvider.getProducedAnnotationTypes();
        }

        governanceActionDescription.governanceServiceDescription = this.getDescription();

        return governanceActionDescription;
    }


    /**
     * Output of this enum class and main value.
     *
     * @return string showing enum value
     */
    @Override
    public String toString()
    {
        return "GovernanceServiceDefinition{" + "name='" + name + '\'' + "}";
    }
}
