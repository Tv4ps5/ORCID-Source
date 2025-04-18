package org.orcid.core.manager.v3.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.orcid.core.manager.ClientDetailsEntityCacheManager;
import org.orcid.core.manager.ProfileEntityCacheManager;
import org.orcid.core.manager.v3.NotificationManager;
import org.orcid.core.manager.v3.OrcidSecurityManager;
import org.orcid.core.manager.v3.OrgManager;
import org.orcid.core.manager.v3.ProfileFundingManager;
import org.orcid.core.manager.v3.SourceManager;
import org.orcid.core.manager.v3.read_only.impl.ProfileFundingManagerReadOnlyImpl;
import org.orcid.core.manager.v3.validator.ActivityValidator;
import org.orcid.core.messaging.JmsMessageSender;
import org.orcid.core.utils.DisplayIndexCalculatorHelper;
import org.orcid.core.utils.SourceEntityUtils;
import org.orcid.jaxb.model.common.ActionType;
import org.orcid.jaxb.model.v3.release.common.Source;
import org.orcid.jaxb.model.v3.release.common.Visibility;
import org.orcid.jaxb.model.v3.release.notification.amended.AmendedSection;
import org.orcid.jaxb.model.v3.release.notification.permission.Item;
import org.orcid.jaxb.model.v3.release.notification.permission.ItemType;
import org.orcid.jaxb.model.v3.release.record.Funding;
import org.orcid.persistence.dao.FundingSubTypeToIndexDao;
import org.orcid.persistence.jpa.entities.OrgEntity;
import org.orcid.persistence.jpa.entities.ProfileEntity;
import org.orcid.persistence.jpa.entities.ProfileFundingEntity;
import org.orcid.utils.solr.entities.OrgDefinedFundingTypeSolrDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;

