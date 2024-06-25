/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.archiveutilities.governanceengines;

import org.odpi.openmetadata.adapters.connectors.governanceactions.provisioning.MoveCopyFileGovernanceActionProvider;
import org.odpi.openmetadata.adapters.connectors.governanceactions.remediation.OriginSeekerGovernanceActionProvider;
import org.odpi.openmetadata.adapters.connectors.governanceactions.remediation.ZonePublisherGovernanceActionProvider;
import org.odpi.openmetadata.adapters.connectors.governanceactions.stewardship.WriteAuditLogRequestParameter;
import org.odpi.openmetadata.adapters.connectors.governanceactions.watchdog.GenericFolderWatchdogGovernanceActionProvider;
import org.odpi.openmetadata.frameworks.openmetadata.types.OpenMetadataType;
import org.odpi.openmetadata.frameworks.openmetadata.refdata.DeployedImplementationType;
import org.odpi.openmetadata.samples.archiveutilities.combo.CocoBaseArchiveWriter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * CocoGovernanceEnginesArchiveWriter creates a physical open metadata archive file containing the governance engine definitions
 * needed by Coco Pharmaceuticals.
 */
public class CocoGovernanceEnginesArchiveWriter extends CocoBaseArchiveWriter
{
    private static final String archiveFileName = "CocoGovernanceEngineDefinitionsArchive.omarchive";

    /*
     * This is the header information for the archive.
     */
    private static final String                  archiveGUID        = "9cbd2b33-e80f-4df2-adc6-d859ebff4c34";
    private static final String                  archiveName        = "CocoGovernanceEngineDefinitions";
    private static final String                  archiveDescription = "Governance Engines for Coco Pharmaceuticals.";


    /**
     * Default constructor initializes the archive.
     */
    public CocoGovernanceEnginesArchiveWriter()
    {
        super(archiveGUID,
              archiveName,
              archiveDescription,
              new Date(),
              archiveFileName,
              null);
    }


    /**
     * Create an entity for the AssetGovernance governance engine.
     *
     * @return unique identifier for the governance engine
     */
    private String getAssetGovernanceEngine()
    {
        final String assetGovernanceEngineName        = "AssetGovernance";
        final String assetGovernanceEngineDisplayName = "AssetGovernance Governance Action Engine";
        final String assetGovernanceEngineDescription = "Monitors, validates and enriches metadata relating to assets.";

        return archiveHelper.addGovernanceEngine(OpenMetadataType.GOVERNANCE_ACTION_ENGINE.typeName,
                                                 assetGovernanceEngineName,
                                                 assetGovernanceEngineDisplayName,
                                                 assetGovernanceEngineDescription,
                                                 null,
                                                 null,
                                                 null,
                                                 null,
                                                 null,
                                                 null);
    }


    /**
     * Create an entity for the AssetDiscovery governance engine.
     *
     * @return unique identifier for the governance engine
     */
    private String getAssetDiscoveryEngine()
    {
        final String assetDiscoveryEngineName        = "AssetDiscovery";
        final String assetDiscoveryEngineDisplayName = "AssetDiscovery Survey Action Engine";
        final String assetDiscoveryEngineDescription = "Extracts metadata about a digital resource and attach it to its asset description.";

        return archiveHelper.addGovernanceEngine(OpenMetadataType.SURVEY_ACTION_ENGINE.typeName,
                                                 assetDiscoveryEngineName,
                                                 assetDiscoveryEngineDisplayName,
                                                 assetDiscoveryEngineDescription,
                                                 null,
                                                 null,
                                                 null,
                                                 null,
                                                 null,
                                                 null);
    }


    /**
     * Create an entity for the AssetQuality governance engine.
     *
     * @return unique identifier for the governance engine
     */
    private String getAssetQualityEngine()
    {
        final String assetQualityEngineName        = "AssetQuality";
        final String assetQualityEngineDisplayName = "AssetQuality Survey Action Engine";
        final String assetQualityEngineDescription = "Assess the quality of a digital resource identified by the asset in the request.";

        return archiveHelper.addGovernanceEngine(OpenMetadataType.SURVEY_ACTION_ENGINE.typeName,
                                                 assetQualityEngineName,
                                                 assetQualityEngineDisplayName,
                                                 assetQualityEngineDescription,
                                                 null,
                                                 null,
                                                 null,
                                                 null,
                                                 null,
                                                 null);
    }


