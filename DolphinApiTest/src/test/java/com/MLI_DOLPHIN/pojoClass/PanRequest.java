package com.MLI_DOLPHIN.pojoClass;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PanRequest {
	private String firstName;
	private String middleName;
	private String lastName;
	private String dob;
	private String gender;
	private String email;
	private String careOf;
	private String houseNumber;
	private String street;
	private String landmark;
	private String location;
	private String postOffice;
	private String villageCity;
	private String subDistrict;
	private String district;
	private String state;
	private String stateCode;
	private String postalCode;
	private String mobileNumber;
	private String pan;
	private String validationType;

	public PanRequest(String firstName, String middleName, String lastName, String dob, String gender, String email,
			String careOf, String houseNumber, String street, String landmark, String location, String postOffice,
			String villageCity, String subDistrict, String district, String state, String stateCode, String postalCode,
			String mobileNumber, String pan, String validationType) {

		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.dob = dob;
		this.gender =gender;
		this.email = email;
		this.careOf = careOf;
		this.houseNumber = houseNumber;
		this.street = street;
		this.landmark = landmark;
		this.location = location;
		this.landmark = landmark;
		this.postOffice = postOffice;
		this.villageCity = villageCity;
		this.subDistrict = subDistrict;
		this.district = district;
		this.state = state;
		this.stateCode = stateCode;
		this.postalCode = postalCode;
		this.mobileNumber = mobileNumber;
		this.pan = pan;
		this.validationType = validationType;

	}
	
	@JsonProperty("firstName")
	public String getFirstName() {

		return firstName;
	}

	@JsonProperty("firstName")
	public void setFirstName(String value) {
		this.firstName = value;
	}

	@JsonProperty("middleName")
	public String getMiddleName() {
		return middleName;
	}

	@JsonProperty("middleName")
	public void setMiddleName(String value) {
		this.middleName = value;
	}

	@JsonProperty("lastName")
	public String getLastName() {
		return lastName;
	}

	@JsonProperty("lastName")
	public void setLastName(String value) {
		this.lastName = value;
	}

	@JsonProperty("dob")
	public String getDob() {
		return dob;
	}

	@JsonProperty("dob")
	public void setDob(String value) {
		this.dob = value;
	}

	@JsonProperty("gender")
	public String getGender() {
		return gender;
	}

	@JsonProperty("gender")
	public void setGender(String value) {
		this.gender = value;
	}

	@JsonProperty("email")
	public String getEmail() {
		return email;
	}

	@JsonProperty("email")
	public void setEmail(String value) {
		this.email = value;
	}

	@JsonProperty("careOf")
	public String getCareOf() {
		return careOf;
	}

	@JsonProperty("careOf")
	public void setCareOf(String value) {
		this.careOf = value;
	}

	@JsonProperty("houseNumber")
	public String getHouseNumber() {
		return houseNumber;
	}

	@JsonProperty("houseNumber")
	public void setHouseNumber(String value) {
		this.houseNumber = value;
	}

	@JsonProperty("street")
	public String getStreet() {
		return street;
	}

	@JsonProperty("street")
	public void setStreet(String value) {
		this.street = value;
	}

	@JsonProperty("landmark")
	public String getLandmark() {
		return landmark;
	}

	@JsonProperty("landmark")
	public void setLandmark(String value) {
		this.landmark = value;
	}

	@JsonProperty("location")
	public String getLocation() {
		return location;
	}

	@JsonProperty("location")
	public void setLocation(String value) {
		this.location = value;
	}

	@JsonProperty("postOffice")
	public String getPostOffice() {
		return postOffice;
	}

	@JsonProperty("postOffice")
	public void setPostOffice(String value) {
		this.postOffice = value;
	}

	@JsonProperty("village_city")
	public String getVillageCity() {
		return villageCity;
	}

	@JsonProperty("village_city")
	public void setVillageCity(String value) {
		this.villageCity = value;
	}

	@JsonProperty("subDistrict")
	public String getSubDistrict() {
		return subDistrict;
	}

	@JsonProperty("subDistrict")
	public void setSubDistrict(String value) {
		this.subDistrict = value;
	}

	@JsonProperty("district")
	public String getDistrict() {
		return district;
	}

	@JsonProperty("district")
	public void setDistrict(String value) {
		this.district = value;
	}

	@JsonProperty("state")
	public String getState() {
		return state;
	}

	@JsonProperty("state")
	public void setState(String value) {
		this.state = value;
	}

	@JsonProperty("stateCode")
	public String getStateCode() {
		return stateCode;
	}

	@JsonProperty("stateCode")
	public void setStateCode(String value) {
		this.stateCode = value;
	}

	@JsonProperty("postalCode")
	public String getPostalCode() {
		return postalCode;
	}

	@JsonProperty("postalCode")
	public void setPostalCode(String value) {
		this.postalCode = value;
	}

	@JsonProperty("mobileNumber")
	public String getMobileNumber() {
		return mobileNumber;
	}

	@JsonProperty("mobileNumber")
	public void setMobileNumber(String value) {
		this.mobileNumber = value;
	}

	@JsonProperty("pan")
	public String getPan() {
		return pan;
	}

	@JsonProperty("pan")
	public void setPan(String value) {
		this.pan = value;
	}

	@JsonProperty("validationType")
	public String getValidationType() {
		return validationType;
	}

	@JsonProperty("validationType")
	public void setValidationType(String value) {
		this.validationType = value;
	}
}
