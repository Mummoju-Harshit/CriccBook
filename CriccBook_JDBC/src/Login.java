import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Login extends JFrame implements ActionListener 
{
	private static final long serialVersionUID = 1L;
	
	private ImageIcon Bimg;
	private JLabel bg,title,c_id,c_pass;
	private JTextField cid;
	private JPasswordField cpass;
	private JButton submit,reset;
	private JMenuBar mb;
	private JMenu Menu;
	private JMenuItem Home,Exit;
	
	private SQL_Connection SQL;
	
	Login()
	{			
		initializeComponents();
    	settingBounds();
    	settingFont();
    	addComponents();
    	registerListeners();
    	
    	setTitle("Login");     
        setSize(600,400);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        
	}
	
	public void initializeComponents()
	{
		Bimg=new ImageIcon("F:/CriccBook/src/Reg.jpg");   	
    	bg=new JLabel(Bimg);
    	
    	mb = new JMenuBar();
    	Menu= new JMenu("Menu");
    	Home = new JMenuItem("Home");
    	Exit = new JMenuItem("Exit");
    	
    	Menu.add(Home);Menu.add(Exit);
    	mb.add(Menu);
    	
    	title =new JLabel("Sign-In");
    	c_id = new JLabel("Username:");
    	c_pass = new JLabel("Password:");
    	
    	cid=new JTextField();
    	
    	cpass=new JPasswordField();
    	cpass.setEchoChar('*');
    	  	
    	submit = new JButton("SUBMIT");
    	reset = new JButton("RESET");
	}
	
	public void settingBounds()
	{
		bg.setSize(600,400);
		
    	title.setBounds(230,30,300,30);
    	c_id.setBounds(180,80,110,30);
    	c_pass.setBounds(180,150,100,30);
    	
    	cid.setBounds(280,80,100,30);
    	cpass.setBounds(280,150,100,30);
    	
    	submit.setBounds(170,230,100,30);
    	reset.setBounds(290,230,100,30);
	}
	
	public void settingFont()
	{
		title.setFont(new Font("Times New Roman", Font.BOLD|Font.ITALIC, 22));
		title.setForeground(new Color(255,0,0));
		
		c_id.setFont(new Font("Times New Roman", Font.BOLD, 18));
		c_id.setForeground(new Color(0,255,0));
		
		c_pass.setFont(new Font("Times New Roman", Font.BOLD, 18));
		c_pass.setForeground(new Color(0,255,0));
	}
	
	public void registerListeners()
	{
		Home.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				new CriccBook();
				dispose();
	        }  
	    });
		
		Exit.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				System.exit(1);
	        }  
	    });
		
		submit.addActionListener(this);
		reset.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
	            cid.setText("");  
	            cpass.setText("");
	        }  
	    });
	}
	
	public void addComponents()
	{
		setJMenuBar(mb);
		add(bg);
		
		bg.add(title);
		bg.add(c_id);
		bg.add(c_pass);
		
		bg.add(cid);
		bg.add(cpass);
		
		bg.add(submit);
		bg.add(reset);
	}
	
	public void actionPerformed(ActionEvent AE)
	{
		SQL=new SQL_Connection();
		String ID=cid.getText();
		char[] pw=cpass.getPassword();
		String Password=String.valueOf(pw);
		
		
		
		if(SQL.login(ID,Password))
		{
			JOptionPane.showMessageDialog(this,"LOGIN SUCCESSFUL","CriccBook", JOptionPane.INFORMATION_MESSAGE);
			
			if(ID.equals("ADMIN"))
			{
				new Admin();
				dispose();
			}
			else
			{
				new Customer(ID,Password);
				dispose();					
			}

		}
		else
		{
			JOptionPane.showMessageDialog(this,"Invalid ID (or) Password!!","WARNING", JOptionPane.WARNING_MESSAGE);			
		}
	}
}