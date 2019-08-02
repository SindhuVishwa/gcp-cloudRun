package com.gcp.storage.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gcp.storage.config.CloudConfig;
import com.gcp.storage.model.Body;
import com.gcp.storage.service.DownloadService;
import com.gcp.storage.service.PDFConversionService;
import com.gcp.storage.service.UploadImageService;
import com.google.gson.Gson;

import java.util.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class GCPController {
	
	@Autowired
	private DownloadService downloadService;
	@Autowired
	private UploadImageService uploadImageService;
	
	
	@Autowired
	private PDFConversionService pDFConversionService;
	
	/*@Value("gs://${gcs-resource-test-bucket}/my-file.txt")
	private Resource gcsFile;*/

	 @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/read", method = RequestMethod.POST)
	  public String receiveMessage(@RequestBody String message) throws Exception {
	      System.out.println(message);
		 
	    // Get PubSub message from request body.
		 //Body.Message message = body.getMessage();
		    Gson gson = new Gson();
	    if (message == null) {
	      String msg = "Bad Request: invalid Pub/Sub message format";
	      System.out.println(msg);
	    //  return new ResponseEntity(msg, HttpStatus.BAD_REQUEST);
	    }
	    
	    Body body = gson.fromJson(message, Body.class);
	    String bucketId = body.getMessage().getAttribute().getBucketId();
	    String objectId = body.getMessage().getAttribute().getObjectId();
	    
	    downloadService.downloadFile(objectId,bucketId);
	    
	  //  String gcs = "gs://" + bucketId + "/" + objectId; 
	  /*//  String data = message.getData();
	    String data = message;
	    String target =
	        !StringUtils.isEmpty(data) ? new String(Base64.getDecoder().decode(data)) : "World";
	    String msg = "Hello " + target + "!";

	    System.out.println(msg);*/
	    return "file downloaded";
	  }

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public ResponseEntity downloadFile() throws Exception {	
		
		downloadService.downloadFile("sample.pdf","files-pdf");
		
		 return new ResponseEntity("file downloaded", HttpStatus.OK);
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/convert", method = RequestMethod.GET)
	public ResponseEntity convertFile() throws IOException {	
		
		pDFConversionService.convert();
		
		 return new ResponseEntity("file converted", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String readGcsFile() throws IOException {	
		
	/*	return StreamUtils.copyToString(
				this.gcsFile.getInputStream(),
				Charset.defaultCharset()) + "\n";*/
		
		return uploadImageService.uploadImage();
	}
	
	
}
