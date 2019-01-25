package com.example.ProjectApi.dao.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.example.ProjectApi.dao.ClassDAO;
import com.example.ProjectApi.entity.Class;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

public class ClassDAOImpl implements ClassDAO {
	

	public void quickStart() throws IOException {
		FirebaseApp firebaseApp;
		FileInputStream serviceAccount = new FileInputStream("./serviceAccountKey.json");
		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.setDatabaseUrl("https://projectdb-83c55.firebaseio.com").build();

		FirebaseApp.initializeApp(options);
		Firestore db = FirestoreClient.getFirestore();

	}

	@Override
	public void insert(Class c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(List<Class> classList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Class c, Class oldClass) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Class c) {
		// TODO Auto-generated method stub
		
	}

}
