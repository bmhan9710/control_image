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
				
				
				//1. 파일에서 이미지 불러오기
				Image orginalImage = ImageIO.read(new File(eachFileName));
				//2. 이미지 사이즈 수정
				Image resizeImage = orginalImage.getScaledInstance((int) (orginalImage.getWidth(null)*(double)1.4), orginalImage.getHeight(null), Image.SCALE_SMOOTH);	
				//속도보다 이미지 부드러움 우선 (SCALE_AREA_AVERAGING, SCALE_DEFAULT, SCALE_FAST, SCALE_REPLICATE, SCALE_SMOOTH 중에 선택)
				//3. 결과물을 옮길 이미지 생성
				BufferedImage newImage = new BufferedImage( (int) (orginalImage.getWidth(null)*(double)1.4), orginalImage.getHeight(null), BufferedImage.TYPE_INT_RGB );
				//4. 생성한 이미지에 크기 수정된 이미지 그리기
				Graphics g = newImage.getGraphics();
				g.drawImage(resizeImage, 0, 0, null);
				g.dispose();
				//5. 새로 생성한 이미지를 파일로 저장하기
				ImageIO.write((RenderedImage) newImage, "jpg", new File("/Users/bmhan/Downloads/20190125_160513_/New_File" + i + ".jpg"));
				
			}else{
			}
		}

	}
}
