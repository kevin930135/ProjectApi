package com.example.ProjectApi.updatetxtImpl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;

import com.example.ProjectApi.entity.TrainModel;
//import com.example.ProjectApi.retxt.TrainRetxt;

public class UpdateTxtImpl {

	public boolean updatetxtTrain(TrainModel trainmodel) throws IOException {
		String userId, photoName;
		boolean flag = false;
		
		userId = (trainmodel.getUserId());
		photoName = trainmodel.getphotoName();
		Random ran = new Random();
		String ranNum = Integer.toString(ran.nextInt(100)+1);
		FileWriter fw = new FileWriter("C:\\eGroupAI_FaceEngine_v3.1.0\\list.txt", true);

		String newText = "C:\\eGroupAI_FaceEngine_v3.1.0\\TrainFace\\" + photoName + ".jpg"+ "\t" + userId + "[No]" + ranNum;

		// 輸入新辨識資料 到list.txt檔裡
		fw.write("\r\n");
		fw.write(newText);
		flag = true;

		// 寫入完關閉檔案，並儲存
		fw.close();
		return flag;
	}
	
	public boolean updatetxtRetrieve(TrainModel trainmodel) throws IOException {
		String photoName;
		boolean flag = false;
		photoName = trainmodel.getphotoName();
		
		File file = new File("C:\\eGroupAI_FaceEngine_v3.1.0\\photolist.egroupList");
		file.delete();
		file.createNewFile();
		
		FileWriter fw = new FileWriter("C:\\eGroupAI_FaceEngine_v3.1.0\\photolist.egroupList", true);

		String newText = "C:\\eGroupAI_FaceEngine_v3.1.0\\RetrievePhoto\\" + photoName + ".jpg";

		// 輸入新辨識資料 到list.txt檔裡
		fw.write(newText);
		flag = true;

		// 寫入完關閉檔案，並儲存
		fw.close();
		return flag;
	}
	
	
}
