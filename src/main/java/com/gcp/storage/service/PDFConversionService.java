package com.gcp.storage.service;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.text.Document;

public class PDFConversionService {
	/*
	   private static final String FILEPATH = null;
	Document document = new Document();
       try {
          document.setFile(FILEPATH);
       } catch (Exception ex) {
          System.out.println("Error IOException " + ex);
       }
       float scale = 1.0f;
       float rotation = 0f;
       for (int i = 0; i < document.getNumberOfPages(); i++) {
          BufferedImage image = (BufferedImage) document.getPageImage(
              i, GraphicsRenderingHints.PRINT, Page.BOUNDARY_CROPBOX, rotation, scale);
          RenderedImage rendImage = image;
          try {
             System.out.println(" capturing page " + i);
             File file = new File("imageCapture1_" + i + ".tif");
             ImageIO.write(rendImage, "tiff", file);
          } catch (IOException e) {
             e.printStackTrace();
          }
          image.flush();
       }
       document.dispose();*/

}
