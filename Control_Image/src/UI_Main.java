import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class UI_Main {
	
	String searchPath="";
	String searchText="";

	
	
	// used by createMenuBar
	public JMenu createMenu(String title) {
		JMenu m = new HorizontalMenu(title);
		//m.add("Menu item #1 in " + title);
		//m.add("Menu item #2 in " + title);
		//m.add("Menu item #3 in " + title);
		
		/*
		JMenu submenu = new HorizontalMenu("Submenu");
		submenu.add("Submenu item #1");
		submenu.add("Submenu item #2");
		m.add(submenu);
		*/
		return m;
	}
	
	class HorizontalMenu extends JMenu {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		HorizontalMenu(String label) {
	      super(label);
	      JPopupMenu pm = getPopupMenu();
	      pm.setLayout(new BoxLayout(pm, BoxLayout.LINE_AXIS));
	    }

	    public Dimension getMinimumSize() {
	      return getPreferredSize();
	    }

	    public Dimension getMaximumSize() {
	      return getPreferredSize();
	    }

	    public void setPopupMenuVisible(boolean b) {
	      boolean isVisible = isPopupMenuVisible();
	      if (b != isVisible) {
	        if ((b == true) && isShowing()) {
	          // Set location of popupMenu (pulldown or pullright).
	          // Perhaps this should be dictated by L&F.
	          int x = 0;
	          int y = 0;
	          Container parent = getParent();
	          if (parent instanceof JPopupMenu) {
	            x = 0;
	            y = getHeight();
	          } else {
	            x = getWidth();
	            y = 0;
	          }
	          getPopupMenu().show(this, x, y);
	        } else {
	          getPopupMenu().setVisible(false);
	        }
	      }
	    }
	  }
	
	UI_Main() {
		// Create and set up the window.
		JFrame frame = new JFrame();
		frame.setSize(800, 500);
		frame.setTitle("Text Search Tool");
		frame.setLocationByPlatform(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// set up panel
		JPanel tabPanel = new JPanel();
		tabPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		//panel.setLayout(new GridBagLayout());
		
		
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.Y_AXIS));
		JMenu menu1 =createMenu("Menu 1"); 
		menuBar.add(menu1);
		JMenu menu2 =createMenu("Menu 2"); 
		menuBar.add(menu2);
		JMenu menu3 =createMenu("Menu 3"); 
		menuBar.add(menu3);
		
		UI_MENU_001 menu001 = new UI_MENU_001();
		UI_MENU_002 menu002 = new UI_MENU_002();
		UI_MENU_003 menu003 = new UI_MENU_003();
		JPanel menu001_panel = menu001.panel();
		JPanel menu002_panel = menu002.panel();
		JPanel menu003_panel = menu003.panel();
		
		menu1.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
			    // action here
				System.out.println("Menu1 clicked");
				frame.setVisible(false);
				frame.remove(menu002_panel);
				frame.remove(menu003_panel);

				frame.add(menu001_panel);
				frame.setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mousePressed(MouseEvent arg0) {}
			@Override
			public void mouseReleased(MouseEvent arg0) {}
		});
		
		menu2.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
			    // action here
				System.out.println("Menu2 clicked");
				frame.setVisible(false);
				frame.remove(menu001_panel);
				frame.remove(menu003_panel);

				frame.add(menu002_panel);
				frame.setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mousePressed(MouseEvent arg0) {}
			@Override
			public void mouseReleased(MouseEvent arg0) {}
		});
		
		menu3.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
			    // action here
				System.out.println("Menu3 clicked");
				frame.setVisible(false);
				frame.remove(menu001_panel);
				frame.remove(menu002_panel);
				
				frame.add(menu003_panel);
				frame.setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mousePressed(MouseEvent arg0) {}
			@Override
			public void mouseReleased(MouseEvent arg0) {}
		});
		
		// Set border layout of menu buttons
		menuBar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		

		tabPanel.setBackground(Color.WHITE); // contrasting bg
		//tabPanel.setBounds(10, 10, 10, 10);
		
		tabPanel.add(menuBar, BorderLayout.LINE_START);
		
		//tabPanel.setLayout(new FlowLayout(1, 1, 1));
		
		
		// add panel to frame and make it visible
		//frame.add(panel);
		frame.add(tabPanel, BorderLayout.WEST);
		frame.add(menu001_panel, BorderLayout.CENTER);
		
		//frame.add(panel2);
		frame.setVisible(true);
	}
	
}
