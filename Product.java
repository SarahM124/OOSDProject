package entities;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Product {

	private JFrame frame;

	/**
	 * Launch the application.
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Product window = new Product();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public Product() throws SQLException {
		initialize();
	}
	
	static Connection con;
	static PreparedStatement ps;
	private JTextField txtmake;
	private JTextField txtdesc;
	private JTextField txtprice;
	private JTextField txtqty;
	
	
	public void Connect() 
	{
	
	      
		try {
			/* Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:8889/ElectricStore", "root", "root");
			*/
			String url = "jdbc:mysql://localhost:8889/ElectricStore";
		      String user = "root";
		      String password = "root";
		    
		      Connection connect = DriverManager.getConnection(url, user, password);
		    
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Product ID");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel.setBounds(39, 65, 103, 28);
		frame.getContentPane().add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(154, 66, 103, 30);
		frame.getContentPane().add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Make");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(39, 128, 61, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		txtmake = new JTextField();
		txtmake.setBounds(154, 120, 136, 30);
		frame.getContentPane().add(txtmake);
		txtmake.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Description");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(39, 186, 90, 28);
		frame.getContentPane().add(lblNewLabel_2);
		
		txtdesc = new JTextField();
		txtdesc.setColumns(10);
		txtdesc.setBounds(154, 188, 136, 30);
		frame.getContentPane().add(txtdesc);
		
		JLabel lblNewLabel_3 = new JLabel("Price");
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(39, 246, 90, 28);
		frame.getContentPane().add(lblNewLabel_3);
		
		txtprice = new JTextField();
		txtprice.setColumns(10);
		txtprice.setBounds(154, 248, 136, 30);
		frame.getContentPane().add(txtprice);
		
		JLabel lblNewLabel_4 = new JLabel("Quantity");
		lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(39, 310, 90, 16);
		frame.getContentPane().add(lblNewLabel_4);
		
		txtqty = new JTextField();
		txtqty.setColumns(10);
		txtqty.setBounds(154, 306, 136, 30);
		frame.getContentPane().add(txtqty);
		
		// table
		
		String query = "SELECT * FROM product";
	    
	      Statement stm = con.createStatement();
	      ResultSet res = stm.executeQuery(query);
	      
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
	      JTable table = new JTable(model);
	      table.setShowGrid(true);
	      table.setShowVerticalLines(true);
	      JScrollPane pane = new JScrollPane(table);
	      JPanel panel = new JPanel();
	      panel.setLayout(null);
	      panel.add(pane);
	      frame.getContentPane().add(panel);
	      
	      
	      model.setColumnIdentifiers(columns); 
			table.setModel(model);
			
			table.setBackground(Color.white);
			table.setForeground(Color.black);
			table.setSelectionBackground(Color.green); 
			table.setGridColor(Color.black); 
			table.setSelectionForeground(Color.white); 
			//table.setFont(new Font("Tahoma", Font.PLAIN, 17));
			table.setRowHeight(30);
			table.setAutoCreateRowSorter(true);
			
			//JScrollPane pane = new JScrollPane(table);
			
			pane.setForeground(Color.red); 
			pane.setBackground(Color.WHITE);
			pane.setBounds(413,6,454,346); 
	}
}
