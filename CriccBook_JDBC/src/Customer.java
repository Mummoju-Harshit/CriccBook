import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Customer extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private ImageIcon Bimg;
	private JLabel bg,select,title;
	private JComboBox CB;
	private JMenuBar mb;
	private JMenu Account;
	private JMenuItem Details,History,Logout;
	
	private String ID,Pass;
	
	Customer(String ID,String Pass)
	{
		this.ID=ID;
		this.Pass=Pass;
		
		initializeComponents();
    	settingBounds();
    	settingFont();
    	addComponents();
    	registerListeners();
    	
		
		setTitle("Customer");     
        setSize(600,575);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true); 
		
	}
	
	public void initializeComponents(){
		Bimg = new ImageIcon("F:/CriccBook/src/IPL.png");
		bg = new JLabel(Bimg);

		mb = new JMenuBar();
    	Account = new JMenu("My Account");
		Details = new JMenuItem("Details");
    	History = new JMenuItem("History");
    	Logout = new JMenuItem("Logout");
    	
    	Account.add(Details);Account.add(History);Account.add(Logout);
    	mb.add(Account);
		
		title = new JLabel("Start Your Bookings");
    	select = new JLabel("Select Team:");
		
    	String[] teams = {"CSK", "PBKS", "MI", "RCB", "DC", "KKR", "RR", "SRH"};
    	CB = new JComboBox(teams);
    	
	}
	
	public void settingBounds(){
		bg.setSize(600,575);

		title.setBounds(210,10,200,20);

    	select.setBounds(200,180,120,20);
    	CB.setBounds(320,180,70,25);
	}
	
	public void settingFont(){

		select.setFont(new Font("Times New Roman", Font.BOLD|Font.ITALIC, 20));
    	select.setForeground(new Color(255,0,0));

		title.setFont(new Font("Times New Roman", Font.BOLD|Font.ITALIC, 22));
    	title.setForeground(new Color(255,0,0));
	}
	
	public void addComponents(){
		add(bg);
		
		setJMenuBar(mb);

		bg.add(title);

		bg.add(select);
		bg.add(CB);
	}
	
	public void registerListeners(){
		Details.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				
				String Details = "\nUsername: " + ID + "Password: " + Pass;
				
				JOptionPane.showMessageDialog(Customer.this,Details,"Customer Details", JOptionPane.INFORMATION_MESSAGE);
	        }  
	    });
		
		Logout.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				new CriccBook();
				dispose();
	        }  
	    });
		
		CB.addActionListener(this);
		
	}	
	
	public void actionPerformed(ActionEvent AE)
	{
		JComboBox comboBox = (JComboBox)AE.getSource();
        String Team = (String)comboBox.getSelectedItem();
		new TMatches(ID,Team);
	}
}

