import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;

public class Get_Image {
	public static void main(String[] args) throws IOException {
		Get_Image get = new Get_Image();


	
		String path = "/Users/bmhan/Documents/test_file";
		//get.image_resize(path);
		
		get.fileUrlReadAndDownload("https://smurfs.toptoon.com/assets/img/toptoon_thumb.jpg", path, "td01.jpg");
		//fileUrlReadAndDownload(String fileAddress, String downloadDir, String localFileName)
		
		System.out.print("Complete");
	}
	
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

	public static void openWebpage(String urlString) {
		try {
			Desktop.getDesktop().browse(new URL(urlString).toURI());
		} catch (Exception e) {
			e.printStackTrace();
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


	public void fileRename_front(File mainFile) {
		File tempFile = new File(mainFile.getParent() + "/20060621_" + mainFile.getName());
		mainFile.renameTo(tempFile);
	}
	
	// URL에서 이미지 다운로드 받아오는 함수.
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
			int size = 10000;   // 임의로 정함
			Url = new URL(fileAddress);
			outStream = new BufferedOutputStream(new FileOutputStream(downloadDir + "\\" + localFileName));

			uCon = Url.openConnection();
			uCon.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");  // 403 에러 방지해주는 코드
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

	
	/**
	 * 
	 * @param fileAddress
	 * @param downloadDir
	 */
	/*
	public static void fileUrlDownload(String fileAddress, String downloadDir) {

		int slashIndex = fileAddress.lastIndexOf('/');
		int periodIndex = fileAddress.lastIndexOf('.');

		// 파일 어드레스에서 마지막에 있는 파일이름을 취득
		String fileName = fileAddress.substring(slashIndex + 1);

		if (periodIndex >= 1 && slashIndex >= 0 && slashIndex < fileAddress.length() - 1) {
			fileUrlReadAndDownload(fileAddress, fileName, downloadDir);
		} else {
			System.err.println("path or file name NG.");
		}
	}
	*/
	
	/*public static void urlImageDownload(String fileAddress, String downloadDir) {
		File outputFile = new File(downloadDir); // 저장할 경로 및 파일명
		 
		URL url = null;
		BufferedImage bi = null;
		        
		try {
		    url = new URL(fileAddress);
		    bi = ImageIO.read(url);
		    ImageIO.write(bi, "jpg", outputFile);
		 
		} catch (MalformedURLException e) {
		   
		} catch (IOException e) {
		   
		}
	}*/
}
