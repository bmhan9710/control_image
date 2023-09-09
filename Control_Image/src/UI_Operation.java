import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.filechooser.FileSystemView;

public class UI_Operation {
	
	String searchPath="";
	String searchText="";

	UI_Operation() {
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
		panel.setBounds(10, 10, 10, 10);
		
		// set up labels
		JLabel directoryLbl = new JLabel();
		directoryLbl.setText("Find Directory");
		JLabel searchTxtLbl = new JLabel();
		searchTxtLbl.setText("Search Text");
		JLabel resultLbl = new JLabel();
		resultLbl.setText("Result");
		JLabel resultVarLbl = new JLabel();
		resultVarLbl.setText("No Input");
		
		
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
					resultVarLbl.setText("Error!");
				}
			}
		});
		
		// set up search button
		JButton searchBtn = new JButton();
		searchBtn.setText("Search");
		searchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				String resultTxt = "";
				
				try {
					searchText = searchTextFld.getText();
					Local_Operation lo = new Local_Operation();
					List<String> resultList = lo.findTextFromFile(searchPath, searchText);
					
					for(int i=0; i<resultList.size(); i++) {
						resultTxt = resultTxt + " " + resultList.get(i) + "<br>";
					}
				} catch (Exception e) {
					resultVarLbl.setText("Error!");
				} finally {
					if("".equals(resultTxt)) {
						resultTxt = "Nothing found";
					}
					resultVarLbl.setText("<html>" + resultTxt + "</html>");
				}
			}
		});
		
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		Border edge = BorderFactory.createRaisedBevelBorder();
		
		// ROW 0
		gbc.fill = GridBagConstraints.CENTER;
		gbc.weightx = 1;   // width of object
		gbc.weighty = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		//directoryLbl.setBorder(edge);
		panel.add(directoryLbl, gbc);
		
		
		// ROW 1
		gbc.ipady = 40;      // make this row tall
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;   // width of object
		gbc.weighty = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		browsePathBtn.setPreferredSize(new Dimension(50, 20));
		browsePathBtn.setBorder(edge);
		panel.add(browsePathBtn, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.weightx = 1;
	    gbc.weighty = 1;
	    gbc.gridx = 1;
	    gbc.gridy = 1;
		//gbc.gridwidth = 6;
		panel.add(searchPathFld, gbc);
		searchPathFld.setBorder(edge);
		
		
		// ROW 2
		gbc.ipady = 10;      //make this row back to normal
		gbc.fill = GridBagConstraints.CENTER;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 0;
		gbc.gridy = 2;
		//searchTxtLbl.setBorder(edge);
		panel.add(searchTxtLbl, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		//gbc.weightx = 0.5;
		//gbc.gridwidth = 5;   // Can change the location of next row
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 1;
		gbc.gridy = 2;
		searchTextFld.setBorder(edge);
		panel.add(searchTextFld, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 2;
		gbc.gridy = 2;
		searchBtn.setBorder(edge);
		panel.add(searchBtn, gbc);
		
		
		// ROW 3
		gbc.ipady = 10;      //make this row back to normal
		gbc.fill = GridBagConstraints.CENTER;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 0;
		gbc.gridy = 3;
		//resultLbl.setBorder(edge);
		panel.add(resultLbl, gbc);
		
		gbc.fill = GridBagConstraints.CENTER;
		// gbc.gridwidth = 3;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 1;
		gbc.gridy = 3;
		//resultVarLbl.setBorder(edge);
		panel.add(resultVarLbl, gbc);
		
		
		// add panel to frame and make it visible
		frame.add(panel);
		frame.setVisible(true);
	}
	
}