    /**
     * Create an entity for the FileProvisioning governance action service.
     *
     * @return unique identifier for the governance engine
     */
    private String getFileProvisioningGovernanceActionService()
    {
        final String governanceServiceName        = "coco-file-provisioning-governance-action-service";
        final String governanceServiceDisplayName = "File {move, copy, delete} Governance Action Service";
        final String governanceServiceDescription = "Works with files.  The request type defines which action is taken.  " +
                "The request parameters define the source file and destination, along with lineage options";
        final String ftpGovernanceServiceProviderClassName = MoveCopyFileGovernanceActionProvider.class.getName();

        return archiveHelper.addGovernanceService(DeployedImplementationType.GOVERNANCE_ACTION_SERVICE_CONNECTOR,
                                                  ftpGovernanceServiceProviderClassName,
                                                  null,
                                                  governanceServiceName,
                                                  governanceServiceDisplayName,
                                                  governanceServiceDescription,
                                                  null);
    }


    /**
     * Create an entity for the generic watchdog governance action service.
     *
     * @return unique identifier for the governance engine
     */
    private String getWatchdogGovernanceActionService()
    {
        final String governanceServiceName = "coco-new-measurements-watchdog-governance-action-service";
        final String governanceServiceDisplayName = "New Measurements Watchdog Governance Action Service";
        final String governanceServiceDescription = "Initiates a governance action process when a new weekly measurements file arrives.";
        final String governanceServiceProviderClassName = GenericFolderWatchdogGovernanceActionProvider.class.getName();

        return archiveHelper.addGovernanceService(DeployedImplementationType.GOVERNANCE_ACTION_SERVICE_CONNECTOR,
                                                  governanceServiceProviderClassName,
                                                  null,
                                                  governanceServiceName,
                                                  governanceServiceDisplayName,
                                                  governanceServiceDescription,
                                                  null);
    }


    /**
     * Add a governance service that walks backwards through an asset's lineage to find an origin classification.  If found, the same origin is added
     * to the asset.
     *
     * @return unique identifier fo the governance service
     */
    private String getZonePublisherGovernanceActionService()
    {
        final String governanceServiceName = "coco-zone-publisher-governance-action-service";
        final String governanceServiceDisplayName = "Update Asset's Zone Membership Governance Action Service";
        final String governanceServiceDescription = "Set up the zone membership for one or more assets supplied as action targets.";
        final String governanceServiceProviderClassName = ZonePublisherGovernanceActionProvider.class.getName();

        return archiveHelper.addGovernanceService(DeployedImplementationType.GOVERNANCE_ACTION_SERVICE_CONNECTOR,
                                                  governanceServiceProviderClassName,
                                                  null,
                                                  governanceServiceName,
                                                  governanceServiceDisplayName,
                                                  governanceServiceDescription,
                                                  null);
    }


    /**
     * Set up the request type that links the governance engine to the governance service.
     *
     * @return unique identifier fo the governance service
     */
    private String getOriginSeekerGovernanceActionService()
    {
        final String governanceServiceName = "coco-origin-seeker-governance-action-service";
        final String governanceServiceDisplayName = "Locate and Set Origin Governance Action Service";
        final String governanceServiceDescription = "Navigates back through the lineage relationships to locate the origin classification(s) from the source(s) and sets it on the requested asset if the origin is unique.";
        final String governanceServiceProviderClassName = OriginSeekerGovernanceActionProvider.class.getName();

        return archiveHelper.addGovernanceService(DeployedImplementationType.GOVERNANCE_ACTION_SERVICE_CONNECTOR,
                                                  governanceServiceProviderClassName,
                                                  null,
                                                  governanceServiceName,
                                                  governanceServiceDisplayName,
                                                  governanceServiceDescription,
                                                  null);
    }


    /**
     * Set up the request type that links the governance engine to the governance service.
     *
     * @param governanceEngineGUID unique identifier of the governance engine
     * @param governanceServiceGUID unique identifier of the governance service
     */
    private void addFTPFileRequestType(String governanceEngineGUID,
                                       String governanceServiceGUID)
    {
        final String governanceRequestType = "simulate-ftp";
        final String serviceRequestType = "copy-file";
        final String noLineagePropertyName = "noLineage";

        Map<String, String> requestParameters = new HashMap<>();

        requestParameters.put(noLineagePropertyName, "");

        archiveHelper.addSupportedGovernanceService(governanceEngineGUID, governanceRequestType, serviceRequestType, requestParameters, governanceServiceGUID);
    }


    /**
     * Set up the request type that links the governance engine to the governance service.
     *
     * @param governanceEngineGUID unique identifier of the governance engine
     * @param governanceServiceGUID unique identifier of the governance service
     */
    private void addWatchNestedInFolderRequestType(String governanceEngineGUID,
                                                   String governanceServiceGUID)
    {
        final String governanceRequestType = "watch-for-new-files";
        final String serviceRequestType = "watch-nested-in-folder";

        archiveHelper.addSupportedGovernanceService(governanceEngineGUID, governanceRequestType, serviceRequestType, null, governanceServiceGUID);
    }


