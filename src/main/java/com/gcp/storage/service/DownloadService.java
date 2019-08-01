package com.gcp.storage.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import com.google.api.client.util.Base64;
import com.google.cloud.ReadChannel;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
public class DownloadService {
	
    String url = "https://storage.cloud.google.com/files-pdf/sample.pdf";
/*    
	public void download()
	{
	
	try {
        downloadUsingNIO(url, "C:/Users/M1047094/Desktop/Sindhu/destination/sample.txt");
        
      //  downloadUsingStream(url, "/Users/pankaj/sitemap_stream.xml");
    } catch (IOException e) {
        e.printStackTrace();
    }
}

private static void downloadUsingStream(String urlStr, String file) throws IOException{
    URL url = new URL(urlStr);
    BufferedInputStream bis = new BufferedInputStream(url.openStream());
    FileOutputStream fis = new FileOutputStream(file);
    byte[] buffer = new byte[1024];
    int count=0;
    while((count = bis.read(buffer,0,1024)) != -1)
    {
        fis.write(buffer, 0, count);
    }
    fis.close();
    bis.close();
}

private static void downloadUsingNIO(String urlStr, String file) throws IOException {
    URL url = new URL(urlStr);
    ReadableByteChannel rbc = Channels.newChannel(url.openStream());
    FileOutputStream fos = new FileOutputStream(file);
    fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
    fos.close();
    rbc.close();
}
*/
    
    private static final int MAX_NUMBER_OF_TRIES = 3;
    @SuppressWarnings("resource")
	public Path downloadFile(String storageFileName, String bucketName) throws Exception {
        // In my real code, this is a field populated in the constructor.
        Storage storage = Objects.requireNonNull(StorageOptions.getDefaultInstance().getService());

        BlobId blobId = BlobId.of(bucketName, storageFileName);
        Path outputFile = Paths.get(storageFileName.replaceAll("/", "-"));
        int retryCounter = 1;
        Blob blob;
        boolean checksumOk;
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }

        do {
         //   LOGGER.debug("Start download file {} from bucket {} to Content Store (try {})", storageFileName, bucketName, retryCounter);
            blob = storage.get(blobId);
            if (null == blob) {
                throw new Exception("Failed to download file after " + retryCounter + " tries.");
            }
            if (Files.exists(outputFile)) {
                Files.delete(outputFile);
            }
            try (ReadChannel reader = blob.reader();
                 FileChannel channel = new FileOutputStream(outputFile.toFile(), true).getChannel()) {
                ByteBuffer bytes = ByteBuffer.allocate(128 * 1024);
                int bytesRead = reader.read(bytes);
                while (bytesRead > 0) {
                    bytes.flip();
                    messageDigest.update(bytes.array(), 0, bytesRead);
                    channel.write(bytes);
                    bytes.clear();
                    bytesRead = reader.read(bytes);
                }
            }
            String checksum = Base64.encodeBase64String(messageDigest.digest());
            checksumOk = checksum.equals(blob.getMd5());
            if (!checksumOk) {
                Files.delete(outputFile);
                messageDigest.reset();
            }
        } while (++retryCounter <= MAX_NUMBER_OF_TRIES && !checksumOk);
        if (!checksumOk) {
            throw new Exception("Failed to download file after " + MAX_NUMBER_OF_TRIES + " tries.");
        }
        
    //  File file =  new File(outputFile.toFile(), "");
      FileOutputStream fos = new FileOutputStream(outputFile.toFile());
      fos.close();
        return outputFile;
    }

    
}
