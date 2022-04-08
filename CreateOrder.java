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

public class CreateOrder

{
	
	private static JTextField txtproductid;
	private static JTextField txtcustid;
	private static JTextField txtqty;
	private static JTextField txtorderprice;
	
	
	
	
	public CreateOrder()
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
	JFrame frame = new JFrame("Create order");
  	frame.getContentPane().setLayout(null);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int width = (int) screenSize.getWidth();
    int height = (int) screenSize.getHeight();

   frame.setSize(800, 600);
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   frame.setVisible(true);
	      
   JPanel panel = new JPanel();
   panel.setLayout(null);
   panel.setBounds((width -1200)/2, (height - 800)/2, 1200, 800);


	
	  
	txtproductid = new JTextField();
	txtproductid.setBounds(314, 82, 219, 44);
	frame.getContentPane().add(txtproductid);
	txtproductid.setColumns(10);
	
	txtcustid = new JTextField();
	txtcustid.setColumns(10);
	txtcustid.setBounds(314, 159, 219, 44);
	frame.getContentPane().add(txtcustid);
	
	txtqty = new JTextField();
	txtqty.setColumns(10);
	txtqty.setBounds(314, 236, 219, 44);
	frame.getContentPane().add(txtqty);
	
	txtorderprice = new JTextField();
	txtorderprice.setColumns(10);
	txtorderprice.setBounds(314, 313, 219, 44);
	frame.getContentPane().add(txtorderprice);
	
	JLabel lblNewLabel = new JLabel("Product ID");
	lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 18));
	lblNewLabel.setBounds(67, 80, 159, 32);
	frame.getContentPane().add(lblNewLabel);
	
	JLabel lblCustomerId = new JLabel("Customer ID");
	lblCustomerId.setFont(new Font("Arial", Font.PLAIN, 18));
	lblCustomerId.setBounds(67, 159, 159, 32);
	frame.getContentPane().add(lblCustomerId);
	
	JLabel lblOrderQuantity = new JLabel("Order quantity");
	lblOrderQuantity.setFont(new Font("Arial", Font.PLAIN, 18));
	lblOrderQuantity.setBounds(67, 250, 159, 32);
	frame.getContentPane().add(lblOrderQuantity);
	
	JLabel lblOrderPrice = new JLabel("Order Price");
	lblOrderPrice.setFont(new Font("Arial", Font.PLAIN, 18));
	lblOrderPrice.setBounds(67, 327, 159, 32);
	frame.getContentPane().add(lblOrderPrice);
	
	// create order button
	JButton btnNewButton = new JButton("Create order");
	btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
	try {
		String url = "jdbc:mysql://localhost:8889/ElectricStore";
	      String user = "root";
	      String password = "root";
				
				String productid = txtproductid.getText(); 
				String txtcust = txtcustid.getText();
				String quantity = txtqty.getText();
				String txtprice = txtorderprice.getText();
				
				// insert data into orders table
				Connection con = DriverManager.getConnection(url, user, password);
			    PreparedStatement ps = con.prepareStatement("insert into orders(productID, customerID, quantity, orderPrice) VALUES (?,?,?,?)");
				ps.setString(1, productid);
				ps.setString(2, txtcust);
				ps.setString(3, txtprice);
				ps.setString(4, quantity);
				int record = ps.executeUpdate();
				
				if(record == 1) 
				{
					JOptionPane.showMessageDialog(null, "Record Added");
					
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
			
			
			
	
	btnNewButton.setBounds(314, 459, 159, 44);
	frame.getContentPane().add(btnNewButton);
	
	JButton btnMenu = new JButton("Menu");
	btnMenu.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			frame.dispose();
			Menu.menuFrame();
			
		}
	});
	btnMenu.setBounds(497, 459, 159, 44);
	frame.getContentPane().add(btnMenu);
}


	    	
		
	
    
    
  }









