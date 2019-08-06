import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StudentPopUp extends JDialog implements ActionListener
{
	public final static int ADD_MODE = 0;
	public final static int MODIFY_MODE = 1;
	public final static int DELETE_MODE = 2;
	protected int count = 0;
	private JTable myReferencedTable;
	JTextField txtRow = new JTextField(15);
	JTextField txtFName = new JTextField(15);
	JTextField txtLName = new JTextField(15);
	JTextField txtCUNYID = new JTextField(15);
	JTextField txtGPA = new JTextField(15);
	JTextField txtVLOGIN = new JTextField(15);
	public StudentPopUp(JTable newTable)
	{
		this.myReferencedTable = newTable;
		this.setSize(350,250);
		this.setLayout(new FlowLayout());
		this.setLocationRelativeTo(null);
		this.setTitle("Modify A Student Record");
		
		JLabel lblRow = new JLabel("Row: ");
		JLabel lblFName = new JLabel("First Name: ");
		JLabel lblLName = new JLabel("Last Name: ");
		JLabel lblCUNYID = new JLabel("CUNY ID: ");
		JLabel lblGPA = new JLabel("GPA: ");
		JLabel lblVLOGIN = new JLabel("Venus Login: ");
		lblRow.setPreferredSize(new Dimension(100,11));
		lblFName.setPreferredSize(new Dimension(100,11));
		lblLName.setPreferredSize(new Dimension(100,11));
		lblCUNYID.setPreferredSize(new Dimension(100,11));
		lblGPA.setPreferredSize(new Dimension(100,11));
		lblVLOGIN.setPreferredSize(new Dimension(100,11));
		txtVLOGIN.setEditable(false);
		txtRow.setEditable(false);
		JButton btnOperation = new JButton("Modify");
		btnOperation.setActionCommand("Operation");
		btnOperation.addActionListener(this);
		JButton btnCancel = new JButton ("Cancel");
		btnCancel.addActionListener(this);
		this.add(lblRow);
		this.add(txtRow);
		this.add(lblFName);
		this.add(txtFName);
		this.add(lblLName);
		this.add(txtLName);
		this.add(lblCUNYID);
		this.add(txtCUNYID);
		this.add(lblGPA);
		this.add(txtGPA);
		this.add(lblVLOGIN);
		this.add(txtVLOGIN);
		this.add(btnOperation);
		this.add(btnCancel);
	
		
		this.setModal(true); // This is how you setup a Modal Dialog 
		this.getRootPane().setDefaultButton(btnOperation); //Setting default button for JDialog
		//this.setVisible(true);
	}
	public StudentPopUp(JTable newTable, int add)
	{
		this.count = add;
		this.myReferencedTable = newTable;
		this.setSize(350,200);
		this.setLayout(new FlowLayout());
		this.setLocationRelativeTo(null);
		this.setTitle("Add A New Student Record");
		
		//JLabel lblRow = new JLabel("Row: ");
		JLabel lblFName = new JLabel("First Name: ");
		JLabel lblLName = new JLabel("Last Name: ");
		JLabel lblCUNYID = new JLabel("CUNY ID: ");
		JLabel lblGPA = new JLabel("GPA: ");
		JLabel lblVLOGIN = new JLabel("Venus Login: ");
		//lblRow.setPreferredSize(new Dimension(100,11));
		lblFName.setPreferredSize(new Dimension(100,11));
		lblLName.setPreferredSize(new Dimension(100,11));
		lblCUNYID.setPreferredSize(new Dimension(100,11));
		lblGPA.setPreferredSize(new Dimension(100,11));
		lblVLOGIN.setPreferredSize(new Dimension(100,11));
		txtVLOGIN.setEditable(false);
		JButton btnOperation = new JButton("Add");
		btnOperation.setActionCommand("Add");
		btnOperation.addActionListener(this);
		JButton btnCancel = new JButton ("Cancel");
		btnCancel.addActionListener(this);
		this.add(lblFName);
		this.add(txtFName);
		this.add(lblLName);
		this.add(txtLName);
		this.add(lblCUNYID);
		this.add(txtCUNYID);
		this.add(lblGPA);
		this.add(txtGPA);
		this.add(lblVLOGIN);
		this.add(txtVLOGIN);
		this.add(btnOperation);
		this.add(btnCancel);
	
		
		this.setModal(true); // This is how you setup a Modal Dialog 
		this.getRootPane().setDefaultButton(btnOperation); //Setting default button for JDialog
		//this.setVisible(true);
	}
	
	public void PopulateDataFromSelectedRowInTable()
	{
		//int rowIndex = this.myReferencedTable.convertRowIndexToModel(this.myReferencedTable.getSelectedRow());
		int currentRowInGUI = this.myReferencedTable.getSelectedRow();
		if(currentRowInGUI >= 0)
		{
			
			this.txtRow.setText(this.myReferencedTable.getValueAt
					(currentRowInGUI, 0).toString());
			this.txtFName.setText(this.myReferencedTable.getValueAt
					(currentRowInGUI, 1).toString());
			this.txtLName.setText(this.myReferencedTable.getValueAt
					(currentRowInGUI, 2).toString());
			this.txtCUNYID.setText(this.myReferencedTable.getValueAt
					(currentRowInGUI, 3).toString());
			this.txtGPA.setText(this.myReferencedTable.getValueAt
					(currentRowInGUI, 4).toString());
			this.txtVLOGIN.setText(this.myReferencedTable.getValueAt
					(currentRowInGUI, 5).toString());
			
		}
		else
		{
			System.out.println("No row selected in JTable");
		}
	}
	
	public boolean Validate(){
		boolean valid = false;
		if(txtFName.getText().length()<2)
		{
			JOptionPane.showMessageDialog(null, "First Name needs to have atleast 2 characters.");
		}
		else if(txtLName.getText().length()<2)
		{
			JOptionPane.showMessageDialog(null, "Last Name needs to have atleast 2 characters.");
		}
		else if(Integer.parseInt(txtCUNYID.getText())<10000000||
				Integer.parseInt(txtCUNYID.getText())>99999999)
		{
			JOptionPane.showMessageDialog(null, "CUNYID needs to be an 8 digit integer.");
		}
		else if(
				Double.parseDouble(txtGPA.getText())<0.0||
				Double.parseDouble(txtGPA.getText())>4.0)
		{
			JOptionPane.showMessageDialog(null, "GPA is not valid");
		}
		else
		{
			valid = true;
			return valid;
		}
		return valid;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		switch(e.getActionCommand())
		{
		case "Operation":
			int currentRowInGUI = this.myReferencedTable.getSelectedRow();
			System.out.println("Pressed modify in JPopUp Dialog");
			StringBuilder vlogin = new StringBuilder();
			String fname,lname;
			int cunyid;
			double gpa;			
			int result = JOptionPane.showConfirmDialog(null,"Are you sure you want to modify this student's data?");
			if(result == JOptionPane.OK_CANCEL_OPTION)
			{	
				if(currentRowInGUI >= 0)
				{
					if(Validate())
					{
						fname = txtFName.getText();
						lname = txtLName.getText();
						cunyid = Integer.parseInt(txtCUNYID.getText());
						gpa = Double.parseDouble(txtGPA.getText());
						vlogin.append(lname, 0, 2).append(fname,0,2).append(txtCUNYID.getText(),4,8);
						this.myReferencedTable.setValueAt(fname, currentRowInGUI, 1);
						this.myReferencedTable.setValueAt(lname, currentRowInGUI, 2);
						this.myReferencedTable.setValueAt(cunyid, currentRowInGUI, 3);
						this.myReferencedTable.setValueAt(gpa, currentRowInGUI, 4);
						this.myReferencedTable.setValueAt(vlogin, currentRowInGUI, 5);
					}
				}
				
			}
			this.setVisible(false);
			break;
		case "Add":
			StringBuilder vlogin1 = new StringBuilder();
			String fname1,lname1;
			int cunyid1;
			double gpa1;
			int result1 = JOptionPane.showConfirmDialog(null,"Are you sure you want to add this student's data?");
			if(result1 == JOptionPane.OK_OPTION)
			{
				
				if(Validate())
				{
					fname1 = txtFName.getText();
					lname1 = txtLName.getText();
					cunyid1 = Integer.parseInt(txtCUNYID.getText());
					gpa1 = Double.parseDouble(txtGPA.getText());
					vlogin1.append(lname1, 0, 2).append(fname1,0,2).append(txtCUNYID.getText(),4,8);
					DefaultTableModel tblModel = (DefaultTableModel)this.myReferencedTable.getModel();
					tblModel.addRow(new Object[]{tblModel.getRowCount()+1,fname1,lname1,cunyid1,gpa1,vlogin1});
				}
			}
			this.setVisible(false);
			break;
		case "Cancel":
			this.setVisible(false);
			break;
		}
		
	}
}
