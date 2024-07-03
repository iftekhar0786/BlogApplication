package com.tyss.blogapplication.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	String uploadImage(String path, MultipartFile multipartFile) throws IOException;
	InputStream getResources(String path, String fileName) throws FileNotFoundException;
}
