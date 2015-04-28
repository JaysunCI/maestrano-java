package com.maestrano.connec;

import java.util.Map;
import java.util.TimeZone;

import com.maestrano.exception.ApiException;
import com.maestrano.exception.AuthenticationException;
import com.maestrano.exception.InvalidRequestException;
import com.maestrano.helpers.MnoMapHelper;
import com.maestrano.net.ConnecClient;

public class CnCompany extends ConnecResource {
	private String name;
	private String currency;
	private String note;
	private TimeZone timezone;
	private Integer capital;
	private String juridicalStatus;
	private String vatNumber;
	private String fiscalYearFirstMonth;
	private CnEmail email;
	private CnAddressGroup address;
	private CnWebsite website;
	private CnPhone phone;
	private CnLogo logo;
	
	public String getEntityName() {
        return "company";
    }
    
    public static Class<?> getEntityClass() {
        return CnCompany.class;
    }
	
	/**
	 * Instantiate a new Company entity from a JSON string
	 * 
	 * @param jsonStr representing the object attributes using snake case keys
	 * @return
	 */
	public static CnCompany fromJson(String jsonStr) {
		CnCompany obj = ConnecClient.GSON.fromJson(jsonStr, CnCompany.class);
		return obj;
	}
	
	/**
	 * Instantiate a new Company entity from a map of attributes
	 * @param map of attributes
	 * @return
	 */
	public static CnCompany fromMap(Map<String,Object> map) {
		String jsonStr = ConnecClient.GSON.toJson(MnoMapHelper.toUnderscoreHash(map));
		CnCompany obj = fromJson(jsonStr);
		return obj;
	}
	
	/**
	 * Retrieve the company corresponding to the provided groupId
	 * @param groupId customer group id
	 * @return a company entity if found, null otherwise
	 * @throws ApiException 
	 * @throws AuthenticationException 
	 * @throws InvalidRequestException 
	 */
	public static CnCompany retrieve(String groupId) throws AuthenticationException, ApiException, InvalidRequestException {
		return ConnecClient.retrieve(entityName(CnCompany.class),groupId,null,CnCompany.class);
	}
	
	/**
	 * Update an entity remotely
	 * @param clazz entity class
	 * @param groupId customer group id
	 * @param entityId id of the entity to retrieve
	 * @param hash entity attributes to update 
	 * @return true if the resource was saved
	 * @throws AuthenticationException
	 * @throws ApiException
	 * @throws InvalidRequestException
	 */
	public boolean save() throws AuthenticationException, ApiException, InvalidRequestException {
		CnCompany obj = ConnecClient.update(getEntityName(),groupId,null,this);
		this.merge(obj);
		
		return true;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public TimeZone getTimezone() {
		return timezone;
	}
	public void setTimezone(TimeZone timezone) {
		this.timezone = timezone;
	}
	public Integer getCapital() {
		return capital;
	}
	public void setCapital(Integer capital) {
		this.capital = capital;
	}
	public String getJuridicalStatus() {
		return juridicalStatus;
	}
	public void setJuridicalStatus(String juridicalStatus) {
		this.juridicalStatus = juridicalStatus;
	}
	public String getVatNumber() {
		return vatNumber;
	}
	public void setVatNumber(String vatNumber) {
		this.vatNumber = vatNumber;
	}
	public String getFiscalYearFirstMonth() {
		return fiscalYearFirstMonth;
	}
	public void setFiscalYearFirstMonth(String fiscalYearFirstMonth) {
		this.fiscalYearFirstMonth = fiscalYearFirstMonth;
	}
	public CnEmail getEmail() {
		return email;
	}
	public void setEmail(CnEmail email) {
		this.email = email;
	}
	public CnAddressGroup getAddress() {
		return address;
	}
	public void setAddress(CnAddressGroup address) {
		this.address = address;
	}
	public CnWebsite getWebsite() {
		return website;
	}
	public void setWebsite(CnWebsite website) {
		this.website = website;
	}
	public CnPhone getPhone() {
		return phone;
	}
	public void setPhone(CnPhone phone) {
		this.phone = phone;
	}
	public CnLogo getLogo() {
		return logo;
	}
	public void setLogo(CnLogo logo) {
		this.logo = logo;
	}
}
