package org.orcid.pojo.ajaxForm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.orcid.jaxb.model.v3.rc1.record.AffiliationType;
import org.orcid.jaxb.model.v3.rc1.record.ExternalID;
import org.orcid.jaxb.model.v3.rc1.record.summary.AffiliationSummary;
import org.orcid.jaxb.model.v3.rc1.record.summary.DistinctionSummary;
import org.orcid.jaxb.model.v3.rc1.record.summary.EducationSummary;
import org.orcid.jaxb.model.v3.rc1.record.summary.EmploymentSummary;
import org.orcid.jaxb.model.v3.rc1.record.summary.InvitedPositionSummary;
import org.orcid.jaxb.model.v3.rc1.record.summary.MembershipSummary;
import org.orcid.jaxb.model.v3.rc1.record.summary.QualificationSummary;
import org.orcid.jaxb.model.v3.rc1.record.summary.ServiceSummary;

public class AffiliationGroupForm implements Serializable {
    
    private static final long serialVersionUID = -8347171671099477223L;

    private List<AffiliationForm> affiliations = new ArrayList<>();

    private Long activePutCode;

    private AffiliationForm defaultAffiliation;

    private int groupId;

    private String activeVisibility;

    private Boolean userVersionPresent;

    private List<ActivityExternalIdentifier> externalIdentifiers = new ArrayList<>();

    private AffiliationType affiliationType;

    public List<AffiliationForm> getAffiliations() {
        return affiliations;
    }

    public void setAffiliations(List<AffiliationForm> affiliations) {
        this.affiliations = affiliations;
    }

    public Long getActivePutCode() {
        return activePutCode;
    }

    public void setActivePutCode(Long activePutCode) {
        this.activePutCode = activePutCode;
    }

    public AffiliationForm getDefaultAffiliation() {
        return defaultAffiliation;
    }

    public void setDefaultAffiliation(AffiliationForm defaultAffiliation) {
        this.defaultAffiliation = defaultAffiliation;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getActiveVisibility() {
        return activeVisibility;
    }

    public void setActiveVisibility(String activeVisibility) {
        this.activeVisibility = activeVisibility;
    }

    public Boolean isUserVersionPresent() {
        return userVersionPresent;
    }

    public void setUserVersionPresent(Boolean userVersionPresent) {
        this.userVersionPresent = userVersionPresent;
    }

    public List<ActivityExternalIdentifier> getExternalIdentifiers() {
        return externalIdentifiers;
    }

    public void setExternalIdentifiers(List<ActivityExternalIdentifier> externalIdentifiers) {
        this.externalIdentifiers = externalIdentifiers;
    }

    public AffiliationType getAffiliationType() {
        return affiliationType;
    }

    public void setAffiliationType(AffiliationType affiliationType) {
        this.affiliationType = affiliationType;
    }
    
    public static AffiliationGroupForm valueOf(org.orcid.jaxb.model.v3.rc1.record.summary.AffiliationGroup<? extends AffiliationSummary> group, int id, String orcid) {
        AffiliationGroupForm affiliationGroup = new AffiliationGroupForm();
        affiliationGroup.setGroupId(id);
        affiliationGroup.setUserVersionPresent(false);
        
        Long maxDisplayIndex = null;
        for(AffiliationSummary summary : group.getActivities()) {
            if(affiliationGroup.getAffiliationType() == null) {
                if(summary instanceof DistinctionSummary) {
                    affiliationGroup.setAffiliationType(AffiliationType.DISTINCTION);
                } else if(summary instanceof EducationSummary) {
                    affiliationGroup.setAffiliationType(AffiliationType.EDUCATION);
                } else if(summary instanceof EmploymentSummary) {
                    affiliationGroup.setAffiliationType(AffiliationType.EMPLOYMENT);
                } else if(summary instanceof InvitedPositionSummary) {
                    affiliationGroup.setAffiliationType(AffiliationType.INVITED_POSITION);
                } else if(summary instanceof MembershipSummary) {
                    affiliationGroup.setAffiliationType(AffiliationType.MEMBERSHIP);
                } else if(summary instanceof QualificationSummary) {
                    affiliationGroup.setAffiliationType(AffiliationType.QUALIFICATION);
                } else if(summary instanceof ServiceSummary) {
                    affiliationGroup.setAffiliationType(AffiliationType.SERVICE);
                } 
            }
            
            Long displayIndex = Long.parseLong(summary.getDisplayIndex());
            if(maxDisplayIndex == null || displayIndex > maxDisplayIndex) {
                affiliationGroup.setActivePutCode(summary.getPutCode());
                affiliationGroup.setActiveVisibility(summary.getVisibility().name());
                affiliationGroup.setDefaultAffiliation(AffiliationForm.valueOf(summary));                
            }
            
            if(summary.getSource().retrieveSourcePath().equals(orcid)) {
                affiliationGroup.setUserVersionPresent(true);
            } 
            
            if (summary.getExternalIdentifiers() != null) {
                List<ActivityExternalIdentifier> workExternalIdentifiersList = new ArrayList<ActivityExternalIdentifier>();
                for (ExternalID extId : summary.getExternalIdentifiers().getExternalIdentifier()) {                    
                    workExternalIdentifiersList.add(ActivityExternalIdentifier.valueOf(extId));
                }
                affiliationGroup.setExternalIdentifiers(workExternalIdentifiersList);
            }
            
            affiliationGroup.getAffiliations().add(AffiliationForm.valueOf(summary));            
        }
        
        return affiliationGroup;
    }
}