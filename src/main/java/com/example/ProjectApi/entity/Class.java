package com.example.ProjectApi.entity;

import java.util.ArrayList;

public class Class {
	public Class() {
		
	}
	
	private String class_id;
	private String class_name;
	private Integer class_team_total;
	private String teacher_gmail;
	private String [] class_schoolyear = new String[2];
	private ArrayList<String>student_id;
	private ArrayList<ClassRecallRecord> classRecallRecordList;
	private ArrayList<ClassTeam> classTeamList;
	public String getClass_id() {
		return class_id;
	}
	public void setClass_id(String class_id) {
		this.class_id = class_id;
	}
	public String getClass_name() {
		return class_name;
	}
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
	public Integer getClass_team_total() {
		return class_team_total;
	}
	public void setClass_team_total(Integer class_team_total) {
		this.class_team_total = class_team_total;
	}
	public String getTeacher_gmail() {
		return teacher_gmail;
	}
	public void setTeacher_gmail(String teacher_gmail) {
		this.teacher_gmail = teacher_gmail;
	}
	public String[] getClass_schoolyear() {
		return class_schoolyear;
	}
	public void setClass_schoolyear(String[] class_schoolyear) {
		this.class_schoolyear = class_schoolyear;
	}
	public ArrayList<String> getStudent_id() {
		return student_id;
	}
	public void setStudent_id(ArrayList<String> student_id) {
		this.student_id = student_id;
	}
	public ArrayList<ClassRecallRecord> getClassRecallRecordList() {
		return classRecallRecordList;
	}
	public void setClassRecallRecordList(ArrayList<ClassRecallRecord> classRecallRecordList) {
		this.classRecallRecordList = classRecallRecordList;
	}
	public ArrayList<ClassTeam> getClassTeamList() {
		return classTeamList;
	}
	public void setClassTeamList(ArrayList<ClassTeam> classTeamList) {
		this.classTeamList = classTeamList;
	}
	
	
	
	
}
