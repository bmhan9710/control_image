import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;



public class Get_Image {
	
	static String searchPath="";
	static String searchText="";
	
	public static void main(String[] args) throws IOException {
		
		// Constructor
		Get_Image get = new Get_Image();
		
		
		// set up frame
		JFrame frame = new JFrame();
		frame.setSize(500, 500);
		frame.setTitle("Simple Calculator");
		frame.setLocationByPlatform(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// set up panel
		JPanel panel = new JPanel();
		// set layout to 5x2 grid layout
		panel.setLayout(new GridLayout(5, 2));

		// set up answer label
		JLabel answer = new JLabel();

		// set up number text fields
		JTextField searchPathLbl = new JTextField();
		JTextField searchTextLbl = new JTextField();

		
		
		// set up buttons
		JButton add = new JButton();
		add.setText("+");
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				
				
				try {
					
					searchText = searchTextLbl.getText();

					JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
					
					// invoke the showsOpenDialog function to show the save dialog
		            int selectFile = fileChooser.showOpenDialog(null);
		 
		            // if the user selects a file
		            if (selectFile == JFileChooser.APPROVE_OPTION) {
		                // set the label to the path of the selected file
		            	searchPathLbl.setText(fileChooser.getSelectedFile().getAbsolutePath());
		            	searchPath = searchPathLbl.getText();
		            }
		            // if the user cancelled the operation
		            else {
		            	searchPathLbl.setText("Non selected");
		            }

					Local_Operation lo = new Local_Operation();
					lo.text_file_find(searchPath, searchText);
					
				} catch (Exception e) {
					answer.setText("Error!");
				}
			}
		});
		
		
		
		
		// add components to panel
		panel.add(new JLabel("Number 1"));
		panel.add(new JLabel("Number 2"));
		panel.add(searchPathLbl);
		panel.add(searchTextLbl);
		panel.add(add);
		//panel.add(sub);
		//panel.add(mul);
		//panel.add(div);
		panel.add(new JLabel("Answer"));
		panel.add(answer);

		// add panel to frame and make it visible
		frame.add(panel);
		frame.setVisible(true);
	    
		
		/*
		
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
	    */
	}
	
}
