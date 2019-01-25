package com.example.ProjectApi.dao;

import java.util.List;

import com.example.ProjectApi.entity.Class;

public interface ClassDAO {
	
	public void insert(Class c);

	public void insert(List<Class> classList);

	public void update(Class c, Class oldClass);

	public void delete(Class c);


	
	

}
