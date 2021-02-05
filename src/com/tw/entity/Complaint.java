package com.tw.entity;

public class Complaint {
	private String CALL_NAME;
	private String BUSINESS_ITEMTYPE_NAME;
	private String CALLER_ID;
	private String VEHICLE_PLATE_NUMBER;
	private String BUSINESS_STATUS_NAME;
	private String ARCHIVE_TIME;
	private String ACCEPT_TIME;
	public String getCALL_NAME() {
		return CALL_NAME;
	}
	public void setCALL_NAME(String cALL_NAME) {
		CALL_NAME = cALL_NAME;
	}
	public String getBUSINESS_ITEMTYPE_NAME() {
		return BUSINESS_ITEMTYPE_NAME;
	}
	public void setBUSINESS_ITEMTYPE_NAME(String bUSINESS_ITEMTYPE_NAME) {
		BUSINESS_ITEMTYPE_NAME = bUSINESS_ITEMTYPE_NAME;
	}
	public String getCALLER_ID() {
		return CALLER_ID;
	}
	public void setCALLER_ID(String cALLER_ID) {
		CALLER_ID = cALLER_ID;
	}
	public String getVEHICLE_PLATE_NUMBER() {
		return VEHICLE_PLATE_NUMBER;
	}
	public void setVEHICLE_PLATE_NUMBER(String vEHICLE_PLATE_NUMBER) {
		VEHICLE_PLATE_NUMBER = vEHICLE_PLATE_NUMBER;
	}
	public String getBUSINESS_STATUS_NAME() {
		return BUSINESS_STATUS_NAME;
	}
	public void setBUSINESS_STATUS_NAME(String bUSINESS_STATUS_NAME) {
		BUSINESS_STATUS_NAME = bUSINESS_STATUS_NAME;
	}
	public String getARCHIVE_TIME() {
		return ARCHIVE_TIME;
	}
	public void setARCHIVE_TIME(String aRCHIVE_TIME) {
		ARCHIVE_TIME = aRCHIVE_TIME;
	}
	public String getACCEPT_TIME() {
		return ACCEPT_TIME;
	}
	public void setACCEPT_TIME(String aCCEPT_TIME) {
		ACCEPT_TIME = aCCEPT_TIME;
	}
}
