import java.awt.Color;

import javax.swing.JFrame;

public class Main 
{
	public static void main(String[] args) 
	{
		
		JFrame obj = new JFrame();
		Gameplay gameplay = new Gameplay();
		
		
		
		obj.setBounds(10, 10, 905, 700);
		obj.setBackground(Color.DARK_GRAY);
		obj.setVisible(true);
		obj.setResizable(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		obj.add(gameplay);
		
		
	}
}
