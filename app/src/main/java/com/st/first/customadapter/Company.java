package com.st.first.customadapter;

public class Company {
    private String name, phone,url, logo;

	public Company(String name, String phone, String url, String logo) {
		super();
		this.name = name;
		this.phone = phone;
		this.url = url;
		this.logo = logo;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
