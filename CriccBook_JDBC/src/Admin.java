import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Admin extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private ImageIcon Bimg;
	private JLabel bg,title;
	private JMenuBar mb;
	private JMenu Account;
	private JMenuItem Schedule,Stadium,Logout;
	private JButton customers,bookings;
	
	Admin()
	{
		initializeComponents();
    	settingBounds();
    	settingFont();
    	addComponents();
    	registerListeners();
    	
		
		setTitle("Admin");     
        setSize(600,575);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
	}
	
	public void initializeComponents()
	{
		Bimg = new ImageIcon("F:/CriccBook/src/Admin.png");
		bg = new JLabel(Bimg);

		mb = new JMenuBar();
    	Account = new JMenu("My Account");
		Schedule = new JMenuItem("Schedule");
    	Stadium = new JMenuItem("Stadium");
    	Logout = new JMenuItem("Logout");
    	
    	Account.add(Schedule);Account.add(Stadium);Account.add(Logout);
    	mb.add(Account);
    	
    	title = new JLabel("Admin Panel");
    	
    	customers = new JButton("Customers");
    	bookings = new JButton("Bookings");
	}
	
	public void settingBounds()
	{
		bg.setSize(600,575);
		title.setBounds(230,120,120,20);
		
		customers.setBounds(240,180,100,50);
		bookings.setBounds(240,260,100,50);
	}
	
	public void settingFont()
	{
		title.setFont(new Font("Times New Roman", Font.BOLD|Font.ITALIC, 22));
    	title.setForeground(new Color(255,0,0));
	}
	
	public void addComponents()
	{
		setJMenuBar(mb);
		
		add(bg);
		bg.add(title);
		bg.add(customers);
		bg.add(bookings);
	}
	
	public void registerListeners()
	{
		Schedule.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				new Schedule();
	        }  
	    });
		
		Stadium.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				new Stadium();
	        }  
	    });
		
		customers.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				new CTable();
	        }  
	    });
		
		Logout.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				new CriccBook();
				dispose();
	        }  
	    });
	}
	
}
