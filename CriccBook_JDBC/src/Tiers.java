import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class Tiers extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private Connection conn = null;
	private Statement stmt=null;
	private ResultSet rs;
	
	private JTable ct;
	private JLabel label1,tid,seats;
	private JTextField tier,seat;
	private JButton book;
	private String CID,MID,SID;
	
	int n;
	
	Tiers(String CID,String MID,String SID)
	{
		try 
		{
			this.CID=CID;this.MID=MID;this.SID=SID;
			
			//Loading Driver
			Class.forName("com.mysql.jdbc.Driver");
			String DB_URL = "jdbc:mysql://localhost:3306/CriccBook";
          
			// Open a connection
			conn = DriverManager.getConnection(DB_URL,"root","root");
	        stmt = conn.createStatement();
	        
	        rs = stmt.executeQuery("select tiers from Stadium where sid='"+SID+"';");
	        while(rs.next()){
				n=rs.getInt(1);				
			}
	        
	        Object[][] rows = new Object[n][4];
	        
	        rs = stmt.executeQuery("select tid,tname,seats from Tier where sid='"+SID+"';");
	        int i=0;
			while(rs.next()){
				rows[i][0] = rs.getString(1);rows[i][1] = rs.getString(2);
				rows[i][2] = rs.getString(3);
				i++;				
			}
			
			for(int p=0;p<n;p++){
				rs = stmt.executeQuery("select price from Seat where seat_id='A_01' and tid='"+rows[p][0]+"' and sid='"+SID+"';");
				while(rs.next()){
					rows[p][3]=rs.getString(1);
				}				
			}
			
			label1 = new JLabel("Stadium Tier Details");
			tid = new JLabel("Tier ID:");
			seats = new JLabel("No.of Seats:");
			
			tier = new JTextField(5);
			seat = new JTextField(5);
			
			book = new JButton("Book Now");
	        
			String[] cols = new String[]{"Tier ID","Tier Name","Available Seats","Price"};

			ct = new JTable(rows,cols);
			
			JScrollPane scrollP = new JScrollPane(ct);
			
			DefaultTableCellRenderer tableRenderer = new DefaultTableCellRenderer();
			tableRenderer.setHorizontalAlignment(JLabel.CENTER); //Aligning the table data centrally.
			ct.setDefaultRenderer(Object.class, tableRenderer);
			
			
			scrollP.setBorder(BorderFactory.createEmptyBorder()); //How to remove the border of JScrollPane.
			scrollP.setPreferredSize(new Dimension(480, 140));
			
			add(label1);
			add(scrollP);
			add(tid);add(tier);
			add(seats);add(seat);
			add(book);
						
			registerListeners();
			
			setTitle("Tiers");     
			setSize(530,280);
			setLayout(new FlowLayout());
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setVisible(true);
		}
		catch(Exception e){ System.out.println(e);}  	
	}
	
	public void registerListeners()
	{
		book.addActionListener(new ActionListener()
		{  
			public void actionPerformed(ActionEvent ae)
			{
				try
				{
					int nos = Integer.parseInt(seat.getText());
					String ts = tier.getText();
					int t = ts.charAt(4) - '0';
					if(nos <=0 || nos > 10){
						JOptionPane.showMessageDialog(Tiers.this, "Invalid Seats!!", "WARNING", JOptionPane.WARNING_MESSAGE);
					}
					if(t <=0 || t > n)
					{
						JOptionPane.showMessageDialog(Tiers.this, "Invalid Tier ID!!", "WARNING", JOptionPane.WARNING_MESSAGE);
					}
					else{
						JOptionPane.showMessageDialog(Tiers.this,"Booking Successful!!", "CriccBook", JOptionPane.INFORMATION_MESSAGE);
						dispose();
					}
				}
				catch(Exception E){ 
					JOptionPane.showMessageDialog(Tiers.this, "Invalid Seats!!", "WARNING", JOptionPane.WARNING_MESSAGE);
					System.out.println(E);
				}
	        }  
	    });
	}
}


