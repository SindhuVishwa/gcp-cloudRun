package com.gcp.storage.config;

import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@Configuration
public class CloudConfig {
	
	@Autowired
	Storage storage;

	@Bean
	public Storage getStorage() throws IOException {
	
	Credentials credentials = GoogleCredentials
			  .fromStream(new FileInputStream("C:/Users/M1047094/Desktop/Sindhu/encoded-bonfire-246911-93527624d928.json"));
	 storage = StorageOptions.newBuilder().setCredentials(credentials)
			  .setProjectId("encoded-bonfire-246911").build().getService();	 
	 return storage;
	}	

}
