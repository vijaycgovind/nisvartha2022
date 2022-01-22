package org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.interfaces.rest;

import java.io.File;
import java.util.Arrays;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import lombok.extern.slf4j.Slf4j;
import java.time.Year;


@RestController
@RequestMapping("/document")
@Slf4j
public class UploadController {
	
	//path where nisartha student files will be created
	private static String UPLOADED_FOLDER = "/Users/vijaygovind/Documents/upload/";
	
	
	@PostMapping("/uploadFiles")
	public boolean uploadFiles(@RequestParam("file") MultipartFile[] files) {
		//creating Nivsartha Temp student allocation number with NFTAN + Year+ RANDOM 6 chars from string provided
		String directoryName = "NFTAN-"+ Year.now()+"-"+RandomStringUtils.random(6, "LLVVEEKKAARRNNAATTAAKKAANNSSVVAARRTTHANDA223334444555556666666777777778888888889999999990");
		//setting return status flag to false
		boolean filewritestatus=false;
		
		try {
			//create file path with path and nftan number so that unique folder per student gets created
		File theDir = new File(UPLOADED_FOLDER+File.separator+directoryName);
		if (!theDir.exists()){
		    theDir.mkdirs();
		    }
		}catch (Exception e) {
			//directory create exception so set the retrun status to false
			filewritestatus=false;
			log.error("Error While creating NFTAN folder during student file upload process!!");
            e.printStackTrace();
        }

		try {
			//for each file uploade call upload file function towards writing
//	    Arrays.asList(files)
//	        .stream()
//	        .forEach(file -> FileService.uploadFile(file,UPLOADED_FOLDER+File.separator+directoryName));
//	    //if all files written successfully, set the retrun status flag to true
//	    filewritestatus=true;
		}catch (Exception e) {
			//file create exception so set the retrun status to false
			filewritestatus=false;
			log.error("Error While writing the student uploaded files!!");
            e.printStackTrace();
        }
	 
	    return filewritestatus;
	}


}
