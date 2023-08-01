import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		//panel.setBounds(10, 10, 10, 10);
		
	    
		// set up answer label
		JLabel resultLbl = new JLabel();
		resultLbl.setText("No Error!");
		
		// set up number text fields
		JTextField searchPathFld= new JTextField();
		JTextField searchTextFld = new JTextField();

		
		
		// set up browse path button
		JButton browsePathBtn = new JButton();
		browsePathBtn.setText("Browse");
		browsePathBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
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
		            	searchPathFld.setText("No directory selected");
		            }

				} catch (Exception e) {
					resultLbl.setText("Error!");
				}
			}
		});
		
		// set up search button
		JButton searchBtn = new JButton();
		searchBtn.setText("Search");
		searchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					searchText = searchTextFld.getText();
					
					Local_Operation lo = new Local_Operation();
					
					List<String> resultList = lo.findTextFromFile(searchPath, searchText);
					String resultTxt = "";
					for(int i=0; i<resultList.size(); i++) {
						resultTxt = resultTxt + " " + resultList.get(i);
					}
					resultLbl.setText(resultTxt);
					
				} catch (Exception e) {
					resultLbl.setText("Error!");
				}
			}
		});
		
		
		
		
		

		GridBagConstraints gbc = new GridBagConstraints();
		
		
		
		// ROW 0
		gbc.fill = GridBagConstraints.CENTER;
		gbc.weightx = 1;   // width of object
		gbc.weighty = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(new JLabel("Find Directory"), gbc);
		
		
		// ROW 1
		gbc.ipady = 40;      // make this row tall
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;   // width of object
		gbc.weighty = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(browsePathBtn, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.weightx = 1;
	    gbc.weighty = 1;
	    gbc.gridx = 1;
	    gbc.gridy = 1;
		//gbc.gridwidth = 6;
		panel.add(searchPathFld, gbc);

		
		
		// ROW 2
		gbc.ipady = 10;      //make this row back to normal
		gbc.fill = GridBagConstraints.CENTER;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 0;
		gbc.gridy = 2;
		panel.add(new JLabel("Search Text"), gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		//gbc.weightx = 0.5;
		//gbc.gridwidth = 5;   // Can change the location of next row
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 1;
		gbc.gridy = 2;
		panel.add(searchTextFld, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 2;
		gbc.gridy = 2;
		panel.add(searchBtn, gbc);
		
		
		// ROW 3
		gbc.fill = GridBagConstraints.CENTER;
		// gbc.gridwidth = 3;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 1;
		gbc.gridy = 3;
		panel.add(resultLbl, gbc);
		
		
		// add panel to frame and make it visible
		frame.add(panel);
		frame.setVisible(true);
	    
	}
	
}
