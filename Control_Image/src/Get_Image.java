import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;

import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageInputStream;

import org.w3c.dom.NamedNodeMap;

import com.sun.org.apache.bcel.internal.classfile.Node;
import com.sun.xml.internal.ws.api.addressing.WSEndpointReference.Metadata;

public class Get_Image {
	public static void main(String[] args) throws IOException {
		Get_Image get = new Get_Image();
		
		
		//BasicFileAttributes 이거 한번 찾아보자.
		
		//BufferedImage myPicture = ImageIO.read(new File("/Users/bmhan/Documents/결재영수증.jpg"));
		
		ImageObserver observer;
		//observer.imageUpdate(img, infoflags, x, y, width, height)
		//Image image = new Image();
		//image.getProperty("/Users/bmhan/Documents/결재영수증.jpg", null);
		//imggetProperty(String name, ImageObserver observer);
		

		JPEGImageWriteParam jpegParams = new JPEGImageWriteParam(null);
	
		//String path = "/Users/bmhan/Documents/test_file";

		
		//String path = "F:\\외장하드_20190815\\entertainment(사진)_20190706\\1990-2019\\test";

	    String filename = "C:\\Users\\ByungMinHan\\Desktop\\잡다";
	    
	    get.text_file_find(filename);
	    
	}
	
