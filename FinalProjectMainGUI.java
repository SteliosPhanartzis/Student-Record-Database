import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class FinalProjectMainGUI extends FrameBase {

	//Class Variables
	private JTable displayTable;
	
	private DefaultTableModel defTableModel;
	private JTextField searchTextField;
	private StudentPopUp studentDialog;
	protected int count;
	private JPanel PanelTop, PanelTable, PanelBottom;
	private JComboBox searchList;
	private JButton Add_Button, Delete_Button, Export_Button;
	public String[] columns = new String []{"Row ID", "First Name", "Last Name", "CUNY ID", "GPA", "Venus Login"};
	public FinalProjectMainGUI()
	{
		//Organization of Contents
		PanelTable = new JPanel();
		PanelTable.setSize(300, 300);
		PanelTop = new JPanel(new GridBagLayout());
		PanelTop.setSize(600,80);
		PanelTop.setLocation(0,0);
		PanelBottom = new JPanel();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(4,4,4,4);
		
		JLabel lblSearch = new JLabel("Search By:");
		PanelTop.add(lblSearch,gbc);
		searchList = new JComboBox();
		searchList.setActionCommand("Search");
		for(int i = 0; i< columns.length;i++)
			searchList.addItem(columns[i]);
		searchList.getSelectedIndex();
		PanelTop.add(searchList,gbc);
		
		searchTextField = new JTextField(10);
		searchTextField.setActionCommand("Search");
		searchTextField.addActionListener(this);
		searchTextField.addKeyListener(this);
		PanelTop.add(searchTextField,gbc);
		JButton btnAdd = new JButton("Add");
		btnAdd.setActionCommand("Add");
		btnAdd.addActionListener(this);
		
		PanelTop.add(btnAdd,gbc);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setActionCommand("Delete");
		btnDelete.addActionListener(this);
		
		PanelTop.add(btnDelete,gbc);
		
		
		displayTable = new JTable();
		//displayTable.setEnabled(false);
		JScrollPane jspData = new JScrollPane(displayTable);
		PanelTable.add(jspData);
		
		displayTable.setModel(new DefaultTableModel() 
		{
			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
			
            @Override
            public Class getColumnClass(int column) 
            {
                switch (column) 
                {
                    case 0:
                        	return Integer.class; 
                    case 1:
                    		return String.class; 
                    case 2:
                    		return String.class;
                    case 3:
                			return Integer.class;
                    case 4:
                			return Integer.class;
                    case 5:
                			return String.class;

                    default:
                    		return String.class;
                }
            }
        });
		
		
		defTableModel = (DefaultTableModel)displayTable.getModel();
		for(int i = 0; i < columns.length;i++)
			defTableModel.addColumn(columns[i]);

		displayTable.setAutoCreateRowSorter(true);
		displayTable.addMouseListener(this);
		JButton btnExport = new JButton("Export Data");
		btnExport.setActionCommand("Export Data");
		btnExport.addActionListener(this);
		
		PanelBottom.add(btnExport);
		
		jfrm.add(PanelTop,BorderLayout.NORTH);
		jfrm.add(PanelTable,BorderLayout.CENTER);
		jfrm.add(PanelBottom,BorderLayout.SOUTH);
		
		this.jfrm.setVisible(true);
	}
	private int GetCurrentComboSearchIndex() { return this.searchList.getSelectedIndex(); }
	private String GetCurrentTextSearch() { return this.searchTextField.getText();}
	private void FilterRowBasedOnSearch()
	{
		RowFilter<Object,Object> rowFilter = new RowFilter<Object,Object>()
		{
			@Override
			public boolean include(javax.swing.RowFilter.Entry<? extends Object, ? extends Object> entry) {
				boolean shouldInclude = false;
				
				switch(GetCurrentComboSearchIndex())
			     {
			     	case 0: //Search column 0
			     		if(FrameBase.CheckInteger(GetCurrentTextSearch()) 
			     				&& Integer.parseInt(entry.getStringValue(GetCurrentComboSearchIndex()).trim()) 
			     				== Integer.parseInt(GetCurrentTextSearch()))
			     		{
			     			shouldInclude = true;
			     		}
			     	break;
			     	case 1: //search column 1
			     		if(
			     				entry.getStringValue( GetCurrentComboSearchIndex() ).toLowerCase().indexOf
			     				(
			     					GetCurrentTextSearch().toLowerCase()
			     				) >= 0
			     		)
			     		{
			     			shouldInclude = true;
			     		}
			     	break;
			     	case 2: //search column 2
			     		if(
			     				entry.getStringValue( GetCurrentComboSearchIndex() ).toLowerCase().indexOf
			     				(
			     					GetCurrentTextSearch().toLowerCase()
			     				) >= 0
			     		)
			     		{
			     			shouldInclude = true;
			     		}
			     	break;
			     	case 3: //Search column 3
			     		if(FrameBase.CheckInteger(GetCurrentTextSearch()) 
			     				&& Integer.parseInt(entry.getStringValue(GetCurrentComboSearchIndex()).trim()) 
			     				== Integer.parseInt(GetCurrentTextSearch()))
			     		{
			     			shouldInclude = true;
			     		}
			     	break;
			     	case 4: //Search column 3
			     		if(FrameBase.CheckDouble(GetCurrentTextSearch()) 
			     				&& Double.parseDouble(entry.getStringValue(GetCurrentComboSearchIndex()).trim()) 
			     				>= Double.parseDouble(GetCurrentTextSearch()))
			     		{
			     			shouldInclude = true;
			     		}
			     	break;
			     	case 5: //search column 5
			     		if(
			     				entry.getStringValue( GetCurrentComboSearchIndex() ).toLowerCase().indexOf
			     				(
			     					GetCurrentTextSearch().toLowerCase()
			     				) >= 0
			     		)
			     		{
			     			shouldInclude = true;
			     		}
			     	break;
			     }
				
				
				return shouldInclude;
			}
		};
		TableRowSorter<DefaultTableModel> sorter = (TableRowSorter<DefaultTableModel>) displayTable.getRowSorter();
		sorter.setRowFilter(rowFilter);
	}
	//Check Table for Entry
	public boolean queryEntry(JTable tbl, Object[] entry) {
	    int rowCount = tbl.getRowCount();
	    int colCount = tbl.getColumnCount();
	    
	    String curEntry = "";
	    for (Object o : entry) {
	        String e = o.toString();
	        curEntry = curEntry + " " + e;
	    }
	    
	    for (int i = 0; i < rowCount; i++) {
	        String rowEntry = "";
	        for (int j = 0; j < colCount; j++)
	            rowEntry = rowEntry + " " + tbl.getValueAt(i, j).toString();
	        if (rowEntry.equalsIgnoreCase(curEntry))
	            return true;
	    }
	    return false;
	}
	//Check entry in Col
	public boolean queryColEntry(JTable tbl, Object entry, int colIndex) {
	    int rowCount = tbl.getRowCount();
	    
	    String curEntry = "";
//	    for (Object o : entry) {
	        String e = entry.toString();
	        curEntry = curEntry + " " + e;
	    //}
	    
	    for (int i = 0; i < rowCount; i++) {
	        String rowEntry = "";
	        rowEntry = rowEntry + " " + tbl.getValueAt(i, colIndex).toString();
	        if (rowEntry.equalsIgnoreCase(curEntry))
	            return true;
	    }
	    return false;
	}
	@Override
	protected void HandleAboutEvent()
	{
		JOptionPane.showMessageDialog(jfrm, "Student Data Management Applet.\nCreated by Stelios Phanartzis.");
	}
	@Override
	protected void HandleFileOpenEvent() 
	{
		FileDialog fd = new FileDialog(jfrm);
		fd.setVisible(true);
		String InputFile = fd.getFile();
        if (InputFile != null) {
        File selectedFile = new File(fd.getDirectory()+InputFile);
        try
        {
        	Scanner fsc = new Scanner(selectedFile).useDelimiter(", |,|\\n");
        	while(fsc.hasNextLine())
        	{
        		String FName = fsc.next().trim();
        		String LName = fsc.next().trim();
        		int CID = fsc.nextInt();
        		if(queryColEntry(displayTable, CID, 3))
        			continue;
        		Double Grade = fsc.nextDouble();
        		String Login = fsc.next().trim();
        		defTableModel.addRow(new Object[]{defTableModel.getRowCount()+1,FName,LName,CID,Grade,Login});
        	}
        	fsc.close();
        }
        catch (IOException e){
        	System.out.println("Could not open file.");
        	e.printStackTrace();
        }
        System.out.println(selectedFile.getName());
         
        }
	}
	protected void HandleFileExportEvent(JTable tbl)
	{
		
		FileDialog fd = new FileDialog(jfrm,"Save Info",FileDialog.SAVE);
		fd.setVisible(true);
		String InputFile = fd.getFile();
		TableModel model = tbl.getModel();
        if (InputFile != null) {
        	File selectedFile = new File(fd.getDirectory()+InputFile);
        	try
        	{
        		FileWriter fw = new FileWriter(selectedFile);
        		for (int i = 0; i < model.getRowCount();i++) {
        			for (int j = 1; j < model.getColumnCount(); j++) {
        				fw.write(model.getValueAt(i,j) + "");
        				if(j < model.getColumnCount()-1)
        					fw.write(",");
        			}
        			fw.write("\n");
        		}
        		fw.close();
        		JOptionPane.showMessageDialog(null, "File Exported to "+fd.getDirectory()+InputFile);
        	}
        	catch(IOException e){
        		System.out.println("Could not export information.");
        		e.printStackTrace();
        	}
        }
	}
	protected void HandleAddPersonEvent()
	{
		studentDialog = new StudentPopUp(this.displayTable,count);
		studentDialog.setVisible(true);
	}
	protected void HandleDeletePersonEvent()
	{
		if(displayTable.getSelectedRow() >= 0 )
		{
			StringBuilder sb = new StringBuilder();
			int rowID = displayTable.convertRowIndexToModel(displayTable.getSelectedRow());
			defTableModel.getColumnCount();
			for(int i = 0; i < defTableModel.getColumnCount(); i++)
			{
				
				sb.append("\r\n" + defTableModel.getColumnName(i) + " : " +  defTableModel.getValueAt(rowID, i) + " ");
			}
			int result = JOptionPane.showConfirmDialog(null, 
					"Are you sure you want to delete this person's data?" + sb.toString()
					);
			if(result == JOptionPane.OK_OPTION)
			{
			//defTableModel.removeRow(displayTable.getSelectedRow());
				defTableModel.removeRow(displayTable.convertRowIndexToModel(displayTable.getSelectedRow()));
			}

		}
	}
	protected void HandleModifyPersonEvent()
	{
		
		studentDialog = new StudentPopUp(this.displayTable);
		//JOptionPane.showMessageDialog(this.jfrm, "My New Button was clicked!");
		if(displayTable.getSelectedRow() >= 0)
		{
			studentDialog.PopulateDataFromSelectedRowInTable();
			studentDialog.setVisible(true);
		}
		else
		{
			JOptionPane.showMessageDialog(this.jfrm, "No Row Selected",
					"No Row Selected",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		switch(e.getActionCommand())
		{
			case "Search":
				System.out.println("Search for " 
						+ this.searchList.getSelectedItem() 
						+ " using " + this.searchTextField.getText());
				FilterRowBasedOnSearch();
			break;
			case "Delete" :
				HandleDeletePersonEvent();
			break;
			case "Add" :
				HandleAddPersonEvent();
			break;
			case "Modify":
				HandleModifyPersonEvent();
			break;
			case "Open":
				HandleFileOpenEvent();
			break;
			case "Export Data":
				HandleFileExportEvent(displayTable);
			break;
			case "Quit":
				System.exit(0);
			break;
			case "About":
				HandleAboutEvent();
			break;
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		//this will capture double click on JTable!
		if(e.getClickCount() == 2)
		{
			HandleModifyPersonEvent();
			
		}
		
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println(e.getKeyChar());
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyChar());
		
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println(e.getKeyChar());
		
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}