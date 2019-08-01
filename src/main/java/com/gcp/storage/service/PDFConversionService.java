package com.gcp.storage.service;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.text.Document;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

public class PDFConversionService {

	
	public void convert() throws InvalidPasswordException, IOException
	{	
	// File file = new File("C:\\Users\\M1047094\\Desktop\\Sindhu\\sample.pdf");
		//File file = new File("https://storage.cloud.google.com/files-pdf/sample.pdf");
		
		URL url = new URL("https://storage.cloud.google.com/files-pdf/sample.pdf");
		File file = new File(url.getFile());
	PDDocument document = PDDocument.load(file);
	PDFRenderer pdfRenderer = new PDFRenderer(document);	
	for (int page = 0; page < document.getNumberOfPages(); ++page)
	{ 
	    BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
	    // suffix in filename will be used as the file format
	    ImageIOUtil.writeImage(bim, file + "-" + (page+1) + ".png", 300);
	}
	document.close();
	}
}
