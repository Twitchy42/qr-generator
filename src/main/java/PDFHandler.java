import java.awt.image.BufferedImage;




import java.awt.print.PrinterException;
import java.io.IOException;

import org.apache.pdfbox.PDFToImage;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;

public class PDFHandler{
	public static int MAX_ARRAY_SIZE = 24;
	public static void main(String[] args){
		PDFHandler pdfh = new PDFHandler();
		pdfh.makePDF("V",135);
		//pdfh.previewImage("NewPDF.pdf", "PreviewImage");
		//pdfh.printPDF();
		
	}
	
	public void makePDF(String liquorCode, int startingNumber){
		CodeMaker cm = new CodeMaker();;
		BufferedImage[] imageArray = new BufferedImage[MAX_ARRAY_SIZE];
		String[] codes = new String[MAX_ARRAY_SIZE];
		for(int i = 1; i < 25; i++){
			codes[i-1] = liquorCode + "-" + String.format("%05d",startingNumber);
			imageArray[i-1] = cm.makeCode(codes[i-1]);
			startingNumber++;
		}
		write(imageArray, codes);
	}
	
	public void write(BufferedImage[] bi, String[] codes) {
		try{
		PDDocument doc = new PDDocument();
		PDPage page = new PDPage();
		doc.addPage(page);
		/**
		PDXObjectImage image = null;
		image = new PDJpeg(doc, bi);
		**/
		PDPageContentStream content = new PDPageContentStream(doc, page);
		int x = 0;
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 6; j++){
				content.drawImage(new PDJpeg(doc, bi[x]), 45+130*i, 34 + 122*j);
				content.beginText();
				content.setFont(PDType1Font.COURIER_BOLD, 14);
				content.moveTextPositionByAmount(65+130*i, 42+122*j);
				content.drawString(codes[x]);
				content.endText();
				x++;
			}
			
		}
		content.close();
		
		doc.save("NewPDF.pdf");
		System.out.println("new pdf saved!");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void previewImage(String pdfPath, String imagePath){
		String[] args = new String[3];
		args[0] = "-outputPrefix";
		args[1] = imagePath;
		args[2] = pdfPath;
		
		try{
			PDFToImage.main(args);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void printPDF(){
		try {
			PDDocument doc = PDDocument.load("NewPDF.pdf");
			doc.print();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(PrinterException e){
			e.printStackTrace();
		}
		
	}
	
}