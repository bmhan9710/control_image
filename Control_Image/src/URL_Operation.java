import java.awt.Desktop;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class URL_Operation {

	
	public static void openWebpage(String urlString) {
		try {
			Desktop.getDesktop().browse(new URL(urlString).toURI());
		} catch (Exception e) {
			e.printStackTrace();
		}
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
