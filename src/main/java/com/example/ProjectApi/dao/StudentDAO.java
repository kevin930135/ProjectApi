package com.example.ProjectApi.dao;

import java.io.IOException;
import java.util.List;

import com.example.ProjectApi.entity.Student;

public interface StudentDAO {

	public void insert(Student student);

	public void insert(List<Student> studentList);

	public void update(Student student, Student oldStudent);

	public void delete(Student student);

	public Student get(Student student) throws IOException ;

}
