import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;




public class Get_Image {
	public static void main (String[] args) throws IOException{
		
		
		String path = "/Users/bmhan/Downloads/20190125_160513_";
		
		Get_Image getImg = new Get_Image();
		getImg.getFileName(path);
		
		System.out.print("Hello World");
		
	}
		
	public void getFileName(String path) throws IOException{
		
		File dir = new File(path);
		File[] fileList = dir.listFiles();

		
		for(int i=0; i<fileList.length; i++){
			File file = fileList[i];
			if(file.isFile()){
				System.out.println("File Name :" + path + file.getName());
				String eachFileName = file.getAbsoluteFile().toString();
				System.out.println(eachFileName);
				
				
				//1. ���Ͽ��� �̹��� �ҷ�����
				Image orginalImage = ImageIO.read(new File(eachFileName));
				//2. �̹��� ������ ����
				Image resizeImage = orginalImage.getScaledInstance((int) (orginalImage.getWidth(null)*(double)1.4), orginalImage.getHeight(null), Image.SCALE_SMOOTH);	
				//�ӵ����� �̹��� �ε巯�� �켱 (SCALE_AREA_AVERAGING, SCALE_DEFAULT, SCALE_FAST, SCALE_REPLICATE, SCALE_SMOOTH �߿� ����)
				//3. ������� �ű� �̹��� ����
				BufferedImage newImage = new BufferedImage( (int) (orginalImage.getWidth(null)*(double)1.4), orginalImage.getHeight(null), BufferedImage.TYPE_INT_RGB );
				//4. ������ �̹����� ũ�� ������ �̹��� �׸���
				Graphics g = newImage.getGraphics();
				g.drawImage(resizeImage, 0, 0, null);
				g.dispose();
				//5. ���� ������ �̹����� ���Ϸ� �����ϱ�
				ImageIO.write((RenderedImage) newImage, "jpg", new File("/Users/bmhan/Downloads/20190125_160513_/New_File" + i + ".jpg"));
				
			}else{
			}
		}

	}
}
