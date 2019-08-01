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

import com.gcp.storage.model.Body;
import com.gcp.storage.service.DownloadService;
import com.gcp.storage.service.PDFConversionService;

import java.util.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class GCPController {
	
	@Autowired
	private DownloadService downloadService;
	
	@Autowired
	private PDFConversionService pDFConversionService;
	
	@Value("gs://${gcs-resource-test-bucket}/my-file.txt")
	private Resource gcsFile;

	 @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/", method = RequestMethod.POST)
	  public ResponseEntity receiveMessage(@RequestBody Body body) {
	    // Get PubSub message from request body.
	    Body.Message message = body.getMessage();
	    if (message == null) {
	      String msg = "Bad Request: invalid Pub/Sub message format";
	      System.out.println(msg);
	      return new ResponseEntity(msg, HttpStatus.BAD_REQUEST);
	    }

	    String data = message.getData();
	    String target =
	        !StringUtils.isEmpty(data) ? new String(Base64.getDecoder().decode(data)) : "World";
	    String msg = "Hello " + target + "!";

	    System.out.println(msg);
	    return new ResponseEntity(msg, HttpStatus.OK);
	  }
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String readGcsFile() throws IOException {	
		
		return StreamUtils.copyToString(
				this.gcsFile.getInputStream(),
				Charset.defaultCharset()) + "\n";
	}
	
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public ResponseEntity downloadFile() throws Exception {	
		
		downloadService.downloadFile("sample.pdf","files-pdf");
		
		 return new ResponseEntity("file downloaded", HttpStatus.OK);
	}
	@RequestMapping(value = "/convert", method = RequestMethod.GET)
	public ResponseEntity convertFile() throws IOException {	
		
		pDFConversionService.convert();
		
		 return new ResponseEntity("file converted", HttpStatus.OK);
	}
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	String writeGcs(@RequestBody String data) throws IOException {
		try (OutputStream os = ((WritableResource) this.gcsFile).getOutputStream()) {
			os.write(data.getBytes());
		}
		return "file was updated\n";
	}
}
