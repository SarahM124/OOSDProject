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

public class OrderMenu

{
	// variables 
	private static JTextField txtaddress;
	private static JTextField txtphone;
	private static JTextField txtname;
	 Connection con;
     PreparedStatement ps;
      private static JTextField txtid;
	
	
	
	
	public OrderMenu()
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
	JFrame frame = new JFrame("Order menu ");
  	frame.getContentPane().setLayout(null);
  	
  	JButton btnNewButton = new JButton("Create order");
  	btnNewButton.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			
  			frame.dispose();
  			CreateOrder.showFrame();
  		}
  	});
  	btnNewButton.setFont(new Font("Arial", Font.PLAIN, 18));
  	btnNewButton.setBounds(268, 92, 187, 51);
  	frame.getContentPane().add(btnNewButton);
  	
  	// view order button
  	JButton btnViewOrder = new JButton("View order");
  	btnViewOrder.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			
  			frame.dispose();
  			ViewOrder.showFrame();
  		}
  	});
  	btnViewOrder.setFont(new Font("Arial", Font.PLAIN, 18));
  	btnViewOrder.setBounds(268, 182, 187, 51);
  	frame.getContentPane().add(btnViewOrder);
  	
  	// menu button
  	JButton btnMenu = new JButton("Menu");
  	btnMenu.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			frame.dispose();
			Menu.menuFrame();
  		}
  	});
  	btnMenu.setFont(new Font("Arial", Font.PLAIN, 18));
  	btnMenu.setBounds(268, 290, 187, 51);
  	frame.getContentPane().add(btnMenu);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int width = (int) screenSize.getWidth();
    int height = (int) screenSize.getHeight();

   frame.setSize(700, 600);
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   frame.setVisible(true);
	      
   JPanel panel = new JPanel();
   panel.setLayout(null);
   panel.setBounds((width -1200)/2, (height - 800)/2, 1200, 800);


	
	  
    try 
  {
		
		String url = "jdbc:mysql://localhost:8889/ElectricStore";
	      String user = "root";
	      String password = "root";
	    
	      Connection connect = DriverManager.getConnection(url, user, password);
	    
	   
						
						
					}
				 catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	      	
	    
			
		
		
	
    
  }
    
  }









