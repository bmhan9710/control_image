import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.im4java.core.Stream2BufferedImage;
import org.im4java.process.Pipe;
import org.im4java.process.ProcessStarter;

public class File_Operation {

	// Function: Find specific text from selected folder's files
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
	
	// Function: Replace letters of the files in the path
	public void fileRename_replace(String path, String originalStr, String replaceStr) {
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
				
				// change file name here   
				File tempFile = new File(file.getParent()  + "/" + file.getName().replace(originalStr, replaceStr));
				
				file.renameTo(tempFile);
				System.out.println("Updated file Name : " + tempFile.getName());
				
			}
		}
	}
	
	public static void openWebpage(String urlString) {
		try {
			Desktop.getDesktop().browse(new URL(urlString).toURI());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// URL���� �̹��� �ٿ�ε� �޾ƿ��� �Լ�.
	public static void fileUrlReadAndDownload(String fileAddress, String downloadDir, String localFileName) {
		OutputStream outStream = null;
		URLConnection uCon = null;

		InputStream is = null;
		try {

			System.out.println("-------Download Start------");

			URL Url;
			byte[] buf;
			int byteRead;
			int byteWritten = 0;
			int size = 10000;   // ���Ƿ� ����
			Url = new URL(fileAddress);
			outStream = new BufferedOutputStream(new FileOutputStream(downloadDir + "\\" + localFileName));

			uCon = Url.openConnection();
			uCon.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");  // 403 ���� �������ִ� �ڵ�
			is = uCon.getInputStream();
			buf = new byte[size];
			while ((byteRead = is.read(buf)) != -1) {
				System.out.print("Test" + buf);
				outStream.write(buf, 0, byteRead);
				byteWritten += byteRead;
			}

			System.out.println("Download Successfully.");
			System.out.println("File name : " + localFileName);
			System.out.println("of bytes  : " + byteWritten);
			System.out.println("-------Download End--------");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				outStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void convertFileJpg(String filePath) {
		File dir = new File(filePath);
		File[] fileList = dir.listFiles();
		List<String> resultList = new ArrayList<String>();
		
		for (File file : fileList) { // Reads all files in directory
			String fileFullPath = filePath + "/" + file.getName() + "";
			if (file.isDirectory()) {
				System.out.println("Directory Name :" + fileFullPath);
			} else {
				System.out.println("Searcing file name " + fileFullPath);

				byte[] fileContent = null;
				// Read file from folder
				try {
					fileContent = Files.readAllBytes(file.toPath());
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				try {
					convertHEICtoJPG("C:/Users/bmhan/Desktop/새 폴더/imagetest/imagesample1.heic", "C:/Users/bmhan/Desktop/새 폴더/imagetest/imagesample1.jpg");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}
	
	public static void convertHEICtoJPG(String inputPath, String outputPath) throws Exception {
        // Create a ConvertCmd object
        ConvertCmd cmd = new ConvertCmd();
        //cmd.setSearchPath("C:/Users/bmhan/Desktop/새 폴더/imagetest/");
        // Create an IMOperation object to specify the image processing operations
        IMOperation operation = new IMOperation();

        // Add the input file path
        operation.addImage(inputPath);

        System.out.println(" Search path " + cmd.getSearchPath() );
        
        // Set the output format to JPG
        operation.addImage(outputPath);

        
        cmd.setSearchPath("C:\\Users\\bmhan\\Downloads\\ImageMagick-7.1.1-29-portable-Q16-x64");  // Added "C:\Users\bmhan\Downloads\ImageMagick-7.1.1-29-portable-Q16-x64" to environment variable path
        //cmd.setGlobalSearchPath("C:/Program Files/ImageMagick-7.1.1-Q16-HDRI");
        System.out.println(" Search path " + cmd.getGlobalSearchPath() );
        //ProcessStarter.setGlobalSearchPath
        
        
        // Execute the command
        cmd.run(operation);
    }
	
	/*
	public static byte[] convertHEICtoJPEG(byte[] heicBytes, String filePath) throws IOException, InterruptedException, IM4JavaException {
	    IMOperation op = new IMOperation();
	    op.addImage("-");
	    op.addImage("jpeg:-");
	    File tempFile = File.createTempFile("temp", ".heic");
	    try (FileOutputStream fos = new FileOutputStream(tempFile)) {
	        fos.write(heicBytes);
	    }
	    FileInputStream fis = new FileInputStream(tempFile);
	    Pipe pipeIn  = new Pipe(fis,null);
	    ConvertCmd convert = new ConvertCmd();
	    //convert.setSearchPath(IMAGE_MAGICK_PATH);   // Set path
	    convert.setSearchPath("C:\\\\Users\\\\bmhan\\\\Downloads\\\\ImageMagick-7.1.1-29-portable-Q16-x64");
	    convert.setInputProvider(pipeIn); 
	    Stream2BufferedImage s2b = new Stream2BufferedImage();
	    convert.setOutputConsumer(s2b);
	    convert.run(op);
	    BufferedImage image = s2b.getImage();
	    byte[] imageBytes = imageToBytes(image);
	    fis.close();
	    return imageBytes;

	}

	private static byte[] imageToBytes(BufferedImage image) throws IOException {
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    ImageIO.write(image, "jpeg", baos);
	    baos.flush();
	    byte[] imageBytes = baos.toByteArray();
	    baos.close();
	    return imageBytes;
	}
	*/
	
	
	
	public void mergeSort(int[] a, int start, int end) {
		int temp;
		int temp_arr[] = new int[end-start+1];
		
		System.out.println("Separate Start: " + start + " end: " + end);
		
		if(end-start<2) {
			if(a[end]<a[start]) {
				temp=a[end];
				a[end]=a[start];
				a[start]=temp;
			}
			System.out.print("Switch result: ");
			for(int i=0; i<a.length; i++) {
				System.out.print(a[i] + " ");
			}System.out.println();
		}else {
			mergeSort(a, start, start+(end-start)/2);
			
			mergeSort(a, start+(end-start)/2+1, end);
			
			
			int cur_front = start;
			int cur_back = start+((end-start)/2)+1;
			int limit_cur_front=cur_back;
			int limit_cur_back=end+1;
			int temp_cur = 0;
			
  			while(temp_cur <= end-start) {
  				if(cur_front >= limit_cur_front) {
  					temp_arr[temp_cur]=a[cur_back];
					cur_back++;
  				}else if(cur_back >= limit_cur_back) {
  					temp_arr[temp_cur]=a[cur_front];
					cur_front++;
  				}else {
  					if(a[cur_front] <= a[cur_back]) {
						temp_arr[temp_cur]=a[cur_front];
						cur_front++;
					}else if(a[cur_front] > a[cur_back]) {
						temp_arr[temp_cur]=a[cur_back];
						cur_back++;
					}
				}
				temp_cur++;
			}
			for(int i=start, j=0; i<end+1; i++, j++) {
				a[i] = temp_arr[j];
			}
			
			System.out.print("Merge result: ");
			for(int i=0; i<a.length; i++) {
				System.out.print(a[i] + " ");
			}System.out.println();
			
			
		}   
	}
	
}