    /**
     * Set up the request type that links the governance engine to the governance service.
     *
     * @param governanceEngineGUID unique identifier of the governance engine
     * @param governanceServiceGUID unique identifier of the governance service
     */
    private void addCopyFileRequestType(String governanceEngineGUID,
                                        String governanceServiceGUID)
    {
        final String governanceRequestType = "copy-file";

        archiveHelper.addSupportedGovernanceService(governanceEngineGUID, governanceRequestType, null, null, governanceServiceGUID);
    }


    /**
     * Set up the request type that links the governance engine to the governance service.
     *
     * @param governanceEngineGUID unique identifier of the governance engine
     * @param governanceServiceGUID unique identifier of the governance service
     */
    private void addMoveFileRequestType(String governanceEngineGUID,
                                        String governanceServiceGUID)
    {
        final String governanceRequestType = "move-file";

        archiveHelper.addSupportedGovernanceService(governanceEngineGUID, governanceRequestType, null, null, governanceServiceGUID);
    }



    /**
     * Set up the request type that links the governance engine to the governance service.
     *
     * @param governanceEngineGUID unique identifier of the governance engine
     * @param governanceServiceGUID unique identifier of the governance service
     */
    private void addDeleteFileRequestType(String governanceEngineGUID,
                                          String governanceServiceGUID)
    {
        final String governanceRequestType = "delete-file";

        archiveHelper.addSupportedGovernanceService(governanceEngineGUID, governanceRequestType, null, null, governanceServiceGUID);
    }


    /**
     * Set up the request type that links the governance engine to the governance service.
     *
     * @param governanceEngineGUID unique identifier of the governance engine
     * @param governanceServiceGUID unique identifier of the governance service
     */
    private void addSeekOriginRequestType(String governanceEngineGUID,
                                          String governanceServiceGUID)
    {
        final String governanceServiceRequestType = "seek-origin";

        archiveHelper.addSupportedGovernanceService(governanceEngineGUID, governanceServiceRequestType, null, null, governanceServiceGUID);
    }



    /**
     * Set up the request type that links the governance engine to the governance service.
     *
     * @param governanceEngineGUID unique identifier of the governance engine
     * @param governanceServiceGUID unique identifier of the governance service
     */
    private void addSetZoneMembershipRequestType(String governanceEngineGUID,
                                                 String governanceServiceGUID)
    {
        final String governanceServiceRequestType = "set-zone-membership";

        archiveHelper.addSupportedGovernanceService(governanceEngineGUID, governanceServiceRequestType, null, null, governanceServiceGUID);
    }


