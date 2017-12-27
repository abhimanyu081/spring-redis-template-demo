package com.redis.model;

import java.io.Serializable;

public class MutualFundDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String primaryObjective;
	private Integer schemeId;
	private Integer VRRiskRating;
	private Integer VRReturnRating;
	private Double absChangeNav;
	private String latestNavDate;
	private String entityType;
	private Double latestNav;
	private Double r1Month;
	private String secondaryObjective;
	private String nameOfScheme;
	private Double percentChange;
	private String objectiveId;
	private Double netChange;
	private String fundTypeName;
	private String shortName;
	public String getPrimaryObjective() {
		return primaryObjective;
	}
	public void setPrimaryObjective(String primaryObjective) {
		this.primaryObjective = primaryObjective;
	}
	public Integer getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(Integer schemeId) {
		this.schemeId = schemeId;
	}
	public Integer getVRRiskRating() {
		return VRRiskRating;
	}
	public void setVRRiskRating(Integer vRRiskRating) {
		VRRiskRating = vRRiskRating;
	}
	public Integer getVRReturnRating() {
		return VRReturnRating;
	}
	public void setVRReturnRating(Integer vRReturnRating) {
		VRReturnRating = vRReturnRating;
	}
	public Double getAbsChangeNav() {
		return absChangeNav;
	}
	public void setAbsChangeNav(Double absChangeNav) {
		this.absChangeNav = absChangeNav;
	}
	public String getLatestNavDate() {
		return latestNavDate;
	}
	public void setLatestNavDate(String latestNavDate) {
		this.latestNavDate = latestNavDate;
	}
	public String getEntityType() {
		return entityType;
	}
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	public Double getLatestNav() {
		return latestNav;
	}
	public void setLatestNav(Double latestNav) {
		this.latestNav = latestNav;
	}
	public Double getR1Month() {
		return r1Month;
	}
	public void setR1Month(Double r1Month) {
		this.r1Month = r1Month;
	}
	public String getSecondaryObjective() {
		return secondaryObjective;
	}
	public void setSecondaryObjective(String secondaryObjective) {
		this.secondaryObjective = secondaryObjective;
	}
	public String getNameOfScheme() {
		return nameOfScheme;
	}
	public void setNameOfScheme(String nameOfScheme) {
		this.nameOfScheme = nameOfScheme;
	}
	public Double getPercentChange() {
		return percentChange;
	}
	public void setPercentChange(Double percentChange) {
		this.percentChange = percentChange;
	}
	public String getObjectiveId() {
		return objectiveId;
	}
	public void setObjectiveId(String objectiveId) {
		this.objectiveId = objectiveId;
	}
	public Double getNetChange() {
		return netChange;
	}
	public void setNetChange(Double netChange) {
		this.netChange = netChange;
	}
	public String getFundTypeName() {
		return fundTypeName;
	}
	public void setFundTypeName(String fundTypeName) {
		this.fundTypeName = fundTypeName;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	

}
