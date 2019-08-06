
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

public abstract class FrameBase implements ActionListener, KeyListener, MouseListener{
	protected JFrame jfrm;
	protected FileDialog fd;
	public FrameBase()
	{
		//Setup of JFrame
		jfrm = new JFrame();
		jfrm.setSize(550, 600);
		jfrm.setLocationRelativeTo(null);
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jfrm.setTitle("Student Roster Management");	
		JMenuBar MenuBar = new JMenuBar();
	    JMenu jmFile = new JMenu("File");
		//Setup menus
	    JMenuItem jmiOpen = new JMenuItem("Open", 79);
	    jmiOpen.setAccelerator(KeyStroke.getKeyStroke(79, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
	    
	    JMenuItem jmiExport = new JMenuItem("Export Data", 69);
	    jmiExport.setAccelerator(KeyStroke.getKeyStroke(69, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
	    
	    JMenuItem jmiQuit = new JMenuItem("Quit", 81);
	    jmiQuit.setAccelerator(KeyStroke.getKeyStroke(81, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
	    
	    jmiOpen.addActionListener(this);
	    jmiExport.addActionListener(this);
	    jmiQuit.addActionListener(this);
	    jmFile.add(jmiOpen);
	    jmFile.addSeparator();
	    jmFile.add(jmiExport);
	    jmFile.addSeparator();
	    jmFile.add(jmiQuit);
	    MenuBar.add(jmFile);
	    
		JMenu jmHelp = new JMenu("Help");
		jmHelp.setMnemonic(KeyEvent.VK_H);
		JMenuItem jmiAbout = new JMenuItem("About");
		jmiAbout.setMnemonic(KeyEvent.VK_A);
		jmiAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, 
				Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()
				));
	    jmHelp.setMnemonic(72);
	    jmiAbout.setMnemonic(65);
	    jmiAbout.setAccelerator(KeyStroke.getKeyStroke(65, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
	    jmiAbout.addActionListener(this);
	    jmHelp.add(jmiAbout);
	    MenuBar.add(jmHelp);
	    this.jfrm.setJMenuBar(MenuBar);
	
		FileDialog fd = new FileDialog(jfrm,"Select a File",FileDialog.LOAD);

		fd.setVisible(false);
		
		
		jfrm.setVisible(true);
	}
	

	protected void HandleAboutEvent()
	{
		JOptionPane.showMessageDialog(this.jfrm, "");
	}
	
	public static boolean CheckInteger(String value)
	{
		boolean isValidInteger = false;
		try
		{
			Integer.parseInt(value);
			isValidInteger = true;
		}
		catch(Exception ex)
		{
		}
		return isValidInteger;
	}
	
	public static boolean CheckDouble(String value)
	{
		boolean isValidDouble = false;
		try
		{
			Double.parseDouble(value);
			isValidDouble = true;
		}
		catch(Exception ex)
		{
		}
		return isValidDouble;
	}
	
	
	
	abstract protected void HandleFileOpenEvent();
	
	abstract public void keyTyped(KeyEvent e); 
	
	abstract public void keyPressed(KeyEvent e);
	abstract public void keyReleased(KeyEvent e);

	abstract public void mouseReleased(MouseEvent e); 
	
	abstract public void mouseEntered(MouseEvent e);

	abstract public void mouseExited(MouseEvent e);

	abstract public void actionPerformed(ActionEvent e);
	
}