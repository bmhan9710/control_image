import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.filechooser.FileSystemView;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.im4java.core.Stream2BufferedImage;
import org.im4java.process.Pipe;

public class UI_MENU_003 {
	
	String searchPath="";
	//String searchText="";
	static String IMAGE_MAGICK_PATH;
	
	public static byte[] convertHEICtoJPEG(byte[] heicBytes) throws IOException, InterruptedException, IM4JavaException {
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
	    convert.setSearchPath(IMAGE_MAGICK_PATH);
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
	
	public JPanel panel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setBounds(10, 10, 10, 10);
		panel.setComponentOrientation(ComponentOrientation.UNKNOWN);
		
		// set up labels
		JLabel directoryLbl = new JLabel();
		directoryLbl.setText("Replace Text from Files");
		
		JLabel originalStrLbl = new JLabel();
		originalStrLbl.setText("Original Text");
		
		JLabel replaceStrLbl = new JLabel();
		replaceStrLbl.setText("Replace Text");
		
		
		
		JLabel resultLbl = new JLabel();
		resultLbl.setText("Result");
		JLabel resultVarLbl = new JLabel();
		resultVarLbl.setText("No Input");
		
		// set up path search text fields
		JTextField searchPathFld= new JTextField();
		JTextField searchTextFld = new JTextField();

		// set up original, replace search text fields
		JTextField originalStrFld= new JTextField();
		JTextField replaceStrFld= new JTextField();
		
		
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
		
		// set up replace button
		JButton replaceBtn = new JButton();
		replaceBtn.setText("Replace");
		replaceBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				String resultStr = "";
				String originalStr = "";
				String replaceStr = "";
				
				try {
					originalStr= originalStrFld.getText();
					replaceStr = replaceStrFld.getText();
					
					Operation lo = new Operation();
					lo.fileRename_replace(searchPath, originalStr, replaceStr);
					
				} catch (Exception e) {
					resultVarLbl.setText("Error!");
				} finally {
					if("".equals(resultStr)) {
						resultStr = "Nothing found";
					}
					resultVarLbl.setText("Complete");
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
		panel.add(originalStrLbl, gbc);
		
		// ROW 2
		//gbc.ipady = 10;      //make this row back to normal
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 1;
		gbc.gridy = 2;
		originalStrFld.setBorder(edge);
		panel.add(originalStrFld, gbc);
		
		// ROW 2
		gbc.ipady = 10;      //make this row back to normal
		gbc.fill = GridBagConstraints.CENTER;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 2;
		gbc.gridy = 2;
		//replaceStrLbl.setBorder(edge);
		panel.add(replaceStrLbl, gbc);

		// ROW 2
		//gbc.ipady = 10;      //make this row back to normal
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 3;
		gbc.gridy = 2;
		replaceStrFld.setBorder(edge);
		panel.add(replaceStrFld, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 4;
		gbc.gridy = 2;
		replaceBtn.setBorder(edge);
		panel.add(replaceBtn, gbc);
		
		
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
		
		return panel;
	}
	
}
