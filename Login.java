package entities;



import javax.swing.JFrame;

public class Login extends LoginScreen {
		
		public static void main(String[] a) 
		{
			LoginScreen frame = new LoginScreen(); 
			frame.setTitle("Login Frame");
			frame.setVisible(true);
			frame.setBounds(10, 10, 370, 600);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setResizable(false);
		}
	}



