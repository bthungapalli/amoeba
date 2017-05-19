package com.js.amoeba.utils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.js.amoeba.exception.AmoebaDaoException;


//import com.js.Amoeba.utils.AmoebaUtils;

public class AmoebaUtils {
	private static final Logger log = LoggerFactory
			.getLogger(AmoebaUtils.class);
	
	public static boolean validateVideoFormat(String fileExtension) {
		String str[] = new String[] {"webm", "ogg", "ogv" };
		List<String> list = Arrays.asList(str);
		return list.contains(fileExtension);
	}

	public static boolean validateImageFormat(String fileExtension) {
		String str[] = new String[] {"jpeg", "jpg", "png" };
		List<String> list = Arrays.asList(str);
		return list.contains(fileExtension);
	}

	public String getVideoFileExtension(MultipartFile file) {
		String fileExtension = "";
		if (file != null) {
			if (!file.isEmpty() && file.getContentType().contains("/")) {
				String[] array = file.getContentType().split("/");
				fileExtension = array[1];
			}
		}
		return fileExtension;
	}

	public String saveFile(MultipartFile attachment, String sources, String path) throws AmoebaDaoException {
		File fileDirectory = new File(path);
		if (!fileDirectory.exists()) {
			fileDirectory.mkdirs();
		}
		String fileName = sources + "-" + attachment.getOriginalFilename();
		path = fileDirectory + File.separator +fileName;
		File file = new File(path);
		try {

			FileCopyUtils.copy(attachment.getBytes(), file);
		} catch (IllegalStateException | IOException e) {
			throw new AmoebaDaoException(e.getMessage());
		}
		return fileName;
	}
	
	
	public byte[] fetchBytes(String path) throws IOException{
		
		File file = new File(path);
		return FileUtils.readFileToByteArray(file);
	}
	
	public static String fetchFirstLastName(String firstName,String lastName){
		return  firstName+" "+ lastName;
	}
	
	

}
