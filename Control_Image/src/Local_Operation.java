import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Local_Operation {

	public List<String> findTextFromFile(String path, String findText) throws IOException {
		
		File dir = new File(path);
		File[] fileList = dir.listFiles();
		
		List<String> resultList = new ArrayList<String>();
		
		for (File file : fileList) { // Reads all files in directory
			if (file.isDirectory()) {
				String inDir = path + "/" + file.getName() + "";

				System.out.println("Directory Name :" + inDir);
			} else {
				System.out.println("Searcing file name " + file.getName());

	        	// Declare input stream to read file
		        BufferedReader br = null;
           
	            // create input stream object
	            br = new BufferedReader(new FileReader(file));
	            String line = "";
	           
	            // Read each line per file
	            while((line = br.readLine()) != null) {
	                
	            	// If the letter is found
	                if(line.indexOf(findText) != -1) {
	                	
	                	resultList.add(file.getName());
	                    System.out.println("Text " + findText + " found in File Name " + file.getName() + ".");
	                    break;
	                }
	            }
	            br.close();
			}
		}
		
		return resultList;
	}
	
	
	public void resizeImage(String path) throws IOException {

		File dir = new File(path);
		File[] fileList = dir.listFiles();

		for (int i = 0; i < fileList.length; i++) {
			File file = fileList[i];
			if (file.isFile()) {
				System.out.println("File Name :" + path + file.getName());
				String eachFileName = file.getAbsoluteFile().toString();
				System.out.println(eachFileName);

				// 1. ���Ͽ��� �̹��� �ҷ�����
				Image orginalImage = ImageIO.read(new File(eachFileName));
				// 2. �̹��� ������ ����
				Image resizeImage = orginalImage.getScaledInstance((int) (orginalImage.getWidth(null) * (double) 1.4),
						orginalImage.getHeight(null), Image.SCALE_SMOOTH);
				// �ӵ����� �̹��� �ε巯�� �켱 (SCALE_AREA_AVERAGING, SCALE_DEFAULT, SCALE_FAST,
				// SCALE_REPLICATE, SCALE_SMOOTH �߿� ����)
				// 3. ������� �ű� �̹��� ����
				BufferedImage newImage = new BufferedImage((int) (orginalImage.getWidth(null) * (double) 1.4),
						orginalImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
				// 4. ������ �̹����� ũ�� ������ �̹��� �׸���
				Graphics g = newImage.getGraphics();
				g.drawImage(resizeImage, 0, 0, null);
				g.dispose();
				// 5. ���� ������ �̹����� ���Ϸ� �����ϱ�
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

				// fileCopy(file.getAbsolutePath(), "/Users/IBM_ADMIN/Desktop/SoW/" + "[��༭]" +
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
		// ���� �ȿ� �ִ� ���ϸ��� �պκ��� �ϰ������� ������Ʈ �Ѵ�. 
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
		// ���� �ȿ� �ִ� ���ϸ��� �պκ��� �ϰ������� ������Ʈ �Ѵ�. 
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
