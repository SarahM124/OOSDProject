package entities;


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

public class ProductFrame 

{
	// variables 
	private static JTextField txtmake;
	private static JTextField txtdesc;
	private static JTextField txtqty;
	private static JTextField txtprice;
	 Connection con;
     PreparedStatement ps;
      private static JTextField txtid;
	
	
	
	
	public ProductFrame()
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
	JFrame frame = new JFrame("Product Screen");
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
		JLabel lblNewLabel = new JLabel("Product ID");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel.setBounds(6, 67, 97, 31);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Make");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(6, 149, 89, 31);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("Quantity"); 
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(6, 261, 82, 22);
		panel.add(lblNewLabel_3);
		
		
		JLabel lblNewLabel_2 = new JLabel("Description");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(6, 207, 97, 22);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("Price");
		lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(6, 315, 70, 22);
		panel.add(lblNewLabel_4);
		
		// textfields 

		txtdesc = new JTextField(); // description textfield
		txtdesc.setBounds(105, 198, 190, 42);
		panel.add(txtdesc);
		txtdesc.setColumns(10);
		
		txtqty = new JTextField(); // quantity textfield
		txtqty.setBounds(105, 252, 190, 42);
		panel.add(txtqty);
		txtqty.setColumns(10);
		

		txtmake = new JTextField(); // make textfield
		txtmake.setBounds(107, 138, 190, 42);
		panel.add(txtmake);
		txtmake.setColumns(10);
		
		
		
		txtprice = new JTextField(); // price textfield
		txtprice.setBounds(105, 306, 190, 42);
		panel.add(txtprice);
		txtprice.setColumns(10);
		
		
	 
	  
    try 
  {
		// log into database
		String url = "jdbc:mysql://localhost:8889/ElectricStore";
	      String user = "root";
	      String password = "root";
	    
	      Connection connect = DriverManager.getConnection(url, user, password);
	    
	      String query = "SELECT * FROM product";
	    
	      Statement stm = connect.createStatement();
	      ResultSet res = stm.executeQuery(query);
	      Connection con;
	      PreparedStatement ps;
	      
	      // populates table with data from the product database
	      String columns[] = { "Product ID", "Make", "Description", "Quantity" , "Price"};
	      String data[][] = new String[8][5];
	    
	      int i = 0;
	      while (res.next()) {
	        int id = res.getInt("productID");
	        String make = res.getString("make");
	        String desc = res.getString("description");
	        int quantity = res.getInt("quantity");
	        double price = res.getDouble("price");
	        data[i][0] = id + "";
	        data[i][1] = make;
	        data[i][2] = desc;
	        data[i][3] = quantity + "";
	        data[i][4] = price + "";
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
					String sql = "select * from product where productID = "+tblclick+"";
					 ps = con.prepareStatement(sql);
					rs = ps.executeQuery();
					if(rs.next())
					{
						String id = String.valueOf(rs.getInt("productID"));
						txtid.setText(id);
						
						String make = rs.getString("make");
						txtmake.setText(make);
						
						String desc = rs.getString("description");
						txtdesc.setText(desc); 
						
						String price = String.valueOf(rs.getDouble("price"));
						txtprice.setText(price);
						
						String quantity = String.valueOf(rs.getInt("quantity"));
						txtqty.setText(quantity);
						
						
						
						
						
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
			
			
			// Buttons
			JButton btnNewButton = new JButton("Search");
			btnNewButton.setBounds(107, 398, 124, 33);
			panel.add(btnNewButton);
			
			JButton addButton = new JButton("Add");
			addButton.setBounds(352, 398, 141, 36);
			panel.add(addButton);
				
			JButton deleteButton = new JButton("Delete");
			deleteButton.setBounds(526, 398, 141, 36);
			panel.add(deleteButton);
			
			
			
			JButton updateButton = new JButton("Update");
			updateButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
					   
					   
					   
					   
					   
					
					try {
						
						String make = txtmake.getText(); 
						String description = txtdesc.getText();
						String quantity = txtqty.getText();
						String price = txtprice.getText();

						
						
					// updates the selected row 
						Connection con = DriverManager.getConnection(url, user, password);
						int row = table.getSelectedRow();
						String tblclick = (table.getModel().getValueAt(row, 0).toString());
					    PreparedStatement ps = con.prepareStatement("UPDATE product SET make=?, description=?, price=?, quantity=? WHERE productID= "+tblclick);
						ps.setString(1, make);
						ps.setString(2, description);
						ps.setString(3, price);
						ps.setString(4, quantity);
						
						
						int record = ps.executeUpdate();
						
						if(record == 1) 
						{
							JOptionPane.showMessageDialog(null, "Record updated");
							txtmake.setText("");
							txtdesc.setText("");
							txtqty.setText("");
							txtprice.setText("");
							txtmake.requestFocus();
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
				
			
			updateButton.setBounds(698, 398, 141, 36);
			panel.add(updateButton);
			
			JButton menuButton = new JButton("Menu");
			menuButton.setBounds(930, 398, 141, 36);
			panel.add(menuButton);
			
			txtid = new JTextField();
			txtid.setColumns(10);
			txtid.setBounds(107, 62, 190, 42);
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
			
			
			
			// delete button
			deleteButton.addActionListener(new ActionListener()
					{
				public void actionPerformed(ActionEvent e)
				{
					Statement ps;
					String query = "DELETE FROM product WHERE productID = " + txtid.getText();
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
					
					String make = txtmake.getText(); 
					String description = txtdesc.getText();
					String quantity = txtqty.getText();
					String price = txtprice.getText();
					
					Connection con = DriverManager.getConnection(url, user, password);
				    PreparedStatement ps = con.prepareStatement("insert into product(make, description, price, quantity) VALUES (?,?,?,?)");
				    
				    // if textfields are empty, display a message telling the user 
				    if(make.isEmpty() || description.isEmpty() || quantity.isEmpty() || price.isEmpty()) 
				    {
				    	JOptionPane.showMessageDialog(null, "Text fields are empty");
				    	return;
				    }
				    // Allows only numbers to be entered for quantity textfield 
				    try {
				    	Integer x = Integer.parseInt(quantity);
				    } catch (NumberFormatException e1) {
				    	JOptionPane.showMessageDialog(null, "Please enter a number for quantity");
				    	
				    }
				    
				    // Allows only numbers to be entered for price textfield
				    try {
				    	Integer x = Integer.parseInt(price);
				    } catch (NumberFormatException e1) {
				    	JOptionPane.showMessageDialog(null, "Please enter a number for price");
				    	
				    }
					ps.setString(1, make);
					ps.setString(2, description);
					ps.setString(3, price);
					ps.setString(4, quantity);
					int record = ps.executeUpdate();
					
					if(record == 1) 
					{
						JOptionPane.showMessageDialog(null, "Record Added");
						txtmake.setText("");
						txtdesc.setText("");
						txtqty.setText("");
						txtprice.setText("");
						txtmake.requestFocus();
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







