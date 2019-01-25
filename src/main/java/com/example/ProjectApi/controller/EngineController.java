package com.example.ProjectApi.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.GZIP;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firestore.v1beta1.Document;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.example.ProjectApi.dao.ClassDAO;
import com.example.ProjectApi.dao.StudentDAO;
import com.example.ProjectApi.dao.impl.StudentDAOImpl;
import com.example.ProjectApi.engine.entity.Face;
import com.example.ProjectApi.engineControl.EngineFunc;
import com.example.ProjectApi.engineControl.GetResult;
import com.example.ProjectApi.entity.Student;
import com.example.ProjectApi.entity.TrainModel;
import com.example.ProjectApi.updatetxtImpl.UpdateTxtImpl;
import com.example.ProjectApi.util.entity.WebResponse;

@Path("/FaceApi")
@Controller
public class EngineController {

	final ApplicationContext context = new ClassPathXmlApplicationContext("spring-module-rds.xml");
	private final String UPLOADED_Photo_Train_PATH = "C:\\eGroupAI_FaceEngine_v3.1.0\\TrainFace\\";
	private final String UPLOADED_Photo_Retrieve_PATH = "C:\\eGroupAI_FaceEngine_v3.1.0\\RetrievePhoto\\";

	private GetResult getResult = new GetResult();

	@POST
	@Path("/TrainFace")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response TrainNewModel(@MultipartForm TrainModel trainmodel, @Context HttpServletRequest request,
			@Context HttpServletResponse response) throws IOException {

		String fileName = UPLOADED_Photo_Train_PATH;
		fileName = fileName + trainmodel.getphotoName() + ".jpg";
		try {
			writeFile(trainmodel.getphoto(), fileName);
		} catch (IOException e) {
			System.out.println("Error in PhotoUpload!");

			e.printStackTrace();
		}
		// init func
		UpdateTxtImpl trainRetxtImpl = new UpdateTxtImpl();
		boolean flag = trainRetxtImpl.updatetxtTrain(trainmodel);
		// init variable

		EngineFunc enginefunc = new EngineFunc();
		enginefunc.trainface(null);

		final WebResponse webResponse = new WebResponse();
		webResponse.OK();
		// webResponse.setData(trainface);
		return Response.status(webResponse.getStatusCode()).entity(webResponse.getData()).build();
	}

	@POST
	@Path("/RetrieveRtsp")
	// @Consumes(MediaType.APPLICATION_JSON)
	public Response RetrieveRtsp(@Context HttpServletRequest request, @Context HttpServletResponse response) {
		// init variable

		EngineFunc enginefunc = new EngineFunc();
		enginefunc.retrieveface(null);

		final WebResponse webResponse = new WebResponse();
		webResponse.OK();
		// webResponse.setData(trainface);
		return Response.status(webResponse.getStatusCode()).build();
	}

	@GET
	@Path("/RetrievePhoto")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response RetrievePhoto(@MultipartForm TrainModel trainmodel, @Context HttpServletRequest request,
			@Context HttpServletResponse response) throws IOException {

//		final StudentDAO studentDAO = (StudentDAO) context.getBean("studentDAO");
		final WebResponse webResponse = new WebResponse();
		List<Face> faceList = new ArrayList<>();
		String fileName = UPLOADED_Photo_Retrieve_PATH;
		fileName = fileName + trainmodel.getphotoName() + ".jpg";
		try {
			writeFile(trainmodel.getphoto(), fileName);
		} catch (IOException e) {
			System.out.println("Error in PhotoUpload!");

			e.printStackTrace();
		}

		UpdateTxtImpl updatetxtimpl = new UpdateTxtImpl();
		updatetxtimpl.updatetxtRetrieve(trainmodel);
		EngineFunc enginefunc = new EngineFunc();
		enginefunc.retrievephotolist();
		final Gson gson = new Gson();
		final Type faceListType = new TypeToken<ArrayList<Face>>() {
		}.getType();
		ArrayList<String> faceIDList = new ArrayList<String>();
		faceList = getResult.main(null);
		faceList = gson.fromJson(new Gson().toJson(faceList), faceListType);
//		//測試從API就先取JSON值 勿刪
		System.out.println("FLAG");
		Student student = new Student();
		for (Face face : faceList) {
			faceIDList.add(face.getPersonId());
			System.out.println("FLAG1");
			System.out.println(face.getPersonId());
			student.setStudent_id(face.getPersonId());
			System.out.println("FLAG2");
		}

		StudentDAO.get(student);

		System.out.println(faceIDList);
		webResponse.setData(faceList);

		webResponse.OK();
		return Response.status(webResponse.getStatusCode()).entity(webResponse.getData()).build();

	}

	@POST
	@Path("/ModelSwitch")
	// @Consumes(MediaType.APPLICATION_JSON)
	public Response Modelswitch(@Context HttpServletRequest request, @Context HttpServletResponse response) {
		// init variable

		EngineFunc enginefunc = new EngineFunc();
		enginefunc.modelswitch(null);

		final WebResponse webResponse = new WebResponse();
		webResponse.OK();
		// webResponse.setData(trainface);
		return Response.status(webResponse.getStatusCode()).build();
	}

	@GET
	@Path("/TestDB")
	public Response TestDB(@Context HttpServletRequest request, @Context HttpServletResponse response)
			throws IOException, InterruptedException, ExecutionException {
		String ProjectResourcePath = this.getClass().getClassLoader().getResource(".").getPath();
		FirebaseApp firebaseApp;
		FileInputStream serviceAccount = new FileInputStream(
				ProjectResourcePath+"firestore/serviceAccountKey.json");
		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.setDatabaseUrl("https://projectdb-83c55.firebaseio.com").build();

		FirebaseApp.initializeApp(options);
		Firestore db = FirestoreClient.getFirestore();

		DocumentReference docref = db.collection("Student").document("X6jUGHNHkz5XrZdQqk4r");
		ApiFuture<DocumentSnapshot> future = docref.get();
		DocumentSnapshot documentSnapshot = future.get();
		if (documentSnapshot.exists()) {
			System.out.println("Success");
		} else {
			System.out.println("NOPE");
		}

		return null;

	}

	private void writeFile(byte[] content, String filename) throws IOException {

		File file = new File(filename);

		if (!file.exists()) {
			file.createNewFile();
		}

		FileOutputStream fop = new FileOutputStream(file);

		fop.write(content);
		fop.flush();
		fop.close();

	}
}
