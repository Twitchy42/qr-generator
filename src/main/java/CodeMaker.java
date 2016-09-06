
import com.google.zxing.*;
import com.google.zxing.common.*;
import com.google.zxing.qrcode.*;

import java.io.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.*;

import javax.imageio.ImageIO;

public class CodeMaker {

	public static void main(String[] args){
		CodeMaker cm = new CodeMaker();
		cm.makeCode("21");
	}
	
	public BufferedImage makeCode(String codeText){
		int size = 125;
		
		try{
			QRCodeWriter writer = new QRCodeWriter();
			BitMatrix matrix = writer.encode(codeText, BarcodeFormat.QR_CODE, size, size);
			int imageWidth = matrix.getWidth();
			
			BufferedImage image = new BufferedImage(imageWidth, imageWidth, BufferedImage.TYPE_INT_RGB);
			image.createGraphics();
			
			Graphics2D graphics = (Graphics2D) image.getGraphics();
			graphics.setColor(Color.WHITE);
			graphics.fillRect(0, 0, imageWidth, imageWidth);
			graphics.setColor(Color.BLACK);
			
			for(int i = 0; i < imageWidth; i++){
				for(int j = 0; j < imageWidth; j++){
					if(matrix.get(i, j)){
						graphics.fillRect(i, j, 1, 1);
					}
				}
			}
			
			return image;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
}



