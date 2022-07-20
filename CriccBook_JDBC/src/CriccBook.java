import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CriccBook extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private ImageIcon Bimg;
	private JLabel bg,title;
	private JButton register,login;
	private JMenuBar mb;
	private JMenu Menu;
	private JMenuItem About,Admin,Exit;
    
	CriccBook()
    {   	
    	initializeComponents();
    	settingBounds();
    	settingFont();
    	addComponents();
    	registerListeners();
    	setLookandFeel();
    	
    	setTitle("CriccBook");     
        setSize(650,650);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);        
    }
    
    public void initializeComponents()
    {
    	Bimg = new ImageIcon("F:/CriccBook/src/Stadium.jpg");
    	
    	bg = new JLabel(Bimg);
    	title = new JLabel("Welcome To CriccBook");
    	
    	mb = new JMenuBar();
    	Menu= new JMenu("Menu");
    	About = new JMenuItem("About");
    	Admin = new JMenuItem("Admin");
    	Exit = new JMenuItem("Exit");
    	
    	Menu.add(About);Menu.add(Admin);Menu.add(Exit);
    	mb.add(Menu);
        
    	register = new JButton("Register");
    	login = new JButton("Login");
    }
    
    public void settingBounds()
    {
    	bg.setSize(650,650);
    	title.setBounds(220,150,300,30);
    	register.setBounds(250,200,150,50);
    	login.setBounds(250,300,150,50);
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
    	bg.add(register);
    	bg.add(login);
    }
    
    public void registerListeners()
	{
    	
    	Admin.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				new Login();
				dispose();
	        }  
	    });
    	
    	About.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				JOptionPane.showMessageDialog(CriccBook.this,"CriccBook is an Desktop application.\n"
						+ "It helps Customers to book Cricket match tickets in advance and enjoy the match.\n"
						+ "Customer must be a registered user to do so.\nTHANK YOU!!","CRICCBOOK", JOptionPane.INFORMATION_MESSAGE);
	        }  
	    });
    	
    	Exit.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				System.exit(1);
	        }  
	    });
    	
		register.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){ 
	            new Register();
	            dispose();
	        }  
	    });
		
		login.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				new Login();
				dispose();
	        }  
	    });
	}
    
    public void setLookandFeel()
    {
    	try 
        {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");   		
            SwingUtilities.updateComponentTreeUI(CriccBook.this);
        } 
        catch(Exception e2)
        { 
            System.out.println("Look and Feel not set"); 
        } 
    }
    
    public static void main(String args[]) {
        new CriccBook();  
    }
}

