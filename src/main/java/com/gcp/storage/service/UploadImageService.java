package com.gcp.storage.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcp.storage.config.CloudConfig;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;

public class UploadImageService {

	@Autowired
	private CloudConfig cloudConfig;
	
	/*try {
	    FileInputStream serviceAccount =
	            new FileInputStream("firebaseKey/serviceAccountKey.json");

	    FirebaseOptions options = new FirebaseOptions.Builder()
	            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
	            .setDatabaseUrl("https://xxxxx.firebaseio.com")
	            .build();
	    FirebaseApp fireApp = FirebaseApp.initializeApp(options);

	    StorageClient storageClient = StorageClient.getInstance(fireApp);
	    InputStream testFile = new FileInputStream(file.getPath());
	    String blobString = "NEW_FOLDER/" + "FILE_NAME.jpg";
	    Blob blob = storageClient.bucket("xxxxxx.appspot.com")
	                    .create(blobString, testFile , Bucket.BlobWriteOption.userProject("xxxxxxx"));
	    blob.getStorage().createAcl(blob.getBlobId(), Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));
	    System.out.println(blob.getMediaLink());
	} catch (Exception e){
	    e.printStackTrace();
	}*/


  public String uploadImage(/*String fileName , String filePath,String fileType*/) throws IOException {
    Bucket bucket = cloudConfig.getStorage().get("files-pdf");
    InputStream inputStream = new FileInputStream(new File("C:\\Users\\M1047094\\Desktop\\Sindhu\\image.jpg"));
    Blob blob = bucket.create("image", inputStream, "*/*");
    //log.info("Blob Link:"+blob.getMediaLink());
    return blob.getMediaLink();
  }


 /* private Bucket getBucket(String bucketName) throws IOException {
    GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(CREDENTIALS_PATH))
        .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
    Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
    Bucket bucket = storage.get(bucketName);
    if (bucket == null) {
      throw new IOException("Bucket not found:"+bucketName);
    }
    return bucket;
  }
*/
}
