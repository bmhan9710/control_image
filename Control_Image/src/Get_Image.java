import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;



public class Get_Image {
	
	static String searchPath="";
	static String searchText="";
	
	public static void main(String[] args) throws IOException {
		
		// Constructor
		Get_Image get = new Get_Image();
		
		
		// set up frame
		JFrame frame = new JFrame();
		frame.setSize(800, 500);
		frame.setTitle("Text Search Tool");
		frame.setLocationByPlatform(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// set up panel
		Container panel = new JPanel();
		
		panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		// set up answer label
		JLabel answer = new JLabel();
		
		
		
		// set up number text fields
		JTextField searchPathFld= new JTextField();
		JTextField searchTextFld = new JTextField();

		
		
		// set up buttons
		JButton searchPathBtn = new JButton();
		searchPathBtn.setText("Search Path");
		searchPathBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				
				
				try {
					
					searchText = searchTextFld.getText();

					JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
					fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // To enable directories selection only.
					
					// invoke the showsOpenDialog function to show the save dialog
		            int selectFile = fileChooser.showOpenDialog(null);
		 
		            // if the user selects a file
		            if (selectFile == JFileChooser.APPROVE_OPTION) {
		                // set the label to the path of the selected file
		            	searchPathFld.setText(fileChooser.getSelectedFile().getAbsolutePath());
		            	searchPath = searchPathFld.getText();
		            }
		            // if the user cancelled the operation
		            else {
		            	searchPathFld.setText("Non selected");
		            }

					Local_Operation lo = new Local_Operation();
					lo.text_file_find(searchPath, searchText);
					
				} catch (Exception e) {
					answer.setText("Error!");
				}
			}
		});
		
		
		answer.setText("no Error!");
		
		
		// add components to panel
		panel.add(new JLabel("Find Directory"));
		
		c.ipady = 40;      // make this line tall
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;   // width of object
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 0;
		panel.add(searchPathBtn, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
	    c.weightx = 1;
	    c.weighty = 1;
	    c.gridx = 1;
	    c.gridy = 0;
		panel.add(searchPathFld, c);
		c.ipady = 20;      //make this line back to normal
		//c.gridwidth = 6;
		
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 2;
		panel.add(new JLabel("Search Text"), c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		//c.ipady = 40;      //make this component tall
		c.weightx = 0.5;
		c.gridwidth = 3;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 1;
		c.gridy = 2;
		panel.add(searchTextFld, c);
		
		
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 3;
		panel.add(answer, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 1;
		c.gridy = 3;
		panel.add(new JLabel(""), c);

		
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
