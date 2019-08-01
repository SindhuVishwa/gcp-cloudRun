package com.gcp.storage.service;

public class UploadImageService {
	
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
	}
-----------------------	
	@Override
  public String uploadImage(String fileName , String filePath,String fileType) throws IOException {
    Bucket bucket = getBucket("sample-bucket");
    InputStream inputStream = new FileInputStream(new File(filePath));
    Blob blob = bucket.create(fileName, inputStream, fileType);
    log.info("Blob Link:"+blob.getMediaLink());
    return blob.getMediaLink();
  }


  private Bucket getBucket(String bucketName) throws IOException {
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
