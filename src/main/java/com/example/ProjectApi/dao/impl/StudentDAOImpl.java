package com.example.ProjectApi.dao.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.sql.DataSource;

import com.example.ProjectApi.dao.StudentDAO;
import com.example.ProjectApi.entity.ClassRecord;
import com.example.ProjectApi.entity.Student;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

public class StudentDAOImpl implements StudentDAO {
//	private DataSource dataSource;
//
//	public void setDataSource(DataSource dataSource) {
//		this.dataSource = dataSource;
//	}
	
	static Firestore db;
	String ProjectResourcePath = this.getClass().getClassLoader().getResource(".").getPath();
	
	public Firestore quickStart() throws IOException {
		
		FirebaseApp firebaseApp;
		FileInputStream serviceAccount = new FileInputStream(
				ProjectResourcePath+"firestore/serviceAccountKey.json");
		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.setDatabaseUrl("https://projectdb-83c55.firebaseio.com").build();

		FirebaseApp.initializeApp(options);
		Firestore db = FirestoreClient.getFirestore();
		return db;
	}
	
	

	@Override
	public void insert(Student student) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(List<Student> studentList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Student student, Student oldStudent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Student student) {
		// TODO Auto-generated method stub
		
	}

	public Student get(Student student) throws IOException {
		db=quickStart();
		//asynchronously retrieve multiple documents
		String s_id=student.getStudent_id();
		ApiFuture<QuerySnapshot> future =
		    db.collection("Student").whereEqualTo("student_id",s_id ).get();
		// future.get() blocks on response
		List<QueryDocumentSnapshot> documents;
		try {
			documents = future.get().getDocuments();
			for (DocumentSnapshot document : documents) {
				  System.out.println(document.getId() + " => " + document.toObject(Student.class));
				  student.setClass_id((ArrayList<String>)document.get("Class_id"));
				  student.setStudent_department((String)document.get("student_department"));
				  student.setStudent_email((String)document.get("student_email"));
				  student.setStudent_id((String)document.get("student_id"));
				  student.setStudent_name((String)document.get("student_name"));
				  student.setStudent_school((String)document.get("student_school"));
				  
				  System.out.println(student.getStudent_name());
				  System.out.println("888");
				}
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return student;
	}
}