    /**
     * Create the onboarding process for clinical trials.
     */
    private void addOnboardingGovernanceActionProcess()
    {
        String governanceEngineGUID = archiveHelper.getGUID("AssetOnboarding");

        String qualifiedName = "Coco:GovernanceActionProcess:ClinicalTrials:DropFoot:WeeklyMeasurements:Onboarding";

        String processGUID = archiveHelper.addGovernanceActionProcess(OpenMetadataType.GOVERNANCE_ACTION_PROCESS_TYPE_NAME,
                                                                      qualifiedName,
                                                                      "Onboard Weekly Drop Foot Measurement Files",
                                                                      "V1.0",
                                                                      """
                                                                              Ensures that new weekly drop foot measurement files from the hospitals are correctly catalogued in the data lake.

                                                                              This process performs the follow function:
                                                                                   1) The physical file is moved to the data lake and renamed,
                                                                                   2) A new asset is created for the new file,
                                                                                   3) Lineage is created between the original file asset and the new file asset,
                                                                                   4) The owner and origin are assigned,
                                                                                   5) The governance zones are assigned to make the new asset visible to the research team.""",
                                                                      null,
                                                                      0,
                                                                      null,
                                                                      null,
                                                                      null);

        String step1GUID = archiveHelper.addGovernanceActionProcessStep(OpenMetadataType.GOVERNANCE_ACTION_PROCESS_STEP_TYPE_NAME,
                                                                        processGUID,
                                                                        OpenMetadataType.GOVERNANCE_ACTION_PROCESS_TYPE_NAME,
                                                                        OpenMetadataType.ASSET.typeName,
                                                                        qualifiedName + ":MoveWeeklyMeasurementsFile",
                                                                        "Move Weekly Measurements File",
                                                                        "The physical file is moved to the data lake and renamed, an asset is created for the new file (in the quarantine zone) and a lineage relationship is created between the original file asset and the new file asset.",
                                                                        0,
                                                                        null,
                                                                        null,
                                                                        null,
                                                                        null,
                                                                        null,
                                                                        null,
                                                                        null,
                                                                        0,
                                                                        true,
                                                                        null,
                                                                        null,
                                                                        null);

        if (step1GUID != null)
        {
            archiveHelper.addGovernanceActionExecutor(step1GUID,
                                                      "move-file",
                                                      null,
                                                      null,
                                                      null,
                                                      null,
                                                      null,
                                                      governanceEngineGUID);

            archiveHelper.addGovernanceActionProcessFlow(processGUID,
                                                         null,
                                                         step1GUID);
        }

        String step2GUID = archiveHelper.addGovernanceActionProcessStep(OpenMetadataType.GOVERNANCE_ACTION_PROCESS_STEP_TYPE_NAME,
                                                                        processGUID,
                                                                        OpenMetadataType.GOVERNANCE_ACTION_PROCESS_TYPE_NAME,
                                                                        OpenMetadataType.ASSET.typeName,
                                                                        "Egeria:DailyGovernanceActionProcess:MondayTask",
                                                                        "Output Monday's task",
                                                                        null,
                                                                        0,
                                                                        null,
                                                                        null,
                                                                        null,
                                                                        null,
                                                                        null,
                                                                        null,
                                                                        null,
                                                                        0,
                                                                        true,
                                                                        null,
                                                                        null,
                                                                        null);

        if (step2GUID != null)
        {
            Map<String, String> requestParameters = new HashMap<>();

            requestParameters.put(WriteAuditLogRequestParameter.MESSAGE_TEXT.getName(), "Action For Monday is: Wash");
            archiveHelper.addGovernanceActionExecutor(step2GUID,
                                                      "write-to-audit-log",
                                                      requestParameters,
                                                      null,
                                                      null,
                                                      null,
                                                      null,
                                                      governanceEngineGUID);

            archiveHelper.addNextGovernanceActionProcessStep(step1GUID, "monday", false, step2GUID);
        }

        String step3GUID = archiveHelper.addGovernanceActionProcessStep(OpenMetadataType.GOVERNANCE_ACTION_PROCESS_STEP_TYPE_NAME,
                                                                        processGUID,
                                                                        OpenMetadataType.GOVERNANCE_ACTION_PROCESS_TYPE_NAME,
                                                                        OpenMetadataType.ASSET.typeName,
                                                                        "Egeria:DailyGovernanceActionProcess:TuesdayTask",
                                                                        "Output Tuesday's task",
                                                                        null,
                                                                        0,
                                                                        null,
                                                                        null,
                                                                        null,
                                                                        null,
                                                                        null,
                                                                        null,
                                                                        null,
                                                                        0,
                                                                        true,
                                                                        null,
                                                                        null,
                                                                        null);

        if (step3GUID != null)
        {
            Map<String, String> requestParameters = new HashMap<>();

            requestParameters.put(WriteAuditLogRequestParameter.MESSAGE_TEXT.getName(), "Action For Tuesday is: Iron");
            archiveHelper.addGovernanceActionExecutor(step3GUID,
                                                      "write-to-audit-log",
                                                      requestParameters,
                                                      null,
                                                      null,
                                                      null,
                                                      null,
                                                      governanceEngineGUID);

            archiveHelper.addNextGovernanceActionProcessStep(step1GUID, "tuesday", false, step3GUID);
        }
    }


    /**
     * Add the content to the archive builder.
     */
    public void getArchiveContent()
    {
        /*
         * Create governance services
         */
        String fileProvisionerGUID = this.getFileProvisioningGovernanceActionService();
        String watchDogServiceGUID = this.getWatchdogGovernanceActionService();
        String originSeekerGUID = this.getOriginSeekerGovernanceActionService();
        String zonePublisherGUID = this.getZonePublisherGovernanceActionService();

        String assetGovernanceEngineGUID = this.getAssetGovernanceEngine();

        this.addFTPFileRequestType(assetGovernanceEngineGUID, fileProvisionerGUID);
        this.addWatchNestedInFolderRequestType(assetGovernanceEngineGUID, watchDogServiceGUID);
        this.addSeekOriginRequestType(assetGovernanceEngineGUID, originSeekerGUID);
        this.addSetZoneMembershipRequestType(assetGovernanceEngineGUID, zonePublisherGUID);
        this.addCopyFileRequestType(assetGovernanceEngineGUID, fileProvisionerGUID);
        this.addMoveFileRequestType(assetGovernanceEngineGUID, fileProvisionerGUID);
        this.addDeleteFileRequestType(assetGovernanceEngineGUID, fileProvisionerGUID);

        this.addOnboardingGovernanceActionProcess();

        String assetDiscoveryEngineGUID = this.getAssetDiscoveryEngine();

        String assetQualityEngineGUID = this.getAssetQualityEngine();
        // todo add services when they written

    }

    /**
     * Generates and writes out an open metadata archive for Coco Pharmaceuticals governance engines.
     */
    public void writeOpenMetadataArchive()
    {
        try
        {
            System.out.println("Writing to file: " + archiveFileName);

            super.writeOpenMetadataArchive(archiveFileName, this.getOpenMetadataArchive());
        }
        catch (Exception error)
        {
            System.out.println("error is " + error);
        }
    }
}
