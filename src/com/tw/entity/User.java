package com.tw.entity;

public class User {
	private String id;
	private String username;
	private String password;
	private String date_view_type;
	private String real_name;
	private String station_id;
	private String station_admin;
	private String job_number;
	private String parent;
	private String ymqx;//页面权限
	private String glqx;//管理权限
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDate_view_type() {
		return date_view_type;
	}
	public void setDate_view_type(String dateViewType) {
		date_view_type = dateViewType;
	}
	public String getStation_id() {
		return station_id;
	}
	public void setStation_id(String stationId) {
		station_id = stationId;
	}
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String realName) {
		real_name = realName;
	}
	public String getStation_admin() {
		return station_admin;
	}
	public void setStation_admin(String stationAdmin) {
		station_admin = stationAdmin;
	}
	public String getJob_number() {
		return job_number;
	}
	public void setJob_number(String jobNumber) {
		job_number = jobNumber;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getGlqx() {
		return glqx;
	}
	public void setGlqx(String glqx) {
		this.glqx = glqx;
	}
	public String getYmqx() {
		return ymqx;
	}
	public void setYmqx(String ymqx) {
		this.ymqx = ymqx;
	}
	
}
