package com.gcp.storage.config;

import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gcp.storage.service.DownloadService;
import com.gcp.storage.service.PDFConversionService;
import com.gcp.storage.service.UploadImageService;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.common.collect.Lists;

@Configuration
public class CloudConfig {
	
	@Autowired
	Storage storage;

	@Bean
	public Storage getStorage() throws IOException {
	
	Credentials credentials = GoogleCredentials
			  .fromStream(new FileInputStream("C:/Users/M1047094/Desktop/Sindhu/encoded-bonfire-246911-adea1cc88be0.json")).createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
	 storage = StorageOptions.newBuilder().setCredentials(credentials)
			  .setProjectId("encoded-bonfire-246911").build().getService();	 
	 return storage;
	}	
	@Bean
	public DownloadService downloadService()
	{
		return new DownloadService();
	}

	
	@Bean
	public UploadImageService uploadImageService()
	{
		return new UploadImageService();
	}

@Bean
public PDFConversionService pDFConversionService()
{
	return new PDFConversionService();
}

}
