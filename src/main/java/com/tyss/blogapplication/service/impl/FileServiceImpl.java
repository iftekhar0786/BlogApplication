package com.tyss.blogapplication.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.tyss.blogapplication.service.FileService;

public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile multipartFile) throws IOException {
		
		//file name
		String originalFilename = multipartFile.getOriginalFilename();
		
		
		
		
		//random name  generate file
		String randomId = UUID.randomUUID().toString();
		
		String fileName = randomId.concat(originalFilename.substring(originalFilename.lastIndexOf(".")));
			
		//full path
		//  path + File.separator + fileName;
		return null;
	}

	@Override
	public InputStream getResources(String path, String fileName) throws FileNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
