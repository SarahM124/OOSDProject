package entities;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomerFrame

{
	// variables 
	private static JTextField txtaddress;
	private static JTextField txtphone;
	private static JTextField txtname;
	 Connection con;
     PreparedStatement ps;
      private static JTextField txtid;
	
	
	
	
	public CustomerFrame()
	  {
		  
		  showFrame();
		  
	  }
	
	
			  
			  
		  
			 
			 
			 
	
	
  public static void main(String[] args) 
  {
	  javax.swing.SwingUtilities.invokeLater(new Runnable()  {
		  public void run() { 
			  showFrame();
		  }
	  });
	  
  }

  
  
  
  // frame method 
  static void showFrame() 
  {
		
	  // set up frame and its size
	JFrame frame = new JFrame("Customer Screen");
  	frame.getContentPane().setLayout(null);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int width = (int) screenSize.getWidth();
    int height = (int) screenSize.getHeight();

   frame.setSize(1200, 600);
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   frame.setVisible(true);
	      
   JPanel panel = new JPanel();
   panel.setLayout(null);
   panel.setBounds((width -1200)/2, (height - 800)/2, 1200, 800);


	
		// labels for buttons
		JLabel lblNewLabel = new JLabel("Customer ID");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel.setBounds(6, 62, 129, 36);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(6, 149, 89, 31);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("Phone Number"); 
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(6, 252, 129, 31);
		panel.add(lblNewLabel_3);
		
		
		JLabel lblNewLabel_2 = new JLabel("Address");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(6, 207, 97, 22);
		panel.add(lblNewLabel_2);
		
		// textfields 

		txtaddress = new JTextField(); // description textfield
		txtaddress.setBounds(152, 198, 190, 42);
		panel.add(txtaddress);
		txtaddress.setColumns(10);
		
		txtphone = new JTextField(); // quantity textfield
		txtphone.setBounds(152, 247, 190, 42);
		panel.add(txtphone);
		txtphone.setColumns(10);
		

		txtname = new JTextField(); // make textfield
		txtname.setBounds(152, 144, 190, 42);
		panel.add(txtname);
		txtname.setColumns(10);
		
		
	 
	  
    try 
  {
		
    	// log in to database 
		String url = "jdbc:mysql://localhost:8889/ElectricStore";
	      String user = "root";
	      String password = "root";
	    
	      Connection connect = DriverManager.getConnection(url, user, password);
	    
	      String query = "SELECT * FROM Customer";
	    
	      Statement stm = connect.createStatement();
	      ResultSet res = stm.executeQuery(query);
	      Connection con;
	      PreparedStatement ps;
	      
	      // populates table with data from the product database
	      String columns[] = { "Customer ID", "Name", "Address", "Phone Number"};
	      String data[][] = new String[8][5];
	    
	      int i = 0;
	      while (res.next()) {
	        int id = res.getInt("CustomerID");
	        String name = res.getString("Name");
	        String address = res.getString("Address");
	        int phoneNo = res.getInt("PhoneNo");
	        data[i][0] = id + "";
	        data[i][1] = name;
	        data[i][2] = address;
	        data[i][3] = phoneNo + "";
	        
	        i++;
	      }
	    
	      DefaultTableModel model = new DefaultTableModel(data, columns);
	      JTable table = new JTable(model) {
	    	  public boolean isCellEditable(int row, int col) { // does not allow table cells to be editable
	    		  return false;
	    	  }
	      };
	      
	      
	      
	      // click on row in table to populate textfields
	      table.addMouseListener(new MouseAdapter() {
	      	public void mouseClicked(MouseEvent e) 
	      	{
	      		 
	      		try {
	      			String url = "jdbc:mysql://localhost:8889/ElectricStore";
	      	      String user = "root";
	      	      String password = "root";
	      	    
	      	      Connection con = DriverManager.getConnection(url, user, password);
	      	      
	      	      
	      		      PreparedStatement ps;
	      	      	ResultSet rs; 
					int row = table.getSelectedRow();
					String tblclick = (table.getModel().getValueAt(row, 0).toString());
					String sql = "select * from customer where CustomerID = "+tblclick+"";
					 ps = con.prepareStatement(sql);
					rs = ps.executeQuery();
					if(rs.next())
					{
						String id = String.valueOf(rs.getInt("CustomerID"));
						txtid.setText(id);
						
						String address = rs.getString("Address");
						txtaddress.setText(address); 
						
						String name = rs.getString("Name");
						txtname.setText(name);
						
						
						
						String phoneno = String.valueOf(rs.getInt("PhoneNo"));
						txtphone.setText(phoneno);
						
						
						
					
						
						
						
						
						
						
						
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	      	}
	      	
	      });
	      
	     // shows black grid lines on table
	      table.setShowGrid(true);
	      table.setShowVerticalLines(true);
	      

	      model.setColumnIdentifiers(columns); 
			table.setModel(model);
			
			// determines the colour and font of the table
			table.setBackground(Color.white);
			table.setForeground(Color.black);
			table.setSelectionBackground(Color.red); // row turns red when selected
			table.setGridColor(Color.black); 
			table.setSelectionForeground(Color.white); 
			table.setFont(new Font("Tahoma", Font.PLAIN, 17));
			table.setRowHeight(30);
			table.setAutoCreateRowSorter(true);
			
	        JScrollPane pane = new JScrollPane(table);
			pane.setForeground(Color.red); 
			pane.setBackground(Color.WHITE);
			pane.setBounds(413,6,454,346);
			panel.add(pane);
			
			frame.getContentPane().add(panel);
			
			// add button
			JButton addButton = new JButton("Add");
			addButton.setBounds(352, 398, 141, 36);
			panel.add(addButton);
				
			// delete button
			JButton deleteButton = new JButton("Delete");
			deleteButton.setBounds(526, 398, 141, 36);
			panel.add(deleteButton);
			
			
			// update button
			JButton updateButton = new JButton("Update");
			updateButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
						
					
					
try {
						
						String name = txtname.getText(); 
						String address = txtaddress.getText();
						String phone = txtphone.getText();
						

						
						
					// update selected row from table 
						Connection con = DriverManager.getConnection(url, user, password);
						int row = table.getSelectedRow();
						String tblclick = (table.getModel().getValueAt(row, 0).toString());
					    PreparedStatement ps = con.prepareStatement("UPDATE customer SET Name=?, Address=?, PhoneNo=? WHERE CustomerID= "+tblclick);
						ps.setString(1, name);
						ps.setString(2, address);
						ps.setString(3, phone);
						
						
						
						int record = ps.executeUpdate();
						
						if(record == 1) 
						{
							JOptionPane.showMessageDialog(null, "Record updated");
							txtname.setText("");
							txtaddress.setText("");
							txtphone.setText("");
							txtname.requestFocus();
							frame.dispose();
							showFrame();

						}
						else
						{
							JOptionPane.showMessageDialog(null, "Record Failed");
						}
							
							
						
						
						
						
						} catch (SQLException e1) {
							
							e1.printStackTrace();
						}
						
						
				}
						
						
				
					
				
				});		
				
			
			updateButton.setBounds(698, 398, 141, 36);
			panel.add(updateButton);
			
			JButton menuButton = new JButton("Menu");
			menuButton.setBounds(930, 398, 141, 36);
			panel.add(menuButton);
			
			txtid = new JTextField();
			txtid.setColumns(10);
			txtid.setBounds(152, 60, 190, 42);
			panel.add(txtid);
			
			
			// menu button 
			menuButton.addActionListener(new ActionListener() 
					{
				public void actionPerformed(ActionEvent e)
				{
					frame.dispose();
					Menu.menuFrame();
				}
					});
			
			
			
			
			deleteButton.addActionListener(new ActionListener()
					{
				public void actionPerformed(ActionEvent e)
				{
					Statement ps;
					String query = "DELETE FROM customer WHERE CustomerID = " + txtid.getText();
					//executeSQLQuery(query, "Deleted");
					
					try {
						Connection con = DriverManager.getConnection(url, user, password);
							ps = con.createStatement();
							if(ps.executeUpdate(query) == 1)
							{
								JOptionPane.showMessageDialog(null, "Record deleted");
								frame.dispose();
								showFrame();
							}
							else
								JOptionPane.showMessageDialog(null, "Record not deleted");
							
					} catch(SQLException e1) {
						e1.printStackTrace();
					}
				}
					});
			
			// add button
			addButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					try {
					
					String Name = txtname.getText(); 
					String Address = txtaddress.getText();
					String PhoneNo = txtphone.getText();
					
					
					Connection con = DriverManager.getConnection(url, user, password);
				    PreparedStatement ps = con.prepareStatement("insert into customer(Name, PhoneNo, Address) VALUES (?,?,?)");
				    
				    // if textfields are empty, display a message telling the user 
				    if(Name.isEmpty() || Address.isEmpty() || PhoneNo.isEmpty()) 
				    {
				    	JOptionPane.showMessageDialog(null, "Text fields are empty");
				    	return;
				    }
				    // Allows only numbers to be entered for phone number textfield 
				    try {
				    	Integer x = Integer.parseInt(PhoneNo);
				    } catch (NumberFormatException e1) {
				    	JOptionPane.showMessageDialog(null, "Please enter a number for phone number");
				    	
				    }
				    
				   
				    
				    
					ps.setString(1, Name);
					ps.setString(2, PhoneNo);
					ps.setString(3, Address);
					
					
					int record = ps.executeUpdate();
					
					// add new customer
					if(record == 1) 
					{
						JOptionPane.showMessageDialog(null, "Record Added");
						txtaddress.setText("");
						txtphone.setText("");
						txtname.setText("");
						txtaddress.requestFocus();
						frame.dispose();
						showFrame();

					}
					else
					{
						JOptionPane.showMessageDialog(null, "Record Failed");
					}
						
						
					
					
					
					
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				}
			});		
			
			
		
		
		
	
    
    } catch(SQLException e) {
      e.printStackTrace();
    } 
    
  }
}