	public void text_file_find(String path) throws IOException {
		//String save = "result.txt";
		String word = "GTS";
		
		File dir = new File(path);
		
		File[] fileList = dir.listFiles();
		
		for (File file : fileList) { // Reads all files in directory
			if (file.isDirectory()) {
				String inDir = path + "/" + file.getName() + "";

				System.out.println("Directory Name :" + inDir);
				try {
					// This is Directory
				} catch (java.lang.NullPointerException e) {
				}
			} else {
				System.out.println("File Name :" + file.getName());
				
				//저장할 파일 output stream 생성
		        //PrintWriter writer = new PrintWriter(new FileWriter("새로운 파일명"));
       
	        	//읽어들일 파일 input stream 선언
		        BufferedReader br = null;
           
	            //input stream object 생성
	            br = new BufferedReader(new FileReader(file));
	            String line = "";
	           
	            //각 파일의 한 라인씩 읽어들인다.
	            while((line = br.readLine()) != null) {
	                //라인 내용중 검색하고자 단어가 있으면 파일에 기록한다.
	                if(line.indexOf(word) != -1) {
	                    //writer.write(word + "=" + file.getAbsolutePath());
	                	System.out.println("File Name : " + file.getName());
	                }
	            }
	            //writer.flush();
	          	// input stream close.
	            br.close();
	           
	            //output stream close.
	            // writer.close();
			}
		}
	}
	
	
	public void image_resize(String path) throws IOException {

		File dir = new File(path);
		File[] fileList = dir.listFiles();

		for (int i = 0; i < fileList.length; i++) {
			File file = fileList[i];
			if (file.isFile()) {
				System.out.println("File Name :" + path + file.getName());
				String eachFileName = file.getAbsoluteFile().toString();
				System.out.println(eachFileName);

				// 1. 파일에서 이미지 불러오기
				Image orginalImage = ImageIO.read(new File(eachFileName));
				// 2. 이미지 사이즈 수정
				Image resizeImage = orginalImage.getScaledInstance((int) (orginalImage.getWidth(null) * (double) 1.4),
						orginalImage.getHeight(null), Image.SCALE_SMOOTH);
				// 속도보다 이미지 부드러움 우선 (SCALE_AREA_AVERAGING, SCALE_DEFAULT, SCALE_FAST,
				// SCALE_REPLICATE, SCALE_SMOOTH 중에 선택)
				// 3. 결과물을 옮길 이미지 생성
				BufferedImage newImage = new BufferedImage((int) (orginalImage.getWidth(null) * (double) 1.4),
						orginalImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
				// 4. 생성한 이미지에 크기 수정된 이미지 그리기
				Graphics g = newImage.getGraphics();
				g.drawImage(resizeImage, 0, 0, null);
				g.dispose();
				// 5. 새로 생성한 이미지를 파일로 저장하기
				ImageIO.write((RenderedImage) newImage, "jpg", new File(path + "/New_File" + i + ".jpg"));

			} else {
			}
		}

	}

	public void getFileName(String path) {

		File dir = new File(path);
		File[] fileList = dir.listFiles();

		for (File file : fileList) { // Reads all files in directory
			if (file.isDirectory()) {
				String inDir = path + "/" + file.getName() + "";

				System.out.println("Directory Name :" + inDir);
				try {
					getFileName(inDir);
				} catch (java.lang.NullPointerException e) {
				}
			} else {
				String testName = file.getName();
				System.out.print("Parent File Name :" + file.getParent() + "--->");
				System.out.println("File Name :" + testName);

				// if(checkFileName(testName)==true) {
				System.out.println("Target File");
				//fileRename_back(file);
				System.out.println("Updated file Name : " + file.getName());
				// }

				// fileCopy(file.getAbsolutePath(), "/Users/IBM_ADMIN/Desktop/SoW/" + "[계약서]" +
				// file.getName());

			}
		}
	}

	public boolean checkFileName(String fileName) {
		// 2016-xx-xx -> 20160348
		boolean isTarget = false;
		if (fileName.charAt(4) == '-' && fileName.charAt(7) == '-') {
			isTarget = true;
		}
		return isTarget;
	}

	public void fileCopy(String inFileName, String outFileName) {
		try {
			FileInputStream fis = new FileInputStream(inFileName);
			FileOutputStream fos = new FileOutputStream(outFileName);

			int data = 0;
			while ((data = fis.read()) != -1) {
				fos.write(data);
			}
			fis.close();
			fos.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void fileRename_back(File mainFile, String concat_Str) {
		String temp = "";
		for (int i = 0; i < mainFile.getName().length(); i++) {
			if (mainFile.getName().charAt(i) == '_') {  // find the latest '_' in the filename
				temp = temp + '_';
				temp = temp + concat_Str;
			} else {
				temp = temp + mainFile.getName().charAt(i);
			}
		}
		File tempFile = new File(mainFile.getParent() + "/" + temp);
		mainFile.renameTo(tempFile);

	}

	public void fileRename_front(String path) {
		// 폴더 안에 있는 파일명의 앞부분을 일괄적으로 업데이트 한다. 
		File dir = new File(path);
		File[] fileList = dir.listFiles();

		for (File file : fileList) { // Reads all files in directory
			if (file.isDirectory()) {
				String inDir = path + "/" + file.getName() + "";
				System.out.println("Directory Name :" + inDir);
				try {
					getFileName(inDir);
				} catch (java.lang.NullPointerException e) {
				}
			} else {
				String testName = file.getName();
				System.out.print("Parent File Name :" + file.getParent() + "--->");
				System.out.println("File Name :" + testName);
				
				System.out.println("Updated file Name : " + file.getName());
				
				File tempFile = new File(file.getParent() + "/19000000_" + file.getName());
				file.renameTo(tempFile);
				
				System.out.println("Updated file Name : " + tempFile.getName());
			}
		}
	}
	
	public void fileRename_delete(String path) {
		// 폴더 안에 있는 파일명의 앞부분을 일괄적으로 업데이트 한다. 
		File dir = new File(path);
		File[] fileList = dir.listFiles();

		for (File file : fileList) { // Reads all files in directory
			if (file.isDirectory()) {
				String inDir = path + "/" + file.getName() + "";
				System.out.println("Directory Name :" + inDir);
				try {
					getFileName(inDir);
				} catch (java.lang.NullPointerException e) {
				}
			} else {
				String testName = file.getName();
				System.out.print("Parent File Name :" + file.getParent() + "--->");
				System.out.println("File Name :" + testName);
				
				
				
				File tempFile = new File(file.getParent()  + "/" + file.getName().replace("19000000_", ""));
				
				file.renameTo(tempFile);
				System.out.println("Updated file Name : " + tempFile.getName());
				
			}
		}
	}
}
