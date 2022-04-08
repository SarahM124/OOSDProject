package entities;

import java.awt.EventQueue;

import javax.swing.JFrame;



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

public class ViewOrder

{
	// variables 
	private static JTextField txtname;
	private static JTextField txtaddress;
	private static JTextField txtphone;
	 Connection con;
     PreparedStatement ps;
      private static JTextField txtid;
	
	
	
	
	public ViewOrder()
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


		
	 
	  
    try 
  {
		// log into database 
		String url = "jdbc:mysql://localhost:8889/ElectricStore";
	      String user = "root";
	      String password = "root";
	    
	      Connection connect = DriverManager.getConnection(url, user, password);
	      	
	      		String query = "SELECT orders.orderID, customer.Name, customer.Address, customer.PhoneNo FROM orders INNER JOIN customer ON orders.orderID = customer.CustomerID ";
	      		
	      		 Statement stm = connect.createStatement();
	   	      ResultSet res = stm.executeQuery(query);
	   	      Connection con;
	   	      PreparedStatement ps;
	   	      
	   	      // populates table with data from the product database
	   	      String columns[] = { "Order ID", "Customer name", "Customer Address", "Customer Phone Number"};
	   	      String data[][] = new String[8][5];
	   	    
	   	      int i = 0;
	   	      while (res.next()) {
	   	        int id = res.getInt("orderID");
	   	        String name = res.getString("Name");
	   	     String address = res.getString("Address");
	   	  int phoneno = res.getInt("PhoneNo");
	   	        
	   	        data[i][0] = id + "";
	   	        data[i][1] = name;
	   	        data[i][2] = address;
	   	        data[i][3] = phoneno + "";
	   	       
	   	        
	   	        i++;
	   	      }
	      
	    
	      DefaultTableModel model = new DefaultTableModel(data, columns);
	      JTable table = new JTable(model) {
	    	  public boolean isCellEditable(int row, int col) { // does not allow table cells to be editable
	    		  return false;
	    	  }
	      };
	      
	      
	      
	      // click on row in table to populate textfields
	     
	      
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
			pane.setBounds(271,6,454,346);
			panel.add(pane);
			
			frame.getContentPane().add(panel);
			
			
			
			JButton menuButton = new JButton("Menu");
			menuButton.setBounds(403, 424, 141, 36);
			panel.add(menuButton);
			
			
			
			
			
			menuButton.addActionListener(new ActionListener() 
					{
				public void actionPerformed(ActionEvent e)
				{
					frame.dispose();
					Menu.menuFrame();
				}
					});
			
			
			
			
			
			
			
		
		
		
	
    
    } catch(SQLException e) {
      e.printStackTrace();
    } 
    
  }
}








