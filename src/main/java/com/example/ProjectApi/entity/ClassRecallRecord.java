package com.example.ProjectApi.entity;

import java.util.ArrayList;

import com.mysql.fabric.xmlrpc.base.Data;

public class ClassRecallRecord {
	public ClassRecallRecord() {
		// TODO Auto-generated constructor stub
	}
	
	private String class_id;
	private ArrayList<String> c_RecallRecord_absence;
	private ArrayList<String> c_RecallRecord_attend;
	private Data c_RecallRecord_date;
	
	public String getClass_id() {
		return class_id;
	}
	public void setClass_id(String class_id) {
		this.class_id = class_id;
	}
	public ArrayList<String> getC_RecallRecord_absence() {
		return c_RecallRecord_absence;
	}
	public void setC_RecallRecord_absence(ArrayList<String> c_RecallRecord_absence) {
		this.c_RecallRecord_absence = c_RecallRecord_absence;
	}
	public ArrayList<String> getC_RecallRecord_attend() {
		return c_RecallRecord_attend;
	}
	public void setC_RecallRecord_attend(ArrayList<String> c_RecallRecord_attend) {
		this.c_RecallRecord_attend = c_RecallRecord_attend;
	}
	public Data getC_RecallRecord_date() {
		return c_RecallRecord_date;
	}
	public void setC_RecallRecord_date(Data c_RecallRecord_date) {
		this.c_RecallRecord_date = c_RecallRecord_date;
	}
	
}