public class ProfileFundingManagerImpl extends ProfileFundingManagerReadOnlyImpl implements ProfileFundingManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileFundingManagerImpl.class);

    @Resource
    private FundingSubTypeToIndexDao fundingSubTypeToIndexDao;

    @Resource
    private FundingSubTypeToIndexDao fundingSubTypeToIndexDaoReadOnly;

    @Resource(name = "orgManagerV3")
    private OrgManager orgManager;

    @Resource(name = "sourceManagerV3")
    private SourceManager sourceManager;

    @Resource(name = "orcidSecurityManagerV3")
    private OrcidSecurityManager orcidSecurityManager;

    @Resource(name = "activityValidatorV3")
    private ActivityValidator activityValidator;

    @Resource
    private ProfileEntityCacheManager profileEntityCacheManager;

    @Resource(name = "notificationManagerV3")
    private NotificationManager notificationManager;

    @Value("${org.orcid.persistence.messaging.updated.fundingSubType.solr:indexFundingSubTypes}")
    private String solrQueueName;

    @Resource(name = "jmsMessageSender")
    private JmsMessageSender messaging;

    @Resource
    private ClientDetailsEntityCacheManager clientDetailsEntityCacheManager;

    /**
     * Removes the relationship that exists between a funding and a profile.
     * 
     * @param profileFundingId
     *            The id of the profileFunding that will be removed from the
     *            client profile
     * @param clientOrcid
     *            The client orcid
     * @return true if the relationship was deleted
     */
    public boolean removeProfileFunding(String clientOrcid, Long profileFundingId) {
        return profileFundingDao.removeProfileFunding(clientOrcid, profileFundingId);
    }

    /**
     * Updates the visibility of an existing profile funding relationship
     * 
     * @param clientOrcid
     *            The client orcid
     * 
     * @param profileFundingId
     *            The id of the profile funding that will be updated
     * 
     * @param visibility
     *            The new visibility value for the profile profileFunding object
     * 
     * @return true if the relationship was updated
     */
    public boolean updateProfileFundingVisibility(String clientOrcid, Long profileFundingId, Visibility visibility) {
        return profileFundingDao.updateProfileFundingVisibility(clientOrcid, profileFundingId, visibility.name());
    }

    /**
     * Updates visibility of multiple existing profile funding relationships
     * 
     * @param clientOrcid
     *            The client orcid
     * 
     * @param profileFundingIds
     *            The ids of the profile fundings that will be updated
     * 
     * @param visibility
     *            The new visibility value for the profile profileFunding
     *            objects
     * 
     * @return true if the relationships were updated
     */
    public boolean updateProfileFundingVisibilities(String clientOrcid, ArrayList<Long> profileFundingIds, Visibility visibility) {
        return profileFundingDao.updateProfileFundingVisibilities(clientOrcid, profileFundingIds, visibility.name());
    }

    /**
     * Add a new funding subtype to the list of pending for indexing subtypes
     */
    public void addFundingSubType(String subtype, String orcid) {
        fundingSubTypeToIndexDao.addSubTypes(HtmlUtils.htmlEscape(subtype), orcid);
    }

    /**
     * A process that will process all funding subtypes, filter and index them.
     */
    public void indexFundingSubTypes() {
        LOGGER.info("Indexing funding subtypes");
        List<String> subtypes = fundingSubTypeToIndexDaoReadOnly.getSubTypes();
        List<String> wordsToFilter = new ArrayList<String>();
        try {
            wordsToFilter = IOUtils.readLines(getClass().getResourceAsStream("words_to_filter.txt"));
        } catch (IOException e) {
            throw new RuntimeException("Problem reading words_to_filter.txt from classpath", e);
        }
        for (String subtype : subtypes) {
            try {
                boolean isInappropriate = false;
                // All filter words are in lower case, so, lowercase the subtype
                // before comparing
                for (String wordToFilter : wordsToFilter) {
                    if (wordToFilter.matches(".*\\b" + Pattern.quote(subtype) + "\\b.*")) {
                        isInappropriate = true;
                        break;
                    }
                }

                if (!isInappropriate) {
                    OrgDefinedFundingTypeSolrDocument document = new OrgDefinedFundingTypeSolrDocument();
                    document.setOrgDefinedFundingType(subtype);

                    // Send message to the message listener
                    if (!messaging.send(document, solrQueueName)) {
                        LOGGER.error("Unable to send fundingSubType: " + document.getOrgDefinedFundingType() + " to the message queue " + solrQueueName);
                        return;
                    }

                } else {
                    LOGGER.warn("A word have been flaged as inappropiate: " + subtype);
                }
                fundingSubTypeToIndexDao.removeSubTypes(subtype);
            } catch (Exception e) {
                // If any exception happens, log the error and continue with the
                // next one
                LOGGER.warn("Unable to process subtype " + subtype, e);
            }
        }
        LOGGER.info("Funding subtypes have been correcly indexed");
    }

    public boolean updateToMaxDisplay(String orcid, Long fundingId) {
        return profileFundingDao.updateToMaxDisplay(orcid, fundingId);
    }

    /**
     * Add a new funding to the given user
     * 
     * @param orcid
     *            The user to add the funding
     * @param funding
     *            The funding to add
     * @return the added funding
     */
    @Override
    @Transactional
    public Funding createFunding(String orcid, Funding funding, boolean isApiRequest) {
        Source activeSource = sourceManager.retrieveActiveSource();
        activityValidator.validateFunding(funding, activeSource, true, isApiRequest, null);

        // Check for duplicates
        List<ProfileFundingEntity> existingFundings = profileFundingDao.getByUser(orcid, getLastModified(orcid));
        List<Funding> fundings = jpaJaxbFundingAdapter.toFunding(existingFundings);
        if (fundings != null && isApiRequest) {
            for (Funding exstingFunding : fundings) {
                activityValidator.checkExternalIdentifiersForDuplicates(funding, exstingFunding, exstingFunding.getSource(), activeSource);
            }
        }

        ProfileFundingEntity profileFundingEntity = jpaJaxbFundingAdapter.toProfileFundingEntity(funding);

        // Updates the give organization with the latest organization from
        // database
        OrgEntity updatedOrganization = orgManager.getOrgEntity(funding);
        profileFundingEntity.setOrg(updatedOrganization);

        // Set the source
        SourceEntityUtils.populateSourceAwareEntityFromSource(activeSource, profileFundingEntity);

        ProfileEntity profile = profileEntityCacheManager.retrieve(orcid);
        profileFundingEntity.setProfile(profile);
        setIncomingPrivacy(profileFundingEntity, profile, isApiRequest);
        DisplayIndexCalculatorHelper.setDisplayIndexOnNewEntity(profileFundingEntity, isApiRequest);
        profileFundingDao.persist(profileFundingEntity);
        profileFundingDao.flush();
        if (isApiRequest) {
            notificationManager.sendAmendEmail(orcid, AmendedSection.FUNDING, createItemList(profileFundingEntity, ActionType.CREATE));
        }
        return jpaJaxbFundingAdapter.toFunding(profileFundingEntity);
    }

    private void setIncomingPrivacy(ProfileFundingEntity workEntity, ProfileEntity profile, boolean isApiRequest) {
        String incomingWorkVisibility = workEntity.getVisibility();
        String defaultWorkVisibility = profile.getActivitiesVisibilityDefault();

        if ((isApiRequest && profile.getClaimed()) || (incomingWorkVisibility == null && !isApiRequest)) {
            workEntity.setVisibility(defaultWorkVisibility);
        } else if (isApiRequest && !profile.getClaimed() && incomingWorkVisibility == null) {
            workEntity.setVisibility(org.orcid.jaxb.model.common_v2.Visibility.PRIVATE.name());
        }
    }

    /**
     * Updates a funding that belongs to the given user
     * 
     * @param orcid
     *            The user
     * @param funding
     *            The funding to update
     * @return the updated funding
     */
    @Override
    public Funding updateFunding(String orcid, Funding funding, boolean isApiRequest) {
        Source activeSOurce = sourceManager.retrieveActiveSource();
        ProfileFundingEntity pfe = profileFundingDao.getProfileFunding(orcid, funding.getPutCode());
        Visibility originalVisibility = Visibility.valueOf(pfe.getVisibility());

        // Save the original source
        Source originalSource = SourceEntityUtils.extractSourceFromEntity(pfe, clientDetailsEntityCacheManager);

        activityValidator.validateFunding(funding, activeSOurce, false, isApiRequest, originalVisibility);
        if (!isApiRequest) {
            List<ProfileFundingEntity> existingFundings = profileFundingDao.getByUser(orcid, getLastModified(orcid));
            for (ProfileFundingEntity existingFunding : existingFundings) {
                Funding existing = jpaJaxbFundingAdapter.toFunding(existingFunding);
                if (!existing.getPutCode().equals(funding.getPutCode())) {
                    activityValidator.checkExternalIdentifiersForDuplicates(funding, existing, existing.getSource(), activeSOurce);
                }
            }
        }

        orcidSecurityManager.checkSourceAndThrow(pfe);
        jpaJaxbFundingAdapter.toProfileFundingEntity(funding, pfe);
        if (pfe.getVisibility() == null) {
            pfe.setVisibility(originalVisibility.name());
        }

        // Be sure it doesn't overwrite the source
        SourceEntityUtils.populateSourceAwareEntityFromSource(originalSource, pfe);

        // Updates the give organization with the latest organization from
        // database, or, create a new one
        OrgEntity updatedOrganization = orgManager.getOrgEntity(funding);
        pfe.setOrg(updatedOrganization);

        pfe = profileFundingDao.merge(pfe);
        profileFundingDao.flush();
        if (isApiRequest) {
            notificationManager.sendAmendEmail(orcid, AmendedSection.FUNDING, createItemList(pfe, ActionType.UPDATE));
        }
        return jpaJaxbFundingAdapter.toFunding(pfe);
    }

    /**
     * Deletes a given funding, if and only if, the client that requested the
     * delete is the source of the funding
     * 
     * @param orcid
     *            the funding owner
     * @param fundingId
     *            The funding id
     * @return true if the funding was deleted, false otherwise
     */
    @Override
    @Transactional
    public boolean checkSourceAndDelete(String orcid, Long fundingId) {
        ProfileFundingEntity pfe = profileFundingDao.getProfileFunding(orcid, fundingId);
        orcidSecurityManager.checkSourceAndThrow(pfe);
        boolean result = profileFundingDao.removeProfileFunding(orcid, fundingId);
        notificationManager.sendAmendEmail(orcid, AmendedSection.FUNDING, createItemList(pfe, ActionType.DELETE));
        return result;
    }

    private List<Item> createItemList(ProfileFundingEntity profileFundingEntity, ActionType type) {
        Item item = new Item();
        item.setItemName(profileFundingEntity.getTitle());
        item.setItemType(ItemType.FUNDING);
        item.setPutCode(String.valueOf(profileFundingEntity.getId()));
        item.setActionType(type);
        return Arrays.asList(item);
    }

    @Override
    public void removeAllFunding(String orcid) {
        profileFundingDao.removeAllFunding(orcid);
    }
}
